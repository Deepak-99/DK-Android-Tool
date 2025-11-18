package com.hawkshaw.library.datalayer.room.telephony;

import H1.a;
import H1.b;
import H1.c;
import H1.d;
import a3.e;
import android.os.CancellationSignal;
import androidx.room.A;
import androidx.room.C0276g;
import androidx.room.D;
import androidx.room.F;
import androidx.room.j;
import java.util.Collections;
import java.util.List;

public final class TelephonyDao_Impl implements TelephonyDao {
    /* access modifiers changed from: private */
    public final A __db;
    /* access modifiers changed from: private */
    public final j __insertionAdapterOfCallBlockEntity;
    /* access modifiers changed from: private */
    public final j __insertionAdapterOfCallRecordEntity;
    /* access modifiers changed from: private */
    public final F __preparedStmtOfNukeCallBlockNumbers;
    /* access modifiers changed from: private */
    public final F __preparedStmtOfNukeCallRecordNumbers;

    public TelephonyDao_Impl(A a5) {
        this.__db = a5;
        this.__insertionAdapterOfCallBlockEntity = new b(this, a5, 0);
        this.__insertionAdapterOfCallRecordEntity = new b(this, a5, 1);
        this.__preparedStmtOfNukeCallBlockNumbers = new c(a5, 0);
        this.__preparedStmtOfNukeCallRecordNumbers = new c(a5, 1);
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    public Object getCallBlockNumbers(e eVar) {
        D e5 = D.e(0, "Select * from CallBlockEntity");
        return C0276g.a(this.__db, new CancellationSignal(), new a(this, e5, 1), eVar);
    }

    public Object getCallRecordNumbers(e eVar) {
        D e5 = D.e(0, "Select * from CallRecordEntity");
        return C0276g.a(this.__db, new CancellationSignal(), new a(this, e5, 0), eVar);
    }

    public Object nukeCallBlockNumbers(e eVar) {
        return C0276g.b(this.__db, new H1.e(this, 0), eVar);
    }

    public Object nukeCallRecordNumbers(e eVar) {
        return C0276g.b(this.__db, new H1.e(this, 1), eVar);
    }

    public Object saveCallBlockNumbers(List<CallBlockEntity> list, e eVar) {
        return C0276g.b(this.__db, new d(this, list, 0), eVar);
    }

    public Object saveCallRecordNumbers(List<CallRecordEntity> list, e eVar) {
        return C0276g.b(this.__db, new d(this, list, 1), eVar);
    }
}
