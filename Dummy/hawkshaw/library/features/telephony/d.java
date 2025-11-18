package com.hawkshaw.library.features.telephony;

import a3.e;
import c3.C0325c;
import com.hawkshaw.library.features.telephony.CallRecorder;

public final class d extends C0325c {

    /* renamed from: D  reason: collision with root package name */
    public String f5024D;

    /* renamed from: E  reason: collision with root package name */
    public /* synthetic */ Object f5025E;

    /* renamed from: F  reason: collision with root package name */
    public final /* synthetic */ CallRecorder.Companion f5026F;

    /* renamed from: G  reason: collision with root package name */
    public int f5027G;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public d(CallRecorder.Companion companion, e eVar) {
        super(eVar);
        this.f5026F = companion;
    }

    public final Object invokeSuspend(Object obj) {
        this.f5025E = obj;
        this.f5027G |= Integer.MIN_VALUE;
        return this.f5026F.getMatch((String) null, this);
    }
}
