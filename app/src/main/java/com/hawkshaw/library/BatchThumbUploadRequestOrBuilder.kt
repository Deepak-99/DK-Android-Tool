package com.hawkshaw.library

/**
 * Interface for accessing BatchThumbUploadRequest properties.
 */
interface BatchThumbUploadRequestOrBuilder {
    /**
     * Get the thumbnail data for the batch upload
     * @return Thumbnail instance or null if not present
     */
    fun getThumb(): BatchThumbUploadRequest.Thumbnail?

    /**
     * Check if this request contains thumbnail data
     * @return true if thumbnail is present
     */
    fun hasThumb(): Boolean
} 