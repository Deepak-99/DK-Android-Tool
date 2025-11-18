package com.hawkshaw.library.datalayer.network.service;

import W2.y;
import a3.e;
import b3.C0298a;
import w3.C1066b;
import w3.c;

public final class FileServiceImpl$uploadThumbnails$$inlined$map$1 implements C1066b {
    final /* synthetic */ C1066b $this_unsafeTransform$inlined;

    public FileServiceImpl$uploadThumbnails$$inlined$map$1(C1066b bVar) {
        this.$this_unsafeTransform$inlined = bVar;
    }

    public Object collect(final c cVar, e eVar) {
        Object collect = this.$this_unsafeTransform$inlined.collect(new c() {
            /* JADX WARNING: Removed duplicated region for block: B:12:0x002f  */
            /* JADX WARNING: Removed duplicated region for block: B:8:0x0021  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final java.lang.Object emit(java.lang.Object r8, a3.e r9) {
                /*
                    r7 = this;
                    boolean r0 = r9 instanceof com.hawkshaw.library.datalayer.network.service.FileServiceImpl$uploadThumbnails$$inlined$map$1.AnonymousClass2.AnonymousClass1
                    if (r0 == 0) goto L_0x0013
                    r0 = r9
                    com.hawkshaw.library.datalayer.network.service.FileServiceImpl$uploadThumbnails$$inlined$map$1$2$1 r0 = (com.hawkshaw.library.datalayer.network.service.FileServiceImpl$uploadThumbnails$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                    int r1 = r0.label
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r3 = r1 & r2
                    if (r3 == 0) goto L_0x0013
                    int r1 = r1 - r2
                    r0.label = r1
                    goto L_0x0018
                L_0x0013:
                    com.hawkshaw.library.datalayer.network.service.FileServiceImpl$uploadThumbnails$$inlined$map$1$2$1 r0 = new com.hawkshaw.library.datalayer.network.service.FileServiceImpl$uploadThumbnails$$inlined$map$1$2$1
                    r0.<init>(r7, r9)
                L_0x0018:
                    java.lang.Object r9 = r0.result
                    b3.a r1 = b3.C0298a.f4140D
                    int r2 = r0.label
                    r3 = 1
                    if (r2 == 0) goto L_0x002f
                    if (r2 != r3) goto L_0x0027
                    Y1.C0110h.P(r9)
                    goto L_0x007b
                L_0x0027:
                    java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                    java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
                    r8.<init>(r9)
                    throw r8
                L_0x002f:
                    Y1.C0110h.P(r9)
                    w3.c r9 = r3
                    com.hawkshaw.library.datalayer.models.BatchThumbUploadRequest r8 = (com.hawkshaw.library.datalayer.models.BatchThumbUploadRequest) r8
                    com.hawkshaw.library.BatchThumbUploadRequest$Builder r2 = com.hawkshaw.library.BatchThumbUploadRequest.newBuilder()
                    com.hawkshaw.library.BatchThumbUploadRequest$Thumbnail$Builder r4 = com.hawkshaw.library.BatchThumbUploadRequest.Thumbnail.newBuilder()
                    com.hawkshaw.library.datalayer.models.BatchThumbUploadRequest$Thumbnail r5 = r8.getThumb()
                    java.lang.String r5 = r5.getName()
                    com.hawkshaw.library.BatchThumbUploadRequest$Thumbnail$Builder r4 = r4.setName(r5)
                    com.hawkshaw.library.datalayer.models.BatchThumbUploadRequest$Thumbnail r5 = r8.getThumb()
                    java.lang.String r5 = r5.getPath()
                    com.hawkshaw.library.BatchThumbUploadRequest$Thumbnail$Builder r4 = r4.setPath(r5)
                    com.hawkshaw.library.datalayer.models.BatchThumbUploadRequest$Thumbnail r8 = r8.getThumb()
                    byte[] r8 = r8.getContent()
                    com.google.protobuf.l r5 = com.google.protobuf.C0366m.f4803E
                    int r5 = r8.length
                    r6 = 0
                    com.google.protobuf.l r8 = com.google.protobuf.C0366m.j(r6, r8, r5)
                    com.hawkshaw.library.BatchThumbUploadRequest$Thumbnail$Builder r8 = r4.setContent(r8)
                    com.hawkshaw.library.BatchThumbUploadRequest$Builder r8 = r2.setThumb((com.hawkshaw.library.BatchThumbUploadRequest.Thumbnail.Builder) r8)
                    com.google.protobuf.I r8 = r8.build()
                    r0.label = r3
                    java.lang.Object r8 = r9.emit(r8, r0)
                    if (r8 != r1) goto L_0x007b
                    return r1
                L_0x007b:
                    W2.y r8 = W2.y.f2418a
                    return r8
                */
                throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.network.service.FileServiceImpl$uploadThumbnails$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, a3.e):java.lang.Object");
            }
        }, eVar);
        return collect == C0298a.f4140D ? collect : y.f2418a;
    }
}
