package com.example.hoangtruc.thread_async_task_handler;

import android.graphics.Bitmap;
import android.graphics.BitmapRegionDecoder;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private List<Bitmap> mListImage;
    private static final int ZERO=0;
    public ImageAdapter(List<Bitmap> images){
        this.mListImage=images;
    }
    @NonNull
    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ViewHolder holder, int position) {
          holder.mImage.setImageBitmap(mListImage.get(position));
    }

    @Override
    public int getItemCount() {
        return mListImage !=null ? mListImage.size() :ZERO ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImage;

        public ViewHolder(View itemView) {
            super(itemView);
            mImage=itemView.findViewById(R.id.image_hero);
        }

    }
}
