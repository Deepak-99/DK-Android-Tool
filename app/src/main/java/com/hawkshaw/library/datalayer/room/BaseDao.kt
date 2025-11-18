package com.hawkshaw.library.datalayer.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

/**
 * Base Data Access Object interface for all Room DAOs
 * @param T the entity type that must be annotated with @Entity
 */
@Dao
interface BaseDao<T> {
    /**
     * Delete a single entity
     * @return The number of rows deleted
     */
    @Delete
    fun delete(entity: T): Int

    /**
     * Delete multiple entities
     * @return The number of rows deleted
     */
    @Delete
    fun deleteAll(entities: List<T>): Int

    /**
     * Insert a single entity
     * @return The row ID of the newly inserted entity
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: T): Long

    /**
     * Insert multiple entities
     * @return Array of row IDs for the inserted entities
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(entities: List<T>): List<Long>

    /**
     * Update a single entity
     * @return The number of rows updated
     */
    @Update
    fun update(entity: T): Int

    /**
     * Update multiple entities
     * @return The number of rows updated
     */
    @Update
    fun updateAll(entities: List<T>): Int

    /**
     * Delete a single entity synchronously
     * @return The number of rows deleted
     */
    @Delete
    fun deleteSync(entity: T): Int

    /**
     * Delete multiple entities synchronously
     * @return The number of rows deleted
     */
    @Delete
    fun deleteAllSync(entities: List<T>): Int

    /**
     * Insert a single entity synchronously
     * @return The row ID of the newly inserted entity
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSync(entity: T): Long

    /**
     * Insert multiple entities synchronously
     * @return Array of row IDs for the inserted entities
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllSync(entities: List<T>): List<Long>

    /**
     * Update a single entity synchronously
     * @return The number of rows updated
     */
    @Update
    fun updateSync(entity: T): Int

    /**
     * Update multiple entities synchronously
     * @return The number of rows updated
     */
    @Update
    fun updateAllSync(entities: List<T>): Int
} 