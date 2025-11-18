package com.hawkshaw.library.features.telephony.messages;

import android.content.Context;
import c3.C0325c;

public final class a extends C0325c {

    /* renamed from: D  reason: collision with root package name */
    public /* synthetic */ Object f5042D;

    /* renamed from: E  reason: collision with root package name */
    public int f5043E;

    public final Object invokeSuspend(Object obj) {
        this.f5042D = obj;
        this.f5043E |= Integer.MIN_VALUE;
        return GetMessagesKt.pushMessages((Context) null, this);
    }
}
