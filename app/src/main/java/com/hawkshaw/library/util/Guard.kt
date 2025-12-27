package com.hawkshaw.library.util

object Guard {

    fun allow(pkg: String): Boolean {
        return !pkg.contains("com.android.systemui")
    }
}