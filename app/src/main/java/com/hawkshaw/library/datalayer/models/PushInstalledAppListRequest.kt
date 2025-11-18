package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Request to push a list of installed applications to the server
 */
@Serializable
data class PushInstalledAppListRequest(
    @SerialName("apps")
    val apps: List<InstalledApp>
) 