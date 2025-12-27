package com.hawkshaw.library.adapters.social

import com.hawkshaw.library.data.*

object TelegramAdapter : SocialAdapter("org.telegram.messenger") {

    override fun onSignal(signal: String) {
        EventQueue.enqueue(
            AgentEvent.SocialSignal(
                platform = "telegram",
                signal = signal,
                confidence = 0.85f
            )
        )
    }
}