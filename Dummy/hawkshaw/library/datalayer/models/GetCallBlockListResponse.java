package com.hawkshaw.library.datalayer.models;

import B3.f;
import D3.b;
import E3.C0020d;
import E3.q0;
import Y1.K;
import com.hawkshaw.library.datalayer.room.telephony.CallBlockEntity;
import java.util.List;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import p3.q;
import w3.w;

@f
public final class GetCallBlockListResponse {
    /* access modifiers changed from: private */
    public static final KSerializer[] $childSerializers = {new C0020d(GetCallBlockListResponse$CallBlock$$serializer.INSTANCE, 0)};
    public static final Companion Companion = new Companion((j3.f) null);
    private final List<CallBlock> list;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(j3.f fVar) {
            this();
        }

        public final KSerializer serializer() {
            return GetCallBlockListResponse$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ GetCallBlockListResponse(int i5, List list2, q0 q0Var) {
        if (1 == (i5 & 1)) {
            this.list = list2;
        } else {
            w.x(i5, 1, GetCallBlockListResponse$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
    }

    public final List<CallBlock> getList() {
        return this.list;
    }

    @f
    public static final class CallBlock {
        public static final Companion Companion = new Companion((j3.f) null);
        private final boolean blockOutgoingCall;
        private final String name;
        private final String number;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final KSerializer serializer() {
                return GetCallBlockListResponse$CallBlock$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ CallBlock(int i5, String str, String str2, boolean z4, q0 q0Var) {
            if (7 == (i5 & 7)) {
                this.name = str;
                this.number = str2;
                this.blockOutgoingCall = z4;
                return;
            }
            w.x(i5, 7, GetCallBlockListResponse$CallBlock$$serializer.INSTANCE.getDescriptor());
            throw null;
        }

        public static /* synthetic */ void getBlockOutgoingCall$annotations() {
        }

        public static final /* synthetic */ void write$Self$library_release(CallBlock callBlock, b bVar, SerialDescriptor serialDescriptor) {
            q qVar = (q) bVar;
            qVar.f0(serialDescriptor, 0, callBlock.name);
            qVar.f0(serialDescriptor, 1, callBlock.number);
            qVar.X(serialDescriptor, 2, callBlock.blockOutgoingCall);
        }

        public final boolean getBlockOutgoingCall() {
            return this.blockOutgoingCall;
        }

        public final String getName() {
            return this.name;
        }

        public final String getNumber() {
            return this.number;
        }

        public final CallBlockEntity toCallBlockEntity() {
            return new CallBlockEntity(this.name, this.number, this.blockOutgoingCall);
        }

        public CallBlock(String str, String str2, boolean z4) {
            K.n(str, "name");
            K.n(str2, "number");
            this.name = str;
            this.number = str2;
            this.blockOutgoingCall = z4;
        }
    }

    public GetCallBlockListResponse(List<CallBlock> list2) {
        K.n(list2, "list");
        this.list = list2;
    }
}
