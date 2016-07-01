package com.lcf.like.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.view.View;

/**
 * @author LiChaofeng
 * @description
 * @date 2016/6/30 19:20
 * @since 1.0
 */
public class FooterViewModel extends BaseObservable implements ViewModel {
    private Context context;
    private boolean isLoadingMore;
    private boolean isLoadingFailed;
    private String text;

    public FooterViewModel(Context context, boolean isLoadingMore, boolean isLoadingFailed) {
        this.context = context;
        this.isLoadingMore = isLoadingMore;
        this.isLoadingFailed = isLoadingFailed;
    }

    public boolean isLoadingFailed() {
        return isLoadingFailed;
    }

    public String getText() {
        if (isLoadingFailed) {
            text = "加载失败，点击重试";
        } else if (!isLoadingMore) {
            text = "没有更多数据";
        } else {
            text = "加载中";
        }
        return text;
    }

    public void onFooterClick(View view) {
        if (isLoadingFailed) {
            // When load failed,retry load
        }
    }

    @Override
    public void destroy() {

    }
}
