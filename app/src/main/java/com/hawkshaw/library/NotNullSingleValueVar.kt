package com.hawkshaw.library

import kotlin.reflect.KProperty
import android.util.Log // Added for logging

/**
 * A variable that can only be set once and must not be null
 */
class NotNullSingleValueVar<T> {
    private companion object { // Companion object for TAG
        private const val TAG = "NotNullSingleValueVar"
    }

    private var _value: T? = null
    private var isSet = false

    init {
        Log.d(TAG, "[DEBUG] Instance created. _value: null, isSet: false")
    }

    var value: T
        get() {
            Log.d(TAG, "[DEBUG] 'value' getter invoked. isSet: $isSet")
            if (!isSet) {
                Log.w(TAG, "[DEBUG] 'value' getter: Value not set. Throwing NotNullSingleValueVarException.")
                throw NotNullSingleValueVarException("Value not set")
            }
            Log.d(TAG, "[DEBUG] 'value' getter: Value is set. Returning: $_value")
            return _value!!
        }
        set(value) {
            Log.d(TAG, "[DEBUG] 'value' setter invoked. Attempting to set value: $value. Current isSet: $isSet")
            if (isSet) {
                Log.w(TAG, "[DEBUG] 'value' setter: Value already set. Throwing NotNullSingleValueVarException. Current value: $_value")
                throw NotNullSingleValueVarException("Value already set")
            }
            _value = value
            isSet = true
            Log.d(TAG, "[DEBUG] 'value' setter: Value set successfully. _value: $_value, isSet: $isSet")
        }

    /**
     * Sets the value, throws if already initialized
     */
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        Log.d(TAG, "[DEBUG] operator 'setValue' invoked. Property: '${property.name}', Value: $value. Current isSet: $isSet. thisRef: $thisRef")
        if (isSet) {
            Log.w(TAG, "[DEBUG] operator 'setValue': Property '${property.name}' already initialized. Throwing NotNullSingleValueVarException. Current value: $_value")
            throw NotNullSingleValueVarException("${property.name} already initialized")
        }
        _value = value
        isSet = true
        Log.d(TAG, "[DEBUG] operator 'setValue': Property '${property.name}' set successfully. _value: $_value, isSet: $isSet")
    }

    /**
     * Gets the value for property delegation, throws if not initialized
     */
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        Log.d(TAG, "[DEBUG] operator 'getValue' invoked. Property: '${property.name}'. Current isSet: $isSet. thisRef: $thisRef")
        if (!isSet) {
            Log.w(TAG, "[DEBUG] operator 'getValue': Property '${property.name}' not initialized. Throwing NotNullSingleValueVarException.")
            throw NotNullSingleValueVarException("${property.name} not initialized")
        }
        Log.d(TAG, "[DEBUG] operator 'getValue': Property '${property.name}' is initialized. Returning: $_value")
        return _value!!
    }

    /**
     * Returns true if the property has been initialized
     */
    fun isInitialized(): Boolean {
        Log.d(TAG, "[DEBUG] isInitialized() called. Returning: $isSet")
        return isSet
    }
}

/**
 * Exception thrown when attempting to access an unset value or set a value multiple times
 */
class NotNullSingleValueVarException(message: String) : Exception(message) {
    // Companion object for TAG if specific logging for exception creation is needed,
    // otherwise, the outer class's TAG can be used if logging is done by the thrower.
    // For simplicity, we'll log directly in the constructor.
    init {
        // Using a generic TAG or could define one specific to the exception
        Log.e("NotNullSingleValueVar", "[DEBUG] NotNullSingleValueVarException created with message: \"$message\"")
    }
}
