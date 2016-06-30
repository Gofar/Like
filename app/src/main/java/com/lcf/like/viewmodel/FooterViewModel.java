package com.lcf.like.viewmodel;

import android.databinding.BaseObservable;
import android.view.View;

/**
 * @author LiChaofeng
 * @description
 * @date 2016/6/30 19:20
 * @since 1.0
 */
public class FooterViewModel extends BaseObservable implements ViewModel {

    public int isShowLoading(boolean flag) {
        return flag ? View.VISIBLE : View.GONE;
    }

    public int isLoading(boolean flag) {
        return flag ? View.VISIBLE : View.GONE;
    }

    public int isLoadingFailed(boolean flag) {
        return flag ? View.VISIBLE : View.GONE;
    }

    @Override
    public void destroy() {

    }
}
