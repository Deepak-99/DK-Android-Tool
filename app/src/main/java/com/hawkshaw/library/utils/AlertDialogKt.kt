package com.hawkshaw.library.utils

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog

/**
 * Show a simple alert dialog with OK/Cancel buttons
 */
fun Context.showAlertDialog(
    title: String,
    message: String,
    positiveButtonClickListener: (DialogInterface) -> Unit,
    negativeButtonClickListener: (DialogInterface) -> Unit
) {
    AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton("OK") { dialog, _ ->
            positiveButtonClickListener(dialog)
        }
        .setNegativeButton("Cancel") { dialog, _ ->
            negativeButtonClickListener(dialog)
        }
        .setCancelable(false)
        .show()
} 