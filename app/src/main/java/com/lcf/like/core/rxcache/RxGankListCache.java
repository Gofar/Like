package com.lcf.like.core.rxcache;

import com.lcf.like.managers.NetworkManager;
import com.lcf.like.model.BaseListEntity;
import com.lcf.like.model.GankItem;

import java.util.HashMap;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * @author LiChaofeng
 * @description
 * @date 2016/7/28 16:12
 * @since 1.0
 */
public class RxGankListCache extends RxBaseListCache<List<GankItem>> {
    @Override
    protected Observable<List<GankItem>> obtainFromNetwork(HashMap<String, Object> param) {
        System.out.println("obtainFromNetwork is on processing.");
        String type = (String) param.get("type");
        int limit = (int) param.get("limit");
        int page = (int) param.get("page");
        Observable<BaseListEntity<GankItem>> observable = NetworkManager.getGankApi().getGankList(type, limit, page);
        return observable.map(new Func1<BaseListEntity<GankItem>, List<GankItem>>() {
            @Override
            public List<GankItem> call(BaseListEntity<GankItem> baseListEntity) {
                return baseListEntity.getResults();
            }
        });

    }
}
