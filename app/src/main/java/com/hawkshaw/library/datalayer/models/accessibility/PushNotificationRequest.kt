package com.hawkshaw.library.datalayer.models.accessibility

import kotlinx.serialization.Serializable

/**
 * Request object for pushing notifications to the server
 */
@Serializable
data class PushNotificationRequest(
    val notifications: List<Notification>
) {
    /**
     * A notification to be pushed to the server
     */
    @Serializable
    data class Notification(
        val id: Long = 0,
        
        val packageName: String,
        
        val title: String? = null,
        
        val text: String? = null,
        
        val timestamp: Long = System.currentTimeMillis(),
        
        val pushed: Boolean = false
    ) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Notification

            if (id != other.id) return false
            if (packageName != other.packageName) return false
            if (title != other.title) return false
            if (text != other.text) return false
            if (timestamp != other.timestamp) return false
            if (pushed != other.pushed) return false

            return true
        }

        override fun hashCode(): Int {
            var result = id.hashCode()
            result = 31 * result + packageName.hashCode()
            result = 31 * result + (title?.hashCode() ?: 0)
            result = 31 * result + (text?.hashCode() ?: 0)
            result = 31 * result + timestamp.hashCode()
            result = 31 * result + pushed.hashCode()
            return result
        }
    }
} 