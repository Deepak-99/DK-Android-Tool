package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model representing an installed app on the device
 * Contains app details including name, version, installation time, and category
 */
@Serializable
data class InstalledApp(
    @SerialName("name")
    val name: String,

    @SerialName("package_name")
    val packageName: String,

    @SerialName("version_name")
    val versionName: String? = null,

    @SerialName("version_code")
    val versionCode: Long = 0,

    @SerialName("first_install_time")
    val firstInstallTime: Long = 0,

    @SerialName("is_system_app")
    val isSystemApp: Boolean = false,

    @SerialName("category")
    val category: Category,

    @SerialName("enabled")
    val enabled: Boolean = true
) {
    /**
     * Enum representing the category of an app
     */
    @Serializable
    enum class Category {
        @SerialName("UNDEFINED")
        UNDEFINED,

        @SerialName("GAME")
        GAME,

        @SerialName("AUDIO")
        AUDIO,

        @SerialName("VIDEO")
        VIDEO,

        @SerialName("IMAGE")
        IMAGE,

        @SerialName("SOCIAL")
        SOCIAL,

        @SerialName("NEWS")
        NEWS,

        @SerialName("MAPS")
        MAPS,

        @SerialName("PRODUCTIVITY")
        PRODUCTIVITY,

        @SerialName("ACCESSIBILITY")
        ACCESSIBILITY
    }
}