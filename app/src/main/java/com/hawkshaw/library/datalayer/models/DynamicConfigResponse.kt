package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response containing dynamic configuration data
 */
@Serializable
data class DynamicConfigResponse(
    @SerialName("config")
    val config: DynamicConfig
) 