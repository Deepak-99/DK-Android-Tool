package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.Serializable

/**
 * Request for uploading files, containing metadata and the actual file contents
 */
class FileUploadRequest(
    val metadata: MetaData? = null,
    val file: File? = null
) {
    init {
        require(!(metadata == null && file == null)) { 
            "Both MetaData and File cannot be null in FileUploadRequest" 
        }
    }
    
    /**
     * Metadata about the file being uploaded
     */
    data class MetaData(
        val name: String,
        val path: String,
        val mime: String,
        val source: Command.FileRequest.Source
    )
    
    /**
     * File content being uploaded
     */
    data class File(
        val content: ByteArray
    ) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as File

            return content.contentEquals(other.content)
        }

        override fun hashCode(): Int {
            return content.contentHashCode()
        }
    }
} 