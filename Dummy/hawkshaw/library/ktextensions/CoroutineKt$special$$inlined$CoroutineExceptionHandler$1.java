package com.hawkshaw.library.ktextensions;

import a3.C0244a;
import a3.j;
import com.hawkshaw.library.logger.Logger;
import t3.C0973B;
import t3.C0974C;

public final class CoroutineKt$special$$inlined$CoroutineExceptionHandler$1 extends C0244a implements C0974C {
    public CoroutineKt$special$$inlined$CoroutineExceptionHandler$1(C0973B b5) {
        super(b5);
    }

    public void handleException(j jVar, Throwable th) {
        Logger.e$default(Logger.INSTANCE, "CoroutineExceptionHandler", StringsKt.getEmpty(th.getMessage()), (Exception) null, false, 12, (Object) null);
        ExceptionsKt.logNonFatal(th);
    }
}
