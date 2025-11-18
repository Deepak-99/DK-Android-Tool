package com.hawkshaw.library.datalayer.models.accessibility;

import B3.f;
import D3.b;
import E3.C0020d;
import E3.o0;
import E3.q0;
import E3.u0;
import Y1.K;
import j3.t;
import java.util.List;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import p3.q;
import w3.w;

@f
public final class PushNotificationRequest {
    /* access modifiers changed from: private */
    public static final KSerializer[] $childSerializers = {new C0020d(PushNotificationRequest$Notification$$serializer.INSTANCE, 0)};
    public static final Companion Companion = new Companion((j3.f) null);
    private final List<Notification> notifications;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(j3.f fVar) {
            this();
        }

        public final KSerializer serializer() {
            return PushNotificationRequest$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ PushNotificationRequest(int i5, List list, q0 q0Var) {
        if (1 == (i5 & 1)) {
            this.notifications = list;
        } else {
            w.x(i5, 1, PushNotificationRequest$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
    }

    public static /* synthetic */ void getNotifications$annotations() {
    }

    public final List<Notification> getNotifications() {
        return this.notifications;
    }

    @f
    public static final class Notification {
        /* access modifiers changed from: private */
        public static final KSerializer[] $childSerializers = {null, null, null, null, null, new o0(t.a(String.class), u0.f536a), null, null};
        public static final Companion Companion = new Companion((j3.f) null);
        private final String extraBigText;
        private final String extraMessage;
        private final String extraText;
        private final String[] extraTextLines;
        private final String extraTitle;
        private final String packageName;
        private final String tickerText;
        private final long timestamp;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final KSerializer serializer() {
                return PushNotificationRequest$Notification$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Notification(int i5, String str, String str2, String str3, String str4, String str5, String[] strArr, String str6, long j5, q0 q0Var) {
            if (255 == (i5 & 255)) {
                this.packageName = str;
                this.extraText = str2;
                this.extraTitle = str3;
                this.extraMessage = str4;
                this.extraBigText = str5;
                this.extraTextLines = strArr;
                this.tickerText = str6;
                this.timestamp = j5;
                return;
            }
            w.x(i5, 255, PushNotificationRequest$Notification$$serializer.INSTANCE.getDescriptor());
            throw null;
        }

        public static /* synthetic */ void getExtraBigText$annotations() {
        }

        public static /* synthetic */ void getExtraMessage$annotations() {
        }

        public static /* synthetic */ void getExtraText$annotations() {
        }

        public static /* synthetic */ void getExtraTextLines$annotations() {
        }

        public static /* synthetic */ void getExtraTitle$annotations() {
        }

        public static /* synthetic */ void getPackageName$annotations() {
        }

        public static /* synthetic */ void getTickerText$annotations() {
        }

        public static /* synthetic */ void getTimestamp$annotations() {
        }

        public static final /* synthetic */ void write$Self$library_release(Notification notification, b bVar, SerialDescriptor serialDescriptor) {
            KSerializer[] kSerializerArr = $childSerializers;
            q qVar = (q) bVar;
            qVar.f0(serialDescriptor, 0, notification.packageName);
            u0 u0Var = u0.f536a;
            qVar.s(serialDescriptor, 1, u0Var, notification.extraText);
            qVar.s(serialDescriptor, 2, u0Var, notification.extraTitle);
            qVar.s(serialDescriptor, 3, u0Var, notification.extraMessage);
            qVar.s(serialDescriptor, 4, u0Var, notification.extraBigText);
            qVar.s(serialDescriptor, 5, kSerializerArr[5], notification.extraTextLines);
            qVar.s(serialDescriptor, 6, u0Var, notification.tickerText);
            qVar.d0(serialDescriptor, 7, notification.timestamp);
        }

        public final String getExtraBigText() {
            return this.extraBigText;
        }

        public final String getExtraMessage() {
            return this.extraMessage;
        }

        public final String getExtraText() {
            return this.extraText;
        }

        public final String[] getExtraTextLines() {
            return this.extraTextLines;
        }

        public final String getExtraTitle() {
            return this.extraTitle;
        }

        public final String getPackageName() {
            return this.packageName;
        }

        public final String getTickerText() {
            return this.tickerText;
        }

        public final long getTimestamp() {
            return this.timestamp;
        }

        public Notification(String str, String str2, String str3, String str4, String str5, String[] strArr, String str6, long j5) {
            K.n(str, "packageName");
            this.packageName = str;
            this.extraText = str2;
            this.extraTitle = str3;
            this.extraMessage = str4;
            this.extraBigText = str5;
            this.extraTextLines = strArr;
            this.tickerText = str6;
            this.timestamp = j5;
        }
    }

    public PushNotificationRequest(List<Notification> list) {
        K.n(list, "notifications");
        this.notifications = list;
    }
}
