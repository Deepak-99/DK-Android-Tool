package com.hawkshaw.library.datalayer.room

import androidx.room.Room
import com.hawkshaw.library.App

/**
 * Provides a singleton instance of the Room database
 */
object AppDatabaseKt {
    private const val DATABASE_NAME = "hawkshaw-db"

    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            App.getContext(),
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
} 