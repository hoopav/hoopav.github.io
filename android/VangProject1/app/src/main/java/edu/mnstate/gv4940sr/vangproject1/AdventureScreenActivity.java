package edu.mnstate.gv4940sr.vangproject1;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.media.ToneGenerator.MAX_VOLUME;
import static edu.mnstate.gv4940sr.vangproject1.MainActivity.mediaPlayer;

public class AdventureScreenActivity extends AppCompatActivity {
    private PlayerData player;
    public static boolean loaded=false;
    Bundle b;
    Intent thisIntent;
    TextView atk,mp,hp,def,name,classname,exp, expToNext, level,volumeLevel;
    SeekBar volume;
    Spinner inventory;
    CheckBox useItem;
    Item selected;
Player stats;
    final Context context=this;
    ArrayAdapter<Item> adapter;
ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure_screen);


        mediaPlayer= MediaPlayer.create(this, R.raw.dq3battleprep);
        mediaPlayer.setLooping(true);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.release();

            }});
        mediaPlayer.start();
        player=PlayerData.self;
if(loaded==false) {
    Log.d("loaded is false","loaded false");
    thisIntent = getIntent();
    b = thisIntent.getExtras();
    String playerClassInfo = b.getString("selectedClass");
    String[] playerarray = playerClassInfo.split("::-::");
    stats = new Player();
    stats.setName(playerarray[1]);
    stats.setDescription(playerarray[3]);
    stats.setImageUrl(playerarray[5]);
    // Toast.makeText(this,stats.getImageUrl(),Toast.LENGTH_LONG).show();
    stats.setAbility(playerarray[7]);
    stats.atk = playerarray[9];
    stats.def = playerarray[11];
    stats.hp = playerarray[13];
    stats.mp = playerarray[15];

    Log.d("Character stats:",stats.atk);
    Log.d("Character stats:",stats.def);
    Log.d("Character stats:",stats.mp);
    Log.d("Character stats:",stats.hp);

    PlayerData.vocation=stats;
    PlayerData.inventory=new ArrayList<Item>();
    PlayerData.self.addInventory(new Item(getString(R.string.herb),10,true,getString(R.string.hp)));
    PlayerData.self.addInventory(new Item(getString(R.string.herb),10,true,getString(R.string.hp)));
    PlayerData.self.addInventory(new Item(getString(R.string.herb),10,true,getString(R.string.hp)));
    PlayerData.self.setName(b.getString("name"));
    PlayerData.self.setGender(b.getString("gender"));
    PlayerData.self.setLevel(1);
    PlayerData.self.setExp(0);
    PlayerData.self.setSkills(new ArrayList<Ability>());
}
else
    {
        stats=PlayerData.vocation;
    }

       img=(ImageView)findViewById(R.id.characterPic);

        volumeLevel=(TextView)findViewById(R.id.volumeLeveltextView);
        atk=(TextView) findViewById(R.id.playerAtkTextView);
        def=(TextView) findViewById(R.id.playerDefTextView);
        hp=(TextView) findViewById(R.id.playerHPTextView);
        mp=(TextView) findViewById(R.id.playerMPTextView);
        exp=(TextView) findViewById(R.id.playerExpTextView);
        expToNext=(TextView) findViewById(R.id.playerExpToNextTextView);
        level=(TextView) findViewById(R.id.levelTextView);
        classname=(TextView) findViewById(R.id.classNameTextView);
        name=(TextView) findViewById(R.id.playerSaveNameTextView);
        Log.d("Class check","Got here");
        atk.setText(getString(R.string.ATK)+stats.atk);
        def.setText(getString(R.string.DEF)+stats.def);
        hp.setText(getString(R.string.HP)+stats.hp);
        mp.setText(getString(R.string.MP)+stats.mp);
        classname.setText(getString(R.string.classnameText)+stats.name);
        name.setText(getString(R.string.playerNameText)+player.getName());
        exp.setText(getString(R.string.currentExptext)+player.getExp()+"");
        expToNext.setText(getString(R.string.exptonextlevelText)+player.getExpToLevel()+"");
        level.setText(getString(R.string.leveltext)+player.getLevel()+"");/**/
        atk.setTextColor(ContextCompat.getColor(this,R.color.colorText));
        def.setTextColor(ContextCompat.getColor(this,R.color.colorText));
        hp.setTextColor(ContextCompat.getColor(this,R.color.colorText));
        mp.setTextColor(ContextCompat.getColor(this,R.color.colorText));
        classname.setTextColor(ContextCompat.getColor(this,R.color.colorText));
        name.setTextColor(ContextCompat.getColor(this,R.color.colorText));
        exp.setTextColor(ContextCompat.getColor(this,R.color.colorText));
        expToNext.setTextColor(ContextCompat.getColor(this,R.color.colorText));
        level.setTextColor(ContextCompat.getColor(this,R.color.colorText));

        Picasso.Builder builder = new Picasso.Builder(this);
        builder.listener(new Picasso.Listener()
        {

            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception)
            {
                exception.printStackTrace();
            }
        });

        builder.build().load(stats.getImageUrl()).into(img);

        inventory=(Spinner) findViewById(R.id.selectItem);

        adapter=new ArrayAdapter<Item>(this,android.R.layout.simple_spinner_item,PlayerData.inventory);
        inventory.setAdapter(adapter);

        inventory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
                    public void onItemSelected(AdapterView<?> parent,View view, int position, long id)
            {
                MediaPlayer m=MediaPlayer.create(context,R.raw.dw1clickse);
                m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();

                    }});
                m.start();
                m.release();

                selected=adapter.getItem(position);
                Toast.makeText(context,selected.name+" selected. Modifies "+selected.stat+" by "+selected.baseValue,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

        volume=(SeekBar) findViewById(R.id.volumeSeekBar);

        volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 70;
            @Override
            public void onProgressChanged(SeekBar seekBar,
                                          int progresValue, boolean fromUser) {
                progress = progresValue;
                float log1=(float)(Math.log(MAX_VOLUME-progress)/Math.log(MAX_VOLUME));
                mediaPlayer.setVolume(1-log1,1-log1);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                float log1=(float)(Math.log(MAX_VOLUME-progress)/Math.log(MAX_VOLUME));
                mediaPlayer.setVolume(1-log1,1-log1);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Display the value in textview
                volumeLevel.setText(getString(R.string.volumebarText)+progress + "/" + seekBar.getMax());
                float log1=(float)(Math.log(MAX_VOLUME-progress)/Math.log(MAX_VOLUME));
                mediaPlayer.setVolume(1-log1,1-log1);
            }
        });

    }

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(!mediaPlayer.isPlaying())
        {mediaPlayer= MediaPlayer.create(this, R.raw.dq3battleprep);
            mediaPlayer.start();
        }
        img=(ImageView)findViewById(R.id.characterPic);

        volumeLevel=(TextView)findViewById(R.id.volumeLeveltextView);
        atk=(TextView) findViewById(R.id.playerAtkTextView);
        def=(TextView) findViewById(R.id.playerDefTextView);
        hp=(TextView) findViewById(R.id.playerHPTextView);
        mp=(TextView) findViewById(R.id.playerMPTextView);
        exp=(TextView) findViewById(R.id.playerExpTextView);
        expToNext=(TextView) findViewById(R.id.playerExpToNextTextView);
        level=(TextView) findViewById(R.id.levelTextView);
        classname=(TextView) findViewById(R.id.classNameTextView);
        name=(TextView) findViewById(R.id.playerSaveNameTextView);

        atk.setText(getString(R.string.ATK)+PlayerData.vocation.atk);
        def.setText(getString(R.string.DEF)+PlayerData.vocation.def);
        hp.setText(getString(R.string.HP)+PlayerData.vocation.hp);
        mp.setText(getString(R.string.MP)+PlayerData.vocation.mp);
        classname.setText(getString(R.string.classnameText)+PlayerData.vocation.name);
        name.setText(getString(R.string.playerNameText)+player.getName());
        exp.setText(getString(R.string.currentExptext)+PlayerData.self.getExp()+"");
        expToNext.setText(getString(R.string.exptonextlevelText)+PlayerData.self.getExpToLevel()+"");
        level.setText(getString(R.string.leveltext)+PlayerData.self.getLevel()+"");/**/
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!mediaPlayer.isPlaying())
        {mediaPlayer= MediaPlayer.create(this, R.raw.dq3battleprep);
        mediaPlayer.start();
        }
        img=(ImageView)findViewById(R.id.characterPic);

        volumeLevel=(TextView)findViewById(R.id.volumeLeveltextView);
        atk=(TextView) findViewById(R.id.playerAtkTextView);
        def=(TextView) findViewById(R.id.playerDefTextView);
        hp=(TextView) findViewById(R.id.playerHPTextView);
        mp=(TextView) findViewById(R.id.playerMPTextView);
        exp=(TextView) findViewById(R.id.playerExpTextView);
        expToNext=(TextView) findViewById(R.id.playerExpToNextTextView);
        level=(TextView) findViewById(R.id.levelTextView);
        classname=(TextView) findViewById(R.id.classNameTextView);
        name=(TextView) findViewById(R.id.playerSaveNameTextView);

        atk.setText(getString(R.string.ATK)+PlayerData.vocation.atk);
        def.setText(getString(R.string.DEF)+PlayerData.vocation.def);
        hp.setText(getString(R.string.HP)+PlayerData.vocation.hp);
        mp.setText(getString(R.string.MP)+PlayerData.vocation.mp);
        classname.setText(getString(R.string.classnameText)+PlayerData.vocation.name);
        name.setText(getString(R.string.playerNameText)+player.getName());
        exp.setText(getString(R.string.currentExptext)+PlayerData.self.getExp()+"");
        expToNext.setText(getString(R.string.exptonextlevelText)+PlayerData.self.getExpToLevel()+"");
        level.setText(getString(R.string.leveltext)+PlayerData.self.getLevel()+"");/**/
    }


    public void startBattle(View view)
    {
        MediaPlayer m=MediaPlayer.create(context,R.raw.dw1clickse);
        m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.release();

            }});
        m.start();
        m.release();
        CheckBox checked=(CheckBox) findViewById(R.id.checkBox);
        if(checked.isChecked())
        {
            Toast.makeText(context, R.string.toastUpdateText,Toast.LENGTH_LONG).show();

            selected.update(PlayerData.vocation,selected.stat);

        }
        Intent battle=new Intent(this,Battle.class);
        startActivity(battle);
    }

    public void saveData(View view) {

        mediaPlayer.pause();
        Toast.makeText(context, R.string.toastSavingData,Toast.LENGTH_SHORT).show();
        MediaPlayer m=MediaPlayer.create(context,R.raw.dq3save);
        m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.release();

            }});
        m.start();

        PlayerData.writeToFile(context);
        while(m.isPlaying()){}

        mediaPlayer.start();

    }

    public void checkboxClick(View view) {
        MediaPlayer m=MediaPlayer.create(context,R.raw.dw1clickse);
        m.start();
        m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.release();

            }});

    }
}
