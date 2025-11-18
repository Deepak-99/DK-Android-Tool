package com.hawkshaw.library.preferences

import android.content.Context
import android.content.SharedPreferences
import com.hawkshaw.library.App
import kotlin.properties.ReadWriteProperty
import android.util.Log // Added for logging
import androidx.core.content.edit

/**
 * Shared preferences helper for the app
 */
object Prefs {
    private const val TAG = "PrefsObject" // Added for logging

    private const val PREF_NAME = "hawkshaw_prefs"
    private const val KEY_INIT_FLAG = "init_flag"
    private const val KEY_PHONE_NUMBER = "phone_number"
    private const val KEY_TOKEN = "token"
    private const val KEY_REFRESH_TOKEN = "refreshToken"
    private const val KEY_DYNAMIC_CONFIG = "dynamic_config"
    private const val KEY_EMAIL = "email"
    private const val KEY_LAST_PUSH_DATA_TIME = "lastPushDataTime"
    private const val KEY_CALL_RECORD_SETTINGS = "call_record_settings"

    // Make this a lateinit to allow initialization later
    lateinit var sharedPrefs: SharedPreferences
        private set

    // Property delegates for preferences
    // Logging for delegate creation is in ExtKt.kt
    // Logging for delegate get/set is in PreferenceProperty.kt & ExtKt.kt
    private val dynamicConfig by lazy {
        Log.d(TAG, "[DEBUG] Lazy init for dynamicConfig delegate. Key: \"$KEY_DYNAMIC_CONFIG\"")
        stringPreference(KEY_DYNAMIC_CONFIG)
    }
    
    private val phoneNumber by lazy { 
        Log.d(TAG, "[DEBUG] Lazy init for phoneNumber delegate. Key: \"$KEY_PHONE_NUMBER\", Default: \"\"")
        stringPreference(KEY_PHONE_NUMBER, "")
    }
    
    val initFlag by lazy {
        Log.d(TAG, "[DEBUG] Lazy init for initFlag delegate. Key: \"$KEY_INIT_FLAG\", Default: false")
        booleanPreference(KEY_INIT_FLAG, false)
    }
    
    private val token by lazy {
        Log.d(TAG, "[DEBUG] Lazy init for token delegate. Key: \"$KEY_TOKEN\"")
        stringPreference(KEY_TOKEN)
    }
    
    private val refreshToken by lazy {
        Log.d(TAG, "[DEBUG] Lazy init for refreshToken delegate. Key: \"$KEY_REFRESH_TOKEN\"")
        stringPreference(KEY_REFRESH_TOKEN)
    }
    
    private val email by lazy {
        Log.d(TAG, "[DEBUG] Lazy init for email delegate. Key: \"$KEY_EMAIL\"")
        stringPreference(KEY_EMAIL)
    }
    
    val lastPushDataTime by lazy {
        Log.d(TAG, "[DEBUG] Lazy init for lastPushDataTime delegate. Key: \"$KEY_LAST_PUSH_DATA_TIME\", Default: 0L")
        longPreference(KEY_LAST_PUSH_DATA_TIME, 0L)
    }
    
    val callRecordSettings by lazy {
        Log.d(TAG, "[DEBUG] Lazy init for callRecordSettings delegate. Key: \"$KEY_CALL_RECORD_SETTINGS\", Default: 0")
        intPreference(KEY_CALL_RECORD_SETTINGS, 0)
    }
    
    /**
     * Initialize shared preferences with the application context
     */
    fun init(context: Context) {
        Log.d(TAG, "[DEBUG] init called. Context: $context, PrefName: \"$PREF_NAME\"")
        sharedPrefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        Log.d(TAG, "[DEBUG] init: SharedPreferences initialized.")
    }
    
    /**
     * Get whether initialization is complete
     */
    fun getInitFlag(): Boolean {
        Log.d(TAG, "[DEBUG] getInitFlag called for key \"$KEY_INIT_FLAG\".")
        // Delegate getter will log the actual value retrieval
        val value = initFlag.getValue(this, ::initFlag) ?: false
        Log.d(TAG, "[DEBUG] getInitFlag for key \"$KEY_INIT_FLAG\" returning: $value")
        return value
    }
    
    /**
     * Set whether initialization is complete
     */
    fun setInitFlag(value: Boolean) {
        Log.d(TAG, "[DEBUG] setInitFlag called for key \"$KEY_INIT_FLAG\" with value: $value")
        // Delegate setter will log the actual value write
        initFlag.setValue(this, ::initFlag, value)
        Log.d(TAG, "[DEBUG] setInitFlag for key \"$KEY_INIT_FLAG\" completed.")
    }
    
    /**
     * Get the stored phone number
     */
    fun getPhoneNumber(): String {
        Log.d(TAG, "[DEBUG] getPhoneNumber called for key \"$KEY_PHONE_NUMBER\".")
        val value = phoneNumber.getValue(this, ::phoneNumber) ?: ""
        Log.d(TAG, "[DEBUG] getPhoneNumber for key \"$KEY_PHONE_NUMBER\" returning: \"$value\"")
        return value
    }
    
    /**
     * Set the stored phone number
     */
    fun setPhoneNumber(value: String?) {
        Log.d(TAG, "[DEBUG] setPhoneNumber called for key \"$KEY_PHONE_NUMBER\" with value: \"$value\"")
        phoneNumber.setValue(this, ::phoneNumber, value)
        Log.d(TAG, "[DEBUG] setPhoneNumber for key \"$KEY_PHONE_NUMBER\" completed.")
    }
    
    /**
     * Get dynamic configuration data
     */
    fun getDynamicConfig(): String? {
        Log.d(TAG, "[DEBUG] getDynamicConfig called for key \"$KEY_DYNAMIC_CONFIG\".")
        val value = dynamicConfig.getValue(this, ::dynamicConfig)
        Log.d(TAG, "[DEBUG] getDynamicConfig for key \"$KEY_DYNAMIC_CONFIG\" returning: \"$value\"")
        return value
    }
    
    /**
     * Set dynamic configuration data
     */
    fun setDynamicConfig(value: String?) {
        Log.d(TAG, "[DEBUG] setDynamicConfig called for key \"$KEY_DYNAMIC_CONFIG\" with value: \"$value\"")
        dynamicConfig.setValue(this, ::dynamicConfig, value)
        Log.d(TAG, "[DEBUG] setDynamicConfig for key \"$KEY_DYNAMIC_CONFIG\" completed.")
    }
    
    /**
     * Get the authentication token
     */
    fun getToken(): String? {
        Log.d(TAG, "[DEBUG] getToken called for key \"$KEY_TOKEN\".")
        val value = token.getValue(this, ::token)
        Log.d(TAG, "[DEBUG] getToken for key \"$KEY_TOKEN\" returning: \"$value\"")
        return value
    }
    
    /**
     * Set the authentication token
     */
    fun setToken(value: String?) {
        Log.d(TAG, "[DEBUG] setToken called for key \"$KEY_TOKEN\" with value: \"$value\"")
        token.setValue(this, ::token, value)
        Log.d(TAG, "[DEBUG] setToken for key \"$KEY_TOKEN\" completed.")
    }
    
    /**
     * Get the refresh token
     */
    fun getRefreshToken(): String? {
        Log.d(TAG, "[DEBUG] getRefreshToken called for key \"$KEY_REFRESH_TOKEN\".")
        val value = refreshToken.getValue(this, ::refreshToken)
        Log.d(TAG, "[DEBUG] getRefreshToken for key \"$KEY_REFRESH_TOKEN\" returning: \"$value\"")
        return value
    }
    
    /**
     * Set the refresh token
     */
    fun setRefreshToken(value: String?) {
        Log.d(TAG, "[DEBUG] setRefreshToken called for key \"$KEY_REFRESH_TOKEN\" with value: \"$value\"")
        refreshToken.setValue(this, ::refreshToken, value)
        Log.d(TAG, "[DEBUG] setRefreshToken for key \"$KEY_REFRESH_TOKEN\" completed.")
    }
    
    /**
     * Get the email address
     */
    fun getEmail(): String? {
        Log.d(TAG, "[DEBUG] getEmail called for key \"$KEY_EMAIL\".")
        val value = email.getValue(this, ::email)
        Log.d(TAG, "[DEBUG] getEmail for key \"$KEY_EMAIL\" returning: \"$value\"")
        return value
    }
    
    /**
     * Set the email address
     */
    fun setEmail(value: String?) {
        Log.d(TAG, "[DEBUG] setEmail called for key \"$KEY_EMAIL\" with value: \"$value\"")
        email.setValue(this, ::email, value)
        Log.d(TAG, "[DEBUG] setEmail for key \"$KEY_EMAIL\" completed.")
    }
    
    /**
     * Get the last push data time
     */
    fun getLastPushDataTime(): Long {
        Log.d(TAG, "[DEBUG] getLastPushDataTime called for key \"$KEY_LAST_PUSH_DATA_TIME\".")
        val value = lastPushDataTime.getValue(this, ::lastPushDataTime) ?: 0L
        Log.d(TAG, "[DEBUG] getLastPushDataTime for key \"$KEY_LAST_PUSH_DATA_TIME\" returning: $value")
        return value
    }
    
    /**
     * Set the last push data time
     */
    fun setLastPushDataTime(value: Long) {
        Log.d(TAG, "[DEBUG] setLastPushDataTime called for key \"$KEY_LAST_PUSH_DATA_TIME\" with value: $value")
        lastPushDataTime.setValue(this, ::lastPushDataTime, value)
        Log.d(TAG, "[DEBUG] setLastPushDataTime for key \"$KEY_LAST_PUSH_DATA_TIME\" completed.")
    }
    
    /**
     * Remove a preference by key
     */
    fun remove(key: String) {
        Log.d(TAG, "[DEBUG] remove called for key: \"$key\"")
        sharedPrefs.edit { remove(key) }
        Log.d(TAG, "[DEBUG] remove for key: \"$key\" completed.")
    }
    
    /**
     * Check if a key exists in preferences
     */
    fun contains(key: String): Boolean {
        Log.d(TAG, "[DEBUG] contains called for key: \"$key\"")
        val result = sharedPrefs.contains(key)
        Log.d(TAG, "[DEBUG] contains for key: \"$key\" returning: $result")
        return result
    }
    
    /**
     * Clear all preferences
     */
    fun clear() {
        Log.d(TAG, "[DEBUG] clear called.")
        sharedPrefs.edit { clear() }
        Log.d(TAG, "[DEBUG] clear completed.")
    }
    
    /**
     * Get a boolean preference
     */
    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        Log.d(TAG, "[DEBUG] getBoolean called for key: \"$key\", DefaultValue: $defaultValue")
        val value = sharedPrefs.getBoolean(key, defaultValue)
        Log.d(TAG, "[DEBUG] getBoolean for key: \"$key\" returning: $value")
        return value
    }
    
    /**
     * Get an integer preference
     */
    fun getInt(key: String, defaultValue: Int = 0): Int {
        Log.d(TAG, "[DEBUG] getInt called for key: \"$key\", DefaultValue: $defaultValue")
        val value = sharedPrefs.getInt(key, defaultValue)
        Log.d(TAG, "[DEBUG] getInt for key: \"$key\" returning: $value")
        return value
    }
    
    /**
     * Get a float preference
     */
    fun getFloat(key: String, defaultValue: Float = 0f): Float {
        Log.d(TAG, "[DEBUG] getFloat called for key: \"$key\", DefaultValue: $defaultValue")
        val value = sharedPrefs.getFloat(key, defaultValue)
        Log.d(TAG, "[DEBUG] getFloat for key: \"$key\" returning: $value")
        return value
    }
    
    /**
     * Get a long preference
     */
    fun getLong(key: String, defaultValue: Long = 0L): Long {
        Log.d(TAG, "[DEBUG] getLong called for key: \"$key\", DefaultValue: $defaultValue")
        val value = sharedPrefs.getLong(key, defaultValue)
        Log.d(TAG, "[DEBUG] getLong for key: \"$key\" returning: $value")
        return value
    }
    
    /**
     * Get a string preference
     */
    fun getString(key: String, defaultValue: String? = null): String? {
        Log.d(TAG, "[DEBUG] getString called for key: \"$key\", DefaultValue: \"$defaultValue\"")
        val value = sharedPrefs.getString(key, defaultValue)
        Log.d(TAG, "[DEBUG] getString for key: \"$key\" returning: \"$value\"")
        return value
    }
    
    /**
     * Get a string set preference
     */
    fun getStringSet(key: String, defaultValue: Set<String>? = null): Set<String>? {
        Log.d(TAG, "[DEBUG] getStringSet called for key: \"$key\", DefaultValue: $defaultValue")
        val value = sharedPrefs.getStringSet(key, defaultValue)
        Log.d(TAG, "[DEBUG] getStringSet for key: \"$key\" returning: $value")
        return value
    }
    
    /**
     * Put a boolean preference
     */
    fun putBoolean(key: String, value: Boolean, commit: Boolean = false) {
        Log.d(TAG, "[DEBUG] putBoolean called for key: \"$key\", Value: $value, Commit: $commit")
        val editor = sharedPrefs.edit()
        editor.putBoolean(key, value)
        if (commit) {
            Log.d(TAG, "[DEBUG] putBoolean for key: \"$key\": Committing.")
            editor.apply()
        } else {
            Log.d(TAG, "[DEBUG] putBoolean for key: \"$key\": Applying.")
            editor.apply()
        }
        Log.d(TAG, "[DEBUG] putBoolean for key: \"$key\" completed.")
    }
    
    /**
     * Put an integer preference
     */
    fun putInt(key: String, value: Int, commit: Boolean = false) {
        Log.d(TAG, "[DEBUG] putInt called for key: \"$key\", Value: $value, Commit: $commit")
        val editor = sharedPrefs.edit()
        editor.putInt(key, value)
        if (commit) {
            Log.d(TAG, "[DEBUG] putInt for key: \"$key\": Committing.")
            editor.apply()
        } else {
            Log.d(TAG, "[DEBUG] putInt for key: \"$key\": Applying.")
            editor.apply()
        }
        Log.d(TAG, "[DEBUG] putInt for key: \"$key\" completed.")
    }
    
    /**
     * Put a float preference
     */
    fun putFloat(key: String, value: Float, commit: Boolean = false) {
        Log.d(TAG, "[DEBUG] putFloat called for key: \"$key\", Value: $value, Commit: $commit")
        val editor = sharedPrefs.edit()
        editor.putFloat(key, value)
        if (commit) {
            Log.d(TAG, "[DEBUG] putFloat for key: \"$key\": Committing.")
            editor.apply()
        } else {
            Log.d(TAG, "[DEBUG] putFloat for key: \"$key\": Applying.")
            editor.apply()
        }
        Log.d(TAG, "[DEBUG] putFloat for key: \"$key\" completed.")
    }
    
    /**
     * Put a long preference
     */
    fun putLong(key: String, value: Long, commit: Boolean = false) {
        Log.d(TAG, "[DEBUG] putLong called for key: \"$key\", Value: $value, Commit: $commit")
        val editor = sharedPrefs.edit()
        editor.putLong(key, value)
        if (commit) {
            Log.d(TAG, "[DEBUG] putLong for key: \"$key\": Committing.")
            editor.apply()
        } else {
            Log.d(TAG, "[DEBUG] putLong for key: \"$key\": Applying.")
            editor.apply()
        }
        Log.d(TAG, "[DEBUG] putLong for key: \"$key\" completed.")
    }
    
    /**
     * Put a string preference
     */
    fun putString(key: String, value: String?, commit: Boolean = false) {
        Log.d(TAG, "[DEBUG] putString called for key: \"$key\", Value: \"$value\", Commit: $commit")
        val editor = sharedPrefs.edit()
        editor.putString(key, value)
        if (commit) {
            Log.d(TAG, "[DEBUG] putString for key: \"$key\": Committing.")
            editor.apply()
        } else {
            Log.d(TAG, "[DEBUG] putString for key: \"$key\": Applying.")
            editor.apply()
        }
        Log.d(TAG, "[DEBUG] putString for key: \"$key\" completed.")
    }
    
    /**
     * Put a string set preference
     */
    fun putStringSet(key: String, value: Set<String>?, commit: Boolean = false) {
        Log.d(TAG, "[DEBUG] putStringSet called for key: \"$key\", Value: $value, Commit: $commit")
        val editor = sharedPrefs.edit()
        editor.putStringSet(key, value)
        if (commit) {
            Log.d(TAG, "[DEBUG] putStringSet for key: \"$key\": Committing.")
            editor.apply()
        } else {
            Log.d(TAG, "[DEBUG] putStringSet for key: \"$key\": Applying.")
            editor.apply()
        }
        Log.d(TAG, "[DEBUG] putStringSet for key: \"$key\" completed.")
    }
    
    /**
     * Create a string preference delegate
     */
    private fun stringPreference(key: String, defaultValue: String? = null): ReadWriteProperty<Any, String?> {
        Log.d(TAG, "[DEBUG] Creating stringPreference delegate via ExtKt. Key: \"$key\", DefaultValue: \"$defaultValue\"")
        return ExtKt.stringPreference(key, defaultValue)
    }
    
    /**
     * Create a boolean preference delegate
     */
    private fun booleanPreference(key: String, defaultValue: Boolean = false): ReadWriteProperty<Any, Boolean?> {
        Log.d(TAG, "[DEBUG] Creating booleanPreference delegate via ExtKt. Key: \"$key\", DefaultValue: $defaultValue")
        return ExtKt.booleanPreference(key, defaultValue)
    }
    
    /**
     * Create a long preference delegate
     */
    private fun longPreference(key: String, defaultValue: Long = 0L): ReadWriteProperty<Any, Long?> {
        Log.d(TAG, "[DEBUG] Creating longPreference delegate via ExtKt. Key: \"$key\", DefaultValue: $defaultValue")
        return ExtKt.longPreference(key, defaultValue)
    }
    
    /**
     * Create an integer preference delegate
     */
    private fun intPreference(key: String, defaultValue: Int = 0): ReadWriteProperty<Any, Int?> {
        Log.d(TAG, "[DEBUG] Creating intPreference delegate via ExtKt. Key: \"$key\", DefaultValue: $defaultValue")
        return ExtKt.intPreference(key, defaultValue)
    }
}
