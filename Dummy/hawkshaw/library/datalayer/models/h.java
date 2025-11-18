package com.hawkshaw.library.datalayer.models;

import com.hawkshaw.library.datalayer.models.Command;
import i3.C0542a;
import j3.j;
import java.lang.annotation.Annotation;

public final class h extends j implements C0542a {

    /* renamed from: D  reason: collision with root package name */
    public static final h f4892D = new j(0);

    public final Object invoke() {
        return v3.j.e("com.hawkshaw.library.datalayer.models.Command.GetLocationRequest.Priority", Command.GetLocationRequest.Priority.values(), new String[]{"PRIORITY_HIGH_ACCURACY", "PRIORITY_BALANCED_POWER_ACCURACY", "PRIORITY_LOW_POWER", "PRIORITY_PASSIVE"}, new Annotation[][]{null, null, null, null});
    }
}
