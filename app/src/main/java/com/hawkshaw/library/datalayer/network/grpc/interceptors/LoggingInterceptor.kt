package com.hawkshaw.library.datalayer.network.grpc.interceptors

import android.util.Log
import io.grpc.CallOptions
import io.grpc.Channel
import io.grpc.ClientCall
import io.grpc.ClientInterceptor
import io.grpc.ForwardingClientCall
import io.grpc.Metadata
import io.grpc.MethodDescriptor
import io.grpc.Status
import com.hawkshaw.library.logger.Logger
import java.util.EnumSet

/**
 * A gRPC interceptor that logs request and response information
 */
class LoggingInterceptor(vararg levels: Level) : ClientInterceptor {
    private val levels: EnumSet<Level> = EnumSet.copyOf(levels.toList())

    init {
        Log.d(TAG, "[DEBUG] LoggingInterceptor initialized. Enabled levels: ${this.levels}")
    }

    /**
     * The logging levels available
     */
    enum class Level {
        /** Log status information */
        STATUS,
        
        /** Log headers information */
        HEADERS,
        
        /** Log message contents */
        MESSAGE
    }

    /**
     * Main interceptor method that logs information about calls
     */
    override fun <ReqT, RespT> interceptCall(
        method: MethodDescriptor<ReqT, RespT>,
        callOptions: CallOptions,
        next: Channel
    ): ClientCall<ReqT, RespT> {
        Log.d(TAG, "[DEBUG] interceptCall: Method: ${method.fullMethodName}, CallOptions: $callOptions")
        // Log method information if enabled
        Log.d(TAG, "[DEBUG] interceptCall: Checking if STATUS level is enabled for logMethod.")
        logMethod(method) // logMethod itself checks levels.contains(Level.STATUS)

        Log.d(TAG, "[DEBUG] interceptCall: Creating ForwardingClientCall. Calling next.newCall.")
        // Create a forwarding call that can log headers and status
        return object : ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(
            next.newCall(method, callOptions)
        ) {
            init {
                Log.d(TAG, "[DEBUG] ForwardingClientCall instance created for method: ${method.fullMethodName}")
            }

            override fun sendMessage(message: ReqT) {
                Log.d(TAG, "[DEBUG] ForwardingClientCall.sendMessage called. Message: $message")
                Log.d(TAG, "[DEBUG] ForwardingClientCall.sendMessage: Checking if MESSAGE level is enabled for logMessage (REQUEST).")
                logMessage(REQUEST, message) // logMessage itself checks levels.contains(Level.MESSAGE)
                Log.d(TAG, "[DEBUG] ForwardingClientCall.sendMessage: Calling super.sendMessage.")
                super.sendMessage(message)
                Log.d(TAG, "[DEBUG] ForwardingClientCall.sendMessage: super.sendMessage finished.")
            }

            override fun start(responseListener: Listener<RespT>, headers: Metadata) {
                Log.d(TAG, "[DEBUG] ForwardingClientCall.start called. Headers: $headers")
                Log.d(TAG, "[DEBUG] ForwardingClientCall.start: Checking if HEADERS level is enabled for logHeaders (REQUEST).")
                logHeaders(REQUEST, headers) // logHeaders itself checks levels.contains(Level.HEADERS)
                
                Log.d(TAG, "[DEBUG] ForwardingClientCall.start: Creating ForwardingClientCallListener.")
                // Create a forwarding listener to intercept responses
                val listener = object : ForwardingClientCallListener.SimpleForwardingClientCallListener<RespT>(
                    responseListener
                ) {
                    init {
                        Log.d(TAG, "[DEBUG] ForwardingClientCallListener instance created for method: ${method.fullMethodName}")
                    }
                    override fun onMessage(message: RespT) {
                        Log.d(TAG, "[DEBUG] Listener.onMessage called. Message: $message")
                        Log.d(TAG, "[DEBUG] Listener.onMessage: Checking if MESSAGE level is enabled for logMessage (RESPONSE).")
                        logMessage(RESPONSE, message) // logMessage itself checks levels.contains(Level.MESSAGE)
                        Log.d(TAG, "[DEBUG] Listener.onMessage: Calling super.onMessage.")
                        super.onMessage(message)
                        Log.d(TAG, "[DEBUG] Listener.onMessage: super.onMessage finished.")
                    }

                    override fun onHeaders(headers: Metadata) {
                        Log.d(TAG, "[DEBUG] Listener.onHeaders called. Headers: $headers")
                        Log.d(TAG, "[DEBUG] Listener.onHeaders: Checking if HEADERS level is enabled for logHeaders (RESPONSE).")
                        logHeaders(RESPONSE, headers) // logHeaders itself checks levels.contains(Level.HEADERS)
                        Log.d(TAG, "[DEBUG] Listener.onHeaders: Calling super.onHeaders.")
                        super.onHeaders(headers)
                        Log.d(TAG, "[DEBUG] Listener.onHeaders: super.onHeaders finished.")
                    }

                    override fun onClose(status: Status, trailers: Metadata) {
                        Log.d(TAG, "[DEBUG] Listener.onClose called. Status: $status, Trailers: $trailers")
                        Log.d(TAG, "[DEBUG] Listener.onClose: Checking if STATUS level is enabled for logStatus.")
                        logStatus(status, method) // logStatus itself checks levels.contains(Level.STATUS)
                        Log.d(TAG, "[DEBUG] Listener.onClose: Calling super.onClose.")
                        super.onClose(status, trailers)
                        Log.d(TAG, "[DEBUG] Listener.onClose: super.onClose finished.")
                    }

                    override fun onReady() {
                        Log.d(TAG, "[DEBUG] Listener.onReady called.")
                        super.onReady()
                        Log.d(TAG, "[DEBUG] Listener.onReady: super.onReady finished.")
                    }
                }
                Log.d(TAG, "[DEBUG] ForwardingClientCall.start: Calling super.start with new listener.")
                super.start(listener, headers)
                Log.d(TAG, "[DEBUG] ForwardingClientCall.start: super.start finished.")
            }
        }
    }

    /**
     * Log a message to logcat and application logger
     */
    private fun log(msg: String?) {
        // This is the interceptor's own logging method.
        // We are adding [DEBUG] logs around its callers, not modifying this directly.
        msg?.let {
            Log.d(TAG, it) // This is the original log call from the interceptor
        }
    }

    /**
     * Log method information if STATUS level is enabled
     */
    private fun <ReqT, RespT> logMethod(method: MethodDescriptor<ReqT, RespT>) {
        if (levels.contains(Level.STATUS)) {
            val msg = "$REQUEST path : ${method.fullMethodName}"
            Log.d(TAG, "[DEBUG] logMethod: STATUS level enabled. Logging: \"$msg\"")
            log(msg) // Calls the interceptor's log method
            Logger.v(TAG, msg, false, 4, null)
        } else {
            Log.d(TAG, "[DEBUG] logMethod: STATUS level disabled. Not logging method.")
        }
    }

    /**
     * Log headers information if HEADERS level is enabled
     */
    private fun logHeaders(prefix: String, headers: Metadata) {
        if (levels.contains(Level.HEADERS)) {
            Log.d(TAG, "[DEBUG] logHeaders: HEADERS level enabled for prefix '$prefix'. Logging: \"$prefix headers : $headers\"")
            log("$prefix headers : $headers") // Calls the interceptor's log method
        } else {
            Log.d(TAG, "[DEBUG] logHeaders: HEADERS level disabled for prefix '$prefix'. Not logging headers.")
        }
    }

    /**
     * Log message content if MESSAGE level is enabled
     */
    private fun <T> logMessage(prefix: String, message: T) {
        if (levels.contains(Level.MESSAGE)) {
            Log.d(TAG, "[DEBUG] logMessage: MESSAGE level enabled for prefix '$prefix'. Logging: \"$prefix message : $message\"")
            log("$prefix message : $message") // Calls the interceptor's log method
        } else {
            Log.d(TAG, "[DEBUG] logMessage: MESSAGE level disabled for prefix '$prefix'. Not logging message.")
        }
    }

    /**
     * Log status information if STATUS level is enabled
     */
    private fun <ReqT, RespT> logStatus(status: Status, method: MethodDescriptor<ReqT, RespT>) {
        if (levels.contains(Level.STATUS)) {
            val msg = "$RESPONSE status: ${status.code.value()} $status for path : ${method.fullMethodName}"
            Log.d(TAG, "[DEBUG] logStatus: STATUS level enabled. Logging: \"$msg\"")
            log(msg) // Calls the interceptor's log method
            Logger.v(TAG, msg, false, 4, null)
        } else {
            Log.d(TAG, "[DEBUG] logStatus: STATUS level disabled. Not logging status.")
        }
    }

    companion object {
        private const val TAG = "LoggingInterceptor" // Existing TAG
        private const val REQUEST = "Request"
        private const val RESPONSE = "Response"
    }

    /**
     * Simple forwarding client call listener
     */
    private abstract class ForwardingClientCallListener<T> : ClientCall.Listener<T>() {
        // Not adding logs directly into this abstract class unless specific debug needs arise for its own logic,
        // as the concrete anonymous class above is where the main interception logic happens.
        abstract fun delegate(): ClientCall.Listener<T>

        override fun onMessage(message: T) {
            delegate().onMessage(message)
        }

        override fun onHeaders(headers: Metadata) {
            delegate().onHeaders(headers)
        }

        override fun onClose(status: Status, trailers: Metadata) {
            delegate().onClose(status, trailers)
        }

        override fun onReady() {
            delegate().onReady()
        }

        /**
         * Simple implementation that takes a delegate listener
         */
        open class SimpleForwardingClientCallListener<T>(
            private val delegate: ClientCall.Listener<T>
        ) : ForwardingClientCallListener<T>() {
            // init {
            //     Log.d(TAG, "[DEBUG] SimpleForwardingClientCallListener instance created.")
            // }
            override fun delegate(): ClientCall.Listener<T> = delegate
        }
    }
}
