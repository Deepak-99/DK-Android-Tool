package com.hawkshaw.library.datalayer.network.twirp.interceptors;

import X2.o;
import Y1.K;
import com.hawkshaw.library.logger.Logger;
import i3.l;
import java.util.ArrayList;
import r3.j;
import u2.C1035f;

public final class LoggingInterceptorKt$installLoggingInterceptor$1$1 implements C1035f {
    public void log(String str) {
        K.n(str, "message");
        ArrayList arrayList = new ArrayList();
        for (Object next : j.X(str)) {
            String str2 = (String) next;
            if (!j.J(str2, "Authorization", false) && !j.J(str2, "App-Id", false)) {
                arrayList.add(next);
            }
        }
        Logger.v$default(Logger.INSTANCE, "LoggingInterceptor", o.X0(arrayList, "\n", (String) null, (String) null, (l) null, 62), false, 4, (Object) null);
    }
}
