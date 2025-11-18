package com.hawkshaw.library.datalayer.room.socialmedia

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

/**
 * Entity representing social media messages
 */
@Entity(tableName = "social_media")
data class SocialMediaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "type")
    // FIX: Changed type from String to SocialMediaEntity.Type
    val type: Type,

    @ColumnInfo(name = "package_name")
    val packageName: String,

    @ColumnInfo(name = "sender")
    val sender: String?,

    @ColumnInfo(name = "message")
    val message: String?,

    @ColumnInfo(name = "timestamp")
    val timestamp: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "pushed")
    var pushed: Boolean = false
) {
    /**
     * Type of message
     */
    enum class Type {
        Unknown,
        Sent,
        Received
    }
}