package com.hawkshaw.library.datalayer.room.logger

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

/**
 * Room database entity for logging
 */
@Entity(tableName = "logs")
data class LogEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    
    @ColumnInfo(name = "level")
    val level: String,
    
    @ColumnInfo(name = "tag")
    val tag: String,
    
    @ColumnInfo(name = "message")
    val message: String,
    
    @ColumnInfo(name = "timestamp")
    val timestamp: Long = System.currentTimeMillis(),
    
    @ColumnInfo(name = "pushed")
    var pushed: Boolean = false
) {
    constructor(level: String, tag: String, message: String) : this(
        level = level,
        tag = tag,
        message = message,
        timestamp = System.currentTimeMillis()
    )
} 