package util;

import okhttp3.*;
import okhttp3.internal.Util;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author lifeng
 * @createTime 2017/2/13.
 * @updateTime 2017/2/13
 */
public class OkHttpUtil {
    private final  OkHttpClient okHttpClient = getOkHttpClient();

    private final static OkHttpUtil okHttpUtil = new OkHttpUtil();


    public static OkHttpUtil getInstance() {
        return okHttpUtil;
    }

    private Call processGetParam(String url){
        Request.Builder requestBuilder = new Request.Builder().url(url);
        //可以省略，默认是GET请求
        requestBuilder.method("GET",null);
        Request request = requestBuilder.build();
        return okHttpClient.newCall(request);
    }

    /**
     * 同步执行url操作
     * @param url 请求地址
     * @return
     */
    public String doGet(String url) throws IOException {
        Call call = processGetParam(url);
        Response response = call.execute();
        return response.body().string();
    }


    /**
     * 异步执行get操作
     * @param url 请求地址
     */
    public void getAsynHttp(String url) {
        final long b = System.currentTimeMillis();
        Call mcall = processGetParam(url);
        mcall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (null != response.cacheResponse()) {
                    String str = response.cacheResponse().toString();
                    System.out.println(str);
                } else {
                    System.out.println(response.body().string());
                    System.out.println(System.currentTimeMillis()-b);
                }
            }
        });

    }

    public void postAsynHttpForm(String url, Map<String, String> map, Map<String, String> headers) throws IOException {

        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        Request.Builder requestBuilder = new Request.Builder();

        if (map != null) {
            for (String s : map.keySet()) {
                formBodyBuilder = formBodyBuilder.add(s, map.get(s));
            }
        }

        if (headers != null) {
            for (String s : headers.keySet()) {
                requestBuilder = requestBuilder.addHeader(s, headers.get(s));
            }
        }

    final long b = System.currentTimeMillis();
        RequestBody formBody = formBodyBuilder.build();
        Request request = requestBuilder
                .url(url)
                .post(formBody)
                .build();

        Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String str = response.body().string();
//
//                System.out.println(str);
//                System.out.println("调用时间:"+(System.currentTimeMillis()-b));
//            }
//
//        });

        Response response =  call.execute();
        System.out.println(response.body().string()+" 调用时间:"+(System.currentTimeMillis()-b));

    }

    private static OkHttpClient getOkHttpClient() {
        File sdcache = new File("/Users/lifeng/Desktop ");
        int cacheSize = 10 * 1024 * 1024;
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .cache(new Cache(sdcache.getAbsoluteFile(), cacheSize));
        return builder.build();
    }


}
