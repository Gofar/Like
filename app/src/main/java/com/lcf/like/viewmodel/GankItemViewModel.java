package com.lcf.like.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.view.View;

import com.lcf.like.model.GankItem;
import com.lcf.like.utils.UrlUtils;

/**
 * @author lcf
 * @version 1.0
 * @description
 * @time 2016/6/25 13:44
 */
public class GankItemViewModel extends BaseObservable implements ViewModel {
    private Context context;
    private GankItem gankItem;

    public GankItemViewModel(Context context, GankItem gankItem) {
        this.context = context;
        this.gankItem = gankItem;
    }

    public String getWho() {
        return gankItem.getWho();
    }

    public String getTime() {
        return gankItem.getPublishedAt();
    }

    public String getDesc() {
        return gankItem.getDesc();
    }

    public String getType() {
        return gankItem.getType();
    }

    public String getUrl() {
        return gankItem.getUrl();
    }

    public boolean isImageUrl(){
        return UrlUtils.isImageUrl(getUrl());
    }

    public int getVisibility(){
        return isImageUrl()? View.VISIBLE:View.INVISIBLE;
    }
    public void onItemClick(){
        if (isImageUrl()){
            // start activity for big image
            context.startActivity(new Intent());
        }else {
            // start activity for details
            context.startActivity(new Intent());
        }
    }

    public void setGankItem(GankItem gankItem) {
        this.gankItem = gankItem;
        notifyChange();
    }

    @Override
    public void destroy() {

    }
}
