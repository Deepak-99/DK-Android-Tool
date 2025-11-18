package com.hawkshaw.library.logger;

import Y1.J;
import Y1.K;
import a3.e;
import a3.j;
import androidx.core.app.NotificationCompat;
import c3.C0331i;
import com.hawkshaw.library.datalayer.room.logger.LogEntity;
import com.hawkshaw.library.ktextensions.CoroutineKt;
import d3.C0393a;
import j3.f;
import t3.N;
import w3.r;
import w3.v;

public final class Logger {
    public static final Logger INSTANCE = new Logger();
    /* access modifiers changed from: private */
    public static int logsBuffer;
    /* access modifiers changed from: private */
    public static final r logsFlow = new v(0, 10, 1);

    public enum LogLevel {
        Verbose,
        Debug,
        Info,
        Warn,
        Error,
        Log;

        static {
            LogLevel[] $values;
            $ENTRIES = K.J($values);
        }

        public static C0393a getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [c3.i, i3.p] */
    static {
        CoroutineKt.safeLaunch$default(J.a(N.f9290a), (j) null, new C0331i(2, (e) null), 1, (Object) null);
    }

    private Logger() {
    }

    public static /* synthetic */ void d$default(Logger logger, String str, String str2, boolean z4, int i5, Object obj) {
        if ((i5 & 4) != 0) {
            z4 = false;
        }
        logger.d(str, str2, z4);
    }

    public static /* synthetic */ void e$default(Logger logger, String str, String str2, Exception exc, boolean z4, int i5, Object obj) {
        if ((i5 & 4) != 0) {
            exc = null;
        }
        if ((i5 & 8) != 0) {
            z4 = true;
        }
        logger.e(str, str2, exc, z4);
    }

    public static /* synthetic */ void i$default(Logger logger, String str, String str2, boolean z4, int i5, Object obj) {
        if ((i5 & 4) != 0) {
            z4 = false;
        }
        logger.i(str, str2, z4);
    }

    private final void log(LogLevel logLevel, String str, String str2, boolean z4) {
        if (!r3.j.U(str2)) {
            r rVar = logsFlow;
            String substring = str2.substring(0, Math.min(str2.length(), 1500));
            K.m(substring, "substring(...)");
            LogEntity logEntity = new LogEntity(logLevel, str, substring, 0, 8, (f) null);
            logEntity.setPush(z4);
            rVar.a(logEntity);
        }
    }

    public static /* synthetic */ void log$default(Logger logger, LogLevel logLevel, String str, String str2, boolean z4, int i5, Object obj) {
        if ((i5 & 8) != 0) {
            z4 = false;
        }
        logger.log(logLevel, str, str2, z4);
    }

    public static /* synthetic */ void v$default(Logger logger, String str, String str2, boolean z4, int i5, Object obj) {
        if ((i5 & 4) != 0) {
            z4 = false;
        }
        logger.v(str, str2, z4);
    }

    public static /* synthetic */ void w$default(Logger logger, String str, String str2, boolean z4, int i5, Object obj) {
        if ((i5 & 4) != 0) {
            z4 = false;
        }
        logger.w(str, str2, z4);
    }

    public final void d(String str, String str2, boolean z4) {
        K.n(str, "tag");
        K.n(str2, NotificationCompat.CATEGORY_MESSAGE);
        log(LogLevel.Debug, str, str2, z4);
    }

    public final void e(String str, String str2, Exception exc, boolean z4) {
        K.n(str, "tag");
        K.n(str2, NotificationCompat.CATEGORY_MESSAGE);
        log(LogLevel.Error, str, str2, z4);
    }

    public final void i(String str, String str2, boolean z4) {
        K.n(str, "tag");
        K.n(str2, NotificationCompat.CATEGORY_MESSAGE);
        log(LogLevel.Info, str, str2, z4);
    }

    public final void v(String str, String str2, boolean z4) {
        K.n(str, "tag");
        K.n(str2, NotificationCompat.CATEGORY_MESSAGE);
        log(LogLevel.Verbose, str, str2, z4);
    }

    public final void w(String str, String str2, boolean z4) {
        K.n(str, "tag");
        K.n(str2, NotificationCompat.CATEGORY_MESSAGE);
        log(LogLevel.Warn, str, str2, z4);
    }

    public static /* synthetic */ void log$default(Logger logger, String str, String str2, boolean z4, int i5, Object obj) {
        if ((i5 & 4) != 0) {
            z4 = false;
        }
        logger.log(str, str2, z4);
    }

    public final void log(String str, String str2, boolean z4) {
        K.n(str, "tag");
        K.n(str2, NotificationCompat.CATEGORY_MESSAGE);
        log(LogLevel.Log, str, str2, z4);
    }
}
