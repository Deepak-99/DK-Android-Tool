package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model representing a response to a push location request
 */
@Serializable
data class PushLocationResponse(
    @SerialName("message")
    val message: String
) 