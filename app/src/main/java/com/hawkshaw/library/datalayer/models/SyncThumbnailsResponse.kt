package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response model for syncing thumbnails with the server
 */
@Serializable
data class SyncThumbnailsResponse(
    @SerialName("success")
    val success: Boolean,
    
    @SerialName("message")
    val message: String? = null,
    
    @SerialName("synced_paths")
    val syncedPaths: List<String> = emptyList()
) 