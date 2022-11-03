package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.RecycleViewModel;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private final List<RecycleViewModel> mData;
    private int setIconColor;
    private ItemClickListener mClickListener;

    public RecyclerViewAdapter(Context context, List<RecycleViewModel> mData) {
        this.context = context;
        this.mData = mData;
    }

    public void setSetIconColor(int setIconColor) {
        this.setIconColor = setIconColor;
    }

    public void setmClickListener(ItemClickListener mClickListener) {
        this.mClickListener = mClickListener;
    }

    public interface ItemClickListener{
        void onItemClick(View view,int position);
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_for_recyclerview,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {

        RecycleViewModel myModel = mData.get(position);

        holder.imageView.setImageResource(myModel.getPhotoId());
        holder.myTitle_txt.setText(myModel.getmTitle());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imageView;
        private TextView myTitle_txt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            myTitle_txt = itemView.findViewById(R.id.myTitle_txt);

            imageView.setColorFilter(setIconColor);

            itemView.setOnClickListener( this);
        }

        @Override
        public void onClick(View v) {

            if(mClickListener !=null)
                mClickListener.onItemClick(v,getAdapterPosition());

        }
    }


}
