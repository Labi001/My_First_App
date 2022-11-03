package com.example.myapplication.activities.databaseAct;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.helper.DBHelper;
import com.google.android.material.textfield.TextInputEditText;

public class InsertActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private Button save_btn;
    private Button clear_btn;

    private TextInputEditText TextInputEditText_userID;
    private TextInputEditText TextInputEditText_name;
    private TextInputEditText TextInputEditText_data;
    private TextInputEditText TextInputEditText_address;
    private TextInputEditText TextInputEditText_email;
    private DBHelper dbHelper;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home)
            finish();

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Insert Activity");
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

       LoadViews();
        initData();

    }


    private void LoadViews() {

        save_btn = findViewById(R.id.save_btn);
        clear_btn = findViewById(R.id.clear_btn);

        save_btn = findViewById(R.id.save_btn);
        clear_btn = findViewById(R.id.clear_btn);

        TextInputEditText_userID = findViewById(R.id.TextInputEditText_userID);
        TextInputEditText_name = findViewById(R.id.TextInputEditText_name);
        TextInputEditText_data = findViewById(R.id.TextInputEditText_data);
        TextInputEditText_address = findViewById(R.id.TextInputEditText_address);
        TextInputEditText_email = findViewById(R.id.TextInputEditText_email);

    }

    private void initData() {

        dbHelper = new DBHelper(InsertActivity.this);
        clear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearInputs();
            }
        });

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String id = TextInputEditText_userID.getText().toString();
                final String name = TextInputEditText_name.getText().toString();
                final String data = TextInputEditText_data.getText().toString();
                final String address = TextInputEditText_address.getText().toString();
                final String email = TextInputEditText_email.getText().toString();

                AlertDialog.Builder dialog =new AlertDialog.Builder(InsertActivity.this);
                dialog.setTitle("Title");
                dialog.setMessage("Deshiron ti ruash te dhenat e tua");
                dialog.setPositiveButton("PO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if(!checkIsEmpty()){

                            if(dbHelper.insertData(id,name,data,address,email)){

                                Toast.makeText(InsertActivity.this, "Data saved!", Toast.LENGTH_SHORT).show();
                                  clearInputs();
                            }else{

                                Toast.makeText(InsertActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }


                        }else{

                            Toast.makeText(InsertActivity.this, "Error", Toast.LENGTH_SHORT).show();

                        }

                    }
                });

                dialog.setNegativeButton("JO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                dialog.show();



            }
            public boolean checkIsEmpty() {

                boolean rezultati = false;

                if (TextUtils.isEmpty(TextInputEditText_userID.getText())) {
                    TextInputEditText_userID.setError("User ID cannot be empty");
                    rezultati = true;
                }

                if (TextUtils.isEmpty(TextInputEditText_name.getText())) {
                    TextInputEditText_name.setError("Name cannot be empty");
                    rezultati = true;
                }

                if (TextUtils.isEmpty(TextInputEditText_data.getText())) {
                    TextInputEditText_data.setError("Data cannot be empty");
                    rezultati = true;
                }

                if (TextUtils.isEmpty(TextInputEditText_address.getText())) {
                    TextInputEditText_address.setError("Address cannot be empty");
                    rezultati = true;
                }

                if (TextUtils.isEmpty(TextInputEditText_email.getText())) {
                    TextInputEditText_email.setError("Email cannot be empty");
                    rezultati = true;
                }
                return rezultati;
            }
        });



    }

    private void clearInputs() {
        TextInputEditText_userID.setText("");
        TextInputEditText_name.setText("");
        TextInputEditText_data.setText("");
        TextInputEditText_address.setText("");
        TextInputEditText_email.setText("");
    }
}