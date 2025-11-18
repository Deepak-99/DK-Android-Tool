package com.hawkshaw.library.datalayer.network.service

import kotlinx.coroutines.flow.Flow
import com.hawkshaw.library.datalayer.network.grpc.GrpcResponse
import java.io.File

/**
 * Service for handling screen recording and projection operations
 */
interface ScreenService {
    
    /**
     * Upload screen recording to server
     */
    suspend fun uploadScreenRecording(
        file: File,
        deviceId: String,
        recordingId: String,
        duration: Int,
        resolution: String,
        quality: String,
        frameRate: Int,
        startTime: Long,
        endTime: Long,
        metadata: Map<String, Any>?
    ): Flow<GrpcResponse<String>>
    
    /**
     * Send screen frame data for projection
     */
    suspend fun sendScreenFrame(
        sessionId: String,
        frameData: String,
        frameNumber: Int,
        timestamp: Long
    ): Flow<GrpcResponse<String>>
    
    /**
     * Start screen projection session
     */
    suspend fun startScreenProjection(
        deviceId: String,
        projectionType: String,
        quality: String,
        frameRate: Int,
        resolution: String?,
        maxViewers: Int
    ): Flow<GrpcResponse<String>>
    
    /**
     * Stop screen projection session
     */
    suspend fun stopScreenProjection(sessionId: String): Flow<GrpcResponse<String>>
}
