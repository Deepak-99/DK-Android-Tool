package com.hawkshaw.library.datalayer.network.socket;

import W2.y;
import X1.C;
import X1.C0088h;
import X1.n;
import Y1.C0110h;
import Y1.K;
import a3.e;
import b3.C0298a;
import c3.C0331i;
import com.hawkshaw.library.logger.Logger;
import i3.p;
import java.net.ConnectException;
import t3.E;

public final class c extends C0331i implements p {

    /* renamed from: D  reason: collision with root package name */
    public int f4933D;

    /* renamed from: E  reason: collision with root package name */
    public final /* synthetic */ long f4934E;

    /* renamed from: F  reason: collision with root package name */
    public final /* synthetic */ SocketService f4935F;

    /* renamed from: G  reason: collision with root package name */
    public final /* synthetic */ C f4936G;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public c(long j5, SocketService socketService, C c5, e eVar) {
        super(2, eVar);
        this.f4934E = j5;
        this.f4935F = socketService;
        this.f4936G = c5;
    }

    public final e create(Object obj, e eVar) {
        return new c(this.f4934E, this.f4935F, this.f4936G, eVar);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((c) create((E) obj, (e) obj2)).invokeSuspend(y.f2418a);
    }

    public final Object invokeSuspend(Object obj) {
        SocketService socketService = this.f4935F;
        C0298a aVar = C0298a.f4140D;
        int i5 = this.f4933D;
        if (i5 == 0) {
            C0110h.P(obj);
            long j5 = this.f4934E;
            this.f4933D = 1;
            if (K.C(j5, this) == aVar) {
                return aVar;
            }
        } else if (i5 == 1) {
            try {
                C0110h.P(obj);
            } catch (n unused) {
                Logger.e$default(Logger.INSTANCE, "SocketService", "Connect: A violation against the WebSocket protocol was detected during the opening handshake.", (Exception) null, false, 12, (Object) null);
            } catch (C0088h unused2) {
                Logger.e$default(Logger.INSTANCE, "SocketService", "Connect: The certificate of the peer does not match the expected hostname.", (Exception) null, false, 12, (Object) null);
            } catch (X1.E unused3) {
                Logger.e$default(Logger.INSTANCE, "SocketService", "Connect: Failed to establish a WebSocket connection.", (Exception) null, false, 12, (Object) null);
            } catch (ConnectException e5) {
                Logger.e$default(Logger.INSTANCE, "SocketService", "Connect: ".concat(K.v0(e5)), (Exception) null, false, 12, (Object) null);
            } catch (Exception e6) {
                Exception exc = e6;
                Logger.e$default(Logger.INSTANCE, "SocketService", "Connect: ".concat(K.v0(exc)), exc, false, 8, (Object) null);
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        C c5 = this.f4936G;
        c5.i(socketService.listener);
        c5.b(socketService.listener);
        socketService.setHeaders(c5).e();
        return y.f2418a;
    }
}
