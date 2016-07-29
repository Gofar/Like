package com.lcf.like.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author LiChaofeng
 * @description Base fragment
 * @date 2016/7/22 11:08
 * @since 1.0
 */
public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initVariables();
        View view = initViews(inflater, container, savedInstanceState);
        loadData();
        return view;
    }

    /**
     * This is where can get data from Intent,
     * or init some variables.
     */
    protected abstract void initVariables();

    /**
     * This is where most initialization should go:
     * inflate the fragment's UI,
     * or using DataBinding view.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.  The fragment should not add the view itself,
     *                           but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    protected abstract View initViews(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    /**
     * if you want request network or get other data when the frament
     * {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)},
     * you can do something here.
     */
    protected abstract void loadData();
}
