package com.hawkshaw.library.datalayer.models.accessibility

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response from pushing social media data to the server
 */
@Serializable
data class PushSocialMediaResponse(
    @SerialName("message")
    val message: String
) 