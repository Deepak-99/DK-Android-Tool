package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response containing a list of call records
 */
@Serializable
data class CallRecordListResponse(
    @SerialName("list")
    val list: List<CallRecord>
) {
    /**
     * Represents a call record entry
     */
    @Serializable
    data class CallRecord(
        @SerialName("name")
        val name: String,
        
        @SerialName("number")
        val number: String
    )
} 