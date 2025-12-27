package com.hawkshaw.library.control

import com.hawkshaw.library.network.socket.SocketService
import kotlinx.coroutines.*

object WsCommandService {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    fun start() {
        SocketService.setListener { command ->
            scope.launch {
                CommandBus.dispatch(command)
            }
        }
    }

    fun stop() {
        scope.cancel()
        SocketService.disconnect()
    }
}
