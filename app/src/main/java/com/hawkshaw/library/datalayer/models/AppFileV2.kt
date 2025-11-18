package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a file in the device file system
 */
@Serializable
data class AppFileV2(
    @SerialName("name")
    val name: String,
    
    @SerialName("path")
    val path: String,
    
    @SerialName("mime")
    val mime: String? = null,
    
    @SerialName("last_modified")
    val lastModified: Timestamp,
    
    @SerialName("length")
    val length: Long
) 