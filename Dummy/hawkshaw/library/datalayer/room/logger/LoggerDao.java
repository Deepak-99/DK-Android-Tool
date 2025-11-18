package com.hawkshaw.library.datalayer.room.logger;

import a3.e;
import com.hawkshaw.library.datalayer.room.BaseDao;

public interface LoggerDao extends BaseDao<LogEntity> {
    Object getAll(e eVar);

    Object nukeTable(e eVar);
}
