package map;

import java.util.HashMap;

/**
 * Created by lifeng on 16/4/23.
 */
public class Maps {

    private Maps(){}

    public static <K,V> HashMap<K,V> newHashMap() {
        return new HashMap<K, V>();
    }

}
