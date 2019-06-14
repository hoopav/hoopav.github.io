package edu.mnstate.gv4940sr.vangproject1;


import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hoopa on 10/14/2017.
 */

public class PlayerData
{
    public static Player vocation;
    public static List<Item> inventory;
    private static String name;
    private static String gender;
    private static int level;
    private static List<Ability> skills;
    private static int exp;
    private static int expToLevel;
    public static PlayerData self;



    public PlayerData(Player v, List<Item> i, String name, String gender, int lvl, int exp ,List<Ability> skills)
    {
        setVocation(v);
        setInventory(i);
        this.setName(name);
        this.setGender(gender);
        setLevel(lvl);
        this.setExp(exp);
        this.setSkills(skills);
        self=this;
    }

    public void addInventory(Item i)
    {
        inventory.add(i);

    }

    public void levelUp()
    {
        MediaPlayer m=MediaPlayer.create(vocation.context,R.raw.dq3levelup);
        m.start();
      vocation.hp=((int)(Integer.parseInt(vocation.hp)*1.5))+"";

        vocation.atk=((int)(Integer.parseInt(vocation.atk)*1.5))+"";

        vocation.def=((int)(Integer.parseInt(vocation.def)*1.5))+"";

        vocation.mp=((int)(Integer.parseInt(vocation.mp)*1.5))+"";

    }

    public int getNextExpLevel()
    {
        return getLevel() *10;
    }

    public void addExp(int xp)
    {
        Log.d("EXP:","Added exp: "+xp);
        exp=exp+xp;
        if(exp> getExpToLevel())
        {
            exp=exp- getExpToLevel();
            setLevel(getLevel() + 1);
            levelUp();
            setExpToLevel(getNextExpLevel());
        }

    }

    public PlayerData()
    {
        setVocation(new Player());
        setInventory(new ArrayList<Item>());
        getInventory().add(new Item("herb",10,true,"hp"));
        setName("Player1");
        setGender("male");
        setLevel(1);
        setExp(0);
        setExpToLevel(getNextExpLevel());
        setSkills(new ArrayList<Ability>());
        self=this;
    }

    private static String makeSaveFile()
    {
        return name+":-A-:"+gender+":-A-:"+level+":-A-:"+exp+":-A-:"+vocation.toString();
    }



    public static void loadFromFile(Context context)
    {
        try {
            FileInputStream f=context.openFileInput("savedata.txt");
            BufferedReader br=new BufferedReader(new InputStreamReader(f));
            String file=br.readLine();
            String[] data=file.split(":-A-:");
            name=data[0];
            gender=data[1];
            level=Integer.parseInt(data[2]);
            exp=Integer.parseInt(data[3]);
            String playerClassInfo =data[4];
            String[] playerarray = playerClassInfo.split("::-::");
            Player stats = new Player();
            stats.setName(playerarray[1]);
            stats.setDescription(playerarray[3]);
            stats.setImageUrl(playerarray[5]);
            // Toast.makeText(this,stats.getImageUrl(),Toast.LENGTH_LONG).show();
            stats.setAbility(playerarray[7]);
            stats.atk = playerarray[9];
            stats.def = playerarray[11];
            stats.hp = playerarray[13];
            stats.mp = playerarray[15];
            vocation=stats;



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFile(Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("savedata.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(makeSaveFile());
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }


    public Player getVocation() {
        return vocation;
    }

    public void setVocation(Player vocation) {
        this.vocation = vocation;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<Ability> getSkills() {
        return skills;
    }

    public void setSkills(List<Ability> skills) {
        this.skills = skills;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getExpToLevel() {
        return expToLevel;
    }

    public void setExpToLevel(int expToLevel) {
        this.expToLevel = expToLevel;
    }
}
