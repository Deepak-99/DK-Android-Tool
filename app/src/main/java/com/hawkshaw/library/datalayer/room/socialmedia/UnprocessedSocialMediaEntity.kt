package com.hawkshaw.library.datalayer.room.socialmedia

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hawkshaw.library.datalayer.models.PackageName

/**
 * Entity representing unprocessed social media messages
 */
@Entity(tableName = "unprocessed_social_media_entity")
data class UnprocessedSocialMediaEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "status")
    var status: String? = null,

    @ColumnInfo(name = "sender")
    var sender: String? = null,

    @ColumnInfo(name = "message")
    var message: String = "",

    @ColumnInfo(name = "type")
    var type: SocialMediaEntity.Type = SocialMediaEntity.Type.Unknown,

    @ColumnInfo(name = "application")
    var application: PackageName = PackageName.Unknown,

    @ColumnInfo(name = "time_string")
    var timeString: String = "",

    @ColumnInfo(name = "uploaded")
    var uploaded: Boolean = false,

    @ColumnInfo(name = "timestamp")
    var timestamp: Long = System.currentTimeMillis()
) 