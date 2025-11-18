package com.hawkshaw.library

import io.grpc.CallOptions
import io.grpc.Channel
import io.grpc.MethodDescriptor
import io.grpc.ServerServiceDefinition
import io.grpc.ServiceDescriptor
import io.grpc.Status
import io.grpc.StatusException
import io.grpc.kotlin.AbstractCoroutineServerImpl
import io.grpc.kotlin.AbstractCoroutineStub
import io.grpc.kotlin.ClientCalls
import io.grpc.kotlin.ServerCalls
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import android.util.Log // Added for logging

/**
 * Kotlin gRPC stub implementations for FileService
 */
object FileServiceGrpcKt {
    private const val TAG = "FileServiceGrpcKtLogs" // Added for logging
    const val SERVICE_NAME = "filecrud.FileService" // Referenced by FileServiceGrpc.java

    init {
        Log.d(TAG, "[DEBUG] FileServiceGrpcKt object initialized. SERVICE_NAME: \"$SERVICE_NAME\"")
    }

    /**
     * Coroutine implementation of the FileService
     */
    abstract class FileServiceCoroutineImplBase(
        coroutineContext: CoroutineContext = EmptyCoroutineContext
    ) : AbstractCoroutineServerImpl(coroutineContext) {
        
        init {
            Log.d(TAG, "[DEBUG] FileServiceCoroutineImplBase initialized. CoroutineContext: $coroutineContext, EffectiveContext: $this.coroutineContext")
        }

        /**
         * Upload a file with streaming
         */
        open suspend fun uploadFile(requests: Flow<FileUploadRequest>): Flow<FileUploadResponse> {
            Log.d(TAG, "[DEBUG] FileServiceCoroutineImplBase.uploadFile called (default UNIMPLEMENTED). Requests: $requests")
            throw StatusException(Status.UNIMPLEMENTED.withDescription("Method filecrud.FileService.UploadFile is unimplemented"))
        }
        
        /**
         * Upload multiple thumbnails in batch
         */
        open suspend fun batchThumbUpload(requests: Flow<BatchThumbUploadRequest>): Flow<BatchThumbUploadResponse> {
            Log.d(TAG, "[DEBUG] FileServiceCoroutineImplBase.batchThumbUpload called (default UNIMPLEMENTED). Requests: $requests")
            throw StatusException(Status.UNIMPLEMENTED.withDescription("Method filecrud.FileService.BatchThumbUpload is unimplemented"))
        }
        
        /**
         * Bind service methods to their handlers
         */
        final override fun bindService(): ServerServiceDefinition {
            Log.d(TAG, "[DEBUG] FileServiceCoroutineImplBase.bindService called.")
            // Use the FileServiceGrpc implementation instead 
            // which already has the correct types set up
            Log.d(TAG, "[DEBUG] FileServiceCoroutineImplBase.bindService: Creating anonymous FileServiceGrpc.FileServiceImplBase for delegation.")
            val serviceImpl = object : FileServiceGrpc.FileServiceImplBase() {
                init {
                    Log.d(TAG, "[DEBUG] Anonymous FileServiceGrpc.FileServiceImplBase (in FileServiceCoroutineImplBase.bindService) initialized.")
                }
                override fun uploadFile(responseObserver: io.grpc.stub.StreamObserver<FileUploadResponse>): io.grpc.stub.StreamObserver<FileUploadRequest> {
                    Log.d(TAG, "[DEBUG] Anonymous FileServiceGrpc.FileServiceImplBase.uploadFile called. Returning asyncUnimplementedStreamingCall.")
                    return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(
                        FileServiceGrpc.getUploadFileMethod(), 
                        responseObserver
                    )
                }
                
                override fun batchThumbUpload(responseObserver: io.grpc.stub.StreamObserver<BatchThumbUploadResponse>): io.grpc.stub.StreamObserver<BatchThumbUploadRequest> {
                    Log.d(TAG, "[DEBUG] Anonymous FileServiceGrpc.FileServiceImplBase.batchThumbUpload called. Returning asyncUnimplementedStreamingCall.")
                    return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(
                        FileServiceGrpc.getBatchThumbUploadMethod(), 
                        responseObserver
                    )
                }
            }
            Log.d(TAG, "[DEBUG] FileServiceCoroutineImplBase.bindService: Delegating to serviceImpl.bindService().")
            val serverDef = serviceImpl.bindService()
            Log.d(TAG, "[DEBUG] FileServiceCoroutineImplBase.bindService: Delegated bindService returned: $serverDef")
            return serverDef
        }
    }
    
    /**
     * Kotlin-specific client stub for FileService
     */
    class FileServiceCoroutineStub @JvmOverloads constructor(
        channel: Channel,
        callOptions: CallOptions = CallOptions.DEFAULT
    ) : AbstractCoroutineStub<FileServiceCoroutineStub>(channel, callOptions) {
        
        init {
            Log.d(TAG, "[DEBUG] FileServiceCoroutineStub initialized. Channel: $channel, CallOptions: $callOptions")
        }

        /**
         * Upload a file with streaming
         */
        fun uploadFile(requests: Flow<FileUploadRequest>): Flow<FileUploadResponse> {
            Log.d(TAG, "[DEBUG] FileServiceCoroutineStub.uploadFile called. Requests: $requests, CallOptions: $this.callOptions")
            val responseFlow = ClientCalls.bidiStreamingRpc(
                channel = channel,
                method = FileServiceGrpc.getUploadFileMethod(), // FileServiceGrpc logs this
                requests = requests,
                callOptions = callOptions
            )
            Log.d(TAG, "[DEBUG] FileServiceCoroutineStub.uploadFile: ClientCalls.bidiStreamingRpc invoked. Returning Flow: $responseFlow")
            return responseFlow
        }
        
        /**
         * Upload multiple thumbnails in batch
         */
        fun batchThumbUpload(requests: Flow<BatchThumbUploadRequest>): Flow<BatchThumbUploadResponse> {
            Log.d(TAG, "[DEBUG] FileServiceCoroutineStub.batchThumbUpload called. Requests: $requests, CallOptions: $this.callOptions")
            val responseFlow = ClientCalls.bidiStreamingRpc( // Reverted to bidiStreamingRpc
                channel = channel,
                method = FileServiceGrpc.getBatchThumbUploadMethod(), // FileServiceGrpc logs this
                requests = requests,
                callOptions = callOptions
            )
            Log.d(TAG, "[DEBUG] FileServiceCoroutineStub.batchThumbUpload: ClientCalls.bidiStreamingRpc invoked. Returning Flow: $responseFlow")
            return responseFlow
        }
        
        override fun build(channel: Channel, callOptions: CallOptions): FileServiceCoroutineStub {
            Log.d(TAG, "[DEBUG] FileServiceCoroutineStub.build called. Channel: $channel, CallOptions: $callOptions")
            val newStub = FileServiceCoroutineStub(channel, callOptions)
            Log.d(TAG, "[DEBUG] FileServiceCoroutineStub.build: New stub created: $newStub")
            return newStub
        }
    }
} 
