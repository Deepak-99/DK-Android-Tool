package com.hawkshaw.library.features.auth

import android.app.Activity
import android.os.Bundle
import android.util.Log // Added for Android logging
import android.webkit.WebView
import android.webkit.WebViewClient
import com.hawkshaw.library.features.AuthCallback
import com.hawkshaw.library.logger.Logger

/**
 * Activity for handling web-based authentication
 */
class WebAuthActivity : Activity() {
    companion object {
        private const val TAG = "WebAuth"
        const val EXTRA_AUTH_URL = "auth_url"
        const val EXTRA_CALLBACK = "callback"
    }

    private lateinit var webView: WebView
    private lateinit var callback: AuthCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "[DEBUG] onCreate called.")

        // Get the callback from intent
        try {
            callback = intent.getSerializableExtra(EXTRA_CALLBACK) as? AuthCallback
                ?: throw IllegalArgumentException("AuthCallback is required and was null or of wrong type")
            Log.d(TAG, "[DEBUG] AuthCallback received: $callback")
        } catch (e: Exception) {
            Log.e(TAG, "[DEBUG] Error retrieving AuthCallback: ${e.message}", e)
            // Potentially finish activity or show error if callback is essential and missing
            throw IllegalArgumentException("AuthCallback is required. Error: ${e.message}")
        }
        
        // Get the auth URL
        val authUrl = intent.getStringExtra(EXTRA_AUTH_URL)
        if (authUrl == null) {
            Log.e(TAG, "[DEBUG] Auth URL is null. Finishing activity.")
            callback.onFailure("Authentication URL was not provided.")
            finish() // Finish if authUrl is critical and missing
            throw IllegalArgumentException("Auth URL is required")
        }
        Log.d(TAG, "[DEBUG] Auth URL received: $authUrl")
        
        // Create and configure WebView
        webView = WebView(this).apply {
            settings.javaScriptEnabled = true
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    Log.d(TAG, "[DEBUG] onPageFinished called. URL: $url")

                    // Check if the URL contains the auth token
                    url?.let { currentUrl ->
                        Log.d(TAG, "[DEBUG] Checking URL for token/error: $currentUrl")
                        if (currentUrl.contains("token=")) {
                            val token = currentUrl.substringAfter("token=")
                            Log.d(TAG, "[DEBUG] Token found: $token")
                            // Existing Logger.d is good here
                            Logger.d(TAG, "Auth token received", false, 4, null)
                            callback.onSuccess(token)
                            finish()
                        } else if (currentUrl.contains("error=")) {
                            val error = currentUrl.substringAfter("error=")
                            Log.d(TAG, "[DEBUG] Error found in URL: $error")
                            // Existing Logger.e is good here
                            Logger.e(TAG, "Auth error: $error", b = false, i = 12, nothing = null)
                            callback.onFailure(error)
                            finish()
                        } else {
                            Log.d(TAG, "[DEBUG] URL does not contain 'token=' or 'error='. Current URL: $currentUrl")
                        }
                    } ?: Log.d(TAG, "[DEBUG] onPageFinished: URL is null.")
                }
            }
        }
        
        // Load the auth URL
        Log.d(TAG, "[DEBUG] Loading auth URL in WebView: $authUrl")
        setContentView(webView)
        webView.loadUrl(authUrl)
    }

    override fun onDestroy() {
        Log.d(TAG, "[DEBUG] onDestroy called.")
        if (::webView.isInitialized) { // Check if webView has been initialized
            webView.destroy()
            Log.d(TAG, "[DEBUG] WebView destroyed.")
        }
        super.onDestroy()
    }
}
