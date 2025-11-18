package com.hawkshaw.library.features.telephony.calllogs

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.provider.CallLog
import com.hawkshaw.library.datalayer.models.CallLog as CallLogModel
import kotlin.jvm.internal.Intrinsics
import android.util.Log // Added for logging

private const val TAG = "AddCallLogKt" // Defined a TAG for this file

/**
 * Add a call log entry to the device's call log database
 *
 * @param context Application context
 * @param callLog Call log data to add
 * @return URI of the newly added call log entry, or null if operation failed
 */
fun addCallLog(context: Context, callLog: CallLogModel): Uri? {
    Log.d(TAG, "[DEBUG] addCallLog called. Context: $context, CallLogModel: $callLog")
    Intrinsics.checkNotNullParameter(context, "context")
    Intrinsics.checkNotNullParameter(callLog, "callLog")

    val callType = callLog.callType
    Log.d(TAG, "[DEBUG] Input callLog.callType: \"$callType\"")

    val callTypeInt = when {
        false -> { // This case is unusual and will be logged if ever hit
            Log.w(TAG, "[DEBUG] 'when { false -> ... }' branch was hit. This is unexpected. Defaulting callTypeInt to 6 for callType: \"$callType\"")
            6
        }
        callType.equals("Incoming", ignoreCase = true) -> 1
        callType.equals("Outgoing", ignoreCase = true) -> 2
        callType.equals("Missed", ignoreCase = true) -> 3
        callType.equals("Rejected", ignoreCase = true) -> 5
        else -> {
            Log.d(TAG, "[DEBUG] callType \"$callType\" not explicitly mapped, defaulting callTypeInt to 6 (Unknown/Other).")
            6
        }
    }
    Log.d(TAG, "[DEBUG] Mapped callTypeInt: $callTypeInt")

    val values = ContentValues().apply {
        put("number", callLog.number)
        put("date", callLog.date)
        put("duration", callLog.duration)
        put("type", callTypeInt)
        put("new", 1)
        put("name", callLog.name)
    }
    Log.d(TAG, "[DEBUG] ContentValues prepared: $values")

    Log.d(TAG, "[DEBUG] Attempting to insert call log into ContentResolver.")
    val resultUri = context.contentResolver.insert(CallLog.Calls.CONTENT_URI, values)
    Log.d(TAG, "[DEBUG] Call log insertion result URI: $resultUri")

    return resultUri
}

