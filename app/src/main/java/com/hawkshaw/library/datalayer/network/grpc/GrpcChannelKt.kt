package com.hawkshaw.library.datalayer.network.grpc

import io.grpc.ManagedChannel
import com.hawkshaw.library.App
import com.hawkshaw.library.config.RemoteConfig
import com.hawkshaw.library.datalayer.network.grpc.interceptors.AuthInterceptor
import com.hawkshaw.library.datalayer.network.grpc.interceptors.LoggingInterceptor
import io.grpc.android.AndroidChannelBuilder
import java.net.URI
import java.net.URISyntaxException
import java.util.concurrent.TimeUnit
import android.util.Log // Added for logging

/**
 * Utility class for managing the gRPC channel
 */
object GrpcChannelKt {
    private const val TAG = "GrpcChannelKtLogs" // Added for logging

    /**
     * The gRPC channel instance.
     * This property provides the ManagedChannel.
     * @JvmStatic makes it accessible from Java like GrpcChannelKt.getChannel().
     */
    @JvmStatic // Apply @JvmStatic directly to the property to expose it as a static getter in Java
    val channel: ManagedChannel by lazy {
        Log.d(TAG, "[DEBUG] Initializing gRPC channel (lazy block)...")
        // Pass App.getContext() to getInstance method
        val remoteConfig = RemoteConfig.getInstance(App.getContext())
        val grpcName = remoteConfig.getGrpcName()
        val grpcPort = remoteConfig.getGrpcPort()
        Log.d(TAG, "[DEBUG] RemoteConfig: grpc_channel_name='$grpcName', grpc_channel_port=$grpcPort")

        if (grpcName == null) {
            Log.e(TAG, "[DEBUG] gRPC channel name not configured in RemoteConfig. Throwing IllegalStateException.")
            throw IllegalStateException("gRPC channel name not configured")
        }
        Log.d(TAG, "[DEBUG] gRPC channel name is present: '$grpcName'")

        try {
            // Create URI to parse authority component properly
            val authority = URI(null, null, grpcName, grpcPort, null, null, null).authority.toString()
            Log.d(TAG, "[DEBUG] Constructed authority for channel: '$authority'")

            // Create and configure the channel using AndroidChannelBuilder
            Log.d(TAG, "[DEBUG] Creating AndroidChannelBuilder for target: '$authority'")
            val builder = AndroidChannelBuilder
                .forTarget(authority)
                .context(App.getContext().also { Log.d(TAG, "[DEBUG] Setting context for channel builder: $it") })
                .keepAliveTime(5, TimeUnit.SECONDS).also { Log.d(TAG, "[DEBUG] Setting keepAliveTime: 5 SECONDS") }
                .keepAliveTimeout(10, TimeUnit.SECONDS).also { Log.d(TAG, "[DEBUG] Setting keepAliveTimeout: 10 SECONDS") }

            // Use TLS if port is 443
            if (grpcPort == 443) {
                Log.d(TAG, "[DEBUG] Port is 443. Using transport security.")
                builder.useTransportSecurity()
            } else {
                Log.d(TAG, "[DEBUG] Port is not 443 (it's $grpcPort). Using plaintext.")
                builder.usePlaintext()
            }

            // Add interceptors
            val authInterceptor = AuthInterceptor()
            val loggingInterceptor = LoggingInterceptor(
                LoggingInterceptor.Level.HEADERS,
                LoggingInterceptor.Level.STATUS
            )
            Log.d(TAG, "[DEBUG] Adding interceptors: AuthInterceptor ($authInterceptor), LoggingInterceptor ($loggingInterceptor with levels HEADERS, STATUS)")
            builder.intercept(
                authInterceptor,
                loggingInterceptor
            )

            // Build and return the channel
            Log.d(TAG, "[DEBUG] Building the channel...")
            val builtChannel = builder.build()
            Log.d(TAG, "[DEBUG] Channel built successfully: $builtChannel")
            builtChannel
        } catch (e: URISyntaxException) {
            Log.e(TAG, "[DEBUG] URISyntaxException while creating channel authority for host '$grpcName':$grpcPort. Throwing IllegalArgumentException.", e)
            throw IllegalArgumentException("Invalid host or port: $grpcName:$grpcPort", e)
        }
    }

    // Removed the redundant explicit getChannel() function.
    // The @JvmStatic val channel property now serves this purpose.
    // fun getChannel(): ManagedChannel = channel
}
