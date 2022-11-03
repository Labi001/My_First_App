package com.example.myapplication.activities.databaseAct;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.helper.Constants;
import com.example.myapplication.helper.DBHelper;
import com.google.android.material.textfield.TextInputEditText;

public class EditActivity extends AppCompatActivity {

    private Button update_btn;
    private Button delete_btn;

    private Toolbar toolbar;

    private TextInputEditText TextInputEditText_userID;
    private TextInputEditText TextInputEditText_name;
    private TextInputEditText TextInputEditText_data;
    private TextInputEditText TextInputEditText_address;
    private TextInputEditText TextInputEditText_email;

    private String getUserID = "";
    private DBHelper myDbHelper;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home)
            finish();

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("EditData Activity");
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        Intent editintent = getIntent();
        getUserID = editintent.getStringExtra(Constants.DATABASE_SEND_ID);
        myDbHelper = new DBHelper(this);
        
        loadViews();
        manageData();
        initButtons();
    }

    private void loadViews() {

        update_btn = findViewById(R.id.update_btn);
        delete_btn = findViewById(R.id.delete_btn);

        TextInputEditText_userID = findViewById(R.id.TextInputEditText_userID);
        TextInputEditText_name = findViewById(R.id.TextInputEditText_name);
        TextInputEditText_data = findViewById(R.id.TextInputEditText_data);
        TextInputEditText_address = findViewById(R.id.TextInputEditText_address);
        TextInputEditText_email = findViewById(R.id.TextInputEditText_email);

    }

    private void manageData() {

        Cursor cursor = myDbHelper.searchData(getUserID);
        cursor.moveToFirst();

        if(cursor.getCount()<0){

            Toast.makeText(this, "No data founded", Toast.LENGTH_SHORT).show();

        }else{

            String id = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_NAME));
            String data = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_DATA));
            String address = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_ADDRESS));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_EMAIL));

            TextInputEditText_userID.setText(id);
            TextInputEditText_name.setText(name);
            TextInputEditText_data.setText(data);
            TextInputEditText_address.setText(address);
            TextInputEditText_email.setText(email);

            Toast.makeText(this, "Data Founded", Toast.LENGTH_SHORT).show();


        }

        if (!cursor.isClosed())
            cursor.close();

    }

    private void initButtons() {

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = TextInputEditText_userID.getText().toString();
                String name = TextInputEditText_name.getText().toString();
                String data = TextInputEditText_data.getText().toString();
                String address = TextInputEditText_address.getText().toString();
                String email = TextInputEditText_email.getText().toString();

                AlertDialog.Builder alert = new AlertDialog.Builder(EditActivity.this);
                alert.setTitle("Update");
                alert.setMessage("Are you sure you want to update these data ? ");

                alert.setPositiveButton("PO", new DialogInterface.OnClickListener() {
                    @SuppressLint("SuspiciousIndentation")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      if(myDbHelper.update(id,name,data,address,email))

                          Toast.makeText(EditActivity.this, "Data updated successfully !", Toast.LENGTH_SHORT).show();
                          else
                          Toast.makeText(EditActivity.this, "Error", Toast.LENGTH_SHORT).show();

                          dialog.dismiss();
                          finish();
                    }
                });

                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       dialog.dismiss();
                    }
                });

              alert.show();

            }
        });

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = TextInputEditText_userID.getText().toString();

                AlertDialog.Builder alert1 = new AlertDialog.Builder(EditActivity.this);
                alert1.setTitle("Delete");
                alert1.setMessage("Are you sure you want to delete this data ?");
                alert1.setPositiveButton("PO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(myDbHelper.delete(id))
                            Toast.makeText(EditActivity.this, "Data deletet successfully!", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(EditActivity.this, "Error!", Toast.LENGTH_SHORT).show();

                        dialog.dismiss();

                    }
                });

                alert1.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       dialog.dismiss();
                    }
                });

                alert1.show();


            }
        });


    }
}