package com.hawkshaw.library.datalayer.network.twirp

import kotlinx.serialization.Serializable

/**
 * Generic interface for API responses
 */
interface ApiResponse<T> {
    /**
     * Check if the response is successful
     */
    val isSuccess: Boolean

    /**
     * Check if the response is an error
     */
    val isError: Boolean

    /**
     * Get the state of the response
     */
    val state: String

    /**
     * Get the error message if the response is an error
     */
    val errorMessage: String?

    /**
     * Represents a successful API response
     */
    class Success<T>(val result: T) : ApiResponse<T> {
        override val isSuccess: Boolean = true
        override val isError: Boolean = false
        override val state: String = "Success"
        override val errorMessage: String? = null
    }

    /**
     * Represents an API response that is in progress
     */
    class InProgress<T> : ApiResponse<T> {
        override val isSuccess: Boolean = false
        override val isError: Boolean = false
        override val state: String = "InProgress"
        override val errorMessage: String? = null
    }

    /**
     * Represents an error API response
     */
    @Serializable
    class Error<T>(
        val message: String,
        val code: String = "",
        val meta: Map<String, String>? = null
    ) : ApiResponse<T> {
        override val isSuccess: Boolean = false
        override val isError: Boolean = true
        override val state: String = "Error"

        override val errorMessage: String?
            get() {
                val sb = StringBuilder()
                sb.append("Message: $message")
                if (code.isNotBlank()) {
                    sb.append(", Code: $code")
                }
                if (!meta.isNullOrEmpty()) {
                    sb.append(", Meta: $meta")
                }
                return sb.toString()
            }
    }
}