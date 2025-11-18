package com.hawkshaw.library.features.accessibility

import android.os.Bundle
import android.util.Log
import android.view.accessibility.AccessibilityNodeInfo

/**
 * Utility object containing helper functions for working with AccessibilityNodeInfo.
 * All functions are defined as extension functions for convenience and idiomatic Kotlin.
 */
object AccessibilityUtilsKt {

    private const val TAG = "AccessibilityUtils_DEBUG"

    /**
     * Find all AccessibilityNodeInfo elements matching the provided view ID
     * on the receiver AccessibilityNodeInfo.
     *
     * @param id The resource ID to search for.
     * @return List of matching AccessibilityNodeInfo objects.
     */
    fun AccessibilityNodeInfo.find(id: String): List<AccessibilityNodeInfo> {
        Log.d(TAG, "find(id='$id') called on node: ${this.className}")
        val results = findAccessibilityNodeInfosByViewId(id)
        Log.d(TAG, "find(id='$id'): Found ${results.size} nodes.")
        return results
    }

    /**
     * Finds the first element matching the provided view ID on the receiver node
     * and performs a click action on it.
     *
     * @param id The resource ID to search for.
     * @return True if an element was found and clicked, false otherwise.
     */
    fun AccessibilityNodeInfo.findAndClick(id: String): Boolean {
        Log.d(TAG, "findAndClick(id='$id') called on node: ${this.className}")
        // Adding a small delay can sometimes help with UI stability in accessibility services.
        Thread.sleep(200)
        val node = findFirst(id)
        if (node == null) {
            Log.w(TAG, "findAndClick(id='$id'): Node not found.")
            return false
        }
        Log.d(TAG, "findAndClick(id='$id'): Node found. Performing click.")
        val result = node.performAction(AccessibilityNodeInfo.ACTION_CLICK)
        Log.d(TAG, "findAndClick(id='$id'): Click action result: $result")
        // Note: 'node' obtained from findFirst (which uses find) should be recycled by the caller if it's the root of a new search
        // or if this utility is the effective owner. However, here 'node' is a temporary reference.
        return result
    }

    /**
     * Finds an element matching the provided view ID on the receiver node and sets its text.
     *
     * @param id The resource ID to search for.
     * @param text The text to set.
     * @return True if text was set successfully, false otherwise.
     */
    fun AccessibilityNodeInfo.findAndTypeText(id: String, text: String): Boolean {
        Log.d(TAG, "findAndTypeText(id='$id', text='$text') called on node: ${this.className}")
        Thread.sleep(200) // Delay for UI stability
        val node = findFirst(id)
        if (node == null) {
            Log.w(TAG, "findAndTypeText(id='$id'): Node not found.")
            return false
        }
        Log.d(TAG, "findAndTypeText(id='$id'): Node found. Setting text.")

        val bundle = Bundle()
        bundle.putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, text)
        Thread.sleep(200) // Delay before performing action

        val result = node.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, bundle)
        Log.d(TAG, "findAndTypeText(id='$id', text='$text'): Set text action result: $result")
        return result
    }

    /**
     * Finds the first element that matches the provided predicate, searching recursively
     * within the subtree of the receiver AccessibilityNodeInfo.
     *
     * @param predicate Function that evaluates each node.
     * @return The first matching node, or null if none found.
     */
    fun AccessibilityNodeInfo.findBy(predicate: (AccessibilityNodeInfo) -> Boolean): AccessibilityNodeInfo? {
        Log.v(TAG, "findBy() called on node: ${this.className}, viewId: ${this.viewIdResourceName}")
        if (predicate(this)) {
            Log.d(TAG, "findBy(): Predicate matched on current node: ${this.className}, viewId: ${this.viewIdResourceName}")
            return this
        }
        Log.v(TAG, "findBy(): Predicate did not match current node. Checking ${this.childCount} children.")

        for (i in 0 until childCount) {
            val child = getChildSafe(i) ?: continue
            Log.v(TAG, "findBy(): Recursively calling findBy() on child $i: ${child.className}, viewId: ${child.viewIdResourceName}")
            val result = child.findBy(predicate)
            // It's important that 'child' is recycled if not returned or used further.
            // However, if 'result' is non-null, 'result' is 'child' or one of its descendants.
            // If 'result' is null, 'child' was searched and didn't match.
            // The current structure doesn't easily allow recycling 'child' here if it's not the result,
            // as it might be part of a larger structure still being processed by the caller of the top-level findBy.
            // If 'result' is returned, the caller owns it.
            if (result != null) {
                Log.d(TAG, "findBy(): Predicate matched in subtree of child $i. Returning matching node.")
                return result
            }
        }
        Log.v(TAG, "findBy(): Predicate not matched in this subtree (node: ${this.className}, viewId: ${this.viewIdResourceName}). Returning null.")
        return null
    }

    /**
     * Finds all elements with the specified class name within the subtree of the receiver node.
     *
     * @param classname The class name to search for.
     * @param maxResults Maximum number of results to return, or 0 for unlimited.
     * @return List of matching AccessibilityNodeInfo objects.
     */
    fun AccessibilityNodeInfo.findByClass(classname: String, maxResults: Int = 1): List<AccessibilityNodeInfo> {
        Log.d(TAG, "findByClass(classname='$classname', maxResults=$maxResults) called on node: ${this.className}")
        val results = ArrayList<AccessibilityNodeInfo>()
        findByClassInternal(classname, results, maxResults, this)
        Log.d(TAG, "findByClass(classname='$classname'): Found ${results.size} nodes.")
        return results
    }

    /**
     * Internal recursive function to find nodes by class name.
     *
     * @param classname The class name to match.
     * @param results The list to add matching nodes to.
     * @param maxResults The maximum number of results to collect.
     * @param node The current node being processed.
     */
    private fun findByClassInternal(
        classname: String,
        results: ArrayList<AccessibilityNodeInfo>,
        maxResults: Int,
        node: AccessibilityNodeInfo?
    ) {
        if (node == null) {
            Log.v(TAG, "findByClassInternal(classname='$classname'): Node is null. Returning.")
            return
        }
        Log.v(TAG, "findByClassInternal(classname='$classname') processing node: ${node.className}, current results: ${results.size}")


        if (node.className?.toString() == classname) {
            Log.d(TAG, "findByClassInternal(classname='$classname'): Matched node: ${node.className}. Adding to results.")
            results.add(node) // The caller of findByClass is responsible for recycling these nodes
            if (maxResults > 0 && results.size >= maxResults) {
                Log.d(TAG, "findByClassInternal(classname='$classname'): Max results ($maxResults) reached. Returning.")
                return // Stop if max results reached
            }
        }

        for (i in 0 until node.childCount) {
            // Continue searching children only if max results not yet reached.
            if (maxResults <= 0 || results.size < maxResults) {
                val childNode = node.getChild(i) // Child node obtained here
                Log.v(TAG, "findByClassInternal(classname='$classname'): Recursively calling on child $i: ${childNode?.className}")
                findByClassInternal(classname, results, maxResults, childNode)
                // If childNode was added to results, it's handled.
                // If not, and findByClassInternal returns, childNode might need recycling if not used further by ITS caller.
                // This is complex for utilities; often, the top-level caller manages recycling of the list.
                // For a utility like this, it's generally assumed the list of nodes it returns will be managed (recycled) by the caller.
            } else {
                Log.v(TAG, "findByClassInternal(classname='$classname'): Max results reached, skipping further children of ${node.className}.")
                return // Optimization: if max results reached, no need to check further children.
            }
        }
        Log.v(TAG, "findByClassInternal(classname='$classname'): Finished processing children for node: ${node.className}")
    }

    /**
     * Finds the first element matching the provided view ID on the receiver node.
     * This is an extension function that leverages the `find` extension function.
     *
     * @param id The resource ID to search for.
     * @return The first matching node, or null if none found.
     */
    fun AccessibilityNodeInfo.findFirst(id: String): AccessibilityNodeInfo? {
        Log.d(TAG, "findFirst(id='$id') called on node: ${this.className}")
        val result = find(id).firstOrNull()
        if (result != null) {
            Log.d(TAG, "findFirst(id='$id'): Node found: ${result.className}")
        } else {
            Log.w(TAG, "findFirst(id='$id'): Node not found.")
        }
        return result
    }

    /**
     * Safely gets a child node at the specified index from the receiver node.
     * Handles potential exceptions during child retrieval.
     *
     * @param index The index of the child to retrieve.
     * @return The child node, or null if not found or an error occurred.
     */
    fun AccessibilityNodeInfo.getChildSafe(index: Int): AccessibilityNodeInfo? {
        Log.v(TAG, "getChildSafe(index=$index) called on node: ${this.className}")
        return try {
            val child = getChild(index)
            if (child == null) {
                Log.w(TAG, "getChildSafe(index=$index): Child at index is null for node: ${this.className}")
            } else {
                Log.v(TAG, "getChildSafe(index=$index): Successfully retrieved child: ${child.className}")
            }
            child
        } catch (e: Exception) {
            Log.e(TAG, "getChildSafe(index=$index): Exception getting child for node ${this.className}", e)
            null
        }
    }

    /**
     * Executes a predicate if the given condition is false.
     *
     * @param condition The boolean condition to check.
     * @param predicate A function to execute if `condition` is false.
     * @return True if `condition` was true, or if `predicate` returned true; false otherwise.
     */
    fun ifFalse(condition: Boolean, predicate: () -> Boolean): Boolean {
        Log.d(TAG, "ifFalse(condition=$condition) called.")
        if (condition) {
            Log.d(TAG, "ifFalse: Condition is true. Returning true.")
            return true
        }
        Log.d(TAG, "ifFalse: Condition is false. Executing predicate.")
        val predicateResult = predicate()
        Log.d(TAG, "ifFalse: Predicate result: $predicateResult")
        return predicateResult
    }

    // Removed the redundant top-level functions as they clash with the extension functions.
    // The extension functions (e.g., node.find(id), node.findFirst(id), node.findAndClick(id))
    // should be used directly on the AccessibilityNodeInfo object.

    /*
    // Removed:
    fun find(node: AccessibilityNodeInfo, id: String): List<AccessibilityNodeInfo> {
        return node.find(id)
    }

    // Removed:
    fun findFirst(node: AccessibilityNodeInfo, id: String): AccessibilityNodeInfo? {
        return node.findFirst(id)
    }

    // Removed:
    fun findAndClick(node: AccessibilityNodeInfo, id: String): Boolean {
        return node.findAndClick(id)
    }
    */
}
