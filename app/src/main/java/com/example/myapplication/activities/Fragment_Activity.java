package com.example.myapplication.activities;

import android.content.res.Configuration;
import android.os.Bundle;

import com.example.myapplication.fragments.FirstFragment;
import com.example.myapplication.fragments.SecondFragment;
import com.example.myapplication.helper.Constants;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;


import com.example.myapplication.R;

public class Fragment_Activity extends AppCompatActivity implements FirstFragment.MyFragmentItemSelectedListener {


    private Toolbar toolbar;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()== android.R.id.home)
            finish();

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Fragment Activity");
        setSupportActionBar(toolbar);


            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            FirstFragment firstFragment = new FirstFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, firstFragment);
            fragmentTransaction.commit();
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            SecondFragment secondFragment = new SecondFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(Constants.FRAGMENT_SEND_STRING, 0);
            secondFragment.setArguments(bundle);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.fragment_container2, secondFragment);
            fragmentTransaction.commit();
        }



    }

    @Override
    public void onNewsItemSelected(int position) {

        SecondFragment secondFragment = new SecondFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.FRAGMENT_SEND_STRING,position);
        secondFragment.setArguments(bundle);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container2,secondFragment)
                    .commit();
        }else {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,secondFragment)
                    .addToBackStack(null)
                    .commit();

        }


    }
}