package com.hawkshaw.library.datalayer.network.grpc.interceptors

import io.grpc.CallOptions
import io.grpc.Channel
import io.grpc.ClientCall
import io.grpc.ClientInterceptor
import io.grpc.ForwardingClientCall
import io.grpc.Metadata
import io.grpc.MethodDescriptor
import com.hawkshaw.app.BuildConfig

/**
 * A gRPC interceptor that adds authentication and application version headers to outgoing requests
 */
class AuthInterceptor : ClientInterceptor {
    private val tokenScheme = "Bearer"
    private val baseHeaders = Metadata().apply {
        put(
            Metadata.Key.of("x-version-code", Metadata.ASCII_STRING_MARSHALLER),
            "109"
        )
        put(
            Metadata.Key.of("x-version-name", Metadata.ASCII_STRING_MARSHALLER),
            try {
                BuildConfig.VERSION_NAME
            } catch (e: Exception) {
                "1.0.0" // Fallback version
            }
        )
    }

    override fun <ReqT, RespT> interceptCall(
        method: MethodDescriptor<ReqT, RespT>,
        callOptions: CallOptions,
        next: Channel
    ): ClientCall<ReqT, RespT> {
        // Forward the call with added authentication headers
        return object : ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(
            next.newCall(method, callOptions)
        ) {
            override fun start(responseListener: Listener<RespT>, headers: Metadata) {
                // Add base headers that include version information
                headers.merge(baseHeaders)
                
                // Add authentication token if available
                // Note: In real implementation, this would get the token from some secure storage
                // val token = getTokenFromStorage()
                // if (token != null) {
                //     headers.put(
                //         Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER),
                //         "$tokenScheme $token"
                //     )
                // }
                
                super.start(responseListener, headers)
            }
        }
    }
} 