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



public class HomeScreenFragment extends Fragment {

    RecyclerView rvGameModes;

    private GameModeAdapter mAdapter;
    private LinearLayoutManager myLinearLayoutManager;

    public HomeScreenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home_screen, container, false);
        rvGameModes=(RecyclerView) view.findViewById(R.id.home_screen_recycler_view);
        myLinearLayoutManager=new LinearLayoutManager(getActivity());
        rvGameModes.setLayoutManager(myLinearLayoutManager);
        mAdapter=new GameModeAdapter(HomeScreen.gameModeRoster,getActivity());
        rvGameModes.setAdapter(mAdapter);
        SnapHelper snapHelper=new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rvGameModes);

        return view;
    }

}
