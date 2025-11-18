package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CallLog(
    @SerialName("id")
    val id: String?, // Matches Java's 'String id'
    @SerialName("name")
    val name: String?, // Matches Java's 'String name'
    @SerialName("number")
    val number: String?, // Matches Java's 'String number' (note: Java constructor accepts nullable)
    @SerialName("date")
    val date: Long?, // Matches Java's 'Long date'
    @SerialName("duration")
    val duration: Long?, // Matches Java's 'Long duration'
    @SerialName("call_type") // Matches Java's 'callType'
    val callType: String?, // Matches Java's 'String callType'
    @SerialName("call_new") // Matches Java's 'callNew'
    val callNew: String?, // Matches Java's 'String callNew' (This is often a "1" or "0" string)
    @SerialName("sim_slot") // Matches Java's 'simSlot'
    val simSlot: String?, // Matches Java's 'String simSlot'
    @SerialName("features")
    val features: Int?, // Matches Java's 'Integer features'
    @SerialName("voice_mail_uri") // Matches Java's 'voiceMailUri' (note: capital M)
    val voiceMailUri: String?, // Matches Java's 'String voiceMailUri'
    @SerialName("phone_account_id") // Matches Java's 'phoneAccountId'
    val phoneAccountId: String?, // Matches Java's 'String phoneAccountId'
    @SerialName("last_modified") // Matches Java's 'lastModified'
    val lastModified: Long?, // Matches Java's 'Long lastModified'
    @SerialName("call_screening_app_name") // Matches Java's 'callScreeningAppName'
    val callScreeningAppName: String?, // Matches Java's 'String callScreeningAppName'
    @SerialName("block_reason") // Matches Java's 'blockReason'
    val blockReason: Int? // Matches Java's 'Integer blockReason'
)