package com.hawkshaw.library.datalayer.models

import com.hawkshaw.library.datalayer.room.telephony.CallBlockEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response containing a list of call blocks
 */
@Serializable
data class GetCallBlockListResponse(
    @SerialName("list")
    val list: List<CallBlock>
) {
    /**
     * Represents a call block entry
     */
    @Serializable
    data class CallBlock(
        @SerialName("name")
        val name: String,
        
        @SerialName("number")
        val number: String,
        
        @SerialName("block_outgoing_call")
        val blockOutgoingCall: Boolean
    ) {
        /**
         * Convert to a CallBlockEntity for database storage
         */
        fun toCallBlockEntity(): CallBlockEntity {
            return CallBlockEntity(name, number, blockOutgoingCall)
        }
    }
} 