package com.hawkshaw.library.control

object CommandBus {
    private val handlers = mutableMapOf<String, CommandHandler>()

    fun register(type: String, handler: CommandHandler) {
        handlers[type] = handler
    }

    fun dispatch(type: String, payload: JSONObject) {
        handlers[type]?.handle(payload)
    }
}