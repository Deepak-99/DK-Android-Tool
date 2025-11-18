package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.Serializable

/**
 * Data class representing call information
 */
@Serializable
data class CallInfo(
    val phoneNumber: String?,
    val callType: CallType,
    val isStarted: Boolean
) 