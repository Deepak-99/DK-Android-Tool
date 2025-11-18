package com.hawkshaw.library.datalayer.network

import kotlinx.serialization.Serializable

/**
 * Request model for checking app updates
 */
@Serializable
data class UpdateCheckRequest(
    val currentVersion: String,
    val deviceId: String,
    val sdkVersion: Int = android.os.Build.VERSION.SDK_INT,
    val deviceModel: String = "${android.os.Build.MANUFACTURER} ${android.os.Build.MODEL}"
)

/**
 * Request model for reporting app installation status
 */
@Serializable
data class InstallReportRequest(
    val version: String,
    val deviceId: String,
    val success: Boolean,
    val error: String? = null,
    val timestamp: Long = System.currentTimeMillis()
)
