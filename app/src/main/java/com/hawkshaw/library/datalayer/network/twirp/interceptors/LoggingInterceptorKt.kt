package com.hawkshaw.library.datalayer.network.twirp.interceptors

import com.hawkshaw.library.logger.Logger
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException
import android.util.Log // Added for Logcat

/**
 * Logging configuration for HTTP client
 */
object LoggingInterceptorKt {
    private const val TAG = "LoggingInterceptor"
    private val SENSITIVE_HEADERS = setOf(
        "Authorization",
        "App-Id"
    )

    /**
     * Installs logging interceptor on the HTTP client
     */
    fun installLoggingInterceptor(builder: OkHttpClient.Builder) {
        Log.d(TAG, "[DEBUG] installLoggingInterceptor called. Adding LoggingInterceptor to OkHttpClient builder.") // Added Logcat
        builder.addInterceptor(LoggingInterceptor())
    }
    
    private class LoggingInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            Log.d(TAG, "[DEBUG] LoggingInterceptor: intercept called for request: ${chain.request().url}") // Added Logcat
            val request = chain.request()
            
            // Log request (using existing custom Logger)
            val requestLog = buildString {
                appendLine("--> ${request.method} ${request.url}")
                
                // Log headers (excluding sensitive ones)
                request.headers.forEach { (name, value) ->
                    if (!SENSITIVE_HEADERS.contains(name)) {
                        appendLine("$name: $value")
                    }
                }
                
                // Log request body if present
                request.body?.let { body ->
                    appendLine("Content-Type: ${body.contentType()}")
                    appendLine("Content-Length: ${body.contentLength()}")
                }
                appendLine("--> END ${request.method}")
            }
            Logger.v(TAG, requestLog, false, 4, null)
            
            Log.d(TAG, "[DEBUG] LoggingInterceptor: Proceeding with chain for request: ${request.url}") // Added Logcat
            // Execute request
            val response = chain.proceed(request)
            Log.d(TAG, "[DEBUG] LoggingInterceptor: Received response with code: ${response.code} for request: ${request.url}") // Added Logcat

            // Log response (using existing custom Logger)
            val responseLog = buildString {
                appendLine("<-- ${response.code} ${response.message} ${response.request.url}")
                
                // Log headers (excluding sensitive ones)
                response.headers.forEach { (name, value) ->
                    if (!SENSITIVE_HEADERS.contains(name)) {
                        appendLine("$name: $value")
                    }
                }
                
                // Log response body if present
                response.body?.let { body ->
                    Log.d(TAG, "[DEBUG] LoggingInterceptor: Processing response body for URL: ${response.request.url}") // Added Logcat
                    val bodyString = body.string() // This consumes the body
                    appendLine("Body: $bodyString")
                    
                    // We need to create a new response since body.string() consumes the body
                    Log.d(TAG, "[DEBUG] LoggingInterceptor: Rebuilding response after reading body for URL: ${response.request.url}") // Added Logcat
                    return response.newBuilder()
                        .body(okhttp3.ResponseBody.create(body.contentType(), bodyString))
                        .build()
                        .also {
                            appendLine("<-- END HTTP")
                            // Note: toString() here refers to the buildString's current state, not the response object.
                            // The original log might have intended to log the full responseLog string.
                            // For clarity, I'm keeping it as is, but if this Logger.v call was meant for the *entire* responseLog,
                            // it should be outside the also block or the buildString should be passed explicitly.
                            Logger.v(TAG, toString(), false, 4, null) // Existing custom Logger call
                            Log.d(TAG, "[DEBUG] LoggingInterceptor: New response built and returning for URL: ${response.request.url}") // Added Logcat
                        }
                }
                
                appendLine("<-- END HTTP")
            }
            Logger.v(TAG, responseLog, false, 4, null) // Existing custom Logger call
            
            Log.d(TAG, "[DEBUG] LoggingInterceptor: Returning response (body might have been handled if present) for URL: ${response.request.url}") // Added Logcat
            return response
        }
    }
}
