package com.hawkshaw.library.datalayer.room.socialmedia;

import C1.d;
import D1.a;
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

public final class SocialMediaDao_Impl implements SocialMediaDao {
    /* access modifiers changed from: private */
    public final A __db;
    /* access modifiers changed from: private */
    public final i __deletionAdapterOfSocialMediaEntity;
    /* access modifiers changed from: private */
    public final j __insertionAdapterOfSocialMediaEntity;
    /* access modifiers changed from: private */
    public final F __preparedStmtOfNukeTable;
    /* access modifiers changed from: private */
    public final i __updateAdapterOfSocialMediaEntity;

    public SocialMediaDao_Impl(A a5) {
        this.__db = a5;
        this.__insertionAdapterOfSocialMediaEntity = new c(this, a5);
        this.__deletionAdapterOfSocialMediaEntity = new C1.j(this, a5, 3);
        this.__updateAdapterOfSocialMediaEntity = new d(this, a5);
        this.__preparedStmtOfNukeTable = new a(this, a5, 3);
    }

    /* access modifiers changed from: private */
    public String __PackageName_enumToString(PackageName packageName) {
        switch (b.f4948b[packageName.ordinal()]) {
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
        int i5 = b.f4947a[type.ordinal()];
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

    public Object getAll(e eVar) {
        D e5 = D.e(0, "SELECT * from SocialMediaEntity");
        return C0276g.a(this.__db, new CancellationSignal(), new a(this, e5), eVar);
    }

    public Object nukeTable(e eVar) {
        return C0276g.b(this.__db, new d(this, 4), eVar);
    }

    public Object delete(SocialMediaEntity socialMediaEntity, e eVar) {
        return C0276g.b(this.__db, new C0933b(6, this, socialMediaEntity), eVar);
    }

    public Object deleteAll(SocialMediaEntity[] socialMediaEntityArr, e eVar) {
        return C0276g.b(this.__db, new G1.a(this, socialMediaEntityArr, 1), eVar);
    }

    public Object insert(SocialMediaEntity[] socialMediaEntityArr, e eVar) {
        return C0276g.b(this.__db, new G1.a(this, socialMediaEntityArr, 0), eVar);
    }

    public void insertSync(SocialMediaEntity... socialMediaEntityArr) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfSocialMediaEntity.f(socialMediaEntityArr);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public Object update(SocialMediaEntity[] socialMediaEntityArr, e eVar) {
        return C0276g.b(this.__db, new G1.a(this, socialMediaEntityArr, 2), eVar);
    }

    public void updateSync(SocialMediaEntity... socialMediaEntityArr) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfSocialMediaEntity.f(socialMediaEntityArr);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }
}
