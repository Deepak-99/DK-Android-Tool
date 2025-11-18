package com.hawkshaw.library.preferences;

import Y1.K;
import android.content.SharedPreferences;
import i3.q;
import j3.i;

public final /* synthetic */ class e extends i implements q {

    /* renamed from: L  reason: collision with root package name */
    public static final e f5061L = new i(3, SharedPreferences.class, "getInt", "getInt(Ljava/lang/String;I)I", 0);

    public final Object d(Object obj, Object obj2, Object obj3) {
        SharedPreferences sharedPreferences = (SharedPreferences) obj;
        int intValue = ((Number) obj3).intValue();
        K.n(sharedPreferences, "p0");
        return Integer.valueOf(sharedPreferences.getInt((String) obj2, intValue));
    }
}
