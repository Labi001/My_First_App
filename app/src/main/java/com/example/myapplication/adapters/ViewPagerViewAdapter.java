package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class ViewPagerViewAdapter extends RecyclerView.Adapter<ViewPagerViewAdapter.SilderViewPager> {

    private Context context;
    private final int[] images;

    public ViewPagerViewAdapter(Context context, int[] images) {
        this.context = context;
        this.images = images;
    }

    @NonNull
    @Override
    public ViewPagerViewAdapter.SilderViewPager onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_slider_layout,parent,false);

        return new SilderViewPager(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerViewAdapter.SilderViewPager holder, int position) {
        holder.imagesView.setImageResource(images[position]);

    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class SilderViewPager extends RecyclerView.ViewHolder {
        ImageView imagesView;
        public SilderViewPager(@NonNull View itemView) {
            super(itemView);

            imagesView = itemView.findViewById(R.id.gallery_image);
        }
    }
}
