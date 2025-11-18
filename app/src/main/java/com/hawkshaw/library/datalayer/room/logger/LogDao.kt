package com.hawkshaw.library.datalayer.room.logger

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface LogDao {
    @Query("SELECT * FROM logs ORDER BY timestamp DESC")
    fun getAllLogs(): Flow<List<LogEntity>>

    @Query("SELECT * FROM logs WHERE pushed = 0 ORDER BY timestamp ASC")
    fun getUnpushedLogs(): Flow<List<LogEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(log: LogEntity)

    @Update
    suspend fun update(log: LogEntity)

    @Delete
    suspend fun delete(log: LogEntity)

    @Query("DELETE FROM logs WHERE pushed = 1")
    suspend fun deletePushedLogs()

    @Query("UPDATE logs SET pushed = 1 WHERE id = :id")
    suspend fun markAsPushed(id: Long)
} 