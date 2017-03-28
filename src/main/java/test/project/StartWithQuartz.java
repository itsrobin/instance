package test.project;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author lifeng
 * @version 1.0 on 2017/2/20.
 * 使用quartz实现定时任务
 */
public class StartWithQuartz {
    public static void start() throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //设置任务
        JobDetail jobDetail = newJob(Model.class).withIdentity("testJob_1","group_1").build();


        //设置任务的驱动时间
        Trigger trigger=
                newTrigger()
                        .withIdentity("trigger_1","group_1")
                        .withSchedule(cronSchedule("0/1 * * * * ?")
                        )
                        .build();

//        scheduler.start();
//        //驱动任务
//        scheduler.scheduleJob(jobDetail,trigger);
    }
}
