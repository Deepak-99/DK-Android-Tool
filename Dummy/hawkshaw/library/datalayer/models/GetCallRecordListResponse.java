package com.hawkshaw.library.datalayer.models;

import B3.f;
import D3.b;
import E3.C0020d;
import E3.q0;
import Y1.K;
import com.hawkshaw.library.datalayer.room.telephony.CallRecordEntity;
import java.util.List;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import p3.q;
import w3.w;

@f
public final class GetCallRecordListResponse {
    /* access modifiers changed from: private */
    public static final KSerializer[] $childSerializers = {new C0020d(GetCallRecordListResponse$CallRecord$$serializer.INSTANCE, 0)};
    public static final Companion Companion = new Companion((j3.f) null);
    private final List<CallRecord> list;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(j3.f fVar) {
            this();
        }

        public final KSerializer serializer() {
            return GetCallRecordListResponse$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ GetCallRecordListResponse(int i5, List list2, q0 q0Var) {
        if (1 == (i5 & 1)) {
            this.list = list2;
        } else {
            w.x(i5, 1, GetCallRecordListResponse$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
    }

    public final List<CallRecord> getList() {
        return this.list;
    }

    @f
    public static final class CallRecord {
        public static final Companion Companion = new Companion((j3.f) null);
        private final String name;
        private final String number;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final KSerializer serializer() {
                return GetCallRecordListResponse$CallRecord$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ CallRecord(int i5, String str, String str2, q0 q0Var) {
            if (3 == (i5 & 3)) {
                this.name = str;
                this.number = str2;
                return;
            }
            w.x(i5, 3, GetCallRecordListResponse$CallRecord$$serializer.INSTANCE.getDescriptor());
            throw null;
        }

        public static final /* synthetic */ void write$Self$library_release(CallRecord callRecord, b bVar, SerialDescriptor serialDescriptor) {
            q qVar = (q) bVar;
            qVar.f0(serialDescriptor, 0, callRecord.name);
            qVar.f0(serialDescriptor, 1, callRecord.number);
        }

        public final String getName() {
            return this.name;
        }

        public final String getNumber() {
            return this.number;
        }

        public final CallRecordEntity toCallRecordEntity() {
            return new CallRecordEntity(this.name, this.number);
        }

        public CallRecord(String str, String str2) {
            K.n(str, "name");
            K.n(str2, "number");
            this.name = str;
            this.number = str2;
        }
    }

    public GetCallRecordListResponse(List<CallRecord> list2) {
        K.n(list2, "list");
        this.list = list2;
    }
}
