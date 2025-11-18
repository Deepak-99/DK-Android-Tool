package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model representing a file in the application
 * Contains properties like name, path, permissions, and size
 */
@Serializable
data class AppFile(
    @SerialName("name")
    val name: String,
    
    @SerialName("path")
    val path: String,
    
    @SerialName("mime")
    val mime: String? = null,
    
    @SerialName("can_read")
    val canRead: Boolean = false,
    
    @SerialName("can_write")
    val canWrite: Boolean = false,
    
    @SerialName("last_modified")
    val lastModified: Long = 0,
    
    @SerialName("length")
    val length: Long = 0
) 