package edu.mnstate.gv4940sr.vangproject1;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static MediaPlayer mediaPlayer;
    public static MediaPlayer m;
    private final static int MAX_VOLUME = 100;
    public static float currentVolume=(float)(Math.log(MAX_VOLUME-50)/Math.log(MAX_VOLUME));
    final Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer=MediaPlayer.create(this, R.raw.dq3title);
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(currentVolume,currentVolume);
        mediaPlayer.start();

        PlayerData p=new PlayerData();



    }

    public void stopMusic(View view) {
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();}
        else
        {
            mediaPlayer.start();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
    }

    public void gotoMenu(View view) {
        mediaPlayer.pause();
        m=MediaPlayer.create(context,R.raw.dw1clickse);
        m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.release();

            }});
        m.start();
        DialogFragment newGame=new NewGameDialogFragment();
        newGame.show(getSupportFragmentManager(),"newgame");

    }

    public void loadgame(View view) {
        Intent beginGame=new Intent(context,AdventureScreenActivity.class);
        AdventureScreenActivity.loaded=true;
        PlayerData.loadFromFile(this);
        m=MediaPlayer.create(context,R.raw.dw1clickse);
        m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.release();

            }});
        m.start();
        startActivity(beginGame);
    }
    @Override
    public void onBackPressed() {
    }
}
