package com.hawkshaw.library;

import Y1.C0106f;
import Y1.C0108g;
import com.hawkshaw.library.FileServiceGrpc;
import io.grpc.stub.e;

public final class d implements io.grpc.stub.d {
    public final e a(C0108g gVar, C0106f fVar) {
        return new FileServiceGrpc.FileServiceFutureStub(gVar, fVar, 0);
    }
}
