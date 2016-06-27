package com.lcf.like.managers;

import com.lcf.like.api.GankApi;

import retrofit2.Retrofit;

/**
 * @Description: Network Manager
 * @author: lichaofeng
 * @since: 1.0
 * @Date: 2016/6/27 17:03
 */
public class NetworkManager {
    private static GankApi gankApi;

    public static synchronized GankApi getGankApi(){
        if (gankApi==null){
            buildGankApi();
        }
        return gankApi;
    }

    private static void buildGankApi(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(GankApi.BASE_URL)
                .build();
        gankApi=retrofit.create(GankApi.class);
    }
}
