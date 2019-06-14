package edu.mnstate.gv4940sr.vangproject1;

import java.lang.reflect.Field;

/**
 * Created by Hoopa on 10/14/2017.
 */

public class Item implements UpdateStatusInterface
{
    int baseValue;
    String name;
    String stat;
    public boolean singleUse;
    public Item( String n, int value, boolean singleUse,String stat)
    {
        baseValue=value;
        this.singleUse=singleUse;
        name=n;
        this.stat=stat;
    }

    public String toString()
    {
        return name;
    }

    @Override
    public void update(Thing t, String stat)
    {
        for(Field f:t.fields)
        {
            if (f.getName()=="stat")
            {
                try {
                    String statVal=(String) f.get(t);
                    int trueStat=Integer.parseInt(statVal);
                    trueStat=trueStat+baseValue;
                    statVal=trueStat+"";
                    f.set(t,statVal);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        if(singleUse==true)
        {
            name="used up "+name;
            baseValue=0;
        }
    }
}
