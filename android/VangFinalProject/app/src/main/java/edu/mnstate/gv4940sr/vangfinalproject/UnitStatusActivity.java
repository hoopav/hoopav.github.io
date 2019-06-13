package edu.mnstate.gv4940sr.vangfinalproject;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class UnitStatusActivity extends AppCompatActivity {
    public MediaPlayer unitDisplayMediaPlayer;
    private ImageView unit_status_image;
    private TextView unit_status_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_status);
        Bundle b=getIntent().getExtras();
        int imageID=b.getInt("IMAGE");
        String name=b.getString("NAME");
        unit_status_image=(ImageView)findViewById(R.id.unit_status_image);
        unit_status_name=(TextView)findViewById(R.id.unit_status_name);
        unit_status_image.setImageResource(imageID);
        unit_status_name.setText(name);

        unitDisplayMediaPlayer=MediaPlayer.create(this,R.raw.unitdisplay);
        unitDisplayMediaPlayer.setLooping(true);


    }


    @Override
    protected void onPause()
    {
        super.onPause();
        unitDisplayMediaPlayer.pause();
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        unitDisplayMediaPlayer.seekTo(0);
        if(!HomeScreen.pref.getBoolean("mute",false))//if not mute, play
            unitDisplayMediaPlayer.start();

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if(!HomeScreen.pref.getBoolean("mute",false))//if not mute, play
            unitDisplayMediaPlayer.start();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        unitDisplayMediaPlayer.release();
    }
}
