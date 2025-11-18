package com.hawkshaw.library.datalayer.room.telephony

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for telephony-related entities (call blocking and recording)
 */
@Dao
interface TelephonyDao {
    /**
     * Get all phone numbers to be blocked
     * @return Flow of list of call block entities
     */
    @Transaction
    @Query("SELECT * FROM call_block_entity ORDER BY name ASC")
    fun getCallBlockNumbers(): Flow<List<CallBlockEntity>>
    
    /**
     * Get all phone numbers to be recorded
     * @return Flow of list of call record entities
     */
    @Transaction
    @Query("SELECT * FROM call_record_entity ORDER BY name ASC")
    fun getCallRecordNumbers(): Flow<List<CallRecordEntity>>
    
    /**
     * Delete all call block numbers
     * @return The number of rows deleted
     */
    @Query("DELETE FROM call_block_entity")
    fun nukeCallBlockNumbers(): Int
    
    /**
     * Delete all call record numbers
     * @return The number of rows deleted
     */
    @Query("DELETE FROM call_record_entity")
    fun nukeCallRecordNumbers(): Int
    
    /**
     * Save call block numbers
     * @param list List of call block entities to save
     * @return List of row IDs for the inserted entities
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCallBlockNumbers(list: List<CallBlockEntity>): List<Long>
    
    /**
     * Save call record numbers
     * @param list List of call record entities to save
     * @return List of row IDs for the inserted entities
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCallRecordNumbers(list: List<CallRecordEntity>): List<Long>

    /**
     * Delete a single call block entity
     */
    @Delete
    fun deleteCallBlockNumber(entity: CallBlockEntity): Int

    /**
     * Delete multiple call block entities
     */
    @Delete
    fun deleteCallBlockNumbers(entities: List<CallBlockEntity>): Int

    /**
     * Delete a single call record entity
     */
    @Delete
    fun deleteCallRecordNumber(entity: CallRecordEntity): Int

    /**
     * Delete multiple call record entities
     */
    @Delete
    fun deleteCallRecordNumbers(entities: List<CallRecordEntity>): Int

    /**
     * Update a single call block entity
     */
    @Update
    fun updateCallBlockNumber(entity: CallBlockEntity): Int

    /**
     * Update multiple call block entities
     */
    @Update
    fun updateCallBlockNumbers(entities: List<CallBlockEntity>): Int

    /**
     * Update a single call record entity
     */
    @Update
    fun updateCallRecordNumber(entity: CallRecordEntity): Int

    /**
     * Update multiple call record entities
     */
    @Update
    fun updateCallRecordNumbers(entities: List<CallRecordEntity>): Int
} 