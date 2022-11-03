package com.example.myapplication.activities;

import static com.example.myapplication.helper.Constants.mobilearray;
import static com.example.myapplication.helper.Constants.photoArray;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.adapters.RecyclerViewAdapter;
import com.example.myapplication.models.RecycleViewModel;

import java.util.ArrayList;
import java.util.List;

public class RecyclerView_Acticity extends AppCompatActivity implements RecyclerViewAdapter.ItemClickListener {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()== android.R.id.home)
            finish();

        return true;
    }


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_acticity);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("RecyclerView");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerView);

        ArrayList<RecycleViewModel> phoneList = new ArrayList<>();

        for(int i=0; i <photoArray.length;i++){

            RecycleViewModel myModel = new RecycleViewModel(photoArray[i], mobilearray[i] + "-" + i);
            phoneList.add(myModel);

        }

        recyclerViewAdapter = new RecyclerViewAdapter(this,phoneList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter.setmClickListener(this);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}