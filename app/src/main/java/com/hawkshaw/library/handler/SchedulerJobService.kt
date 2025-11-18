package com.hawkshaw.library.handler

import android.app.job.JobParameters
import android.app.job.JobService
import android.os.Build
import com.hawkshaw.library.ktextensions.safeLaunch
import com.hawkshaw.library.logger.Logger
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import android.util.Log
import androidx.annotation.RequiresApi

/**
 * Job ID range for SchedulerJobService.
 * WorkManager requires a specific range of job IDs to be defined.
 * These IDs must be unique across the app and within the range 0-1000.
 */
const val JOB_ID_SCHEDULER = 1001

// If you need additional job IDs, define them here with unique values
// const val JOB_ID_ANOTHER_SERVICE = 1002

/**
 * JobService for scheduled tasks
 */
class SchedulerJobService : JobService() {
    private val TAG = "SchedulerJobService"
    private var job: Job? = null
    
    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d(TAG, "[DEBUG] onStartJob called. JobId: ${params?.jobId}, Extras: ${params?.extras}")
        // Existing Logger.v call
        Logger.v(TAG, "onStartJob", false, 4, null)
        
        // Start job in a coroutine
        Log.d(TAG, "[DEBUG] onStartJob: Launching coroutine for scheduled tasks.")
        job = GlobalScope.safeLaunch {
            Log.d(TAG, "[DEBUG] Coroutine started for job: ${params?.jobId}")
            try {
                // Perform scheduled tasks here
                Log.d(TAG, "[DEBUG] Coroutine: Performing scheduled tasks.")
                // Existing Logger.v call
                Logger.v(TAG, "Performing scheduled tasks", false, 4, null)
                
                // Finish the job
                Log.d(TAG, "[DEBUG] Coroutine: Scheduled tasks complete. Calling jobFinished with reschedule=false.")
                jobFinished(params, false)
                Log.d(TAG, "[DEBUG] Coroutine: jobFinished(false) called.")
            } catch (e: Exception) {
                Log.e(TAG, "[DEBUG] Coroutine: Exception caught in job: ${e.message}", e) // Debug log for exception
                // Existing Logger.e call
                Logger.e(TAG, "Error in job: ${e.message}", e, false, 12, null)
                Log.d(TAG, "[DEBUG] Coroutine: Calling jobFinished with reschedule=true due to exception.")
                jobFinished(params, true) // Reschedule on error
                Log.d(TAG, "[DEBUG] Coroutine: jobFinished(true) called after exception.")
            }
        }
        Log.d(TAG, "[DEBUG] onStartJob: Coroutine launched. Returning true (still doing work).")
        // Return true to indicate we're still doing work
        return true
    }
    
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d(TAG, "[DEBUG] onStopJob called. JobId: ${params?.jobId}, Reason: ${params?.stopReason}")
        // Existing Logger.v call
        Logger.v(TAG, "onStopJob", false, 4, null)
        
        // Cancel any running job
        if (job != null) {
            Log.d(TAG, "[DEBUG] onStopJob: Active job found. Attempting to cancel.")
            job?.cancel()
            Log.d(TAG, "[DEBUG] onStopJob: Job cancellation requested.")
        } else {
            Log.d(TAG, "[DEBUG] onStopJob: No active job to cancel.")
        }
        job = null
        Log.d(TAG, "[DEBUG] onStopJob: Job set to null. Returning true (to reschedule).")
        // Return true to reschedule the job
        return true
    }
}
