package com.hawkshaw.library;

import Y1.K;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.C0257m;
import androidx.lifecycle.C0264u;
import androidx.lifecycle.C0266w;
import androidx.lifecycle.C0268y;
import w3.z;

public final class HawkshawInitializer$init$1$2 implements C0264u {
    final /* synthetic */ HawkshawInitializer this$0;

    public HawkshawInitializer$init$1$2(HawkshawInitializer hawkshawInitializer) {
        this.this$0 = hawkshawInitializer;
    }

    public void onStateChanged(C0266w wVar, C0257m mVar) {
        K.n(wVar, "source");
        K.n(mVar, NotificationCompat.CATEGORY_EVENT);
        ((z) HawkshawInitializer.Companion.getActivityState$library_release()).f(this.this$0.toState(((C0268y) wVar.getLifecycle()).f3923d));
    }
}
