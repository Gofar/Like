package com.lcf.like.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private int page;
    private boolean isLoading;

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
        loadData(page = 0);
    }

    @Override
    public void onResponse(List<GankItem> gankItems) {
        GankItemAdapter adapter = (GankItemAdapter) binding.rvList.getAdapter();
        if (page == 0) { // refresh
            binding.refresher.setRefreshing(false);
            adapter.setGankItems(gankItems);
        } else { // load more
            adapter.addGankItems(gankItems);
        }
        isLoading = false;
    }

    @Override
    public void onErrorResponse(Throwable e) {
        if (page == 0) { // Refresh error
            binding.refresher.setRefreshing(false);
        }else {
            page--;// Load more error
        }
        isLoading = false;
    }

    /**
     * Init RecyclerView
     */
    public void setRecyclerView() {
        GankItemAdapter adapter = new GankItemAdapter();
        binding.rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvList.setAdapter(adapter);
        binding.refresher.setColorSchemeResources(R.color.colorPrimary);
        binding.refresher.setOnRefreshListener(this);
        binding.rvList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItem = manager.findLastVisibleItemPosition();
                int totalItemCount = manager.getItemCount();
                //lastVisibleItem >= totalItemCount - 4 表示剩下4个item自动加载，各位自由选择
                // dy>0 表示向下滑动
                if (lastVisibleItem >= totalItemCount - 1 && dy > 0) {
                    if (isLoading) {
                        Log.d("Hello", "ignore manually update!");
                    } else {
                        loadData(++page);
                    }
                }
            }
        });
    }

    /**
     * Loading data
     *
     * @param page page number
     */
    private void loadData(int page) {
        if (isLoading) {
            return;
        }
        if (page == 0) {
            if (!binding.refresher.isRefreshing()) {
                binding.refresher.setRefreshing(true);
            }
        }
        gankViewModel.loadGankData(page);
        isLoading = true;
    }
}
