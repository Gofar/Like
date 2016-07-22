package com.lcf.like.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

/**
 * @author LiChaofeng
 * @description Base Activity
 * @date 2016/7/21 22:04
 * @since 1.0
 */
public abstract class BaseActivity extends FragmentActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initVariables();
        initViews(savedInstanceState);
        loadData();
    }

    protected abstract void initVariables();
    protected abstract void initViews(Bundle savedInstanceState);
    protected abstract void loadData();
}
