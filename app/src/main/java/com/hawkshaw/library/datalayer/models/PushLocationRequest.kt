package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model representing a request to push location data to the server
 */
@Serializable
data class PushLocationRequest(
    @SerialName("location")
    val location: Location
) 