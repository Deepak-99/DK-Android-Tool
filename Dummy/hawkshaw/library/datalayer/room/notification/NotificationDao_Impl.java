package com.hawkshaw.library.datalayer.room.notification;

import C1.d;
import F1.a;
import H1.b;
import a3.e;
import android.os.CancellationSignal;
import androidx.room.A;
import androidx.room.C0276g;
import androidx.room.D;
import androidx.room.F;
import androidx.room.i;
import androidx.room.j;
import java.util.Collections;
import java.util.List;
import s.C0933b;

public final class NotificationDao_Impl implements NotificationDao {
    /* access modifiers changed from: private */
    public final A __db;
    /* access modifiers changed from: private */
    public final i __deletionAdapterOfNotificationEntity;
    /* access modifiers changed from: private */
    public final j __insertionAdapterOfNotificationEntity;
    /* access modifiers changed from: private */
    public final F __preparedStmtOfNukeTable;
    /* access modifiers changed from: private */
    public final i __updateAdapterOfNotificationEntity;

    public NotificationDao_Impl(A a5) {
        this.__db = a5;
        this.__insertionAdapterOfNotificationEntity = new b(this, a5, 2);
        this.__deletionAdapterOfNotificationEntity = new a(a5, 0);
        this.__updateAdapterOfNotificationEntity = new a(a5, 1);
        this.__preparedStmtOfNukeTable = new D1.a(this, a5, 2);
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    public Object getAll(e eVar) {
        D e5 = D.e(0, "SELECT * from NotificationEntity");
        return C0276g.a(this.__db, new CancellationSignal(), new C0933b(4, this, e5), eVar);
    }

    public Object nukeTable(e eVar) {
        return C0276g.b(this.__db, new d(this, 3), eVar);
    }

    public Object delete(NotificationEntity notificationEntity, e eVar) {
        return C0276g.b(this.__db, new C0933b(5, this, notificationEntity), eVar);
    }

    public Object deleteAll(NotificationEntity[] notificationEntityArr, e eVar) {
        return C0276g.b(this.__db, new F1.b(this, notificationEntityArr, 1), eVar);
    }

    public Object insert(NotificationEntity[] notificationEntityArr, e eVar) {
        return C0276g.b(this.__db, new F1.b(this, notificationEntityArr, 0), eVar);
    }

    public void insertSync(NotificationEntity... notificationEntityArr) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfNotificationEntity.f(notificationEntityArr);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public Object update(NotificationEntity[] notificationEntityArr, e eVar) {
        return C0276g.b(this.__db, new F1.b(this, notificationEntityArr, 2), eVar);
    }

    public void updateSync(NotificationEntity... notificationEntityArr) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfNotificationEntity.f(notificationEntityArr);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }
}
