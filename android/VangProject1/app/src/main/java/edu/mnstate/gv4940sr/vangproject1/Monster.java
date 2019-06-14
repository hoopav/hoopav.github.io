package edu.mnstate.gv4940sr.vangproject1;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.widget.Toast;

/**
 * Created by Hoopa on 10/14/2017.
 */

public class Monster extends Thing implements BattleInterface
{
    private String name;
    private String ability;
    private String imageurl;
    Context context;



    Monster(String name, String ability, String imageurl, String hp,String mp, String atk,String def)
    {
        this.setName(name);
        this.setAbility(ability);
        this.setImageurl(imageurl);
        this.hp=hp;
        this.mp=mp;
        this.atk=atk;
        this.def=def;
    }

    public void setContext(Context c)
    {
        context=c;

    }

    @Override
    public void attack(Thing m) {
        int targethp=Integer.parseInt(m.hp);
        int targetDef=Integer.parseInt(m.def);
        int myatk=Integer.parseInt(atk);
        targethp=targethp-(myatk-targetDef);
        m.hp=targethp+"";
        final String modHP=m.hp;
        MediaPlayer n;
        n= MediaPlayer.create(context, R.raw.dw1enemyatk);
        n.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.release();

            }});
        n.start();
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            public void run() {
            }
        }, 2000);






    }

    @Override
    public void defend() {

    }

    @Override
    public void ability(Thing self, Thing target) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
