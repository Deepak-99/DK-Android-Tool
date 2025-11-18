package com.hawkshaw.library.datalayer.network.socket;

import a3.e;
import android.content.Intent;
import c3.C0325c;
import com.hawkshaw.library.datalayer.network.socket.SocketService;

public final class b extends C0325c {

    /* renamed from: D  reason: collision with root package name */
    public Intent f4929D;

    /* renamed from: E  reason: collision with root package name */
    public /* synthetic */ Object f4930E;

    /* renamed from: F  reason: collision with root package name */
    public final /* synthetic */ SocketService.Companion f4931F;

    /* renamed from: G  reason: collision with root package name */
    public int f4932G;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public b(SocketService.Companion companion, e eVar) {
        super(eVar);
        this.f4931F = companion;
    }

    public final Object invokeSuspend(Object obj) {
        this.f4930E = obj;
        this.f4932G |= Integer.MIN_VALUE;
        return this.f4931F.connectSocket(this);
    }
}
