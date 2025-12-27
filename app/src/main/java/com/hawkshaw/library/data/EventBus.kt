package com.hawkshaw.library.data

object EventBus {
    private val queue = EventQueue()

    fun emit(event: AgentEvent) {
        queue.enqueue(event)
    }

    fun init() {
        EventUploader.start(queue)
    }
}