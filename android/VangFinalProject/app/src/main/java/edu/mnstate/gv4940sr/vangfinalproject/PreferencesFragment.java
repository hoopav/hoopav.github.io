package edu.mnstate.gv4940sr.vangfinalproject;

import android.app.ListFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;


public class PreferencesFragment  extends ListFragment implements AdapterView.OnItemClickListener {
    ArrayAdapter<String> adapter;
    String[] prefs;
    boolean[] prefvalues;
    int count=0;

    public PreferencesFragment(){
        // Required empty public constructor
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        prefs=getResources().getStringArray(R.array.prefs);
        prefvalues=new boolean[2];
        prefvalues[0]=HomeScreen.pref.getBoolean("mute", false);
        prefvalues[1]=HomeScreen.pref.getBoolean("instant-text", false);
        adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_checked,prefs);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
        ListView lv=getListView();

        Toast.makeText(getActivity(),"mute is: "+HomeScreen.pref.getBoolean("mute", false),Toast.LENGTH_LONG).show();

        Toast.makeText(getActivity(),"instant-text is: "+HomeScreen.pref.getBoolean("instant-text", false),Toast.LENGTH_LONG).show();




/*
        for(int i=0;i<lv.getAdapter().getCount();i++)
        {
            CheckedTextView checkedTextView = (CheckedTextView) lv.getAdapter().getView(i,null,lv);
            checkedTextView.setChecked(prefvalues[i]);
            lv.setItemChecked(i,prefvalues[i]);

            Toast.makeText(getActivity(),"checked: "+checkedTextView.isChecked(),Toast.LENGTH_LONG).show();
        }
        setListAdapter(adapter);
        adapter.notifyDataSetChanged();*/
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_preferences, container, false);

        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CheckedTextView checkedTextView = ((CheckedTextView)view);
        checkedTextView.setChecked(!checkedTextView.isChecked());

        if(adapter.getItem(position).contains("Mute Sound"))
        {
            HomeScreen.prefEdit.putBoolean("mute",checkedTextView.isChecked());
            HomeScreen.prefEdit.apply();
            Toast.makeText(getActivity(),"mute is: "+HomeScreen.pref.getBoolean("mute", false),Toast.LENGTH_LONG).show();

        }
        else if(adapter.getItem(position).contains("Auto Save"))
        {
            HomeScreen.prefEdit.putBoolean("instant-text",checkedTextView.isChecked());
            HomeScreen.prefEdit.apply();
            Toast.makeText(getActivity(),"instant-text is: "+HomeScreen.pref.getBoolean("instant-text", false),Toast.LENGTH_LONG).show();

        }


    }

}
