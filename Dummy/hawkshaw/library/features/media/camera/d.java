package com.hawkshaw.library.features.media.camera;

import W2.y;
import Y1.C0110h;
import a3.e;
import androidx.lifecycle.C0266w;
import b3.C0298a;
import c3.C0331i;
import i3.p;
import o.C0780o;
import o.z0;
import t3.E;

public final class d extends C0331i implements p {

    /* renamed from: D  reason: collision with root package name */
    public final /* synthetic */ A.d f4988D;

    /* renamed from: E  reason: collision with root package name */
    public final /* synthetic */ C0266w f4989E;

    /* renamed from: F  reason: collision with root package name */
    public final /* synthetic */ C0780o f4990F;

    /* renamed from: G  reason: collision with root package name */
    public final /* synthetic */ z0 f4991G;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public d(A.d dVar, C0266w wVar, C0780o oVar, z0 z0Var, e eVar) {
        super(2, eVar);
        this.f4988D = dVar;
        this.f4989E = wVar;
        this.f4990F = oVar;
        this.f4991G = z0Var;
    }

    public final e create(Object obj, e eVar) {
        return new d(this.f4988D, this.f4989E, this.f4990F, this.f4991G, eVar);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((d) create((E) obj, (e) obj2)).invokeSuspend(y.f2418a);
    }

    public final Object invokeSuspend(Object obj) {
        C0298a aVar = C0298a.f4140D;
        C0110h.P(obj);
        A.d dVar = this.f4988D;
        dVar.c();
        return dVar.a(this.f4989E, this.f4990F, this.f4991G);
    }
}
