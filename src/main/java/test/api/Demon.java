package test.api;

import org.junit.Test;
import util.OkHttpUtil;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author lifeng
 * @version 1.0 on 2017/2/20.
 */
public class Demon {



    public static void main(String[] args) throws IOException {
        System.out.println(postRequest());
    }



    public static String postRequest() throws IOException {
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


        return OkHttpUtil.getInstance().doPost(url,param,null);
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
