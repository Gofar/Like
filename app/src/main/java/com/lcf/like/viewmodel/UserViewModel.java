package com.lcf.like.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.util.Log;

import com.google.gson.Gson;
import com.lcf.like.core.cache.UserCache;
import com.lcf.like.core.rxcache.RxUserCache;
import com.lcf.like.managers.NetworkManager;
import com.lcf.like.model.User;
import com.lcf.like.utils.MD5Util;

import java.util.HashMap;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @author LiChaofeng
 * @description
 * @date 2016/8/3 19:12
 * @since 1.0
 */
public class UserViewModel extends MyBaseObservable implements ViewModel {
    private Context context;
    private User user;
    private UserCache userCache;
    private RxUserCache rxUserCache;
    public ObservableField<String> userName;

    public UserViewModel(Context context, String login) {
        this.context = context;

        userName=new ObservableField<>();

        userCache = new UserCache();
        rxUserCache = new RxUserCache();

        loadData(login);
    }

    public String getLogin() {
        return user.login;
    }

    public String getName() {
        return user.name;
    }

    public String getBlog() {
        return user.blog;
    }

    public String getCompany() {
        return user.company;
    }

    public String getLocation() {
        return user.location;
    }

    public String getFollowers() {
        return String.valueOf(user.followers);
    }

    public String getFollowering() {
        return String.valueOf(user.following);
    }

    public String getRepos() {
        return String.valueOf(user.getPublicRepos());
    }

    public void setUser(User user) {
        this.user = user;
        notifyChange();
    }

    private void loadData(String login) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("login", login);

        //loadNormal(login);
        //loadCache(param);
        loadRxCache(param);
    }

    private void loadNormal(String login) {
        subscription = NetworkManager.getGitHubApi().getUserDetails(login)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<User>() {
                    @Override
                    public void call(User user) {
                        setUser(user);

                        //name.set(user.name);
                    }
                });
    }

    private void loadCache(HashMap<String, Object> param) {
        userCache.setKey(MD5Util.md5(this.getClass().getSimpleName() + new Gson().toJson(param)));
        subscription = userCache.processing(false, param)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<User>() {
                    @Override
                    public void call(User user) {
                        setUser(user);

                        //name.set(user.name);
                    }
                });
    }

    private void loadRxCache(HashMap<String, Object> param) {
        rxUserCache.setKey(MD5Util.md5(this.getClass().getSimpleName() + new Gson().toJson(param)));
        subscription = rxUserCache.processing(false, param)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {
                        Log.d("uservm","onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("uservm",e.toString());
                    }

                    @Override
                    public void onNext(User user) {
                        //setUser(user);

                        userName.set(user.name);
                        Log.d("uservm",user.name);
                    }
                });
    }

    @Override
    public void destroy() {
        unsubscribe();
        subscription = null;
        context = null;
    }
}
