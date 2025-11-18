package com.hawkshaw.library.features.media.camera;

import A.d;

public final /* synthetic */ class a implements Runnable {

    /* renamed from: D  reason: collision with root package name */
    public final /* synthetic */ int f4974D;

    /* renamed from: E  reason: collision with root package name */
    public final /* synthetic */ d f4975E;

    public /* synthetic */ a(d dVar, int i5) {
        this.f4974D = i5;
        this.f4975E = dVar;
    }

    public final void run() {
        int i5 = this.f4974D;
        d dVar = this.f4975E;
        switch (i5) {
            case 0:
                RecordVideoKt.recordVideo$releaseResources$lambda$2(dVar);
                return;
            default:
                TakePictureKt.takePicture$releaseResources$lambda$3(dVar);
                return;
        }
    }
}
