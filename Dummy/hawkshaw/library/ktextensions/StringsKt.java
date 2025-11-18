package com.hawkshaw.library.ktextensions;

import Y1.K;
import java.util.Locale;
import r3.j;

public final class StringsKt {
    public static final String getEmpty(CharSequence charSequence) {
        return (charSequence == null || j.U(charSequence)) ? "" : charSequence.toString();
    }

    public static final int hash(String str) {
        K.n(str, "<this>");
        String lowerCase = str.toLowerCase(Locale.ROOT);
        K.m(lowerCase, "toLowerCase(...)");
        return Math.abs(lowerCase.hashCode());
    }

    public static final int hash(CharSequence charSequence) {
        K.n(charSequence, "<this>");
        return hash(charSequence.toString());
    }
}
