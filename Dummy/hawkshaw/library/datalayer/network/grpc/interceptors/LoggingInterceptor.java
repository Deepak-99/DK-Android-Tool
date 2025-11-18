package com.hawkshaw.library.datalayer.network.grpc.interceptors;

import X2.k;
import Y1.C0106f;
import Y1.C0108g;
import Y1.C0114j;
import Y1.C0116k;
import Y1.F0;
import Y1.K;
import Y1.m0;
import Y1.p0;
import android.util.Log;
import com.hawkshaw.library.logger.Logger;
import d3.C0393a;
import j3.f;
import java.util.Arrays;
import java.util.EnumSet;

public final class LoggingInterceptor implements C0116k {
    public static final Companion Companion = new Companion((f) null);
    private static final String REQUEST = "Request";
    private static final String RESPONSE = "Response";
    private static final String TAG = "LoggingInterceptor";
    private final EnumSet<Level> levels;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(f fVar) {
            this();
        }
    }

    public enum Level {
        STATUS,
        HEADERS,
        MESSAGE;

        static {
            Level[] $values;
            $ENTRIES = K.J($values);
        }

        public static C0393a getEntries() {
            return $ENTRIES;
        }
    }

    public LoggingInterceptor(Level... levelArr) {
        K.n(levelArr, "levels");
        EnumSet<Level> copyOf = EnumSet.copyOf(k.H(levelArr));
        K.m(copyOf, "copyOf(...)");
        this.levels = copyOf;
    }

    private final void log(String str) {
        if (str != null) {
            Log.d("grpc.LoggingInterceptor", str);
        }
    }

    /* access modifiers changed from: private */
    public final void logHeaders(String str, m0 m0Var) {
        if (this.levels.contains(Level.HEADERS)) {
            log(String.format("%s headers : %s", Arrays.copyOf(new Object[]{str, m0Var}, 2)));
        }
    }

    /* access modifiers changed from: private */
    public final <RespT> void logMessage(String str, RespT respt) {
        if (this.levels.contains(Level.MESSAGE)) {
            log(String.format("%s message : %s", Arrays.copyOf(new Object[]{str, respt}, 2)));
        }
    }

    /* access modifiers changed from: private */
    public final <ReqT, RespT> void logMethod(p0 p0Var) {
        if (this.levels.contains(Level.STATUS)) {
            String format = String.format("%s path : %s", Arrays.copyOf(new Object[]{REQUEST, p0Var.f2799b}, 2));
            log(format);
            Logger.v$default(Logger.INSTANCE, TAG, format, false, 4, (Object) null);
        }
    }

    /* access modifiers changed from: private */
    public final <ReqT, RespT> void logStatus(F0 f02, p0 p0Var) {
        if (this.levels.contains(Level.STATUS)) {
            String format = String.format("%s status: %s %s for path : %s", Arrays.copyOf(new Object[]{RESPONSE, Integer.valueOf(f02.f2695a.f2675D), f02.f2695a, p0Var.f2799b}, 4));
            log(format);
            Logger.v$default(Logger.INSTANCE, TAG, format, false, 4, (Object) null);
        }
    }

    public <ReqT, RespT> C0114j interceptCall(p0 p0Var, C0106f fVar, C0108g gVar) {
        K.n(p0Var, "method");
        K.n(gVar, "next");
        return new LoggingInterceptor$interceptCall$1(this, p0Var, gVar.h(p0Var, fVar));
    }
}
