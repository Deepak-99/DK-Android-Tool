package com.hawkshaw.library.ktextensions

import android.content.res.Resources
import kotlin.math.pow
import kotlin.math.round

/**
 * Utility functions for numbers and conversions
 */

/**
 * Convert device independent pixels (dp) to physical pixels (px)
 */
fun Int.toPx(): Int {
    return (this * Resources.getSystem().displayMetrics.density).toInt()
}

/**
 * Convert physical pixels (px) to device independent pixels (dp)
 */
fun Int.toDp(): Int {
    return (this / Resources.getSystem().displayMetrics.density).toInt()
}

/**
 * Convert physical pixels (px) to density independent pixels (dpi)
 */
fun Int.toDpi(): Int {
    return this / Resources.getSystem().displayMetrics.densityDpi
}

/**
 * Alias for toDp for backward compatibility
 */
fun getDp(px: Int): Int = px.toDp()

/**
 * Alias for toDpi for backward compatibility
 */
fun getDpi(px: Int): Int = px.toDpi()

/**
 * Alias for toPx for backward compatibility
 */
fun getPx(dp: Int): Int = dp.toPx()

/**
 * Round a double to the specified number of decimal places
 */
fun Double.roundTo(decimals: Int): Double {
    val factor = 10.0.pow(decimals)
    return round(this * factor) / factor
}

/**
 * Round a float to the specified number of decimal places
 */
fun Float.roundTo(decimals: Int): Double {
    val factor = 10.0.pow(decimals)
    return round(this * factor) / factor
}

/**
 * Convert bytes to gigabytes with specified precision
 */
fun Long.toGB(): Double {
    return (this / 1000.0.pow(3)).roundTo(2)
}

// Removed the redundant top-level functions that caused platform declaration clashes.
// The functionality is now fully covered by the extension functions above.

/*
// Removed: Alias for backward compatibility
fun toGB(bytes: Long): Double = bytes.toGB()

// Removed: Alias for backward compatibility
fun roundTo(value: Double, decimals: Int): Double = value.roundTo(decimals)

// Removed: Alias for backward compatibility
fun roundTo(value: Float, decimals: Int): Double = value.roundTo(decimals)
*/
