package com.lcf.like.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
 * @Description: Gank Fragment
 * @author: lichaofeng
 * @since: 1.0
 * @Date: 2016/6/27 14:12
 */
public class GankFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, ResponseCallback<List<GankItem>> {

    private GankViewModel gankViewModel;
    private FragmentBaseBinding binding;
    private String type;
    private int page=1;

    public static GankFragment newFragment(String type) {
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        GankFragment fragment = new GankFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getString("type");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_base, container, false);
        setRecyclerView();
        gankViewModel = new GankViewModel(getContext(), type, this);
        loadData(page);
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        gankViewModel.destroy();
    }

    @Override
    public void onRefresh() {
        loadData(page = 1);
    }

    @Override
    public void onResponse(List<GankItem> gankItems) {
        GankItemAdapter adapter = (GankItemAdapter) binding.rvList.getAdapter();
        if (page == 1) { // refresh
            binding.refresher.setRefreshing(false);
            adapter.setGankItems(gankItems);
        } else { // load more
            adapter.addGankItems(gankItems);
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
        if (page == 1) {
            if (!binding.refresher.isRefreshing()) {
                binding.refresher.setRefreshing(true);
            }
        }
        gankViewModel.loadGankData(page);
    }
}
