package com.hawkshaw.library.features.telephony;

import android.telephony.TelephonyCallback;

public final class TelephonyCallbackService$callStateListener$2$1 extends TelephonyCallback implements TelephonyCallback.CallStateListener {
    final /* synthetic */ TelephonyCallbackService this$0;

    public TelephonyCallbackService$callStateListener$2$1(TelephonyCallbackService telephonyCallbackService) {
        this.this$0 = telephonyCallbackService;
    }

    public void onCallStateChanged(int i5) {
        this.this$0.onCallStateChanged(i5);
    }
}
