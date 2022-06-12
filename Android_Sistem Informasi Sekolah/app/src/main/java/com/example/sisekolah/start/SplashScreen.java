package com.example.sisekolah.start;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sisekolah.R;

public class SplashScreen extends AppCompatActivity {
    private int loadTime = 1500;    //waktu loading 1.5 detik

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);

        //berpindah activity ke tampilan login
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent login = new Intent(SplashScreen.this, LoginPage.class);
                startActivity(login);
                finish();
            }
        },loadTime);
    }
}