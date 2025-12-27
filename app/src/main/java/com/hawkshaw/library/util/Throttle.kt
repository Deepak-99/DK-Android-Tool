package com.hawkshaw.library.util

object Throttle {

    private val last = mutableMapOf<String, Long>()

    fun allow(key: String, intervalMs: Long): Boolean {
        val now = System.currentTimeMillis()
        val prev = last[key] ?: 0
        return if (now - prev > intervalMs) {
            last[key] = now
            true
        } else false
    }
}