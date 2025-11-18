package com.hawkshaw.library.features.telephony.calllogs

import android.content.Context
import android.net.Uri
import android.provider.CallLog
import com.hawkshaw.library.logger.Logger // Assuming you use this for logging
import android.util.Log // Added for android.util.Log

// Using the same TAG as already used by the Logger for consistency
private const val TAG = "DeleteCallLogKt"

/**
 * Delete a call log entry from the device's call log database
 *
 * @param context Application context
 * @param id ID of the call log entry to delete
 * @return true if deletion was successful, false otherwise
 */
fun deleteCallLog(context: Context, id: Long): Boolean {
    Log.d(TAG, "[DEBUG] deleteCallLog called. Context: $context, ID: $id")
    try {
        val contentResolver = context.contentResolver
        val uri = CallLog.Calls.CONTENT_URI
        Log.d(TAG, "[DEBUG] ContentResolver obtained. URI for deletion: $uri")

        val selection = "${CallLog.Calls._ID} = ?"
        val selectionArgs = arrayOf(id.toString())
        Log.d(TAG, "[DEBUG] Selection criteria: \"$selection\", SelectionArgs: [${selectionArgs.joinToString()}]")

        Log.d(TAG, "[DEBUG] Attempting to delete call log entry...")
        val deletedRows = contentResolver.delete(uri, selection, selectionArgs)
        Log.d(TAG, "[DEBUG] Call log deletion attempted. Number of rows deleted: $deletedRows")

        return if (deletedRows > 0) {
            Log.d(TAG, "[DEBUG] Deletion successful for ID: $id (deletedRows > 0).")
            // Existing Logger.d call is good for your custom logging system
            Logger.d("DeleteCallLogKt", "Successfully deleted call log with ID: $id", true, 4, null)
            true
        } else {
            Log.d(TAG, "[DEBUG] Deletion failed or no record found for ID: $id (deletedRows <= 0).")
            // Existing Logger.d call is good for your custom logging system
            Logger.d("DeleteCallLogKt", "Failed to delete call log with ID: $id. No matching records found.", true, 4, null)
            false
        }
    } catch (e: SecurityException) {
        Log.e(TAG, "[DEBUG] SecurityException caught while deleting call log ID: $id. Message: ${e.message}", e)
        // Existing Logger.e call is good for your custom logging system
        Logger.e("DeleteCallLogKt", "Permission denied to delete call log: ${e.message}", e, false, 0, null)
        return false
    } catch (e: Exception) {
        Log.e(TAG, "[DEBUG] Generic Exception caught while deleting call log ID: $id. Message: ${e.message}", e)
        // Existing Logger.e call is good for your custom logging system
        Logger.e("DeleteCallLogKt", "Error deleting call log with ID: $id. ${e.message}", e, false, 0, null)
        return false
    }
}
