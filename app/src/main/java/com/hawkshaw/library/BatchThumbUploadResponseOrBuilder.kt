package com.hawkshaw.library

/**
 * Interface for accessing BatchThumbUploadResponse properties.
 */
interface BatchThumbUploadResponseOrBuilder {
    /**
     * Get a specific thumb from the success list
     * @param index zero-based index of the element to retrieve
     * @return Thumb instance at the specified index
     */
    fun getSuccess(index: Int): BatchThumbUploadResponse.Thumb

    /**
     * Get the number of successful thumbnail uploads
     * @return count of successful thumbnails
     */
    fun getSuccessCount(): Int

    /**
     * Get the list of successful thumbnail uploads
     * @return unmodifiable list of Thumb instances
     */
    fun getSuccessList(): List<BatchThumbUploadResponse.Thumb>
} 