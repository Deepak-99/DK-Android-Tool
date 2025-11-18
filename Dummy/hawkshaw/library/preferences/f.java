package com.hawkshaw.library.preferences;

import Y1.K;
import android.content.SharedPreferences;
import i3.q;
import j3.i;

public final /* synthetic */ class f extends i implements q {

    /* renamed from: L  reason: collision with root package name */
    public static final f f5062L = new i(3, SharedPreferences.Editor.class, "putInt", "putInt(Ljava/lang/String;I)Landroid/content/SharedPreferences$Editor;", 0);

    public final Object d(Object obj, Object obj2, Object obj3) {
        SharedPreferences.Editor editor = (SharedPreferences.Editor) obj;
        int intValue = ((Number) obj3).intValue();
        K.n(editor, "p0");
        return editor.putInt((String) obj2, intValue);
    }
}
