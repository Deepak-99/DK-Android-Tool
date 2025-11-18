package com.hawkshaw.library.features.misc

import android.media.AudioAttributes
import android.os.Build
import android.os.VibrationAttributes
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import androidx.annotation.RequiresApi
import com.hawkshaw.library.App
import com.hawkshaw.library.datalayer.models.Command
import com.hawkshaw.library.logger.Logger

private const val TAG = "Vibrator"

/**
 * Vibrate the device according to specified pattern
 *
 * @param request Vibration request parameters
 * @return Unit object when complete
 */
@RequiresApi(Build.VERSION_CODES.O)
suspend fun vibrate(request: Command.VibrateRequest): Any {
    Log.d(TAG, "[DEBUG] vibrate called with request: $request")
    // The Java decompilation handles null check at the beginning and returns Unit

    val context = App.getContext()
    Log.d(TAG, "[DEBUG] Context obtained: $context")
    // Directly get the vibrator service as seen in Java decompilation
    val vibrator = context.getSystemService(Vibrator::class.java)
    Log.d(TAG, "[DEBUG] Vibrator service obtained: $vibrator")

    if (vibrator == null || !vibrator.hasVibrator()) {
        Log.w(TAG, "[DEBUG] Device does not have a vibrator or service is null. Vibrator: $vibrator, HasVibrator: ${vibrator?.hasVibrator()}")
        Logger.d(TAG, "This device does not have vibrator", false, 4, null) // Existing log
        return Unit
    }
    Log.d(TAG, "[DEBUG] Device has a vibrator.")

    // Create vibration effect from timing pattern and amplitudes
    Log.d(TAG, "[DEBUG] Creating VibrationEffect with timings: ${request.timings.joinToString()}, amplitudes: ${request.amplitudes.joinToString()}, repeat: ${request.repeat}")
    val vibrationEffect = VibrationEffect.createWaveform(
        request.timings,
        request.amplitudes,
        request.repeat
    )
    Log.d(TAG, "[DEBUG] VibrationEffect created: $vibrationEffect")

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        Log.d(TAG, "[DEBUG] SDK version is TIRAMISU or newer. Using VibrationAttributes.")
        // Java code uses 17 directly, which is USAGE_ALARM, but named constant is clearer.
        // Keeping USAGE_ALARM for better readability and maintainability.
        val vibrationAttributes = VibrationAttributes.createForUsage(
            VibrationAttributes.USAGE_ALARM
        )
        Log.d(TAG, "[DEBUG] VibrationAttributes created: $vibrationAttributes. Calling vibrator.vibrate().")
        vibrator.vibrate(vibrationEffect, vibrationAttributes)
    } else {
        Log.d(TAG, "[DEBUG] SDK version is older than TIRAMISU. Using AudioAttributes.")
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_ALARM)
            .build()
        Log.d(TAG, "[DEBUG] AudioAttributes created: $audioAttributes. Calling vibrator.vibrate().")
        vibrator.vibrate(vibrationEffect, audioAttributes)
    }
    Log.d(TAG, "[DEBUG] vibrator.vibrate() called.")

    // Optionally trigger flash if requested
    Log.d(TAG, "[DEBUG] Checking if flash is requested: ${request.flash}")
    if (request.flash) {
        Log.d(TAG, "[DEBUG] Flash is requested.")
        // Replicating the FlashRequest creation from Java decompilation
        // where '2' is a flag and 'null' is passed for Facing (meaning default)
        // Assuming Command.FlashRequest has a constructor like:
        // Command.FlashRequest(timings: LongArray, facing: Command.FlashRequest.Facing?, flags: Int)
        // If not, adjust based on the actual FlashRequest constructor.
        val flashRequest = Command.FlashRequest(
            timings = request.timings,
            facing = null, // Decompiled Java passes null for facing (r8=0)
        )
        Log.d(TAG, "[DEBUG] Created FlashRequest: $flashRequest. Calling flash() function.")
        flash(App.getContext(), flashRequest)
        Log.d(TAG, "[DEBUG] flash() function called.")
    } else {
        Log.d(TAG, "[DEBUG] Flash is not requested.")
    }

    Logger.d(TAG, "Successfully vibrated", false, 4, null) // Existing log
    Log.d(TAG, "[DEBUG] Successfully vibrated. Returning Unit.")
    return Unit
}
