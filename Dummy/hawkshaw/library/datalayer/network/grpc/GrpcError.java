package com.hawkshaw.library.datalayer.network.grpc;

import j3.f;

public final class GrpcError extends RuntimeException {
    private final Throwable cause;
    private final String message;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ GrpcError(Throwable th, String str, int i5, f fVar) {
        this(th, (i5 & 2) != 0 ? th != null ? th.getMessage() : null : str);
    }

    public Throwable getCause() {
        return this.cause;
    }

    public String getMessage() {
        return this.message;
    }

    public GrpcError(Throwable th, String str) {
        this.cause = th;
        this.message = str;
    }
}
