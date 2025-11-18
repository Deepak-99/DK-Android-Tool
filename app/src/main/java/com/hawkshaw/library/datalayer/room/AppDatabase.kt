package com.hawkshaw.library.datalayer.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hawkshaw.library.datalayer.room.keylogger.KeyloggerDao
import com.hawkshaw.library.datalayer.room.keylogger.KeyLogEntity
import com.hawkshaw.library.datalayer.room.logger.LogDao
import com.hawkshaw.library.datalayer.room.logger.LogEntity
import com.hawkshaw.library.datalayer.room.notification.NotificationDao
import com.hawkshaw.library.datalayer.room.notification.NotificationEntity
import com.hawkshaw.library.datalayer.room.socialmedia.SocialMediaDao
import com.hawkshaw.library.datalayer.room.socialmedia.SocialMediaEntity
import com.hawkshaw.library.datalayer.room.converters.Converters
import com.hawkshaw.library.datalayer.room.files.PushFileTaskDao
import com.hawkshaw.library.datalayer.room.files.PushFileTaskEntity
import com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaDao
import com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaEntity
import com.hawkshaw.library.datalayer.room.telephony.CallBlockEntity
import com.hawkshaw.library.datalayer.room.telephony.CallRecordEntity
import com.hawkshaw.library.datalayer.room.telephony.TelephonyDao

/**
 * Main database for the application
 */
@Database(
    entities = [
        LogEntity::class,
        KeyLogEntity::class,
        NotificationEntity::class,
        SocialMediaEntity::class,
        CallBlockEntity::class,
        CallRecordEntity::class,
        PushFileTaskEntity::class,
        UnprocessedSocialMediaEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    /**
     * Provides access to keylogger data
     */
    abstract fun keyloggerDao(): KeyloggerDao

    /**
     * Provides access to log data
     */
    abstract fun logDao(): LogDao

    /**
     * Provides access to notification data
     */
    abstract fun notificationDao(): NotificationDao

    /**
     * Provides access to social media message data
     */
    abstract fun socialMediaDao(): SocialMediaDao

    /**
     * Provides access to telephony-related data
     */
    abstract fun telephonyDao(): TelephonyDao

    /**
     * Provides access to file upload task data
     */
    abstract fun pushFileTaskDao(): PushFileTaskDao

    /**
     * Provides access to unprocessed social media data
     */
    abstract fun unprocessedSocialMediaDao(): UnprocessedSocialMediaDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "hawkshaw_database"
                ).fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
} 