package com.hawkshaw.library.database.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Migration from version 1 to 2
 * - Adds the remote_config table for storing remote configuration values
 */
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        // Create the remote_config table
        db.execSQL("""
            CREATE TABLE IF NOT EXISTS `remote_config` (
                `config_key` TEXT NOT NULL, 
                `config_value` TEXT NOT NULL, 
                `last_updated` INTEGER NOT NULL, 
                PRIMARY KEY(`config_key`)
            )
        """.trimIndent())
        
        // Create an index on the key for faster lookups
        db.execSQL(
            "CREATE INDEX IF NOT EXISTS `index_remote_config_config_key` ON `remote_config` (`config_key`)"
        )
    }
}
