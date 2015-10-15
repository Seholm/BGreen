package com.bgreen.filips.bgreen.profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bgreen.filips.bgreen.achievements.AchievementFragment;
import com.bgreen.filips.bgreen.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */

public class ProfileFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    private SwipeRefreshLayout profileRefresh;
    private CircleImageView circleImageView;
    private TextView profileRankingAndDistance;
    private TextView nameTextView;
    private TextView profileCarbonCalc;
    private ProfileService profileService;
    private CarbonDioxideCalculator carbonCalculator;
    private DistanceTransformer distanceTransformer;

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        profileService = new ProfileService();
        carbonCalculator = new CarbonDioxideCalculator();
        distanceTransformer = new DistanceTransformer();

        // Inflate the layout for this fragment
        View myInflatedView = inflater.inflate(R.layout.fragment_profile, container,false);
        profileRankingAndDistance = (TextView) myInflatedView.findViewById(R.id.profile_Ranking_Distance_TextView);
        circleImageView = (CircleImageView) myInflatedView.findViewById(R.id.profile_image);
        nameTextView = (TextView) myInflatedView.findViewById(R.id.profile_textView);
        profileCarbonCalc = (TextView) myInflatedView.findViewById(R.id.profile_carbondioxide_calculator);

        profileRefresh = (SwipeRefreshLayout) myInflatedView.findViewById
                (R.id.profile_swipe_refresh);
        profileRefresh.setOnRefreshListener(this);

        Fragment achievementFragment = new AchievementFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.achivement_fragment, achievementFragment).commit();
        setProfileText();

        return myInflatedView;
    }

    private void setProfileText() {
        profileService.fetchAllProfiles();
        User user = User.getInstance();
        profileRankingAndDistance.setText("#" + user.getPlacement() + "  "+ "Distans: "
                + user.getTotalDistance() + "m");
        nameTextView.setText(user.getFirstName() + " " + user.getLastName());
        profileCarbonCalc.setText(carbonCalculator.calculateSpill(user.getTotalDistance()));

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
