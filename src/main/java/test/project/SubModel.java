package test.project;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author lifeng
 * @version 1.0 on 2017/2/20.
 */
public class SubModel extends Model {

    public SubModel() {
        Model.register(this);
    }

    @Override
    public void work() {
        System.out.println(this+" start work");
    }

}
