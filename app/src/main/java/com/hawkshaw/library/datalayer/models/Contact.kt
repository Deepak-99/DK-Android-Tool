package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model representing a contact
 * Contains contact details including name, phone numbers, and email addresses
 */
@Serializable
data class Contact(
    @SerialName("id")
    val id: String? = null,
    
    @SerialName("name")
    val name: String? = null,
    
    @SerialName("person")
    val person: String? = null,
    
    @SerialName("starred")
    val starred: Int = 0,
    
    @SerialName("pinned")
    val pinned: Int = 0,
    
    @SerialName("photo_uri")
    val photoUri: String? = null,
    
    @SerialName("last_updated_timestamp")
    val lastUpdatedTimestamp: String? = null,
    
    @SerialName("mobile_numbers")
    val mobileNumbers: List<MobileNumber>,
    
    @SerialName("email_list")
    val emailList: List<String>
) {
    /**
     * Data model representing a mobile phone number with a type
     */
    @Serializable
    data class MobileNumber(
        @SerialName("number")
        val number: String,
        
        @SerialName("type")
        val type: String
    )
} 