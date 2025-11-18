package com.hawkshaw.library.utils

import android.accessibilityservice.AccessibilityServiceInfo
import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.PowerManager
import android.view.accessibility.AccessibilityManager
import androidx.core.app.NotificationCompat
import com.hawkshaw.app.R
import com.hawkshaw.library.App
import com.hawkshaw.library.activities.TransparentActivity
import com.hawkshaw.library.features.accessibility.MainAccessibilityService
import com.hawkshaw.library.ktextensions.ContextKt
import com.hawkshaw.library.ktextensions.ExceptionsKt
import com.hawkshaw.library.preferences.Prefs
import android.util.Log // Added for logging

object MiscKt {
    private const val TAG = "MiscKtUtils" // Defined a TAG for logging

    @JvmStatic
    fun isAccessibilityEnabled(context: Context?): Boolean {
        Log.d(TAG, "[DEBUG] isAccessibilityEnabled(context) called. Context: $context")
        if (context == null) {
            Log.d(TAG, "[DEBUG] isAccessibilityEnabled: Context is null, returning false.")
            return false
        }
        val serviceName = "${context.packageName}/${MainAccessibilityService::class.java.canonicalName}"
        Log.d(TAG, "[DEBUG] isAccessibilityEnabled: Expected serviceName: \"$serviceName\"")

        val accessibilityManager = context.getSystemService(Context.ACCESSIBILITY_SERVICE) as? AccessibilityManager
        if (accessibilityManager == null) {
            Log.d(TAG, "[DEBUG] isAccessibilityEnabled: AccessibilityManager is null, returning false.")
            return false
        }
        
        val enabledServices = accessibilityManager.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_ALL_MASK)
        Log.d(TAG, "[DEBUG] isAccessibilityEnabled: Found ${enabledServices?.size ?: 0} enabled accessibility services.")

        for (service in enabledServices ?: emptyList()) {
            Log.d(TAG, "[DEBUG] isAccessibilityEnabled: Checking service.id: \"${service.id}\"")
            if (serviceName == service.id) {
                Log.d(TAG, "[DEBUG] isAccessibilityEnabled: Match found for \"$serviceName\". Returning true.")
                return true
            }
        }
        Log.d(TAG, "[DEBUG] isAccessibilityEnabled: No match found for \"$serviceName\". Returning false.")
        return false
    }

    @JvmStatic
    fun isAccessibilityEnabled(): Boolean {
        val appContext = App.getContext()
        Log.d(TAG, "[DEBUG] isAccessibilityEnabled() (no-arg) called. Using App.getContext(): $appContext")
        val result = isAccessibilityEnabled(appContext)
        Log.d(TAG, "[DEBUG] isAccessibilityEnabled() (no-arg) result: $result")
        return result
    }

    @JvmStatic
    fun isUserLoggedIn(): Boolean {
        Log.d(TAG, "[DEBUG] isUserLoggedIn() called.")
        val token = Prefs.getToken() // Prefs.getToken() already logs its own details
        val loggedIn = !token.isNullOrEmpty()
        Log.d(TAG, "[DEBUG] isUserLoggedIn: Token (first 10 chars): \"${token?.take(10)}...\", IsNullOrEmpty: ${token.isNullOrEmpty()}. Returning: $loggedIn")
        return loggedIn
    }

    @JvmStatic
    fun openBgActivity(context: Context, forceOpen: Boolean) {
        Log.d(TAG, "[DEBUG] openBgActivity called. Context: $context, forceOpen: $forceOpen")
        val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager?
        val isInteractive = powerManager?.isInteractive ?: true // Default to true if powerManager is null to potentially allow open
        Log.d(TAG, "[DEBUG] openBgActivity: PowerManager: $powerManager, isInteractive: $isInteractive")

        if (forceOpen || (powerManager != null && !isInteractive)) {
            Log.d(TAG, "[DEBUG] openBgActivity: Condition met (forceOpen=$forceOpen || !isInteractive=$isInteractive). Starting TransparentActivity.")
            val intent = Intent(context, TransparentActivity::class.java).apply {
                putExtra("OPEN_ACTIVITY_IN_FOREGROUND", true)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            Log.d(TAG, "[DEBUG] openBgActivity: Intent created: $intent, Extras: ${intent.extras?.let { bundle -> bundle.keySet().joinToString { key -> "$key=${bundle.get(key)}" } } ?: "null"}")
            context.startActivity(intent)
            Log.d(TAG, "[DEBUG] openBgActivity: startActivity called.")
        } else {
            Log.d(TAG, "[DEBUG] openBgActivity: Condition not met. Not starting TransparentActivity.")
        }
    }

    @JvmStatic
    fun openBgActivity(): Unit {
        val appContext = App.getContext()
        Log.d(TAG, "[DEBUG] openBgActivity() (no-arg) called. Using App.getContext(): $appContext, forceOpen: false")
        openBgActivity(appContext, false)
    }

    @JvmStatic
    @JvmOverloads
    fun startActivity(
        intent: Intent,
        title: String = "Settings",
        subtitle: String = "Click to view",
        context: Context = App.getContext()
    ) {
        Log.d(TAG, "[DEBUG] startActivity called. Intent: $intent, Title: \"$title\", Subtitle: \"$subtitle\", Context: $context")
        try {
            val canLaunch = ContextKt.canLaunchFromBg(context) // ContextKt.canLaunchFromBg logs its own details
            Log.d(TAG, "[DEBUG] startActivity: ContextKt.canLaunchFromBg result: $canLaunch")
            if (canLaunch) {
                Log.d(TAG, "[DEBUG] startActivity: Can launch from background. Calling context.startActivity.")
                context.startActivity(intent)
                Log.d(TAG, "[DEBUG] startActivity: context.startActivity called directly.")
            } else {
                Log.d(TAG, "[DEBUG] startActivity: Cannot launch from background. Calling startActivityWithNotification.")
                startActivityWithNotification(context, intent, title, subtitle)
                Log.d(TAG, "[DEBUG] startActivity: startActivityWithNotification called.")
            }
        } catch (e: Exception) {
            Log.e(TAG, "[DEBUG] startActivity: Exception caught: ${e.message}", e)
            ExceptionsKt.logNonFatal(e) // ExceptionsKt.logNonFatal likely logs as well
            Log.d(TAG, "[DEBUG] startActivity: Exception path. Calling startActivityWithNotification.")
            startActivityWithNotification(context, intent, title, subtitle)
            Log.d(TAG, "[DEBUG] startActivity: startActivityWithNotification called from exception path.")
        }
    }

    @JvmStatic
    @JvmOverloads
    fun startService(
        intent: Intent,
        title: String = "Settings",
        subtitle: String = "Click to view",
        context: Context = App.getContext()
    ) {
        Log.d(TAG, "[DEBUG] startService called. Intent: $intent, Title: \"$title\", Subtitle: \"$subtitle\", Context: $context")
        try {
            Log.d(TAG, "[DEBUG] startService: Calling context.startService.")
            context.startService(intent)
            Log.d(TAG, "[DEBUG] startService: context.startService called directly.")
        } catch (e: Exception) {
            Log.e(TAG, "[DEBUG] startService: Exception caught: ${e.message}", e)
            ExceptionsKt.logNonFatal(e) // ExceptionsKt.logNonFatal likely logs
            Log.d(TAG, "[DEBUG] startService: Exception path. Calling startServiceWithAlarm.")
            startServiceWithAlarm(context, intent) // Original code uses startServiceWithAlarm here.
            Log.d(TAG, "[DEBUG] startService: startServiceWithAlarm called from exception path.")
        }
    }

    private fun startActivityWithNotification(context: Context, intent: Intent, title: String, subtitle: String) {
        Log.d(TAG, "[DEBUG] startActivityWithNotification called. Context: $context, Intent: $intent, Title: \"$title\", Subtitle: \"$subtitle\"")
        val pendingIntent = PendingIntent.getActivity(
            context,
            76,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        Log.d(TAG, "[DEBUG] startActivityWithNotification: Created PendingIntent (Activity): $pendingIntent")

        val notificationBuilder = getNotification(title, subtitle, intent, context) // getNotification logs its details
        val notification = notificationBuilder
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setFullScreenIntent(pendingIntent, true)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()
        Log.d(TAG, "[DEBUG] startActivityWithNotification: Notification built: $notification")

        (context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
            .notify(76, notification)
        Log.d(TAG, "[DEBUG] startActivityWithNotification: NotificationManager.notify called with ID 76.")
    }

    private fun startServiceWithAlarm(context: Context, intent: Intent) {
        Log.d(TAG, "[DEBUG] startServiceWithAlarm called. Context: $context, Intent: $intent")
        val pendingIntent = PendingIntent.getService(
            context,
            76,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        Log.d(TAG, "[DEBUG] startServiceWithAlarm: Created PendingIntent (Service): $pendingIntent")

        val currentTime = System.currentTimeMillis()
        val triggerTime = currentTime + 1000
        Log.d(TAG, "[DEBUG] startServiceWithAlarm: CurrentTime: $currentTime, TriggerTime: $triggerTime")

        (context.getSystemService(Context.ALARM_SERVICE) as AlarmManager).set(
            AlarmManager.RTC,
            triggerTime,
            pendingIntent
        )
        Log.d(TAG, "[DEBUG] startServiceWithAlarm: AlarmManager.set called.")
    }

    private fun startServiceWithNotification(context: Context, intent: Intent, title: String, subtitle: String) {
        Log.d(TAG, "[DEBUG] startServiceWithNotification called. Context: $context, Intent: $intent, Title: \"$title\", Subtitle: \"$subtitle\"")
        val pendingIntent = PendingIntent.getService(
            context,
            76,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        Log.d(TAG, "[DEBUG] startServiceWithNotification: Created PendingIntent (Service): $pendingIntent")

        val notificationBuilder = getNotification(title, subtitle, intent, context) // getNotification logs its details
        val notification = notificationBuilder
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .addAction(R.drawable.ic_media_record, "Open", pendingIntent)
            .build()
        Log.d(TAG, "[DEBUG] startServiceWithNotification: Notification built: $notification")

        (context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
            .notify(76, notification)
        Log.d(TAG, "[DEBUG] startServiceWithNotification: NotificationManager.notify called with ID 76.")
    }

    private fun getNotification(title: String, subtitle: String, intent: Intent, context: Context): NotificationCompat.Builder {
        Log.d(TAG, "[DEBUG] getNotification called. Title: \"$title\", Subtitle: \"$subtitle\", Intent: $intent, Context: $context")
        val channelId = "hawkshaw_default_channel"
        Log.d(TAG, "[DEBUG] getNotification: ChannelId: \"$channelId\"")

        // Create NotificationChannel (idempotent)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = android.app.NotificationChannel(
                channelId,
                "Hawkshaw Notifications",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Default notification channel for Hawkshaw"
            }
            Log.d(TAG, "[DEBUG] getNotification: Creating/getting NotificationChannel: $channel")
            (context.getSystemService(NotificationManager::class.java)).createNotificationChannel(channel)
            Log.d(TAG, "[DEBUG] getNotification: createNotificationChannel called.")
        }

        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setContentText(subtitle)
            .setAutoCancel(true)
        Log.d(TAG, "[DEBUG] getNotification: NotificationCompat.Builder created: $builder")
        return builder
    }
}
