package com.hawkshaw.library.datalayer.models.accessibility;

import B3.f;
import D3.b;
import E3.C0020d;
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
public final class PushUnprocessedSocialMediaRequest {
    /* access modifiers changed from: private */
    public static final KSerializer[] $childSerializers = {new C0020d(PushUnprocessedSocialMediaRequest$UnprocessedSocialMediaMessage$$serializer.INSTANCE, 0)};
    public static final Companion Companion = new Companion((j3.f) null);
    private final List<UnprocessedSocialMediaMessage> messages;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(j3.f fVar) {
            this();
        }

        public final KSerializer serializer() {
            return PushUnprocessedSocialMediaRequest$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ PushUnprocessedSocialMediaRequest(int i5, List list, q0 q0Var) {
        if (1 == (i5 & 1)) {
            this.messages = list;
        } else {
            w.x(i5, 1, PushUnprocessedSocialMediaRequest$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
    }

    public static /* synthetic */ void getMessages$annotations() {
    }

    public final List<UnprocessedSocialMediaMessage> getMessages() {
        return this.messages;
    }

    @f
    public static final class UnprocessedSocialMediaMessage {
        /* access modifiers changed from: private */
        public static final KSerializer[] $childSerializers = {null, null, null, null, null, j.f("com.hawkshaw.library.datalayer.room.socialmedia.SocialMediaEntity.Type", SocialMediaEntity.Type.values()), j.f("com.hawkshaw.library.datalayer.models.PackageName", PackageName.values()), null};
        public static final Companion Companion = new Companion((j3.f) null);
        private final PackageName application;
        private final int id;
        private final String message;
        private final String sender;
        private final String status;
        private final String timeString;
        private final String title;
        private final SocialMediaEntity.Type type;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final KSerializer serializer() {
                return PushUnprocessedSocialMediaRequest$UnprocessedSocialMediaMessage$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ UnprocessedSocialMediaMessage(int i5, int i6, String str, String str2, String str3, String str4, SocialMediaEntity.Type type2, PackageName packageName, String str5, q0 q0Var) {
            if (255 == (i5 & 255)) {
                this.id = i6;
                this.title = str;
                this.status = str2;
                this.sender = str3;
                this.message = str4;
                this.type = type2;
                this.application = packageName;
                this.timeString = str5;
                return;
            }
            w.x(i5, 255, PushUnprocessedSocialMediaRequest$UnprocessedSocialMediaMessage$$serializer.INSTANCE.getDescriptor());
            throw null;
        }

        public static final /* synthetic */ void write$Self$library_release(UnprocessedSocialMediaMessage unprocessedSocialMediaMessage, b bVar, SerialDescriptor serialDescriptor) {
            KSerializer[] kSerializerArr = $childSerializers;
            q qVar = (q) bVar;
            qVar.c0(0, unprocessedSocialMediaMessage.id, serialDescriptor);
            qVar.f0(serialDescriptor, 1, unprocessedSocialMediaMessage.title);
            u0 u0Var = u0.f536a;
            qVar.s(serialDescriptor, 2, u0Var, unprocessedSocialMediaMessage.status);
            qVar.s(serialDescriptor, 3, u0Var, unprocessedSocialMediaMessage.sender);
            qVar.f0(serialDescriptor, 4, unprocessedSocialMediaMessage.message);
            qVar.e0(serialDescriptor, 5, kSerializerArr[5], unprocessedSocialMediaMessage.type);
            qVar.e0(serialDescriptor, 6, kSerializerArr[6], unprocessedSocialMediaMessage.application);
            qVar.f0(serialDescriptor, 7, unprocessedSocialMediaMessage.timeString);
        }

        public final PackageName getApplication() {
            return this.application;
        }

        public final int getId() {
            return this.id;
        }

        public final String getMessage() {
            return this.message;
        }

        public final String getSender() {
            return this.sender;
        }

        public final String getStatus() {
            return this.status;
        }

        public final String getTimeString() {
            return this.timeString;
        }

        public final String getTitle() {
            return this.title;
        }

        public final SocialMediaEntity.Type getType() {
            return this.type;
        }

        public UnprocessedSocialMediaMessage(int i5, String str, String str2, String str3, String str4, SocialMediaEntity.Type type2, PackageName packageName, String str5) {
            K.n(str, "title");
            K.n(str4, "message");
            K.n(type2, "type");
            K.n(packageName, "application");
            K.n(str5, "timeString");
            this.id = i5;
            this.title = str;
            this.status = str2;
            this.sender = str3;
            this.message = str4;
            this.type = type2;
            this.application = packageName;
            this.timeString = str5;
        }
    }

    public PushUnprocessedSocialMediaRequest(List<UnprocessedSocialMediaMessage> list) {
        K.n(list, "messages");
        this.messages = list;
    }
}
