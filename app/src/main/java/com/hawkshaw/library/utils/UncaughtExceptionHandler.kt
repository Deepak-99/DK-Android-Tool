package com.hawkshaw.library.utils

import android.util.Log // Keep this for existing Log.e and new Log.d
import com.hawkshaw.library.NotNullSingleValueVarException
import com.hawkshaw.library.ktextensions.safeLaunch
import com.hawkshaw.library.logger.Logger
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.exitProcess

/**
 * Global uncaught exception handler
 */
object UncaughtExceptionHandler {
    private const val TAG = "UncaughtExceptionHandler"

    // Default values for exception handling
    private const val DEFAULT_MAX_EXCEPTIONS = 5L
    private const val DEFAULT_RESET_DELAY_MS = 60000L

    /**
     * Set up global exception handler
     */
    fun handleException() {
        Log.d(TAG, "[DEBUG] handleException called. Setting DefaultUncaughtExceptionHandler.")
        Thread.setDefaultUncaughtExceptionHandler(ExceptionHandler())
        Log.d(TAG, "[DEBUG] handleException: DefaultUncaughtExceptionHandler set.")
    }

    /**
     * Private class for handling uncaught exceptions
     */
    private class ExceptionHandler : Thread.UncaughtExceptionHandler {
        private val exceptionCounter = AtomicInteger()
        private var counterResetJob: Job? = null

        override fun uncaughtException(t: Thread, e: Throwable) {
            Log.d(TAG, "[DEBUG] uncaughtException triggered. Thread: ${t.name}, Exception: ${e.javaClass.simpleName}, Message: ${e.message}")
            // Existing Logger.e call
            Logger.e(TAG, e.message.toString(), b = false, i = 12, nothing = null)
            Log.d(TAG, "[DEBUG] uncaughtException: Stack trace printing to System.err.")
            e.printStackTrace() // This prints to System.err, usually visible in Logcat

            // Use default max exception count
            val maxExceptions = DEFAULT_MAX_EXCEPTIONS
            Log.d(TAG, "[DEBUG] uncaughtException: Max exceptions allowed: $maxExceptions")

            val currentCountBeforeIncrement = exceptionCounter.get()
            val currentCountAfterIncrement = exceptionCounter.getAndIncrement() // This gets then increments, so after is actually before the check
            Log.d(TAG, "[DEBUG] uncaughtException: Exception counter before increment: $currentCountBeforeIncrement. Value used in check (after getAndIncrement): $currentCountAfterIncrement")


            if (currentCountAfterIncrement < maxExceptions) {
                Log.d(TAG, "[DEBUG] uncaughtException: Exception count ($currentCountAfterIncrement) is less than maxExceptions ($maxExceptions). Scheduling counter reset.")
                // Cancel previous reset job if it exists
                if (counterResetJob != null) {
                    Log.d(TAG, "[DEBUG] uncaughtException: Previous counterResetJob exists. Cancelling it.")
                    counterResetJob?.cancel(CancellationException("New exception detected, cancelling previous reset job"))
                    Log.d(TAG, "[DEBUG] uncaughtException: Previous counterResetJob cancelled.")
                } else {
                    Log.d(TAG, "[DEBUG] uncaughtException: No previous counterResetJob to cancel.")
                }

                // Schedule a new reset job
                Log.d(TAG, "[DEBUG] uncaughtException: Scheduling new counterResetJob.")
                counterResetJob = CoroutineScope(Dispatchers.IO).safeLaunch {
                    Log.d(TAG, "[DEBUG] CounterResetJob: Coroutine started on thread: ${Thread.currentThread().name}")
                    try {
                        // Use default reset delay
                        val resetDelay = DEFAULT_RESET_DELAY_MS
                        Log.d(TAG, "[DEBUG] CounterResetJob: Reset delay set to: $resetDelay ms.")

                        Log.d(TAG, "[DEBUG] CounterResetJob: Calling delay($resetDelay).")
                        delay(resetDelay)
                        Log.d(TAG, "[DEBUG] CounterResetJob: Delay completed.")

                        exceptionCounter.set(0)
                        Log.d(TAG, "[DEBUG] CounterResetJob: Exception counter reset to 0.")
                    } catch (ce: CancellationException) {
                        Log.d(TAG, "[DEBUG] CounterResetJob: Coroutine was cancelled: ${ce.message}")
                        // This is expected if another exception occurs before reset
                    } catch (e: Exception) {
                        Log.e(TAG, "Failed to reset exception counter: ${e.message}")
                        Log.d(TAG, "[DEBUG] CounterResetJob: Caught Exception during reset: ${e.message}", e)
                    } finally {
                        Log.d(TAG, "[DEBUG] CounterResetJob: Coroutine finally block reached.")
                    }
                }
                Log.d(TAG, "[DEBUG] uncaughtException: New counterResetJob scheduled: $counterResetJob")
            } else if (e is NotNullSingleValueVarException) {
                Log.d(TAG, "[DEBUG] uncaughtException: Exception count ($currentCountAfterIncrement) reached/exceeded max ($maxExceptions). Exception is NotNullSingleValueVarException. Exiting process(2).")
                exitProcess(2)
            } else {
                Log.d(TAG, "[DEBUG] uncaughtException: Exception count ($currentCountAfterIncrement) reached/exceeded max ($maxExceptions). Exception is not NotNullSingleValueVarException. Exiting process(2).")
                exitProcess(2)
            }
            Log.d(TAG, "[DEBUG] uncaughtException: Method complete.")
        }
    }
}
