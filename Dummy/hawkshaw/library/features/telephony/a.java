package com.hawkshaw.library.features.telephony;

import a3.e;
import c3.C0325c;

public final class a extends C0325c {

    /* renamed from: D  reason: collision with root package name */
    public String f5013D;

    /* renamed from: E  reason: collision with root package name */
    public /* synthetic */ Object f5014E;

    /* renamed from: F  reason: collision with root package name */
    public final /* synthetic */ CallBlocker f5015F;

    /* renamed from: G  reason: collision with root package name */
    public int f5016G;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public a(CallBlocker callBlocker, e eVar) {
        super(eVar);
        this.f5015F = callBlocker;
    }

    public final Object invokeSuspend(Object obj) {
        this.f5014E = obj;
        this.f5016G |= Integer.MIN_VALUE;
        return this.f5015F.getMatch((String) null, this);
    }
}
