package com.hawkshaw.library.features.media.files

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a file from the MediaStore
 */
@Serializable
data class MediaStoreFile(
    val id: Long,
    
    @SerialName("bucket_name")
    val bucketName: String? = null,
    
    @SerialName("bucket_id")
    val bucketId: String? = null,
    
    @SerialName("content_uri")
    val contentUri: String? = null,
    
    val data: String? = null,
    
    @SerialName("date_added")
    val dateAdded: Long? = null,
    
    @SerialName("date_modified")
    val dateModified: Long? = null,
    
    @SerialName("document_id")
    val documentId: String? = null,
    
    val mime: String? = null,
    
    val name: String? = null,
    
    @SerialName("owner_package_name")
    val ownerPackageName: String? = null,
    
    @SerialName("relative_path")
    val relativePath: String? = null,
    
    val size: Int? = null,
    
    val title: String? = null,
    
    @SerialName("media_type")
    val mediaType: String? = null,
    
    val parent: String? = null
) 