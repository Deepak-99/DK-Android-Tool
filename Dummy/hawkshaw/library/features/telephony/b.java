package com.hawkshaw.library.features.telephony;

import a3.e;
import c3.C0325c;

public final class b extends C0325c {

    /* renamed from: D  reason: collision with root package name */
    public CallBlocker f5017D;

    /* renamed from: E  reason: collision with root package name */
    public String f5018E;

    /* renamed from: F  reason: collision with root package name */
    public int f5019F;

    /* renamed from: G  reason: collision with root package name */
    public /* synthetic */ Object f5020G;

    /* renamed from: H  reason: collision with root package name */
    public final /* synthetic */ CallBlocker f5021H;

    /* renamed from: I  reason: collision with root package name */
    public int f5022I;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public b(CallBlocker callBlocker, e eVar) {
        super(eVar);
        this.f5021H = callBlocker;
    }

    public final Object invokeSuspend(Object obj) {
        this.f5020G = obj;
        this.f5022I |= Integer.MIN_VALUE;
        return this.f5021H.onCallStateChanged(0, (String) null, this);
    }
}
