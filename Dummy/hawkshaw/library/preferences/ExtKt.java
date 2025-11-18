package com.hawkshaw.library.preferences;

import Y1.K;
import l3.C0709a;

public final class ExtKt {
    public static final C0709a booleanPreference(String str, boolean z4) {
        K.n(str, "key");
        return new PreferenceProperty(str, Boolean.valueOf(z4), a.f5057L, b.f5058L);
    }

    public static /* synthetic */ C0709a booleanPreference$default(String str, boolean z4, int i5, Object obj) {
        if ((i5 & 2) != 0) {
            z4 = false;
        }
        return booleanPreference(str, z4);
    }

    public static final C0709a floatPreference(String str, float f5) {
        K.n(str, "key");
        return new PreferenceProperty(str, Float.valueOf(f5), c.f5059L, d.f5060L);
    }

    public static /* synthetic */ C0709a floatPreference$default(String str, float f5, int i5, Object obj) {
        if ((i5 & 2) != 0) {
            f5 = 0.0f;
        }
        return floatPreference(str, f5);
    }

    public static final C0709a intPreference(String str, int i5) {
        K.n(str, "key");
        return new PreferenceProperty(str, Integer.valueOf(i5), e.f5061L, f.f5062L);
    }

    public static /* synthetic */ C0709a intPreference$default(String str, int i5, int i6, Object obj) {
        if ((i6 & 2) != 0) {
            i5 = 0;
        }
        return intPreference(str, i5);
    }

    public static final C0709a longPreference(String str, long j5) {
        K.n(str, "key");
        return new PreferenceProperty(str, Long.valueOf(j5), g.f5063L, h.f5064L);
    }

    public static /* synthetic */ C0709a longPreference$default(String str, long j5, int i5, Object obj) {
        if ((i5 & 2) != 0) {
            j5 = 0;
        }
        return longPreference(str, j5);
    }

    public static final C0709a stringPreference(String str, String str2) {
        K.n(str, "key");
        return new PreferenceProperty(str, str2, i.f5065L, j.f5066L);
    }

    public static /* synthetic */ C0709a stringPreference$default(String str, String str2, int i5, Object obj) {
        if ((i5 & 2) != 0) {
            str2 = null;
        }
        return stringPreference(str, str2);
    }
}
