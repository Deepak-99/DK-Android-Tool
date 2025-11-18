package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response model for call log push requests
 */
@Serializable
data class PushCallLogsResponse(
    @SerialName("success")
    val success: Boolean,
    
    @SerialName("message")
    val message: String? = null,
    
    @SerialName("error")
    val error: String? = null,
    
    @SerialName("processed_count")
    val processedCount: Int = 0
) 