package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response model for application logs submission
 */
@Serializable
data class AppLogsResponse(
    @SerialName("message")
    val message: String
) 