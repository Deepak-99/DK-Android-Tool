package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Request for pushing file explorer walk data to the server
 * Contains a list of files with pagination support
 */
@Serializable
data class PushFileExplorerWalkV2Request(
    @SerialName("current_page")
    val currentPage: Int,
    
    @SerialName("files")
    val files: List<AppFileV2>
) 