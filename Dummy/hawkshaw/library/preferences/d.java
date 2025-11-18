package com.hawkshaw.library.preferences;

import Y1.K;
import android.content.SharedPreferences;
import i3.q;
import j3.i;

public final /* synthetic */ class d extends i implements q {

    /* renamed from: L  reason: collision with root package name */
    public static final d f5060L = new i(3, SharedPreferences.Editor.class, "putFloat", "putFloat(Ljava/lang/String;F)Landroid/content/SharedPreferences$Editor;", 0);

    public final Object d(Object obj, Object obj2, Object obj3) {
        SharedPreferences.Editor editor = (SharedPreferences.Editor) obj;
        float floatValue = ((Number) obj3).floatValue();
        K.n(editor, "p0");
        return editor.putFloat((String) obj2, floatValue);
    }
}
