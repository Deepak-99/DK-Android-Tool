package com.hawkshaw.library.datalayer.models.accessibility

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response from pushing notification data to the server
 */
@Serializable
data class PushNotificationResponse(
    @SerialName("message")
    val message: String
) 