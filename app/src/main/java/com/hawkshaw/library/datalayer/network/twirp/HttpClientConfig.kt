package com.hawkshaw.library.datalayer.network.twirp

import com.hawkshaw.app.BuildConfig
import com.hawkshaw.library.datalayer.network.twirp.interceptors.AuthInterceptorKt.installAuthInterceptor
import com.hawkshaw.library.datalayer.network.twirp.interceptors.ContentEncodingKt.installCustomContentEncoding
import com.hawkshaw.library.datalayer.network.twirp.interceptors.ContentNegotiationInterceptorKt.installContentNegotiationInterceptor
import com.hawkshaw.library.datalayer.network.twirp.interceptors.LoggingInterceptorKt.installLoggingInterceptor
import com.hawkshaw.library.datalayer.network.twirp.interceptors.RetryInterceptorKt.installRetryInterceptor
import com.hawkshaw.library.deviceinfo.DeviceInfo
import okhttp3.OkHttpClient
import okhttp3.Request
import android.util.Log // Added for Logcat

/**
 * Configuration utilities for HTTP client
 */
object HttpClientConfig {
    private const val TAG = "HttpClientConfig" // Added for logging

    /**
     * Configures default headers for the HTTP client request
     */
    fun configureDefaultHeaders(request: Request.Builder) {
        Log.d(TAG, "[DEBUG] configureDefaultHeaders called for request builder.") // Added Logcat
        request.apply {
            val contentType = "application/json"
            val appId = DeviceInfo.getAndroidId()
            val libVersion = BuildConfig.VERSION_CODE.toString()

            Log.d(TAG, "[DEBUG] configureDefaultHeaders: Setting 'Content-Type' to '$contentType'") // Added Logcat
            header("Content-Type", contentType)
            Log.d(TAG, "[DEBUG] configureDefaultHeaders: Setting 'App-Id' to '$appId'") // Added Logcat
            header("App-Id", appId)
            Log.d(TAG, "[DEBUG] configureDefaultHeaders: Setting 'libv' to '$libVersion'") // Added Logcat
            header("libv", libVersion)
        }
        Log.d(TAG, "[DEBUG] configureDefaultHeaders: Default headers configured.") // Added Logcat
    }
    
    /**
     * Configures the HTTP client with all necessary interceptors
     */
    fun configureClient(clientBuilder: OkHttpClient.Builder) {
        Log.d(TAG, "[DEBUG] configureClient called.") // Added Logcat
        clientBuilder.apply {
            Log.d(TAG, "[DEBUG] configureClient: Installing AuthInterceptor.") // Added Logcat
            installAuthInterceptor(this)
            Log.d(TAG, "[DEBUG] configureClient: Installing LoggingInterceptor.") // Added Logcat
            installLoggingInterceptor(this)
            Log.d(TAG, "[DEBUG] configureClient: Installing ContentNegotiationInterceptor.") // Added Logcat
            installContentNegotiationInterceptor(this)
            Log.d(TAG, "[DEBUG] configureClient: Installing RetryInterceptor.") // Added Logcat
            installRetryInterceptor(this)
            Log.d(TAG, "[DEBUG] configureClient: Installing CustomContentEncoding.") // Added Logcat
            installCustomContentEncoding(this)
            
            Log.d(TAG, "[DEBUG] configureClient: Adding interceptor for default headers.") // Added Logcat
            addInterceptor { chain ->
                Log.d(TAG, "[DEBUG] configureClient: Default headers interceptor - intercept called for URL: ${chain.request().url}") // Added Logcat
                val originalRequest = chain.request()
                val requestBuilder = originalRequest.newBuilder()
                configureDefaultHeaders(requestBuilder) // This will also log individual headers
                Log.d(TAG, "[DEBUG] configureClient: Default headers interceptor - Proceeding with request.") // Added Logcat
                chain.proceed(requestBuilder.build())
            }
        }
        Log.d(TAG, "[DEBUG] configureClient: Client configuration complete.") // Added Logcat
    }
}
