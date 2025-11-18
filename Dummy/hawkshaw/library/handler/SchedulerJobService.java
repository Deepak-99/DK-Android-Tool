package com.hawkshaw.library.handler;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;

public final class SchedulerJobService extends JobService {
    public boolean onStartJob(JobParameters jobParameters) {
        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
        intent.putExtra("source", "SchedulerJobService");
        getApplicationContext().sendBroadcast(intent);
        return false;
    }

    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
