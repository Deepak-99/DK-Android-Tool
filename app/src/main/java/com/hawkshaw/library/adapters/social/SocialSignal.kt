package com.hawkshaw.library.adapters.social

data class SocialSignal(
    val platform: String,
    val event: String,
    val confidence: Float
)