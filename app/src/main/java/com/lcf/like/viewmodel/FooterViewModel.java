package com.lcf.like.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.view.View;

import com.lcf.like.adapter.GankItemAdapter;

/**
 * @author LiChaofeng
 * @description Footer ViewModel
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

    /**
     * Whether show loading ProgressBar:loading failed or has no more data
     *
     * @return Whether the progressbar show
     */
    public boolean isLoadingShow() {
        return isLoadingFailed || !isLoadingMore;
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

    public void onItemClick(View view) {
        if (isLoadingFailed ) {
            // When load failed,retry load
            GankItemAdapter.LoadingMoreListener listener= (GankItemAdapter.LoadingMoreListener) view.getTag();
            if (listener!=null){
                listener.onLoadingMore();
                isLoadingFailed=false;
                isLoadingMore=true;
                notifyChange();
            }
        }
    }

    @Override
    public void destroy() {

    }
}
