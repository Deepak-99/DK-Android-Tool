package com.hawkshaw.library.datalayer.room.socialmedia

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for social media messages
 */
@Dao
interface SocialMediaDao {
    /**
     * Get all social media entities
     * @return Flow of list of social media entities
     */
    @Query("SELECT * FROM social_media ORDER BY timestamp DESC")
    fun getAllMessages(): Flow<List<SocialMediaEntity>>

    /**
     * Get all unpushed social media entities
     * @return Flow of list of unpushed social media entities
     */
    @Query("SELECT * FROM social_media WHERE pushed = 0 ORDER BY timestamp ASC")
    fun getUnpushedMessages(): Flow<List<SocialMediaEntity>>

    /**
     * Insert a single entity
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(message: SocialMediaEntity)

    /**
     * Update a single entity
     */
    @Update
    suspend fun update(message: SocialMediaEntity)

    /**
     * Delete a single entity
     */
    @Delete
    suspend fun delete(message: SocialMediaEntity)

    /**
     * Delete all pushed social media entities
     */
    @Query("DELETE FROM social_media WHERE pushed = 1")
    suspend fun deletePushedMessages()

    /**
     * Mark a single entity as pushed
     */
    @Query("UPDATE social_media SET pushed = 1 WHERE id = :id")
    suspend fun markAsPushed(id: Long)

    /**
     * Nuke (delete all) entries from the social_media table.
     * This corresponds to the nukeTable method in the Java interface.
     */
    @Query("DELETE FROM social_media")
    suspend fun nukeTable()
}