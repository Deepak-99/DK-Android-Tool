package com.hawkshaw.library.config

import android.content.Context
import android.util.Log
import com.hawkshaw.library.database.AppDatabase
import com.hawkshaw.library.database.DatabaseManager
import com.hawkshaw.library.logger.Logger // Assuming you have this
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import com.hawkshaw.library.preferences.Prefs

private const val DEBUG_TAG = "RemoteConfig_DEBUG"

class RemoteConfig private constructor(context: Context) {
    companion object {
        private const val TAG = "RemoteConfig"
        private const val KEY_HTTP_BASE_URL = "http_base_url"
        private const val KEY_DELAY_AFTER_LOGIN = "delay_after_login_ms"
        private const val KEY_EXCEPTION_COUNTER_RESET_DELAY =
            "exception_handler_exception_counter_reset_delay_in_millis"
        private const val KEY_GRPC_CHANNEL_NAME = "grpc_channel_name"
        private const val KEY_GRPC_CHANNEL_PORT = "grpc_channel_port"
        private const val KEY_MAX_FREQUENT_EXCEPTION =
            "exception_handler_max_frequent_exception_allowed_count"
        private const val KEY_PUSH_LOGS_THRESHOLD = "push_logs_threshold"
        private const val KEY_PUSHY_APP_ID = "pushy_app_id"
        private const val KEY_TUS_ENDPOINT = "tus_endpoint"
        private const val KEY_WEBSOCKET_URI = "web_socket_uri"

        @Volatile
        private var instance: RemoteConfig? = null

        fun getInstance(context: Context): RemoteConfig {
            Log.d(DEBUG_TAG, "getInstance() called.")
            return instance ?: synchronized(this) {
                instance ?: RemoteConfig(context.applicationContext).also {
                    Log.i(DEBUG_TAG, "New RemoteConfig instance created.")
                    instance = it
                    // it.initDefaults() // Consider calling initDefaults here
                }
            }
        }
    }

    private val defaultValues: Map<String, Any> = mapOf(
        KEY_HTTP_BASE_URL to "http://10.0.2.2:3000",
        KEY_DELAY_AFTER_LOGIN to 500L,
        KEY_EXCEPTION_COUNTER_RESET_DELAY to 500L,
        KEY_GRPC_CHANNEL_NAME to "10.0.2.2",
        KEY_GRPC_CHANNEL_PORT to 3000, // Make sure this matches getLong/getInt expectations
        KEY_MAX_FREQUENT_EXCEPTION to 5L,
        KEY_PUSH_LOGS_THRESHOLD to 10L,
        KEY_PUSHY_APP_ID to "",
        KEY_TUS_ENDPOINT to "http://10.0.2.2:3000/files/upload",
        KEY_WEBSOCKET_URI to "ws://10.0.2.2:3000"
    )

    // Reference to the main AppDatabase
    private val appDatabase: AppDatabase // Changed name for clarity from just 'database'

    // Reference to the nested RemoteConfigDao
    // This is the CRITICAL CHANGE
    private val remoteConfigDao: AppDatabase.RemoteConfigDao

    init {
        Log.d(DEBUG_TAG, "RemoteConfig initializing...")
        try {
            // DatabaseManager.init(context.applicationContext) // Call if DatabaseManager truly needs separate init
            appDatabase = DatabaseManager.createDatabase(context.applicationContext, "hawkshaw_remote_config_db") // Or your desired DB name
            remoteConfigDao = appDatabase.remoteConfigDao() // Get the DAO from the AppDatabase instance
            Log.i(TAG, "Database and RemoteConfigDao initialized successfully in RemoteConfig constructor")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to initialize database in RemoteConfig constructor", e)
            throw IllegalStateException("Failed to initialize database for RemoteConfig", e)
        }
    }

    fun getString(key: String): String {
        return runBlocking(Dispatchers.IO) {
            try {
                // getValue will return AppDatabase.RemoteConfigEntity?
                val entity: AppDatabase.RemoteConfigEntity? = remoteConfigDao.getValue(key)
                entity?.value ?: (defaultValues[key] as? String ?: "")
            } catch (e: Exception) {
                Log.e(TAG, "Error getting string value for key: $key. Returning default.", e)
                (defaultValues[key] as? String ?: "")
            }
        }
    }

    fun getLong(key: String): Long {
        Log.d(DEBUG_TAG, "getLong() called for key: '$key'")
        return runBlocking(Dispatchers.IO) {
            try {
                val entity: AppDatabase.RemoteConfigEntity? = remoteConfigDao.getValue(key)
                val value = entity?.value?.toLongOrNull() ?: (defaultValues[key] as? Long) ?: 0L
                Log.d(DEBUG_TAG, "getLong for key: '$key' retrieved value: $value")
                value
            } catch (e: Exception) {
                Log.e(DEBUG_TAG, "getLong for key: '$key' failed. Exception: ${e.message}", e)
                Logger.e(TAG, "Failed to get long value for key: $key", e, false, 12, null)
                (defaultValues[key] as? Long) ?: 0L
            }
        }
    }
    fun getBoolean(key: String): Boolean {
        Log.d(DEBUG_TAG, "getBoolean() called for key: '$key'")
        return try {
            val value = getString(key)
            value.toBoolean()
        } catch (e: Exception) {
            Log.e(DEBUG_TAG, "getBoolean for key: '$key' failed. Exception: ${e.message}", e)
            Logger.e(TAG, "Failed to get boolean value for key: $key", e, false, 12, null)
            (defaultValues[key] as? Boolean) ?: false
        }
    }
    // --- Your other getter methods (getBaseUrl, etc.) would remain similar ---
    // Make sure they use getString or getLong which now correctly reference the types

    fun getBaseUrl(): String {
        Log.d(DEBUG_TAG, "getBaseUrl() called, retrieving key: '$KEY_HTTP_BASE_URL'")
        val value = getString(KEY_HTTP_BASE_URL)
        Log.d(DEBUG_TAG, "getBaseUrl() returning: '$value'")
        return value
    }

    fun getDelayAfterLoginMs(): Long {
        Log.d(DEBUG_TAG, "getDelayAfterLoginMs() called, retrieving key: '$KEY_DELAY_AFTER_LOGIN'")
        val value = getLong(KEY_DELAY_AFTER_LOGIN)
        Log.d(DEBUG_TAG, "getDelayAfterLoginMs() returning: $value")
        return value
    }

    fun getExceptionCounterResetDelayInMillis(): Long {
        Log.d(
            DEBUG_TAG,
            "getExceptionCounterResetDelayInMillis() called, retrieving key: '$KEY_EXCEPTION_COUNTER_RESET_DELAY'"
        )
        val value = getLong(KEY_EXCEPTION_COUNTER_RESET_DELAY)
        Log.d(DEBUG_TAG, "getExceptionCounterResetDelayInMillis() returning: $value")
        return value
    }

    fun getGrpcName(): String {
        Log.d(DEBUG_TAG, "getGrpcName() called, retrieving key: '$KEY_GRPC_CHANNEL_NAME'")
        val value = getString(KEY_GRPC_CHANNEL_NAME)
        Log.d(DEBUG_TAG, "getGrpcName() returning: '$value'")
        return value
    }

    fun getGrpcPort(): Int {
        Log.d(DEBUG_TAG, "getGrpcPort() called, retrieving key: '$KEY_GRPC_CHANNEL_PORT'")
        // getLong will return the default (0L or from map) if not found or error
        val value = getLong(KEY_GRPC_CHANNEL_PORT).toInt()
        Log.d(DEBUG_TAG, "getGrpcPort() returning: $value")
        return value
    }

    fun getMaxFrequentExceptionAllowedCount(): Long {
        Log.d(
            DEBUG_TAG,
            "getMaxFrequentExceptionAllowedCount() called, retrieving key: '$KEY_MAX_FREQUENT_EXCEPTION'"
        )
        val value = getLong(KEY_MAX_FREQUENT_EXCEPTION)
        Log.d(DEBUG_TAG, "getMaxFrequentExceptionAllowedCount() returning: $value")
        return value
    }

    fun getPushLogsThreshold(): Long {
        Log.d(
            DEBUG_TAG,
            "getPushLogsThreshold() called, retrieving key: '$KEY_PUSH_LOGS_THRESHOLD'"
        )
        val value = getLong(KEY_PUSH_LOGS_THRESHOLD)
        Log.d(DEBUG_TAG, "getPushLogsThreshold() returning: $value")
        return value
    }

    fun getPushyAppId(): String {
        Log.d(DEBUG_TAG, "getPushyAppId() called, retrieving key: '$KEY_PUSHY_APP_ID'")
        val value = getString(KEY_PUSHY_APP_ID)
        Log.d(DEBUG_TAG, "getPushyAppId() returning: '$value'")
        return value
    }

    fun getTusEndpoint(): String {
        Log.d(DEBUG_TAG, "getTusEndpoint() called, retrieving key: '$KEY_TUS_ENDPOINT'")
        val value = getString(KEY_TUS_ENDPOINT)
        Log.d(DEBUG_TAG, "getTusEndpoint() returning: '$value'")
        return value
    }

    fun getWebsocketUri(): String {
        Log.d(DEBUG_TAG, "getWebsocketUri() called, retrieving key: '$KEY_WEBSOCKET_URI'")
        val value = getString(KEY_WEBSOCKET_URI)
        Log.d(DEBUG_TAG, "getWebsocketUri() returning: '$value'")
        return value
    }

    fun initDefaults() {
        Log.i(DEBUG_TAG, "initDefaults() called: Setting default config values in database.")
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val existingCount = remoteConfigDao.getCount()
                if (existingCount == 0) {
                    Log.d(DEBUG_TAG, "No existing config values found. Inserting defaults.")
                    defaultValues.forEach { (defaultKey, defaultValue) ->
                        try {
                            // Create an instance of the nested RemoteConfigEntity
                            val entity = AppDatabase.RemoteConfigEntity(
                                key = defaultKey,
                                value = defaultValue.toString(),
                                lastUpdated = System.currentTimeMillis()
                            )
                            remoteConfigDao.insert(entity)
                        } catch (e: Exception) {
                            Log.e(DEBUG_TAG, "Failed to insert default value for key: $defaultKey", e)
                        }
                    }
                    Log.i(DEBUG_TAG, "Successfully inserted ${defaultValues.size} default config values.")
                } else {
                    Log.d(DEBUG_TAG, "Found $existingCount existing config values. Checking for missing defaults.")
                    defaultValues.forEach { (defaultKey, defaultValue) ->
                        try {
                            if (remoteConfigDao.getValue(defaultKey) == null) {
                                val entity = AppDatabase.RemoteConfigEntity(
                                    key = defaultKey,
                                    value = defaultValue.toString(),
                                    lastUpdated = System.currentTimeMillis()
                                )
                                remoteConfigDao.insert(entity)
                                Log.d(DEBUG_TAG, "Added missing default value for key: $defaultKey")
                            }
                        } catch (e: Exception) {
                            Log.e(DEBUG_TAG, "Failed to check/insert default value for key: $defaultKey", e)
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e(DEBUG_TAG, "Failed to initialize config values. Exception: ${e.message}", e)
                Logger.e(TAG, "Failed to initialize config values", e, false, 12, null)
            }
        }
    }

    fun updateConfig(key: String, value: String) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                // Create an instance of the nested RemoteConfigEntity
                val entity = AppDatabase.RemoteConfigEntity(
                    key = key,
                    value = value,
                    lastUpdated = System.currentTimeMillis()
                )
                remoteConfigDao.insert(entity) // Uses OnConflictStrategy.REPLACE
                Log.d(DEBUG_TAG, "Updated config value for key: $key")
            } catch (e: Exception) {
                Log.e(DEBUG_TAG, "Failed to update config value for key: $key. Exception: ${e.message}", e)
                Logger.e(TAG, "Failed to update config value for key: $key", e, false, 12, null)
            }
        }
    }
}
