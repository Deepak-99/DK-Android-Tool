package com.hawkshaw.library.preferences;

import Y1.K;
import android.content.SharedPreferences;
import i3.q;
import j3.i;

public final /* synthetic */ class g extends i implements q {

    /* renamed from: L  reason: collision with root package name */
    public static final g f5063L = new i(3, SharedPreferences.class, "getLong", "getLong(Ljava/lang/String;J)J", 0);

    public final Object d(Object obj, Object obj2, Object obj3) {
        SharedPreferences sharedPreferences = (SharedPreferences) obj;
        long longValue = ((Number) obj3).longValue();
        K.n(sharedPreferences, "p0");
        return Long.valueOf(sharedPreferences.getLong((String) obj2, longValue));
    }
}
