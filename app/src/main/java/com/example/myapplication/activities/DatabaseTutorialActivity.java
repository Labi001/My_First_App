package com.example.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.activities.databaseAct.InsertActivity;
import com.example.myapplication.activities.databaseAct.Search_Activity;
import com.example.myapplication.activities.databaseAct.ViewData_Activity;

public class DatabaseTutorialActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button btn_insert,btn_view,btn_search;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home)
            finish();

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_tutorial);

        toolbar =findViewById(R.id.toolbar);
        toolbar.setTitle("Database");
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        btn_insert= findViewById(R.id.btnInsert);
        btn_view= findViewById(R.id.btnView);
        btn_search= findViewById(R.id.btnSearch);

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(DatabaseTutorialActivity.this, InsertActivity.class));
            }
        });

        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(DatabaseTutorialActivity.this, ViewData_Activity.class));
            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(DatabaseTutorialActivity.this,Search_Activity.class));
            }
        });


    }
}