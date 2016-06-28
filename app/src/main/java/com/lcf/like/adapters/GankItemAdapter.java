package com.lcf.like.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.lcf.like.databinding.ItemGankListBinding;
import com.lcf.like.model.GankItem;
import com.lcf.like.viewmodel.GankItemViewModel;

import java.util.Collections;
import java.util.List;

/**
 * @Description: Gank Item Adapter
 * @author: lichaofeng
 * @since: 1.0
 * @Date: 2016/6/28 16:35
 */
public class GankItemAdapter extends RecyclerView.Adapter<GankItemAdapter.GankItemViewHolder> {
    private List<GankItem> gankItems;

    public GankItemAdapter() {
        gankItems = Collections.emptyList();
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

    public void setGankItems(List<GankItem> gankItems) {
        gankItems.clear();
        this.gankItems.addAll(gankItems);
        notifyDataSetChanged();
    }

    static class GankItemViewHolder extends RecyclerView.ViewHolder {
        private ItemGankListBinding binding;

        public GankItemViewHolder(ItemGankListBinding binding) {
            super(binding.cardView);
            this.binding = binding;
        }

        void bindGankItem(GankItem gankItem) {
            binding.setGankItem(new GankItemViewModel(itemView.getContext(), gankItem));
        }
    }
}
