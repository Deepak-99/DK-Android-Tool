package com.hawkshaw.library;

import android.content.Intent;
import androidx.annotation.Keep;
import com.hawkshaw.library.App;
import com.hawkshaw.library.activities.TransparentActivity;
import com.hawkshaw.library.preferences.Prefs;
import me.pushy.sdk.lib.paho.internal.ClientDefaults;

@Keep
public final class Hawkshaw {
    @Keep
    public static void initFromInternalActivity(boolean z4) {
        initFromInternalActivity(z4, TransparentActivity.FinishAction.Finish.name());
    }

    @Keep
    public static void initFromInternalActivity(boolean z4, String str) {
        if (!z4 || !Prefs.INSTANCE.getInitFlag()) {
            App.Companion companion = App.Companion;
            Intent intent = new Intent(companion.getContext(), TransparentActivity.class);
            intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
            intent.setAction(TransparentActivity.Action.StartHawkshawInitializer.name());
            intent.putExtra("check_init_flag", z4);
            intent.putExtra("finish_action", str);
            companion.getContext().startActivity(intent);
        }
    }
}
