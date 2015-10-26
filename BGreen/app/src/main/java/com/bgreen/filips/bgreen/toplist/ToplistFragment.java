package com.bgreen.filips.bgreen.toplist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bgreen.filips.bgreen.R;
import com.bgreen.filips.bgreen.profile.IProfile;
import com.bgreen.filips.bgreen.profile.ITransformer;
import com.bgreen.filips.bgreen.profile.ProfileHolder;
import com.bgreen.filips.bgreen.profile.ProfileService;
import com.bgreen.filips.bgreen.profile.ValueTransformer;
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
    private ITransformer transformer;

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

        transformer = new ValueTransformer();
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
            ProfileService profileService = new ProfileService();
            profileService.fetchAllProfiles();
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

    @Override
    public void flipCard(int position) {

        if(position>=0) {
            TextView targetProfileName = (TextView) myInflatedView.findViewById
                    (R.id.targetprofile_name_textView);
            TextView targetProfileDistance = (TextView) myInflatedView.findViewById
                    (R.id.targetprofile_Ranking_Distance_TextView);
            CircleImageView targetProfilePicture =
                    (CircleImageView)myInflatedView.findViewById(R.id.targetprofile_image);
            TextView targetProfileCarbonCalc =
                    (TextView) myInflatedView.findViewById(R.id.targetprofile_carbon_calculator);

            Picasso.with(getContext()).load(changeSizeOnURLImage(profiles.get(position).
                    getImageURL())).into(targetProfilePicture);
            targetProfileDistance.setText("#" + profiles.get(position).getPlacement() +
                    "  "+"Distans: " + transformer.distanceTransformer(profiles.get(position).getTotalDistance()));
            targetProfileName.setText(profiles.get(position).getFirstName() + " " +
                    profiles.get(position).getLastName());
            targetProfileCarbonCalc.setText(transformer.calculateSpill(profiles.
                    get(position).getTotalDistance()));
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
            Fragment newTopList = new ToplistFragment();
            android.support.v4.app.FragmentManager fm = getActivity().getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.toplist_top_container, newTopList,"hej");
            fragmentTransaction.addToBackStack("tag_toplist_fragment");
            fragmentTransaction.commit();
            mswipeRefresh.setRefreshing(false);
        }
    }

    //Change the default size of the image to new size with 150 x 150 px
    private String changeSizeOnURLImage(String s){
        String temp = s.substring(0,s.length()-2);
        temp = temp + "150";
        return temp;
    }
}
