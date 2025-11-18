package com.hawkshaw.library.features.telephony;

import E0.C0010a;
import android.media.MediaRecorder;
import android.os.Build;
import i3.C0542a;
import j3.j;

public final class f extends j implements C0542a {

    /* renamed from: D  reason: collision with root package name */
    public final /* synthetic */ CallRecorder f5034D;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public f(CallRecorder callRecorder) {
        super(0);
        this.f5034D = callRecorder;
    }

    public final Object invoke() {
        if (Build.VERSION.SDK_INT < 31) {
            return new MediaRecorder();
        }
        C0010a.s();
        return N1.f.a(this.f5034D.context);
    }
}
