package com.hawkshaw.library.features.accessibility.socialmedia

import android.os.Build
import android.util.Log
import android.view.accessibility.AccessibilityNodeInfo
import androidx.annotation.RequiresApi

/**
 * Utility class for debugging and research purposes
 * Used to print accessibility trees and debug information
 */
object Researcher {
    private const val TAG = "Researcher"

    /**
     * Generate a dash string with specified length
     */
    private fun dash(depth: Int): String {
        return "-".repeat(depth * 2)
    }

    /**
     * Returns an empty string if the string is null or empty
     */
    private fun getEmpty(str: String?): String {
        return str?.takeIf { it.isNotEmpty() } ?: ""
    }

    /**
     * Returns an empty string if the CharSequence is null or empty
     */
    private fun getEmpty(charSequence: CharSequence?): String {
        return charSequence?.toString()?.takeIf { it.isNotEmpty() } ?: ""
    }

    /**
     * Print multiple strings to logcat
     */
    fun print(vararg s: String) {
        for (message in s) {
            Log.v(TAG, message)
        }
    }

    /**
     * Print multiple CharSequences to logcat
     */
    fun print(vararg s: CharSequence) {
        for (message in s) {
            Log.v(TAG, getEmpty(message))
        }
    }

    /**
     * Print an accessibility tree starting from the given node
     * 
     * @param source The root node to start printing from
     * @param depth The indentation depth (used for recursive calls)
     */
    @RequiresApi(Build.VERSION_CODES.O)
    @JvmOverloads
    fun printTree(source: AccessibilityNodeInfo?, depth: Int = 0) {
        if (source == null) {
            print("printTree-null source")
            return
        }
        
        val viewId = source.viewIdResourceName
        val text = source.text
        val className = getEmpty(source.className)
        val contentDescription = getEmpty(source.contentDescription)
        val hintText = getEmpty(source.hintText)
        
        // Get properties available in newer API levels
        val paneTitle = if (Build.VERSION.SDK_INT >= 28) getEmpty(source.paneTitle) else ""
        val tooltipText = if (Build.VERSION.SDK_INT >= 28) getEmpty(source.tooltipText) else ""
        
        val indentation = dash(depth)
        val nodeInfo = "$indentation-> $viewId, $text, $className, $contentDescription, $hintText, $paneTitle, $tooltipText"
        
        print(nodeInfo)
        
        // Recursively print children
        val childCount = source.childCount
        for (i in 0 until childCount) {
            printTree(source.getChild(i), depth + 1)
        }
    }
} 