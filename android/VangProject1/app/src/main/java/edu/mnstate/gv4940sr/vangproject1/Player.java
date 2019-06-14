package edu.mnstate.gv4940sr.vangproject1;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Hoopa on 10/14/2017.
 */

public class Player extends Thing implements BattleInterface
{
    public String name;
    public String description;
    public String imageUrl;
    public String ability;
    public Context context;


    public Player()
    {
        name="Hero";
        description="The secret class. Super strong.";
        imageUrl="http://www.realmofdarkness.net/dq/img/snes/dq3/char/hero-m.png";
        ability="nothing";
        hp="1111";
        atk="999";
        def="999";
        mp="999";

    }

    public void setContext(Context c)
    {
        context=c;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAbility() {
        return ability;
    }

    public String toString()
    {
        return "Name::-::"+name+ "::-::Description::-::"+description+"::-::ImageURL::-::"+imageUrl+"::-::Ability::-::"+ability+"::-::atk::-::"+atk+"::-::def::-::"+def+"::-::HP::-::"+hp+"::-::MP::-::"+mp;

    }

    public void setAbility(String ability) {
        this.ability = ability;
    }


    public static ArrayList<Player> getPlayersFromFile(String filename, Context context){
        final ArrayList<Player> PlayerList = new ArrayList<>();

        try {
            // Load data

            String jsonString = loadJsonFromAsset(filename, context);

            JSONObject json = new JSONObject(jsonString);
            JSONArray Players = json.getJSONArray("playerclasses");



            // Get Player objects from data
            for(int i = 0; i < Players.length(); i++){
                Player Player = new Player();

                Player.name = Players.getJSONObject(i).getString("name");
                Player.description = Players.getJSONObject(i).getString("description");
                Player.imageUrl = Players.getJSONObject(i).getString("imageurl");
                Player.ability=Players.getJSONObject(i).getString("ability");
                Player.hp=Players.getJSONObject(i).getString("hp");
                Player.mp=Players.getJSONObject(i).getString("mp");
                Player.atk=Players.getJSONObject(i).getString("atk");
                Player.def=Players.getJSONObject(i).getString("def");

                //Toast.makeText(context,Player.toString(),Toast.LENGTH_SHORT).show();


                PlayerList.add(Player);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(context, "Failed jsonload",Toast.LENGTH_SHORT).show();
        }

        return PlayerList;
    }

    private static String loadJsonFromAsset(String filename, Context context) {
        String json = null;

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }

    @Override
    public void attack(Thing m) {
        int targethp=Integer.parseInt(m.hp);
        int targetDef=Integer.parseInt(m.def);
        int myatk=Integer.parseInt(atk);
        targethp=targethp-(myatk-targetDef);
        m.hp=targethp+"";

        final int damage=myatk-targetDef;
        Handler mHandler = new Handler();
        MediaPlayer n= MediaPlayer.create(context, R.raw.dw1playeratk);
        n.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.release();

            }});
        Toast.makeText(context,"You attack the enemy for "+damage+" damage!",Toast.LENGTH_SHORT).show();
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        wait(3000);
                    }
                } catch (InterruptedException ex) {
                }

                // TODO              
            }
        };

        thread.start();
        n.start();

        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            public void run() {

            }
        }, 4000);

        n=MediaPlayer.create(context, R.raw.dw1dmg);
        n.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.release();

            }});
        n.start();
    }

    @Override
    public void defend() {
        def=(int)(Integer.parseInt(def)*1.5)+"";

        hp=(int)(Integer.parseInt(hp)+Integer.parseInt(hp)*.25)+"";


        Handler mHandler = new Handler();


        MediaPlayer n= MediaPlayer.create(context, R.raw.dw1guard);
        n.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.release();

            }});
        n.start();
            mHandler.postDelayed(new Runnable() {
                public void run() {
                    Toast.makeText(context,context.getString(R.string.toastDefend)+hp,Toast.LENGTH_SHORT).show();
                }
            }, 2000);



    }

    @Override
    public void ability(Thing self, Thing target) {
        MediaPlayer n= MediaPlayer.create(context, R.raw.dw1spell);
        n.start();
        n.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.release();

            }});
        Handler mHandler=new Handler();
        mHandler.postDelayed(new Runnable() {
            public void run() {
            }
        }, 2000);

        if(Integer.parseInt(mp)>=0){
        switch(name.toLowerCase())
        {
            case "warrior":
                Toast.makeText(context, R.string.toastNoAbility,Toast.LENGTH_SHORT).show();
                break;
            case "cleric":
                hp=(Integer.parseInt(hp)+100)+"";
                mp=(Integer.parseInt(mp)-10)+"";
                Toast.makeText(context,context.getString(R.string.toastHeal)+hp,Toast.LENGTH_SHORT).show();
                Toast.makeText(context,context.getString(R.string.toastMp)+mp,Toast.LENGTH_SHORT).show();



                break;
            case "mage":

                target.hp=(Integer.parseInt(target.hp)-Integer.parseInt(atk)*3)+"";
                Toast.makeText(context,context.getString(R.string.toastBlaze)+target.hp,Toast.LENGTH_SHORT).show();
                mp=(Integer.parseInt(mp)-10)+"";
                n.release();
                Toast.makeText(context,context.getString(R.string.toastMp)+mp,Toast.LENGTH_SHORT).show();
                n=MediaPlayer.create(context, R.raw.dw1dmg);
n.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
    public void onCompletion(MediaPlayer mp) {
        mp.release();

    }});
                n.start();
                 mHandler=new Handler();
                mHandler.postDelayed(new Runnable() {
                    public void run() {
                    }
                }, 2000);



            break;
        }}
    }
}
