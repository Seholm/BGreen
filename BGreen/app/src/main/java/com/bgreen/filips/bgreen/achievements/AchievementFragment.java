package com.bgreen.filips.bgreen.achievements;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bgreen.filips.bgreen.R;
import com.bgreen.filips.bgreen.profile.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class AchievementFragment extends Fragment {

    private View myInflatedView;
    private List<IAchievement> achievementList = new ArrayList<>();
    private IAchievementService achievementService = new AchievementService();

    private Map<String,Boolean> unlockedAchievements;
    private Map<String,Double> achievementProgress;
    private AchievementModel aModel;

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private AchievementAdapter adapter;

    public AchievementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        myInflatedView = inflater.inflate(R.layout.fragment_achievement, container,false);

        //*!*!*!*!*! TEST *!*!*!*!*!*
        achievementList = achievementService.getAllAchievements();
        checkAchievements();

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView = (RecyclerView) myInflatedView.findViewById(R.id.recycler_view_achievement);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AchievementAdapter(achievementList, this, unlockedAchievements, achievementProgress);
        recyclerView.setAdapter(adapter);

        /*
        //List with the place where achievementimages gets put
        imageViewList = new ArrayList<>();
        addToImageViewList();
        //List with images for unlocked achievementList
        imageList = new ArrayList<>();
        addToImageList();
        //List with images for locked achievementList
        imageListLocked = new ArrayList<>();
        addToImageListLocked();
        */

        return myInflatedView;
    }
/*
    private void addToImageViewList(){
        imageViewList.add((ImageView) myInflatedView.findViewById(R.id.achivement_imageView_01));
        imageViewList.add((ImageView) myInflatedView.findViewById(R.id.achivement_imageView_02));
        imageViewList.add((ImageView) myInflatedView.findViewById(R.id.achivement_imageView_03));
        imageViewList.add((ImageView) myInflatedView.findViewById(R.id.achivement_imageView_04));
        imageViewList.add((ImageView) myInflatedView.findViewById(R.id.achivement_imageView_05));

    }*/

    public void displayAchievement(int achivement){

        Intent intent = new Intent(this.getActivity(), DetailedAchievementActivity.class);

        intent.putExtra("ACHIEVMENT", achivement);
        startActivityForResult(intent, 10);
    }

    public void checkAchievements(){
        //AchievementModel to use to see which achievementList profile have unlocked
        aModel = new AchievementModel();

        if(achievementList != null){
            //Se profiles achievementList
            aModel.checkUnlockedAchievements(User.getInstance());
            aModel.checkProgressAchievements(User.getInstance());
            //Map with unlocked and locked achievement
            unlockedAchievements = aModel.getAchievementsUnlocked();
            //Map with progress for achievementList
            achievementProgress = aModel.getAchievementsProgress();
        }
    }

    /*@Override
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
    }*/
}
