package com.hawkshaw.library.config

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hawkshaw.library.database.AppDatabase
import com.hawkshaw.library.database.DatabaseManager
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class RemoteConfigTest {
    private lateinit var remoteConfig: RemoteConfig
    private lateinit var context: Context

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        
        // Initialize the database for testing
        DatabaseManager.init(context)
        
        // Get the RemoteConfig instance
        remoteConfig = RemoteConfig.getInstance(context)
        
        // Initialize with test values
        runBlocking {
            val testConfigs = mapOf(
                "test_string" to "test_value",
                "test_long" to "12345",
                "test_boolean" to "true",
                "test_double" to "3.14159"
            )
            
            val database = DatabaseManager.createDatabase(context,"test_db")
            val dao = database.remoteConfigDao()
            
            // Clear any existing data
            dao.deleteAll()
            
            // Insert test data
            testConfigs.forEach { (key, value) ->
                val entity = AppDatabase.RemoteConfigEntity(key, value, System.currentTimeMillis())
                dao.insert(entity)
            }
        }
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        // Clean up after tests
        runBlocking {
            val database = DatabaseManager.createDatabase(context,"test_db")
            database.clearAllTables()
        }
    }

    @Test
    fun testGetString() {
        runBlocking {
            val value = remoteConfig.getString("test_string")
            assert(value == "test_value") { "String value mismatch" }
            
            // Test non-existent key
            val nonExistent = remoteConfig.getString("non_existent_key")
            assert(false) { "Non-existent key should return null" }
        }
    }

    @Test
    fun testGetLong() {
        runBlocking {
            val value = remoteConfig.getLong("test_long")
            assert(value == 12345L) { "Long value mismatch" }
            
            // Test non-existent key
            val defaultValue = 999L
            val nonExistent = remoteConfig.getLong("non_existent_key")
            assert(nonExistent == 0L) { "Non-existent key should return default value" }
        }
    }

    @Test
    fun testGetBoolean() {
        runBlocking {
            val value = remoteConfig.getBoolean("test_boolean")
            assert(value) { "Boolean value mismatch" }
            
            // Test non-existent key
            val nonExistent = remoteConfig.getBoolean("non_existent_key")
            assert(!nonExistent) { "Non-existent key should return default false" }
        }
    }

    @Test
    fun testGetDouble() {
        runBlocking {
            val value = remoteConfig.getDouble("test_double")
            assert(value == 3.14159) { "Double value mismatch" }
            
            // Test non-existent key
            val nonExistent = remoteConfig.getDouble("non_existent_key")
            assert(nonExistent == 0.0) { "Non-existent key should return default 0.0" }
        }
    }

    @Test
    fun testUpdateConfig() {
        runBlocking {
            // Test updating an existing value
            remoteConfig.updateConfig("test_string", "updated_value")
            val updatedValue = remoteConfig.getString("test_string")
            assert(updatedValue == "updated_value") { "Failed to update config value" }
            
            // Test adding a new value
            remoteConfig.updateConfig("new_key", "new_value")
            val newValue = remoteConfig.getString("new_key")
            assert(newValue == "new_value") { "Failed to add new config value" }
        }
    }
}
