package com.hawkshaw.library.preferences;

import Y1.K;
import android.content.SharedPreferences;
import i3.q;
import j3.i;

public final /* synthetic */ class h extends i implements q {

    /* renamed from: L  reason: collision with root package name */
    public static final h f5064L = new i(3, SharedPreferences.Editor.class, "putLong", "putLong(Ljava/lang/String;J)Landroid/content/SharedPreferences$Editor;", 0);

    public final Object d(Object obj, Object obj2, Object obj3) {
        SharedPreferences.Editor editor = (SharedPreferences.Editor) obj;
        long longValue = ((Number) obj3).longValue();
        K.n(editor, "p0");
        return editor.putLong((String) obj2, longValue);
    }
}
