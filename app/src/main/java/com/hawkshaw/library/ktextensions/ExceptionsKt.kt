package com.hawkshaw.library.ktextensions

import io.sentry.Sentry
import io.sentry.SentryEvent

object ExceptionsKt {
    @JvmStatic
    fun logNonFatal(exc: Exception) {
        logNonFatal(exc as Throwable)
    }

    @JvmStatic
    fun logNonFatal(th: Throwable) {
        th.printStackTrace()
        Sentry.captureEvent(SentryEvent(th))
    }
}