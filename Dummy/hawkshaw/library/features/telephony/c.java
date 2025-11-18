package com.hawkshaw.library.features.telephony;

import android.media.MediaRecorder;

public final /* synthetic */ class c implements MediaRecorder.OnErrorListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ CallRecorder f5023a;

    public /* synthetic */ c(CallRecorder callRecorder) {
        this.f5023a = callRecorder;
    }

    public final void onError(MediaRecorder mediaRecorder, int i5, int i6) {
        CallRecorder.startRecording$lambda$0(this.f5023a, mediaRecorder, i5, i6);
    }
}
