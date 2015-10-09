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



    public ToplistFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setToplist();
        // profiles = ProfileHolder.getInstance().getProfiles();
        myInflatedView = inflater.inflate(R.layout.fragment_toplist, container, false);
        mRecyclerView = (RecyclerView) myInflatedView.findViewById(R.id.toplist_fragment1);
        mswipeRefresh = (SwipeRefreshLayout) myInflatedView.findViewById
                (R.id.toplist_swipe_refresh);
        mswipeRefresh.setOnRefreshListener(this);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());


        return myInflatedView;
    }

    private void setToplist() {
        profiles = ProfileHolder.getInstance().getProfiles();
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ToplistAdapter(profiles,this);
        mRecyclerView.setAdapter(mAdapter);


    }

    @Override
    public void flipCard(int position) {

        if(position>=0) {
            TextView targetProfileName = (TextView) getActivity().findViewById
                    (R.id.targetprofile_name_textView);
            TextView targetProfileDistance = (TextView) getActivity().findViewById
                    (R.id.targetprofile_Ranking_Distance_TextView);
            CircleImageView targetProfilePicture =
                    (CircleImageView)getActivity().findViewById(R.id.targetprofile_image);

            Picasso.with(getContext()).load(profiles.get(position).getImageURL()).into(targetProfilePicture);
            targetProfileDistance.setText("#" + profiles.get(position).getPlacement()
                    + "   Distance: " + profiles.get(position).getTotalDistance() + "m");
            targetProfileName.setText(profiles.get(position).getFirstName() + " " +
                    profiles.get(position).getLastName());
            mswipeRefresh.setEnabled(false);


        }

        View rootLayout = getActivity().findViewById(R.id.toplist_top_container);
        View cardFace = getActivity().findViewById(R.id.toplist_fragment1);
        cardBack = getActivity().findViewById(R.id.targetprofile_framelayout);
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
