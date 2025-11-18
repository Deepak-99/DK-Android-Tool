package com.hawkshaw.library

import android.content.Intent
import android.util.Log // Import Log for debug logs
import androidx.annotation.Keep
import com.hawkshaw.library.App
import com.hawkshaw.library.activities.TransparentActivity
import com.hawkshaw.library.preferences.Prefs

@Keep
object Hawkshaw {

    private const val TAG = "Hawkshaw" // Define TAG for logging

    @Keep
    @JvmStatic
    fun initFromInternalActivity(checkInitFlag: Boolean) {
        Log.d(TAG, "initFromInternalActivity(checkInitFlag=$checkInitFlag) called.") // Debug log
        initFromInternalActivity(checkInitFlag, TransparentActivity.FinishAction.Finish.name)
    }

    @Keep
    @JvmStatic
    fun initFromInternalActivity(checkInitFlag: Boolean, finishAction: String) {
        Log.d(TAG, "initFromInternalActivity(checkInitFlag=$checkInitFlag, finishAction=$finishAction) called.") // Debug log
        if (!checkInitFlag || !Prefs.getInitFlag()) {
            Log.d(TAG, "Initialization flag check passed. Initializing Hawkshaw.") // Debug log
            val context = App.getContext()
            Log.d(TAG, "Retrieved application context.") // Debug log

            val intent = Intent(context, TransparentActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                action = TransparentActivity.Action.StartHawkshawInitializer.name
                putExtra("check_init_flag", checkInitFlag)
                putExtra("finish_action", finishAction)
            }
            Log.d(TAG, "Created Intent for TransparentActivity with action: ${intent.action}") // Debug log

            context.startActivity(intent)
            Log.d(TAG, "Started TransparentActivity.") // Debug log
        } else {
            Log.d(TAG, "Initialization not required. checkInitFlag is $checkInitFlag and Prefs.getInitFlag() is ${Prefs.getInitFlag()}.") // Debug log
        }
    }
}