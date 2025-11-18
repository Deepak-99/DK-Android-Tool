package com.hawkshaw.library.datalayer.models.accessibility

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response from pushing key logs data to the server
 */
@Serializable
data class PushKeyLogsResponse(
    @SerialName("message")
    val message: String
) 