package com.hawkshaw.library.preferences;

import Y1.K;
import android.content.SharedPreferences;
import i3.q;
import l3.C0709a;
import p3.C0867h;

public final class PreferenceProperty<T> implements C0709a {
    private final T defaultValue;
    private final q getter;
    private final String key;
    private final q setter;

    public PreferenceProperty(String str, T t4, q qVar, q qVar2) {
        K.n(str, "key");
        K.n(qVar, "getter");
        K.n(qVar2, "setter");
        this.key = str;
        this.defaultValue = t4;
        this.getter = qVar;
        this.setter = qVar2;
    }

    public T getValue(Object obj, C0867h hVar) {
        K.n(obj, "thisRef");
        K.n(hVar, "property");
        return this.getter.d(Prefs.INSTANCE.getSharedPrefs(), this.key, this.defaultValue);
    }

    public void setValue(Object obj, C0867h hVar, T t4) {
        K.n(obj, "thisRef");
        K.n(hVar, "property");
        if (t4 == null) {
            Prefs.INSTANCE.remove(this.key);
            return;
        }
        q qVar = this.setter;
        SharedPreferences.Editor edit = Prefs.INSTANCE.getSharedPrefs().edit();
        K.m(edit, "edit(...)");
        ((SharedPreferences.Editor) qVar.d(edit, this.key, t4)).apply();
    }
}
