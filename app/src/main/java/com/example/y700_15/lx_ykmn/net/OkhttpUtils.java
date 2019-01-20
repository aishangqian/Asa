package com.example.y700_15.lx_ykmn.net;

import android.os.Handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkhttpUtils {

    private static OkhttpUtils mInstance;
    private OkHttpClient okHttpClient;
    private Handler handler = new Handler();

    private OkhttpUtils(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .writeTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();
    }

    public static OkhttpUtils getmInstance(){
        if (mInstance==null){
            synchronized (OkhttpUtils.class){
                if (mInstance==null){
                    mInstance = new OkhttpUtils();
                }
            }
        }
        return mInstance;
    }

    /**
     * get请求方式
     */
    public void doGet(String url, HashMap<String,String> params, final OkhttpCallback okhttpCallback){
        Request request = new Request.Builder().url(url)
                .get().build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (okhttpCallback!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okhttpCallback.failure("网络异常");
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                if (okhttpCallback!=null){

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                okhttpCallback.success(result);
                            }
                        });

                }
            }
        });
    }


    /**
     * post请求方式
     */
    public void doPost(String url, HashMap<String,String> params, final OkhttpCallback okhttpCallback){
        FormBody.Builder builder = new FormBody.Builder();

        for (Map.Entry<String,String> map : params.entrySet()){
            builder.add(map.getKey(),map.getValue());
        }

        RequestBody requestBody = builder.build();

        Request request = new Request.Builder().url(url)
                .post(requestBody).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (okhttpCallback!=null){
                    okhttpCallback.failure("网络异常");
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (okhttpCallback!=null){
                    if (200==response.code()){
                        final String result = response.body().string();

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                okhttpCallback.success(result);
                            }
                        });
                    }
                }
            }
        });
    }

    /**
     * 取消所有请求
     */

    public void cancelTask(){
        if (okHttpClient!=null){
            okHttpClient.dispatcher().cancelAll();
        }
    }

}
