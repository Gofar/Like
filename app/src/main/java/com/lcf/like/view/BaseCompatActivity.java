package com.lcf.like.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @author LiChaofeng
 * @description
 * @copyright © Medlinker
 * @date 2016/8/5 14:49
 * @since 3.4
 */
public abstract class BaseCompatActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initVariables();
        initViews(savedInstanceState);
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
