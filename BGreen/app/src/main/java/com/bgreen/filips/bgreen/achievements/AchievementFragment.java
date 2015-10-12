package com.bgreen.filips.bgreen.achievements;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bgreen.filips.bgreen.R;
import com.bgreen.filips.bgreen.profile.IProfile;
import com.bgreen.filips.bgreen.profile.User;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AchievementFragment extends Fragment implements View.OnClickListener {

    View myInflatedView;
    List<CircleImageView> circleImageViewList;
    List<Integer> imageList;
    List<Integer> imageListLocked;


    Map<String,Boolean> unlockedAchievements;
    Map<String,Double> achievementProgress;
    AchievementModel aModel;


    public AchievementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        myInflatedView = inflater.inflate(R.layout.fragment_achievement, container,false);

        //List with the place where achievementimages gets put
        circleImageViewList = new ArrayList<>();
        addToCircleImageViewList();
        //List with images for unlocked achievements
        imageList = new ArrayList<>();
        addToImageList();
        //List with images for locked achievements
        imageListLocked = new ArrayList<>();
        addToImageListLocked();

        //AchievementModel to use to see which achievements profile have unlocked
        aModel = new AchievementModel();

        if(circleImageViewList != null && imageList != null){
            //Se profiles achievements
            aModel.checkUnlockedAchievements(User.getInstance());
            aModel.checkProgressAchievements(User.getInstance());
            //Map with unlocked and locked achievement
            unlockedAchievements = aModel.getAchievementsUnlocked();
            //Map with progress for achievements
            achievementProgress = aModel.getAchievementsProgress();

            for(int i = 0; i < circleImageViewList.size(); i++){
                circleImageViewList.get(i).setClickable(true);
                circleImageViewList.get(i).setOnClickListener(this);

                String achievement = "Achievement" + (i+1);

                //if there is no more achievements don't load an image
                if(unlockedAchievements.get(achievement)!=null){
                    //image for unlocked achievement
                    if(unlockedAchievements.get(achievement)==true){
                        Picasso.with(this.getContext()).load(imageList.get(i)).into(circleImageViewList.get(i));
                    }
                    //image for locked achievement
                    else{
                        Picasso.with(this.getContext()).load(imageListLocked.get(i)).into(circleImageViewList.get(i));
                    }
                }
            }
        }
        return myInflatedView;
    }

    private void addToCircleImageViewList(){
        circleImageViewList.add((CircleImageView) myInflatedView.findViewById(R.id.achivement_image_01));
        circleImageViewList.add((CircleImageView) myInflatedView.findViewById(R.id.achivement_image_02));
        circleImageViewList.add((CircleImageView) myInflatedView.findViewById(R.id.achivement_image_03));
        circleImageViewList.add((CircleImageView) myInflatedView.findViewById(R.id.achivement_image_04));
        circleImageViewList.add((CircleImageView) myInflatedView.findViewById(R.id.achivement_image_05));
    }

    private void addToImageList(){
        imageList.add(R.drawable.achivement01);
        imageList.add(R.drawable.achivement02);
        imageList.add(R.drawable.achivement03);
        imageList.add(R.drawable.achievements);
        imageList.add(R.drawable.ment_01);
    }

    private void addToImageListLocked(){
        imageListLocked.add(R.drawable.achivement_opac_01);
        imageListLocked.add(R.drawable.achivement_opac_02);
        imageListLocked.add(R.drawable.achivement_opac_03);
        imageListLocked.add(R.drawable.achievements_locked);
        imageListLocked.add(R.drawable.ment_01_locked);
    }

    @Override
    public void onClick(View v) {
        if (circleImageViewList.get(0).getId() == v.getId()) {
            Intent intent = new Intent(this.getActivity(), DetailedAchievementActivity.class);
            intent.putExtra("ACHIEVMENT", 1);
            intent.putExtra("Progress", achievementProgress.get("Achievement1"));
            startActivityForResult(intent, 10);
        }
        if (circleImageViewList.get(1).getId() == v.getId()) {
            Intent intent = new Intent(this.getActivity(), DetailedAchievementActivity.class);
            intent.putExtra("ACHIEVMENT", 2);
            intent.putExtra("Progress", achievementProgress.get("Achievement2"));
            System.out.println(achievementProgress.get("Achievement2"));
            startActivityForResult(intent, 10);
        }
        if (circleImageViewList.get(2).getId() == v.getId()) {
            Intent intent = new Intent(this.getActivity(), DetailedAchievementActivity.class);
            intent.putExtra("ACHIEVMENT", 3);
            intent.putExtra("Progress", achievementProgress.get("Achievement3"));
            startActivityForResult(intent, 10);
        }
        if (circleImageViewList.get(3).getId() == v.getId()) {
            Intent intent = new Intent(this.getActivity(), DetailedAchievementActivity.class);
            intent.putExtra("ACHIEVMENT", 4);
            intent.putExtra("Progress", achievementProgress.get("Achievement4"));
            startActivity(intent);
        }
        if (circleImageViewList.get(4).getId() == v.getId()) {
            Intent intent = new Intent(this.getActivity(), DetailedAchievementActivity.class);
            intent.putExtra("ACHIEVMENT", 5);
            intent.putExtra("Progress", achievementProgress.get("Achievement5"));
            startActivity(intent);
        }
    }


}
