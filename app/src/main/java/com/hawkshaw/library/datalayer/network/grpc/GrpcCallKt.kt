package com.hawkshaw.library.datalayer.network.grpc

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import android.util.Log // Added for logging

// Define a TAG for logging in this file
public const val TAG = "GrpcCallKtLogs" // Changed from internal to public

/**
 * Helper function to create a nested gRPC call flow
 *
 * @param emitInProgress Whether to emit InProgress state
 * @param call The suspend function to call
 * @return A flow of GrpcResponse
 */
inline fun <T> grpcCallNestedFlow(
    emitInProgress: Boolean = true,
    crossinline call: suspend () -> Flow<T>
): Flow<GrpcResponse<T>> = flow {
    Log.d(TAG, "[DEBUG] grpcCallNestedFlow invoked. emitInProgress: $emitInProgress")
    if (emitInProgress) {
        Log.d(TAG, "[DEBUG] grpcCallNestedFlow: Emitting GrpcResponse.InProgress")
        emit(GrpcResponse.InProgress)
    }
    try {
        Log.d(TAG, "[DEBUG] grpcCallNestedFlow: Calling and collecting from the 'call' suspend function.")
        call().collect { result ->
            Log.d(TAG, "[DEBUG] grpcCallNestedFlow: Collected result from 'call': $result. Emitting GrpcResponse.Success.")
            emit(GrpcResponse.Success(result))
        }
        Log.d(TAG, "[DEBUG] grpcCallNestedFlow: 'call' flow collection completed.")
    } catch (e: Throwable) {
        Log.e(TAG, "[DEBUG] grpcCallNestedFlow: Caught exception: ${e.message}. Emitting GrpcResponse.Error.", e)
        emit(GrpcResponse.Error(GrpcError(e)))
    }
    Log.d(TAG, "[DEBUG] grpcCallNestedFlow: Flow processing finished.")
}

/**
 * Create a default gRPC state flow
 */
fun <T> grpcStateFlowDefault(): Flow<GrpcResponse<T>> = flow {
    Log.d(TAG, "[DEBUG] grpcStateFlowDefault invoked. Emitting GrpcResponse.Default.")
    emit(GrpcResponse.Default)
    Log.d(TAG, "[DEBUG] grpcStateFlowDefault: Flow processing finished.")
}

/**
 * Create an in-progress gRPC state flow
 */
fun <T> grpcStateFlowInProgress(): Flow<GrpcResponse<T>> = flow {
    Log.d(TAG, "[DEBUG] grpcStateFlowInProgress invoked. Emitting GrpcResponse.InProgress.")
    emit(GrpcResponse.InProgress)
    Log.d(TAG, "[DEBUG] grpcStateFlowInProgress: Flow processing finished.")
}
