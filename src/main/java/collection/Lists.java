package collection;

import java.util.ArrayList;

/**
 * Created by lifeng on 16/5/28.
 */
public class Lists {
    private Lists() {};
    public static <T> ArrayList<T> newArrayList(){
        return new ArrayList<T>();
    }
}
