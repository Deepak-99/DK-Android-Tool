package com.hawkshaw.library.core

data class CapabilityReport(
    val deviceId: String,
    val sdkInt: Int,
    val grantedPermissions: List<String>,
    val enabledAdapters: List<String>,
    val riskyCapabilities: List<String>,
    val timestamp: Long = System.currentTimeMillis()
)