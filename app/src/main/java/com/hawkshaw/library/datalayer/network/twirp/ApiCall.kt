package com.hawkshaw.library.datalayer.network.twirp

import com.hawkshaw.library.App // Added import
import com.hawkshaw.library.config.RemoteConfig
import com.hawkshaw.library.logger.Logger
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException

/**
 * Logger tag for API calls
 */
const val API_CALL_TAG = "ApiCall"

/**
 * Makes an API call to the specified endpoint
 *
 * @param endpoint The endpoint to call
 * @param req The request body
 * @param retry Whether to retry the request on failure (handled by RetryInterceptor)
 * @return ApiResponse containing either the result or an error
 */
suspend inline fun <reified S, reified T> apiCall(
    endpoint: String,
    req: S,
    retry: Boolean = true
): ApiResponse<T> {
    return try {
        val client = ClientKt.getClient()
        // Pass App.getContext() to RemoteConfig.getInstance()
        val baseUrl = RemoteConfig.getInstance(App.getContext()).getBaseUrl()
        val url = "$baseUrl/twirp/$endpoint"
        
        // Create request body
        val requestBody = when (req) {
            is MultipartBody -> req
            else -> {
                val jsonString = Json.encodeToString(req)
                jsonString.toRequestBody("application/json".toMediaType())
            }
        }
        
        // Build request
        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()
        
        // Execute request
        val response: Response = client.newCall(request).execute()
        response.use { resp: Response ->  // Explicitly specify Response type
            if (!resp.isSuccessful) {
                val errorBody = resp.body?.string()
                try {
                    if (errorBody != null) {
                        // Parse error response
                        val errorResponse: ApiResponse.Error<T> = Json.decodeFromString(errorBody)
                        errorResponse
                    } else {
                        ApiResponse.Error("Request failed with status: ${resp.code}")
                    }
                } catch (e: Exception) {
                    Logger.e(
                        API_CALL_TAG,
                        "Failed to parse error response: $errorBody",
                        b = false,
                        i = 12,
                        nothing = null
                    )
                    ApiResponse.Error("Request failed with status: ${resp.code}")
                }
            } else {
                try {
                    val responseBody = resp.body?.string()
                    if (responseBody != null) {
                        // Parse success response
                        val result: T = Json.decodeFromString(responseBody)
                        ApiResponse.Success(result)
                    } else {
                        ApiResponse.Error("Empty response body")
                    }
                } catch (e: Exception) {
                    Logger.e(
                        API_CALL_TAG,
                        "Failed to parse response: ${e.message}",
                        e,
                        false,
                        12,
                        null
                    )
                    ApiResponse.Error("Failed to parse response: ${e.message}")
                }
            }
        }
    } catch (e: IOException) {
        Logger.e(
            API_CALL_TAG,
            "Network error: ${e.message}, Endpoint: $endpoint",
            e,
            false,
            12,
            null
        )
        ApiResponse.Error("Network error: ${e.message}")
    } catch (e: Exception) {
        Logger.e(
            API_CALL_TAG,
            "ApiCallError: ${e.message}, Endpoint: $endpoint",
            e,
            false,
            12,
            null
        )
        ApiResponse.Error(e.message ?: "Unknown error")
    }
} 