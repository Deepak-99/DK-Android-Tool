package com.hawkshaw.library.datalayer.room.notification

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

/**
 * Entity representing a notification received by the application
 */
@Entity(tableName = "notifications")
data class NotificationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    
    @ColumnInfo(name = "package_name")
    val packageName: String,
    
    @ColumnInfo(name = "title")
    val title: String?,
    
    @ColumnInfo(name = "text")
    val text: String?,
    
    @ColumnInfo(name = "timestamp")
    val timestamp: Long = System.currentTimeMillis(),
    
    @ColumnInfo(name = "pushed")
    var pushed: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NotificationEntity

        if (packageName != other.packageName) return false
        if (title != other.title) return false
        if (text != other.text) return false
        if (timestamp != other.timestamp) return false
        if (pushed != other.pushed) return false

        return true
    }

    override fun hashCode(): Int {
        var result = packageName.hashCode()
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (text?.hashCode() ?: 0)
        result = 31 * result + timestamp.hashCode()
        result = 31 * result + pushed.hashCode()
        return result
    }
} 