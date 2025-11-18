package com.hawkshaw.library.datalayer.models;

import B3.f;
import D3.b;
import E3.C0020d;
import E3.q0;
import Y1.K;
import com.hawkshaw.library.logger.Logger;
import java.util.List;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import p3.q;
import v3.j;
import w3.w;

@f
public final class AppLogsRequest {
    /* access modifiers changed from: private */
    public static final KSerializer[] $childSerializers = {new C0020d(AppLogsRequest$Log$$serializer.INSTANCE, 0)};
    public static final Companion Companion = new Companion((j3.f) null);
    private final List<Log> logs;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(j3.f fVar) {
            this();
        }

        public final KSerializer serializer() {
            return AppLogsRequest$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ AppLogsRequest(int i5, List list, q0 q0Var) {
        if (1 == (i5 & 1)) {
            this.logs = list;
        } else {
            w.x(i5, 1, AppLogsRequest$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
    }

    public static /* synthetic */ void getLogs$annotations() {
    }

    public final List<Log> getLogs() {
        return this.logs;
    }

    @f
    public static final class Log {
        /* access modifiers changed from: private */
        public static final KSerializer[] $childSerializers = {j.f("com.hawkshaw.library.logger.Logger.LogLevel", Logger.LogLevel.values()), null, null, null, null};
        public static final Companion Companion = new Companion((j3.f) null);
        private final int id;
        private final Logger.LogLevel logLevel;
        private final String message;
        private final String tag;
        private final long timestamp;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final KSerializer serializer() {
                return AppLogsRequest$Log$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Log(int i5, Logger.LogLevel logLevel2, String str, String str2, long j5, int i6, q0 q0Var) {
            if (31 == (i5 & 31)) {
                this.logLevel = logLevel2;
                this.tag = str;
                this.message = str2;
                this.timestamp = j5;
                this.id = i6;
                return;
            }
            w.x(i5, 31, AppLogsRequest$Log$$serializer.INSTANCE.getDescriptor());
            throw null;
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void getLogLevel$annotations() {
        }

        public static /* synthetic */ void getMessage$annotations() {
        }

        public static /* synthetic */ void getTag$annotations() {
        }

        public static /* synthetic */ void getTimestamp$annotations() {
        }

        public static final /* synthetic */ void write$Self$library_release(Log log, b bVar, SerialDescriptor serialDescriptor) {
            q qVar = (q) bVar;
            qVar.e0(serialDescriptor, 0, $childSerializers[0], log.logLevel);
            qVar.f0(serialDescriptor, 1, log.tag);
            qVar.f0(serialDescriptor, 2, log.message);
            qVar.d0(serialDescriptor, 3, log.timestamp);
            qVar.c0(4, log.id, serialDescriptor);
        }

        public final int getId() {
            return this.id;
        }

        public final Logger.LogLevel getLogLevel() {
            return this.logLevel;
        }

        public final String getMessage() {
            return this.message;
        }

        public final String getTag() {
            return this.tag;
        }

        public final long getTimestamp() {
            return this.timestamp;
        }

        public Log(Logger.LogLevel logLevel2, String str, String str2, long j5, int i5) {
            K.n(logLevel2, "logLevel");
            K.n(str, "tag");
            K.n(str2, "message");
            this.logLevel = logLevel2;
            this.tag = str;
            this.message = str2;
            this.timestamp = j5;
            this.id = i5;
        }
    }

    public AppLogsRequest(List<Log> list) {
        K.n(list, "logs");
        this.logs = list;
    }
}
