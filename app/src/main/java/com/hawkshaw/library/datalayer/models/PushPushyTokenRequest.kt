package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Request to push a Pushy token to the server
 */
@Serializable
data class PushPushyTokenRequest(
    @SerialName("token")
    val token: String
) 