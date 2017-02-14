package test.api;


import com.google.common.collect.Lists;
import org.junit.Test;
import util.OkHttpUtil;

import java.io.*;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lifeng
 * @createTime 2017/2/14.
 * @updateTime 2017/2/14
 */
public class TestModel {

    public static ThreadPoolExecutor executor = new ThreadPoolExecutor(200 /* corePoolSize */,
            200 /* maximumPoolSize */, 10L /* keepAliveTime */, TimeUnit.SECONDS,
            new ArrayBlockingQueue(100), new ThreadPoolExecutor.CallerRunsPolicy());

    private static volatile boolean shutdown = false;

    public static void main(String[] args) throws IOException {
        executor.allowsCoreThreadTimeOut();
        File file = new File("src/main/resources/ip");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String s = "";
        final List<String> list = new ArrayList<>(296796);
        final int i = 0;
        while ((s = reader.readLine()) != null) {
            list.add(s);
        }

        final List<String> osTypes = Lists.newArrayList("未知", "WWW", "m站", "android", "iOS");

        final Random random = new Random();

        final Map<String, String> queryParam = new HashMap<>(4);
        queryParam.put("account", "congshao");
        queryParam.put("password", "MTIzNDU2");

        final AtomicInteger atomic = new AtomicInteger(0);

        long start = System.currentTimeMillis();
        long end;
        for (int j = 0; j < list.size(); j++) {
            queryParam.put("ip", list.get(j));
            queryParam.put("osTypes", osTypes.get(random.nextInt(5)));
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        OkHttpUtil.getInstance().postAsynHttpForm("http://dolphin.mycreditpal.com:8888/ecreditpal/rest/model/CCNB", queryParam, null);
                        System.out.println(atomic.incrementAndGet());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            if (atomic.intValue() > 2000) {
                end = System.currentTimeMillis();
                System.out.println("费时" + (end - start));
                break;
            }
        }



    }


}

//        for (int n = 0; n < 50000; n++) {
//            executor.execute(new Runnable() {
//                @Override
//                public void run() {
//                     OkHttpUtil.getInstance().postAsynHttpForm("http://dolphin.mycreditpal.com:8888/ecreditpal/rest/model/CCNB",queryParam,null);
//                }
//            });
//        }




