package com.lcf.like.core.rxcache;

import com.lcf.like.managers.DiskManager;
import com.lcf.like.managers.NetworkManager;
import com.lcf.like.model.BaseListEntity;
import com.lcf.like.model.GankItem;
import com.lcf.like.model.RealmGankItem;

import java.util.HashMap;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * @author LiChaofeng
 * @description
 * @date 2016/8/2 17:06
 * @since 1.0
 */
public class RxGankListCache2 extends RxBaseListCache3<GankItem, RealmGankItem> {
    @Override
    protected List<GankItem> obtainFromDisk(HashMap<String, Object> param) {
        System.out.println("obtainFromDisk is on processing.");
        DiskManager<GankItem, RealmGankItem> manager = new DiskManager<>();
        List<GankItem> itemList = manager.loadAllFromDisk(new GankItem());
        manager.closeRealm();
        return itemList;
    }

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
