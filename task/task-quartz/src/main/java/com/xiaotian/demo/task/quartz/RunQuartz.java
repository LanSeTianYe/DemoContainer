package com.xiaotian.demo.task.quartz;

import com.xiaotian.demo.task.quartz.job.Data;
import com.xiaotian.demo.task.quartz.job.HelloJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.HolidayCalendar;

import java.util.concurrent.TimeUnit;

@Slf4j
public class RunQuartz {

    private static Scheduler scheduler;

    static {
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
        } catch (SchedulerException e) {
            log.error(e.getMessage(), e);
        }
    }

    public static void main(String[] args) throws SchedulerException, InterruptedException {

        scheduler.start();

        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("name", new Data());
        JobDetail jobdetail = JobBuilder.newJob(HelloJob.class)
                .withIdentity("job1", "group1")
                .usingJobData(jobDataMap)
                .build();


        //排除执行
        HolidayCalendar holidayCalendar = new HolidayCalendar();
        scheduler.addCalendar("myHolidays", holidayCalendar, false, false);

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever().withIntervalInSeconds(3))
                .modifiedByCalendar("myHolidays")
                .build();


        scheduler.scheduleJob(jobdetail, trigger);

        TimeUnit.SECONDS.sleep(10);
        scheduler.standby();
        TimeUnit.SECONDS.sleep(1);
        scheduler.shutdown();

    }


}