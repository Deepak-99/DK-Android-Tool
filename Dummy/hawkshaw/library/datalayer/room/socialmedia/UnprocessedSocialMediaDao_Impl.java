package com.hawkshaw.library.datalayer.room.socialmedia;

import C1.d;
import D1.a;
import G1.b;
import a3.e;
import android.os.CancellationSignal;
import androidx.room.A;
import androidx.room.C0276g;
import androidx.room.D;
import androidx.room.F;
import androidx.room.i;
import androidx.room.j;
import com.hawkshaw.library.datalayer.models.PackageName;
import com.hawkshaw.library.datalayer.room.socialmedia.SocialMediaEntity;
import java.util.Collections;
import java.util.List;
import me.pushy.sdk.lib.jackson.databind.deser.std.StdKeyDeserializer;
import s.C0933b;

public final class UnprocessedSocialMediaDao_Impl implements UnprocessedSocialMediaDao {
    /* access modifiers changed from: private */
    public final A __db;
    /* access modifiers changed from: private */
    public final i __deletionAdapterOfUnprocessedSocialMediaEntity;
    /* access modifiers changed from: private */
    public final j __insertionAdapterOfUnprocessedSocialMediaEntity;
    /* access modifiers changed from: private */
    public final F __preparedStmtOfNukeTable;
    /* access modifiers changed from: private */
    public final i __updateAdapterOfUnprocessedSocialMediaEntity;

    public UnprocessedSocialMediaDao_Impl(A a5) {
        this.__db = a5;
        this.__insertionAdapterOfUnprocessedSocialMediaEntity = new h(this, a5);
        this.__deletionAdapterOfUnprocessedSocialMediaEntity = new C1.j(this, a5, 4);
        this.__updateAdapterOfUnprocessedSocialMediaEntity = new i(this, a5);
        this.__preparedStmtOfNukeTable = new a(this, a5, 4);
    }

    /* access modifiers changed from: private */
    public String __PackageName_enumToString(PackageName packageName) {
        switch (g.f4956b[packageName.ordinal()]) {
            case 1:
                return "FACEBOOK";
            case 2:
                return "FACEBOOK_MESSENGER";
            case 3:
                return "WHATSAPP";
            case 4:
                return "GB_WHATSAPP";
            case 5:
                return "WHATSAPP_BUSINESS";
            case 6:
                return "INSTAGRAM";
            case 7:
                return "TELEGRAM";
            case 8:
                return "TELEGRAM_WEB";
            case 9:
                return "SKYPE";
            case 10:
                return "KIK";
            case 11:
                return "WECHAT";
            case 12:
                return "LINE";
            case 13:
                return "GMAIL";
            case StdKeyDeserializer.TYPE_URL /*14*/:
                return "TINDER";
            case StdKeyDeserializer.TYPE_CLASS /*15*/:
                return "SNAPCHAT";
            case 16:
                return "HIKE";
            default:
                throw new IllegalArgumentException("Can't convert enum to string, unknown enum value: " + packageName);
        }
    }

    /* access modifiers changed from: private */
    public PackageName __PackageName_stringToEnum(String str) {
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case -1820310312:
                if (str.equals("TINDER")) {
                    c5 = 0;
                    break;
                }
                break;
            case -1738440922:
                if (str.equals("WECHAT")) {
                    c5 = 1;
                    break;
                }
                break;
            case -1577559662:
                if (str.equals("WHATSAPP")) {
                    c5 = 2;
                    break;
                }
                break;
            case -1479469166:
                if (str.equals("INSTAGRAM")) {
                    c5 = 3;
                    break;
                }
                break;
            case -577840895:
                if (str.equals("TELEGRAM")) {
                    c5 = 4;
                    break;
                }
                break;
            case -554816563:
                if (str.equals("WHATSAPP_BUSINESS")) {
                    c5 = 5;
                    break;
                }
                break;
            case -306558858:
                if (str.equals("GB_WHATSAPP")) {
                    c5 = 6;
                    break;
                }
                break;
            case 74413:
                if (str.equals("KIK")) {
                    c5 = 7;
                    break;
                }
                break;
            case 2217499:
                if (str.equals("HIKE")) {
                    c5 = 8;
                    break;
                }
                break;
            case 2336756:
                if (str.equals("LINE")) {
                    c5 = 9;
                    break;
                }
                break;
            case 67928702:
                if (str.equals("GMAIL")) {
                    c5 = 10;
                    break;
                }
                break;
            case 78974646:
                if (str.equals("SKYPE")) {
                    c5 = 11;
                    break;
                }
                break;
            case 1067023906:
                if (str.equals("SNAPCHAT")) {
                    c5 = 12;
                    break;
                }
                break;
            case 1216680122:
                if (str.equals("FACEBOOK_MESSENGER")) {
                    c5 = 13;
                    break;
                }
                break;
            case 1279756998:
                if (str.equals("FACEBOOK")) {
                    c5 = 14;
                    break;
                }
                break;
            case 1488252662:
                if (str.equals("TELEGRAM_WEB")) {
                    c5 = 15;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return PackageName.TINDER;
            case 1:
                return PackageName.WECHAT;
            case 2:
                return PackageName.WHATSAPP;
            case 3:
                return PackageName.INSTAGRAM;
            case 4:
                return PackageName.TELEGRAM;
            case 5:
                return PackageName.WHATSAPP_BUSINESS;
            case 6:
                return PackageName.GB_WHATSAPP;
            case 7:
                return PackageName.KIK;
            case 8:
                return PackageName.HIKE;
            case 9:
                return PackageName.LINE;
            case 10:
                return PackageName.GMAIL;
            case 11:
                return PackageName.SKYPE;
            case 12:
                return PackageName.SNAPCHAT;
            case 13:
                return PackageName.FACEBOOK_MESSENGER;
            case StdKeyDeserializer.TYPE_URL /*14*/:
                return PackageName.FACEBOOK;
            case StdKeyDeserializer.TYPE_CLASS /*15*/:
                return PackageName.TELEGRAM_WEB;
            default:
                throw new IllegalArgumentException("Can't convert value to enum, unknown value: ".concat(str));
        }
    }

    /* access modifiers changed from: private */
    public String __Type_enumToString(SocialMediaEntity.Type type) {
        int i5 = g.f4955a[type.ordinal()];
        if (i5 == 1) {
            return "Unknown";
        }
        if (i5 == 2) {
            return "Sent";
        }
        if (i5 == 3) {
            return "Received";
        }
        throw new IllegalArgumentException("Can't convert enum to string, unknown enum value: " + type);
    }

    /* access modifiers changed from: private */
    public SocialMediaEntity.Type __Type_stringToEnum(String str) {
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case -744075775:
                if (str.equals("Received")) {
                    c5 = 0;
                    break;
                }
                break;
            case 2573240:
                if (str.equals("Sent")) {
                    c5 = 1;
                    break;
                }
                break;
            case 1379812394:
                if (str.equals("Unknown")) {
                    c5 = 2;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return SocialMediaEntity.Type.Received;
            case 1:
                return SocialMediaEntity.Type.Sent;
            case 2:
                return SocialMediaEntity.Type.Unknown;
            default:
                throw new IllegalArgumentException("Can't convert value to enum, unknown value: ".concat(str));
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v0, resolved type: com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaEntity} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v1, resolved type: com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaEntity} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v3, resolved type: com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaEntity} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v0, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v4, resolved type: com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaEntity} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v1, resolved type: com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaEntity} */
    /* JADX WARNING: type inference failed for: r14v2, types: [java.lang.String] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaEntity getSync(int r26) {
        /*
            r25 = this;
            r1 = r25
            java.lang.String r0 = "SELECT * from UnprocessedSocialMediaEntity WHERE id = ?"
            r2 = 1
            androidx.room.D r3 = androidx.room.D.e(r2, r0)
            r0 = r26
            long r4 = (long) r0
            r3.L(r2, r4)
            androidx.room.A r0 = r1.__db
            r0.assertNotSuspendingTransaction()
            androidx.room.A r0 = r1.__db
            android.database.Cursor r4 = o.C0769d.T(r0, r3)
            java.lang.String r0 = "id"
            int r0 = t3.F.r(r4, r0)     // Catch:{ all -> 0x00a6 }
            java.lang.String r5 = "title"
            int r5 = t3.F.r(r4, r5)     // Catch:{ all -> 0x00a6 }
            java.lang.String r6 = "status"
            int r6 = t3.F.r(r4, r6)     // Catch:{ all -> 0x00a6 }
            java.lang.String r7 = "sender"
            int r7 = t3.F.r(r4, r7)     // Catch:{ all -> 0x00a6 }
            java.lang.String r8 = "message"
            int r8 = t3.F.r(r4, r8)     // Catch:{ all -> 0x00a6 }
            java.lang.String r9 = "type"
            int r9 = t3.F.r(r4, r9)     // Catch:{ all -> 0x00a6 }
            java.lang.String r10 = "application"
            int r10 = t3.F.r(r4, r10)     // Catch:{ all -> 0x00a6 }
            java.lang.String r11 = "timeString"
            int r11 = t3.F.r(r4, r11)     // Catch:{ all -> 0x00a6 }
            java.lang.String r12 = "uploaded"
            int r12 = t3.F.r(r4, r12)     // Catch:{ all -> 0x00a6 }
            boolean r13 = r4.moveToFirst()     // Catch:{ all -> 0x00a6 }
            r14 = 0
            if (r13 == 0) goto L_0x00a8
            int r16 = r4.getInt(r0)     // Catch:{ all -> 0x00a6 }
            java.lang.String r17 = r4.getString(r5)     // Catch:{ all -> 0x00a6 }
            boolean r0 = r4.isNull(r6)     // Catch:{ all -> 0x00a6 }
            if (r0 == 0) goto L_0x0068
            r18 = r14
            goto L_0x006e
        L_0x0068:
            java.lang.String r0 = r4.getString(r6)     // Catch:{ all -> 0x00a6 }
            r18 = r0
        L_0x006e:
            boolean r0 = r4.isNull(r7)     // Catch:{ all -> 0x00a6 }
            if (r0 == 0) goto L_0x0077
        L_0x0074:
            r19 = r14
            goto L_0x007c
        L_0x0077:
            java.lang.String r14 = r4.getString(r7)     // Catch:{ all -> 0x00a6 }
            goto L_0x0074
        L_0x007c:
            java.lang.String r20 = r4.getString(r8)     // Catch:{ all -> 0x00a6 }
            java.lang.String r0 = r4.getString(r9)     // Catch:{ all -> 0x00a6 }
            com.hawkshaw.library.datalayer.room.socialmedia.SocialMediaEntity$Type r21 = r1.__Type_stringToEnum(r0)     // Catch:{ all -> 0x00a6 }
            java.lang.String r0 = r4.getString(r10)     // Catch:{ all -> 0x00a6 }
            com.hawkshaw.library.datalayer.models.PackageName r22 = r1.__PackageName_stringToEnum(r0)     // Catch:{ all -> 0x00a6 }
            java.lang.String r23 = r4.getString(r11)     // Catch:{ all -> 0x00a6 }
            int r0 = r4.getInt(r12)     // Catch:{ all -> 0x00a6 }
            if (r0 == 0) goto L_0x009d
        L_0x009a:
            r24 = r2
            goto L_0x009f
        L_0x009d:
            r2 = 0
            goto L_0x009a
        L_0x009f:
            com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaEntity r14 = new com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaEntity     // Catch:{ all -> 0x00a6 }
            r15 = r14
            r15.<init>(r16, r17, r18, r19, r20, r21, r22, r23, r24)     // Catch:{ all -> 0x00a6 }
            goto L_0x00a8
        L_0x00a6:
            r0 = move-exception
            goto L_0x00af
        L_0x00a8:
            r4.close()
            r3.f()
            return r14
        L_0x00af:
            r4.close()
            r3.f()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaDao_Impl.getSync(int):com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaEntity");
    }

    public Object list(e eVar) {
        D e5 = D.e(0, "SELECT * from UnprocessedSocialMediaEntity");
        return C0276g.a(this.__db, new CancellationSignal(), new e(this, e5), eVar);
    }

    public Object nukeTable(e eVar) {
        return C0276g.b(this.__db, new d(this, 5), eVar);
    }

    public Object delete(UnprocessedSocialMediaEntity unprocessedSocialMediaEntity, e eVar) {
        return C0276g.b(this.__db, new C0933b(7, this, unprocessedSocialMediaEntity), eVar);
    }

    public Object deleteAll(UnprocessedSocialMediaEntity[] unprocessedSocialMediaEntityArr, e eVar) {
        return C0276g.b(this.__db, new b(this, unprocessedSocialMediaEntityArr, 1), eVar);
    }

    public Object insert(UnprocessedSocialMediaEntity[] unprocessedSocialMediaEntityArr, e eVar) {
        return C0276g.b(this.__db, new b(this, unprocessedSocialMediaEntityArr, 0), eVar);
    }

    public void insertSync(UnprocessedSocialMediaEntity... unprocessedSocialMediaEntityArr) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfUnprocessedSocialMediaEntity.f(unprocessedSocialMediaEntityArr);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public Object update(UnprocessedSocialMediaEntity[] unprocessedSocialMediaEntityArr, e eVar) {
        return C0276g.b(this.__db, new b(this, unprocessedSocialMediaEntityArr, 2), eVar);
    }

    public void updateSync(UnprocessedSocialMediaEntity... unprocessedSocialMediaEntityArr) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfUnprocessedSocialMediaEntity.f(unprocessedSocialMediaEntityArr);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public Object list(boolean z4, e eVar) {
        D e5 = D.e(1, "SELECT * from UnprocessedSocialMediaEntity WHERE uploaded = ?");
        e5.L(1, z4 ? 1 : 0);
        return C0276g.a(this.__db, new CancellationSignal(), new f(this, e5), eVar);
    }
}
