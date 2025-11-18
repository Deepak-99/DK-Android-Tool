package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response model for pushing file explorer walk data to the server (V2)
 */
@Serializable
data class PushFileExplorerWalkV2Response(
    @SerialName("success")
    val success: Boolean,
    
    @SerialName("message")
    val message: String? = null
) 