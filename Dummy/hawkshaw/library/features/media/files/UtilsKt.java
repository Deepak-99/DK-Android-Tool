package com.hawkshaw.library.features.media.files;

import P1.d;
import W2.y;
import X1.B;
import a3.e;
import b3.C0298a;
import java.io.File;
import java.util.List;
import t3.N;

public final class UtilsKt {
    public static final Object zipDirectory(File file, File file2, e eVar) {
        Object B4 = B.B(N.f9292c, new d(file2, file, (e) null), eVar);
        return B4 == C0298a.f4140D ? B4 : y.f2418a;
    }

    public static final Object zipFiles(List<? extends File> list, File file, e eVar) {
        Object B4 = B.B(N.f9292c, new P1.e(list, file, (e) null), eVar);
        return B4 == C0298a.f4140D ? B4 : y.f2418a;
    }
}
