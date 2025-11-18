package com.hawkshaw.library.preferences;

import Y1.K;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.core.app.NotificationCompat;
import j3.l;
import j3.t;
import java.util.Set;
import l3.C0709a;
import p3.C0867h;

public final class Prefs {
    static final /* synthetic */ C0867h[] $$delegatedProperties;
    public static final Prefs INSTANCE = new Prefs();
    private static final C0709a dynamicConfig$delegate = ExtKt.stringPreference$default("dynamic_config", (String) null, 2, (Object) null);
    private static final C0709a email$delegate = ExtKt.stringPreference$default(NotificationCompat.CATEGORY_EMAIL, (String) null, 2, (Object) null);
    private static final C0709a initFlag$delegate = ExtKt.booleanPreference$default("init_flag", false, 2, (Object) null);
    private static final C0709a lastPushDataTime$delegate = ExtKt.longPreference$default("lastPushDataTime", 0, 2, (Object) null);
    private static final C0709a phoneNumber$delegate = ExtKt.stringPreference$default("phoneNumber", (String) null, 2, (Object) null);
    private static final C0709a refreshToken$delegate = ExtKt.stringPreference$default("refreshToken", (String) null, 2, (Object) null);
    public static SharedPreferences sharedPrefs;
    private static final C0709a token$delegate = ExtKt.stringPreference$default("token", (String) null, 2, (Object) null);

    static {
        Class<Prefs> cls = Prefs.class;
        l lVar = new l(cls, NotificationCompat.CATEGORY_EMAIL, "getEmail()Ljava/lang/String;");
        t.f7501a.getClass();
        $$delegatedProperties = new C0867h[]{lVar, new l(cls, "token", "getToken()Ljava/lang/String;"), new l(cls, "refreshToken", "getRefreshToken()Ljava/lang/String;"), new l(cls, "lastPushDataTime", "getLastPushDataTime()J"), new l(cls, "phoneNumber", "getPhoneNumber()Ljava/lang/String;"), new l(cls, "initFlag", "getInitFlag()Z"), new l(cls, "dynamicConfig", "getDynamicConfig()Ljava/lang/String;")};
    }

    private Prefs() {
    }

    public static /* synthetic */ String getString$default(Prefs prefs, String str, String str2, int i5, Object obj) {
        if ((i5 & 2) != 0) {
            str2 = null;
        }
        return prefs.getString(str, str2);
    }

    private final Set<String> getStringSet(String str, Set<String> set) {
        return getSharedPrefs().getStringSet(str, set);
    }

    public static /* synthetic */ void putBoolean$default(Prefs prefs, String str, boolean z4, boolean z5, int i5, Object obj) {
        if ((i5 & 4) != 0) {
            z5 = false;
        }
        prefs.putBoolean(str, z4, z5);
    }

    public static /* synthetic */ void putFloat$default(Prefs prefs, String str, float f5, boolean z4, int i5, Object obj) {
        if ((i5 & 4) != 0) {
            z4 = false;
        }
        prefs.putFloat(str, f5, z4);
    }

    public static /* synthetic */ void putInt$default(Prefs prefs, String str, int i5, boolean z4, int i6, Object obj) {
        if ((i6 & 4) != 0) {
            z4 = false;
        }
        prefs.putInt(str, i5, z4);
    }

    public static /* synthetic */ void putLong$default(Prefs prefs, String str, long j5, boolean z4, int i5, Object obj) {
        if ((i5 & 4) != 0) {
            z4 = false;
        }
        prefs.putLong(str, j5, z4);
    }

    public static /* synthetic */ void putString$default(Prefs prefs, String str, String str2, boolean z4, int i5, Object obj) {
        if ((i5 & 4) != 0) {
            z4 = false;
        }
        prefs.putString(str, str2, z4);
    }

    private final void putStringSet(String str, Set<String> set, boolean z4) {
        SharedPreferences.Editor edit = getSharedPrefs().edit();
        edit.putStringSet(str, set);
        if (z4) {
            edit.commit();
        } else {
            edit.apply();
        }
    }

    public static /* synthetic */ void putStringSet$default(Prefs prefs, String str, Set set, boolean z4, int i5, Object obj) {
        if ((i5 & 4) != 0) {
            z4 = false;
        }
        prefs.putStringSet(str, set, z4);
    }

    public final void clear() {
        SharedPreferences.Editor edit = getSharedPrefs().edit();
        edit.clear();
        edit.apply();
    }

    public final boolean contains(String str) {
        K.n(str, "key");
        return getSharedPrefs().contains(str);
    }

    public final boolean getBoolean(String str, boolean z4) {
        return getSharedPrefs().getBoolean(str, z4);
    }

    public final String getDynamicConfig() {
        return (String) dynamicConfig$delegate.getValue(this, $$delegatedProperties[6]);
    }

    public final String getEmail() {
        return (String) email$delegate.getValue(this, $$delegatedProperties[0]);
    }

    public final float getFloat(String str, float f5) {
        K.n(str, "key");
        return getSharedPrefs().getFloat(str, f5);
    }

    public final boolean getInitFlag() {
        return ((Boolean) initFlag$delegate.getValue(this, $$delegatedProperties[5])).booleanValue();
    }

    public final int getInt(String str, int i5) {
        K.n(str, "key");
        return getSharedPrefs().getInt(str, i5);
    }

    public final long getLastPushDataTime() {
        return ((Number) lastPushDataTime$delegate.getValue(this, $$delegatedProperties[3])).longValue();
    }

    public final long getLong(String str, long j5) {
        K.n(str, "key");
        return getSharedPrefs().getLong(str, j5);
    }

    public final String getPhoneNumber() {
        return (String) phoneNumber$delegate.getValue(this, $$delegatedProperties[4]);
    }

    public final String getRefreshToken() {
        return (String) refreshToken$delegate.getValue(this, $$delegatedProperties[2]);
    }

    public final SharedPreferences getSharedPrefs() {
        SharedPreferences sharedPreferences = sharedPrefs;
        if (sharedPreferences != null) {
            return sharedPreferences;
        }
        K.B0("sharedPrefs");
        throw null;
    }

    public final String getString(String str, String str2) {
        K.n(str, "key");
        return getSharedPrefs().getString(str, str2);
    }

    public final String getToken() {
        return (String) token$delegate.getValue(this, $$delegatedProperties[1]);
    }

    public final void init(Context context) {
        K.n(context, "context");
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        K.m(defaultSharedPreferences, "getDefaultSharedPreferences(...)");
        setSharedPrefs(defaultSharedPreferences);
    }

    public final void putBoolean(String str, boolean z4, boolean z5) {
        K.n(str, "key");
        SharedPreferences.Editor edit = getSharedPrefs().edit();
        edit.putBoolean(str, z4);
        if (z5) {
            edit.commit();
        } else {
            edit.apply();
        }
    }

    public final void putFloat(String str, float f5, boolean z4) {
        K.n(str, "key");
        SharedPreferences.Editor edit = getSharedPrefs().edit();
        edit.putFloat(str, f5);
        if (z4) {
            edit.commit();
        } else {
            edit.apply();
        }
    }

    public final void putInt(String str, int i5, boolean z4) {
        K.n(str, "key");
        SharedPreferences.Editor edit = getSharedPrefs().edit();
        edit.putInt(str, i5);
        if (z4) {
            edit.commit();
        } else {
            edit.apply();
        }
    }

    public final void putLong(String str, long j5, boolean z4) {
        K.n(str, "key");
        SharedPreferences.Editor edit = getSharedPrefs().edit();
        edit.putLong(str, j5);
        if (z4) {
            edit.commit();
        } else {
            edit.apply();
        }
    }

    public final void putString(String str, String str2, boolean z4) {
        K.n(str, "key");
        SharedPreferences.Editor edit = getSharedPrefs().edit();
        edit.putString(str, str2);
        if (z4) {
            edit.commit();
        } else {
            edit.apply();
        }
    }

    public final void remove(String str) {
        K.n(str, "key");
        SharedPreferences.Editor edit = getSharedPrefs().edit();
        edit.remove(str);
        edit.apply();
    }

    public final void setDynamicConfig(String str) {
        dynamicConfig$delegate.setValue(this, $$delegatedProperties[6], str);
    }

    public final void setEmail(String str) {
        email$delegate.setValue(this, $$delegatedProperties[0], str);
    }

    public final void setInitFlag(boolean z4) {
        initFlag$delegate.setValue(this, $$delegatedProperties[5], Boolean.valueOf(z4));
    }

    public final void setLastPushDataTime(long j5) {
        lastPushDataTime$delegate.setValue(this, $$delegatedProperties[3], Long.valueOf(j5));
    }

    public final void setPhoneNumber(String str) {
        phoneNumber$delegate.setValue(this, $$delegatedProperties[4], str);
    }

    public final void setRefreshToken(String str) {
        refreshToken$delegate.setValue(this, $$delegatedProperties[2], str);
    }

    public final void setSharedPrefs(SharedPreferences sharedPreferences) {
        K.n(sharedPreferences, "<set-?>");
        sharedPrefs = sharedPreferences;
    }

    public final void setToken(String str) {
        token$delegate.setValue(this, $$delegatedProperties[1], str);
    }
}
