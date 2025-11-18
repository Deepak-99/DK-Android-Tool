package com.hawkshaw.library.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.hawkshaw.library.features.telephony.TelephonyCallbackService;
import com.hawkshaw.library.logger.Logger;
import com.hawkshaw.library.utils.MiscKt;
import i.C0528x;
import j3.f;

public final class CallReceiver extends BroadcastReceiver {
    public static final Companion Companion = new Companion((f) null);
    private static final String TAG = "CallReceiver";

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(f fVar) {
            this();
        }
    }

    public void onReceive(Context context, Intent intent) {
        Context context2 = context;
        Intent intent2 = intent;
        if (context2 != null && intent2 != null) {
            String stringExtra = intent2.getStringExtra("state");
            String stringExtra2 = intent2.getStringExtra("incoming_number");
            Logger logger = Logger.INSTANCE;
            StringBuilder g5 = C0528x.g("OnReceive: Action: ", intent.getAction(), ", State: ", stringExtra, ", PhoneNumber: ");
            g5.append(stringExtra2);
            Logger.v$default(logger, TAG, g5.toString(), false, 4, (Object) null);
            if (stringExtra2 != null) {
                Intent intent3 = new Intent(context2, TelephonyCallbackService.class);
                intent3.setAction(intent.getAction());
                intent3.putExtra("state", stringExtra);
                intent3.putExtra("incoming_number", stringExtra2);
                MiscKt.startService$default(intent3, (String) null, (String) null, (Context) null, 14, (Object) null);
            }
        }
    }
}
