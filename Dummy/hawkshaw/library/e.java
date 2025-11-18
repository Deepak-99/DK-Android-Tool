package com.hawkshaw.library;

import Y1.m0;
import c3.C0325c;
import com.hawkshaw.library.FileServiceGrpcKt;
import w3.C1066b;

public final class e extends C0325c {

    /* renamed from: D  reason: collision with root package name */
    public /* synthetic */ Object f4959D;

    /* renamed from: E  reason: collision with root package name */
    public final /* synthetic */ FileServiceGrpcKt.FileServiceCoroutineStub f4960E;

    /* renamed from: F  reason: collision with root package name */
    public int f4961F;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public e(FileServiceGrpcKt.FileServiceCoroutineStub fileServiceCoroutineStub, a3.e eVar) {
        super(eVar);
        this.f4960E = fileServiceCoroutineStub;
    }

    public final Object invokeSuspend(Object obj) {
        this.f4959D = obj;
        this.f4961F |= Integer.MIN_VALUE;
        return this.f4960E.batchThumbUpload((C1066b) null, (m0) null, this);
    }
}
