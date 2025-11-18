package com.hawkshaw.library.network.model

/**
 * Sealed class representing the result of an API call.
 *
 * @param T The type of data that will be returned on success
 */
sealed class ApiResponse<out T> {
    /**
     * Represents a successful API response.
     *
     * @property data The data returned by the API call
     */
    data class Success<out T>(val data: T) : ApiResponse<T>() {
        /**
         * Maps the success data to a different type using the provided [mapper] function.
         *
         * @param mapper The function to transform the success data
         * @return A new [Success] with the transformed data
         */
        fun <R> mapSuccess(mapper: (T) -> R): Success<R> = Success(mapper(data))
    }

    /**
     * Represents a failed API response.
     *
     * @property message A message describing the error
     */
    data class Error(val message: String) : ApiResponse<Nothing>() {
        /**
         * Creates an [Error] with a custom message.
         *
         * @param customMessage The custom error message
         * @return A new [Error] with the custom message
         */
        fun withMessage(customMessage: String): Error = Error(customMessage)
    }

    /**
     * Executes the appropriate block based on the API response.
     *
     * @param onSuccess The block to execute for a successful response
     * @param onError The block to execute for an error response
     * @return The result of the executed block
     */
    inline fun <R> fold(
        onSuccess: (T) -> R,
        onError: (String) -> R
    ): R = when (this) {
        is Success -> onSuccess(data)
        is Error -> onError(message)
    }

    /**
     * Maps the success data to a different type using the provided [mapper] function.
     *
     * @param mapper The function to transform the success data
     * @return A new [ApiResponse] with the transformed data
     */
    @Suppress("UNCHECKED_CAST")
    fun <R> map(mapper: (T) -> R): ApiResponse<R> = when (this) {
        is Success -> Success(mapper(data))
        is Error -> this as ApiResponse<R>
    }

    /**
     * Maps the error message to a different type using the provided [mapper] function.
     *
     * @param mapper The function to transform the error message
     * @return A new [ApiResponse] with the transformed error message
     */
    fun mapError(mapper: (String) -> String): ApiResponse<T> = when (this) {
        is Success -> this
        is Error -> Error(mapper(message))
    }

    /**
     * Gets the success data if available, or null otherwise.
     */
    fun getOrNull(): T? = (this as? Success)?.data

    /**
     * Gets the success data if available, or the [default] value otherwise.
     *
     * @param default The default value to return if the response is an error
     * @return The success data or the default value
     */
    fun getOrDefault(default: @UnsafeVariance T): T = (this as? Success)?.data ?: default

    companion object {
        /**
         * Creates a success response with the given [data].
         */
        fun <T> success(data: T): ApiResponse<T> = Success(data)

        /**
         * Creates an error response with the given [message].
         */
        fun <T> error(message: String): ApiResponse<T> = Error(message)
    }
}
