package com.example.kuangjia.moudls;

import android.util.Log;


import com.example.kuangjia.com.Constant;
import com.example.kuangjia.moudls.api.ShopApi;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 封装http网络请求
 */
public class HttpManager {




    private ShopApi shopApi;  //商城的接口


    private static volatile HttpManager instance;
    public static HttpManager getInstance(){
        if(instance == null) {
            synchronized (HttpManager.class) {
                if (instance == null) {
                    instance = new HttpManager();
                }
            }
        }
        return instance;
    }

    private static Retrofit getRetrofit(String url){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(getOkhttpclient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }

    private static OkHttpClient getOkhttpclient(){
        File file = new File(Constant.PATH_CACHE); //本地的缓存文件
        Cache cache = new Cache(file,100*1024*1024);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .addInterceptor(new HeaderInterceptor())
                .cache(cache)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
        return client;
    }



    public ShopApi getShopApi(){
        if(shopApi == null) shopApi = getRetrofit(Constant.BASE_SHOP_URL).create(ShopApi.class);
        return shopApi;
    }

    /**
     * 抽取获取对应网络请求api的接口
     * @param url
     * @param cls
     * @param <T>
     * @return
     */
    private synchronized <T> T getApi(String url,Class<T> cls){
        return getRetrofit(url).create(cls);
    }



    static class HeaderInterceptor implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request().newBuilder()
                    .addHeader("Connection","keep-alive")
                    .build();
            return chain.proceed(request);
        }
    }


    static class LoggingInterceptor implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
            Log.i("interceptor",String.format("Sending request %s on %s%n%s",request.url(),chain.connection(),request.headers()));

            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            Log.i("Received:",String.format("Received response for %s in %.1fms%n%s",response.request().url(),(t2-t1)/1e6d,response.headers()));
            return response;
        }
    }



}
