package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Request model for pushing data to the server
 */
@Serializable
data class PushDataRequest(
    @SerialName("timestamp")
    val timestamp: Long
) 