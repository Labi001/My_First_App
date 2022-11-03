package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.adapters.ViewPagerViewAdapter;

public class ViewPagerGallery_Activity extends AppCompatActivity {

    private int Images[] =new int[] {R.drawable.ic_breakfast,R.drawable.ic_sandwich,R.drawable.ic_spaghetti};
    private ViewPager2 viewPager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_gallery);

        viewPager = findViewById(R.id.viewpager_id);
        ViewPagerViewAdapter viewPagerViewAdapter = new ViewPagerViewAdapter(this,Images);
        viewPager.setAdapter(viewPagerViewAdapter);



    }
}