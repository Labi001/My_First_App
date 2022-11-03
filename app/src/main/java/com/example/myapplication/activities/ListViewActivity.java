package com.example.myapplication.activities;

import static com.example.myapplication.helper.Constants.mobilearray;
import static com.example.myapplication.helper.Constants.photoArray;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.CustomAdapter;

public class ListViewActivity extends AppCompatActivity {


    private ListView mList;
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
        setContentView(R.layout.activity_list_view);

        CustomAdapter customAdapter = new CustomAdapter( this,mobilearray,photoArray);

        mList = findViewById(R.id.myList);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("ListView");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mList.setAdapter(customAdapter);
    }

}