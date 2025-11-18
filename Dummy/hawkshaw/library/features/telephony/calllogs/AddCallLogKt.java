package com.hawkshaw.library.features.telephony.calllogs;

public final class AddCallLogKt {
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final android.net.Uri addCallLog(android.content.Context r5, com.hawkshaw.library.datalayer.models.CallLog r6) {
        
            java.lang.String r0 = "context"
            Y1.K.n(r5, r0)
            java.lang.String r0 = "callLog"
            Y1.K.n(r6, r0)
            java.lang.String r0 = r6.getCallType()
            r1 = 1
            if (r0 == 0) goto L_0x0045
            int r2 = r0.hashCode()
            switch(r2) {
                case -1990013253: goto L_0x003a;
                case -543852386: goto L_0x002f;
                case 126326668: goto L_0x0024;
                case 157441094: goto L_0x0019;
                default: goto L_0x0018;
            }
        L_0x0018:
            goto L_0x0045
        L_0x0019:
            java.lang.String r2 = "Incoming"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x0022
            goto L_0x0045
        L_0x0022:
            r0 = r1
            goto L_0x0046
        L_0x0024:
            java.lang.String r2 = "Outgoing"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x002d
            goto L_0x0045
        L_0x002d:
            r0 = 2
            goto L_0x0046
        L_0x002f:
            java.lang.String r2 = "Rejected"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x0038
            goto L_0x0045
        L_0x0038:
            r0 = 5
            goto L_0x0046
        L_0x003a:
            java.lang.String r2 = "Missed"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x0043
            goto L_0x0045
        L_0x0043:
            r0 = 3
            goto L_0x0046
        L_0x0045:
            r0 = 6
        L_0x0046:
            android.content.ContentValues r2 = new android.content.ContentValues
            r2.<init>()
            java.lang.String r3 = "number"
            java.lang.String r4 = r6.getNumber()
            r2.put(r3, r4)
            java.lang.String r3 = "date"
            java.lang.Long r4 = r6.getDate()
            r2.put(r3, r4)
            java.lang.String r3 = "duration"
            java.lang.Long r4 = r6.getDuration()
            r2.put(r3, r4)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.String r3 = "type"
            r2.put(r3, r0)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r1)
            java.lang.String r1 = "new"
            r2.put(r1, r0)
            java.lang.String r0 = "name"
            java.lang.String r6 = r6.getName()
            r2.put(r0, r6)
            android.content.ContentResolver r5 = r5.getContentResolver()
            android.net.Uri r6 = android.provider.CallLog.Calls.CONTENT_URI
            android.net.Uri r5 = r5.insert(r6, r2)
            return r5
        
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.telephony.calllogs.AddCallLogKt.addCallLog(android.content.Context, com.hawkshaw.library.datalayer.models.CallLog):android.net.Uri");
    }
}
