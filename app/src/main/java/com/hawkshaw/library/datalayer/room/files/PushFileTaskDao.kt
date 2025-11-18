package com.hawkshaw.library.datalayer.room.files

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.hawkshaw.library.datalayer.room.BaseDao // Assuming BaseDao is defined elsewhere
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for file upload tasks
 *
 * This interface extends BaseDao<PushFileTaskEntity>, which is expected to provide
 * the standard CRUD operations (insert, insertAll, delete, deleteAll, update, updateAll).
 * Therefore, explicit declarations of these methods are removed here to prevent
 * "Platform declaration clash" errors in the generated Java Room implementation.
 */
@Dao
interface PushFileTaskDao : BaseDao<PushFileTaskEntity> {
    /**
     * Get all pending tasks
     * @return Flow of list of pending upload tasks
     */
    @Transaction
    @Query("SELECT * FROM push_file_task_entity ORDER BY priority DESC, id ASC")
    fun getPendingTasks(): Flow<List<PushFileTaskEntity>>

    /**
     * Get all tasks
     * @return Flow of list of all upload tasks
     */
    @Transaction
    @Query("SELECT * FROM push_file_task_entity ORDER BY id DESC")
    fun getAllTasks(): Flow<List<PushFileTaskEntity>>

    /**
     * Delete all records from the table
     * @return The number of rows deleted
     */
    @Query("DELETE FROM push_file_task_entity")
    fun nukeTable(): Int

    /**
     * Delete a task by ID
     * @param id Task ID to delete
     * @return The number of rows deleted
     */
    @Query("DELETE FROM push_file_task_entity WHERE id = :id")
    fun deleteById(id: Int): Int

    /**
     * Get the top task with file size less than maxSize
     * @param maxSize Maximum file size in bytes
     * @return Top priority task matching the criteria
     */
    @Transaction
    @Query("SELECT * FROM push_file_task_entity WHERE length <= :maxSize ORDER BY priority DESC, id ASC LIMIT 1")
    fun getTopTask(maxSize: Int): PushFileTaskEntity?

    /**
     * Get the top task regardless of size
     * @return Top priority task
     */
    @Transaction
    @Query("SELECT * FROM push_file_task_entity ORDER BY priority DESC, id ASC LIMIT 1")
    fun getTopTask(): PushFileTaskEntity?

    /**
     * Increment the priority of a task
     * @param id Task ID
     * @return The number of rows updated
     */
    @Query("UPDATE push_file_task_entity SET priority = priority + 1 WHERE id = :id")
    fun incrementPriority(id: Int): Int

    /**
     * Set the last push timestamp for a task
     * @param id Task ID
     * @param timestamp Timestamp to set (defaults to current time)
     * @return The number of rows updated
     */
    @Query("UPDATE push_file_task_entity SET last_push_timestamp = :timestamp WHERE id = :id")
    fun setLastPushTimestamp(id: Int, timestamp: Long = System.currentTimeMillis()): Int

    /**
     * Mark a task as in progress (updates last_push_timestamp)
     * @param id Task ID
     * @param timestamp Timestamp to set (defaults to current time)
     * @return The number of rows updated
     */
    @Query("UPDATE push_file_task_entity SET last_push_timestamp = :timestamp WHERE id = :id")
    fun markInProgress(id: Int, timestamp: Long = System.currentTimeMillis()): Int

    /**
     * Mark a task as completed (by deleting it)
     * @param id Task ID
     * @return The number of rows deleted
     */
    @Query("DELETE FROM push_file_task_entity WHERE id = :id")
    fun markCompleted(id: Int): Int

    /**
     * Mark a task as failed
     * @param id Task ID
     * @param errorMessage Error message
     * @param timestamp Timestamp to set (defaults to current time)
     * @return The number of rows updated
     */
    @Query("UPDATE push_file_task_entity SET last_push_timestamp = :timestamp, error_message = :errorMessage WHERE id = :id")
    fun markFailed(id: Int, errorMessage: String, timestamp: Long = System.currentTimeMillis()): Int

    /**
     * Increment the retry count for a task
     * @param id Task ID
     * @return The number of rows updated
     */
    @Query("UPDATE push_file_task_entity SET retry_count = retry_count + 1 WHERE id = :id")
    fun incrementRetry(id: Int): Int

    // Removed explicit override declarations for basic CRUD operations (delete, deleteAll, insert, insertAll, update, updateAll)
    // as they are inherited from BaseDao<PushFileTaskEntity> and were causing name clashes.
    // Room will correctly generate implementations based on BaseDao.
}
