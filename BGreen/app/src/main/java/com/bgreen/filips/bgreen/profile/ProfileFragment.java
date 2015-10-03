package com.bgreen.filips.bgreen.profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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

public class ProfileFragment extends Fragment{

    private User user = User.getInstance();

    private CircleImageView circleImageView;
    private TextView profileRankingAndDistance;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myInflatedView = inflater.inflate(R.layout.fragment_profile, container,false);

        profileRankingAndDistance = (TextView) myInflatedView.findViewById(R.id.profile_Ranking_Distance_TextView);

        profileRankingAndDistance.setText("#" + user.getPlacement() + "   Distance: "+ user.getTotalDistance() + "m");

        circleImageView = (CircleImageView) myInflatedView.findViewById(R.id.profile_image);

        //Picasso library loads the image URL and put it into circleImageView
        Picasso.with(this.getActivity())
                .load(changeSizeOnURLImage(user.getImageURL()))
                .into(circleImageView);

        TextView nameTextView = (TextView) myInflatedView.findViewById(R.id.profile_textView);
        nameTextView.setText(user.getFirstName() + " " + user.getLastName());

        Fragment achievementFragment = new AchievementFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.achivement_fragment, achievementFragment).commit();

        return myInflatedView;
    }

    //Change the default size of the image to new size with 150 x 150 px
    private String changeSizeOnURLImage(String s){
        String temp = s.substring(0,s.length()-2);
        temp = temp + "150";
        return temp;
    }
}
