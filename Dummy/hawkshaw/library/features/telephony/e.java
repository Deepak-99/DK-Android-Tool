package com.hawkshaw.library.features.telephony;

import c3.C0325c;
import com.hawkshaw.library.features.telephony.CallRecorder;

public final class e extends C0325c {

    /* renamed from: D  reason: collision with root package name */
    public CallRecorder.Companion f5028D;

    /* renamed from: E  reason: collision with root package name */
    public String f5029E;

    /* renamed from: F  reason: collision with root package name */
    public boolean f5030F;

    /* renamed from: G  reason: collision with root package name */
    public /* synthetic */ Object f5031G;

    /* renamed from: H  reason: collision with root package name */
    public final /* synthetic */ CallRecorder.Companion f5032H;

    /* renamed from: I  reason: collision with root package name */
    public int f5033I;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public e(CallRecorder.Companion companion, a3.e eVar) {
        super(eVar);
        this.f5032H = companion;
    }

    public final Object invokeSuspend(Object obj) {
        this.f5031G = obj;
        this.f5033I |= Integer.MIN_VALUE;
        return this.f5032H.onCallStateChanged$library_release(0, (String) null, false, this);
    }
}
