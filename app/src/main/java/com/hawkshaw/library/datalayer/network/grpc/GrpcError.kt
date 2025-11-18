package com.hawkshaw.library.datalayer.network.grpc

/**
 * Exception class for gRPC errors
 *
 * @property cause The underlying cause of the error
 * @property message A description of the error
 */
class GrpcError(
    override val cause: Throwable? = null,
    override val message: String? = cause?.message
) : RuntimeException() 