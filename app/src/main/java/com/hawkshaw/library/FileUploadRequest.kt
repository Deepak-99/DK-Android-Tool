package com.hawkshaw.library

import java.io.InputStream
import java.nio.ByteBuffer

/**
 * Interface for accessing File properties
 */
interface FileOrBuilder {
    /**
     * Get the binary content of this file
     */
    fun getContent(): ByteArray
}

/**
 * Interface for accessing MetaData properties
 */
interface MetaDataOrBuilder {
    /**
     * Get the filename
     */
    fun getFilename(): String

    /**
     * Get the MIME type
     */
    fun getMimeType(): String
}

/**
 * Type for file upload requests
 */
class FileUploadRequest private constructor() : FileUploadRequestOrBuilder {
    private var requestCase: RequestCase = RequestCase.REQUEST_NOT_SET
    private var request_: Any? = null

    /**
     * Get the file data if this request contains file content
     */
    override fun getFile(): File? {
        return if (requestCase == RequestCase.FILE) request_ as File else null
    }

    /**
     * Get the metadata if this request contains metadata
     */
    override fun getMetadata(): MetaData? {
        return if (requestCase == RequestCase.METADATA) request_ as MetaData else null
    }

    /**
     * Get the type of request this instance represents
     */
    override fun getRequestCase(): RequestCase {
        return requestCase
    }

    /**
     * Check if this request contains file data
     */
    override fun hasFile(): Boolean {
        return requestCase == RequestCase.FILE
    }

    /**
     * Check if this request contains metadata
     */
    override fun hasMetadata(): Boolean {
        return requestCase == RequestCase.METADATA
    }

    private fun clearFile() {
        if (requestCase == RequestCase.FILE) {
            requestCase = RequestCase.REQUEST_NOT_SET
            request_ = null
        }
    }

    private fun clearMetadata() {
        if (requestCase == RequestCase.METADATA) {
            requestCase = RequestCase.REQUEST_NOT_SET
            request_ = null
        }
    }

    private fun clearRequest() {
        requestCase = RequestCase.REQUEST_NOT_SET
        request_ = null
    }

    private fun mergeFile(file: File) {
        request_ = if (requestCase == RequestCase.FILE && request_ != DEFAULT_INSTANCE.getFile()) {
            File.newBuilder(request_ as File).mergeFrom(file).build()
        } else {
            file
        }
        requestCase = RequestCase.FILE
    }

    private fun mergeMetadata(metadata: MetaData) {
        request_ = if (requestCase == RequestCase.METADATA && request_ != DEFAULT_INSTANCE.getMetadata()) {
            MetaData.newBuilder(request_ as MetaData).mergeFrom(metadata).build()
        } else {
            metadata
        }
        requestCase = RequestCase.METADATA
    }

    private fun setFile(file: File) {
        file.also {
            request_ = it
            requestCase = RequestCase.FILE
        }
    }

    private fun setMetadata(metadata: MetaData) {
        metadata.also {
            request_ = it
            requestCase = RequestCase.METADATA
        }
    }

    /**
     * Builder for FileUploadRequest
     */
    class Builder {
        private val instance = DEFAULT_INSTANCE

        /**
         * Clear file content from this request if present
         */
        fun clearFile(): Builder {
            instance.clearFile()
            return this
        }

        /**
         * Clear metadata from this request if present
         */
        fun clearMetadata(): Builder {
            instance.clearMetadata()
            return this
        }

        /**
         * Clear any type of request data from this instance
         */
        fun clearRequest(): Builder {
            instance.clearRequest()
            return this
        }

        /**
         * Get the file data if this request contains file content
         */
        fun getFile(): File? {
            return instance.getFile()
        }

        /**
         * Get the metadata if this request contains metadata
         */
        fun getMetadata(): MetaData? {
            return instance.getMetadata()
        }

        /**
         * Get the type of request this instance represents
         */
        fun getRequestCase(): RequestCase {
            return instance.getRequestCase()
        }

        /**
         * Check if this request contains file data
         */
        fun hasFile(): Boolean {
            return instance.hasFile()
        }

        /**
         * Check if this request contains metadata
         */
        fun hasMetadata(): Boolean {
            return instance.hasMetadata()
        }

        /**
         * Merge file content into this request
         */
        fun mergeFile(file: File): Builder {
            instance.mergeFile(file)
            return this
        }

        /**
         * Merge metadata into this request
         */
        fun mergeMetadata(metadata: MetaData): Builder {
            instance.mergeMetadata(metadata)
            return this
        }

        /**
         * Set file content for this request
         */
        fun setFile(file: File): Builder {
            instance.setFile(file)
            return this
        }

        /**
         * Set file content for this request using a builder
         */
        fun setFile(builder: File.Builder): Builder {
            instance.setFile(builder.build())
            return this
        }

        /**
         * Set metadata for this request
         */
        fun setMetadata(metadata: MetaData): Builder {
            instance.setMetadata(metadata)
            return this
        }

        /**
         * Set metadata for this request using a builder
         */
        fun setMetadata(builder: MetaData.Builder): Builder {
            instance.setMetadata(builder.build())
            return this
        }
        
        /**
         * Build the FileUploadRequest
         */
        fun build(): FileUploadRequest {
            return instance
        }
    }

    /**
     * File content for upload
     */
    class File private constructor() : FileOrBuilder {
        private var content_: ByteArray = ByteArray(0)

        /**
         * Get the binary content of this file
         */
        override fun getContent(): ByteArray {
            return content_
        }

        private fun clearContent() {
            content_ = ByteArray(0)
        }

        private fun setContent(value: ByteArray) {
            value.also { content_ = it }
        }

        /**
         * Builder for File
         */
        class Builder {
            private val instance = DEFAULT_INSTANCE

            /**
             * Clear the content of this file
             */
            fun clearContent(): Builder {
                instance.clearContent()
                return this
            }

            /**
             * Get the binary content of this file
             */
            fun getContent(): ByteArray {
                return instance.getContent()
            }

            /**
             * Set the binary content of this file
             */
            fun setContent(value: ByteArray): Builder {
                instance.setContent(value)
                return this
            }
            
            /**
             * Build the File
             */
            fun build(): File {
                return instance
            }
            
            /**
             * Merge from another File
             */
            fun mergeFrom(other: File): Builder {
                if (other.getContent().isNotEmpty()) {
                    setContent(other.getContent())
                }
                return this
            }
        }

        companion object {
            val DEFAULT_INSTANCE = File()
            const val CONTENT_FIELD_NUMBER = 1

            /**
             * Create a new builder for File
             */
            @JvmStatic
            fun newBuilder(): Builder {
                return Builder()
            }

            /**
             * Create a new builder from an existing File
             */
            @JvmStatic
            fun newBuilder(prototype: File): Builder {
                return newBuilder().mergeFrom(prototype)
            }

            /**
             * Get the default instance
             */
            @JvmStatic
            fun getDefaultInstance(): File {
                return DEFAULT_INSTANCE
            }
        }
    }

    /**
     * Metadata for file upload
     */
    class MetaData private constructor() : MetaDataOrBuilder {
        private var filename_: String = ""
        private var mimeType_: String = ""

        /**
         * Get the filename
         */
        override fun getFilename(): String {
            return filename_
        }

        /**
         * Get the MIME type
         */
        override fun getMimeType(): String {
            return mimeType_
        }

        private fun clearFilename() {
            filename_ = ""
        }

        private fun clearMimeType() {
            mimeType_ = ""
        }

        private fun setFilename(value: String) {
            value.also { filename_ = it }
        }

        private fun setMimeType(value: String) {
            value.also { mimeType_ = it }
        }

        /**
         * Builder for MetaData
         */
        class Builder {
            private val instance = DEFAULT_INSTANCE

            /**
             * Clear the filename
             */
            fun clearFilename(): Builder {
                instance.clearFilename()
                return this
            }

            /**
             * Clear the MIME type
             */
            fun clearMimeType(): Builder {
                instance.clearMimeType()
                return this
            }

            /**
             * Get the filename
             */
            fun getFilename(): String {
                return instance.getFilename()
            }

            /**
             * Get the MIME type
             */
            fun getMimeType(): String {
                return instance.getMimeType()
            }

            /**
             * Set the filename
             */
            fun setFilename(value: String): Builder {
                instance.setFilename(value)
                return this
            }

            /**
             * Set the MIME type
             */
            fun setMimeType(value: String): Builder {
                instance.setMimeType(value)
                return this
            }
            
            /**
             * Build the MetaData
             */
            fun build(): MetaData {
                return instance
            }
            
            /**
             * Merge from another MetaData
             */
            fun mergeFrom(other: MetaData): Builder {
                if (other.getFilename().isNotEmpty()) {
                    setFilename(other.getFilename())
                }
                if (other.getMimeType().isNotEmpty()) {
                    setMimeType(other.getMimeType())
                }
                return this
            }
        }

        companion object {
            val DEFAULT_INSTANCE = MetaData()
            const val FILENAME_FIELD_NUMBER = 1
            const val MIME_TYPE_FIELD_NUMBER = 2

            /**
             * Create a new builder for MetaData
             */
            @JvmStatic
            fun newBuilder(): Builder {
                return Builder()
            }

            /**
             * Create a new builder from an existing MetaData
             */
            @JvmStatic
            fun newBuilder(prototype: MetaData): Builder {
                return newBuilder().mergeFrom(prototype)
            }

            /**
             * Get the default instance
             */
            @JvmStatic
            fun getDefaultInstance(): MetaData {
                return DEFAULT_INSTANCE
            }
        }
    }

    /**
     * Types of request content
     */
    enum class RequestCase {
        FILE,
        METADATA,
        REQUEST_NOT_SET
    }

    companion object {
        val DEFAULT_INSTANCE = FileUploadRequest()
        const val FILE_FIELD_NUMBER = 1
        const val METADATA_FIELD_NUMBER = 2

        /**
         * Create a new builder for FileUploadRequest
         */
        @JvmStatic
        fun newBuilder(): Builder {
            return Builder()
        }

        /**
         * Get the default instance
         */
        @JvmStatic
        fun getDefaultInstance(): FileUploadRequest {
            return DEFAULT_INSTANCE
        }
    }
} 