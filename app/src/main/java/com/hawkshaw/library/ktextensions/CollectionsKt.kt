package com.hawkshaw.library.ktextensions

/**
 * Utility functions for collections
 */

/**
 * Returns null if the list is empty, otherwise returns the original list
 */
fun <T> List<T>?.nullIfEmpty(): List<T>? {
    return if (this.isNullOrEmpty()) null else this
}

/**
 * Adds an array of elements to the current array and returns a new array
 */
fun <T> Array<T>.plus(vararg elements: T): Array<T> {
    return this + elements
}

/**
 * Safely adds elements to a collection and returns a new collection
 */
fun <T> Collection<T>.plusSafe(vararg elements: T): Collection<T> {
    return this + elements
} 