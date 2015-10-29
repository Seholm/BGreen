package com.bgreen.filips.bgreen.achievements.presenter;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bgreen.filips.bgreen.R;
import com.bgreen.filips.bgreen.achievements.model.AchievementHolder;
import com.bgreen.filips.bgreen.achievements.model.IAchievement;
import com.bgreen.filips.bgreen.achievements.service.AchievementService;
import com.bgreen.filips.bgreen.achievements.service.IAchievementService;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AchievementFragment extends Fragment implements IDisplayAchivment {

    private View myInflatedView;
    private IAchievementService achievementService = new AchievementService();

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private AchievementAdapter adapter;
    private AchievementHolder achievementHolder = AchievementHolder.getInstance();

    public AchievementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        myInflatedView = inflater.inflate(R.layout.fragment_achievement, container, false);

        try {
            achievementHolder.setAchievementList(achievementService.getAllAchievements());
        }catch (Exception e){

        }

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView = (RecyclerView) myInflatedView.findViewById(R.id.recycler_view_achievement);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AchievementAdapter(achievementHolder.getAchievementList(), this);
        recyclerView.setAdapter(adapter);

        return myInflatedView;
    }

    public void displayAchievement(int achivement){

        Intent intent = new Intent(this.getActivity(), DetailedAchievementActivity.class);

        intent.putExtra("ACHIEVMENT", achivement);
        startActivityForResult(intent, 10);
    }
 }





