package com.example.buyify.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.example.buyify.Fragments.Home;
import com.example.buyify.R;

public class PlacedView extends AppCompatActivity {

MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placed_view);

        Uri uri= Uri.parse("https://prodigits.co.uk/content2/mp3-ringtones/tone/2022/alert/zomatoring_z1g51329.mp3");
        mediaPlayer = MediaPlayer.create(this, uri); // 'music' is the name of your audio file in res/raw
        mediaPlayer.setLooping(false);
        mediaPlayer.start();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                mediaPlayer.stop();
                finishAffinity();
            }
        }, 2000);



    }
}