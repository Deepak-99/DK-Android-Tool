package com.hawkshaw.library.datalayer.room.socialmedia

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.hawkshaw.library.datalayer.room.BaseDao // Assuming BaseDao is defined elsewhere
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for unprocessed social media messages
 *
 * This interface extends BaseDao<UnprocessedSocialMediaEntity>, which is expected to provide
 * the standard CRUD operations (insert, insertAll, delete, deleteAll, update, updateAll)
 * and their synchronous counterparts (insertSync, deleteSync, updateSync, etc.).
 * Therefore, explicit declarations of these methods are removed here to prevent
 * "Platform declaration clash" errors in the generated Java Room implementation.
 */
@Dao
interface UnprocessedSocialMediaDao : BaseDao<UnprocessedSocialMediaEntity> {
    /**
     * Get entity by id
     */
    @Transaction
    @Query("SELECT * FROM unprocessed_social_media_entity WHERE id = :id")
    fun getById(id: Int): UnprocessedSocialMediaEntity?

    /**
     * Get all unprocessed social media entities
     * @return Flow of list of unprocessed social media entities
     */
    @Transaction
    @Query("SELECT * FROM unprocessed_social_media_entity ORDER BY id DESC")
    fun list(): Flow<List<UnprocessedSocialMediaEntity>>

    /**
     * Get unprocessed social media entities filtered by upload status
     * @return Flow of list of filtered unprocessed social media entities
     */
    @Transaction
    @Query("SELECT * FROM unprocessed_social_media_entity WHERE uploaded = :uploaded ORDER BY id DESC")
    fun list(uploaded: Boolean): Flow<List<UnprocessedSocialMediaEntity>>

    /**
     * Delete all records from the table
     * @return The number of rows deleted
     */
    @Query("DELETE FROM unprocessed_social_media_entity")
    fun nukeTable(): Int

    // Removed explicit override declarations for basic CRUD operations (delete, deleteAll, insert, insertAll, update, updateAll)
    // and their synchronous counterparts (deleteSync, deleteAllSync, insertSync, insertAllSync, updateSync, updateAllSync)
    // as they are inherited from BaseDao<UnprocessedSocialMediaEntity> and were causing name clashes.
    // Room will correctly generate implementations based on BaseDao.
}
