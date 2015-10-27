package com.bgreen.filips.bgreen.achievements.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by medioloco on 2015-10-27.
 */
public class AchievementHolder {
    private static AchievementHolder instance = null;
    private List<IAchievement> achievementList = new ArrayList<>();

    public static AchievementHolder getInstance(){
        if(instance == null){
            instance = new AchievementHolder();
        }
        return instance;
    }

    private AchievementHolder(){

    }

    public void setAchievementList(List<IAchievement> list){
        achievementList.clear();
        achievementList.addAll(list);
    }

    public List<IAchievement> getAchievementList(){
        return this.achievementList;
    }
}
