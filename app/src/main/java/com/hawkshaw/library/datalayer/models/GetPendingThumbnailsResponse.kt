package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response containing a list of pending thumbnail paths
 */
@Serializable
data class GetPendingThumbnailsResponse(
    @SerialName("paths")
    val paths: List<String>
) 