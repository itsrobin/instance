package test.project;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lifeng
 * @version 1.0 on 2017/2/20.
 */
public  class Model implements Job {
    private static List<Model> list = new ArrayList<>();

    public static void register(Model model) {
        list.add(model);
    }

    public void work(){};

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        for (Model model : list) {
            model.work();
        }
        if (list.size() == 0){
            System.out.println("初始化子类对象");
            SubModel subModel = new SubModel();
            SubModel subModel2 = new SubModel();
        }
    }


}
