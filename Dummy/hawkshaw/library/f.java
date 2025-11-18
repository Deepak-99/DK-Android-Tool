package com.hawkshaw.library;

import Y1.m0;
import a3.e;
import c3.C0325c;
import com.hawkshaw.library.FileServiceGrpcKt;
import w3.C1066b;

public final class f extends C0325c {

    /* renamed from: D  reason: collision with root package name */
    public /* synthetic */ Object f4962D;

    /* renamed from: E  reason: collision with root package name */
    public final /* synthetic */ FileServiceGrpcKt.FileServiceCoroutineStub f4963E;

    /* renamed from: F  reason: collision with root package name */
    public int f4964F;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public f(FileServiceGrpcKt.FileServiceCoroutineStub fileServiceCoroutineStub, e eVar) {
        super(eVar);
        this.f4963E = fileServiceCoroutineStub;
    }

    public final Object invokeSuspend(Object obj) {
        this.f4962D = obj;
        this.f4964F |= Integer.MIN_VALUE;
        return this.f4963E.uploadFile((C1066b) null, (m0) null, this);
    }
}
