package com.hawkshaw.library;

import Y1.C0106f;
import Y1.C0108g;
import com.hawkshaw.library.FileServiceGrpc;
import io.grpc.stub.d;
import io.grpc.stub.e;

public final class b implements d {
    public final e a(C0108g gVar, C0106f fVar) {
        return new FileServiceGrpc.FileServiceStub(gVar, fVar, 0);
    }
}
