package com.hawkshaw.library.features.telephony;

import android.telephony.PhoneStateListener;
import r3.j;

public final class TelephonyCallbackService$phoneStateListener$2$1 extends PhoneStateListener {
    final /* synthetic */ TelephonyCallbackService this$0;

    public TelephonyCallbackService$phoneStateListener$2$1(TelephonyCallbackService telephonyCallbackService) {
        this.this$0 = telephonyCallbackService;
    }

    public void onCallStateChanged(int i5, String str) {
        super.onCallStateChanged(i5, str);
        if (str != null) {
            TelephonyCallbackService telephonyCallbackService = this.this$0;
            if (!j.U(str)) {
                telephonyCallbackService.phoneNumber = str;
            }
        }
        this.this$0.onCallStateChanged(i5);
    }
}
