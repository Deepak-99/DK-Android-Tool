package com.hawkshaw.library

/**
 * Extension object providing property delegates
 */
object DelegatesExt {
    /**
     * Creates a property delegate that can only be set once and must be non-null
     * 
     * Usage:
     * ```
     * private val myProperty by DelegatesExt.notNullSingleValue<String>()
     * ```
     */
    fun <T> notNullSingleValue(): NotNullSingleValueVar<T> = NotNullSingleValueVar()
} 