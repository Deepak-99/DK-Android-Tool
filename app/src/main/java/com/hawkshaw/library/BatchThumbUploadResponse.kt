package com.hawkshaw.library

import java.io.InputStream
import java.nio.ByteBuffer
import java.util.Collections

/**
 * Interface for accessing Thumb properties
 */
interface ThumbOrBuilder {
    /**
     * Get the path where the thumbnail was stored
     */
    fun getPath(): String

    /**
     * Get the path as bytes
     */
    fun getPathBytes(): ByteArray
}

/**
 * Response for batch thumbnail uploads
 */
class BatchThumbUploadResponse private constructor() : BatchThumbUploadResponseOrBuilder {
    private var success_ = mutableListOf<Thumb>()

    /**
     * Get a specific thumb from the success list
     */
    override fun getSuccess(index: Int): Thumb {
        return success_[index]
    }

    /**
     * Get the number of successful thumbnail uploads
     */
    override fun getSuccessCount(): Int {
        return success_.size
    }

    /**
     * Get the list of successful thumbnail uploads
     */
    override fun getSuccessList(): List<Thumb> {
        return Collections.unmodifiableList(success_)
    }

    private fun addAllSuccess(values: Iterable<Thumb>) {
        success_.addAll(values)
    }

    private fun addSuccess(value: Thumb) {
        success_.add(value)
    }

    private fun addSuccess(index: Int, value: Thumb) {
        success_.add(index, value)
    }

    private fun clearSuccess() {
        success_.clear()
    }

    private fun removeSuccess(index: Int) {
        success_.removeAt(index)
    }

    private fun setSuccess(index: Int, value: Thumb) {
        success_[index] = value
    }

    /**
     * Builder for BatchThumbUploadResponse
     */
    class Builder {
        private val instance = DEFAULT_INSTANCE

        /**
         * Add all thumbnails to the success list
         */
        fun addAllSuccess(values: Iterable<Thumb>): Builder {
            instance.addAllSuccess(values)
            return this
        }

        /**
         * Add a thumbnail to the success list
         */
        fun addSuccess(value: Thumb): Builder {
            instance.addSuccess(value)
            return this
        }

        /**
         * Add a thumbnail at specific index to the success list
         */
        fun addSuccess(index: Int, value: Thumb): Builder {
            instance.addSuccess(index, value)
            return this
        }

        /**
         * Add a thumbnail to the success list using a builder
         */
        fun addSuccess(builderForValue: Thumb.Builder): Builder {
            instance.addSuccess(builderForValue.build())
            return this
        }

        /**
         * Add a thumbnail at specific index to the success list using a builder
         */
        fun addSuccess(index: Int, builderForValue: Thumb.Builder): Builder {
            instance.addSuccess(index, builderForValue.build())
            return this
        }

        /**
         * Clear all thumbnails from the success list
         */
        fun clearSuccess(): Builder {
            instance.clearSuccess()
            return this
        }

        /**
         * Get a specific thumb from the success list
         */
        fun getSuccess(index: Int): Thumb {
            return instance.getSuccess(index)
        }

        /**
         * Get the number of successful thumbnail uploads
         */
        fun getSuccessCount(): Int {
            return instance.getSuccessCount()
        }

        /**
         * Get the list of successful thumbnail uploads (unmodifiable)
         */
        fun getSuccessList(): List<Thumb> {
            return instance.getSuccessList()
        }

        /**
         * Remove a thumbnail from the success list at a specific index
         */
        fun removeSuccess(index: Int): Builder {
            instance.removeSuccess(index)
            return this
        }

        /**
         * Set a thumbnail in the success list at a specific index
         */
        fun setSuccess(index: Int, value: Thumb): Builder {
            instance.setSuccess(index, value)
            return this
        }

        /**
         * Set a thumbnail in the success list at a specific index using a builder
         */
        fun setSuccess(index: Int, builderForValue: Thumb.Builder): Builder {
            instance.setSuccess(index, builderForValue.build())
            return this
        }
        
        /**
         * Build the response
         */
        fun build(): BatchThumbUploadResponse {
            return instance
        }
    }

    /**
     * Thumb information for successful uploads
     */
    class Thumb private constructor() : ThumbOrBuilder {
        private var path_: String = ""

        /**
         * Get the path where the thumbnail was stored
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

        private fun clearPath() {
            path_ = ""
        }

        private fun setPath(value: String) {
            path_ = value
        }

        private fun setPathBytes(value: ByteArray) {
            path_ = value.toString(Charsets.UTF_8)
        }

        /**
         * Builder for Thumb
         */
        class Builder {
            private val instance = DEFAULT_INSTANCE

            /**
             * Clear the path where the thumbnail was stored
             */
            fun clearPath(): Builder {
                instance.clearPath()
                return this
            }

            /**
             * Get the path where the thumbnail was stored
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
             * Set the path where the thumbnail was stored
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
             * Build the thumb
             */
            fun build(): Thumb {
                return instance
            }
        }

        companion object {
            val DEFAULT_INSTANCE = Thumb()
            
            /**
             * Create a new builder for Thumb
             */
            @JvmStatic
            fun newBuilder(): Builder {
                return Builder()
            }

            /**
             * Get the default instance
             */
            @JvmStatic
            fun getDefaultInstance(): Thumb {
                return DEFAULT_INSTANCE
            }
        }
    }

    companion object {
        val DEFAULT_INSTANCE = BatchThumbUploadResponse()
        
        /**
         * Create a new builder for BatchThumbUploadResponse
         */
        @JvmStatic
        fun newBuilder(): Builder {
            return Builder()
        }

        /**
         * Get the default instance
         */
        @JvmStatic
        fun getDefaultInstance(): BatchThumbUploadResponse {
            return DEFAULT_INSTANCE
        }
    }
} 