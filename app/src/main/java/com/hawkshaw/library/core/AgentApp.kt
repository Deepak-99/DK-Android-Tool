package com.hawkshaw.library.core

class AgentApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AgentRuntime.init(this)
    }
