package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

public class CustomAdapter extends BaseAdapter {

    Context context;
    String phoneList[];
    int photos[];
    LayoutInflater inflater;

    public CustomAdapter( Context context, String[] phoneList, int[] photos) {

        this.context = context;
        this.phoneList = phoneList;
        this.photos = photos;
        inflater = (LayoutInflater.from(context.getApplicationContext()));

    }

    @Override
    public int getCount() {
        return phoneList.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.list_view_item_layout, null);

        TextView mText = convertView.findViewById(R.id.text_item);
        ImageView mImage = convertView.findViewById(R.id.img_item);

        mText.setText(phoneList[position]);
        mImage.setImageResource(photos[position]);
        mImage.setColorFilter(context.getResources().getColor(R.color.primary));

        return convertView;
    }
}
