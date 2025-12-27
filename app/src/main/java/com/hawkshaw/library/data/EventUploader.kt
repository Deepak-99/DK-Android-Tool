package com.hawkshaw.library.data

import com.hawkshaw.library.network.service.LogsService
import kotlinx.coroutines.*

object EventUploader {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    fun start() {
        scope.launch {
            for (event in EventQueue.channel) {
                try {
                    upload(event)
                } catch (e: Exception) {
                    // silent retry later
                }
            }
        }
    }

    private suspend fun upload(event: AgentEvent) {
        when (event) {
            is AgentEvent.SocialSignal ->
                LogsService.pushAppLogs(event.toLog())
            is AgentEvent.AppUsage ->
                LogsService.pushAppLogs(event.toLog())
            is AgentEvent.NotificationSignal ->
                LogsService.pushAppLogs(event.toLog())
        }
    }
}