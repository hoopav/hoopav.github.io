package edu.mnstate.gv4940sr.vangproject1;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

import static edu.mnstate.gv4940sr.vangproject1.MainActivity.currentVolume;
import static edu.mnstate.gv4940sr.vangproject1.MainActivity.m;
import static edu.mnstate.gv4940sr.vangproject1.MainActivity.mediaPlayer;

public class MenuActivity extends AppCompatActivity {

    final Context context=this;
    private ListView myListView;
    private EditText nameInput;
    private ToggleButton gender;
    private ArrayList<Player> displayedClasses;
    private ArrayList<Player> playerArrayList;
    private Player selectedClass;
    private PlayerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        nameInput=(EditText) findViewById(R.id.nameInputEditText) ;
        nameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                m=MediaPlayer.create(context,R.raw.dw1clickse);
                m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();

                    }});
                m.start();
            }
        });

        gender=(ToggleButton) findViewById(R.id.genderToggleButton);myListView=(ListView) findViewById(R.id.classlistview);
        playerArrayList=Player.getPlayersFromFile("playerclasses.json",context);
        displayedClasses=new ArrayList<>();
        for(int i=0;i<playerArrayList.size()/2;i++)
            displayedClasses.add(playerArrayList.get(i));





        selectedClass=new Player();

        adapter=new PlayerAdapter(this,displayedClasses);
        myListView.setAdapter(adapter); myListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,long l){
                m=MediaPlayer.create(context,R.raw.dw1clickse);
                m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();

                    }});
                m.start();

                Player item = (Player) adapter.getItemAtPosition(position);
                Toast.makeText(context,getString(R.string.toastYouPicked)+item.getName(),Toast.LENGTH_SHORT).show();
                //Toast.makeText(context,"Ability "+item.getAbility(),Toast.LENGTH_SHORT).show();
                selectedClass=item;

            }
        });
        mediaPlayer=MediaPlayer.create(this, R.raw.dq3menu);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.release();

            }});
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(currentVolume,currentVolume);

        mediaPlayer.start();


    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }
    }

    public void createFile(View view) {
        m=MediaPlayer.create(context,R.raw.dw1clickse);
        m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.release();

            }});
        m.start();
        Bundle b=new Bundle();
        EditText nameInput=(EditText) findViewById(R.id.nameInputEditText);
        b.putString(getString(R.string.bundleTextName),nameInput.getText().toString());
        String selectedGender;
        gender=(ToggleButton) findViewById(R.id.genderToggleButton);
        if(gender.isChecked())
        {
            selectedGender=getString(R.string.genderTextFemal);
        }
        else
        {
            selectedGender=getString(R.string.genderTextMale);

        }
        b.putString(getString(R.string.bundleGenderText),selectedGender);

        b.putString(getString(R.string.bundleSelectedClassText),selectedClass.toString());
        Intent beginGame=new Intent(context,AdventureScreenActivity.class);
        beginGame.putExtras(b);
        startActivity(beginGame);
    }

    @Override
    public void onBackPressed() {
    }

    public void updateList(View view) {
       // Toast.makeText(context,"Clicked Toggle",Toast.LENGTH_LONG).show();
        m=MediaPlayer.create(context,R.raw.dw1clickse);
        m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.release();

            }});
        m.start();


        displayedClasses.clear();
        gender=(ToggleButton) findViewById(R.id.genderToggleButton);
        if(gender.isChecked())
        {
            for(int i=playerArrayList.size()/2;i<playerArrayList.size();i++)
                displayedClasses.add(playerArrayList.get(i));
        }
        else
        {
            for(int i=0;i<playerArrayList.size()/2;i++)
                displayedClasses.add(playerArrayList.get(i));

        }
        PlayerAdapter newAdapter= new PlayerAdapter(context,displayedClasses);
        myListView.setAdapter(newAdapter);



    }
}
