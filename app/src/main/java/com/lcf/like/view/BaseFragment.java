package com.lcf.like.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lcf.like.R;

/**
 * @Description: BaseFragment
 * @author: lichaofeng
 * @since: V1.0
 * @Date: 2016/6/27 14:21
 */
public abstract class BaseFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    protected SwipeRefreshLayout mSwipeRefreshLay;
    protected RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_base,container,false);
        mSwipeRefreshLay= (SwipeRefreshLayout) rootView.findViewById(R.id.refresher);
        mRecyclerView= (RecyclerView) rootView.findViewById(R.id.rv_list);
        mSwipeRefreshLay.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLay.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return rootView;
    }

}
