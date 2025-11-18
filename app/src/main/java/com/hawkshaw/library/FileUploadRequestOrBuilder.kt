package com.hawkshaw.library

/**
 * Interface for accessing FileUploadRequest properties.
 */
interface FileUploadRequestOrBuilder {
    /**
     * Get file content if present
     * @return File instance or null if not present
     */
    fun getFile(): FileUploadRequest.File?

    /**
     * Get metadata if present
     * @return MetaData instance or null if not present
     */
    fun getMetadata(): FileUploadRequest.MetaData?

    /**
     * Get the type of request in this instance
     * @return RequestCase enum value
     */
    fun getRequestCase(): FileUploadRequest.RequestCase

    /**
     * Check if this request contains file data
     * @return true if file is present
     */
    fun hasFile(): Boolean

    /**
     * Check if this request contains metadata
     * @return true if metadata is present
     */
    fun hasMetadata(): Boolean
} 