package com.example.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.myapplication.R;
import com.example.myapplication.adapters.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ViewPagerAcitivty extends AppCompatActivity {

    private String[] mtitle = new String[] {"Home","Local","Top"};
    private int[] icon = new int[]{R.drawable.ic_home,R.drawable.ic_local,R.drawable.ic_top_icon};
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager2 view_pager;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home)
            finish();

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_acitivty);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("View Pager");
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= 21) {
            StateListAnimator stateListAnimator = new StateListAnimator();
            stateListAnimator.addState(new int[0], ObjectAnimator.ofFloat(toolbar, "elevation", 0));
            findViewById(R.id.app_Bar).setStateListAnimator(stateListAnimator);
        }

        if(getSupportActionBar() != null){

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }


        tabLayout = findViewById(R.id.tabLayout);
        view_pager = findViewById(R.id.view_pager);

        tabLayout.setBackgroundColor(getResources().getColor(R.color.primary));
        tabLayout.setTabRippleColor(ColorStateList.valueOf(Color.WHITE));
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);

        tabLayout.setTabTextColors(getResources().getColor(R.color.primary_dark), Color.WHITE);

        ColorStateList myColorStateList = new ColorStateList(

                new int[][]{
                        new int[]{android.R.attr.state_selected},
                        new int[]{android.R.attr.state_enabled},
                },

                new int[]{
                        Color.WHITE,
                        getResources().getColor(R.color.primary_dark)
                }

        );

        tabLayout.setTabIconTint(myColorStateList);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this,mtitle);
        view_pager.setAdapter(viewPagerAdapter);

        new TabLayoutMediator(tabLayout, view_pager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                tab.setText(mtitle[position]);
                tab.setIcon(icon[position]);

            }
        }).attach();

    }
}