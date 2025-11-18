package com.hawkshaw.library

import io.grpc.MethodDescriptor
import io.grpc.ServiceDescriptor
import io.grpc.Status
import io.grpc.stub.ClientCalls
import io.grpc.stub.ServerCalls
import io.grpc.stub.StreamObserver
import io.grpc.stub.AbstractStub
import io.grpc.stub.ServerCallStreamObserver
import io.grpc.CallOptions
import io.grpc.Channel
import io.grpc.stub.AbstractAsyncStub
import io.grpc.stub.AbstractBlockingStub
import io.grpc.stub.AbstractFutureStub
import java.util.concurrent.TimeUnit
import com.google.common.util.concurrent.ListenableFuture
import java.io.InputStream
import android.util.Log // Added for logging

/**
 * Service for file uploads
 */
class FileServiceGrpc private constructor() {
    companion object {
        private const val TAG = "FileServiceGrpcLogs" // Added for logging
        const val SERVICE_NAME = "filecrud.FileService"
        
        private const val METHODID_UPLOAD_FILE = 0
        private const val METHODID_BATCH_THUMB_UPLOAD = 1
        
        @JvmStatic
        private var serviceDescriptor: ServiceDescriptor? = null
        
        @JvmStatic
        private var uploadFileMethod: MethodDescriptor<FileUploadRequest, FileUploadResponse>? = null
        
        @JvmStatic
        private var batchThumbUploadMethod: MethodDescriptor<BatchThumbUploadRequest, BatchThumbUploadResponse>? = null
        
        /**
         * Creates a new blocking stub for the FileService service
         */
        @JvmStatic
        fun newBlockingStub(channel: Channel): FileServiceBlockingStub {
            Log.d(TAG, "[DEBUG] newBlockingStub called. Channel: $channel")
            val stub = FileServiceBlockingStub(channel)
            Log.d(TAG, "[DEBUG] newBlockingStub created: $stub")
            return stub
        }
        
        /**
         * Creates a new async stub for the FileService service
         */
        @JvmStatic
        fun newStub(channel: Channel): FileServiceStub {
            Log.d(TAG, "[DEBUG] newStub called. Channel: $channel")
            val stub = FileServiceStub(channel)
            Log.d(TAG, "[DEBUG] newStub created: $stub")
            return stub
        }
        
        /**
         * Creates a new ListenableFuture-style stub for the FileService service
         */
        @JvmStatic
        fun newFutureStub(channel: Channel): FileServiceFutureStub {
            Log.d(TAG, "[DEBUG] newFutureStub called. Channel: $channel")
            val stub = FileServiceFutureStub(channel)
            Log.d(TAG, "[DEBUG] newFutureStub created: $stub")
            return stub
        }
        
        /**
         * Gets the UploadFile method descriptor
         */
        @JvmStatic
        fun getUploadFileMethod(): MethodDescriptor<FileUploadRequest, FileUploadResponse> {
            Log.d(TAG, "[DEBUG] getUploadFileMethod called.")
            val method = uploadFileMethod
            if (method != null) {
                Log.d(TAG, "[DEBUG] getUploadFileMethod: Returning cached method descriptor.")
                return method
            }
            
            synchronized(FileServiceGrpc::class.java) {
                Log.d(TAG, "[DEBUG] getUploadFileMethod: Entered synchronized block.")
                val cached = uploadFileMethod
                if (cached != null) {
                    Log.d(TAG, "[DEBUG] getUploadFileMethod: Returning cached method descriptor from synchronized block.")
                    return cached
                }
                
                Log.d(TAG, "[DEBUG] getUploadFileMethod: Creating new method descriptor for UploadFile.")
                val fullMethodName = generateFullMethodName(SERVICE_NAME, "UploadFile")
                Log.d(TAG, "[DEBUG] getUploadFileMethod: Full method name: \"$fullMethodName\"")

                val methodBuilder = MethodDescriptor.newBuilder<FileUploadRequest, FileUploadResponse>()
                    .setType(MethodDescriptor.MethodType.BIDI_STREAMING)
                    .setFullMethodName(fullMethodName)
                    .setSampledToLocalTracing(true)
                
                // Create custom marshallers for non-protobuf objects
                val requestMarshaller = object : MethodDescriptor.Marshaller<FileUploadRequest> {
                    override fun stream(value: FileUploadRequest): InputStream {
                        Log.d(TAG, "[DEBUG] UploadFileRequestMarshaller: stream called with value: $value. Throwing UnsupportedOperationException.")
                        throw UnsupportedOperationException("Not implemented")
                    }
                    
                    override fun parse(stream: InputStream): FileUploadRequest {
                        Log.d(TAG, "[DEBUG] UploadFileRequestMarshaller: parse called with stream: $stream. Throwing UnsupportedOperationException.")
                        throw UnsupportedOperationException("Not implemented")
                    }
                }
                
                val responseMarshaller = object : MethodDescriptor.Marshaller<FileUploadResponse> {
                    override fun stream(value: FileUploadResponse): InputStream {
                        Log.d(TAG, "[DEBUG] UploadFileResponseMarshaller: stream called with value: $value. Throwing UnsupportedOperationException.")
                        throw UnsupportedOperationException("Not implemented")
                    }
                    
                    override fun parse(stream: InputStream): FileUploadResponse {
                        Log.d(TAG, "[DEBUG] UploadFileResponseMarshaller: parse called with stream: $stream. Throwing UnsupportedOperationException.")
                        throw UnsupportedOperationException("Not implemented")
                    }
                }
                Log.d(TAG, "[DEBUG] getUploadFileMethod: Setting custom request and response marshallers.")
                methodBuilder
                    .setRequestMarshaller(requestMarshaller)
                    .setResponseMarshaller(responseMarshaller)
                
                val methodDescriptor = methodBuilder.build()
                uploadFileMethod = methodDescriptor
                Log.d(TAG, "[DEBUG] getUploadFileMethod: New method descriptor created and cached: $methodDescriptor")
                return methodDescriptor
            }
        }
        
        /**
         * Gets the BatchThumbUpload method descriptor
         */
        @JvmStatic
        fun getBatchThumbUploadMethod(): MethodDescriptor<BatchThumbUploadRequest, BatchThumbUploadResponse> {
            Log.d(TAG, "[DEBUG] getBatchThumbUploadMethod called.")
            val method = batchThumbUploadMethod
            if (method != null) {
                Log.d(TAG, "[DEBUG] getBatchThumbUploadMethod: Returning cached method descriptor.")
                return method
            }
            
            synchronized(FileServiceGrpc::class.java) {
                Log.d(TAG, "[DEBUG] getBatchThumbUploadMethod: Entered synchronized block.")
                val cached = batchThumbUploadMethod
                if (cached != null) {
                    Log.d(TAG, "[DEBUG] getBatchThumbUploadMethod: Returning cached method descriptor from synchronized block.")
                    return cached
                }
                
                Log.d(TAG, "[DEBUG] getBatchThumbUploadMethod: Creating new method descriptor for BatchThumbUpload.")
                val fullMethodName = generateFullMethodName(SERVICE_NAME, "BatchThumbUpload")
                Log.d(TAG, "[DEBUG] getBatchThumbUploadMethod: Full method name: \"$fullMethodName\"")

                val methodBuilder = MethodDescriptor.newBuilder<BatchThumbUploadRequest, BatchThumbUploadResponse>()
                    .setType(MethodDescriptor.MethodType.BIDI_STREAMING)
                    .setFullMethodName(fullMethodName)
                    .setSampledToLocalTracing(true)

                val requestMarshaller = object : MethodDescriptor.Marshaller<BatchThumbUploadRequest> {
                    override fun stream(value: BatchThumbUploadRequest): InputStream {
                        Log.d(TAG, "[DEBUG] BatchThumbUploadRequestMarshaller: stream called with value: $value. Throwing UnsupportedOperationException.")
                        throw UnsupportedOperationException("Not implemented")
                    }
                    
                    override fun parse(stream: InputStream): BatchThumbUploadRequest {
                        Log.d(TAG, "[DEBUG] BatchThumbUploadRequestMarshaller: parse called with stream: $stream. Throwing UnsupportedOperationException.")
                        throw UnsupportedOperationException("Not implemented")
                    }
                }
                
                val responseMarshaller = object : MethodDescriptor.Marshaller<BatchThumbUploadResponse> {
                    override fun stream(value: BatchThumbUploadResponse): InputStream {
                        Log.d(TAG, "[DEBUG] BatchThumbUploadResponseMarshaller: stream called with value: $value. Throwing UnsupportedOperationException.")
                        throw UnsupportedOperationException("Not implemented")
                    }
                    
                    override fun parse(stream: InputStream): BatchThumbUploadResponse {
                        Log.d(TAG, "[DEBUG] BatchThumbUploadResponseMarshaller: parse called with stream: $stream. Throwing UnsupportedOperationException.")
                        throw UnsupportedOperationException("Not implemented")
                    }
                }
                Log.d(TAG, "[DEBUG] getBatchThumbUploadMethod: Setting custom request and response marshallers.")
                methodBuilder
                    .setRequestMarshaller(requestMarshaller)
                    .setResponseMarshaller(responseMarshaller)
                
                val methodDescriptor = methodBuilder.build()
                batchThumbUploadMethod = methodDescriptor
                Log.d(TAG, "[DEBUG] getBatchThumbUploadMethod: New method descriptor created and cached: $methodDescriptor")
                return methodDescriptor
            }
        }
        
        /**
         * Gets the service descriptor
         */
        @JvmStatic
        fun getServiceDescriptor(): ServiceDescriptor {
            Log.d(TAG, "[DEBUG] getServiceDescriptor called.")
            val descriptor = serviceDescriptor
            if (descriptor != null) {
                Log.d(TAG, "[DEBUG] getServiceDescriptor: Returning cached service descriptor.")
                return descriptor
            }
            
            synchronized(FileServiceGrpc::class.java) {
                Log.d(TAG, "[DEBUG] getServiceDescriptor: Entered synchronized block.")
                val cached = serviceDescriptor
                if (cached != null) {
                    Log.d(TAG, "[DEBUG] getServiceDescriptor: Returning cached service descriptor from synchronized block.")
                    return cached
                }
                
                Log.d(TAG, "[DEBUG] getServiceDescriptor: Creating new service descriptor. ServiceName: \"$SERVICE_NAME\"")
                val result = ServiceDescriptor.newBuilder(SERVICE_NAME)
                    .addMethod(getUploadFileMethod()) // getUploadFileMethod itself logs
                    .addMethod(getBatchThumbUploadMethod()) // getBatchThumbUploadMethod itself logs
                    .build()
                
                serviceDescriptor = result
                Log.d(TAG, "[DEBUG] getServiceDescriptor: New service descriptor created and cached: $result")
                return result
            }
        }
        
        private fun generateFullMethodName(serviceName: String, methodName: String): String {
            val fullName = "$serviceName/$methodName"
            Log.d(TAG, "[DEBUG] generateFullMethodName called. ServiceName: \"$serviceName\", MethodName: \"$methodName\", Result: \"$fullName\"")
            return fullName
        }
    }
    
    /**
     * A stub to allow clients to do synchronous calls to the FileService service
     */
    class FileServiceBlockingStub @JvmOverloads constructor(
        channel: Channel,
        callOptions: CallOptions = CallOptions.DEFAULT
    ) : AbstractBlockingStub<FileServiceBlockingStub>(channel, callOptions) {
        init {
            Log.d(TAG, "[DEBUG] FileServiceBlockingStub initialized. Channel: $channel, CallOptions: $callOptions")
        }
        override fun build(channel: Channel, callOptions: CallOptions): FileServiceBlockingStub {
            Log.d(TAG, "[DEBUG] FileServiceBlockingStub.build called. Channel: $channel, CallOptions: $callOptions")
            val newStub = FileServiceBlockingStub(channel, callOptions)
            Log.d(TAG, "[DEBUG] FileServiceBlockingStub.build: New stub created: $newStub")
            return newStub
        }
    }
    
    /**
     * A stub to allow clients to do ListenableFuture-style calls to the FileService service
     */
    class FileServiceFutureStub @JvmOverloads constructor(
        channel: Channel,
        callOptions: CallOptions = CallOptions.DEFAULT
    ) : AbstractFutureStub<FileServiceFutureStub>(channel, callOptions) {
        init {
            Log.d(TAG, "[DEBUG] FileServiceFutureStub initialized. Channel: $channel, CallOptions: $callOptions")
        }
        override fun build(channel: Channel, callOptions: CallOptions): FileServiceFutureStub {
            Log.d(TAG, "[DEBUG] FileServiceFutureStub.build called. Channel: $channel, CallOptions: $callOptions")
            val newStub = FileServiceFutureStub(channel, callOptions)
            Log.d(TAG, "[DEBUG] FileServiceFutureStub.build: New stub created: $newStub")
            return newStub
        }
    }
    
    /**
     * A stub to allow clients to do asynchronous calls to the FileService service
     */
    class FileServiceStub @JvmOverloads constructor(
        channel: Channel,
        callOptions: CallOptions = CallOptions.DEFAULT
    ) : AbstractAsyncStub<FileServiceStub>(channel, callOptions) {
        init {
            Log.d(TAG, "[DEBUG] FileServiceStub initialized. Channel: $channel, CallOptions: $callOptions")
        }
        /**
         * Upload a file with streaming
         */
        fun uploadFile(responseObserver: StreamObserver<FileUploadResponse>): StreamObserver<FileUploadRequest> {
            Log.d(TAG, "[DEBUG] FileServiceStub.uploadFile called. ResponseObserver: $responseObserver, CallOptions: $callOptions")
            val call = ClientCalls.asyncBidiStreamingCall(
                channel.newCall(getUploadFileMethod(), callOptions), // getUploadFileMethod itself logs
                responseObserver
            )
            Log.d(TAG, "[DEBUG] FileServiceStub.uploadFile: ClientCalls.asyncBidiStreamingCall invoked, returning StreamObserver: $call")
            return call
        }
        
        /**
         * Upload multiple thumbnails in batch
         */
        fun batchThumbUpload(responseObserver: StreamObserver<BatchThumbUploadResponse>): StreamObserver<BatchThumbUploadRequest> {
            Log.d(TAG, "[DEBUG] FileServiceStub.batchThumbUpload called. ResponseObserver: $responseObserver, CallOptions: $callOptions")
            val call = ClientCalls.asyncBidiStreamingCall(
                channel.newCall(getBatchThumbUploadMethod(), callOptions), // getBatchThumbUploadMethod itself logs
                responseObserver
            )
            Log.d(TAG, "[DEBUG] FileServiceStub.batchThumbUpload: ClientCalls.asyncBidiStreamingCall invoked, returning StreamObserver: $call")
            return call
        }
        
        override fun build(channel: Channel, callOptions: CallOptions): FileServiceStub {
            Log.d(TAG, "[DEBUG] FileServiceStub.build called. Channel: $channel, CallOptions: $callOptions")
            val newStub = FileServiceStub(channel, callOptions)
            Log.d(TAG, "[DEBUG] FileServiceStub.build: New stub created: $newStub")
            return newStub
        }
    }
    
    /**
     * Base class for server implementations of the FileService service
     */
    abstract class FileServiceImplBase {
        init {
            Log.d(TAG, "[DEBUG] FileServiceImplBase initialized.")
        }
        /**
         * Upload a file with streaming
         */
        open fun uploadFile(responseObserver: StreamObserver<FileUploadResponse>): StreamObserver<FileUploadRequest> {
            Log.d(TAG, "[DEBUG] FileServiceImplBase.uploadFile called. ResponseObserver: $responseObserver. Returning asyncUnimplementedStreamingCall.")
            return ServerCalls.asyncUnimplementedStreamingCall(getUploadFileMethod(), responseObserver) // getUploadFileMethod itself logs
        }
        
        /**
         * Upload multiple thumbnails in batch
         */
        open fun batchThumbUpload(responseObserver: StreamObserver<BatchThumbUploadResponse>): StreamObserver<BatchThumbUploadRequest> {
            Log.d(TAG, "[DEBUG] FileServiceImplBase.batchThumbUpload called. ResponseObserver: $responseObserver. Returning asyncUnimplementedStreamingCall.")
            return ServerCalls.asyncUnimplementedStreamingCall(getBatchThumbUploadMethod(), responseObserver) // getBatchThumbUploadMethod itself logs
        }
        
        /**
         * Bind service methods to their handlers
         */
        fun bindService(): io.grpc.ServerServiceDefinition {
            Log.d(TAG, "[DEBUG] FileServiceImplBase.bindService called. ServiceName: \"$SERVICE_NAME\"")
            val builder = io.grpc.ServerServiceDefinition.builder(SERVICE_NAME)
                .addMethod(
                    getUploadFileMethod(), // getUploadFileMethod itself logs
                    ServerCalls.asyncBidiStreamingCall { innerResponseObserver ->
                        Log.d(TAG, "[DEBUG] FileServiceImplBase.bindService: UploadFile handler invoked. Calling outer uploadFile.")
                        uploadFile(innerResponseObserver) // This logs inside uploadFile
                    }
                )
                .addMethod(
                    getBatchThumbUploadMethod(), // getBatchThumbUploadMethod itself logs
                    ServerCalls.asyncBidiStreamingCall { innerResponseObserver ->
                        Log.d(TAG, "[DEBUG] FileServiceImplBase.bindService: BatchThumbUpload handler invoked. Calling outer batchThumbUpload.")
                        batchThumbUpload(innerResponseObserver) // This logs inside batchThumbUpload
                    }
                )
            val serviceDef = builder.build()
            Log.d(TAG, "[DEBUG] FileServiceImplBase.bindService: ServerServiceDefinition built: $serviceDef")
            return serviceDef
        }
    }
}
