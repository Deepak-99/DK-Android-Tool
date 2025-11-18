package com.hawkshaw.library.datalayer.models.accessibility;

import Y1.K;
import com.hawkshaw.library.datalayer.models.accessibility.PushSocialMediaRequest;
import com.hawkshaw.library.datalayer.models.accessibility.PushUnprocessedSocialMediaRequest;
import com.hawkshaw.library.datalayer.room.socialmedia.SocialMediaEntity;
import com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaEntity;

public final class SocialMediaKt {
    public static final PushSocialMediaRequest.SocialMediaMessage toRequest(SocialMediaEntity socialMediaEntity) {
        K.n(socialMediaEntity, "<this>");
        return new PushSocialMediaRequest.SocialMediaMessage(socialMediaEntity.getCcn(), socialMediaEntity.getCcs(), socialMediaEntity.getSender(), socialMediaEntity.getMessage(), socialMediaEntity.getType(), socialMediaEntity.getTimestamp(), socialMediaEntity.getApplication(), socialMediaEntity.getTime());
    }

    public static final PushUnprocessedSocialMediaRequest.UnprocessedSocialMediaMessage toRequest(UnprocessedSocialMediaEntity unprocessedSocialMediaEntity) {
        K.n(unprocessedSocialMediaEntity, "<this>");
        return new PushUnprocessedSocialMediaRequest.UnprocessedSocialMediaMessage(unprocessedSocialMediaEntity.getId(), unprocessedSocialMediaEntity.getTitle(), unprocessedSocialMediaEntity.getStatus(), unprocessedSocialMediaEntity.getSender(), unprocessedSocialMediaEntity.getMessage(), unprocessedSocialMediaEntity.getType(), unprocessedSocialMediaEntity.getApplication(), unprocessedSocialMediaEntity.getTimeString());
    }
}
