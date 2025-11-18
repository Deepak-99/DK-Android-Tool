package com.hawkshaw.library.handler

import com.hawkshaw.library.datalayer.Injector
import com.hawkshaw.library.datalayer.models.PushDataRequest
import com.hawkshaw.library.datalayer.network.twirp.ApiResponse
import com.hawkshaw.library.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import android.util.Log // Added for logging

private const val TAG = "PushData"

/**
 * Push data to the server
 * @return API response
 */
suspend fun pushData(): ApiResponse<Unit> = withContext(Dispatchers.IO) {
    Log.d(TAG, "[DEBUG] pushData called. Current thread: ${Thread.currentThread().name}")
    try {
        Logger.d(TAG, "Pushing data to server", false, 4, null)
        Log.d(TAG, "[DEBUG] pushData: Inside try block. About to create PushDataRequest.")

        // Create push data request
        val request = PushDataRequest(
            timestamp = System.currentTimeMillis()
        )
        Log.d(TAG, "[DEBUG] pushData: PushDataRequest created: $request")

        // Make API call using appService (corrected from dataService)
        // IMPORTANT: Ensure your AppService has a 'pushData' method accepting PushDataRequest
        Log.d(TAG, "[DEBUG] pushData: About to make API call: Injector.INSTANCE.appService.pushData(request)")
        val response = Injector.INSTANCE.appService.pushData(request)
        Log.d(TAG, "[DEBUG] pushData: API call returned. Response: $response, Response State: ${response.state}")

        Logger.d(TAG, "Push data response: ${response.state}", false, 4, null)
        Log.d(TAG, "[DEBUG] pushData: Returning response from try block.")
        response
    } catch (e: Exception) {
        Log.e(TAG, "[DEBUG] pushData: Exception caught. Message: ${e.message}", e) // Debug log for exception
        // Existing Logger.e call
        Logger.e(TAG, "Error pushing data", e, false, 12, null)
        Log.d(TAG, "[DEBUG] pushData: Returning ApiResponse.Error from catch block.")
        ApiResponse.Error("Error pushing data: ${e.message}")
    }
}

