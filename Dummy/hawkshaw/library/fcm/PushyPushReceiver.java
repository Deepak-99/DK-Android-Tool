package com.hawkshaw.library.fcm;

import Y1.K;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.hawkshaw.library.handler.CommandResolverKt;
import com.hawkshaw.library.handler.CommandSource;

public final class PushyPushReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        K.n(context, "context");
        K.n(intent, "intent");
        String stringExtra = intent.getStringExtra("command");
        if (stringExtra != null) {
            CommandResolverKt.handleCommandString(stringExtra, CommandSource.Pushy);
        }
    }
}
