package com.hawkshaw.library.ktextensions

import android.app.Activity
import android.app.ActivityManager
import android.app.AppOpsManager
import android.app.NotificationManager
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.os.Process
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.hawkshaw.library.logger.Logger
import java.util.ArrayList
import android.util.Log // Added for logging

object ContextKt {
    private const val TAG = "ContextKtExt" // Added for logging

    @RequiresApi(Build.VERSION_CODES.O)
    @JvmStatic
    fun areNotificationsEnabled(context: Context, channelId: String?): Boolean {
        Log.d(TAG, "[DEBUG] areNotificationsEnabled called. Context: $context, ChannelId: $channelId")
        if (channelId.isNullOrEmpty()) {
            Log.d(TAG, "[DEBUG] areNotificationsEnabled: ChannelId is null or empty. Returning false.")
            return false
        }

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        Log.d(TAG, "[DEBUG] areNotificationsEnabled: Got NotificationManager.")
        
        val globalNotificationsEnabled = notificationManager.areNotificationsEnabled()
        Log.d(TAG, "[DEBUG] areNotificationsEnabled: Global notifications enabled: $globalNotificationsEnabled")
        if (!globalNotificationsEnabled) {
            Log.d(TAG, "[DEBUG] areNotificationsEnabled: Global notifications are disabled. Returning false.")
            return false
        }

        val channel = notificationManager.getNotificationChannel(channelId)
        Log.d(TAG, "[DEBUG] areNotificationsEnabled: NotificationChannel for ID '$channelId': $channel")
        
        val result = channel != null && channel.importance != NotificationManager.IMPORTANCE_NONE
        Log.d(TAG, "[DEBUG] areNotificationsEnabled: Channel exists and importance is not NONE: $result. Channel importance: ${channel?.importance}")
        
        return result 
    }

    @JvmStatic
    fun canLaunchFromBg(context: Context): Boolean {
        Log.d(TAG, "[DEBUG] canLaunchFromBg called. Context: $context")
        val sdkInt = Build.VERSION.SDK_INT
        Log.d(TAG, "[DEBUG] canLaunchFromBg: SDK_INT: $sdkInt (Q is ${Build.VERSION_CODES.Q})")
        if (sdkInt < Build.VERSION_CODES.Q) {
            Log.d(TAG, "[DEBUG] canLaunchFromBg: SDK < Q. Returning true.")
            return true
        }
        val appInForeground = isAppInForeground(context) // isAppInForeground already logs
        Log.d(TAG, "[DEBUG] canLaunchFromBg: SDK >= Q. isAppInForeground result: $appInForeground. Returning $appInForeground.")
        return appInForeground
    }

    @JvmStatic
    fun copyToClipboard(context: Context, text: CharSequence, label: String = "label") {
        Log.d(TAG, "[DEBUG] copyToClipboard called. Context: $context, Text: \"$text\", Label: \"$label\"")
        val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
        if (clipboardManager != null) {
            clipboardManager.setPrimaryClip(ClipData.newPlainText(label, text))
            Log.d(TAG, "[DEBUG] copyToClipboard: Text copied to clipboard.")
        } else {
            Log.w(TAG, "[DEBUG] copyToClipboard: ClipboardManager is null.")
        }
    }

    @JvmStatic
    fun getActivity(context: Context): Activity {
        Log.d(TAG, "[DEBUG] getActivity called. Current context: $context (Type: ${context.javaClass.name})")
        return when (context) {
            is Activity -> {
                Log.d(TAG, "[DEBUG] getActivity: Context is Activity. Returning context.")
                context
            }
            is ContextWrapper -> {
                Log.d(TAG, "[DEBUG] getActivity: Context is ContextWrapper. Calling getActivity on baseContext: ${context.baseContext}")
                getActivity(context.baseContext) // Recursive call
            }
            else -> {
                Log.e(TAG, "[DEBUG] getActivity: Activity not found in context hierarchy. Throwing exception.")
                throw Exception("Activity not found")
            }
        }
    }

    @JvmStatic
    fun isAppInForeground(context: Context): Boolean {
        Log.d(TAG, "[DEBUG] isAppInForeground called. Context: $context")
        val applicationContext = context.applicationContext
        Log.d(TAG, "[DEBUG] isAppInForeground: ApplicationContext: $applicationContext, PackageName: ${applicationContext.packageName}")
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningProcesses = activityManager.runningAppProcesses
        
        if (runningProcesses == null) {
            Log.w(TAG, "[DEBUG] isAppInForeground: runningAppProcesses is null. Returning false.")
            return false
        }
        Log.d(TAG, "[DEBUG] isAppInForeground: Found ${runningProcesses.size} running processes.")

        val processInfo = runningProcesses.find {
            it.processName == applicationContext.packageName
        }
        
        if (processInfo == null) {
            Log.w(TAG, "[DEBUG] isAppInForeground: ProcessInfo for package ${applicationContext.packageName} not found. Returning false.")
            return false
        }
        Log.d(TAG, "[DEBUG] isAppInForeground: Found ProcessInfo: $processInfo")

        val result = processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
        Log.d(TAG, "[DEBUG] isAppInForeground: Process importance: ${processInfo.importance}. Is foreground: $result.")
        return result
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @JvmStatic
    fun isAudioRecordAllowed(context: Context): Boolean {
        Log.d(TAG, "[DEBUG] isAudioRecordAllowed called. Context: $context")
        val sdkInt = Build.VERSION.SDK_INT
        Log.d(TAG, "[DEBUG] isAudioRecordAllowed: SDK_INT: $sdkInt (Q is ${Build.VERSION_CODES.Q})")
        if (sdkInt < Build.VERSION_CODES.Q) {
            Log.d(TAG, "[DEBUG] isAudioRecordAllowed: SDK < Q. Returning true.")
            return true
        }

        val appOpsManager = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        Log.d(TAG, "[DEBUG] isAudioRecordAllowed: Got AppOpsManager.")
        val mode = appOpsManager.unsafeCheckOpNoThrow(
            "android:record_audio",
            Process.myUid(),
            context.packageName
        )
        Log.d(TAG, "[DEBUG] isAudioRecordAllowed: AppOps mode for 'android:record_audio': $mode (MODE_ALLOWED is ${AppOpsManager.MODE_ALLOWED})")
        
        val result = mode == AppOpsManager.MODE_ALLOWED
        Log.d(TAG, "[DEBUG] isAudioRecordAllowed: Returning $result.")
        return result
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @JvmStatic
    fun isCameraAllowed(context: Context): Boolean {
        Log.d(TAG, "[DEBUG] isCameraAllowed called. Context: $context")
        val sdkInt = Build.VERSION.SDK_INT
        Log.d(TAG, "[DEBUG] isCameraAllowed: SDK_INT: $sdkInt (Q is ${Build.VERSION_CODES.Q})")
        if (sdkInt < Build.VERSION_CODES.Q) {
            Log.d(TAG, "[DEBUG] isCameraAllowed: SDK < Q. Returning true.")
            return true
        }

        val appOpsManager = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        Log.d(TAG, "[DEBUG] isCameraAllowed: Got AppOpsManager.")
        val mode = appOpsManager.unsafeCheckOpNoThrow(
            "android:camera",
            Process.myUid(),
            context.packageName
        )
        Logger.v("AppOpsManager", "isCameraAllowed: $mode", false, 4, null)
        Log.d(TAG, "[DEBUG] isCameraAllowed: AppOps mode for 'android:camera': $mode (MODE_ALLOWED is ${AppOpsManager.MODE_ALLOWED})")
        
        val result = mode == AppOpsManager.MODE_ALLOWED
        Log.d(TAG, "[DEBUG] isCameraAllowed: Returning $result.")
        return result
    }

    @JvmStatic
    fun readFromClipboard(context: Context): List<String> {
        Log.d(TAG, "[DEBUG] readFromClipboard called. Context: $context")
        val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
        if (clipboardManager == null) {
            Log.w(TAG, "[DEBUG] readFromClipboard: ClipboardManager is null. Returning empty list.")
            return emptyList()
        }
        
        val clipData = clipboardManager.primaryClip
        if (clipData == null) {
            Log.d(TAG, "[DEBUG] readFromClipboard: Primary clip is null. Returning empty list.")
            return emptyList()
        }
        Log.d(TAG, "[DEBUG] readFromClipboard: Got primary clip. Item count: ${clipData.itemCount}")

        val result = ArrayList<String>()
        for (i in 0 until clipData.itemCount) {
            val itemText = clipData.getItemAt(i).coerceToHtmlText(context)
            Log.d(TAG, "[DEBUG] readFromClipboard: Item $i text: \"${itemText}\"")
            result.add(itemText)
        }
        Log.d(TAG, "[DEBUG] readFromClipboard: Returning list with ${result.size} items.")
        return result
    }

    @JvmStatic
    fun toast(context: Context, text: CharSequence) {
        Log.d(TAG, "[DEBUG] toast (short) called. Context: $context, Text: \"$text\"")
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    @JvmStatic
    fun toastLong(context: Context, text: CharSequence) {
        Log.d(TAG, "[DEBUG] toastLong called. Context: $context, Text: \"$text\"")
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }
}
