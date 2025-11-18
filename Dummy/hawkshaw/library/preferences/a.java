package com.hawkshaw.library.preferences;

import Y1.K;
import android.content.SharedPreferences;
import i3.q;
import j3.i;

public final /* synthetic */ class a extends i implements q {

    /* renamed from: L  reason: collision with root package name */
    public static final a f5057L = new i(3, SharedPreferences.class, "getBoolean", "getBoolean(Ljava/lang/String;Z)Z", 0);

    public final Object d(Object obj, Object obj2, Object obj3) {
        SharedPreferences sharedPreferences = (SharedPreferences) obj;
        boolean booleanValue = ((Boolean) obj3).booleanValue();
        K.n(sharedPreferences, "p0");
        return Boolean.valueOf(sharedPreferences.getBoolean((String) obj2, booleanValue));
    }
}
