package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response containing a list of call blocks
 */
@Serializable
data class CallBlockListResponse(
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
    )
} 