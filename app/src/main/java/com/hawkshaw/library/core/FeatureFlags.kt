package com.hawkshaw.library.core

data class FeatureFlags(
    val socialSignals: Boolean,
    val socialDeepCapture: Boolean,
    val accessibilityHooks: Boolean,
    val experimentalAdapters: Boolean
) {
    companion object {
        fun default() = FeatureFlags(
            socialSignals = true,
            socialDeepCapture = false,
            accessibilityHooks = true,
            experimentalAdapters = false
        )
    }
}
