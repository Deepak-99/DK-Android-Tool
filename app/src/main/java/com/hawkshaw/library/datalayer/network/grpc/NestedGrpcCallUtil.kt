package com.hawkshaw.library.datalayer.network.grpc

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

/**
 * Creates a Flow that handles a nested gRPC call execution.
 * This is useful when a gRPC call depends on the result of another gRPC call.
 *
 * The flow emits the following states:
 * 1. InProgress - When the outer or inner call is in progress
 * 2. Success - When both calls complete successfully
 * 3. Error - If either the outer or inner call fails
 *
 * @param T The type of data returned by the first gRPC call
 * @param R The type of data returned by the second (nested) gRPC call
 * @param outerCall A suspend function that makes the first gRPC call
 * @param innerCall A suspend function that takes the result of the first call and makes the second call
 * @return A Flow of GrpcResponse representing the states of the nested call
 */
fun <T, R> nestedGrpcCallFlow(
    outerCall: suspend () -> T,
    innerCall: suspend (T) -> R
): Flow<GrpcResponse<R>> = flow {
    // Emit in-progress state
    emit(GrpcResponse.InProgress)
    
    try {
        // Execute the outer gRPC call
        val outerResult = withContext(Dispatchers.IO) {
            outerCall()
        }
        
        try {
            // Execute the inner gRPC call using the result of the outer call
            val innerResult = withContext(Dispatchers.IO) {
                innerCall(outerResult)
            }
            
            // Emit success state with the inner result
            emit(GrpcResponse.Success(innerResult))
        } catch (e: Throwable) {
            // Emit error state if the inner call fails
            emit(GrpcResponse.Error(GrpcError(e)))
        }
    } catch (e: Throwable) {
        // Emit error state if the outer call fails
        emit(GrpcResponse.Error(GrpcError(e)))
    }
}.flowOn(Dispatchers.Default)

/**
 * A suspend function that executes a nested gRPC call and returns the result directly,
 * without wrapping it in a Flow of states.
 *
 * @param T The type of data returned by the first gRPC call
 * @param R The type of data returned by the second (nested) gRPC call
 * @param outerCall A suspend function that makes the first gRPC call
 * @param innerCall A suspend function that takes the result of the first call and makes the second call
 * @return The result of the nested gRPC call
 * @throws GrpcError if either call fails
 */
suspend fun <T, R> nestedGrpcCall(
    outerCall: suspend () -> T,
    innerCall: suspend (T) -> R
): R {
    return try {
        // Execute the outer gRPC call
        val outerResult = withContext(Dispatchers.IO) {
            outerCall()
        }
        
        // Execute the inner gRPC call using the result of the outer call
        withContext(Dispatchers.IO) {
            innerCall(outerResult)
        }
    } catch (e: Throwable) {
        throw GrpcError(e)
    }
} 