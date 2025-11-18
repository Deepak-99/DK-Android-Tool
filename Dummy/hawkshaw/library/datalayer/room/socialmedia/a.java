package com.hawkshaw.library.datalayer.room.socialmedia;

import android.database.Cursor;
import androidx.room.A;
import androidx.room.D;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import o.C0769d;
import t3.F;

public final class a implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ D f4945a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ SocialMediaDao_Impl f4946b;

    public a(SocialMediaDao_Impl socialMediaDao_Impl, D d5) {
        this.f4946b = socialMediaDao_Impl;
        this.f4945a = d5;
    }

    public final Object call() {
        SocialMediaDao_Impl socialMediaDao_Impl = this.f4946b;
        A a5 = socialMediaDao_Impl.__db;
        D d5 = this.f4945a;
        Cursor T4 = C0769d.T(a5, d5);
        try {
            int r4 = F.r(T4, "ccn");
            int r5 = F.r(T4, "ccs");
            int r6 = F.r(T4, "sender");
            int r7 = F.r(T4, "message");
            int r8 = F.r(T4, "type");
            int r9 = F.r(T4, "timestamp");
            int r10 = F.r(T4, "application");
            int r11 = F.r(T4, "time");
            ArrayList arrayList = new ArrayList(T4.getCount());
            while (T4.moveToNext()) {
                arrayList.add(new SocialMediaEntity(T4.getString(r4), T4.isNull(r5) ? null : T4.getString(r5), T4.isNull(r6) ? null : T4.getString(r6), T4.getString(r7), socialMediaDao_Impl.__Type_stringToEnum(T4.getString(r8)), T4.isNull(r9) ? null : Long.valueOf(T4.getLong(r9)), socialMediaDao_Impl.__PackageName_stringToEnum(T4.getString(r10)), T4.getString(r11)));
            }
            return arrayList;
        } finally {
            T4.close();
            d5.f();
        }
    }
}
