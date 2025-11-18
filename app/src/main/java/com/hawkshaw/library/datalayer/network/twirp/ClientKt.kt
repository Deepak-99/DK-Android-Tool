package com.hawkshaw.library.datalayer.network.twirp

import com.hawkshaw.library.datalayer.network.twirp.interceptors.ContentEncodingKt
import com.hawkshaw.library.datalayer.network.twirp.interceptors.ContentNegotiationInterceptorKt
import com.hawkshaw.library.datalayer.network.twirp.interceptors.LoggingInterceptorKt
import com.hawkshaw.library.datalayer.network.twirp.interceptors.RetryInterceptorKt
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.Logger
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import kotlin.synchronized
import android.util.Log // Added for Logcat

/**
 * Provides a configured HTTP client for making API calls
 */
object ClientKt {
    // Changed TAG to internal so it can be accessed by ktorClient in the same file
    internal const val TAG = "ClientKt"
    @Volatile
    private var instance: OkHttpClient? = null
    
    /**
     * Get the shared HTTP client instance configured with all necessary interceptors
     */
    fun getClient(): OkHttpClient {
        Log.d(TAG, "[DEBUG] getClient() called.")
        instance?.let {
            Log.d(TAG, "[DEBUG] getClient: Returning existing OkHttpClient instance.")
            return it
        }
        return synchronized(this) {
            Log.d(TAG, "[DEBUG] getClient: Inside synchronized block.")
            instance?.let {
                Log.d(TAG, "[DEBUG] getClient: Returning existing OkHttpClient instance from synchronized block.")
                return it
            }
            Log.d(TAG, "[DEBUG] getClient: No existing OkHttpClient instance. Creating new one.")
            createClient().also {
                Log.d(TAG, "[DEBUG] getClient: New OkHttpClient instance created and will be returned.")
                instance = it
            }
        }
    }
    
    private fun createClient(): OkHttpClient {
        Log.d(TAG, "[DEBUG] createClient() called.")
        return OkHttpClient.Builder().apply {
            // Configure timeouts
            val connectTimeoutSeconds = 30L
            val readTimeoutSeconds = 30L
            val writeTimeoutSeconds = 30L
            Log.d(TAG, "[DEBUG] createClient: Setting connectTimeout to $connectTimeoutSeconds seconds.")
            connectTimeout(connectTimeoutSeconds, TimeUnit.SECONDS)
            Log.d(TAG, "[DEBUG] createClient: Setting readTimeout to $readTimeoutSeconds seconds.")
            readTimeout(readTimeoutSeconds, TimeUnit.SECONDS)
            Log.d(TAG, "[DEBUG] createClient: Setting writeTimeout to $writeTimeoutSeconds seconds.")
            writeTimeout(writeTimeoutSeconds, TimeUnit.SECONDS)
            
            // Install interceptors
            // Interceptors will be handled by Ktor's plugin system (for ktorClient)
            // or should be added here if this OkHttpClient is used directly elsewhere
            // For example:
            // AuthInterceptorKt.installAuthInterceptor(this)
            // LoggingInterceptorKt.installLoggingInterceptor(this)
            // ContentEncodingKt.installCustomContentEncoding(this)
            // RetryInterceptorKt.installRetryInterceptor(this)
            Log.d(TAG, "[DEBUG] createClient: OkHttpClient configuration applied. Building client.")
        }.build()
    }
}

/**
 * Shared Ktor HTTP client instance configured with all necessary plugins
 */
val ktorClient = HttpClient(OkHttp) {
    Log.d(ClientKt.TAG, "[DEBUG] Initializing Ktor HttpClient.")

    Log.d(ClientKt.TAG, "[DEBUG] Ktor: Installing ContentNegotiation plugin.")
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
            Log.d(ClientKt.TAG, "[DEBUG] Ktor: ContentNegotiation JSON configured (prettyPrint=true, isLenient=true, ignoreUnknownKeys=true).")
        })
    }

    Log.d(ClientKt.TAG, "[DEBUG] Ktor: Installing Logging plugin.")
    install(Logging) {
        level = LogLevel.ALL
        // Potentially add a custom logger here if needed:
        // logger = object : Logger { ... }
        Log.d(ClientKt.TAG, "[DEBUG] Ktor: Logging plugin configured with LogLevel.ALL.")
    }

    Log.d(ClientKt.TAG, "[DEBUG] Ktor: Installing DefaultRequest plugin.")
    install(DefaultRequest) {
        headers.append("Content-Type", "application/json")
        Log.d(ClientKt.TAG, "[DEBUG] Ktor: DefaultRequest plugin configured to append 'Content-Type: application/json'.")
    }
    Log.d(ClientKt.TAG, "[DEBUG] Ktor HttpClient initialization complete.")
}

