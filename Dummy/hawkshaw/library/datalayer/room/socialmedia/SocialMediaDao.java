package com.hawkshaw.library.datalayer.room.socialmedia;

import a3.e;
import com.hawkshaw.library.datalayer.room.BaseDao;

public interface SocialMediaDao extends BaseDao<SocialMediaEntity> {
    Object getAll(e eVar);

    Object nukeTable(e eVar);
}
