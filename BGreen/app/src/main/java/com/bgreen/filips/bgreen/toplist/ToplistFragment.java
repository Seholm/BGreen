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
import com.bgreen.filips.bgreen.profile.model.IProfile;
import com.bgreen.filips.bgreen.profile.model.ITransformer;
import com.bgreen.filips.bgreen.profile.model.ProfileHolder;
import com.bgreen.filips.bgreen.profile.service.ProfileService;
import com.bgreen.filips.bgreen.profile.model.ValueTransformer;
import com.bgreen.filips.bgreen.profile.utils.ErrorHandler;
import com.parse.ParseException;
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
    private ITransformer transformer;                   //value transformer for CO2 and meters
    private boolean flipEnabled = true; // allows click on the toplist
    private List<IProfile> profiles = new ArrayList<>();
    View cardBack; // represents the Layout not currently showing out of toplist and targetprofile
    View cardFace; // represents the Layout currently showing out of toplist and targetprofile

    public ToplistFragment() {
        profiles = new ArrayList<>();
    }

    //Creates a new instance of the fragment with a new list in the bundle
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
            size = bundle.getInt("Storlek"); //Incase bundle == null, do nothing
        }catch(java.lang.NullPointerException e){
        }

        //Creates list depending on if list has been sent when new instance
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
            try {
                profileService.fetchAllProfiles();
            } catch (ParseException e) {
                new ErrorHandler(this.getContext()).displayError(e.getMessage());
            }
            profiles = ProfileHolder.getInstance().getProfiles();
        }
        mLayoutManager = new LinearLayoutManager(getContext());
        myInflatedView = inflater.inflate(R.layout.fragment_toplist, container, false);
        mRecyclerView = (RecyclerView) myInflatedView.findViewById(R.id.toplist_fragment1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ToplistAdapter(profiles,this); //creates a new adapter for the toplist
        mRecyclerView.setAdapter(mAdapter);
        mswipeRefresh = (SwipeRefreshLayout) myInflatedView.findViewById
                (R.id.toplist_swipe_refresh);
        mswipeRefresh.setOnRefreshListener(this);
        mRecyclerView.setHasFixedSize(true);
        return myInflatedView;
    }

    @Override
    public void flipCard(int position) {
        if(flipEnabled==true){
            if(position>=0) { //if there is a click from the toplist
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
                //uses the picasso dependencie to load image into the profile picture imageview
                targetProfileDistance.setText("#" + profiles.get(position).getPlacement() +
                        "  "+"Distans: " + transformer.distanceTransformer(profiles.get(position).getTotalDistance()));
                targetProfileName.setText(profiles.get(position).getFirstName() + " " +
                        profiles.get(position).getLastName());
                targetProfileCarbonCalc.setText(transformer.calculateSpill(profiles.
                        get(position).getTotalDistance()));
                mswipeRefresh.setEnabled(false); // disables the refresh so it isn't actice on the target profile view
            }

            View rootLayout = getActivity().findViewById(R.id.toplist_top_container);
            cardFace= myInflatedView.findViewById(R.id.toplist_fragment1);
            cardBack = myInflatedView.findViewById(R.id.targetprofile_framelayout);
            cardBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //sets onclicklistner on the profile view when it's showing, sends values -1 to set up toplist next click
                    flipCard(-1);
                    mswipeRefresh.setEnabled(true); //enables the swipe when the toplist is about to show
                }
            });

            //does the flip animation
            FlipAnimation flipAnimation = new FlipAnimation(cardFace, cardBack);
            if (cardFace.getVisibility() == View.GONE)
            {
                flipAnimation.reverse();
            }
            rootLayout.startAnimation(flipAnimation);
        }

    }

    //Make refresh on swipe possible, and reload the fragment with the latest
    //profile values from the database.
    @Override
    public void onRefresh() {
        if ( getActivity().findViewById(R.id.toplist_fragment1).getVisibility() == View.VISIBLE) {
            Fragment newTopList = new ToplistFragment();
            android.support.v4.app.FragmentManager fm = getActivity().getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.toplist_top_container, newTopList);
            fragmentTransaction.addToBackStack(null);
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

    //disables flipCard
    public void disableFlip(){
        flipEnabled = false;
    }
    //enables flipCard
    public void enableFlip(){
        flipEnabled = true;
    }
}
