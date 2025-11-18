package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model representing a directory in the file system
 * Contains properties about the directory and its contents
 */
@Serializable
data class Directory(
    @SerialName("name")
    val name: String,
    
    @SerialName("path")
    val path: String,
    
    @SerialName("can_read")
    val canRead: Boolean,
    
    @SerialName("can_write")
    val canWrite: Boolean,
    
    @SerialName("last_modified")
    val lastModified: Long,
    
    @SerialName("files")
    val files: List<AppFile>,
    
    @SerialName("directories")
    val directories: List<Directory>
) 