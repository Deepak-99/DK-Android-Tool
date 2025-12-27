package com.hawkshaw.library.control

interface CommandHandler {
    fun handle(payload: JSONObject)
}