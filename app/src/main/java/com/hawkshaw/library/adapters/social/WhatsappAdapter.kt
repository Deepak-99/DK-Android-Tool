package com.hawkshaw.library.adapters.social

class WhatsappAdapter : SocialAdapter() {

    val id = "whatsapp"

    fun start() {
        if (!AgentRuntime.flags.socialSignals) return

        // observe accessibility signals / notifications
        // NO text scraping
        emit(
            SocialSignal(
                platform = "WHATSAPP",
                event = "CHAT_ACTIVITY",
                confidence = 0.6f
            )
        )
    }

    fun stop() {}
}