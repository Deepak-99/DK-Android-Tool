package com.hawkshaw.library;

import Y1.K;

public final class NotNullSingleValueVarException extends IllegalStateException {
    public NotNullSingleValueVarException() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NotNullSingleValueVarException(String str) {
        super(str);
        K.n(str, "message");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NotNullSingleValueVarException(String str, Throwable th) {
        super(str, th);
        K.n(str, "message");
        K.n(th, "cause");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NotNullSingleValueVarException(Throwable th) {
        super(th);
        K.n(th, "cause");
    }
}
