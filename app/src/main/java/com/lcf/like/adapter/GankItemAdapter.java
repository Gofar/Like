package com.lcf.like.adapter;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.common.util.UriUtil;
import com.lcf.like.BR;
import com.lcf.like.R;
import com.lcf.like.databinding.ItemGankListBinding;
import com.lcf.like.databinding.ViewFooterBinding;
import com.lcf.like.model.GankItem;
import com.lcf.like.viewmodel.FooterViewModel;
import com.lcf.like.viewmodel.GankItemViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Gank Item Adapter
 * @author: lichaofeng
 * @since: 1.0
 * @Date: 2016/6/28 16:35
 */
public class GankItemAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private List<GankItem> gankItems;
    private int[] resIds = {R.mipmap.avatar_dengfeng, R.mipmap.avatar_jialan
            , R.mipmap.avatar_jiaxuan, R.mipmap.avatar_liangyou, R.mipmap.avatar_liangyuan
            , R.mipmap.avatar_qiuning, R.mipmap.avatar_lingjiao};
    private static final int FOOTER_VIEW = 0x1001;
    private static final int EMPTY_VIEW = 0x1002;

    /**
     * Whether is loading now
     */
    private boolean isLoading;
    /**
     * Whether can load more
     */
    private boolean isLoadMore = true;
    /**
     * Whether is load failed
     */
    private boolean isLoadFailed;
    /**
     * Whether can show empty view
     */
    private boolean isEmptyEnable;
    /**
     * When there is no data,show this view
     */
    private View emptyView;

    private LoadingMoreListener loadingMoreListener;

    public GankItemAdapter() {
        gankItems = new ArrayList<>();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder holder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case EMPTY_VIEW:
                if (emptyView == null) {
                    emptyView = inflater.inflate(R.layout.view_empty,parent,false);
                }
                holder = new BaseViewHolder(emptyView);
                break;
            case FOOTER_VIEW:
                ViewFooterBinding footerBinding = ViewFooterBinding.inflate(inflater, parent, false);
                holder = new FooterViewHolder(footerBinding);
                footerBinding.layFooter.setTag(loadingMoreListener);
                break;
            default:
                ItemGankListBinding binding = ItemGankListBinding.inflate(inflater, parent, false);
                holder = new GankItemViewHolder(binding);
                break;
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        int type = holder.getItemViewType();
        switch (type) {
            case FOOTER_VIEW:
                FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
                footerViewHolder.bindFooterModel(isLoadMore, isLoadFailed);
                addLoadMore();
                break;
            case EMPTY_VIEW:
                break;
            default:
                int index = position % resIds.length;
                GankItemViewHolder gankItemViewHolder = (GankItemViewHolder) holder;
                gankItemViewHolder.bindGankItem(gankItems.get(position), resIds[index]);
                break;
        }

    }

    @Override
    public int getItemCount() {
        int count;
        if (gankItems.isEmpty()) {
            count = 1;
        } else {
            count = gankItems.size() + 1;
        }
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        if (gankItems.size() == 0) {
            if (isEmptyEnable) {
                return EMPTY_VIEW;
            } else {
                return FOOTER_VIEW;
            }
        } else if (position == getItemCount() - 1) {
            return FOOTER_VIEW;
        }
        return super.getItemViewType(position);
    }

    public void addGankItem(GankItem item) {
        gankItems.add(item);
        notifyDataSetChanged();
    }

    public void addGankItems(List<GankItem> items) {
        gankItems.addAll(items);
        notifyDataSetChanged();
    }

    public void setGankItems(List<GankItem> items) {
        gankItems.clear();
        gankItems.addAll(items);
        notifyDataSetChanged();

    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public boolean isLoadMore() {
        return isLoadMore;
    }

    public void setLoadMore(boolean loadMore) {
        isLoadMore = loadMore;
    }

    public boolean isLoadFailed() {
        return isLoadFailed;
    }

    public void setLoadFailed(boolean loadFailed) {
        isLoadFailed = loadFailed;
    }

    public boolean isEmptyEnable() {
        return isEmptyEnable;
    }

    public void setEmptyEnable(boolean emptyEnable) {
        isEmptyEnable = emptyEnable;
    }

    public View getEmptyView() {
        return emptyView;
    }

    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
    }

    public LoadingMoreListener getLoadingMoreListener() {
        return loadingMoreListener;
    }

    public void setLoadingMoreListener(LoadingMoreListener loadingMoreListener) {
        this.loadingMoreListener = loadingMoreListener;
    }

    /**
     * Loading more
     */
    private void addLoadMore() {
        if (!isLoading && isLoadMore) {
            isLoading = true;
            if (loadingMoreListener != null) {
                loadingMoreListener.onLoadingMore();
            }
        }
    }

    static class GankItemViewHolder extends BaseViewHolder {
        private ItemGankListBinding binding;

        public GankItemViewHolder(ItemGankListBinding binding) {
            super(binding.cardView);
            this.binding = binding;
        }

        void bindGankItem(GankItem gankItem, int resId) {
            Uri uri = new Uri.Builder()
                    .scheme(UriUtil.LOCAL_RESOURCE_SCHEME)
                    .path(String.valueOf(resId))
                    .build();
            binding.ivAvatar.setImageURI(uri);
            binding.setVariable(BR.gankItem, new GankItemViewModel(itemView.getContext(), gankItem));
            binding.executePendingBindings();
        }
    }

    static class FooterViewHolder extends BaseViewHolder {
        private ViewFooterBinding binding;

        public FooterViewHolder(ViewFooterBinding binding) {
            super(binding.layFooter);
            this.binding = binding;
            binding.pbLoading.getIndeterminateDrawable().setColorFilter(Color.parseColor("#3F51B5"), PorterDuff.Mode.SRC_IN);
        }

        void bindFooterModel(boolean flag1, boolean flag2) {
            binding.setVariable(BR.footerView, new FooterViewModel(itemView.getContext(), flag1, flag2));
            binding.executePendingBindings();
        }
    }

    /**
     * Loading more listener
     */
    public interface LoadingMoreListener {
        /**
         * Loading more
         */
        void onLoadingMore();
    }
}
