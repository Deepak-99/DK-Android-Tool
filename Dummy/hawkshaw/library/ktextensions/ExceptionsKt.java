package com.hawkshaw.library.ktextensions;

import Y1.K;
import io.sentry.A0;
import io.sentry.C0617v;
import io.sentry.E;

public final class ExceptionsKt {
    public static final void logNonFatal(Exception exc) {
        K.n(exc, "<this>");
        logNonFatal((Throwable) exc);
    }

    public static final void logNonFatal(Throwable th) {
        K.n(th, "<this>");
        th.printStackTrace();
        E b5 = A0.b();
        b5.getClass();
        b5.t(th, new C0617v());
    }
}
