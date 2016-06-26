package com.lcf.like.api;

import com.lcf.like.model.GankItem;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author lcf
 * @version 1.0
 * @description
 * @time 2016/6/25 13:03
 */
public interface GankApi {
    String BASE_URL = "http://gank.io/api";

    @GET("/data/{type}/{page}/{limit}")
    List<GankItem> getGankList(@Path("type") String type, @Path("page") int page, @Path("limit") int limit);
}
