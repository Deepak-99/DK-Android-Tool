package com.hawkshaw.library.ktextensions

import java.util.Locale
import kotlin.math.abs

/**
 * Utility functions for string operations
 */

/**
 * Get an empty string if the CharSequence is null or empty
 */
fun CharSequence?.getEmpty(): String {
    return if (this.isNullOrEmpty()) "" else this.toString()
}

/**
 * Calculate a non-negative hash code for a string
 */
fun String.hash(): Int {
    return abs(this.lowercase(Locale.ROOT).hashCode())
}

/**
 * Calculate a non-negative hash code for a CharSequence
 */
fun CharSequence.hash(): Int {
    return this.toString().hash()
} 