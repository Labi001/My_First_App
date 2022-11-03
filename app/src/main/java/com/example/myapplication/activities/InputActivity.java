package com.example.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Locale;

public class InputActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextInputEditText name_txt;
    private TextInputEditText password_txt;
    private ConstraintLayout main_layout;
    private Button button;

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
        setContentView(R.layout.activity_input);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Input Layout");
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){

            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }


        name_txt = findViewById(R.id.Name_etxt);
        password_txt = findViewById(R.id.Password_etxt);
        button = findViewById(R.id.button);
        main_layout = findViewById(R.id.parent_layout);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = name_txt.getText().toString();
                String password = password_txt.getText().toString();

                String message = getString(R.string.snackbar_message,name.toUpperCase(Locale.ROOT),password);

                Snackbar snackbar = Snackbar.make(main_layout,message,Snackbar.LENGTH_LONG);
                snackbar.setBackgroundTint(getResources().getColor(R.color.primary_dark));
                snackbar.setTextColor(Color.WHITE);
                snackbar.setActionTextColor(Color.WHITE);
                snackbar.setAction("Toast It", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(InputActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                });

                snackbar.show();

            }
        });

    }
}