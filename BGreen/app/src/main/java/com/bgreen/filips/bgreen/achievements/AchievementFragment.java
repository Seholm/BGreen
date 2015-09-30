package com.bgreen.filips.bgreen.achievements;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bgreen.filips.bgreen.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AchievementFragment extends Fragment implements View.OnClickListener {

    View myInflatedView;
    List<CircleImageView> circleImageViewList;
    List<Integer> imageList;

    public AchievementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        myInflatedView = inflater.inflate(R.layout.fragment_achievement, container,false);

        circleImageViewList = new ArrayList<>();
        addToCircleImageViewList();
        imageList = new ArrayList<>();
        addToImageList();

        if(circleImageViewList != null && imageList != null){
            for(int i = 0; i < circleImageViewList.size(); i++){
                circleImageViewList.get(i).setClickable(true);
                circleImageViewList.get(i).setOnClickListener(this);
                Picasso.with(this.getContext()).load(imageList.get(i)).into(circleImageViewList.get(i));
            }
        }
        return myInflatedView;
    }

    private void addToCircleImageViewList(){
        circleImageViewList.add((CircleImageView) myInflatedView.findViewById(R.id.achivement_image_01));
        circleImageViewList.add((CircleImageView) myInflatedView.findViewById(R.id.achivement_image_02));
        circleImageViewList.add((CircleImageView) myInflatedView.findViewById(R.id.achivement_image_03));
    }

    private void addToImageList(){
        imageList.add(R.drawable.ment_01);
        imageList.add(R.drawable.emblem);
        imageList.add(R.drawable.achievements);
    }

    @Override
    public void onClick(View v) {
        if (circleImageViewList.get(0).getId() == v.getId()) {
            //System.out.println("************!!!!!!!!!!!*****0101010101010*******!!!!!!!!!!!!!!*************");
            Intent intent = new Intent(this.getActivity(), DetailedAchievementActivity.class);
            intent.putExtra("ACHIEVMENT", 1);
            startActivity(intent);
        }
        if (circleImageViewList.get(1).getId() == v.getId()) {
            //System.out.println("************!!!!!!!!!!!*****0202020202******!!!!!!!!!!!!!!*************");
            Intent intent = new Intent(this.getActivity(), DetailedAchievementActivity.class);
            intent.putExtra("ACHIEVMENT", 2);
            startActivity(intent);
        }
        if (circleImageViewList.get(2).getId() == v.getId()) {
            //System.out.println("************!!!!!!!!!!!*****0303030303******!!!!!!!!!!!!!!*************");
            Intent intent = new Intent(this.getActivity(), DetailedAchievementActivity.class);
            intent.putExtra("ACHIEVMENT", 3);
            startActivity(intent);
        }
    }
}
