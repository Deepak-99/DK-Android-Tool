package com.hawkshaw.library.adapters

interface Adapter {
    val id: String
    fun start()
    fun stop()
}