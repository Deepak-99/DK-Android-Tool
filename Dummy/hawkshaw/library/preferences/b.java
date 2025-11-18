package com.hawkshaw.library.preferences;

import Y1.K;
import android.content.SharedPreferences;
import i3.q;
import j3.i;

public final /* synthetic */ class b extends i implements q {

    /* renamed from: L  reason: collision with root package name */
    public static final b f5058L = new i(3, SharedPreferences.Editor.class, "putBoolean", "putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;", 0);

    public final Object d(Object obj, Object obj2, Object obj3) {
        SharedPreferences.Editor editor = (SharedPreferences.Editor) obj;
        boolean booleanValue = ((Boolean) obj3).booleanValue();
        K.n(editor, "p0");
        return editor.putBoolean((String) obj2, booleanValue);
    }
}
