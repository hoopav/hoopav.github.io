package edu.mnstate.gv4940sr.vangfinalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TitleScreenActivity extends AppCompatActivity {
    public static MediaPlayer titleMediaPlayer;
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_screen);
        titleMediaPlayer=MediaPlayer.create(this,R.raw.title);
        titleMediaPlayer.setLooping(true);
        pref=getApplicationContext().getSharedPreferences("MyPrefs",0);
        if(!pref.getBoolean("mute",false))//if not mute, play
            titleMediaPlayer.start();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        titleMediaPlayer.pause();
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        titleMediaPlayer.seekTo(0);
        if(!pref.getBoolean("mute",false))//if not mute, play
        titleMediaPlayer.start();

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if(!pref.getBoolean("mute",false))//if not mute, play
        titleMediaPlayer.start();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        titleMediaPlayer.release();
    }

    public void launchHomeScreen(View view) {
        Intent homescreen= new Intent(this,HomeScreen.class);
        startActivity(homescreen);
    }
}
