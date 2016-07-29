package com.lcf.like.viewmodel;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.lcf.like.core.cache.GankListCache;
import com.lcf.like.core.rxcache.RxGankListCache;
import com.lcf.like.managers.NetworkManager;
import com.lcf.like.managers.ResponseCallback;
import com.lcf.like.model.BaseListEntity;
import com.lcf.like.model.GankItem;
import com.lcf.like.utils.MD5Util;

import java.util.HashMap;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Description: Gank ViewModel
 * @author: lichaofeng
 * @since: 1.0
 * @Date: 2016/6/27 16:19
 */
public class GankViewModel extends MyBaseObservable implements ViewModel {
    private Context context;
    private String type;
    private List<GankItem> gankItemList;
    private ResponseCallback<List<GankItem>> callback;

    private GankListCache gankCache;

    private RxGankListCache rxGankCache;

    public GankViewModel(Context context, String type,
                         ResponseCallback<List<GankItem>> callback) {
        this.context = context;
        this.type = type;
        this.callback = callback;

        gankCache = new GankListCache();
        rxGankCache = new RxGankListCache();
    }

    public void loadGankData(int page) {
        loadGankData(page, 15);
    }

    public void loadGankData(int page, int limit) {
        unsubscribe();
        HashMap<String, Object> param = new HashMap<>();
        param.put("type", type);
        param.put("limit", limit);
        param.put("page", page);

        // Method one
        //loadNormalData(page,limit);

        // Method two
        loadCacheData(param);

        // Method three
        //loadRxCacheData(param);
    }

    private void loadNormalData(int page, int limit) {
        subscription = NetworkManager.getGankApi().getGankList(type, limit, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<BaseListEntity<GankItem>>() {
                    @Override
                    public void onCompleted() {
                        Log.d("Hello", "completed");
                        if (callback != null) {
                            callback.onResponse(gankItemList);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Hello", "onError:" + e.toString());
                        if (callback != null) {
                            callback.onErrorResponse(e);
                        }
                    }

                    @Override
                    public void onNext(BaseListEntity<GankItem> baseListEntity) {
                        Log.d("Hello", "onNext");
                        gankItemList = baseListEntity.getResults();
                    }
                });
    }

    private void loadCacheData(HashMap<String, Object> param) {
//        gankCache.setKey(this.getClass().getSimpleName() + type);
//        System.out.println("param:"+new Gson().toJson(param));
        gankCache.setKey(MD5Util.md5(this.getClass().getSimpleName() + new Gson().toJson(param)));
        subscription = gankCache.processing(false, param)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<GankItem>>() {
                    @Override
                    public void onCompleted() {
                        Log.d("Hello", "completed");
                        if (callback != null) {
                            callback.onResponse(gankItemList);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Hello", "onError:" + e.toString());
                        if (callback != null) {
                            callback.onErrorResponse(e);
                        }
                    }

                    @Override
                    public void onNext(List<GankItem> items) {
                        Log.d("Hello", "onNext");
                        gankItemList = items;
                    }
                });
    }

    private void loadRxCacheData(HashMap<String, Object> param){
        rxGankCache.setKey(MD5Util.md5(this.getClass().getSimpleName() + new Gson().toJson(param)));
        subscription = rxGankCache.processing(true, param)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<GankItem>>() {
                    @Override
                    public void onCompleted() {
                        Log.d("Hello", "completed");
                        if (callback != null) {
                            callback.onResponse(gankItemList);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Hello", "onError:" + e.toString());
                        if (callback != null) {
                            callback.onErrorResponse(e);
                        }
                    }

                    @Override
                    public void onNext(List<GankItem> items) {
                        Log.d("Hello", "onNext");
                        gankItemList = items;
                    }
                });
    }

    @Override
    public void destroy() {
        unsubscribe();
        subscription = null;
        context = null;
        callback = null;
    }
}
