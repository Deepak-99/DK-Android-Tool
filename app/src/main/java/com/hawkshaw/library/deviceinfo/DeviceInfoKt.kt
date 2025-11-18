package com.hawkshaw.library.deviceinfo

import android.content.Context
import com.hawkshaw.library.App
import com.hawkshaw.library.datalayer.models.DeviceInfoNonSensitive
import com.hawkshaw.library.datalayer.models.DeviceInfoSensitive
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import android.util.Log // Added for logging

private const val TAG = "DeviceInfoKt"

/**
 * Provides device information utilities as top-level functions
 * This class is a modern Kotlin implementation that serves as a wrapper over DeviceInfo
 */

/**
 * Get non-sensitive device information that doesn't require special permissions
 * This includes:
 * - Device model
 * - OS version
 * - App version
 * - Screen resolution
 * - etc.
 *
 * @return DeviceInfoNonSensitive object with static device info
 */
fun getDeviceInfoNonSensitive(): DeviceInfoNonSensitive {
    Log.d(TAG, "[DEBUG] getDeviceInfoNonSensitive called.")
    // Get static device info from DeviceInfo
    val appContext = App.getContext()
    Log.d(TAG, "[DEBUG] getDeviceInfoNonSensitive: Calling DeviceInfo.getStaticDeviceInfo with context: $appContext")
    val jsonObject = DeviceInfo.getStaticDeviceInfo(appContext)
    Log.d(TAG, "[DEBUG] getDeviceInfoNonSensitive: Received static JsonObject (keys: ${jsonObject.keys.size})")
    
    // Convert to DeviceInfoNonSensitive model
    val result = DeviceInfoNonSensitive(
        deviceInfo = jsonObject
    )
    Log.d(TAG, "[DEBUG] getDeviceInfoNonSensitive: Returning DeviceInfoNonSensitive object.")
    return result
}

/**
 * Get sensitive device information that may require permissions
 * This includes:
 * - Battery status
 * - Network info
 * - RAM usage
 * - etc.
 *
 * @param context Application context
 * @return DeviceInfoSensitive object with dynamic device info
 */
suspend fun getDeviceInfoSensitive(context: Context): DeviceInfoSensitive {
    Log.d(TAG, "[DEBUG] getDeviceInfoSensitive called with context: $context")
    // Get dynamic device info from DeviceInfo
    Log.d(TAG, "[DEBUG] getDeviceInfoSensitive: Calling DeviceInfo.getDynamicDeviceInfo.")
    val jsonObject = DeviceInfo.getDynamicDeviceInfo(context)
    Log.d(TAG, "[DEBUG] getDeviceInfoSensitive: Received dynamic JsonObject (keys: ${jsonObject.keys.size})")

    // Convert to DeviceInfoSensitive model
    val result = DeviceInfoSensitive(
        deviceInfo = jsonObject
    )
    Log.d(TAG, "[DEBUG] getDeviceInfoSensitive: Returning DeviceInfoSensitive object.")
    return result
}

/**
 * Get the device Android ID
 *
 * @return Android ID as string
 */
fun getAndroidId(): String {
    Log.d(TAG, "[DEBUG] getAndroidId called.")
    Log.d(TAG, "[DEBUG] getAndroidId: Calling DeviceInfo.getAndroidId.")
    val androidId = DeviceInfo.getAndroidId()
    Log.d(TAG, "[DEBUG] getAndroidId: Received Android ID: '$androidId'")
    return androidId
}

/**
 * Get full combined device information (both sensitive and non-sensitive)
 *
 * @param context Application context
 * @return JsonObject with all device information
 */
suspend fun getFullDeviceInfo(context: Context): JsonObject {
    Log.d(TAG, "[DEBUG] getFullDeviceInfo called with context: $context")
    // Get both types of device info
    Log.d(TAG, "[DEBUG] getFullDeviceInfo: Calling DeviceInfo.getStaticDeviceInfo.")
    val staticInfo = DeviceInfo.getStaticDeviceInfo(context)
    Log.d(TAG, "[DEBUG] getFullDeviceInfo: Received staticInfo (keys: ${staticInfo.keys.size})")

    Log.d(TAG, "[DEBUG] getFullDeviceInfo: Calling DeviceInfo.getDynamicDeviceInfo.")
    val dynamicInfo = DeviceInfo.getDynamicDeviceInfo(context)
    Log.d(TAG, "[DEBUG] getFullDeviceInfo: Received dynamicInfo (keys: ${dynamicInfo.keys.size})")

    // Helper function to merge JsonObjects
    fun mergeJsonObjects(a: JsonObject, b: JsonObject): JsonObject {
        Log.d(TAG, "[DEBUG] mergeJsonObjects called. Object A keys: ${a.keys.size}, Object B keys: ${b.keys.size}")
        val merged = buildJsonObject {
            // Copy all properties from the first object
            a.forEach { (key, value) ->
                put(key, value)
            }
            // Copy all properties from the second object
            b.forEach { (key, value) ->
                put(key, value)
            }
        }
        Log.d(TAG, "[DEBUG] mergeJsonObjects: Merged object keys: ${merged.keys.size}")
        return merged
    }
    
    // Merge and return
    Log.d(TAG, "[DEBUG] getFullDeviceInfo: Merging staticInfo and dynamicInfo.")
    val mergedResult = mergeJsonObjects(staticInfo, dynamicInfo)
    Log.d(TAG, "[DEBUG] getFullDeviceInfo: Returning merged JsonObject.")
    return mergedResult
}
