package com.hawkshaw.library.preferences;

import Y1.K;
import android.content.SharedPreferences;
import i3.q;
import j3.i;

public final /* synthetic */ class j extends i implements q {

    /* renamed from: L  reason: collision with root package name */
    public static final j f5066L = new i(3, SharedPreferences.Editor.class, "putString", "putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;", 0);

    public final Object d(Object obj, Object obj2, Object obj3) {
        SharedPreferences.Editor editor = (SharedPreferences.Editor) obj;
        K.n(editor, "p0");
        return editor.putString((String) obj2, (String) obj3);
    }
}
