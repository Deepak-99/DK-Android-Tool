package com.hawkshaw.library.datalayer.network

import com.hawkshaw.library.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import org.json.JSONObject

data class AppUpdateResponse(
    val updateAvailable: Boolean,
    val versionName: String = "",
    val versionCode: Int = 0,
    val fileSize: Long = 0,
    val checksum: String = "",
    val releaseNotes: String = "",
    val isMandatory: Boolean = false,
    val downloadUrl: String = "",
    val message: String = ""
)

suspend fun ApiService.checkAppUpdate(deviceId: String, currentVersionCode: Int): AppUpdateResponse {
    return withContext(Dispatchers.IO) {
        try {
            val json = JSONObject().apply {
                put("device_id", deviceId)
                put("current_version_code", currentVersionCode)
            }
            
            val requestBody = json.toString().toRequestBody("application/json".toMediaType())
            val request = Request.Builder()
                .url("${getBaseUrl()}/api/app-update/check")
                .post(requestBody)
                .build()
            
            val response = getHttpClient().newCall(request).execute()
            val responseBody = response.body?.string()
            
            if (response.isSuccessful && responseBody != null) {
                val jsonResponse = JSONObject(responseBody)
                AppUpdateResponse(
                    updateAvailable = jsonResponse.optBoolean("update_available", false),
                    versionName = jsonResponse.optString("version_name", ""),
                    versionCode = jsonResponse.optInt("version_code", 0),
                    fileSize = jsonResponse.optLong("file_size", 0),
                    checksum = jsonResponse.optString("checksum", ""),
                    releaseNotes = jsonResponse.optString("release_notes", ""),
                    isMandatory = jsonResponse.optBoolean("is_mandatory", false),
                    downloadUrl = jsonResponse.optString("download_url", ""),
                    message = jsonResponse.optString("message", "")
                )
            } else {
                AppUpdateResponse(updateAvailable = false, message = "HTTP ${response.code}: ${response.message}")
            }
        } catch (e: Exception) {
            AppUpdateResponse(updateAvailable = false, message = "Error: ${e.message}")
        }
    }
}

suspend fun ApiService.downloadAppUpdate(downloadUrl: String): ResponseBody {
    return withContext(Dispatchers.IO) {
        try {
            val request = Request.Builder()
                .url("${getBaseUrl()}$downloadUrl")
                .get()
                .build()
            
            val response = getHttpClient().newCall(request).execute()
            if (response.isSuccessful && response.body != null) {
                response.body!!
            } else {
                throw Exception("Download failed: HTTP ${response.code}")
            }
        } catch (e: Exception) {
            throw Exception("Download error: ${e.message}")
        }
    }
}

suspend fun ApiService.reportAppInstall(
    deviceId: String, 
    versionCode: Int, 
    success: Boolean, 
    errorMessage: String?
): Boolean {
    return withContext(Dispatchers.IO) {
        try {
            val json = JSONObject().apply {
                put("device_id", deviceId)
                put("version_code", versionCode)
                put("success", success)
                errorMessage?.let { put("error_message", it) }
            }
            
            val requestBody = json.toString().toRequestBody("application/json".toMediaType())
            val request = Request.Builder()
                .url("${getBaseUrl()}/api/app-update/install-report")
                .post(requestBody)
                .build()
            
            val response = getHttpClient().newCall(request).execute()
            response.isSuccessful
        } catch (e: Exception) {
            false
        }
    }
}
