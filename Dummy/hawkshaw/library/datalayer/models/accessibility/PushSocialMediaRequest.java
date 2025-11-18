package com.hawkshaw.library.datalayer.models.accessibility;

import B3.f;
import D3.b;
import E3.C0020d;
import E3.V;
import E3.q0;
import E3.u0;
import Y1.K;
import com.hawkshaw.library.datalayer.models.PackageName;
import com.hawkshaw.library.datalayer.room.socialmedia.SocialMediaEntity;
import java.util.List;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import p3.q;
import v3.j;
import w3.w;

@f
public final class PushSocialMediaRequest {
    /* access modifiers changed from: private */
    public static final KSerializer[] $childSerializers = {null, new C0020d(PushSocialMediaRequest$SocialMediaMessage$$serializer.INSTANCE, 0)};
    public static final Companion Companion = new Companion((j3.f) null);
    private final List<SocialMediaMessage> messages;
    private final int page;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(j3.f fVar) {
            this();
        }

        public final KSerializer serializer() {
            return PushSocialMediaRequest$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ PushSocialMediaRequest(int i5, int i6, List list, q0 q0Var) {
        if (3 == (i5 & 3)) {
            this.page = i6;
            this.messages = list;
            return;
        }
        w.x(i5, 3, PushSocialMediaRequest$$serializer.INSTANCE.getDescriptor());
        throw null;
    }

    public static /* synthetic */ void getMessages$annotations() {
    }

    public static /* synthetic */ void getPage$annotations() {
    }

    public static final /* synthetic */ void write$Self$library_release(PushSocialMediaRequest pushSocialMediaRequest, b bVar, SerialDescriptor serialDescriptor) {
        KSerializer[] kSerializerArr = $childSerializers;
        q qVar = (q) bVar;
        qVar.c0(0, pushSocialMediaRequest.page, serialDescriptor);
        qVar.e0(serialDescriptor, 1, kSerializerArr[1], pushSocialMediaRequest.messages);
    }

    public final List<SocialMediaMessage> getMessages() {
        return this.messages;
    }

    public final int getPage() {
        return this.page;
    }

    public PushSocialMediaRequest(int i5, List<SocialMediaMessage> list) {
        K.n(list, "messages");
        this.page = i5;
        this.messages = list;
    }

    @f
    public static final class SocialMediaMessage {
        /* access modifiers changed from: private */
        public static final KSerializer[] $childSerializers = {null, null, null, null, j.f("com.hawkshaw.library.datalayer.room.socialmedia.SocialMediaEntity.Type", SocialMediaEntity.Type.values()), null, j.f("com.hawkshaw.library.datalayer.models.PackageName", PackageName.values()), null};
        public static final Companion Companion = new Companion((j3.f) null);
        private final PackageName application;
        private final String ccn;
        private final String ccs;
        private final String message;
        private final String sender;
        private final String time;
        private final Long timestamp;
        private final SocialMediaEntity.Type type;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final KSerializer serializer() {
                return PushSocialMediaRequest$SocialMediaMessage$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ SocialMediaMessage(int i5, String str, String str2, String str3, String str4, SocialMediaEntity.Type type2, Long l5, PackageName packageName, String str5, q0 q0Var) {
            if (127 == (i5 & 127)) {
                this.ccn = str;
                this.ccs = str2;
                this.sender = str3;
                this.message = str4;
                this.type = type2;
                this.timestamp = l5;
                this.application = packageName;
                if ((i5 & 128) == 0) {
                    this.time = "";
                } else {
                    this.time = str5;
                }
            } else {
                w.x(i5, 127, PushSocialMediaRequest$SocialMediaMessage$$serializer.INSTANCE.getDescriptor());
                throw null;
            }
        }

        public static final /* synthetic */ void write$Self$library_release(SocialMediaMessage socialMediaMessage, b bVar, SerialDescriptor serialDescriptor) {
            KSerializer[] kSerializerArr = $childSerializers;
            q qVar = (q) bVar;
            qVar.f0(serialDescriptor, 0, socialMediaMessage.ccn);
            u0 u0Var = u0.f536a;
            qVar.s(serialDescriptor, 1, u0Var, socialMediaMessage.ccs);
            qVar.s(serialDescriptor, 2, u0Var, socialMediaMessage.sender);
            qVar.f0(serialDescriptor, 3, socialMediaMessage.message);
            qVar.e0(serialDescriptor, 4, kSerializerArr[4], socialMediaMessage.type);
            qVar.s(serialDescriptor, 5, V.f466a, socialMediaMessage.timestamp);
            qVar.e0(serialDescriptor, 6, kSerializerArr[6], socialMediaMessage.application);
            if (qVar.q(serialDescriptor) || !K.f(socialMediaMessage.time, "")) {
                qVar.f0(serialDescriptor, 7, socialMediaMessage.time);
            }
        }

        public final PackageName getApplication() {
            return this.application;
        }

        public final String getCcn() {
            return this.ccn;
        }

        public final String getCcs() {
            return this.ccs;
        }

        public final String getMessage() {
            return this.message;
        }

        public final String getSender() {
            return this.sender;
        }

        public final String getTime() {
            return this.time;
        }

        public final Long getTimestamp() {
            return this.timestamp;
        }

        public final SocialMediaEntity.Type getType() {
            return this.type;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ SocialMediaMessage(String str, String str2, String str3, String str4, SocialMediaEntity.Type type2, Long l5, PackageName packageName, String str5, int i5, j3.f fVar) {
            this(str, str2, str3, str4, type2, l5, packageName, (i5 & 128) != 0 ? "" : str5);
        }

        public SocialMediaMessage(String str, String str2, String str3, String str4, SocialMediaEntity.Type type2, Long l5, PackageName packageName, String str5) {
            K.n(str, "ccn");
            K.n(str4, "message");
            K.n(type2, "type");
            K.n(packageName, "application");
            K.n(str5, "time");
            this.ccn = str;
            this.ccs = str2;
            this.sender = str3;
            this.message = str4;
            this.type = type2;
            this.timestamp = l5;
            this.application = packageName;
            this.time = str5;
        }
    }
}
