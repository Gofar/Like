package com.lcf.like.api;

import com.lcf.like.model.BaseGankEntity;
import com.lcf.like.model.GankItem;

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
    String BASE_URL = "http://gank.io/api";

    @GET("/data/{type}/{page}/{limit}")
    Observable<BaseGankEntity<GankItem>> getGankList(@Path("type") String type, @Path("page") int page, @Path("limit") int limit);
}
