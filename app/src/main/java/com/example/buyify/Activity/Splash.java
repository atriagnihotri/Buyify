package com.example.buyify.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.buyify.R;
import com.example.buyify.SharedPreferences.SharedPreference;

public class Splash extends AppCompatActivity {
SharedPreference sharedPreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sharedPreference=new SharedPreference(getApplicationContext());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(sharedPreference.getBoolean("login",false)){
                    Intent intent = new Intent(Splash.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(Splash.this, Login.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, 2000);
    }
}