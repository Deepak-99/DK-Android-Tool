package com.hawkshaw.library.datalayer.models;

import B3.f;
import D3.b;
import E3.q0;
import F3.v;
import Y1.K;
import com.hawkshaw.library.datalayer.models.Command;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.json.c;
import p3.q;
import w3.w;

@f
public final class PushDeviceInfoRequest {
    /* access modifiers changed from: private */
    public static final KSerializer[] $childSerializers = {Command.PushDeviceInfoRequest.Type.Companion.serializer(), null};
    public static final Companion Companion = new Companion((j3.f) null);
    private final c deviceInfo;
    private final Command.PushDeviceInfoRequest.Type type;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(j3.f fVar) {
            this();
        }

        public final KSerializer serializer() {
            return PushDeviceInfoRequest$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ PushDeviceInfoRequest(int i5, Command.PushDeviceInfoRequest.Type type2, c cVar, q0 q0Var) {
        if (3 == (i5 & 3)) {
            this.type = type2;
            this.deviceInfo = cVar;
            return;
        }
        w.x(i5, 3, PushDeviceInfoRequest$$serializer.INSTANCE.getDescriptor());
        throw null;
    }

    public static /* synthetic */ void getDeviceInfo$annotations() {
    }

    public static /* synthetic */ void getType$annotations() {
    }

    public static final /* synthetic */ void write$Self$library_release(PushDeviceInfoRequest pushDeviceInfoRequest, b bVar, SerialDescriptor serialDescriptor) {
        q qVar = (q) bVar;
        qVar.e0(serialDescriptor, 0, $childSerializers[0], pushDeviceInfoRequest.type);
        qVar.e0(serialDescriptor, 1, v.f660a, pushDeviceInfoRequest.deviceInfo);
    }

    public final c getDeviceInfo() {
        return this.deviceInfo;
    }

    public final Command.PushDeviceInfoRequest.Type getType() {
        return this.type;
    }

    public PushDeviceInfoRequest(Command.PushDeviceInfoRequest.Type type2, c cVar) {
        K.n(type2, "type");
        K.n(cVar, "deviceInfo");
        this.type = type2;
        this.deviceInfo = cVar;
    }
}
