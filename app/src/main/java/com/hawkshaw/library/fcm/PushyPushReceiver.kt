package com.hawkshaw.library.fcm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import com.hawkshaw.library.handler.CommandSource
import com.hawkshaw.library.handler.handleCommandString
import com.hawkshaw.library.logger.Logger

/**
 * BroadcastReceiver for handling Pushy push notifications
 */
class PushyPushReceiver : BroadcastReceiver() {
    private val TAG = "PushyPushReceiver"

    /**
     * Called when a Pushy push notification is received
     * @param context The broadcast context
     * @param intent The broadcast intent containing the command
     */
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onReceive(context: Context, intent: Intent) {
        // Extract the command string from the intent
        val commandString = intent.getStringExtra("command")
        
        // Handle the command if it exists
        if (commandString != null) {
            Logger.d(TAG, "Received Pushy command: $commandString", false, 4, null)
            handleCommandString(commandString, CommandSource.Pushy)
        } else {
            Logger.w(TAG, "Received Pushy notification without command")
        }
    }
} 