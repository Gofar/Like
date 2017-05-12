package com.lcf.like.managers;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.lcf.like.api.GankApi;
import com.lcf.like.api.GitHubApi;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Description: Network Manager
 * @author: lichaofeng
 * @since: 1.0
 * @Date: 2016/6/27 17:03
 */
public class NetworkManager {
    private static GankApi gankApi;
    private static GitHubApi gitHubApi;
    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addNetworkInterceptor(new StethoInterceptor())
            .build();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    public static synchronized GankApi getGankApi() {
        if (gankApi == null) {
            buildGankApi();
        }
        return gankApi;
    }

    private static void buildGankApi() {
        //okHttpClient.retryOnConnectionFailure();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GankApi.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .build();
        gankApi = retrofit.create(GankApi.class);
    }

    public static synchronized GitHubApi getGitHubApi(){
        if (gitHubApi==null){
            buildGitHubApi();
        }
        return gitHubApi;
    }

    private static void buildGitHubApi(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(GitHubApi.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .build();
        gitHubApi=retrofit.create(GitHubApi.class);
    }
}
