package com.hawkshaw.library.datalayer.room.notification;

import a3.e;
import com.hawkshaw.library.datalayer.room.BaseDao;

public interface NotificationDao extends BaseDao<NotificationEntity> {
    Object getAll(e eVar);

    Object nukeTable(e eVar);
}
