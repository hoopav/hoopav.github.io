package edu.mnstate.gv4940sr.vangfinalproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class UnitDisplayFragment extends Fragment {

    RecyclerView rvUnits;

    private UnitAdapter mAdapter;
    private LinearLayoutManager myLinearLayoutManager;

    public UnitDisplayFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_unit_display, container, false);

        rvUnits=(RecyclerView) view.findViewById(R.id.unit_display_recycler_view);
        myLinearLayoutManager = new LinearLayoutManager(getActivity());
        rvUnits.setLayoutManager(myLinearLayoutManager);
        mAdapter=new UnitAdapter(HomeScreen.unitRoster,getActivity());
        rvUnits.setAdapter(mAdapter);
        SnapHelper snapHelper=new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rvUnits);
        return view;
    }

}
