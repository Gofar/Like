package com.lcf.like.api;

import com.lcf.like.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author LiChaofeng
 * @description
 * @date 2016/8/3 14:35
 * @since 1.0
 */
public interface GitHubApi {
    String BASE_URL="https://api.github.com/";
    @GET("users/{login}")
    Observable<User> getUserDetails(@Path("login") String login);

    Call<User> getUserDetails2(@Path("login") String login);
}
