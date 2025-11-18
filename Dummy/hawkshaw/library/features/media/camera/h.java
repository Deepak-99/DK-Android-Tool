package com.hawkshaw.library.features.media.camera;

import A.d;
import W2.y;
import Y1.C0110h;
import a3.e;
import androidx.lifecycle.C0266w;
import b3.C0298a;
import c3.C0331i;
import i3.p;
import o.C0757L;
import o.C0780o;
import t3.E;

public final class h extends C0331i implements p {

    /* renamed from: D  reason: collision with root package name */
    public final /* synthetic */ d f5007D;

    /* renamed from: E  reason: collision with root package name */
    public final /* synthetic */ C0266w f5008E;

    /* renamed from: F  reason: collision with root package name */
    public final /* synthetic */ C0780o f5009F;

    /* renamed from: G  reason: collision with root package name */
    public final /* synthetic */ C0757L f5010G;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public h(d dVar, C0266w wVar, C0780o oVar, C0757L l5, e eVar) {
        super(2, eVar);
        this.f5007D = dVar;
        this.f5008E = wVar;
        this.f5009F = oVar;
        this.f5010G = l5;
    }

    public final e create(Object obj, e eVar) {
        return new h(this.f5007D, this.f5008E, this.f5009F, this.f5010G, eVar);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((h) create((E) obj, (e) obj2)).invokeSuspend(y.f2418a);
    }

    public final Object invokeSuspend(Object obj) {
        C0298a aVar = C0298a.f4140D;
        C0110h.P(obj);
        d dVar = this.f5007D;
        dVar.c();
        return dVar.a(this.f5008E, this.f5009F, this.f5010G);
    }
}
