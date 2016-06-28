package com.lcf.like.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.lcf.like.R;
import com.lcf.like.adapters.GankItemAdapter;
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
        Log.d("lcf","onRefresh");
        loadData(page = 0);
    }

    @Override
    public void onResponse(List<GankItem> gankItems) {
        Log.d("lcf","onResponse");
        GankItemAdapter adapter = (GankItemAdapter) binding.rvList.getAdapter();
        if (page == 0) { // refresh
            binding.refresher.setRefreshing(false);
            Log.d("lcf",new Gson().toJson(gankItems));
            adapter.setGankItems(gankItems);
            Log.d("lcf","first");
        } else { // load more
            adapter.addGankItems(gankItems);
            Log.d("lcf","more");
        }
        isLoading = false;
    }

    @Override
    public void onErrorResponse(Throwable e) {
        Log.d("lcf","onErrorResponse");
        if (page == 0) {
            binding.refresher.setRefreshing(false);
        }
        isLoading = false;
    }

    /**
     * Set RecyclerView Adapter
     */
    public void setRecyclerView() {
        GankItemAdapter adapter = new GankItemAdapter();
        binding.rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvList.setAdapter(adapter);
//        binding.rvList.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
//                int lastVisibleItem = manager.findLastVisibleItemPosition();
//                int totalItemCount = manager.getItemCount();
//                //lastVisibleItem >= totalItemCount - 4 表示剩下4个item自动加载，各位自由选择
//                // dy>0 表示向下滑动
//                if (lastVisibleItem >= totalItemCount - 4 && dy > 0) {
//                    if (isLoading) {
//                        Log.d("lcf", "ignore manually update!");
//                    } else {
//                        loadData(page++);
//                    }
//                }
//            }
//        });
    }

    /**
     * Loading data
     *
     * @param page page
     */
    private void loadData(int page) {
        Log.d("lcf","loading:"+isLoading);
        if (isLoading) {
            return;
        }
        if (page == 0) {
            if (!binding.refresher.isRefreshing()) {
                binding.refresher.setRefreshing(true);
            }
            Log.d("lcf","refresh");

        }
        Log.d("lcf","loading");
        gankViewModel.loadGankData(page);
        isLoading = true;
    }
}
