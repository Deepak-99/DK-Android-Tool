package com.hawkshaw.library

import java.io.InputStream
import java.nio.ByteBuffer

/**
 * Interface for accessing Thumbnail properties
 */
interface ThumbnailOrBuilder {
    /**
     * Get the name of the thumbnail
     */
    fun getName(): String

    /**
     * Get the name as bytes
     */
    fun getNameBytes(): ByteArray

    /**
     * Get the path where the thumbnail should be stored
     */
    fun getPath(): String

    /**
     * Get the path as bytes
     */
    fun getPathBytes(): ByteArray

    /**
     * Get the binary content of this thumbnail
     */
    fun getContent(): ByteArray
}

/**
 * Response for batch thumbnail uploads
 */
class BatchThumbUploadRequest private constructor() : BatchThumbUploadRequestOrBuilder {
    private var thumb_: Thumbnail? = null

    /**
     * Get the thumbnail data for the batch upload
     */
    override fun getThumb(): Thumbnail? {
        return thumb_
    }

    /**
     * Check if this request contains thumbnail data
     */
    override fun hasThumb(): Boolean {
        return thumb_ != null
    }

    private fun clearThumb() {
        thumb_ = null
    }

    private fun mergeThumb(value: Thumbnail) {
        val thumb = thumb_
        thumb_ = if (thumb != null && thumb != Thumbnail.getDefaultInstance()) {
            Thumbnail.newBuilder(thumb).mergeFrom(value).build()
        } else {
            value
        }
    }

    private fun setThumb(value: Thumbnail) {
        thumb_ = value
    }

    /**
     * Builder for BatchThumbUploadRequest
     */
    class Builder {
        private val instance = DEFAULT_INSTANCE

        /**
         * Clear thumbnail data from this request
         */
        fun clearThumb(): Builder {
            instance.clearThumb()
            return this
        }

        /**
         * Get the thumbnail data for the batch upload
         */
        fun getThumb(): Thumbnail? {
            return instance.getThumb()
        }

        /**
         * Check if this request contains thumbnail data
         */
        fun hasThumb(): Boolean {
            return instance.hasThumb()
        }

        /**
         * Merge thumbnail data into this request
         */
        fun mergeThumb(value: Thumbnail): Builder {
            instance.mergeThumb(value)
            return this
        }

        /**
         * Set thumbnail data for this request
         */
        fun setThumb(value: Thumbnail): Builder {
            instance.setThumb(value)
            return this
        }

        /**
         * Set thumbnail data for this request using a builder
         */
        fun setThumb(builderForValue: Thumbnail.Builder): Builder {
            instance.setThumb(builderForValue.build())
            return this
        }
        
        /**
         * Build the request
         */
        fun build(): BatchThumbUploadRequest {
            return instance
        }
    }

    /**
     * Thumbnail data for batch upload
     */
    class Thumbnail private constructor() : ThumbnailOrBuilder {
        private var name_: String = ""
        private var path_: String = ""
        private var content_: ByteArray = ByteArray(0)

        /**
         * Get the name of the thumbnail
         */
        override fun getName(): String {
            return name_
        }

        /**
         * Get the name as bytes
         */
        override fun getNameBytes(): ByteArray {
            return name_.toByteArray(Charsets.UTF_8)
        }

        /**
         * Get the path where the thumbnail should be stored
         */
        override fun getPath(): String {
            return path_
        }

        /**
         * Get the path as bytes
         */
        override fun getPathBytes(): ByteArray {
            return path_.toByteArray(Charsets.UTF_8)
        }

        /**
         * Get the binary content of this thumbnail
         */
        override fun getContent(): ByteArray {
            return content_
        }

        private fun clearName() {
            name_ = ""
        }

        private fun clearPath() {
            path_ = ""
        }

        private fun clearContent() {
            content_ = ByteArray(0)
        }

        private fun setName(value: String) {
            name_ = value
        }

        private fun setNameBytes(value: ByteArray) {
            name_ = value.toString(Charsets.UTF_8)
        }

        private fun setPath(value: String) {
            path_ = value
        }

        private fun setPathBytes(value: ByteArray) {
            path_ = value.toString(Charsets.UTF_8)
        }

        private fun setContent(value: ByteArray) {
            content_ = value
        }

        /**
         * Builder for Thumbnail
         */
        class Builder {
            private val instance = DEFAULT_INSTANCE

            /**
             * Clear the name of the thumbnail
             */
            fun clearName(): Builder {
                instance.clearName()
                return this
            }

            /**
             * Clear the path where the thumbnail should be stored
             */
            fun clearPath(): Builder {
                instance.clearPath()
                return this
            }

            /**
             * Clear binary content from this thumbnail
             */
            fun clearContent(): Builder {
                instance.clearContent()
                return this
            }

            /**
             * Get the name of the thumbnail
             */
            fun getName(): String {
                return instance.getName()
            }

            /**
             * Get the name as bytes
             */
            fun getNameBytes(): ByteArray {
                return instance.getNameBytes()
            }

            /**
             * Get the path where the thumbnail should be stored
             */
            fun getPath(): String {
                return instance.getPath()
            }

            /**
             * Get the path as bytes
             */
            fun getPathBytes(): ByteArray {
                return instance.getPathBytes()
            }

            /**
             * Get the binary content of this thumbnail
             */
            fun getContent(): ByteArray {
                return instance.getContent()
            }

            /**
             * Set the name of the thumbnail
             */
            fun setName(value: String): Builder {
                instance.setName(value)
                return this
            }

            /**
             * Set the name as bytes
             */
            fun setNameBytes(value: ByteArray): Builder {
                instance.setNameBytes(value)
                return this
            }

            /**
             * Set the path where the thumbnail should be stored
             */
            fun setPath(value: String): Builder {
                instance.setPath(value)
                return this
            }

            /**
             * Set the path as bytes
             */
            fun setPathBytes(value: ByteArray): Builder {
                instance.setPathBytes(value)
                return this
            }

            /**
             * Set binary content for this thumbnail
             */
            fun setContent(value: ByteArray): Builder {
                instance.setContent(value)
                return this
            }
            
            /**
             * Build the thumbnail
             */
            fun build(): Thumbnail {
                return instance
            }
            
            /**
             * Merge from another Thumbnail
             */
            fun mergeFrom(other: Thumbnail): Builder {
                if (other.getName().isNotEmpty()) {
                    setName(other.getName())
                }
                if (other.getPath().isNotEmpty()) {
                    setPath(other.getPath())
                }
                if (other.getContent().isNotEmpty()) {
                    setContent(other.getContent())
                }
                return this
            }
        }

        companion object {
            val DEFAULT_INSTANCE = Thumbnail()
            
            /**
             * Create a new builder for Thumbnail
             */
            @JvmStatic
            fun newBuilder(): Builder {
                return Builder()
            }
            
            /**
             * Create a new builder from an existing Thumbnail
             */
            @JvmStatic
            fun newBuilder(prototype: Thumbnail): Builder {
                return newBuilder().mergeFrom(prototype)
            }

            /**
             * Get the default instance
             */
            @JvmStatic
            fun getDefaultInstance(): Thumbnail {
                return DEFAULT_INSTANCE
            }
        }
    }

    companion object {
        val DEFAULT_INSTANCE = BatchThumbUploadRequest()
        
        /**
         * Create a new builder for BatchThumbUploadRequest
         */
        @JvmStatic
        fun newBuilder(): Builder {
            return Builder()
        }

        /**
         * Get the default instance
         */
        @JvmStatic
        fun getDefaultInstance(): BatchThumbUploadRequest {
            return DEFAULT_INSTANCE
        }
    }
} 