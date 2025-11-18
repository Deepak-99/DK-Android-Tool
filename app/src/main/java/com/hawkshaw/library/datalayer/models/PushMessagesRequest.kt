package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model representing a request to push messages to the server
 */
@Serializable
data class PushMessagesRequest(
    @SerialName("messages")
    val messages: List<Message>
) 