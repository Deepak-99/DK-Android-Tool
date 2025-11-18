package com.hawkshaw.library

import java.io.InputStream
import java.nio.ByteBuffer
import android.util.Log
// Removed import com.hawkshaw.library.FileUploadResponse.Companion.COMPANION_TAG as it's generally not needed for self-reference

/**
 * Response for file uploads
 */
class FileUploadResponse private constructor() : FileUploadResponseOrBuilder {

    // Single Companion Object for FileUploadResponse
    companion object {
        // TAG for the main FileUploadResponse instance methods and private setters
        private const val TAG = "FileUploadRespLogs"
        // TAG for the static methods and properties of FileUploadResponse
        private const val COMPANION_TAG = "FileUploadRespCompanion"

        // DEFAULT_INSTANCE is defined here. The Builder class will reference this.
        val DEFAULT_INSTANCE = FileUploadResponse()

        // Static constants for field numbers
        const val NAME_FIELD_NUMBER = 1
        const val PUBLIC_URL_FIELD_NUMBER = 2
        const val STATUS_FIELD_NUMBER = 3

        /**
         * Create a new builder for FileUploadResponse
         */
        @JvmStatic
        fun newBuilder(): Builder {
            Log.d(COMPANION_TAG, "[DEBUG] newBuilder() called.")
            val builder = Builder()
            Log.d(COMPANION_TAG, "[DEBUG] newBuilder() returning new Builder: $builder")
            return builder
        }

        /**
         * Get the default instance
         */
        @JvmStatic
        fun getDefaultInstance(): FileUploadResponse {
            Log.d(COMPANION_TAG, "[DEBUG] getDefaultInstance() called. Returning: $DEFAULT_INSTANCE")
            return DEFAULT_INSTANCE
        }
    }

    private var name_: String = ""
    private var publicUrl_: String = ""
    private var status_: Int = Status.PENDING.number

    init {
        Log.d(TAG, "[DEBUG] FileUploadResponse private constructor invoked. Current state: name='${this.name_}', publicUrl='${this.publicUrl_}', status=${this.status_}")
    }

    override fun getName(): String {
        Log.d(TAG, "[DEBUG] getName() called. Returning: \"${name_}\"")
        return name_
    }

    override fun getNameBytes(): ByteArray {
        val bytes = name_.toByteArray(Charsets.UTF_8)
        Log.d(TAG, "[DEBUG] getNameBytes() called. Returning byte array of size ${bytes.size} for name \"${name_}\"")
        return bytes
    }

    override fun getPublicUrl(): String {
        Log.d(TAG, "[DEBUG] getPublicUrl() called. Returning: \"${publicUrl_}\"")
        return publicUrl_
    }

    override fun getPublicUrlBytes(): ByteArray {
        val bytes = publicUrl_.toByteArray(Charsets.UTF_8)
        Log.d(TAG, "[DEBUG] getPublicUrlBytes() called. Returning byte array of size ${bytes.size} for publicUrl \"${publicUrl_}\"")
        return bytes
    }

    /**
     * Get the status of the file upload
     */
    override fun getStatus(): Status {
        val statusEnum = Status.forNumber(status_) ?: Status.UNRECOGNIZED
        Log.d(TAG, "[DEBUG] getStatus() called. Int status: $status_, Returning Enum: $statusEnum")
        return statusEnum
    }

    /**
     * Get the status value as an integer
     */
    override fun getStatusValue(): Int {
        Log.d(TAG, "[DEBUG] getStatusValue() called. Returning: $status_")
        return status_
    }

    private fun clearName() {
        Log.d(TAG, "[DEBUG] private clearName() called. Old name: \"${name_}\"")
        name_ = ""
        Log.d(TAG, "[DEBUG] private clearName(): Name cleared.")
    }

    private fun clearPublicUrl() {
        Log.d(TAG, "[DEBUG] private clearPublicUrl() called. Old publicUrl: \"${publicUrl_}\"")
        publicUrl_ = ""
        Log.d(TAG, "[DEBUG] private clearPublicUrl(): PublicUrl cleared.")
    }

    private fun clearStatus() {
        Log.d(TAG, "[DEBUG] private clearStatus() called. Old status: $status_")
        status_ = Status.PENDING.number // Default to PENDING's number
        Log.d(TAG, "[DEBUG] private clearStatus(): Status cleared to ${Status.PENDING.number}.")
    }

    private fun setName(value: String) {
        Log.d(TAG, "[DEBUG] private setName() called. Value: \"$value\"")
        name_ = value
        Log.d(TAG, "[DEBUG] private setName(): Name set to \"${name_}\"")
    }

    private fun setNameBytes(value: ByteArray) {
        val strValue = value.toString(Charsets.UTF_8)
        Log.d(TAG, "[DEBUG] private setNameBytes() called. ByteArray size: ${value.size}, String value: \"$strValue\"")
        name_ = strValue
        Log.d(TAG, "[DEBUG] private setNameBytes(): Name set to \"${name_}\"")
    }

    private fun setPublicUrl(value: String) {
        Log.d(TAG, "[DEBUG] private setPublicUrl() called. Value: \"$value\"")
        publicUrl_ = value
        Log.d(TAG, "[DEBUG] private setPublicUrl(): PublicUrl set to \"${publicUrl_}\"")
    }

    private fun setPublicUrlBytes(value: ByteArray) {
        val strValue = value.toString(Charsets.UTF_8)
        Log.d(TAG, "[DEBUG] private setPublicUrlBytes() called. ByteArray size: ${value.size}, String value: \"$strValue\"")
        publicUrl_ = strValue
        Log.d(TAG, "[DEBUG] private setPublicUrlBytes(): PublicUrl set to \"${publicUrl_}\"")
    }

    private fun setStatus(value: Status) {
        Log.d(TAG, "[DEBUG] private setStatus() called. Enum Value: $value")
        status_ = value.number
        Log.d(TAG, "[DEBUG] private setStatus(): Int status set to $status_")
    }

    private fun setStatusValue(value: Int) {
        Log.d(TAG, "[DEBUG] private setStatusValue() called. Int Value: $value")
        status_ = value
        Log.d(TAG, "[DEBUG] private setStatusValue(): Int status set to $status_")
    }

    /**
     * Builder for FileUploadResponse (Inner Class)
     */
    class Builder {
        // TAG for the Builder class methods
        private val BUILDER_TAG = "FileUploadRespBuilder" // Changed to val, const not strictly needed here for an inner class tag if not used in annotations

        // The Builder operates on the single DEFAULT_INSTANCE from the outer class's companion object.
        private val instance = DEFAULT_INSTANCE

        init {
            Log.d(BUILDER_TAG, "[DEBUG] Builder instance created, operating on DEFAULT_INSTANCE: $instance")
        }

        fun clearName(): Builder {
            Log.d(BUILDER_TAG, "[DEBUG] Builder.clearName() called.")
            instance.clearName()
            return this
        }

        fun clearPublicUrl(): Builder {
            Log.d(BUILDER_TAG, "[DEBUG] Builder.clearPublicUrl() called.")
            instance.clearPublicUrl()
            return this
        }

        fun clearStatus(): Builder {
            Log.d(BUILDER_TAG, "[DEBUG] Builder.clearStatus() called.")
            instance.clearStatus()
            return this
        }

        fun getName(): String {
            Log.d(BUILDER_TAG, "[DEBUG] Builder.getName() called.")
            return instance.getName()
        }

        fun getNameBytes(): ByteArray {
            Log.d(BUILDER_TAG, "[DEBUG] Builder.getNameBytes() called.")
            return instance.getNameBytes()
        }

        fun getPublicUrl(): String {
            Log.d(BUILDER_TAG, "[DEBUG] Builder.getPublicUrl() called.")
            return instance.getPublicUrl()
        }

        fun getPublicUrlBytes(): ByteArray {
            Log.d(BUILDER_TAG, "[DEBUG] Builder.getPublicUrlBytes() called.")
            return instance.getPublicUrlBytes()
        }

        fun getStatus(): Status {
            Log.d(BUILDER_TAG, "[DEBUG] Builder.getStatus() called.")
            return instance.getStatus()
        }

        fun getStatusValue(): Int {
            Log.d(BUILDER_TAG, "[DEBUG] Builder.getStatusValue() called.")
            return instance.getStatusValue()
        }

        fun setName(value: String): Builder {
            Log.d(BUILDER_TAG, "[DEBUG] Builder.setName() called with value: \"$value\"")
            instance.setName(value)
            return this
        }

        fun setNameBytes(value: ByteArray): Builder {
            Log.d(BUILDER_TAG, "[DEBUG] Builder.setNameBytes() called with ByteArray size: ${value.size}")
            instance.setNameBytes(value)
            return this
        }

        fun setPublicUrl(value: String): Builder {
            Log.d(BUILDER_TAG, "[DEBUG] Builder.setPublicUrl() called with value: \"$value\"")
            instance.setPublicUrl(value)
            return this
        }

        fun setPublicUrlBytes(value: ByteArray): Builder {
            Log.d(BUILDER_TAG, "[DEBUG] Builder.setPublicUrlBytes() called with ByteArray size: ${value.size}")
            instance.setPublicUrlBytes(value)
            return this
        }

        fun setStatus(value: Status): Builder {
            Log.d(BUILDER_TAG, "[DEBUG] Builder.setStatus() called with Enum value: $value")
            instance.setStatus(value)
            return this
        }

        fun setStatusValue(value: Int): Builder {
            Log.d(BUILDER_TAG, "[DEBUG] Builder.setStatusValue() called with Int value: $value")
            instance.setStatusValue(value)
            return this
        }

        fun build(): FileUploadResponse {
            Log.d(BUILDER_TAG, "[DEBUG] Builder.build() called. Returning instance: $instance")
            return instance
        }
    }

    /**
     * Status of the file upload
     */
    enum class Status(val number: Int) {
        PENDING(0),
        IN_PROGRESS(1),
        SUCCESS(2),
        FAILED(3),
        UNRECOGNIZED(-1);

        // Companion object for Status enum (this is standard and correct)
        companion object {
            private const val STATUS_ENUM_TAG = "FileUploadRespStatus"
            @JvmStatic
            fun forNumber(value: Int): Status? {
                Log.d(STATUS_ENUM_TAG, "[DEBUG] Status.forNumber() called with value: $value")
                val status = when (value) {
                    0 -> PENDING
                    1 -> IN_PROGRESS
                    2 -> SUCCESS
                    3 -> FAILED
                    else -> null // Or UNRECOGNIZED if it should always return a Status
                }
                Log.d(STATUS_ENUM_TAG, "[DEBUG] Status.forNumber($value) returning: $status")
                return status
            }

            @Deprecated("Use forNumber instead", ReplaceWith("forNumber(value)"))
            @JvmStatic
            fun valueOf(value: Int): Status? { // Renamed from forNumber to valueOf to match original, but it's just an alias
                Log.d(STATUS_ENUM_TAG, "[DEBUG] Status.valueOf() (deprecated) called with value: $value")
                return forNumber(value)
            }
        }
    }
}
