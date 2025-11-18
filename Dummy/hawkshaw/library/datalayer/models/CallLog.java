package com.hawkshaw.library.datalayer.models;

import B3.f;
import D3.b;
import E3.O;
import E3.V;
import E3.q0;
import E3.u0;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import w3.w;

@f
public final class CallLog {
    public static final Companion Companion = new Companion((j3.f) null);
    private final Integer blockReason;
    private final String callNew;
    private final String callScreeningAppName;
    private final String callType;
    private final Long date;
    private final Long duration;
    private final Integer features;
    private final String id;
    private final Long lastModified;
    private final String name;
    private final String number;
    private final String phoneAccountId;
    private final String simSlot;
    private final String voiceMailUri;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(j3.f fVar) {
            this();
        }

        public final KSerializer serializer() {
            return CallLog$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ CallLog(int i5, String str, String str2, String str3, Long l5, Long l6, String str4, String str5, String str6, Integer num, String str7, String str8, Long l7, String str9, Integer num2, q0 q0Var) {
        if (16383 == (i5 & 16383)) {
            this.id = str;
            this.name = str2;
            this.number = str3;
            this.date = l5;
            this.duration = l6;
            this.callType = str4;
            this.callNew = str5;
            this.simSlot = str6;
            this.features = num;
            this.voiceMailUri = str7;
            this.phoneAccountId = str8;
            this.lastModified = l7;
            this.callScreeningAppName = str9;
            this.blockReason = num2;
            return;
        }
        w.x(i5, 16383, CallLog$$serializer.INSTANCE.getDescriptor());
        throw null;
    }

    public static /* synthetic */ void getBlockReason$annotations() {
    }

    public static /* synthetic */ void getCallNew$annotations() {
    }

    public static /* synthetic */ void getCallScreeningAppName$annotations() {
    }

    public static /* synthetic */ void getCallType$annotations() {
    }

    public static /* synthetic */ void getDate$annotations() {
    }

    public static /* synthetic */ void getDuration$annotations() {
    }

    public static /* synthetic */ void getFeatures$annotations() {
    }

    public static /* synthetic */ void getId$annotations() {
    }

    public static /* synthetic */ void getLastModified$annotations() {
    }

    public static /* synthetic */ void getName$annotations() {
    }

    public static /* synthetic */ void getNumber$annotations() {
    }

    public static /* synthetic */ void getPhoneAccountId$annotations() {
    }

    public static /* synthetic */ void getSimSlot$annotations() {
    }

    public static /* synthetic */ void getVoiceMailUri$annotations() {
    }

    public static final /* synthetic */ void write$Self$library_release(CallLog callLog, b bVar, SerialDescriptor serialDescriptor) {
        u0 u0Var = u0.f536a;
        bVar.s(serialDescriptor, 0, u0Var, callLog.id);
        bVar.s(serialDescriptor, 1, u0Var, callLog.name);
        bVar.s(serialDescriptor, 2, u0Var, callLog.number);
        V v4 = V.f466a;
        bVar.s(serialDescriptor, 3, v4, callLog.date);
        bVar.s(serialDescriptor, 4, v4, callLog.duration);
        bVar.s(serialDescriptor, 5, u0Var, callLog.callType);
        bVar.s(serialDescriptor, 6, u0Var, callLog.callNew);
        bVar.s(serialDescriptor, 7, u0Var, callLog.simSlot);
        O o4 = O.f456a;
        bVar.s(serialDescriptor, 8, o4, callLog.features);
        bVar.s(serialDescriptor, 9, u0Var, callLog.voiceMailUri);
        bVar.s(serialDescriptor, 10, u0Var, callLog.phoneAccountId);
        bVar.s(serialDescriptor, 11, v4, callLog.lastModified);
        bVar.s(serialDescriptor, 12, u0Var, callLog.callScreeningAppName);
        bVar.s(serialDescriptor, 13, o4, callLog.blockReason);
    }

    public final Integer getBlockReason() {
        return this.blockReason;
    }

    public final String getCallNew() {
        return this.callNew;
    }

    public final String getCallScreeningAppName() {
        return this.callScreeningAppName;
    }

    public final String getCallType() {
        return this.callType;
    }

    public final Long getDate() {
        return this.date;
    }

    public final Long getDuration() {
        return this.duration;
    }

    public final Integer getFeatures() {
        return this.features;
    }

    public final String getId() {
        return this.id;
    }

    public final Long getLastModified() {
        return this.lastModified;
    }

    public final String getName() {
        return this.name;
    }

    public final String getNumber() {
        return this.number;
    }

    public final String getPhoneAccountId() {
        return this.phoneAccountId;
    }

    public final String getSimSlot() {
        return this.simSlot;
    }

    public final String getVoiceMailUri() {
        return this.voiceMailUri;
    }

    public CallLog(String str, String str2, String str3, Long l5, Long l6, String str4, String str5, String str6, Integer num, String str7, String str8, Long l7, String str9, Integer num2) {
        this.id = str;
        this.name = str2;
        this.number = str3;
        this.date = l5;
        this.duration = l6;
        this.callType = str4;
        this.callNew = str5;
        this.simSlot = str6;
        this.features = num;
        this.voiceMailUri = str7;
        this.phoneAccountId = str8;
        this.lastModified = l7;
        this.callScreeningAppName = str9;
        this.blockReason = num2;
    }
}
