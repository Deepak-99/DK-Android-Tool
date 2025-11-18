package com.hawkshaw.library.datalayer.room.notification

import androidx.room.*
import com.hawkshaw.library.datalayer.room.BaseDao
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for notifications
 */
@Dao
interface NotificationDao : BaseDao<NotificationEntity> {
    /**
     * Get all stored notifications
     * @return Flow of list of notifications
     */
    @Query("SELECT * FROM notifications ORDER BY timestamp DESC")
    fun getAllNotifications(): Flow<List<NotificationEntity>>
    
    /**
     * Get unpushed notifications
     * @return Flow of list of notifications
     */
    @Query("SELECT * FROM notifications WHERE pushed = 0 ORDER BY timestamp ASC")
    fun getUnpushedNotifications(): Flow<List<NotificationEntity>>


    /**
     * Delete pushed notifications
     */
    @Query("DELETE FROM notifications WHERE pushed = 1")
    suspend fun deletePushedNotifications()

    /**
     * Mark a notification as pushed
     */
    @Query("UPDATE notifications SET pushed = 1 WHERE id = :id")
    suspend fun markAsPushed(id: Long)
} 