package com.bgreen.filips.bgreen.profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bgreen.filips.bgreen.achievements.presenter.AchievementFragment;
import com.bgreen.filips.bgreen.R;
import com.bgreen.filips.bgreen.main.TabActivity;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Field;
import java.util.Observable;
import java.util.Observer;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */

public class ProfileFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{


    private SwipeRefreshLayout profileRefresh;
    private CircleImageView circleImageView;
    private TextView profileRankingAndDistance;
    private TextView nameTextView;
    private TextView profileCarbonCalc;
    private IProfileService profileService;

    private ITransformer transformer;

    private Fragment achievementFragment;

    public ProfileFragment() {

    }

    public static ProfileFragment newInstance(Fragment fragment){

        ProfileFragment newProfileFragment = new ProfileFragment();
        newProfileFragment.achievementFragment = fragment;

        return newProfileFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        profileService = new ProfileService();
        transformer = new ValueTransformer();

        // Inflate the layout for this fragment
        View myInflatedView = inflater.inflate(R.layout.fragment_profile, container,false);
        profileRankingAndDistance = (TextView) myInflatedView.findViewById(R.id.profile_Ranking_Distance_TextView);
        circleImageView = (CircleImageView) myInflatedView.findViewById(R.id.profile_image);
        nameTextView = (TextView) myInflatedView.findViewById(R.id.profile_textView);
        profileCarbonCalc = (TextView) myInflatedView.findViewById(R.id.profile_carbondioxide_calculator);

        //profileRefresh = (SwipeRefreshLayout) myInflatedView.findViewById(R.id.profile_swipe_refresh);
        //profileRefresh.setOnRefreshListener(this);



        if(achievementFragment!=null){

            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.add(R.id.achivement_fragment, achievementFragment).commit();
        }

        profileRefresh = (SwipeRefreshLayout) myInflatedView.findViewById(R.id.profile_swipe_refresh1);
        profileRefresh.setOnRefreshListener(this);
        setProfileText();

        return myInflatedView;
    }



    private void setProfileText() {
        profileService.fetchAllProfiles();
        IUser user = User.getInstance();
        profileRankingAndDistance.setText("#" + user.getPlacement() + "  "+ "Distans: "
                + transformer.distanceTransformer(user.getTotalDistance()));
        nameTextView.setText(user.getFirstName() + " " + user.getLastName());
        profileCarbonCalc.setText(transformer.calculateSpill(user.getTotalDistance()));

        //Picasso library loads the image URL and put it into circleImageView
        Picasso.with(this.getActivity())
                .load(changeSizeOnURLImage(user.getImageURL()))
                .into(circleImageView);
    }

    //Change the default size of the image to new size with 150 x 150 px
    private String changeSizeOnURLImage(String s){
        String temp = s.substring(0,s.length()-2);
        temp = temp + "150";
        return temp;
    }

    @Override
    public void onRefresh() {
        setProfileText();
        profileRefresh.setRefreshing(false);

    }
}
