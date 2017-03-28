package test.project;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;

/**
 * @author lifeng
 * @version 1.0 on 2017/2/20.
 */
public class TestModel{
    public static void main(String[] args) throws SchedulerException {
        StartWithQuartz.start();
    }


}
