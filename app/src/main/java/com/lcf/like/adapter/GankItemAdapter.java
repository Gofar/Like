package com.lcf.like.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.facebook.common.util.UriUtil;
import com.lcf.like.BR;
import com.lcf.like.R;
import com.lcf.like.databinding.ItemGankListBinding;
import com.lcf.like.model.GankItem;
import com.lcf.like.viewmodel.GankItemViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Gank Item Adapter
 * @author: lichaofeng
 * @since: 1.0
 * @Date: 2016/6/28 16:35
 */
public class GankItemAdapter extends RecyclerView.Adapter<GankItemAdapter.GankItemViewHolder> {
    private List<GankItem> gankItems;
    private int[] resIds = {R.mipmap.avatar_dengfeng, R.mipmap.avatar_jialan
            , R.mipmap.avatar_jiaxuan, R.mipmap.avatar_liangyou, R.mipmap.avatar_liangyuan
            , R.mipmap.avatar_qiuning, R.mipmap.avatar_lingjiao};

    public GankItemAdapter() {
        gankItems = new ArrayList<>();
    }

    @Override
    public GankItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        ItemGankListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
//                , R.layout.item_gank_list, parent, false);
        ItemGankListBinding binding = ItemGankListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new GankItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(GankItemViewHolder holder, int position) {
        int index=position%resIds.length;
        holder.setAvatar(resIds[index]);
        holder.bindGankItem(gankItems.get(position));
    }

    @Override
    public int getItemCount() {
        return gankItems.size();
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

    static class GankItemViewHolder extends RecyclerView.ViewHolder {
        private ItemGankListBinding binding;

        public GankItemViewHolder(ItemGankListBinding binding) {
            super(binding.cardView);
            this.binding = binding;
        }

        void bindGankItem(GankItem gankItem) {
            binding.setVariable(BR.gankItem, new GankItemViewModel(itemView.getContext(), gankItem));
            binding.executePendingBindings();
        }

        void setAvatar(int resId) {
            Uri uri = new Uri.Builder()
                    .scheme(UriUtil.LOCAL_RESOURCE_SCHEME)
                    .path(String.valueOf(resId))
                    .build();
            binding.ivAvatar.setImageURI(uri);
        }
    }
}
