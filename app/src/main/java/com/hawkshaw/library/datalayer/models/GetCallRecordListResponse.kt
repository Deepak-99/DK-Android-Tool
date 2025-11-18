package com.hawkshaw.library.datalayer.models

import com.hawkshaw.library.datalayer.room.telephony.CallRecordEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response containing a list of call records
 */
@Serializable
data class GetCallRecordListResponse(
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
    ) {
        /**
         * Convert to a CallRecordEntity for database storage
         */
        fun toCallRecordEntity(): CallRecordEntity {
            return CallRecordEntity(name, number)
        }
    }
} 