package com.lcf.like.viewmodel;

import android.content.Context;

import com.lcf.like.managers.NetworkManager;
import com.lcf.like.managers.ResponseCallback;
import com.lcf.like.model.BaseGankEntity;
import com.lcf.like.model.GankItem;

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

    public GankViewModel(Context context, String type,
                         ResponseCallback<List<GankItem>> callback) {
        this.context = context;
        this.type = type;
        this.callback = callback;
    }

    public void loadGankData(int page) {
        loadGankData(page, 15);
    }

    public void loadGankData(int page, int limit) {
        unsubscribe();
        subscription = NetworkManager.getGankApi().getGankList(type, page, limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<BaseGankEntity<GankItem>>() {
                    @Override
                    public void onCompleted() {
                        callback.onResponse(gankItemList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onErrorRespon(e);
                    }

                    @Override
                    public void onNext(BaseGankEntity<GankItem> gankItemBaseGankEntity) {
                        gankItemList = gankItemBaseGankEntity.getResults();
                    }
                });
    }

    @Override
    public void destroy() {
        unsubscribe();
    }
}
