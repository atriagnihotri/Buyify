package com.example.buyify.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buyify.Fragments.Cart;
import com.example.buyify.Fragments.Category;
import com.example.buyify.Fragments.Home;
import com.example.buyify.Fragments.Profile;
import com.example.buyify.R;
import com.example.buyify.SharedPreferences.SharedPreference;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
SharedPreference sharedPreference;
TextView Name,Greet;
BottomNavigationView bottomNavigationView;
FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreference=new SharedPreference(getApplicationContext());
        Name=findViewById(R.id.Name);
        Greet=findViewById(R.id.greet);
        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        frameLayout=findViewById(R.id.frame);
        Name.setText(sharedPreference.getString("name","None"));
        TimeGreet();


        bottomNavigationView
                .setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.Home);

    }
    Home home=new Home();
    Category category=new Category();
    Cart cart=new Cart();
    Profile profile=new Profile();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
       if (item.getItemId()==R.id.Home){
           getSupportFragmentManager()
                   .beginTransaction()
                   .replace(R.id.frame, home)
                   .commit();
           return true;
       }
        if (item.getItemId()==R.id.Category){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame, category)
                    .commit();
            return true;
        }
        if (item.getItemId()==R.id.Cart){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame, cart)
                    .commit();
            return true;
        }
        if (item.getItemId()==R.id.Profile){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame, profile)
                    .commit();
            return true;
        }


        return false;
    }
    public void TimeGreet(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalTime currentTime = LocalTime.now();
            int time= currentTime.getHour();
            if (time>5 && time<12){
                Greet.setText("Good Morning");
            }
            else if (time>12 && time<18){
                Greet.setText("Good AfterNoon");
            }
            else{
                Greet.setText("Good Evening");
            }
        }

    }
}