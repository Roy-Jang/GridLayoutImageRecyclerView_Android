package com.rdmchain.gallery.Adapter;


import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rdmchain.gallery.Data.Items;
import com.rdmchain.gallery.R;

import java.util.List;

public class AdapterItems extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Items> itemsList;
    private Context context;
    private OnLoadMoreListener onLoadMoreListener;
    private ProgressBar progressBar;
    private boolean isMoreLoading = true;

    public interface OnLoadMoreListener{
        void onLoadMore();
    }

    public AdapterItems(OnLoadMoreListener onLoadMoreListener, List<Items> itemsList, Context context, ProgressBar progressBar) {
        this.onLoadMoreListener=onLoadMoreListener;
        this.itemsList = itemsList;
        this.context = context;
        this.progressBar = progressBar;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.items_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemsViewHolder) {
            final Items singleItem = (Items) itemsList.get(position);
            ((ItemsViewHolder) holder).item_title.setText(singleItem.getItemName());
            Glide.with(context).load(singleItem.getItemImg1()).into(((ItemsViewHolder) holder).item_image);
        }
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public void showLoading() {
        if (isMoreLoading && itemsList != null && onLoadMoreListener != null) {
            isMoreLoading = false;
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    progressBar.setVisibility(View.VISIBLE);
                    onLoadMoreListener.onLoadMore();
                }
            });
        }
    }

    public void setMore(boolean isMore) {
        this.isMoreLoading = isMore;
    }

    public void dismissLoading() {
        if (itemsList != null && itemsList.size() > 0) {
            progressBar.setVisibility(View.GONE);
        }
    }

    static class ItemsViewHolder extends RecyclerView.ViewHolder {
        public ImageView item_image;
        public TextView item_title;

        public ItemsViewHolder(View v) {
            super(v);
            item_image = (ImageView) v.findViewById(R.id.item_image);
            item_title = (TextView) v.findViewById(R.id.item_title);
        }
    }

    public void addItems(List<Items> images) {
        for (Items im : images) {
            itemsList.add(im);
        }
        notifyDataSetChanged();
    }

}
