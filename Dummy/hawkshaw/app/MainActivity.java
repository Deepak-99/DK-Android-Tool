package com.hawkshaw.app;

import android.os.Bundle;
import androidx.activity.o;
import androidx.lifecycle.V;
import com.hawkshaw.library.HawkshawInitializer;
import com.hawkshaw.library.R;

public final class MainActivity extends o {
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_main);
        HawkshawInitializer.init$default(new HawkshawInitializer(this, new V(this, 3)), false, 1, (Object) null);
    }
}
