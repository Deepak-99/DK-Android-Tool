package com.hawkshaw.library.datalayer.network.twirp.interceptors

import com.hawkshaw.library.logger.Logger
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException
import kotlin.math.min
import kotlin.random.Random
import android.util.Log // Added for Logcat

/**
 * Retry configuration for HTTP client
 */
object RetryInterceptorKt {
    private const val TAG = "RetryInterceptor"
    private const val MAX_RETRIES = 3
    private const val BASE_DELAY_MS = 1000L
    private const val MAX_DELAY_MS = 10000L // 10 seconds
    private const val JITTER_FACTOR = 0.5

    /**
     * Installs retry interceptor on the HTTP client
     */
    fun installRetryInterceptor(builder: OkHttpClient.Builder) {
        Log.d(TAG, "[DEBUG] installRetryInterceptor called. Adding RetryInterceptor to OkHttpClient builder.") // Added Logcat
        builder.addInterceptor(RetryInterceptor())
    }

    private class RetryInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            var retryCount = 0
            var response: Response? = null
            var lastException: IOException? = null

            Log.d(TAG, "[DEBUG] RetryInterceptor: Intercepting request for URL: ${request.url}") // Added Logcat

            while (retryCount <= MAX_RETRIES) {
                Log.d(TAG, "[DEBUG] RetryInterceptor: Attempt #$retryCount for URL: ${request.url}") // Added Logcat
                try {
                    // If this isn't the first attempt, add retry count header
                    val currentRequest = if (retryCount > 0) {
                        Log.d(TAG, "[DEBUG] RetryInterceptor: Adding X-Retry-Count header: $retryCount for URL: ${request.url}") // Added Logcat
                        request.newBuilder()
                            .header("X-Retry-Count", retryCount.toString())
                            .build()
                    } else {
                        request
                    }
                    Log.d(TAG, "[DEBUG] RetryInterceptor: Proceeding with chain for URL: ${currentRequest.url}, attempt: $retryCount") // Added Logcat
                    response = chain.proceed(currentRequest)
                    Log.d(TAG, "[DEBUG] RetryInterceptor: Received response code ${response.code} for URL: ${currentRequest.url}, attempt: $retryCount") // Added Logcat

                    // If response is successful or not a server error, return it
                    if (response.isSuccessful || response.code < 500) {
                        Log.d(TAG, "[DEBUG] RetryInterceptor: Response successful or not a server error (code ${response.code}). Returning response for URL: ${currentRequest.url}") // Added Logcat
                        return response
                    }

                    // Close the response before retrying
                    Log.d(TAG, "[DEBUG] RetryInterceptor: Server error (code ${response.code}). Closing response before retry for URL: ${currentRequest.url}") // Added Logcat
                    response.close()

                } catch (e: IOException) {
                    lastException = e
                    Log.e(TAG, "[DEBUG] RetryInterceptor: IOException on attempt ${retryCount + 1} for URL: ${request.url}. Message: ${e.message}", e) // Added Logcat
                    Logger.e(
                        TAG,
                        "Attempt ${retryCount + 1} failed: ${e.message}",
                        b = false,
                        i = 12,
                        nothing = null
                    )
                }

                // Calculate delay with exponential backoff and jitter
                if (retryCount < MAX_RETRIES) {
                    val delay = calculateDelay(retryCount)
                    Log.d(TAG, "[DEBUG] RetryInterceptor: Calculated delay: ${delay}ms before attempt ${retryCount + 1} for URL: ${request.url}") // Added Logcat
                    Logger.d(
                        TAG,
                        "Retrying in ${delay}ms (attempt ${retryCount + 1}/$MAX_RETRIES)",
                        false,
                        4,
                        null
                    )
                    Thread.sleep(delay)
                }
                retryCount++
            }

            // If we got here, we failed all retries
            Log.w(TAG, "[DEBUG] RetryInterceptor: All $MAX_RETRIES retries failed for URL: ${request.url}. Closing last response and throwing exception.") // Added Logcat
            response?.close()
            throw lastException ?: IOException("Failed after $MAX_RETRIES retries for URL: ${request.url}")
        }

        private fun calculateDelay(retryCount: Int): Long {
            Log.d(TAG, "[DEBUG] calculateDelay called with retryCount: $retryCount") // Added Logcat
            // Calculate exponential backoff
            val exponentialDelay = BASE_DELAY_MS * (1L shl retryCount)
            
            // Add jitter
            val jitter = Random.nextDouble(-JITTER_FACTOR, JITTER_FACTOR)
            val delay = exponentialDelay * (1.0 + jitter)
            
            // Ensure delay is within bounds
            val finalDelay = min(delay.toLong(), MAX_DELAY_MS)
            Log.d(TAG, "[DEBUG] calculateDelay: exponentialDelay=$exponentialDelay, jitter=$jitter, raw_delay=$delay, final_delay=$finalDelay") // Added Logcat
            return finalDelay
        }
    }
}
