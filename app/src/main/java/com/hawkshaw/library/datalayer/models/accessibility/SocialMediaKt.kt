package com.hawkshaw.library.datalayer.models.accessibility

import com.hawkshaw.library.datalayer.models.PackageName
import com.hawkshaw.library.datalayer.room.socialmedia.SocialMediaEntity
import com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaEntity

/**
 * Extension function to convert a SocialMediaEntity to a PushSocialMediaRequest.SocialMediaMessage
 *
 * @return A new SocialMediaMessage instance with values from this SocialMediaEntity
 */
fun SocialMediaEntity.toRequest(): PushSocialMediaRequest.SocialMediaMessage {
    return PushSocialMediaRequest.SocialMediaMessage(
        ccn = this.packageName,
        sender = this.sender,
        message = this.message ?: "",
        // FIX: this.type is already SocialMediaEntity.Type, no need for valueOf()
        type = this.type,
        timestamp = this.timestamp,
        application = PackageName.from(this.packageName),
        time = this.timestamp.toString()
    )
}

/**
 * Extension function to convert an UnprocessedSocialMediaEntity to a PushUnprocessedSocialMediaRequest.UnprocessedSocialMediaMessage
 *
 * @return A new UnprocessedSocialMediaMessage instance with values from this UnprocessedSocialMediaEntity
 */
fun UnprocessedSocialMediaEntity.toRequest(): PushUnprocessedSocialMediaRequest.UnprocessedSocialMediaMessage {
    return PushUnprocessedSocialMediaRequest.UnprocessedSocialMediaMessage(
        id = this.id,
        title = this.title,
        status = this.status,
        sender = this.sender,
        message = this.message,
        type = this.type,
        application = this.application,
        timeString = this.timeString
    )
} 