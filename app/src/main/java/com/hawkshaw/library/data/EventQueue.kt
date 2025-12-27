package com.hawkshaw.library.data


import kotlinx.coroutines.channels.Channel

object EventQueue {
    val channel = Channel<AgentEvent>(capacity = Channel.BUFFERED)

    suspend fun enqueue(event: AgentEvent) {
        channel.send(event)
    }
}