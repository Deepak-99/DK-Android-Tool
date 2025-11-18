package com.hawkshaw.library.datalayer.network.twirp.interceptors

import com.hawkshaw.library.preferences.Prefs
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import android.util.Log // Added for logging

/**
 * Authentication interceptor for HTTP client
 */
object AuthInterceptorKt {
    private const val TAG = "AuthInterceptor" // Added for logging

    /**
     * Installs the authentication interceptor on the HTTP client
     */
    fun installAuthInterceptor(builder: OkHttpClient.Builder) {
        Log.d(TAG, "[DEBUG] installAuthInterceptor called. Adding AuthInterceptor to OkHttpClient builder.")
        builder.addInterceptor(AuthInterceptor())
    }

    private class AuthInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()
            Log.d(TAG, "[DEBUG] AuthInterceptor: Intercepting request for URL: ${originalRequest.url}")

            // Only add auth header for api.hawkshaw.dev
            if (!originalRequest.url.host.contains("api.hawkshaw.dev")) {
                Log.d(TAG, "[DEBUG] AuthInterceptor: Host is not 'api.hawkshaw.dev' (${originalRequest.url.host}). Proceeding without auth header.")
                return chain.proceed(originalRequest)
            }
            Log.d(TAG, "[DEBUG] AuthInterceptor: Host is 'api.hawkshaw.dev'. Attempting to add auth header.")

            val token = getToken()
            if (token == null) {
                Log.w(TAG, "[DEBUG] AuthInterceptor: Token is null. Proceeding without auth header.")
                return chain.proceed(originalRequest)
            }
            // For security, avoid logging the full token in production.
            // Log.d(TAG, "[DEBUG] AuthInterceptor: Token retrieved: $token") // Use with caution
            Log.d(TAG, "[DEBUG] AuthInterceptor: Token retrieved successfully (not logging full token for security).")


            val authenticatedRequest = originalRequest.newBuilder()
                .header("Authorization", "Bearer $token")
                .build()
            Log.d(TAG, "[DEBUG] AuthInterceptor: Added Authorization header. Proceeding with authenticated request for URL: ${authenticatedRequest.url}")

            return chain.proceed(authenticatedRequest)
        }
    }

    /**
     * Gets the auth token from preferences
     */
    private fun getToken(): String? {
        Log.d(TAG, "[DEBUG] getToken called.")
        val token = Prefs.getToken()
        Log.d(TAG, "[DEBUG] getToken: Token from Prefs: ${if (token.isNullOrBlank()) "null or blank" else "present (not logging full token)"}")
        return if (token.isNullOrBlank()) {
            Log.w(TAG, "[DEBUG] getToken: Token is null or blank, returning null.")
            null
        } else {
            Log.d(TAG, "[DEBUG] getToken: Token is valid, returning it.")
            token
        }
    }
}
