package com.hawkshaw.app

import android.os.Build
import android.os.Bundle
import android.util.Log // Import Log for debug logs
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.hawkshaw.library.HawkshawInitializer

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity" // Define TAG for logging
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate() called. savedInstanceState: $savedInstanceState") // Debug log at start
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "Layout set to activity_main.") // Debug log

        val initializer = HawkshawInitializer(this) {
            // Called when initialization is complete
            // You can add any post-initialization logic here
            Log.d(TAG, "HawkshawInitializer: Initialization complete callback invoked.") // Debug log inside callback
        }
        initializer.pushData = true
        Log.d(TAG, "HawkshawInitializer instance created.") // Debug log after initializer creation

        HawkshawInitializer.init(initializer, false)
        Log.d(TAG, "HawkshawInitializer.init() called with forceInit = false.") // Debug log after init call

        Log.d(TAG, "onCreate() finished.") // Debug log at end
    }
}