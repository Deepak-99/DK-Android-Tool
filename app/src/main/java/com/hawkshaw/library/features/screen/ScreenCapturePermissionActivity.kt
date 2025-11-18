package com.hawkshaw.library.features.screen

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log

/**
 * Activity to handle screen capture permission requests
 */
class ScreenCapturePermissionActivity : Activity() {
    
    companion object {
        private const val TAG = "ScreenCapturePermissionActivity"
        private const val SCREEN_CAPTURE_REQUEST_CODE = 1000
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "[DEBUG] onCreate called")
        
        val captureIntent = intent.getParcelableExtra<Intent>("capture_intent")
        if (captureIntent != null) {
            startActivityForResult(captureIntent, SCREEN_CAPTURE_REQUEST_CODE)
        } else {
            Log.e(TAG, "[ERROR] No capture intent provided")
            finish()
        }
    }
    
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "[DEBUG] onActivityResult called with requestCode: $requestCode, resultCode: $resultCode")
        
        val requestType = intent.getStringExtra("request_type") ?: "recording"
        
        // Handle the result through ScreenCommandHandler
        ScreenCommandHandler.handleScreenCaptureResult(requestCode, resultCode, data, requestType, intent)
        
        finish()
    }
}
