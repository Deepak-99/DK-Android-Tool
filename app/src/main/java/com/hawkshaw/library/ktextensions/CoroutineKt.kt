package com.hawkshaw.library.ktextensions

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import com.hawkshaw.library.logger.Logger
import kotlin.coroutines.CoroutineContext // Import this!
import kotlinx.coroutines.Dispatchers // Needed for potential default dispatcher

// Map to track ongoing deferred operations by key
private val deferreds = mutableMapOf<String, Deferred<*>>()

/**
 * Launch a coroutine with a default exception handler that logs errors.
 *
 * @param context Additional CoroutineContext elements (e.g., Dispatchers.IO, Dispatchers.Main).
 * The exception handler will be combined with this context.
 * @param exceptionHandler Optional custom exception handler. If null, a default one is used.
 * @param block The suspend function to execute in the coroutine.
 */
fun CoroutineScope.safeLaunch(
    context: CoroutineContext = Dispatchers.Main, // Default to Main if no context is provided
    exceptionHandler: CoroutineExceptionHandler? = null,
    block: suspend CoroutineScope.() -> Unit
): Job {
    val handler = exceptionHandler ?: CoroutineExceptionHandler { _, exception ->
        Logger.e(
            "Coroutine",
            "Error in coroutine: ${exception.localizedMessage}",
            b = false,
            i = 12,
            nothing = null
        )
        exception.printStackTrace()
    }

    // Combine the provided context with the exception handler
    return this.launch(context + handler) { // Here's the key change: context + handler
        block()
    }
}

/**
 * Run an asynchronous operation with a key for tracking
 * @param key Unique identifier for this operation
 * @param action The suspend function to execute asynchronously
 * @return The Deferred result
 */
fun <T> CoroutineScope.doAsync(key: String, action: suspend CoroutineScope.() -> T): Deferred<T> {
    // Check if there's an existing deferred operation with this key
    val existing = deferreds[key]
    if (existing != null && !existing.isCompleted) {
        @Suppress("UNCHECKED_CAST")
        return existing as Deferred<T>
    }

    // Create a new deferred operation and store it
    val deferred = this.async(
        CoroutineExceptionHandler { _, exception ->
            Logger.e(
                "Coroutine",
                "Error in async operation: ${exception.localizedMessage}",
                b = false,
                i = 12,
                nothing = null
            )
            exception.printStackTrace()
        }
    ) {
        action()
    }

    deferreds[key] = deferred
    return deferred
}

/**
 * Cancel and forget a tracked deferred operation by key
 * @param key The key of the operation to cancel
 */
suspend fun forget(key: String) {
    val deferred = deferreds[key]
    deferred?.let {
        if (!it.isCompleted) {
            it.cancelAndJoin()
        }
        deferreds.remove(key)
    }
}