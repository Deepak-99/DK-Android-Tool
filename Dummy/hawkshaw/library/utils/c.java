package com.hawkshaw.library.utils;

import W2.y;
import Y1.C0110h;
import Y1.K;
import a3.e;
import a3.k;
import android.app.AlertDialog;
import androidx.activity.o;
import androidx.lifecycle.C0258n;
import androidx.lifecycle.C0259o;
import androidx.lifecycle.C0268y;
import androidx.lifecycle.j0;
import androidx.lifecycle.k0;
import androidx.lifecycle.r;
import b.C0282e;
import b3.C0298a;
import c3.C0331i;
import i3.p;
import t3.C0990k;
import t3.E;
import t3.N;
import u3.C1039c;
import y3.t;
import z3.C1131d;

public final class c extends C0331i implements p {

    /* renamed from: D  reason: collision with root package name */
    public int f5071D;

    /* renamed from: E  reason: collision with root package name */
    public final /* synthetic */ o f5072E;

    /* renamed from: F  reason: collision with root package name */
    public final /* synthetic */ AlertDialog.Builder f5073F;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public c(o oVar, AlertDialog.Builder builder, e eVar) {
        super(2, eVar);
        this.f5072E = oVar;
        this.f5073F = builder;
    }

    public final e create(Object obj, e eVar) {
        return new c(this.f5072E, this.f5073F, eVar);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((c) create((E) obj, (e) obj2)).invokeSuspend(y.f2418a);
    }

    public final Object invokeSuspend(Object obj) {
        C0298a aVar = C0298a.f4140D;
        int i5 = this.f5071D;
        if (i5 == 0) {
            C0110h.P(obj);
            C0259o lifecycle = this.f5072E.getLifecycle();
            K.m(lifecycle, "<get-lifecycle>(...)");
            C0258n nVar = C0258n.f3910H;
            C1131d dVar = N.f9290a;
            C1039c cVar = ((C1039c) t.f10013a).f9625H;
            boolean y02 = cVar.y0(getContext());
            AlertDialog.Builder builder = this.f5073F;
            if (!y02) {
                C0258n nVar2 = ((C0268y) lifecycle).f3923d;
                if (nVar2 == C0258n.f3906D) {
                    throw new r(0);
                } else if (nVar2.compareTo(nVar) >= 0) {
                    builder.show();
                }
            }
            AlertDialogKt$showAlertDialog$2$3$invokeSuspend$$inlined$withResumed$1 alertDialogKt$showAlertDialog$2$3$invokeSuspend$$inlined$withResumed$1 = new AlertDialogKt$showAlertDialog$2$3$invokeSuspend$$inlined$withResumed$1(builder);
            this.f5071D = 1;
            C0990k kVar = new C0990k(1, K.U(this));
            kVar.p();
            C0282e eVar = new C0282e(lifecycle, kVar, alertDialogKt$showAlertDialog$2$3$invokeSuspend$$inlined$withResumed$1);
            if (y02) {
                cVar.w(k.f3728D, new j0(lifecycle, eVar, 0));
            } else {
                lifecycle.a(eVar);
            }
            kVar.r(new k0(cVar, lifecycle, eVar, 0));
            if (kVar.o() == aVar) {
                return aVar;
            }
        } else if (i5 == 1) {
            C0110h.P(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return y.f2418a;
    }
}
