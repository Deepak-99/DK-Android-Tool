package com.hawkshaw.library.datalayer.models;

import B3.f;
import D3.b;
import E3.C0020d;
import E3.q0;
import E3.u0;
import Y1.K;
import androidx.core.app.FrameMetricsAggregator;
import java.util.List;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import p3.q;
import w3.w;

@f
public final class Contact {
    /* access modifiers changed from: private */
    public static final KSerializer[] $childSerializers = {null, null, null, null, null, null, null, new C0020d(Contact$MobileNumber$$serializer.INSTANCE, 0), new C0020d(u0.f536a, 0)};
    public static final Companion Companion = new Companion((j3.f) null);
    private final List<String> emailList;
    private final String id;
    private final String lastUpdatedTimestamp;
    private final List<MobileNumber> mobileNumbers;
    private final String name;
    private final String person;
    private final String photoUri;
    private final int pinned;
    private final int starred;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(j3.f fVar) {
            this();
        }

        public final KSerializer serializer() {
            return Contact$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ Contact(int i5, String str, String str2, String str3, int i6, int i7, String str4, String str5, List list, List list2, q0 q0Var) {
        if (511 == (i5 & FrameMetricsAggregator.EVERY_DURATION)) {
            this.id = str;
            this.name = str2;
            this.person = str3;
            this.starred = i6;
            this.pinned = i7;
            this.photoUri = str4;
            this.lastUpdatedTimestamp = str5;
            this.mobileNumbers = list;
            this.emailList = list2;
            return;
        }
        w.x(i5, FrameMetricsAggregator.EVERY_DURATION, Contact$$serializer.INSTANCE.getDescriptor());
        throw null;
    }

    public static /* synthetic */ void getEmailList$annotations() {
    }

    public static /* synthetic */ void getId$annotations() {
    }

    public static /* synthetic */ void getLastUpdatedTimestamp$annotations() {
    }

    public static /* synthetic */ void getMobileNumbers$annotations() {
    }

    public static /* synthetic */ void getName$annotations() {
    }

    public static /* synthetic */ void getPerson$annotations() {
    }

    public static /* synthetic */ void getPhotoUri$annotations() {
    }

    public static /* synthetic */ void getPinned$annotations() {
    }

    public static /* synthetic */ void getStarred$annotations() {
    }

    public static final /* synthetic */ void write$Self$library_release(Contact contact, b bVar, SerialDescriptor serialDescriptor) {
        KSerializer[] kSerializerArr = $childSerializers;
        u0 u0Var = u0.f536a;
        bVar.s(serialDescriptor, 0, u0Var, contact.id);
        bVar.s(serialDescriptor, 1, u0Var, contact.name);
        bVar.s(serialDescriptor, 2, u0Var, contact.person);
        q qVar = (q) bVar;
        qVar.c0(3, contact.starred, serialDescriptor);
        qVar.c0(4, contact.pinned, serialDescriptor);
        bVar.s(serialDescriptor, 5, u0Var, contact.photoUri);
        bVar.s(serialDescriptor, 6, u0Var, contact.lastUpdatedTimestamp);
        qVar.e0(serialDescriptor, 7, kSerializerArr[7], contact.mobileNumbers);
        qVar.e0(serialDescriptor, 8, kSerializerArr[8], contact.emailList);
    }

    public final List<String> getEmailList() {
        return this.emailList;
    }

    public final String getId() {
        return this.id;
    }

    public final String getLastUpdatedTimestamp() {
        return this.lastUpdatedTimestamp;
    }

    public final List<MobileNumber> getMobileNumbers() {
        return this.mobileNumbers;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPerson() {
        return this.person;
    }

    public final String getPhotoUri() {
        return this.photoUri;
    }

    public final int getPinned() {
        return this.pinned;
    }

    public final int getStarred() {
        return this.starred;
    }

    @f
    public static final class MobileNumber {
        public static final Companion Companion = new Companion((j3.f) null);
        private final String number;
        private final String type;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final KSerializer serializer() {
                return Contact$MobileNumber$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ MobileNumber(int i5, String str, String str2, q0 q0Var) {
            if (3 == (i5 & 3)) {
                this.number = str;
                this.type = str2;
                return;
            }
            w.x(i5, 3, Contact$MobileNumber$$serializer.INSTANCE.getDescriptor());
            throw null;
        }

        public static /* synthetic */ void getNumber$annotations() {
        }

        public static /* synthetic */ void getType$annotations() {
        }

        public static final /* synthetic */ void write$Self$library_release(MobileNumber mobileNumber, b bVar, SerialDescriptor serialDescriptor) {
            q qVar = (q) bVar;
            qVar.f0(serialDescriptor, 0, mobileNumber.number);
            qVar.f0(serialDescriptor, 1, mobileNumber.type);
        }

        public final String getNumber() {
            return this.number;
        }

        public final String getType() {
            return this.type;
        }

        public MobileNumber(String str, String str2) {
            K.n(str, "number");
            K.n(str2, "type");
            this.number = str;
            this.type = str2;
        }
    }

    public Contact(String str, String str2, String str3, int i5, int i6, String str4, String str5, List<MobileNumber> list, List<String> list2) {
        K.n(list, "mobileNumbers");
        K.n(list2, "emailList");
        this.id = str;
        this.name = str2;
        this.person = str3;
        this.starred = i5;
        this.pinned = i6;
        this.photoUri = str4;
        this.lastUpdatedTimestamp = str5;
        this.mobileNumbers = list;
        this.emailList = list2;
    }
}
