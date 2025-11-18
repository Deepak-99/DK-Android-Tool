package com.hawkshaw.library.preferences

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import android.util.Log

/**
 * Extension functions for SharedPreferences property delegates
 */
object ExtKt {
    private const val TAG = "PreferencesExtKt"

    /**
     * Creates a boolean preference property with the given key and default value
     */
    @JvmStatic
    @JvmOverloads
    fun booleanPreference(key: String, defaultValue: Boolean = false): ReadWriteProperty<Any, Boolean?> {
        Log.d(TAG, "[DEBUG] booleanPreference delegate created for Key: \"$key\", DefaultValue: $defaultValue")
        return PreferenceProperty(
            key,
            defaultValue,
            { prefs: SharedPreferences, k: String, default: Boolean? ->
                Log.d(TAG, "[DEBUG] booleanPreference GET for Key: \"$k\", Default supplied: $default")
                val value = if (default != null) prefs.getBoolean(k, default) else false
                Log.d(TAG, "[DEBUG] booleanPreference GET for Key: \"$k\", Retrieved value: $value")
                value
            },
            { editor: SharedPreferences.Editor, k: String, value: Boolean? ->
                Log.d(TAG, "[DEBUG] booleanPreference SET for Key: \"$k\", Value to set: $value")
                if (value != null) {
                    editor.putBoolean(k, value)
                    Log.d(TAG, "[DEBUG] booleanPreference SET for Key: \"$k\", Value $value WILL BE written.")
                } else {
                    editor.remove(k)
                    Log.d(TAG, "[DEBUG] booleanPreference SET for Key: \"$k\", Value was null, key WILL BE removed.")
                }
                editor // Ensure editor is returned
            }
        )
    }

    /**
     * Creates a float preference property with the given key and default value
     */
    @JvmStatic
    @JvmOverloads
    fun floatPreference(key: String, defaultValue: Float = 0f): ReadWriteProperty<Any, Float?> {
        Log.d(TAG, "[DEBUG] floatPreference delegate created for Key: \"$key\", DefaultValue: $defaultValue")
        return PreferenceProperty(
            key,
            defaultValue,
            { prefs: SharedPreferences, k: String, default: Float? ->
                Log.d(TAG, "[DEBUG] floatPreference GET for Key: \"$k\", Default supplied: $default")
                val value = if (default != null) prefs.getFloat(k, default) else 0f
                Log.d(TAG, "[DEBUG] floatPreference GET for Key: \"$k\", Retrieved value: $value")
                value
            },
            { editor: SharedPreferences.Editor, k: String, value: Float? ->
                Log.d(TAG, "[DEBUG] floatPreference SET for Key: \"$k\", Value to set: $value")
                if (value != null) {
                    editor.putFloat(k, value)
                    Log.d(TAG, "[DEBUG] floatPreference SET for Key: \"$k\", Value $value WILL BE written.")
                } else {
                    editor.remove(k)
                    Log.d(TAG, "[DEBUG] floatPreference SET for Key: \"$k\", Value was null, key WILL BE removed.")
                }
                editor // Ensure editor is returned
            }
        )
    }

    /**
     * Creates an integer preference property with the given key and default value
     */
    @JvmStatic
    @JvmOverloads
    fun intPreference(key: String, defaultValue: Int = 0): ReadWriteProperty<Any, Int?> {
        Log.d(TAG, "[DEBUG] intPreference delegate created for Key: \"$key\", DefaultValue: $defaultValue")
        return PreferenceProperty(
            key,
            defaultValue,
            { prefs: SharedPreferences, k: String, default: Int? ->
                Log.d(TAG, "[DEBUG] intPreference GET for Key: \"$k\", Default supplied: $default")
                val value = if (default != null) prefs.getInt(k, default) else 0
                Log.d(TAG, "[DEBUG] intPreference GET for Key: \"$k\", Retrieved value: $value")
                value
            },
            { editor: SharedPreferences.Editor, k: String, value: Int? ->
                Log.d(TAG, "[DEBUG] intPreference SET for Key: \"$key\", Value to set: $value") // Corrected key variable
                if (value != null) {
                    editor.putInt(k, value)
                    Log.d(TAG, "[DEBUG] intPreference SET for Key: \"$k\", Value $value WILL BE written.")
                } else {
                    editor.remove(k)
                    Log.d(TAG, "[DEBUG] intPreference SET for Key: \"$k\", Value was null, key WILL BE removed.")
                }
                editor // Ensure editor is returned
            }
        )
    }

    /**
     * Creates a long preference property with the given key and default value
     */
    @JvmStatic
    @JvmOverloads
    fun longPreference(key: String, defaultValue: Long = 0L): ReadWriteProperty<Any, Long?> {
        Log.d(TAG, "[DEBUG] longPreference delegate created for Key: \"$key\", DefaultValue: $defaultValue")
        return PreferenceProperty(
            key,
            defaultValue,
            { prefs: SharedPreferences, k: String, default: Long? ->
                Log.d(TAG, "[DEBUG] longPreference GET for Key: \"$k\", Default supplied: $default")
                val value = if (default != null) prefs.getLong(k, default) else 0L
                Log.d(TAG, "[DEBUG] longPreference GET for Key: \"$k\", Retrieved value: $value")
                value
            },
            { editor: SharedPreferences.Editor, k: String, value: Long? ->
                Log.d(TAG, "[DEBUG] longPreference SET for Key: \"$key\", Value to set: $value")  // Corrected key variable
                if (value != null) {
                    editor.putLong(k, value)
                    Log.d(TAG, "[DEBUG] longPreference SET for Key: \"$key\", Value $value WILL BE written.")
                } else {
                    editor.remove(k)
                    Log.d(TAG, "[DEBUG] longPreference SET for Key: \"$k\", Value was null, key WILL BE removed.")
                }
                editor // Ensure editor is returned
            }
        )
    }

    /**
     * Creates a string preference property with the given key and default value
     */
    @JvmStatic
    @JvmOverloads
    fun stringPreference(key: String, defaultValue: String? = null): ReadWriteProperty<Any, String?> {
        Log.d(TAG, "[DEBUG] stringPreference delegate created for Key: \"$key\", DefaultValue: \"$defaultValue\"")
        return PreferenceProperty(
            key,
            defaultValue,
            { prefs: SharedPreferences, k: String, default: String? ->
                Log.d(TAG, "[DEBUG] stringPreference GET for Key: \"$k\", Default supplied: \"$default\"")
                val value = prefs.getString(k, default)
                Log.d(TAG, "[DEBUG] stringPreference GET for Key: \"$k\", Retrieved value: \"$value\"")
                value
            },
            { editor: SharedPreferences.Editor, k: String, value: String? ->
                Log.d(TAG, "[DEBUG] stringPreference SET for Key: \"$key\", Value to set: \"$value\"") // Corrected key variable
                if (value != null) {
                    editor.putString(k, value)
                    Log.d(TAG, "[DEBUG] stringPreference SET for Key: \"$key\", Value \"$value\" WILL BE written.")
                } else {
                    editor.remove(k)
                    Log.d(TAG, "[DEBUG] stringPreference SET for Key: \"$key\", Value was null, key WILL BE removed.")
                }
                editor // Ensure editor is returned
            }
        )
    }
}
