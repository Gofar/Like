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
    private boolean isLoading;
    private boolean isFailed;

    public FooterViewModel(Context context,boolean isLoading,boolean isFailed){
        this.context=context;
        this.isLoading=isLoading;
        this.isFailed=isFailed;
    }

    public boolean isFailed() {
        return isFailed;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void onFooterClick(View view){

    }

    @Override
    public void destroy() {

    }
}
