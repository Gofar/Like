package com.lcf.like.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lcf.like.R;
import com.lcf.like.adapter.GankItemAdapter;
import com.lcf.like.databinding.FragmentBaseBinding;
import com.lcf.like.managers.ResponseCallback;
import com.lcf.like.model.GankItem;
import com.lcf.like.viewmodel.GankViewModel;

import java.util.List;

/**
 * @author LiChaofeng
 * @description
 * @date 2016/7/25 11:40
 * @since 1.0
 */
public class GankNewFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, ResponseCallback<List<GankItem>> {
    private GankViewModel gankViewModel;
    private FragmentBaseBinding binding;
    private String type;
    private int page = 0;

    public static GankNewFragment newFragment(String type) {
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        GankNewFragment fragment = new GankNewFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initVariables() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getString("type");
        }
    }

    @Override
    protected View initViews(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_base, container, false);
        setRecyclerView();
        gankViewModel = new GankViewModel(getContext(), type, this);
        return binding.getRoot();
    }

    @Override
    protected void loadData() {
        // Need do noting here.
    }

    @Override
    public void onRefresh() {
        loadData(page = 1);
    }

    @Override
    public void onResponse(List<GankItem> items) {
        GankItemAdapter adapter = (GankItemAdapter) binding.rvList.getAdapter();
        if (page == 1) { // refresh
            binding.refresher.setRefreshing(false);
            adapter.setGankItems(items);
        } else { // load more
            adapter.addGankItems(items);
        }
        // Notify refresh footer view
        adapter.setLoading(false);
        adapter.setLoadMore(true);
        adapter.setLoadFailed(false);
        adapter.notifyItemChanged(adapter.getItemCount() - 1);
    }

    @Override
    public void onErrorResponse(Throwable e) {
        GankItemAdapter adapter = (GankItemAdapter) binding.rvList.getAdapter();
        if (page == 1) { // Refresh or first load has error
            binding.refresher.setRefreshing(false);
            adapter.setEmptyEnable(true);
            adapter.notifyDataSetChanged();
        } else {
            page--;// Load more error
            // Notify refresh footer view
            adapter.setLoading(false);
            adapter.setLoadFailed(true);
            adapter.setLoadMore(false);
            adapter.notifyItemChanged(adapter.getItemCount() - 1);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        gankViewModel.destroy();
    }

    /**
     * Init RecyclerView
     */
    public void setRecyclerView() {
        GankItemAdapter adapter = new GankItemAdapter();
        adapter.setLoadingMoreListener(new GankItemAdapter.LoadingMoreListener() {
            @Override
            public void onLoadingMore() {
                loadData(++page);
            }
        });
        binding.rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvList.setAdapter(adapter);
        binding.refresher.setColorSchemeResources(R.color.colorPrimary);
        binding.refresher.setOnRefreshListener(this);
    }

    /**
     * Loading data
     *
     * @param page page number
     */
    private void loadData(int page) {
        gankViewModel.loadGankData(page);
    }
}
