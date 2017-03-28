package test.api;

/**
 * @author lifeng
 * @version 1.0 on 2017/2/21.
 */
import util.OkHttpUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SimplePoolSizeCaculatorImpl extends PoolSizeCalculator {

    @Override
    protected Runnable creatTask() {
        return new AsyncIOTask();
    }

    @Override
    protected BlockingQueue createWorkQueue() {
        return new LinkedBlockingQueue(1000);
    }

    @Override
    protected long getCurrentThreadCPUTime() {
        return ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
    }

    public static void main(String[] args) {
        PoolSizeCalculator poolSizeCalculator = new SimplePoolSizeCaculatorImpl();
        poolSizeCalculator.calculateBoundaries(new BigDecimal(1.0), new BigDecimal(100000));
    }

}

/**
 * 自定义的异步IO任务
 * @author Will
 *
 */
class AsyncIOTask implements Runnable {

    @Override
    public void run() {
        String url = "http://panda.mycreditpal.com:8888/ecreditpal/rest/model/CCNB";
        Map<String,String> param = new HashMap<>();
        param.put("ip","171.82.68.181");
        param.put("osType","android");
        param.put("card_id","12312412");
        param.put("cookie","fdsfjlij32434");
        param.put("userAgent","fdsfjlij32434");
        param.put("url","mycreditpal.com");
        param.put("account", "woaika");
//        param.put("password", "a2FhaWVjcmVkaXRwYWw=");
        param.put("password", encodeBase64("kaaiecreditpal"));



        try {
            System.out.println(OkHttpUtil.getInstance().doPost(url,param,null));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String encodeBase64(String input) {
        try {
            byte[] b = input.getBytes();
            Class clazz = Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
            Method mainMethod = clazz.getMethod("encode", byte[].class);
            mainMethod.setAccessible(true);
            Object retObj = mainMethod.invoke(null, new Object[]{b});
            return (String) retObj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
