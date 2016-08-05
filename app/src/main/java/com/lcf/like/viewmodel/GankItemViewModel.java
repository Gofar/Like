package com.lcf.like.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.view.View;

import com.lcf.like.model.GankItem;
import com.lcf.like.utils.UrlUtil;
import com.lcf.like.view.UserDetailsActivity;

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
        return gankItem.getPublishedAt().replaceAll("[a-zA-Z]", " ");
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

    public boolean isImageUrl() {
        return UrlUtil.isImageUrl(getUrl());
    }

    public int getVisibility() {
        return isImageUrl() ? View.VISIBLE : View.INVISIBLE;
    }

    public void onItemClick(View view) {
        if (isImageUrl()) {
            // start activity for big image
            //context.startActivity(new Intent());
        } else {
            // start activity for details
            //context.startActivity(new Intent());
        }
    }

    public void onUserClick(View view) {
        String login = "";
        String tag="github.com/";
        int start = gankItem.url.indexOf(tag);
        if (start >= 0) {
            int last = gankItem.url.indexOf("/", start+tag.length());
            if (last > 0) {
                login = gankItem.url.substring(start+tag.length(), last);
            } else {
                login = gankItem.url.substring(start + tag.length());
            }
        }
        Intent intent = new Intent(context, UserDetailsActivity.class);
        intent.putExtra("login", login);
        context.startActivity(intent);
    }

    /**
     * Allows recycling ItemRepoViewModels within the recyclerview adapter
     *
     * @param gankItem GankItem
     */
    public void setGankItem(GankItem gankItem) {
        this.gankItem = gankItem;
        notifyChange();
    }

    @Override
    public void destroy() {
        //In this case destroy doesn't need to do anything because there is not async calls
    }
}
