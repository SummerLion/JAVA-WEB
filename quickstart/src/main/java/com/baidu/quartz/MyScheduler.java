package com.baidu.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 基于日历的作业调度。SimpleTrigger能实现的，CronTrigger都能实现。
 * 基于Cron表达式。类似于Linux上的crontab
 */
public class MyScheduler {
    public static void main(String [] args) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(Myjob.class).withIdentity("myjob").build();
        CronTrigger trigger = (CronTrigger) TriggerBuilder.newTrigger()
                .withIdentity("mytrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ? *"))
                .build();
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail,trigger);

    }

}
