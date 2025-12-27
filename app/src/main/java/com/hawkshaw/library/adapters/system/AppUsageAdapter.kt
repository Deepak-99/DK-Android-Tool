package com.hawkshaw.library.adapters.system

import android.app.usage.UsageStatsManager
import android.content.Context
import com.hawkshaw.library.data.*
import kotlinx.coroutines.*

class AppUsageAdapter(private val context: Context) {

    private val usm =
        context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager

    fun poll() {
        val now = System.currentTimeMillis()
        val stats = usm.queryUsageStats(
            UsageStatsManager.INTERVAL_DAILY,
            now - 60_000,
            now
        )

        val top = stats.maxByOrNull { it.lastTimeUsed } ?: return

        CoroutineScope(Dispatchers.IO).launch {
            EventQueue.enqueue(
                AgentEvent.AppUsage(
                    packageName = top.packageName,
                    lastUsed = top.lastTimeUsed
                )
            )
        }
    }
}