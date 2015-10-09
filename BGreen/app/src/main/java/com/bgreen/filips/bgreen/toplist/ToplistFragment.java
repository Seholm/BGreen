package com.bgreen.filips.bgreen.toplist;



import android.app.Application;
import android.content.DialogInterface;

import android.graphics.drawable.Drawable;
import android.net.IpPrefix;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bgreen.filips.bgreen.R;
import com.bgreen.filips.bgreen.main.TabActivity;
import com.bgreen.filips.bgreen.profile.IProfile;
import com.bgreen.filips.bgreen.profile.ProfileHolder;
import com.bgreen.filips.bgreen.profile.ProfileService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


import de.hdodenhof.circleimageview.CircleImageView;

public class ToplistFragment extends Fragment implements IFlipcard, SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mswipeRefresh;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private View myInflatedView;

    private List<IProfile> profiles = new ArrayList<>();
    View cardBack;
    View cardFace;






    public ToplistFragment() {
        profiles = new ArrayList<>();


    }

    public static ToplistFragment newInstance(Bundle bundle){
        ToplistFragment newTopListFragment= new ToplistFragment();
        newTopListFragment.setArguments(bundle);

        return newTopListFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //setToplist();
        // profiles = ProfileHolder.getInstance().getProfiles();



        //profiles = ProfileHolder.getInstance().getProfiles();


        Bundle bundle = getArguments();
        int size=0;
        try{
            size = bundle.getInt("Storlek");
        }catch(java.lang.NullPointerException e){


        }

        if(size != 0){

            profiles.clear();
            List<IProfile> profileList = new ArrayList<IProfile>();
            for(int i=0; i<size; i++){
                IProfile profil = bundle.getParcelable(""+i);
                profileList.add(profil);

            }
            profiles = profileList;


        }else{
            profiles = ProfileHolder.getInstance().getProfiles();
        }





        mLayoutManager = new LinearLayoutManager(getContext());
        myInflatedView = inflater.inflate(R.layout.fragment_toplist, container, false);

        mRecyclerView = (RecyclerView) myInflatedView.findViewById(R.id.toplist_fragment1);

        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ToplistAdapter(profiles,this);

        mRecyclerView.setAdapter(mAdapter);

        mswipeRefresh = (SwipeRefreshLayout) myInflatedView.findViewById
                (R.id.toplist_swipe_refresh);
        mswipeRefresh.setOnRefreshListener(this);
        mRecyclerView.setHasFixedSize(true);



        return myInflatedView;
    }
/*
    private void setToplist() {
        profiles = ProfileHolder.getInstance().getProfiles();


        mLayoutManager = new LinearLayoutManager(getContext());


        mRecyclerView.setLayoutManager(mLayoutManager);


        mAdapter = new ToplistAdapter(profiles,this);

        mRecyclerView.setAdapter(mAdapter);


    }*/


    @Override
    public void flipCard(int position) {

        if(position>=0) {
            TextView targetProfileName = (TextView) myInflatedView.findViewById
                    (R.id.targetprofile_name_textView);
            TextView targetProfileDistance = (TextView) myInflatedView.findViewById
                    (R.id.targetprofile_Ranking_Distance_TextView);
            CircleImageView targetProfilePicture =
                    (CircleImageView)myInflatedView.findViewById(R.id.targetprofile_image);

            Picasso.with(getContext()).load(profiles.get(position).getImageURL()).into(targetProfilePicture);
            targetProfileDistance.setText("#" + profiles.get(position).getPlacement()
                    + "   Distance: " + profiles.get(position).getTotalDistance() + "m");
            targetProfileName.setText(profiles.get(position).getFirstName() + " " +
                    profiles.get(position).getLastName());
            mswipeRefresh.setEnabled(false);



        }

        View rootLayout = getActivity().findViewById(R.id.toplist_top_container);
        cardFace= myInflatedView.findViewById(R.id.toplist_fragment1);
        //cardFace = getActivity().findViewById(R.id.toplist_fragment1);
        cardBack = myInflatedView.findViewById(R.id.targetprofile_framelayout);
        //cardBack = getActivity().findViewById(R.id.targetprofile_framelayout);
        cardBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipCard(-1);
                mswipeRefresh.setEnabled(true);
            }
        });


        FlipAnimation flipAnimation = new FlipAnimation(cardFace, cardBack);

        if (cardFace.getVisibility() == View.GONE)
        {
            flipAnimation.reverse();
        }
        rootLayout.startAnimation(flipAnimation);

    }


    @Override
    public void onRefresh() {
        if ( getActivity().findViewById(R.id.toplist_fragment1).getVisibility() == View.VISIBLE) {
            ProfileService profileService = new ProfileService();
            profileService.fetchAllProfiles();
            profiles.clear();
            mswipeRefresh.setRefreshing(false);
        }
    }

}
