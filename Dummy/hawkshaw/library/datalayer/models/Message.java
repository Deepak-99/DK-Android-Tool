package com.hawkshaw.library.datalayer.models;

import B3.f;
import D3.b;
import E3.O;
import E3.V;
import E3.q0;
import E3.u0;
import Y1.K;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import p3.q;
import w3.w;

@f
public final class Message {
    public static final Companion Companion = new Companion((j3.f) null);
    private final String address;
    private final String body;
    private final String contactName;
    private final String creator;
    private final Long date;
    private final Long dateSent;
    private final Long id;
    private final String subject;
    private final Integer threadId;
    private final Integer type;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(j3.f fVar) {
            this();
        }

        public final KSerializer serializer() {
            return Message$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ Message(int i5, Long l5, Integer num, String str, String str2, String str3, String str4, Integer num2, Long l6, Long l7, String str5, q0 q0Var) {
        if (1023 == (i5 & 1023)) {
            this.id = l5;
            this.threadId = num;
            this.address = str;
            this.contactName = str2;
            this.body = str3;
            this.subject = str4;
            this.type = num2;
            this.date = l6;
            this.dateSent = l7;
            this.creator = str5;
            return;
        }
        w.x(i5, 1023, Message$$serializer.INSTANCE.getDescriptor());
        throw null;
    }

    public static /* synthetic */ void getAddress$annotations() {
    }

    public static /* synthetic */ void getBody$annotations() {
    }

    public static /* synthetic */ void getContactName$annotations() {
    }

    public static /* synthetic */ void getCreator$annotations() {
    }

    public static /* synthetic */ void getDate$annotations() {
    }

    public static /* synthetic */ void getDateSent$annotations() {
    }

    public static /* synthetic */ void getId$annotations() {
    }

    public static /* synthetic */ void getSubject$annotations() {
    }

    public static /* synthetic */ void getThreadId$annotations() {
    }

    public static /* synthetic */ void getType$annotations() {
    }

    public static final /* synthetic */ void write$Self$library_release(Message message, b bVar, SerialDescriptor serialDescriptor) {
        V v4 = V.f466a;
        bVar.s(serialDescriptor, 0, v4, message.id);
        O o4 = O.f456a;
        bVar.s(serialDescriptor, 1, o4, message.threadId);
        u0 u0Var = u0.f536a;
        bVar.s(serialDescriptor, 2, u0Var, message.address);
        ((q) bVar).f0(serialDescriptor, 3, message.contactName);
        bVar.s(serialDescriptor, 4, u0Var, message.body);
        bVar.s(serialDescriptor, 5, u0Var, message.subject);
        bVar.s(serialDescriptor, 6, o4, message.type);
        bVar.s(serialDescriptor, 7, v4, message.date);
        bVar.s(serialDescriptor, 8, v4, message.dateSent);
        bVar.s(serialDescriptor, 9, u0Var, message.creator);
    }

    public final String getAddress() {
        return this.address;
    }

    public final String getBody() {
        return this.body;
    }

    public final String getContactName() {
        return this.contactName;
    }

    public final String getCreator() {
        return this.creator;
    }

    public final Long getDate() {
        return this.date;
    }

    public final Long getDateSent() {
        return this.dateSent;
    }

    public final Long getId() {
        return this.id;
    }

    public final String getSubject() {
        return this.subject;
    }

    public final Integer getThreadId() {
        return this.threadId;
    }

    public final Integer getType() {
        return this.type;
    }

    public Message(Long l5, Integer num, String str, String str2, String str3, String str4, Integer num2, Long l6, Long l7, String str5) {
        K.n(str2, "contactName");
        this.id = l5;
        this.threadId = num;
        this.address = str;
        this.contactName = str2;
        this.body = str3;
        this.subject = str4;
        this.type = num2;
        this.date = l6;
        this.dateSent = l7;
        this.creator = str5;
    }
}
