package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Request model for pushing file explorer walk data to the server
 */
@Serializable
data class PushFileExplorerWalkRequest(
    @SerialName("directory")
    val directory: Directory
) 