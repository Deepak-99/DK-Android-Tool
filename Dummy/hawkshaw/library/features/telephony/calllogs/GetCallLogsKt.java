package com.hawkshaw.library.features.telephony.calllogs;

import X2.q;
import a3.e;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Build;
import android.provider.CallLog;
import java.util.ArrayList;
import me.pushy.sdk.lib.jackson.core.JsonLocation;
import r3.j;

public final class GetCallLogsKt {
    public static final Object getCallLogs(ContentResolver contentResolver, e eVar) {
        int i5;
        Long l5;
        Integer num;
        String str;
        Cursor query = contentResolver.query(CallLog.Calls.CONTENT_URI, new String[]{"number", "name", "date", "duration", "type", "new", "_id", "subscription_id", "features", "voicemail_uri", "subscription_id", "last_modified"}, (String) null, (String[]) null, "date DESC");
        if (query == null) {
            return q.f2602D;
        }
        int columnIndex = query.getColumnIndex("number");
        int columnIndex2 = query.getColumnIndex("name");
        int columnIndex3 = query.getColumnIndex("date");
        int columnIndex4 = query.getColumnIndex("duration");
        int columnIndex5 = query.getColumnIndex("type");
        int columnIndex6 = query.getColumnIndex("new");
        int columnIndex7 = query.getColumnIndex("_id");
        int columnIndex8 = query.getColumnIndex("subscription_id");
        int columnIndex9 = query.getColumnIndex("features");
        int columnIndex10 = query.getColumnIndex("voicemail_uri");
        int columnIndex11 = query.getColumnIndex("subscription_id");
        int columnIndex12 = query.getColumnIndex("last_modified");
        ArrayList arrayList = new ArrayList();
        int i6 = JsonLocation.MAX_CONTENT_SNIPPET;
        while (query.moveToNext()) {
            int i7 = i6 - 1;
            if (i6 <= 0) {
                break;
            }
            Integer num2 = null;
            String string = query.isNull(columnIndex7) ? null : query.getString(columnIndex7);
            String string2 = query.isNull(columnIndex2) ? null : query.getString(columnIndex2);
            String string3 = query.isNull(columnIndex) ? null : query.getString(columnIndex);
            int i8 = columnIndex;
            int i9 = columnIndex2;
            if (query.isNull(columnIndex3)) {
                i5 = columnIndex3;
                l5 = null;
            } else {
                i5 = columnIndex3;
                l5 = new Long(query.getLong(columnIndex3));
            }
            Long l6 = query.isNull(columnIndex4) ? null : new Long(query.getLong(columnIndex4));
            String string4 = query.isNull(columnIndex5) ? null : query.getString(columnIndex5);
            String string5 = query.isNull(columnIndex6) ? null : query.getString(columnIndex6);
            String string6 = query.isNull(columnIndex8) ? null : query.getString(columnIndex8);
            Integer num3 = query.isNull(columnIndex9) ? null : new Integer(query.getInt(columnIndex9));
            String string7 = query.isNull(columnIndex10) ? null : query.getString(columnIndex10);
            String string8 = query.isNull(columnIndex11) ? null : query.getString(columnIndex11);
            Long l7 = query.isNull(columnIndex12) ? null : new Long(query.getLong(columnIndex12));
            if (Build.VERSION.SDK_INT >= 29) {
                int columnIndex13 = query.getColumnIndex("call_screening_app_name");
                int columnIndex14 = query.getColumnIndex("block_reason");
                String string9 = query.isNull(columnIndex13) ? null : query.getString(columnIndex13);
                if (!query.isNull(columnIndex14)) {
                    num2 = new Integer(query.getInt(columnIndex14));
                }
                str = string9;
                num = num2;
            } else {
                str = null;
                num = null;
            }
            arrayList.add(new com.hawkshaw.library.datalayer.models.CallLog(string, (string2 == null || j.U(string2)) ? string3 : string2, string3, l5, l6, string4, string5, string6, num3, string7, string8, l7, str, num));
            columnIndex = i8;
            columnIndex2 = i9;
            i6 = i7;
            columnIndex3 = i5;
        }
        query.close();
        return arrayList;
    }
}
