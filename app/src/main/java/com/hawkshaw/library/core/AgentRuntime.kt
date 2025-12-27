package com.hawkshaw.library.core

object AgentRuntime {

    lateinit var context: Context
        private set

    lateinit var flags: FeatureFlags
        private set

    fun init(ctx: Context) {
        context = ctx
        flags = FeatureFlags.default()

        EventBus.init()
        AdapterRegistry.registerDefaults()
    }
}
