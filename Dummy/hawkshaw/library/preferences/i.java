package com.hawkshaw.library.preferences;

import Y1.K;
import android.content.SharedPreferences;
import i3.q;

public final /* synthetic */ class i extends j3.i implements q {

    /* renamed from: L  reason: collision with root package name */
    public static final i f5065L = new j3.i(3, SharedPreferences.class, "getString", "getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", 0);

    public final Object d(Object obj, Object obj2, Object obj3) {
        SharedPreferences sharedPreferences = (SharedPreferences) obj;
        K.n(sharedPreferences, "p0");
        return sharedPreferences.getString((String) obj2, (String) obj3);
    }
}
