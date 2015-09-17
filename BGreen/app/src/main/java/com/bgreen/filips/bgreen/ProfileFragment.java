package com.bgreen.filips.bgreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */

public class ProfileFragment extends Fragment{

    private String profileName;
    private String profileEmail;
    private String profileImgURL;
    private String profileID;

    private String profileRanking = "22";
    private String profileDistance = "1056";

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

        Bundle bundle = this.getArguments();
        this.profileName = bundle.getString("PROFILE_NAME_TO_FRAGMENT");
        this.profileEmail = bundle.getString("PROFILE_EMAIL_TO_FRAGMENT");
        this.profileImgURL = bundle.getString("PROFILE_IMG_URL_TO_FRAGMENT");
        this.profileID = bundle.getString("PROFILE_ID_TO_FRAGMENT");

        profileRankingAndDistance = (TextView) myInflatedView.findViewById(R.id.profile_Ranking_Distance_TextView);

        profileRankingAndDistance.setText("#" + profileRanking + "   Distance: "+ profileDistance + "m");

        circleImageView = (CircleImageView) myInflatedView.findViewById(R.id.profile_image);

        //Bitmap bitmap = DownloadImage("http://gurucul.com/wp-content/uploads/2015/01/default-user-icon-profile.png");

        Picasso.with(this.getActivity())
                .load(changeSizeOnURLImage(profileImgURL))
                .into(circleImageView);

        // Set the Text to try this out
        TextView nameTextView = (TextView) myInflatedView.findViewById(R.id.profile_textView);
        nameTextView.setText(profileName);

        return myInflatedView;
    }

    private String changeSizeOnURLImage(String s){
        String temp = s.substring(0,s.length()-2);
        temp = temp + "150";
        return temp;
    }
}
