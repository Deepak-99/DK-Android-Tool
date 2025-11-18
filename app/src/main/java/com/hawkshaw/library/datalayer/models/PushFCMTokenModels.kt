package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.Serializable

/**
 * Request model for pushing FCM token to the server
 */
@Serializable
data class PushFCMTokenRequest(
    /**
     * The FCM token to be registered with the server
     */
    val token: String,
    
    /**
     * The device ID associated with this token
     */
    val deviceId: String,
    
    /**
     * The app version
     */
    val appVersion: String,
    
    /**
     * The Android SDK version
     */
    val sdkVersion: Int = android.os.Build.VERSION.SDK_INT,
    
    /**
     * Device model information
     */
    val deviceModel: String = "${android.os.Build.MANUFACTURER} ${android.os.Build.MODEL}"
)

/**
 * Response model for FCM token push operation
 */
@Serializable
data class PushFCMTokenResponse(
    /**
     * Indicates if the operation was successful
     */
    val success: Boolean,
    
    /**
     * Optional message from the server
     */
    val message: String? = null,
    
    /**
     * Timestamp of the response
     */
    val timestamp: Long = System.currentTimeMillis()
)
