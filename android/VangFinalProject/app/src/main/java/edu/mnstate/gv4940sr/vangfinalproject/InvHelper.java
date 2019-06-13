package edu.mnstate.gv4940sr.vangfinalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Hoopa on 11/13/2017.
 */

public class InvHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="inventory.db";
    public static final int DATABASE_VERSION=2;
    public static final String TABLE_NAME="inventoryTable";
    public static final String ITEMID="_id";
    public static final String ITEMNAME="itemName";
    public static final String ITEMVALUE="itemValue";
    public static final String ITEMBASESTAT="itemBaseStat";
    public static final String ITEMAFFECTEDSTAT="itemAffectedStat";//stat item affects
    public static final String ITEMTARGET="itemTarget";//player,enem,both
    public static final String ITEMDESCRIPTION="itemDescription";

    public static final String CREATE_TABLE="CREATE TABLE "+ TABLE_NAME+" ("+ITEMID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ ITEMNAME
            + " VARCHAR(255), "+ ITEMVALUE
            + " VARCHAR(255), "+ ITEMBASESTAT
            + " VARCHAR(255), "+ ITEMAFFECTEDSTAT
            + " VARCHAR(255), "+ ITEMTARGET
            + " VARCHAR(255), "+ ITEMDESCRIPTION
            + " VARCHAR(255));";

    private static final String DROP_TABLE="DROP TABLE IF EXISTS "+TABLE_NAME;

    private Context context;

    public InvHelper(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
        this.context=context;
      //  Message.message(context,"Called InvHelper constructor");

    }

    public void resetCount()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME+"2");
        String temp="CREATE TABLE "+ TABLE_NAME+"2 ("+ITEMID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ ITEMNAME
                + " VARCHAR(255), "+ ITEMVALUE
                + " VARCHAR(255), "+ ITEMBASESTAT
                + " VARCHAR(255), "+ ITEMAFFECTEDSTAT
                + " VARCHAR(255), "+ ITEMTARGET
                + " VARCHAR(255), "+ ITEMDESCRIPTION
                + " VARCHAR(255));";
        db.execSQL(temp);
        db.execSQL("INSERT INTO "+TABLE_NAME+"2 ("+ITEMNAME+","+ITEMVALUE+","+ITEMBASESTAT+","+ITEMAFFECTEDSTAT+","+ITEMTARGET+","+ITEMDESCRIPTION+")"+" SELECT "+ITEMNAME+","+ITEMVALUE+","+ITEMBASESTAT+","+ITEMAFFECTEDSTAT+","+ITEMTARGET+","+ITEMDESCRIPTION+" "+ "FROM "+TABLE_NAME);
        db.execSQL(DROP_TABLE);
        db.execSQL(CREATE_TABLE);
        db.execSQL("INSERT INTO "+TABLE_NAME+" SELECT * FROM "+TABLE_NAME+"2");
        db.close();


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try
        {
            db.execSQL(CREATE_TABLE);
            ContentValues cVals=new ContentValues();
            cVals.put(InvHelper.ITEMNAME,"Herb");
            cVals.put(InvHelper.ITEMVALUE,"10");
            cVals.put(InvHelper.ITEMDESCRIPTION,"A medicinal herb used to heal wounds. Extremely bitter.");
            cVals.put(InvHelper.ITEMNAME,"Herb");
            cVals.put(InvHelper.ITEMVALUE,"10");
            cVals.put(InvHelper.ITEMDESCRIPTION,"A medicinal herb used to heal wounds. Extremely bitter.");
            cVals.put(InvHelper.ITEMNAME,"Herb");
            cVals.put(InvHelper.ITEMVALUE,"10");
            cVals.put(InvHelper.ITEMDESCRIPTION,"A medicinal herb used to heal wounds. Extremely bitter.");
            Message.message(context,"onCreate called");

        }
        catch(SQLException e)
        {
            Message.message(context,""+e);

        }

    }


    public void clearTable()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL(DROP_TABLE);
        db.execSQL(CREATE_TABLE);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer)
    {
        try
        {
            Message.message(context,"onUpgrade called");
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }
        catch (SQLException e){Message.message(context,""+e);}

    }
}
