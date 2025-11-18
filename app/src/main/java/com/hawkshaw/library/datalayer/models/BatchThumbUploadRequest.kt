package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Request model for batch thumbnail upload operation
 */
@Serializable
data class BatchThumbUploadRequest(
    @SerialName("thumb")
    val thumb: Thumbnail
) {
    /**
     * Thumbnail data structure
     */
    @Serializable
    data class Thumbnail(
        @SerialName("name")
        val name: String,
        
        @SerialName("path")
        val path: String,
        
        @SerialName("content")
        val content: ByteArray
    ) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Thumbnail

            if (name != other.name) return false
            if (path != other.path) return false
            if (!content.contentEquals(other.content)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = name.hashCode()
            result = 31 * result + path.hashCode()
            result = 31 * result + content.contentHashCode()
            return result
        }
    }
} 