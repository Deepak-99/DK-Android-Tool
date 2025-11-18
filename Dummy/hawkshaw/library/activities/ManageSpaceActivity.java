package com.hawkshaw.library.activities;

import Y1.K;
import a3.e;
import a3.j;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.activity.o;
import com.hawkshaw.library.Hawkshaw;
import com.hawkshaw.library.R;
import com.hawkshaw.library.ktextensions.ContextKt;
import com.hawkshaw.library.ktextensions.CoroutineKt;
import j3.q;
import j3.s;
import java.util.concurrent.CancellationException;
import o.C0769d;
import t3.f0;
import x1.C1069a;
import x1.C1070b;

public final class ManageSpaceActivity extends o {
    /* access modifiers changed from: private */
    public static final void onCreate$lambda$0(q qVar, ManageSpaceActivity manageSpaceActivity, s sVar, View view) {
        K.n(qVar, "$count");
        K.n(manageSpaceActivity, "this$0");
        K.n(sVar, "$job");
        int i5 = qVar.f7498D + 1;
        qVar.f7498D = i5;
        if (i5 >= 3) {
            ContextKt.toast(manageSpaceActivity, "Launching Hawkshaw");
            Hawkshaw.initFromInternalActivity(false);
            return;
        }
        ContextKt.toast(manageSpaceActivity, "Data Cleared!");
        f0 f0Var = (f0) sVar.f7500D;
        if (f0Var != null) {
            f0Var.e((CancellationException) null);
        }
        sVar.f7500D = CoroutineKt.safeLaunch$default(C0769d.R(manageSpaceActivity), (j) null, new C1070b(qVar, (e) null), 1, (Object) null);
    }

    /* JADX WARNING: type inference failed for: r4v2, types: [java.lang.Object, j3.q] */
    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Object, j3.s] */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_manage_space);
        ((Button) findViewById(R.id.clearDataButton)).setOnClickListener(new C1069a(new Object(), this, new Object()));
    }
}
