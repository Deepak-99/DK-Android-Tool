package com.hawkshaw.library.preferences;

import Y1.K;
import android.content.SharedPreferences;
import i3.q;
import j3.i;

public final /* synthetic */ class c extends i implements q {

    /* renamed from: L  reason: collision with root package name */
    public static final c f5059L = new i(3, SharedPreferences.class, "getFloat", "getFloat(Ljava/lang/String;F)F", 0);

    public final Object d(Object obj, Object obj2, Object obj3) {
        SharedPreferences sharedPreferences = (SharedPreferences) obj;
        float floatValue = ((Number) obj3).floatValue();
        K.n(sharedPreferences, "p0");
        return Float.valueOf(sharedPreferences.getFloat((String) obj2, floatValue));
    }
}
