package com.rdmchain.gridlayoutimagerecyclerview;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<Images> imagesList;
    private Context context;

    public RecyclerAdapter(List<Images> imagesList, Context context) {
        this.imagesList = imagesList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Images image = imagesList.get(position);
        holder.AlbumTitle.setText("Image " + image.getImageId());
        Glide.with(context).load(image.getImagePath()).into(holder.Album);
    }

    @Override
    public int getItemCount() {
        return imagesList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView Album;
        TextView AlbumTitle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Album = itemView.findViewById(R.id.album_image);
            AlbumTitle = itemView.findViewById(R.id.album_title);
        }
    }

    public void addImages(List<Images> images) {
        for (Images im : images) {
            imagesList.add(im);
        }
        notifyDataSetChanged();
    }

}
