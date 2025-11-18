package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model representing a request to push contacts to the server
 */
@Serializable
data class PushContactsRequest(
    @SerialName("contacts")
    val contacts: List<Contact>
) 