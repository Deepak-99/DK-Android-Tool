package com.hawkshaw.library.database

import android.content.Context
import android.util.Log // Added import
import androidx.room.*
import androidx.room.migration.Migration
import com.hawkshaw.library.database.migrations.MIGRATION_1_2

private const val TAG = "DatabaseManager" // Added TAG for DatabaseManager
private const val APP_DB_TAG = "AppDatabase" // Added TAG for AppDatabase related operations

object DatabaseManager {
    private var instance: AppDatabase? = null
    private lateinit var applicationContext: Context

    fun init(context: Context) {
        Log.d(TAG, "init() called.")
        applicationContext = context.applicationContext
        Log.i(TAG, "DatabaseManager initialized with application context.")
    }

    fun createDatabase(context: Context, name: String): AppDatabase {
        Log.d(TAG, "createDatabase() called for DB name: $name")
        // Check if an instance with a different name is already active
        if (instance != null && !instance!!.isOpen) { // If it's closed, it's safe to create a new one
            Log.w(TAG, "Existing database instance was closed. Creating new instance.")
            instance = null // Allow re-creation
        } else if (instance != null && instance!!.openHelper.databaseName != name) {
            Log.w(TAG, "Attempting to create a database with name '$name', but an instance with name '${instance!!.openHelper.databaseName}' already exists and is open. Returning existing instance.")
            // Decide on behavior: throw error, close old, or return old. For now, returning existing.
            return instance!!
        }

        return instance ?: synchronized(this) {
            instance ?: run {
                Log.i(TAG, "Creating new AppDatabase instance with name: $name")
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    name
                )
                .addMigrations(MIGRATION_1_2)
                .addCallback(object : RoomDatabase.Callback() { // Added Callback for logging
                    override fun onCreate(db: androidx.sqlite.db.SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Log.i(APP_DB_TAG, "Database '$name' onCreate called. Path: ${db.path}")
                    }

                    override fun onOpen(db: androidx.sqlite.db.SupportSQLiteDatabase) {
                        super.onOpen(db)
                        Log.i(APP_DB_TAG, "Database '$name' onOpen called. Path: ${db.path}")
                    }

                    override fun onDestructiveMigration(db: androidx.sqlite.db.SupportSQLiteDatabase) {
                        super.onDestructiveMigration(db)
                        Log.w(APP_DB_TAG, "Database '$name' onDestructiveMigration called. Path: ${db.path}")
                    }
                }).build().also {
                    Log.i(TAG, "AppDatabase instance '$name' created successfully.")
                    instance = it
                }
            }
        }
    }

    fun addMigration(migration: Migration) {
        Log.d(TAG, "addMigration() called with migration: $migration")
        if (!::applicationContext.isInitialized) {
            Log.e(TAG, "Database not initialized. Call init() first.")
            throw IllegalStateException("Database not initialized. Call init() first.")
        }
        // This method of adding migrations usually implies re-creating or re-configuring the instance.
        // It's tricky if the DB is already open. Standard practice is to define all migrations at builder time.
        // For simplicity, let's assume this is called before the DB is heavily used or opened.
        Log.w(TAG, "addMigration called. This will attempt to re-initialize the database instance 'database' with the new migration.")
        instance = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "database" // Assuming a default name "database" if migrations are added this way
        ).addMigrations(migration)
            .build()
        Log.i(TAG, "Database instance reconfigured with new migration for 'database'.")
    }

    // Generic DAO operations with logging
    suspend fun <T> insert(database: AppDatabase, data: T) {
        Log.d(TAG, "insert() called for database: ${database.openHelper.databaseName}, data: $data")
        withDatabaseContext(database.openHelper.databaseName) {
            when (data) {
                is AppDatabase.TestEntity -> database.testDao().insert(data)
                else -> {
                    Log.e(TAG, "Unsupported entity type for insert: ${data?.let { it::class.java.name }}")
                    throw IllegalArgumentException("Unsupported entity type for insert")
                }
            }
            Log.i(TAG, "Data inserted successfully into ${database.openHelper.databaseName}: $data")
        }
    }

    suspend fun <T> update(database: AppDatabase, data: T): Boolean {
        Log.d(TAG, "update() called for database: ${database.openHelper.databaseName}, data: $data")
        return withDatabaseContext(database.openHelper.databaseName) {
            val result = when (data) {
                is AppDatabase.TestEntity -> database.testDao().update(data) > 0
                else -> {
                    Log.e(TAG, "Unsupported entity type for update: ${data?.let { it::class.java.name }}")
                    throw IllegalArgumentException("Unsupported entity type for update")
                }
            }
            if (result) {
                Log.i(TAG, "Data updated successfully in ${database.openHelper.databaseName}: $data")
            } else {
                Log.w(TAG, "Data update failed or found no matching row in ${database.openHelper.databaseName}: $data")
            }
            result
        }
    }

    suspend fun <T> delete(database: AppDatabase, data: T): Boolean {
        Log.d(TAG, "delete() called for database: ${database.openHelper.databaseName}, data: $data")
        return withDatabaseContext(database.openHelper.databaseName) {
            val result = when (data) {
                is AppDatabase.TestEntity -> database.testDao().delete(data) > 0
                else -> {
                    Log.e(TAG, "Unsupported entity type for delete: ${data?.let { it::class.java.name }}")
                    throw IllegalArgumentException("Unsupported entity type for delete")
                }
            }
            if (result) {
                Log.i(TAG, "Data deleted successfully from ${database.openHelper.databaseName}: $data")
            } else {
                Log.w(TAG, "Data delete failed or found no matching row in ${database.openHelper.databaseName}: $data")
            }
            result
        }
    }

    // Query execution with logging
    suspend fun <T> query(database: AppDatabase, queryName: String, block: suspend (AppDatabase) -> T): T {
        Log.d(TAG, "query() called for database: ${database.openHelper.databaseName}, queryName: $queryName")
        return withDatabaseContext(database.openHelper.databaseName) {
            try {
                val result = block(database)
                Log.i(TAG, "Query '$queryName' executed successfully on ${database.openHelper.databaseName}. Result: $result")
                result
            } catch (e: Exception) {
                Log.e(TAG, "Error executing query '$queryName' on ${database.openHelper.databaseName}", e)
                throw e
            }
        }
    }

    // Transaction execution with logging
    suspend fun transaction(database: AppDatabase, operationName: String, block: suspend () -> Unit) {
        Log.d(TAG, "transaction() called for database: ${database.openHelper.databaseName}, operationName: $operationName")
        // Note: Room handles transactions for @Transaction annotated DAO methods automatically.
        // This is for manual transaction control if needed outside DAOs.
        withDatabaseContext(database.openHelper.databaseName) {
            database.beginTransaction()
            Log.i(TAG, "Transaction '$operationName' started on ${database.openHelper.databaseName}.")
            try {
                block()
                database.setTransactionSuccessful()
                Log.i(TAG, "Transaction '$operationName' successful on ${database.openHelper.databaseName}.")
            } catch (e: Exception) {
                Log.e(TAG, "Error during transaction '$operationName' on ${database.openHelper.databaseName}", e)
                throw e // Re-throw after logging
            } finally {
                database.endTransaction()
                Log.i(TAG, "Transaction '$operationName' ended on ${database.openHelper.databaseName}.")
            }
        }
    }

    /**
     * Helper function to provide context for logging within suspend functions.
     * Could be expanded for MDC (Mapped Diagnostic Context) if using a more advanced logging library.
     */
    private suspend fun <R> withDatabaseContext(dbName: String?, block: suspend () -> R): R {
        // This is a placeholder for context. In a real scenario, you might use MDC with Logback/SLF4J
        // For Android Log, context is usually just the TAG.
        // Log.d(TAG, "[DB:$dbName]") // Example of adding context
        return block()
    }
}

@androidx.room.Database(
    entities = [
        AppDatabase.TestEntity::class,
        AppDatabase.RemoteConfigEntity::class
    ], 
    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun testDao(): TestDao
    abstract fun remoteConfigDao(): RemoteConfigDao

    // Companion object for AppDatabase specific logging or constants if needed
    // companion object {
    //     private const val TAG = "AppDatabase"
    // }

    @Entity
    data class TestEntity(
        @PrimaryKey val id: Int,
        @ColumnInfo(name = "name") val name: String
    ) {
        // toString() is implicitly used by Logcat, but can be overridden for more specific log output
        override fun toString(): String {
            return "TestEntity(id=$id, name='$name')"
        }
    }

    @Dao
    interface TestDao {
        @Insert
        suspend fun insert(entity: TestEntity) // Room handles logging for generated code if enabled

        @Update
        suspend fun update(entity: TestEntity): Int

        @Delete
        suspend fun delete(entity: TestEntity): Int

        @Query("SELECT * FROM TestEntity")
        suspend fun getAll(): List<TestEntity>

        // Example of a @Transaction method, Room handles its atomicity.
    }

    @Entity(tableName = "remote_config")
    data class RemoteConfigEntity(
        @PrimaryKey
        @ColumnInfo(name = "config_key") val key: String,
        @ColumnInfo(name = "config_value") val value: String,
        @ColumnInfo(name = "last_updated") val lastUpdated: Long = System.currentTimeMillis()
    ) {
        override fun toString(): String {
            return "RemoteConfigEntity(key='$key', value='$value', lastUpdated=$lastUpdated)"
        }
    }

    @Dao
    interface RemoteConfigDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insert(config: RemoteConfigEntity)

        @Query("SELECT * FROM remote_config WHERE config_key = :key")
        suspend fun getValue(key: String): RemoteConfigEntity?

        @Query("SELECT COUNT(*) FROM remote_config")
        suspend fun getCount(): Int

        @Query("DELETE FROM remote_config WHERE config_key = :key")
        suspend fun delete(key: String)

        @Query("DELETE FROM remote_config")
        suspend fun deleteAll()
    }
}
