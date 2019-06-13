package edu.mnstate.gv4940sr.vangfinalproject;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HomeScreen extends AppCompatActivity {
    public static MediaPlayer homeScreenMediaPlayer;
    private HomeScreenFragment homeDisplay;
    private ShopDisplayFragment shopDisplay;
    private UnitDisplayFragment unitDisplay;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener;

    public static List<UnitDisplay> unitRoster=new ArrayList<>();
    public static List<GameMode> gameModeRoster= new ArrayList<>();
    public static SharedPreferences pref;
    public static SharedPreferences.Editor prefEdit;


    @Override
    protected void onPause()
    {
        super.onPause();
        homeScreenMediaPlayer.pause();
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        homeScreenMediaPlayer.seekTo(0);
        if(!pref.getBoolean("mute",false))//if not mute, play
        homeScreenMediaPlayer.start();

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if(!pref.getBoolean("mute",false))//if not mute, play
        homeScreenMediaPlayer.start();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        homeScreenMediaPlayer.release();
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        initUnits();
        initGameModes();
        homeDisplay=new HomeScreenFragment();
        shopDisplay=new ShopDisplayFragment();
        unitDisplay=new UnitDisplayFragment();



        pref=getApplicationContext().getSharedPreferences("MyPrefs",0);
        prefEdit=pref.edit();

        homeScreenMediaPlayer=MediaPlayer.create(this,R.raw.home);
        homeScreenMediaPlayer.setLooping(true);

        if(!pref.getBoolean("mute",false))//if not mute, play
            homeScreenMediaPlayer.start();

        mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        getFragmentManager().beginTransaction()
                                .replace(R.id.home_screen_frame_layout,homeDisplay)
                                .commit();
                        return true;
                    case R.id.navigation_shop:
                        getFragmentManager().beginTransaction()
                                .replace(R.id.home_screen_frame_layout,shopDisplay)
                                .commit();
                        return true;
                    case R.id.navigation_unit:
                        getFragmentManager().beginTransaction()
                                .replace(R.id.home_screen_frame_layout,unitDisplay)
                                .commit();
                        return true;
                }
                return false;
            }

        };
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getFragmentManager().beginTransaction()
                .replace(R.id.home_screen_frame_layout,homeDisplay)
                .commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.title_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        boolean result=false;
        switch(item.getItemId())
        {
            case R.id.about:
                getFragmentManager().beginTransaction()
                        .replace(R.id.home_screen_frame_layout, new AboutFragment())
                        .commit();
                result=true;
                break;
            case R.id.preferences:
                getFragmentManager().beginTransaction()
                        .replace(R.id.home_screen_frame_layout, new PreferencesFragment())
                        .commit();
                result=true;
                break;
        }
        return result;
    }

    public void initUnits()
    {
        unitRoster.add(new UnitDisplay("Player",R.drawable.playercharacter));
        unitRoster.add(new UnitDisplay("Ally1",R.drawable.playercharacter2));
    }
    public void initGameModes()
    {
        gameModeRoster.add(new GameMode("Dungeon",R.drawable.dungeon));
        gameModeRoster.add(new GameMode("Vortex",R.drawable.vortex));
    }

    public void deleteItem(View view)
    {
        long money=0;
        InvHelper invHelper=new InvHelper(this);
        SQLiteDatabase db=invHelper.getReadableDatabase();
        Cursor c=db.rawQuery("SELECT itemValue FROM inventoryTable",null);
        c.moveToFirst();
        if(c.getCount()==0)
            return;

        for(int j=0;j<c.getCount();j++) {
            String value=c.getString(0);
            if(value==null)
                value="0";
            money=money+Long.parseLong(value);
            c.moveToNext();
        }
        invHelper.clearTable();
        HomeScreen.prefEdit.putLong("money",money);
        HomeScreen.prefEdit.apply();
        Log.d("debug",""+money);
        shopDisplay.refresh();
    }

    public void addItem(View view) {
        ContentValues cVals=new ContentValues();
        cVals.put(InvHelper.ITEMNAME,"Debug Item");
        cVals.put(InvHelper.ITEMVALUE,"9999");
        cVals.put(InvHelper.ITEMDESCRIPTION,"A debug item that serves as a placeholder.");
        InvHelper invHelper=new InvHelper(this);
        SQLiteDatabase db=invHelper.getReadableDatabase();
        db.insert(InvHelper.TABLE_NAME,null,cVals);
        shopDisplay.refresh();
    }

    public void showNames(View view) {
        shopDisplay.showNames();
    }

    public void showNamesDescriptions(View view) {
        shopDisplay.showNamesDescriptions();
    }
}
