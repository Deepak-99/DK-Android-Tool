package com.hawkshaw.library.datalayer.network.twirp;

import Y1.C0110h;
import Y1.K;
import a3.e;
import com.hawkshaw.library.logger.Logger;
import p3.C0870k;
import r3.C0931a;
import t3.N;
import y2.C1097c;
import z3.C1131d;

public final class ApiCallKt {
    private static final String TAG = "ApiCall";

    public static final <S, T> Object apiCall(String str, S s4, boolean z4, e eVar) {
        C1131d dVar = N.f9290a;
        K.A0();
        throw null;
    }

    public static Object apiCall$default(String str, Object obj, boolean z4, e eVar, int i5, Object obj2) {
        C1131d dVar = N.f9290a;
        K.A0();
        throw null;
    }

    private static final <T> Object toResponse(C1097c cVar, e eVar) {
        if (!C0110h.v(cVar.g())) {
            Logger logger = Logger.INSTANCE;
            Object h5 = K.h(cVar, C0931a.f8930a, eVar);
            Logger.e$default(logger, TAG, "ApiCallError: " + h5, (Exception) null, false, 12, (Object) null);
        }
        if (C0110h.v(cVar.g())) {
            K.A0();
            throw null;
        }
        int i5 = C0870k.f8500c;
        K.A0();
        throw null;
    }
}
