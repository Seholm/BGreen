package com.bgreen.filips.bgreen.toplist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bgreen.filips.bgreen.R;
import com.bgreen.filips.bgreen.profile.IProfile;
import com.bgreen.filips.bgreen.profile.Profile;
import java.util.ArrayList;
import java.util.List;

public class ToplistFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private View myInflatedView;

    private List<IProfile> profiles = new ArrayList<IProfile>();

    public ToplistFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myInflatedView = inflater.inflate(R.layout.fragment_toplist, container, false);
        mRecyclerView = (RecyclerView) myInflatedView.findViewById(R.id.toplist_fragment1);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ToplistAdapter(profiles);
        mRecyclerView.setAdapter(mAdapter);
        return myInflatedView;
    }
}
