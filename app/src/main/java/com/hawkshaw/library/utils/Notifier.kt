package com.hawkshaw.library.utils

import android.content.Context
import android.widget.Toast
import com.hawkshaw.library.App

/**
 * Utility class for showing notifications and toasts
 */
object Notifier {
    /**
     * Show a toast message
     * 
     * @param context Context to show the toast in
     * @param message Message to display
     * @param duration Duration of the toast (default: Toast.LENGTH_SHORT)
     */
    fun showToast(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, message, duration).show()
    }
} 