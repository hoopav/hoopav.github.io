package edu.mnstate.gv4940sr.vangfinalproject;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GameModeActivity extends AppCompatActivity {
    public MediaPlayer gameModeMediaPlayer;


    @Override
    protected void onPause()
    {
        super.onPause();
        gameModeMediaPlayer.pause();
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        gameModeMediaPlayer.seekTo(0);
        if(!HomeScreen.pref.getBoolean("mute",false))//if not mute, play
            gameModeMediaPlayer.start();

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if(!HomeScreen.pref.getBoolean("mute",false))//if not mute, play
            gameModeMediaPlayer.start();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        gameModeMediaPlayer.release();
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode);
        gameModeMediaPlayer=MediaPlayer.create(this,R.raw.story);
        gameModeMediaPlayer.setLooping(true);

        if(!HomeScreen.pref.getBoolean("mute",false))//if not mute, play
            gameModeMediaPlayer.start();
    }
}
