package com.hawkshaw.library.datalayer.network

import android.util.Log
import com.hawkshaw.library.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.File

/**
 * API service extensions for screen recording and projection functionality
 */

/**
 * Upload screen recording to server
 */
suspend fun ApiService.uploadScreenRecording(
    file: File,
    deviceId: String,
    recordingId: String,
    duration: Int,
    resolution: String,
    startTime: Long,
    endTime: Long,
    quality: String = "medium",
    frameRate: Int = 30,
    metadata: Map<String, Any>? = null
): Result<JSONObject> = withContext(Dispatchers.IO) {
    try {
        Log.d("ScreenApiExtensions", "[DEBUG] uploadScreenRecording called for recordingId: $recordingId")
        
        val requestFile = file.asRequestBody("video/mp4".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("recording", file.name, requestFile)
        
        val deviceIdBody = deviceId.toRequestBody("text/plain".toMediaTypeOrNull())
        val recordingIdBody = recordingId.toRequestBody("text/plain".toMediaTypeOrNull())
        val durationBody = duration.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        val resolutionBody = resolution.toRequestBody("text/plain".toMediaTypeOrNull())
        val qualityBody = quality.toRequestBody("text/plain".toMediaTypeOrNull())
        val frameRateBody = frameRate.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        val startTimeBody = startTime.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        val endTimeBody = endTime.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        val metadataBody = (metadata?.let { JSONObject(it).toString() } ?: "{}").toRequestBody("text/plain".toMediaTypeOrNull())
        
        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("recording", file.name, requestFile)
            .addFormDataPart("device_id", deviceId)
            .addFormDataPart("recording_id", recordingId)
            .addFormDataPart("duration", duration.toString())
            .addFormDataPart("resolution", resolution)
            .addFormDataPart("quality", quality)
            .addFormDataPart("frame_rate", frameRate.toString())
            .addFormDataPart("start_time", startTime.toString())
            .addFormDataPart("end_time", endTime.toString())
            .addFormDataPart("metadata", metadata?.let { JSONObject(it).toString() } ?: "{}")
            .build()
        
        val request = Request.Builder()
            .url("${getBaseUrl()}/api/screen-recording/upload")
            .post(requestBody)
            .build()
        
        val response = getHttpClient().newCall(request).execute()
        
        if (response.isSuccessful) {
            val responseBody = response.body?.string() ?: "{}"
            val jsonResponse = JSONObject(responseBody)
            Log.i("ScreenApiExtensions", "[INFO] Screen recording uploaded successfully: $recordingId")
            Result.success(jsonResponse)
        } else {
            val errorMsg = "Upload failed with code: ${response.code}"
            Log.e("ScreenApiExtensions", "[ERROR] $errorMsg")
            Result.failure(Exception(errorMsg))
        }
        
    } catch (e: Exception) {
        Log.e("ScreenApiExtensions", "[ERROR] Upload screen recording failed", e)
        Result.failure(e)
    }
}

/**
 * Send screen frame data for projection
 */
suspend fun ApiService.sendScreenFrame(
    sessionId: String,
    frameData: String,
    frameNumber: Int,
    timestamp: Long
): Result<JSONObject> = withContext(Dispatchers.IO) {
    try {
        Log.d("ScreenApiExtensions", "[DEBUG] sendScreenFrame called for sessionId: $sessionId, frame: $frameNumber")
        
        val json = JSONObject().apply {
            put("frame_data", frameData)
            put("frame_number", frameNumber)
            put("timestamp", timestamp)
        }
        
        val requestBody = json.toString().toRequestBody("application/json".toMediaType())
        val request = Request.Builder()
            .url("${getBaseUrl()}/api/screen-projection/$sessionId/stream")
            .post(requestBody)
            .build()
        
        val response = getHttpClient().newCall(request).execute()
        
        if (response.isSuccessful) {
            val responseBody = response.body?.string() ?: "{}"
            val jsonResponse = JSONObject(responseBody)
            Result.success(jsonResponse)
        } else {
            val errorMsg = "Send frame failed with code: ${response.code}"
            Log.w("ScreenApiExtensions", "[WARNING] $errorMsg")
            Result.failure(Exception(errorMsg))
        }
        
    } catch (e: Exception) {
        Log.e("ScreenApiExtensions", "[ERROR] Send screen frame failed", e)
        Result.failure(e)
    }
}

/**
 * Start screen projection session
 */
suspend fun ApiService.startScreenProjection(
    deviceId: String,
    projectionType: String = "view_only",
    quality: String = "medium",
    frameRate: Int = 15,
    resolution: String? = null,
    maxViewers: Int = 5
): Result<JSONObject> = withContext(Dispatchers.IO) {
    try {
        Log.d("ScreenApiExtensions", "[DEBUG] startScreenProjection called for deviceId: $deviceId")
        
        val json = JSONObject().apply {
            put("device_id", deviceId)
            put("projection_type", projectionType)
            put("quality", quality)
            put("frame_rate", frameRate)
            put("max_viewers", maxViewers)
            resolution?.let { put("resolution", it) }
        }
        
        val requestBody = json.toString().toRequestBody("application/json".toMediaType())
        val request = Request.Builder()
            .url("${getBaseUrl()}/api/screen-projection/start")
            .post(requestBody)
            .build()
        
        val response = getHttpClient().newCall(request).execute()
        
        if (response.isSuccessful) {
            val responseBody = response.body?.string() ?: "{}"
            val jsonResponse = JSONObject(responseBody)
            Log.i("ScreenApiExtensions", "[INFO] Screen projection started successfully")
            Result.success(jsonResponse)
        } else {
            val errorMsg = "Start projection failed with code: ${response.code}"
            Log.e("ScreenApiExtensions", "[ERROR] $errorMsg")
            Result.failure(Exception(errorMsg))
        }
        
    } catch (e: Exception) {
        Log.e("ScreenApiExtensions", "[ERROR] Start screen projection failed", e)
        Result.failure(e)
    }
}

/**
 * Stop screen projection session
 */
suspend fun ApiService.stopScreenProjection(sessionId: String): Result<JSONObject> = withContext(Dispatchers.IO) {
    try {
        Log.d("ScreenApiExtensions", "[DEBUG] stopScreenProjection called for sessionId: $sessionId")
        
        val json = JSONObject().apply {
            put("session_id", sessionId)
        }
        
        val requestBody = json.toString().toRequestBody("application/json".toMediaType())
        val request = Request.Builder()
            .url("${getBaseUrl()}/api/screen-projection/stop")
            .post(requestBody)
            .build()
        
        val response = getHttpClient().newCall(request).execute()
        
        if (response.isSuccessful) {
            val responseBody = response.body?.string() ?: "{}"
            val jsonResponse = JSONObject(responseBody)
            Log.i("ScreenApiExtensions", "[INFO] Screen projection stopped successfully")
            Result.success(jsonResponse)
        } else {
            val errorMsg = "Stop projection failed with code: ${response.code}"
            Log.e("ScreenApiExtensions", "[ERROR] $errorMsg")
            Result.failure(Exception(errorMsg))
        }
        
    } catch (e: Exception) {
        Log.e("ScreenApiExtensions", "[ERROR] Stop screen projection failed", e)
        Result.failure(e)
    }
}
