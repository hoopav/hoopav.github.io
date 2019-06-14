package edu.mnstate.gv4940sr.vangproject1;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import static edu.mnstate.gv4940sr.vangproject1.MainActivity.currentVolume;
import static edu.mnstate.gv4940sr.vangproject1.MainActivity.m;
import static edu.mnstate.gv4940sr.vangproject1.MainActivity.mediaPlayer;

public class Battle extends AppCompatActivity {
    Monster slime;
    ImageView img,enemyImg;
   // PlayerData player;
    ProgressBar enemyHP,selfHP;
  //  Bundle b;

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        img=(ImageView)findViewById(R.id.backdropImageView);
        enemyImg=(ImageView)findViewById(R.id.enemyImageView);
        PlayerData.vocation.setContext(this);

        int baseHP=100;
        int baseMP=0;
        int baseAtk=15;
        int baseDef=5;
        double multiplier=(PlayerData.self.getLevel())*.75;

        slime=new Monster(getString(R.string.slimeName), getString(R.string.defaultAbility), getString(R.string.defaultMonsterImageURL), ""+(int)(baseHP*multiplier), ""+(int)(baseMP*multiplier), ""+(int)(baseAtk*multiplier), ""+(int)(baseDef*multiplier));
        Picasso.Builder builder = new Picasso.Builder(this);
        builder.listener(new Picasso.Listener()
        {

            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception)
            {
                exception.printStackTrace();
            }
        });

        builder.build().load(R.drawable.battlebackdrop).into(img);

        builder.build().load(slime.getImageurl()).into(enemyImg);

        mediaPlayer = MediaPlayer.create(this, R.raw.dq3battle);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.release();

            }});
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(currentVolume,currentVolume);
        mediaPlayer.start();

     //   Toast.makeText(this,player.getVocation().toString(),Toast.LENGTH_LONG).show();

        enemyHP=(ProgressBar) findViewById(R.id.enemyhpBar);
        selfHP=(ProgressBar)findViewById(R.id.hpBar);

        enemyHP.setMax(Integer.parseInt(slime.hp));
        enemyHP.setProgress(Integer.parseInt(slime.hp));

        selfHP.setMax(Integer.parseInt(PlayerData.vocation.hp));
        selfHP.setProgress(Integer.parseInt(PlayerData.vocation.hp));
        TextView hpbar,enemyhpbar;
        hpbar=(TextView)findViewById(R.id.hpBarText);
        enemyhpbar=(TextView)findViewById(R.id.enemyhpBarText);
        hpbar.setText("HP: "+PlayerData.vocation.hp);
        enemyhpbar.setText("HP: "+slime.hp);
/**/
slime.setContext(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer.start();
    }

    public void fight(View view)
    {  TextView hpbar,enemyhpbar;
        hpbar=(TextView)findViewById(R.id.hpBarText);
        enemyhpbar=(TextView)findViewById(R.id.enemyhpBarText);
        RadioGroup buttons=(RadioGroup) findViewById(R.id.actionGroup);
        int selectedId=buttons.getCheckedRadioButtonId();
        RadioButton selected=(RadioButton)findViewById(selectedId);

        switch(selected.getText().toString().toLowerCase())
        {
            case "attack":
                PlayerData.vocation.attack(slime);
                enemyHP.setProgress(Integer.parseInt(slime.hp));
                enemyhpbar.setText(getString(R.string.HP)+slime.hp);

                break;
            case "ability":
                PlayerData.vocation.ability(PlayerData.vocation,slime);

                enemyHP.setProgress(Integer.parseInt(slime.hp));
                enemyhpbar.setText(getString(R.string.HP)+slime.hp);

                break;
            case "defend":
                PlayerData.vocation.defend();

                break;

        }

        if(Integer.parseInt(slime.hp)<=0)
            victory();
        else if(Integer.parseInt(PlayerData.vocation.hp)<=0)
            defeat();







        if(Integer.parseInt(slime.hp)>0){
            slime.attack(PlayerData.vocation);
            selfHP.setProgress(Integer.parseInt(PlayerData.vocation.hp));
            hpbar.setText("HP: "+PlayerData.vocation.hp);
            m=MediaPlayer.create(this, R.raw.dw1dmg);
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    mp.release();

                }});
            m.start();

        }





    }

    private void defeat()
    {
        Toast.makeText(this, R.string.toastYouLose,Toast.LENGTH_LONG).show();
        MediaPlayer temp=MediaPlayer.create(this, R.raw.dq3lose);

        temp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.release();

            };
        });
        mediaPlayer.pause();
        temp.start();
        this.finishAffinity();
    }



    private void victory()
    {
        TextView hpbar,enemyhpbar;
        hpbar=(TextView)findViewById(R.id.hpBarText);
        enemyhpbar=(TextView)findViewById(R.id.enemyhpBarText);
        enemyHP.setProgress(Integer.parseInt(slime.hp));
        enemyhpbar.setText(getString(R.string.HP)+slime.hp);
        hpbar.setText("HP: "+PlayerData.vocation.hp);
        Toast.makeText(this, R.string.toastYouWin,Toast.LENGTH_LONG).show();
     PlayerData.self.addExp(10);
       PlayerData.self.addInventory(new Item(getString(R.string.herb),30*PlayerData.self.getLevel(),true,getString(R.string.hp)));

        MediaPlayer temp=MediaPlayer.create(this, R.raw.dq3win);
        temp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
        public void onCompletion(MediaPlayer mp) {
            mp.release();

        }});
        mediaPlayer.pause();
        temp.start();

        Handler mHandler=new Handler();
        mHandler.postDelayed(new Runnable() {
            public void run() {

            }
        }, 2000);
         this.finish();

    }
}
