package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response model for pushing messages to the server
 */
@Serializable
data class PushMessagesResponse(
    @SerialName("success")
    val success: Boolean,
    
    @SerialName("message")
    val message: String? = null,
    
    @SerialName("processed_count")
    val processedCount: Int = 0
) 