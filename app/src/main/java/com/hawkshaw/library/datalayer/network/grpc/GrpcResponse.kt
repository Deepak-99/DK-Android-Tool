package com.hawkshaw.library.datalayer.network.grpc

/**
 * A sealed class representing the different states of a gRPC call response.
 *
 * @param T The type of data returned by the gRPC call
 */
sealed class GrpcResponse<out T> {
    /**
     * Default state, no response yet.
     */
    object Default : GrpcResponse<Nothing>()
    
    /**
     * Represents a gRPC call that is in progress.
     */
    object InProgress : GrpcResponse<Nothing>()

    /**
     * Represents a successful gRPC call with a result.
     *
     * @param data The data returned by the successful gRPC call
     */
    data class Success<T>(val data: T) : GrpcResponse<T>()

    /**
     * Represents a failed gRPC call with an error.
     *
     * @param error The error that occurred during the gRPC call
     */
    data class Error(val error: GrpcError) : GrpcResponse<Nothing>()
} 