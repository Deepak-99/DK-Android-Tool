package com.hawkshaw.library

/**
 * Interface for accessing FileUploadResponse properties.
 */
interface FileUploadResponseOrBuilder {
    /**
     * Get the name of the uploaded file
     * @return file name as a string
     */
    fun getName(): String

    /**
     * Get the name as bytes
     * @return file name as ByteString
     */
    fun getNameBytes(): ByteArray

    /**
     * Get the public URL of the uploaded file
     * @return public URL as a string
     */
    fun getPublicUrl(): String

    /**
     * Get the public URL as bytes
     * @return public URL as ByteString
     */
    fun getPublicUrlBytes(): ByteArray

    /**
     * Get the status of the file upload
     * @return Status enum value
     */
    fun getStatus(): FileUploadResponse.Status

    /**
     * Get the status value as an integer
     * @return status as an integer
     */
    fun getStatusValue(): Int
} 