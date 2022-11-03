package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.SplashScreenTheme);
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(SplashScreen.this,MainActivity.class);
        startActivity(intent);
        finish();

        //  setContentView(R.layout.activity_splash_screen);

//        main_layout = findViewById(R.id.main_layout);
//        icon_image = findViewById(R.id.icon_image);
//        title_textView = findViewById(R.id.title_textView);
//
//
//        main_layout.setBackgroundColor(getResources().getColor(R.color.primary));
//        icon_image.setColorFilter(Color.WHITE);
//        title_textView.setTextColor(Color.WHITE);
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        }, SPLASH_TIME_OUT);

    }
}