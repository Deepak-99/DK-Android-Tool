package com.hawkshaw.library.deviceinfo

import android.app.ActivityManager
import android.content.ContentResolver
import android.content.Context
import android.graphics.Point
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.BatteryManager
import android.os.Build
import android.os.Process
import android.provider.Settings
import android.util.Base64
import android.util.Log
import android.view.WindowManager
import com.hawkshaw.library.App
import com.hawkshaw.app.BuildConfig
import com.hawkshaw.library.logger.Logger
import kotlinx.coroutines.*
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import java.net.NetworkInterface
import java.security.MessageDigest
import java.util.Locale
import java.util.UUID
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlin.math.pow
import kotlin.math.roundToInt

/**
 * Singleton object for device information functionality
 */
object DeviceInfo {
    private const val TAG = "DeviceInfo"
    private const val PLATFORM_CODE = "android"
    private const val LIBRARY_VERSION_CODE = BuildConfig.VERSION_CODE.toString()
    private const val LIBRARY_VERSION_NAME = BuildConfig.VERSION_NAME

    /**
     * Network Interface representation class
     */
    class NIF(val name: String, val mac: String) {
        fun toJsonObject(): JsonObject = buildJsonObject {
            put("name", name)
            put("mac", mac)
        }.also {
            // No specific log here as it's a simple conversion, called within a loop later
        }
    }

    // Core device information functions
    fun getDeviceName(): String {
        Log.d(TAG, "[DEBUG] getDeviceName called.")
        val result = buildString {
            val manufacturer = Build.MANUFACTURER
            val model = Build.MODEL
            Log.d(TAG, "[DEBUG] getDeviceName: Manufacturer='${manufacturer}', Model='${model}'")
            append(if (model.startsWith(manufacturer, true)) model.replaceFirstChar { if (it.isLowerCase()) it.titlecase(
                Locale.ROOT
            ) else it.toString() }
                  else "${manufacturer.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }} $model")
        }
        Log.d(TAG, "[DEBUG] getDeviceName result: '$result'")
        return result
    }

    fun getAndroidId(): String {
        Log.d(TAG, "[DEBUG] getAndroidId called.")
        val androidId = try {
            Settings.Secure.getString(App.getContext().contentResolver, Settings.Secure.ANDROID_ID) ?: ""
        } catch (e: Exception) {
            Log.e(TAG, "[DEBUG] getAndroidId: Error getting Android ID", e) // Debug log for exception
            Logger.e(TAG, "Error getting Android ID", e, false, 12, null)
            ""
        }
        Log.d(TAG, "[DEBUG] getAndroidId result: '$androidId'")
        return androidId
    }

    private fun getBase64Sha256(data: ByteArray): String {
        Log.d(TAG, "[DEBUG] getBase64Sha256 called with data size: ${data.size}.")
        val hash = try {
            Base64.encodeToString(MessageDigest.getInstance("SHA-256").digest(data), Base64.NO_WRAP)
        } catch (e: Exception) {
            Log.e(TAG, "[DEBUG] getBase64Sha256: Error generating SHA-256 hash", e) // Debug log for exception
            Logger.e(TAG, "Error generating SHA-256 hash", e, false, 12, null)
            ""
        }
        Log.d(TAG, "[DEBUG] getBase64Sha256 result (first 10 chars): '${hash.take(10)}...'")
        return hash
    }

    private fun runShellCommand(command: String): String {
        Log.d(TAG, "[DEBUG] runShellCommand called with command: '$command'")
        val result = Shell.runShellCommand(command) // Assuming Shell.runShellCommand handles its own logging or is a black box
        Log.d(TAG, "[DEBUG] runShellCommand for '$command' result: '$result'")
        return result
    }

    private fun toGB(bytes: Long): String {
        // Log.d(TAG, "[DEBUG] toGB called with bytes: $bytes") // Potentially noisy if called often
        val result = "%.2f".format(bytes / (1024.0 * 1024.0 * 1024.0))
        // Log.d(TAG, "[DEBUG] toGB result: '$result'")
        return result
    }

    private fun roundTo(value: Float, places: Int): String {
        Log.d(TAG, "[DEBUG] roundTo called with value: $value, places: $places") // Potentially noisy
        val factor = 10.0.pow(places.toDouble()).toFloat()
        val result = "%.${places}f".format((value * factor).roundToInt() / factor)
        Log.d(TAG, "[DEBUG] roundTo result: '$result'")
        return result
    }

    // Battery and system info functions
    private fun getBatteryCapacity(context: Context): Double {
        Log.d(TAG, "[DEBUG] getBatteryCapacity called.")
        val capacity = try {
            Log.d(TAG, "[DEBUG] getBatteryCapacity: Attempting reflection for com.android.internal.os.PowerProfile.")
            Class.forName("com.android.internal.os.PowerProfile")
                .getConstructor(Context::class.java)
                .newInstance(context)
                .let { powerProfile ->
                    powerProfile::class.java
                        .getMethod("getBatteryCapacity")
                        .invoke(powerProfile) as Double
                }
        } catch (e: Exception) {
            Log.e(TAG, "[DEBUG] getBatteryCapacity: Error getting battery capacity via reflection", e) // Debug log for exception
            Logger.e(TAG, "Error getting battery capacity", e, false, 12, null)
            0.0
        }
        Log.d(TAG, "[DEBUG] getBatteryCapacity result: $capacity")
        return capacity
    }

    private fun getBatteryInfo(context: Context): Map<String, Any> {
        Log.d(TAG, "[DEBUG] getBatteryInfo called.")
        val batteryManager = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
        val info = mapOf(
            "battery.capacity_current_percent" to batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY),
            "battery.capacity_total" to getBatteryCapacity(context),
            "battery.is_charging" to batteryManager.isCharging()
        )
        Log.d(TAG, "[DEBUG] getBatteryInfo result: $info")
        return info
    }

    private fun getRAMInfo(context: Context): Map<String, Any> {
        Log.d(TAG, "[DEBUG] getRAMInfo called.")
        val memInfo = ActivityManager.MemoryInfo().apply {
            (context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager).getMemoryInfo(this)
        }
        val info = mapOf(
            "ram.total_memory" to toGB(memInfo.totalMem),
            "ram.available_memory" to toGB(memInfo.availMem),
            "ram.threshold_memory" to toGB(memInfo.threshold),
            "ram.is_memory_low" to memInfo.lowMemory,
            "ram.percent_available" to roundTo((memInfo.availMem.toFloat() / memInfo.totalMem.toFloat()) * 100f, 2)
        )
        Log.d(TAG, "[DEBUG] getRAMInfo result: Total=${info["ram.total_memory"]}, Available=${info["ram.available_memory"]}")
        return info
    }

    // Network-related functions
    private fun getNetworkInterfaceInfo(): List<NIF> {
        Log.d(TAG, "[DEBUG] getNetworkInterfaceInfo called.")
        val nifList = try {
            NetworkInterface.getNetworkInterfaces()?.toList()?.mapNotNull { nif ->
                nif.hardwareAddress?.let { hwAddress ->
                    NIF(
                        name = nif.name,
                        mac = hwAddress.joinToString(":") { "%02X".format(it) }
                    ).also {
                        Log.d(TAG, "[DEBUG] getNetworkInterfaceInfo: Found NIF Name='${it.name}', MAC='${it.mac}'")
                    }
                }
            } ?: emptyList()
        } catch (e: Exception) {
            Log.e(TAG, "[DEBUG] getNetworkInterfaceInfo: Error getting network interfaces", e) // Debug log for exception
            Logger.e(TAG, "Error getting network interfaces", e, false, 12, null)
            emptyList()
        }
        Log.d(TAG, "[DEBUG] getNetworkInterfaceInfo result count: ${nifList.size}")
        return nifList
    }

    private fun getRestrictBackgroundStatus(cm: ConnectivityManager): String {
        Log.d(TAG, "[DEBUG] getRestrictBackgroundStatus called.")
        val status = when (cm.restrictBackgroundStatus) {
            ConnectivityManager.RESTRICT_BACKGROUND_STATUS_DISABLED -> "DISABLED"
            ConnectivityManager.RESTRICT_BACKGROUND_STATUS_WHITELISTED -> "WHITELISTED"
            ConnectivityManager.RESTRICT_BACKGROUND_STATUS_ENABLED -> "ENABLED"
            else -> cm.restrictBackgroundStatus.toString()
        }
        Log.d(TAG, "[DEBUG] getRestrictBackgroundStatus result: '$status'")
        return status
    }

    private fun isVPNConnected(cm: ConnectivityManager): Boolean {
        Log.d(TAG, "[DEBUG] isVPNConnected called.")
        val connected = cm.getNetworkCapabilities(cm.activeNetwork)?.hasTransport(NetworkCapabilities.TRANSPORT_VPN) == true
        Log.d(TAG, "[DEBUG] isVPNConnected result: $connected")
        return connected
    }

    // System status checks
    private fun isDevOptionsEnabled(cr: ContentResolver): Boolean {
        Log.d(TAG, "[DEBUG] isDevOptionsEnabled called.")
        val enabled = Settings.Secure.getInt(cr, Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0) != 0
        Log.d(TAG, "[DEBUG] isDevOptionsEnabled result: $enabled")
        return enabled
    }

    private fun isMockLocationEnabled(cr: ContentResolver): Boolean {
        Log.d(TAG, "[DEBUG] isMockLocationEnabled called.")
        val enabled = Settings.Secure.getInt(cr, "mock_location", 0) != 0
        Log.d(TAG, "[DEBUG] isMockLocationEnabled result: $enabled")
        return enabled
    }

    private fun isRooted(context: Context): Boolean {
        Log.d(TAG, "[DEBUG] isRooted called. Current implementation returns false.")
        // Implementation omitted, actual result might vary based on omitted logic.
        return false
    }

    private fun hasRootCloakingPackages(context: Context): String {
        Log.d(TAG, "[DEBUG] hasRootCloakingPackages called. Current implementation returns empty string.")
        // Implementation omitted, actual result might vary.
        return ""
    }

    private fun networkTypeClass(networkType: Int): String {
        Log.d(TAG, "[DEBUG] networkTypeClass called with networkType: $networkType")
        val typeClass = when (networkType) {
            20 -> "5G"
            13 -> "4G"
            in arrayOf(3, 5, 6, 8, 9, 10, 12, 15, 16, 17) -> "3G"
            in arrayOf(1, 2, 4, 7, 11, 16) -> "2G"
            else -> "Unknown"
        }
        Log.d(TAG, "[DEBUG] networkTypeClass result: '$typeClass'")
        return typeClass
    }

    private fun networkInfo(cm: ConnectivityManager): Map<String, Any> {
        Log.d(TAG, "[DEBUG] networkInfo called.")
        val info = cm.activeNetworkInfo?.let { activeInfo ->
            Log.d(TAG, "[DEBUG] networkInfo: Active network found. TypeName='${activeInfo.typeName}', SubtypeName='${activeInfo.subtypeName}'")
            mapOf(
                "network.type" to activeInfo.typeName,
                "network.subtype" to if (activeInfo.type == ConnectivityManager.TYPE_WIFI) activeInfo.typeName
                                    else networkTypeClass(activeInfo.subtype),
                "network.subtype_name" to activeInfo.subtypeName,
                "network.subtype_int" to activeInfo.subtype
            )
        } ?: emptyMap()
        Log.d(TAG, "[DEBUG] networkInfo result: Type=${info["network.type"]}, Subtype=${info["network.subtype"]}")
        return info
    }

    // Main device info functions
    fun getLoginDeviceInfo(context: Context): JsonObject {
        Log.d(TAG, "[DEBUG] getLoginDeviceInfo called.")
        val packageInfo = context.packageManager.getPackageInfo(context.packageName, 64)
        val json = buildJsonObject {
            put("android_id", getAndroidId())
            put("sdk_int", Build.VERSION.SDK_INT)
            put("brand", Build.BRAND)
            put("model", Build.MODEL)
            put("sim_operator", runShellCommand("getprop gsm.sim.operator.alpha"))
            put("version_code", packageInfo.versionCode)
            put("package_name", packageInfo.packageName)
            put("app_uid", Process.myUid())
        }
        Log.d(TAG, "[DEBUG] getLoginDeviceInfo result: AndroidId='${json["android_id"]}', Model='${json["model"]}'")
        return json
    }

    fun getStaticDeviceInfo(context: Context): JsonObject {
        Log.d(TAG, "[DEBUG] getStaticDeviceInfo called.")
        val packageInfo = context.packageManager.getPackageInfo(context.packageName, 64)
        val signatureHash = packageInfo.signatures.firstOrNull()?.let {
            getBase64Sha256(it.toByteArray())
        } ?: ""

        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val point = Point().apply { display.getSize(this) }
        Log.d(TAG, "[DEBUG] getStaticDeviceInfo: Display size x=${point.x}, y=${point.y}, RefreshRate=${display.refreshRate}")

        val json = buildJsonObject {
            // Basic device info
            put("platform", PLATFORM_CODE)
            put("package_name", packageInfo.packageName)
            put("version_code", packageInfo.versionCode)
            put("version_name", packageInfo.versionName ?: "")

            // Library info
            put("library_version_code", LIBRARY_VERSION_CODE)
            put("library_version_name", LIBRARY_VERSION_NAME)

            // Build info
            put("sdk_int", Build.VERSION.SDK_INT)
            put("brand", Build.BRAND)
            put("manufacturer", Build.MANUFACTURER)
            put("product", Build.PRODUCT)
            put("model", Build.MODEL)
            put("device", Build.DEVICE)
            // Using Build.SERIAL which is more widely available
            // Note: On API 29+ (Android 10+), this will return "unknown" for apps targeting API 29+
            // This is due to privacy restrictions in newer Android versions
            put("serial", Build.SERIAL.ifEmpty { "unknown" })
            put("display", Build.DISPLAY)
            put("release", Build.VERSION.RELEASE)
            put("build_incremental", Build.VERSION.INCREMENTAL)
            put("build_security_patch", Build.VERSION.SECURITY_PATCH ?: "")
            put("device_board", Build.BOARD)
            put("build_fingerprint", Build.FINGERPRINT)
            put("build_host", Build.HOST)
            put("build_id", Build.ID)

            // System properties
            put("architecture", System.getProperty("os.arch") ?: "")
            put("UA", System.getProperty("http.agent") ?: "")
            put("os_version", System.getProperty("os.version") ?: "")
            put("cores_number", Runtime.getRuntime().availableProcessors())

            // Hardware info
            put("vbmeta_digest", runShellCommand("getprop ro.boot.vbmeta.digest"))
            put("cpu_model", runShellCommand("awk '/^Hardware/{print \$NF}' /proc/cpuinfo"))
            put("cpu_model_2", runShellCommand("cat /proc/cpuinfo | grep 'Hardware' | sed 's/.*\\: //'"))
            put("sha256", signatureHash)
            put("supported_abis", Build.SUPPORTED_ABIS.joinToString())

            // Display info
            put("screen_density", context.resources.displayMetrics.densityDpi)
            if (!display.refreshRate.isNaN()) {
                put("display_refresh_rate", display.refreshRate.roundToInt())
                put("display_x", point.x)
                put("display_y", point.y)
            }
        }
        Log.d(TAG, "[DEBUG] getStaticDeviceInfo result: SDKInt='${json["sdk_int"]}', Model='${json["model"]}', Fingerprint='${json["build_fingerprint"]?.toString()?.take(20)}...'")
        return json
    }

    suspend fun getDynamicDeviceInfo(context: Context): JsonObject = withContext(Dispatchers.IO) {
        Log.d(TAG, "[DEBUG] getDynamicDeviceInfo called (on Dispatchers.IO).")
        val json = buildJsonObject {
            // Network interfaces
            put("network_interfaces", JsonArray(getNetworkInterfaceInfo().map { it.toJsonObject() }))
            
            // System info
            getRAMInfo(context).forEach { (k, v) -> put(k, v.toString()) }
            getBatteryInfo(context).forEach { (k, v) -> put(k, v.toString()) }
            
            // Network status
            (context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager)?.let { cm ->
                Log.d(TAG, "[DEBUG] getDynamicDeviceInfo: Processing network status.")
                put("is_vpn_enabled", isVPNConnected(cm))
                put("battery.optimisation", getRestrictBackgroundStatus(cm))
                networkInfo(cm).forEach { (k, v) -> put(k, v.toString()) }
            }

            // System status
            val cr = context.contentResolver
            Log.d(TAG, "[DEBUG] getDynamicDeviceInfo: Processing system status.")
            put("dev_options_enabled", isDevOptionsEnabled(cr))
            put("mock_location_enabled", isMockLocationEnabled(cr))
            put("is_device_rooted", isRooted(context))
            put("has_root_cloaking_packages", hasRootCloakingPackages(context))
        }
        Log.d(TAG, "[DEBUG] getDynamicDeviceInfo result: RAM_Total='${json["ram.total_memory"]}', VPN='${json["is_vpn_enabled"]}', Rooted='${json["is_device_rooted"]}'")
        json
    }

    suspend fun getAdvertisingId(context: Context, retries: Int = 3): String = withContext(Dispatchers.IO) {
        Log.d(TAG, "[DEBUG] getAdvertisingId called (on Dispatchers.IO). Retries left: $retries")
        if (retries <= 0) {
            Log.d(TAG, "[DEBUG] getAdvertisingId: No retries left. Returning empty string.")
            return@withContext ""
        }
        try {
            Log.d(TAG, "[DEBUG] getAdvertisingId: Simulating network delay (3000ms).")
            delay(3000) // Simulate network delay
            val adId = UUID.randomUUID().toString() // Placeholder - replace with real implementation
            Log.d(TAG, "[DEBUG] getAdvertisingId: Placeholder Ad ID generated: '$adId'")
            adId
        } catch (e: Exception) {
            Log.e(TAG, "[DEBUG] getAdvertisingId: Advertising ID error (retries left: ${retries - 1})", e) // Debug log for exception
            Logger.e(TAG, "Advertising ID error (retries left: ${retries - 1})", e, false, 12, null)
            if (retries > 1) {
                Log.d(TAG, "[DEBUG] getAdvertisingId: Retrying...")
                getAdvertisingId(context, retries - 1)
            } else {
                Log.d(TAG, "[DEBUG] getAdvertisingId: Max retries reached after error. Returning empty string.")
                ""
            }
        }
    }

    suspend fun getDeviceInfo(): JsonObject = withContext(Dispatchers.IO) {
        Log.d(TAG, "[DEBUG] getDeviceInfo called (on Dispatchers.IO).")
        val context = App.getContext()
        val json = buildJsonObject {
            put("static", getStaticDeviceInfo(context))
            put("dynamic", getDynamicDeviceInfo(context))
            put("combined", buildJsonObject {
                val androidId = getAndroidId()
                val rooted = isRooted(context)
                val vpnEnabled = runCatching {
                    (context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager)
                        ?.let { isVPNConnected(it) } ?: false
                }.getOrDefault(false)
                val adId = getAdvertisingId(context, 3)

                Log.d(TAG, "[DEBUG] getDeviceInfo (combined): AndroidId='$androidId', Rooted='$rooted', VPN='$vpnEnabled', AdId='$adId'")
                put("android_id", androidId)
                put("is_rooted", rooted)
                put("is_vpn_enabled", vpnEnabled)
                put("advertising_id", adId)
            })
        }
        Log.d(TAG, "[DEBUG] getDeviceInfo completed. Static keys: ${json["static"]?.let { it as JsonObject }?.keys?.size}, Dynamic keys: ${json["dynamic"]?.let { it as JsonObject }?.keys?.size}")
        json
    }

    suspend fun getDeviceInfo(continuation: Continuation<Any>): Any = suspendCoroutine { cont ->
        Log.d(TAG, "[DEBUG] getDeviceInfo (with Continuation) called.")
        CoroutineScope(Dispatchers.IO).launch {
            Log.d(TAG, "[DEBUG] getDeviceInfo (with Continuation): Coroutine launched on Dispatchers.IO.")
            try {
                val deviceInfoJson = getDeviceInfo() // Calls the suspend fun getDeviceInfo()
                Log.d(TAG, "[DEBUG] getDeviceInfo (with Continuation): Successfully retrieved device info. Resuming continuation.")
                cont.resume(deviceInfoJson)
            } catch (e: Exception) {
                Log.e(TAG, "[DEBUG] getDeviceInfo (with Continuation): Exception caught. Resuming continuation with exception.", e) // Debug log
                cont.resumeWithException(e)
            }
        }
    }

    // This is a suspend function with the same name as kotlinx.coroutines.delay
    // It seems to be an intentional re-declaration within this object, possibly for specific behavior or logging.
    // If it's meant to call the standard delay, it should be kotlinx.coroutines.delay(timeMillis)
    // For now, I'll assume it's an intended shadowing or a mistake and log its call.
    private suspend fun delay(timeMillis: Long) {
        Log.d(TAG, "[DEBUG] Custom DeviceInfo.delay($timeMillis) called. This will call kotlinx.coroutines.delay.")
        kotlinx.coroutines.delay(timeMillis) // Assuming it should call the standard delay
    }
}
