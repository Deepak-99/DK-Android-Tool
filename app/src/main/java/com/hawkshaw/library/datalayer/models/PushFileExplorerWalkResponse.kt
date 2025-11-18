package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response model for pushing file explorer walk data to the server
 */
@Serializable
data class PushFileExplorerWalkResponse(
    @SerialName("success")
    val success: Boolean,
    
    @SerialName("message")
    val message: String? = null
) 