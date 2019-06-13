package edu.mnstate.gv4940sr.vangfinalproject;

import android.app.ListFragment;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class ShopDisplayFragment extends ListFragment implements AdapterView.OnItemClickListener{
    private InvHelper invHelper;
    private SQLiteDatabase db;
    ArrayList<String> cols;
    ArrayAdapter<String> adapter;
    private String itemName;

    public ShopDisplayFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_shop_display, container, false);
        invHelper=new InvHelper(getActivity());
        updateList();
        itemName="'Herb'";
        TextView playermoney=view.findViewById(R.id.player_money);
        playermoney.setText(""+HomeScreen.pref.getLong("money",0));
        return view;
    }

    public void showNames()
    {

        db=invHelper.getReadableDatabase();
        Cursor c=db.rawQuery("SELECT itemName FROM inventoryTable",null);
        //Cursor c=db.rawQuery("SELECT * FROM inventoryTable WHERE itemName="+itemName,null);
        String test="";
        cols=new ArrayList<>();
        try
        {
            c.moveToFirst();
            //   Message.message(getContext(),c.isFirst()+"");

            // Message.message(getContext(),c.getCount()+"");
            String[] colNames=c.getColumnNames();
            String columnNames="";
            for(int i=0;i<colNames.length;i++)
            {
                columnNames=columnNames+colNames[i]+"   ";
            }
            //cols.add(columnNames);
            for(int j=0;j<c.getCount();j++) {

                cols.add(c.getString(0));
                c.moveToNext();
            }
            //Message.message(getContext(),columnNames);
            //  Message.message(getContext(),test);
        }finally {
            c.close();
            db.close();
        }
        adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,cols);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    public void updateList()
    {


        db=invHelper.getReadableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM inventoryTable",null);
        //Cursor c=db.rawQuery("SELECT * FROM inventoryTable WHERE itemName="+itemName,null);
        String test="";
        cols=new ArrayList<>();
        try
        {
            c.moveToFirst();
            //   Message.message(getContext(),c.isFirst()+"");

            // Message.message(getContext(),c.getCount()+"");
            String[] colNames=c.getColumnNames();
            String columnNames="";
            for(int i=0;i<colNames.length;i++)
            {
                columnNames=columnNames+colNames[i]+"   ";
            }
            //cols.add(columnNames);
            for(int j=0;j<c.getCount();j++) {
                test = "";
                for (int i = 0; i < 7; i++) {
                    test += c.getString(i) + "   ";
                }
                cols.add(test);
                c.moveToNext();
            }
            //Message.message(getContext(),columnNames);
            //  Message.message(getContext(),test);
        }finally {
            c.close();
            db.close();
        }


    }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);


            adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,cols);
            setListAdapter(adapter);
            getListView().setOnItemClickListener(this);
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String[] s=adapter.getItem(position).split(" ");

            db=invHelper.getReadableDatabase();
            Cursor c=db.rawQuery("SELECT * FROM inventoryTable",null);
            String temp="";
            for(int j=0;j<position;j++) {

                c.moveToNext();
                temp=c.getString(0);
                Log.d("debug",temp);

            }
           db.execSQL("DELETE FROM inventoryTable WHERE _id='"+temp+"'");


            cols.remove(adapter.getItem(position));
            invHelper.resetCount();
            updateList();

            adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,cols);
            setListAdapter(adapter);
            getListView().setOnItemClickListener(this);
            // refresh();
            // Toast.makeText(getActivity(), adapter.getItem(position), Toast.LENGTH_SHORT).show();

        }

        public void refresh()
        {

            db=invHelper.getReadableDatabase();
            Cursor c=db.rawQuery("SELECT * FROM inventoryTable",null);
            //Cursor c=db.rawQuery("SELECT * FROM inventoryTable WHERE itemName="+itemName,null);
            String test="";
            cols=new ArrayList<>();
            try
            {
                c.moveToFirst();
                //   Message.message(getContext(),c.isFirst()+"");

                // Message.message(getContext(),c.getCount()+"");
                String[] colNames=c.getColumnNames();
                String columnNames="";
                for(int i=0;i<colNames.length;i++)
                {
                    columnNames=columnNames+colNames[i]+"   ";
                }
                //cols.add(columnNames);
                for(int j=0;j<c.getCount();j++) {
                    test = "";
                    for (int i = 0; i < 7; i++) {
                        test += c.getString(i) + "   ";
                    }
                    cols.add(test);
                    c.moveToNext();
                }
                //Message.message(getContext(),columnNames);
                //  Message.message(getContext(),test);
            }finally {
                c.close();
                db.close();
            }
            adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,cols);
            setListAdapter(adapter);
            getListView().setOnItemClickListener(this);

        }

    public void showNamesDescriptions() {


        db=invHelper.getReadableDatabase();
        Cursor c=db.rawQuery("SELECT itemName,itemDescription FROM inventoryTable",null);
        //Cursor c=db.rawQuery("SELECT * FROM inventoryTable WHERE itemName="+itemName,null);
        String test="";
        cols=new ArrayList<>();
        try
        {
            c.moveToFirst();
            //   Message.message(getContext(),c.isFirst()+"");

            // Message.message(getContext(),c.getCount()+"");
            String[] colNames=c.getColumnNames();



            String columnNames="";
            for(int i=0;i<colNames.length;i++)
            {
                columnNames=columnNames+colNames[i]+"   ";
            }
            //cols.add(columnNames);
            for(int j=0;j<c.getCount();j++) {
                test = "";
                for (int i = 0; i < 2; i++) {
                    test += c.getString(i) + "   ";
                }
                cols.add(test);
                c.moveToNext();
            }

        }finally {
            c.close();
            db.close();
        }
        adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,cols);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }
}