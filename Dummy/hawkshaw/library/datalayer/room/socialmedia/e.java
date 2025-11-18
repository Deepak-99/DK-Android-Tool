package com.hawkshaw.library.datalayer.room.socialmedia;

import android.database.Cursor;
import androidx.core.app.NotificationCompat;
import androidx.room.A;
import androidx.room.D;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import o.C0769d;
import t3.F;

public final class e implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ D f4951a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ UnprocessedSocialMediaDao_Impl f4952b;

    public e(UnprocessedSocialMediaDao_Impl unprocessedSocialMediaDao_Impl, D d5) {
        this.f4952b = unprocessedSocialMediaDao_Impl;
        this.f4951a = d5;
    }

    public final Object call() {
        UnprocessedSocialMediaDao_Impl unprocessedSocialMediaDao_Impl = this.f4952b;
        A a5 = unprocessedSocialMediaDao_Impl.__db;
        D d5 = this.f4951a;
        Cursor T4 = C0769d.T(a5, d5);
        try {
            int r4 = F.r(T4, "id");
            int r5 = F.r(T4, "title");
            int r6 = F.r(T4, NotificationCompat.CATEGORY_STATUS);
            int r7 = F.r(T4, "sender");
            int r8 = F.r(T4, "message");
            int r9 = F.r(T4, "type");
            int r10 = F.r(T4, "application");
            int r11 = F.r(T4, "timeString");
            int r12 = F.r(T4, "uploaded");
            ArrayList arrayList = new ArrayList(T4.getCount());
            while (T4.moveToNext()) {
                arrayList.add(new UnprocessedSocialMediaEntity(T4.getInt(r4), T4.getString(r5), T4.isNull(r6) ? null : T4.getString(r6), T4.isNull(r7) ? null : T4.getString(r7), T4.getString(r8), unprocessedSocialMediaDao_Impl.__Type_stringToEnum(T4.getString(r9)), unprocessedSocialMediaDao_Impl.__PackageName_stringToEnum(T4.getString(r10)), T4.getString(r11), T4.getInt(r12) != 0));
            }
            return arrayList;
        } finally {
            T4.close();
            d5.f();
        }
    }
}
