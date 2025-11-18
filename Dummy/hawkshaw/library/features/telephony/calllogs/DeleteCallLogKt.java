package com.hawkshaw.library.features.telephony.calllogs;

import Y1.K;
import android.content.Context;
import android.net.Uri;
import android.provider.CallLog;

public final class DeleteCallLogKt {
    public static final boolean deleteCallLog(Context context, String str) {
        K.n(context, "context");
        K.n(str, "id");
        return context.getContentResolver().delete(Uri.withAppendedPath(CallLog.Calls.CONTENT_URI, str), (String) null, (String[]) null) > 0;
    }
}
