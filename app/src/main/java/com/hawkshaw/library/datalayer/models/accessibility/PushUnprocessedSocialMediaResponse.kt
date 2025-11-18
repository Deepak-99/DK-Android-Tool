package com.hawkshaw.library.datalayer.models.accessibility

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response from pushing unprocessed social media data to the server
 */
@Serializable
data class PushUnprocessedSocialMediaResponse(
    @SerialName("message")
    val message: String
) 