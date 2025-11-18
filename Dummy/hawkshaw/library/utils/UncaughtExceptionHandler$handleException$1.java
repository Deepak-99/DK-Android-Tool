package com.hawkshaw.library.utils;

import Y1.K;
import a3.e;
import a3.j;
import com.hawkshaw.library.NotNullSingleValueVarException;
import com.hawkshaw.library.config.RemoteConfig;
import com.hawkshaw.library.ktextensions.CoroutineKt;
import com.hawkshaw.library.logger.Logger;
import java.lang.Thread;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicInteger;
import t3.Y;
import t3.f0;

public final class UncaughtExceptionHandler$handleException$1 implements Thread.UncaughtExceptionHandler {
    private f0 counterResetJob;
    /* access modifiers changed from: private */
    public final AtomicInteger exceptionCounter = new AtomicInteger();

    public void uncaughtException(Thread thread, Throwable th) {
        K.n(thread, "t");
        K.n(th, "e");
        Logger.e$default(Logger.INSTANCE, "UncaughtExceptionHandler", String.valueOf(th.getMessage()), (Exception) null, false, 12, (Object) null);
        th.printStackTrace();
        if (((long) this.exceptionCounter.getAndIncrement()) < RemoteConfig.INSTANCE.getMaxFrequentExceptionAllowedCount()) {
            f0 f0Var = this.counterResetJob;
            if (f0Var != null) {
                f0Var.e((CancellationException) null);
            }
            this.counterResetJob = CoroutineKt.safeLaunch$default(Y.f9306D, (j) null, new d(this, (e) null), 1, (Object) null);
        } else if (th instanceof NotNullSingleValueVarException) {
            System.exit(2);
            throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
        } else {
            System.exit(2);
            throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
        }
    }
}
