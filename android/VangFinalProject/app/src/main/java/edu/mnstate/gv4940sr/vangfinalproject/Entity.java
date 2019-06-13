package edu.mnstate.gv4940sr.vangfinalproject;

import java.util.Date;
import java.util.Random;

/**
 * Created by Hoopa on 11/15/2017.
 */

public abstract class Entity {
    int hp=100;
    int atk=10;
    int lvl=1;
    boolean isAlive=true;

    public Entity(){}

    public Entity(int hp, int atk, int lvl)
    {
        this.hp=hp;
        this.atk=atk;
        this.lvl=lvl;
    }

    public Entity(int lvl)
    {
        modStatsOnLvl(lvl);
    }

    public void modStatsOnLvl(int lvl)
    {
        Date seed=new Date();

        Random rand=new Random(seed.getTime());
        int multiplier=rand.nextInt(10);
        hp=hp+lvl*multiplier;//randomly increase
        atk=atk+lvl*multiplier;
    }

    public void attack(Entity target)
    {
        Date seed=new Date();
        Random rand=new Random(seed.getTime());
        int dmg=rand.nextInt(atk);;
        while (dmg<atk/2)//forbid damage from dropping below half the atk stat
            dmg=rand.nextInt(atk);
        target.modHP(-dmg);
    }

    public void modHP(int value)
    {
        hp+=value;
        if(hp<=0)//check if dead.
        {
            hp=0;
            isAlive=false;
        }
    }
}
