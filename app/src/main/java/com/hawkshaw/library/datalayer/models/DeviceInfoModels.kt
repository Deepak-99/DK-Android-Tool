package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

/**
 * Model class for non-sensitive device information
 * Contains static device info that doesn't require special permissions
 */
@Serializable
data class DeviceInfoNonSensitive(
    @SerialName("device_info")
    val deviceInfo: JsonObject
)

/**
 * Model class for sensitive device information
 * Contains dynamic device info that may require special permissions
 */
@Serializable
data class DeviceInfoSensitive(
    @SerialName("device_info")
    val deviceInfo: JsonObject
) 