package com.hawkshaw.library.datalayer.room.socialmedia;

import a3.e;
import com.hawkshaw.library.datalayer.room.BaseDao;

public interface UnprocessedSocialMediaDao extends BaseDao<UnprocessedSocialMediaEntity> {
    UnprocessedSocialMediaEntity getSync(int i5);

    void insertSync(UnprocessedSocialMediaEntity... unprocessedSocialMediaEntityArr);

    Object list(e eVar);

    Object list(boolean z4, e eVar);

    Object nukeTable(e eVar);
}
