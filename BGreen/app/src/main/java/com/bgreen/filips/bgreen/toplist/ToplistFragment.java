package com.bgreen.filips.bgreen.toplist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bgreen.filips.bgreen.R;
import com.bgreen.filips.bgreen.profile.Profile;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
//herman med scarlmans hjälp
public class ToplistFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private View myInflatedView;

    private List<Profile> profiles = new ArrayList<Profile>();
    Profile test1 = new Profile("herman", "carlström", "herman@com", "", 5000, 5);
    Profile test2 = new Profile("herman2", "carlström2", "herman@com", "", 5002, 4);
    Profile test3 = new Profile("herman3", "carlström3", "herman@com", "", 5003, 5);

    public ToplistFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myInflatedView = inflater.inflate(R.layout.fragment_toplist, container, false);
        profiles.add(test1);
        profiles.add(test2);
        profiles.add(test3);

        mRecyclerView = (RecyclerView) myInflatedView.findViewById(R.id.toplist_fragment1);
            // Inflate the layout for this fragment
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ToplistAdapter(profiles);
        mRecyclerView.setAdapter(mAdapter);
        return myInflatedView;
    }


}
