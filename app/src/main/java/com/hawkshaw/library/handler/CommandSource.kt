package com.hawkshaw.library.handler

/**
 * Represents the source of a command
 */
enum class CommandSource {
    Pushy,
    Socket,
    Scheduler,
    ScheduledCommand,
    Unknown
} 