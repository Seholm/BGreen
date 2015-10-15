package com.bgreen.filips.bgreen.achievements;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bgreen.filips.bgreen.R;
import com.bgreen.filips.bgreen.profile.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class AchievementFragment extends Fragment implements View.OnClickListener {

    View myInflatedView;
    List<ImageView> imageViewList;
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
        imageViewList = new ArrayList<>();
        addToImageViewList();
        //List with images for unlocked achievements
        imageList = new ArrayList<>();
        addToImageList();
        //List with images for locked achievements
        imageListLocked = new ArrayList<>();
        addToImageListLocked();

        //AchievementModel to use to see which achievements profile have unlocked
        aModel = new AchievementModel();

        if(imageViewList != null && imageList != null){
            //Se profiles achievements
            aModel.checkUnlockedAchievements(User.getInstance());
            aModel.checkProgressAchievements(User.getInstance());
            //Map with unlocked and locked achievement
            unlockedAchievements = aModel.getAchievementsUnlocked();
            //Map with progress for achievements
            achievementProgress = aModel.getAchievementsProgress();

            for(int i = 0; i < imageViewList.size(); i++){
                imageViewList.get(i).setClickable(true);
                imageViewList.get(i).setOnClickListener(this);

                String achievement = "Achievement" + (i+1);

                //if there is no more achievements don't load an image
                if(unlockedAchievements.get(achievement)!=null){
                    //image for unlocked achievement
                    if(unlockedAchievements.get(achievement)==true){
                        //Picasso.with(this.getContext()).load(imageList.get(i)).into(imageViewList.get(i));
                        imageViewList.get(i).setImageResource(imageList.get(i));
                    }
                    //image for locked achievement
                    else{
                        //Picasso.with(this.getContext()).load(imageListLocked.get(i)).into(imageViewList.get(i));
                        imageViewList.get(i).setImageResource(imageListLocked.get(i));
                        imageViewList.get(i).setAlpha(0.3f);
                    }
                }
            }
        }
        return myInflatedView;
    }

    private void addToImageViewList(){
        //imageViewList.add((CircleImageView) myInflatedView.findViewById(R.id.achivement_image_01));
        //imageViewList.add((CircleImageView) myInflatedView.findViewById(R.id.achivement_image_02));
        //imageViewList.add((CircleImageView) myInflatedView.findViewById(R.id.achivement_image_03));
        //imageViewList.add((CircleImageView) myInflatedView.findViewById(R.id.achivement_image_04));
        //imageViewList.add((CircleImageView) myInflatedView.findViewById(R.id.achivement_image_05));
        imageViewList.add((ImageView) myInflatedView.findViewById(R.id.achivement_imageView_01));
        imageViewList.add((ImageView) myInflatedView.findViewById(R.id.achivement_imageView_02));
        imageViewList.add((ImageView) myInflatedView.findViewById(R.id.achivement_imageView_03));
        imageViewList.add((ImageView) myInflatedView.findViewById(R.id.achivement_imageView_04));
        imageViewList.add((ImageView) myInflatedView.findViewById(R.id.achivement_imageView_05));

    }

    private void addToImageList(){
        imageList.add(R.drawable.coffee_cup);
        imageList.add(R.drawable.check_mark);
        imageList.add(R.drawable.air_plane);
        imageList.add(R.drawable.fify_five);
        imageList.add(R.drawable.road_sign);
        //imageList.add(R.drawable.achievements);
        //imageList.add(R.drawable.ment_01);
    }

    private void addToImageListLocked(){
        imageListLocked.add(R.drawable.coffee_cup_locked);
        imageListLocked.add(R.drawable.check_mark_locked);
        imageListLocked.add(R.drawable.air_plane_locked);
        imageListLocked.add(R.drawable.fify_five_locked);
        imageListLocked.add(R.drawable.road_sign_locked);
        //imageListLocked.add(R.drawable.achievements_locked);
        //imageListLocked.add(R.drawable.ment_01_locked);
    }

    @Override
    public void onClick(View v) {
        if (imageViewList.get(0).getId() == v.getId()) {
            Intent intent = new Intent(this.getActivity(), DetailedAchievementActivity.class);
            intent.putExtra("ACHIEVMENT", 1);
            intent.putExtra("Progress", achievementProgress.get("Achievement1"));
            startActivityForResult(intent, 10);
        }
        if (imageViewList.get(1).getId() == v.getId()) {
            Intent intent = new Intent(this.getActivity(), DetailedAchievementActivity.class);
            intent.putExtra("ACHIEVMENT", 2);
            intent.putExtra("Progress", achievementProgress.get("Achievement2"));
            System.out.println(achievementProgress.get("Achievement2"));
            startActivityForResult(intent, 10);
        }
        if (imageViewList.get(2).getId() == v.getId()) {
            Intent intent = new Intent(this.getActivity(), DetailedAchievementActivity.class);
            intent.putExtra("ACHIEVMENT", 3);
            intent.putExtra("Progress", achievementProgress.get("Achievement3"));
            startActivityForResult(intent, 10);
        }
        if (imageViewList.get(3).getId() == v.getId()) {
            Intent intent = new Intent(this.getActivity(), DetailedAchievementActivity.class);
            intent.putExtra("ACHIEVMENT", 4);
            intent.putExtra("Progress", achievementProgress.get("Achievement4"));
            startActivityForResult(intent,10);
        }
        if (imageViewList.get(4).getId() == v.getId()) {
            Intent intent = new Intent(this.getActivity(), DetailedAchievementActivity.class);
            intent.putExtra("ACHIEVMENT", 5);
            intent.putExtra("Progress", achievementProgress.get("Achievement5"));
            startActivityForResult(intent,10);
        }
    }
}
