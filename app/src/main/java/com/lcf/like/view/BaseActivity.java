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
public abstract class BaseActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initVariables();
        initViews(savedInstanceState);
        loadData();
    }

    /**
     * This is where can get data from Intent,
     * or init some variables.
     */
    protected abstract void initVariables();

    /**
     * This is where most initialization should go:
     * calling {@link #setContentView(int)} to inflate the
     * activity's UI, using {@link #findViewById} to programmatically interact
     * with widgets in the UI,
     * or using DataBinding view.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in {@link #onSaveInstanceState}.
     *                           <b><i>Note: Otherwise it is null.</i></b>
     */
    protected abstract void initViews(Bundle savedInstanceState);

    /**
     * if you want request network or get other data when the activity {@link #onCreate(Bundle)},
     * you can do something here.
     */
    protected abstract void loadData();
}
