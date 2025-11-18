package com.hawkshaw.library.datalayer.room.files;

import a3.e;
import com.hawkshaw.library.datalayer.room.BaseDao;

public interface PushFileTaskDao extends BaseDao<PushFileTaskEntity> {

    public static final class DefaultImpls {
        public static /* synthetic */ Object setLastPushTimestamp$default(PushFileTaskDao pushFileTaskDao, int i5, long j5, e eVar, int i6, Object obj) {
            if (obj == null) {
                if ((i6 & 2) != 0) {
                    j5 = System.currentTimeMillis();
                }
                return pushFileTaskDao.setLastPushTimestamp(i5, j5, eVar);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setLastPushTimestamp");
        }
    }

    Object delete(int i5, e eVar);

    Object getAllTasks(e eVar);

    Object getTopTask(int i5, e eVar);

    Object getTopTask(e eVar);

    Object incrementPriority(int i5, e eVar);

    Object nukeTable(e eVar);

    Object setLastPushTimestamp(int i5, long j5, e eVar);
}
