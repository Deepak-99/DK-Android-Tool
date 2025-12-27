package com.hawkshaw.library.adapters.social

abstract class SocialAdapter : Adapter {
    protected fun emit(signal: SocialSignal) {
        EventBus.emit(
            AgentEvent.SocialSignal(
                platform = signal.platform,
                action = signal.event,
                confidence = signal.confidence
            )
        )
    }
}