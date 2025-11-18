package com.hawkshaw.library.datalayer.network.grpc

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import android.util.Log // Added for logging

// Define a TAG for logging in this file
// private const val TAG = "GrpcCallUtilsLogs" // Removed to resolve conflict

/**
 * Creates a Flow that emits the states of a gRPC call execution.
 * The flow starts with [GrpcResponse.InProgress] and ends with either
 * [GrpcResponse.Success] or [GrpcResponse.Error].
 *
 * @param T The type of data returned by the gRPC call
 * @param call A suspend function that makes the actual gRPC call
 * @return A Flow of GrpcResponse representing the states of the call
 */
fun <T> grpcCallFlow(call: suspend () -> T): Flow<GrpcResponse<T>> {
    Log.d(TAG, "[DEBUG] grpcCallFlow: Flow initiated.")
    return flow {
        Log.d(TAG, "[DEBUG] grpcCallFlow: Emitting GrpcResponse.InProgress.")
        // Emit in-progress state
        emit(GrpcResponse.InProgress)

        try {
            Log.d(TAG, "[DEBUG] grpcCallFlow: Attempting to execute gRPC call within withContext(Dispatchers.IO).")
            // Execute the gRPC call
            val result = withContext(Dispatchers.IO) {
                Log.d(TAG, "[DEBUG] grpcCallFlow: Inside withContext(Dispatchers.IO) - invoking call().")
                val callResult = call()
                Log.d(TAG, "[DEBUG] grpcCallFlow: Inside withContext(Dispatchers.IO) - call() returned: $callResult")
                callResult
            }
            Log.d(TAG, "[DEBUG] grpcCallFlow: gRPC call executed successfully. Result: $result")

            // Emit success state with result
            Log.d(TAG, "[DEBUG] grpcCallFlow: Emitting GrpcResponse.Success with result: $result")
            emit(GrpcResponse.Success(result))
        } catch (e: Throwable) {
            Log.e(TAG, "[DEBUG] grpcCallFlow: Caught exception: ${e.message}. Emitting GrpcResponse.Error.", e)
            // Emit error state with exception wrapped in GrpcError
            emit(GrpcResponse.Error(GrpcError(e)))
        }
        Log.d(TAG, "[DEBUG] grpcCallFlow: Flow processing finished for this call instance.")
    }.flowOn(Dispatchers.Default).also {
        Log.d(TAG, "[DEBUG] grpcCallFlow: Flow will run on Dispatchers.Default.")
    }
}

/**
 * A suspend function that makes a gRPC call and returns the result directly,
 * without wrapping it in a Flow of states.
 *
 * @param T The type of data returned by the gRPC call
 * @param call A suspend function that makes the actual gRPC call
 * @return The result of the gRPC call
 * @throws GrpcError if the call fails
 */
suspend fun <T> grpcCall(call: suspend () -> T): T {
    Log.d(TAG, "[DEBUG] grpcCall: Suspend function initiated.")
    return try {
        Log.d(TAG, "[DEBUG] grpcCall: Attempting to execute gRPC call within withContext(Dispatchers.IO).")
        val result = withContext(Dispatchers.IO) {
            Log.d(TAG, "[DEBUG] grpcCall: Inside withContext(Dispatchers.IO) - invoking call().")
            val callResult = call()
            Log.d(TAG, "[DEBUG] grpcCall: Inside withContext(Dispatchers.IO) - call() returned: $callResult")
            callResult
        }
        Log.d(TAG, "[DEBUG] grpcCall: gRPC call executed successfully. Returning result: $result")
        result
    } catch (e: Throwable) {
        Log.e(TAG, "[DEBUG] grpcCall: Caught exception: ${e.message}. Throwing GrpcError.", e)
        throw GrpcError(e)
    }
}
