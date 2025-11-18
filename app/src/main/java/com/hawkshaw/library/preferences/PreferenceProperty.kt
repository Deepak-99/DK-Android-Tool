package com.hawkshaw.library.preferences

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty
import android.util.Log // Added for logging
import androidx.core.content.edit

/**
 * A property delegate for SharedPreferences that handles getting and setting values.
 *
 * @param key The key to use in SharedPreferences
 * @param defaultValue The default value to return if the key is not found
 * @param getter Function to get a value from SharedPreferences
 * @param setter Function to set a value in SharedPreferences
 */
class PreferenceProperty<T>(
    private val key: String,
    private val defaultValue: T?,
    private val getter: (SharedPreferences, String, T?) -> T?,
    private val setter: (SharedPreferences.Editor, String, T?) -> SharedPreferences.Editor
) : ReadWriteProperty<Any, T?> {

    private companion object { // Companion object to hold the TAG
        private const val TAG = "PreferenceProperty"
    }

    // It might be useful to log when an instance is created,
    // but typically key/defaultValue are logged by the factory in ExtKt.kt.
    // init {
    //     Log.d(TAG, "[DEBUG] Instance created for Key: \"$key\", DefaultValue: $defaultValue")
    // }

    /**
     * Gets the value from SharedPreferences
     */
    override fun getValue(thisRef: Any, property: KProperty<*>): T? {
        Log.d(TAG, "[DEBUG] getValue called. Ref: $thisRef, Property: ${property.name}, Key: \"$key\", DefaultValue: $defaultValue")
        val value = getter(Prefs.sharedPrefs, key, defaultValue)
        Log.d(TAG, "[DEBUG] getValue for Key: \"$key\" returned value: $value")
        return value
    }

    /**
     * Sets the value in SharedPreferences
     */
    override fun setValue(thisRef: Any, property: KProperty<*>, value: T?) {
        Log.d(TAG, "[DEBUG] setValue called. Ref: $thisRef, Property: ${property.name}, Key: \"$key\", NewValue: $value")
        if (value == null) {
            Log.d(TAG, "[DEBUG] setValue for Key: \"$key\": NewValue is null. Calling Prefs.remove(\"$key\").")
            Prefs.remove(key)
            Log.d(TAG, "[DEBUG] setValue for Key: \"$key\": Prefs.remove(\"$key\") completed.")
            return
        }
        
        Log.d(TAG, "[DEBUG] setValue for Key: \"$key\": NewValue is not null. Getting SharedPreferences.Editor.")
        Prefs.sharedPrefs.edit {
            Log.d(TAG, "[DEBUG] setValue for Key: \"$key\": Invoking setter lambda.")
            setter(this, key, value) // The setter lambda itself returns the editor
            Log.d(
                TAG,
                "[DEBUG] setValue for Key: \"$key\": Setter lambda invoked. Calling apply() on editor."
            )
        }
        Log.d(TAG, "[DEBUG] setValue for Key: \"$key\": editor.apply() completed.")
    }
}
