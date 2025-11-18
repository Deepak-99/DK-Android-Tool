package com.hawkshaw.library.activities;

import Y1.K;
import a3.e;
import a3.j;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.activity.o;
import com.hawkshaw.library.HawkshawInitializer;
import com.hawkshaw.library.ktextensions.CoroutineKt;
import d3.C0393a;
import o.C0769d;
import x1.C1071c;
import x1.C1072d;

public final class TransparentActivity extends o {
    private boolean resumed;

    public enum Action {
        StartHawkshawInitializer;

        static {
            Action[] $values;
            $ENTRIES = K.J($values);
        }

        public static C0393a getEntries() {
            return $ENTRIES;
        }
    }

    public enum FinishAction {
        None,
        Finish,
        OpenSettingsActivity;

        static {
            FinishAction[] $values;
            $ENTRIES = K.J($values);
        }

        public static C0393a getEntries() {
            return $ENTRIES;
        }
    }

    private final void startHawkshawInitializer() {
        Intent intent = getIntent();
        boolean z4 = true;
        if (intent != null) {
            z4 = intent.getBooleanExtra("check_init_flag", true);
        }
        Intent intent2 = getIntent();
        new HawkshawInitializer(this, new C1072d(0, intent2 != null ? intent2.getStringExtra("finish_action") : null, this)).init(z4);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        TextView textView = new TextView(this);
        textView.setText("Loading...");
        setContentView((View) textView);
        Intent intent = getIntent();
        String action = intent != null ? intent.getAction() : null;
        if (action == null || action.hashCode() != -1223404600 || !action.equals("StartHawkshawInitializer")) {
            CoroutineKt.safeLaunch$default(C0769d.R(this), (j) null, new C1071c(this, (e) null), 1, (Object) null);
        } else {
            startHawkshawInitializer();
        }
    }

    public void onResume() {
        super.onResume();
        boolean booleanExtra = getIntent().getBooleanExtra("OPEN_ACTIVITY_IN_FOREGROUND", false);
        if (this.resumed) {
            finish();
        }
        if (booleanExtra) {
            this.resumed = true;
        }
    }
}
