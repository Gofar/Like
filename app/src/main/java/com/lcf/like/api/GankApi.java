package com.lcf.like.api;

import com.lcf.like.model.BaseListEntity;
import com.lcf.like.model.GankItem;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author lcf
 * @version 1.0
 * @description
 * @time 2016/6/25 13:03
 */
public interface GankApi {
    String BASE_URL = "http://gank.io/api/";

    //@Headers("Cache-Control: max-age=640000")
    @GET("data/{type}/{limit}/{page}")
    Observable<BaseListEntity<GankItem>> getGankList(@Path("type") String type, @Path("limit") int limit, @Path("page") int page);

    @GET("data/{type}/{limit}/{page}")
    Call<BaseListEntity<GankItem>> getGankList2(@Path("type") String type, @Path("limit") int limit, @Path("page") int page);
}
