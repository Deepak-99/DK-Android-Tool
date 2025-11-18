package com.hawkshaw.library.datalayer.room.logger;

import Y1.K;
import com.hawkshaw.library.logger.Logger;
import j3.f;

public final class LogEntity {
    private int id;
    private final Logger.LogLevel logLevel;
    private final String message;
    private boolean push;
    private final String tag;
    private final long timestamp;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ LogEntity(Logger.LogLevel logLevel2, String str, String str2, long j5, int i5, f fVar) {
        this(logLevel2, str, str2, (i5 & 8) != 0 ? System.currentTimeMillis() : j5);
    }

    public final int getId() {
        return this.id;
    }

    public final Logger.LogLevel getLogLevel() {
        return this.logLevel;
    }

    public final String getMessage() {
        return this.message;
    }

    public final boolean getPush() {
        return this.push;
    }

    public final String getTag() {
        return this.tag;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final void setId(int i5) {
        this.id = i5;
    }

    public final void setPush(boolean z4) {
        this.push = z4;
    }

    public LogEntity(Logger.LogLevel logLevel2, String str, String str2, long j5) {
        K.n(logLevel2, "logLevel");
        K.n(str, "tag");
        K.n(str2, "message");
        this.logLevel = logLevel2;
        this.tag = str;
        this.message = str2;
        this.timestamp = j5;
    }
}
