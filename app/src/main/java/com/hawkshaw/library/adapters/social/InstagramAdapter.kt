package com.hawkshaw.library.adapters.social

import com.hawkshaw.library.data.*

object InstagramAdapter : SocialAdapter("com.instagram.android") {

    override fun onSignal(signal: String) {
        EventQueue.enqueue(
            AgentEvent.SocialSignal(
                platform = "instagram",
                signal = signal,
                confidence = 0.80f
            )
        )
    }
}