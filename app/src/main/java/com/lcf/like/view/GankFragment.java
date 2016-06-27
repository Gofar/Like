package com.lcf.like.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lcf.like.managers.ResponseCallback;
import com.lcf.like.model.GankItem;
import com.lcf.like.viewmodel.GankViewModel;

import java.util.List;

/**
 * @Description:
 * @author: lichaofeng
 * @since: V3.3
 * @copyright © Medlinker
 * @Date: 2016/6/27 14:12
 */
public class GankFragment extends BaseFragment implements ResponseCallback<List<GankItem>>{
    private String type;
    private GankViewModel gankViewModel;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getArguments();
        if (bundle!=null){
            type=bundle.getString("type");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        gankViewModel=new GankViewModel(getContext(),type,this);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onResponse(List<GankItem> gankItems) {

    }

    @Override
    public void onErrorRespon(Throwable e) {

    }

    private void loadData(int page){
    }
}
