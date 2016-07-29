package com.lcf.like.viewmodel;

import android.content.Context;

/**
 * @author LiChaofeng
 * @description
 * @date 2016/7/25 14:31
 * @since 1.0
 */
public class MyBaseListObservable extends MyBaseObservable implements ViewModel {
    private static final int DEFAULT_LIMIT = 15;
    private int page;
    private Context context;

    protected void first() {
        page = 0;
        loadData();
    }

    protected void next() {
        loadData();
    }

    public void loadData() {
        loadData(DEFAULT_LIMIT);
    }

    public void loadData(int limit) {
        unsubscribe();
    }

    @Override
    public void destroy() {
        unsubscribe();
        subscription = null;
        context = null;
    }
}
