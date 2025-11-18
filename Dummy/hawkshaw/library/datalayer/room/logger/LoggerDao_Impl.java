package com.hawkshaw.library.datalayer.room.logger;

import D1.a;
import E1.b;
import E1.c;
import E1.d;
import a3.e;
import android.os.CancellationSignal;
import androidx.room.A;
import androidx.room.C0276g;
import androidx.room.D;
import androidx.room.F;
import androidx.room.i;
import androidx.room.j;
import com.hawkshaw.library.logger.Logger;
import java.util.Collections;
import java.util.List;
import s.C0933b;

public final class LoggerDao_Impl implements LoggerDao {
    /* access modifiers changed from: private */
    public final A __db;
    /* access modifiers changed from: private */
    public final i __deletionAdapterOfLogEntity;
    /* access modifiers changed from: private */
    public final j __insertionAdapterOfLogEntity;
    /* access modifiers changed from: private */
    public final F __preparedStmtOfNukeTable;
    /* access modifiers changed from: private */
    public final i __updateAdapterOfLogEntity;

    public LoggerDao_Impl(A a5) {
        this.__db = a5;
        this.__insertionAdapterOfLogEntity = new c(this, a5);
        this.__deletionAdapterOfLogEntity = new C1.j(this, a5, 2);
        this.__updateAdapterOfLogEntity = new d(this, a5);
        this.__preparedStmtOfNukeTable = new a(this, a5, 1);
    }

    /* access modifiers changed from: private */
    public String __LogLevel_enumToString(Logger.LogLevel logLevel) {
        switch (b.f385a[logLevel.ordinal()]) {
            case 1:
                return "Verbose";
            case 2:
                return "Debug";
            case 3:
                return "Info";
            case 4:
                return "Warn";
            case 5:
                return "Error";
            case 6:
                return "Log";
            default:
                throw new IllegalArgumentException("Can't convert enum to string, unknown enum value: " + logLevel);
        }
    }

    /* access modifiers changed from: private */
    public Logger.LogLevel __LogLevel_stringToEnum(String str) {
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case 76580:
                if (str.equals("Log")) {
                    c5 = 0;
                    break;
                }
                break;
            case 2283726:
                if (str.equals("Info")) {
                    c5 = 1;
                    break;
                }
                break;
            case 2688678:
                if (str.equals("Warn")) {
                    c5 = 2;
                    break;
                }
                break;
            case 65906227:
                if (str.equals("Debug")) {
                    c5 = 3;
                    break;
                }
                break;
            case 67232232:
                if (str.equals("Error")) {
                    c5 = 4;
                    break;
                }
                break;
            case 2015760738:
                if (str.equals("Verbose")) {
                    c5 = 5;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return Logger.LogLevel.Log;
            case 1:
                return Logger.LogLevel.Info;
            case 2:
                return Logger.LogLevel.Warn;
            case 3:
                return Logger.LogLevel.Debug;
            case 4:
                return Logger.LogLevel.Error;
            case 5:
                return Logger.LogLevel.Verbose;
            default:
                throw new IllegalArgumentException("Can't convert value to enum, unknown value: ".concat(str));
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    public Object getAll(e eVar) {
        D e5 = D.e(0, "SELECT * from LogEntity");
        return C0276g.a(this.__db, new CancellationSignal(), new E1.a(this, e5), eVar);
    }

    public Object nukeTable(e eVar) {
        return C0276g.b(this.__db, new C1.d(this, 2), eVar);
    }

    public Object delete(LogEntity logEntity, e eVar) {
        return C0276g.b(this.__db, new C0933b(3, this, logEntity), eVar);
    }

    public Object deleteAll(LogEntity[] logEntityArr, e eVar) {
        return C0276g.b(this.__db, new E1.e(this, logEntityArr, 1), eVar);
    }

    public Object insert(LogEntity[] logEntityArr, e eVar) {
        return C0276g.b(this.__db, new E1.e(this, logEntityArr, 0), eVar);
    }

    public void insertSync(LogEntity... logEntityArr) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfLogEntity.f(logEntityArr);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public Object update(LogEntity[] logEntityArr, e eVar) {
        return C0276g.b(this.__db, new E1.e(this, logEntityArr, 2), eVar);
    }

    public void updateSync(LogEntity... logEntityArr) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfLogEntity.f(logEntityArr);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }
}
