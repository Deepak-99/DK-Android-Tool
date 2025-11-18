package com.hawkshaw.library.ktextensions;

import U1.a;
import X1.B;
import Y1.K;
import a3.e;
import a3.j;
import a3.k;
import i3.l;
import i3.p;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;
import t3.C0973B;
import t3.C0974C;
import t3.E;
import t3.I;
import t3.J;
import t3.Y;
import t3.f0;
import t3.o0;

public final class CoroutineKt {
    private static final C0974C coroutineExceptionHandler = new CoroutineKt$special$$inlined$CoroutineExceptionHandler$1(C0973B.f9267D);
    private static final Map<String, I> deferreds = new LinkedHashMap();

    public static final <T> I doAsync(String str, l lVar) {
        K.n(str, "key");
        K.n(lVar, "action");
        Map<String, I> map = deferreds;
        I i5 = map.get(str);
        if (i5 != null) {
            if (!i5.f()) {
                i5 = null;
            }
            if (i5 != null) {
                return i5;
            }
        }
        J e5 = B.e(Y.f9306D, (j) null, new a(lVar, (e) null), 3);
        map.put(str, e5);
        return e5;
    }

    public static final void forget(String str) {
        K.n(str, "key");
        I i5 = deferreds.get(str);
        if (i5 != null) {
            ((o0) i5).e((CancellationException) null);
        }
    }

    public static final f0 safeLaunch(E e5, j jVar, p pVar) {
        K.n(e5, "<this>");
        K.n(jVar, "context");
        K.n(pVar, "block");
        return B.r(e5, jVar.plus(coroutineExceptionHandler), 0, pVar, 2);
    }

    public static /* synthetic */ f0 safeLaunch$default(E e5, j jVar, p pVar, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            jVar = k.f3728D;
        }
        return safeLaunch(e5, jVar, pVar);
    }
}
