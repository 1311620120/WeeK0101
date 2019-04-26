package com.example.week0101.model;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class HttpUtils<T>  {


    private final OkHttpClient okHttpClient;

    public Api api;

    private  HttpUtils(){
        okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new Loggin())
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://172.17.8.100/ks/")
                .build();
        api = retrofit.create(Api.class);

    }
     private     static  class  HttpUtilsgetIntants{
         private  static  HttpUtils httpUtils =new HttpUtils();
     }
     public    static  HttpUtils getIntants(){
         return  HttpUtilsgetIntants.httpUtils;
     }
     private  class  Loggin implements Interceptor{

         @Override
         public Response intercept(Chain chain) throws IOException {
             Request request = chain.request();
             RequestBody body = request.body();
             Headers headers = request.headers();
             Response proceed = chain.proceed(request);
             Headers headers1 = proceed.headers();

             return proceed;
         }
     }

}
