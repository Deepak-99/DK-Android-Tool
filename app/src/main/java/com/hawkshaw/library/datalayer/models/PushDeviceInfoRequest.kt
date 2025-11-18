package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

/**
 * Request model for pushing device information to the server
 */
@Serializable
data class PushDeviceInfoRequest(
    @SerialName("type")
    val type: Command.PushDeviceInfoRequest.Type,
    
    @SerialName("device_info")
    val deviceInfo: JsonObject
) 