package com.hawkshaw.library.data

sealed class AgentEvent(val type: String) {
    data class AppUsage(val packageName: String, val foreground: Boolean)
        : AgentEvent("APP_USAGE")

    data class SocialSignal(
        val platform: String,
        val action: String,
        val confidence: Float
    ) : AgentEvent("SOCIAL_SIGNAL")
}