package com.lcf.like.core.cache;

import com.lcf.like.managers.NetworkManager;
import com.lcf.like.model.BaseListEntity;
import com.lcf.like.model.GankItem;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * @author LiChaofeng
 * @description
 * @date 2016/7/27 17:10
 * @since 1.0
 */
public class GankListCache extends BaseListCache<List<GankItem>> {

    @Override
    protected List<GankItem> obtainFromNetwork(HashMap<String, Object> param) {
        System.out.println("obtainFromNetwork is on processing.");
        String type = (String) param.get("type");
        int limit = (int) param.get("limit");
        int page = (int) param.get("page");
        Call<BaseListEntity<GankItem>> call = NetworkManager.getGankApi().getGankList2(type, limit, page);
        List<GankItem> gankItems = null;
        try {
            Response<BaseListEntity<GankItem>> response = call.execute();
            BaseListEntity<GankItem> entity = response.body();
            if (entity != null) {
                gankItems = entity.getResults();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gankItems;
    }
}
