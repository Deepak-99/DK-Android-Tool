package com.hawkshaw.library.features.telephony.contacts

import android.content.Context
import android.provider.ContactsContract
import com.hawkshaw.library.logger.Logger
import java.security.InvalidParameterException
import android.util.Log // Added for logging

private const val TAG = "DeleteContact"

/**
 * Deletes a contact from the device's contacts
 * 
 * @param context Application context
 * @param contactId ID of the contact to delete
 * @return true if contact was deleted successfully
 * @throws SecurityException if missing required permissions
 * @throws InvalidParameterException if contact ID is invalid
 */
@Throws(SecurityException::class, InvalidParameterException::class)
fun deleteContact(context: Context, contactId: String): Boolean {
    Log.d(TAG, "[DEBUG] deleteContact called. Context: $context, contactId: $contactId")
    // Validate contact ID
    if (contactId.isBlank()) {
        Log.w(TAG, "[DEBUG] Validation failed: contactId is blank.")
        throw InvalidParameterException("Contact ID cannot be empty")
    }
    Log.d(TAG, "[DEBUG] contactId '$contactId' is not blank.")

    return try {
        Log.d(TAG, "[DEBUG] Starting try block to delete contact.")
        // Get raw contact IDs for this contact
        val rawContactIds = mutableListOf<String>()
        val rawContactsUri = ContactsContract.RawContacts.CONTENT_URI
        val rawContactsProjection = arrayOf(ContactsContract.RawContacts._ID)
        val rawContactsSelection = "${ContactsContract.RawContacts.CONTACT_ID} = ?"
        val rawContactsSelectionArgs = arrayOf(contactId)

        Log.d(TAG, "[DEBUG] Querying for raw contact IDs. URI: $rawContactsUri, Selection: \"$rawContactsSelection\", Args: [$contactId]")
        context.contentResolver.query(
            rawContactsUri,
            rawContactsProjection,
            rawContactsSelection,
            rawContactsSelectionArgs,
            null
        )?.use { cursor ->
            Log.d(TAG, "[DEBUG] Cursor obtained for raw contacts. Row count: ${cursor.count}")
            while (cursor.moveToNext()) {
                cursor.getString(0)?.let { id ->
                    Log.d(TAG, "[DEBUG] Found raw contact ID: $id")
                    rawContactIds.add(id)
                }
            }
        }
        Log.d(TAG, "[DEBUG] Finished querying for raw contact IDs. Found: ${rawContactIds.size} IDs: $rawContactIds")

        if (rawContactIds.isEmpty()) {
            Log.w(TAG, "[DEBUG] No raw contacts found for contact ID: $contactId. Returning false.")
            Logger.e( // Existing log
                TAG,
                "No raw contacts found for contact ID: $contactId",
                b = false,
                i = 12,
                nothing = null
            )
            return false
        }
        
        // Delete each raw contact and associated data
        var deletedCount = 0
        Log.d(TAG, "[DEBUG] Attempting to delete ${rawContactIds.size} raw contacts.")
        rawContactIds.forEach { rawId ->
            Log.d(TAG, "[DEBUG] Processing rawId: $rawId")
            // Delete all data rows for this raw contact
            val dataUri = ContactsContract.Data.CONTENT_URI
            val dataSelection = "${ContactsContract.Data.RAW_CONTACT_ID} = ?"
            val dataSelectionArgs = arrayOf(rawId)
            Log.d(TAG, "[DEBUG] Deleting data rows for rawId: $rawId. URI: $dataUri, Selection: \"$dataSelection\", Args: [$rawId]")
            val dataDeleted = context.contentResolver.delete(
                dataUri,
                dataSelection,
                dataSelectionArgs
            )
            Log.d(TAG, "[DEBUG] Data rows deleted for rawId $rawId: $dataDeleted")

            // Delete the raw contact
            val rawContactUri = ContactsContract.RawContacts.CONTENT_URI.buildUpon()
                .appendPath(rawId)
                .build()
            Log.d(TAG, "[DEBUG] Deleting raw contact for rawId: $rawId. URI: $rawContactUri")

            val rawDeleted = context.contentResolver.delete(rawContactUri, null, null)
            Log.d(TAG, "[DEBUG] Raw contact deleted for rawId $rawId: $rawDeleted")
            
            if (rawDeleted > 0) {
                deletedCount++
                Log.d(TAG, "[DEBUG] Successfully deleted raw contact $rawId (rawDeleted > 0). Total deletedCount: $deletedCount")
                Logger.d( // Existing log
                    TAG,
                    "Deleted raw contact $rawId with $dataDeleted data rows",
                    false,
                    4,
                    null
                )
            } else {
                 Log.w(TAG, "[DEBUG] Failed to delete raw contact $rawId (rawDeleted <= 0).")
            }
        }
        
        val success = deletedCount == rawContactIds.size
        Log.d(TAG, "[DEBUG] Deletion process finished. Success: $success (deletedCount: $deletedCount, totalRawContactIds: ${rawContactIds.size})")
        if (success) {
            Logger.d(TAG, "Successfully deleted contact: $contactId", false, 4, null) // Existing log
        } else {
            Logger.e( // Existing log
                TAG,
                "Failed to delete all raw contacts for contact ID: $contactId",
                b = false,
                i = 12,
                nothing = null
            )
        }
        
        success
    } catch (e: SecurityException) {
        Log.e(TAG, "[DEBUG] SecurityException caught: ${e.message}", e)
        Logger.e(TAG, "Missing required permissions", e, false, 12, null) // Existing log
        throw e
    } catch (e: Exception) {
        Log.e(TAG, "[DEBUG] Generic Exception caught: ${e.message}", e)
        Logger.e(TAG, "Error deleting contact", e, false, 12, null) // Existing log
        false
    }
}
