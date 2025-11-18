package com.hawkshaw.library;

import Y1.K;
import j3.C0649b;
import me.pushy.sdk.lib.jackson.databind.a;
import p3.C0867h;

public final class NotNullSingleValueVar<T> {
    private T value;

    public final T getValue(Object obj, C0867h hVar) {
        K.n(hVar, "property");
        T t4 = this.value;
        if (t4 != null) {
            return t4;
        }
        throw new NotNullSingleValueVarException(a.i(new StringBuilder(), ((C0649b) hVar).f7485G, " not initialized"));
    }

    public final void setValue(Object obj, C0867h hVar, T t4) {
        K.n(hVar, "property");
        if (this.value == null) {
            this.value = t4;
            return;
        }
        throw new NotNullSingleValueVarException(a.i(new StringBuilder(), ((C0649b) hVar).f7485G, " already initialized"));
    }
}
