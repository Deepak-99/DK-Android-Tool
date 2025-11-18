package com.hawkshaw.library.datalayer.room.telephony;

import a3.e;
import java.util.List;

public interface TelephonyDao {
    Object getCallBlockNumbers(e eVar);

    Object getCallRecordNumbers(e eVar);

    Object nukeCallBlockNumbers(e eVar);

    Object nukeCallRecordNumbers(e eVar);

    Object saveCallBlockNumbers(List<CallBlockEntity> list, e eVar);

    Object saveCallRecordNumbers(List<CallRecordEntity> list, e eVar);
}
