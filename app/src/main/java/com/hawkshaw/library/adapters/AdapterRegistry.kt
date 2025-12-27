package com.hawkshaw.library.adapters

object AdapterRegistry {
    private val adapters = mutableListOf<Adapter>()

    fun register(adapter: Adapter) {
        adapters += adapter
    }

    fun registerDefaults() {
        register(AppUsageAdapter())
        register(NotificationSignalAdapter())
        register(WhatsappAdapter())
    }
}