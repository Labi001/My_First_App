package com.example.myapplication.activities.databaseAct;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.helper.Constants;
import com.example.myapplication.helper.DBHelper;

public class Search_Activity extends AppCompatActivity {

    private SearchView search_view;

    private TextView userID_resource;
    private TextView nameID_resource;
    private TextView dataID_resource;
    private TextView addressID_resource;
    private TextView emailID_resource;

    private Button email_Btn;
    private Button address_btn;
    private Button clear_btn;
    private Button edit_btn;
    private String currentSearchedQuery = "";

    private Toolbar toolbar;

    private DBHelper dbHelper;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home)
            finish();

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(!currentSearchedQuery.equals(""))
            Search_Query(currentSearchedQuery);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Search Activity");
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        dbHelper =new DBHelper(this);

        LoadViews();
        searchViewInit();
        buttonInit();
    }



    private void searchViewInit() {

        search_view.setSubmitButtonEnabled(true);

        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                currentSearchedQuery = query;
                Search_Query(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    private void Search_Query(String text) {

        Cursor cursor = dbHelper.searchData(text);
        cursor.moveToFirst();

        if(cursor.getCount() < 1){

            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
            clearData();
        }else{
            Toast.makeText(this, "Data Founded", Toast.LENGTH_SHORT).show();
            showData(cursor);
        }

        if(!cursor.isClosed())
            cursor.close();

    }

    private void buttonInit() {

        email_Btn.setVisibility(View.GONE);
        address_btn.setVisibility(View.GONE);
        clear_btn.setVisibility(View.GONE);
        edit_btn.setVisibility(View.GONE);

        email_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse("mailto:" + emailID_resource.getText().toString());
                Intent emailintent = new Intent(Intent.ACTION_SENDTO,uri);
                startActivity(Intent.createChooser(emailintent,""));

            }
        });

       address_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Uri uri = Uri.parse("geo:0,0?q=" + addressID_resource.getText().toString());
               Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri);
               mapIntent.setPackage("com.google.android.apps.maps");
               startActivity(Intent.createChooser(mapIntent, ""));
           }
       });

       clear_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                search_view.setQuery("",false);
                search_view.clearFocus();
               clearData();
           }
       });

       edit_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               String resID = userID_resource.getText().toString();

               if(resID.equals("")){

                   Toast.makeText(Search_Activity.this, "No data to edit", Toast.LENGTH_SHORT).show();
               }else{

                   Intent intent = new Intent(Search_Activity.this,EditActivity.class);
                   intent.putExtra(Constants.DATABASE_SEND_ID,resID);
                   startActivity(intent);

               }

           }
       });

    }

    private void clearData() {

        userID_resource.setText("");
        nameID_resource.setText("");
        dataID_resource.setText("");
        addressID_resource.setText("");
        emailID_resource.setText("");

        email_Btn.setVisibility(View.GONE);
        address_btn.setVisibility(View.GONE);
        clear_btn.setVisibility(View.GONE);
        edit_btn.setVisibility(View.GONE);
    }

    private void showData(Cursor cursor) {

        String id = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_ID));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_NAME));
        String data = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_DATA));
        String address = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_ADDRESS));
        String email = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_EMAIL));


        userID_resource.setText(id);
        nameID_resource.setText(name);
        dataID_resource.setText(data);
        addressID_resource.setText(address);
        emailID_resource.setText(email);

        email_Btn.setVisibility(View.VISIBLE);
        address_btn.setVisibility(View.VISIBLE);
        clear_btn.setVisibility(View.VISIBLE);
        edit_btn.setVisibility(View.VISIBLE);
    }
    
    private void LoadViews() {

        search_view = findViewById(R.id.search_view);

        userID_resource = findViewById(R.id.userID_resource);
        nameID_resource = findViewById(R.id.nameID_resource);
        dataID_resource = findViewById(R.id.dataID_resource);
        addressID_resource = findViewById(R.id.addressID_resource);
        emailID_resource = findViewById(R.id.emailID_resource);

        email_Btn = findViewById(R.id.email_Btn);
        address_btn = findViewById(R.id.address_btn);
        clear_btn = findViewById(R.id.clear_btn);
        edit_btn = findViewById(R.id.edit_btn);

    }
}