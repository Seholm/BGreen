package com.bgreen.filips.bgreen.achievements.model;

import com.bgreen.filips.bgreen.achievements.model.AchievementCategory;
import com.bgreen.filips.bgreen.achievements.model.IAchievement;
import com.bgreen.filips.bgreen.achievements.model.IAchievmentRequirements;
import com.bgreen.filips.bgreen.profile.IProfile;

/**
 * Created by Filips on 10/26/2015.
 */
public class AchievmentRequirements implements IAchievmentRequirements {

    public boolean checkAchivment(IProfile profile,IAchievement achievement){
        if(achievement.getCategory() == AchievementCategory.DISTANCE){
            if(profile.getTotalDistance()>=achievement.getRequirement()){
                return true;
            }else{
                return false;
            }

        }if(achievement.getCategory() == AchievementCategory.TRIPS){
            if(profile.getBusTrips()>=achievement.getRequirement()){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public double checkAchivmentProgress(IProfile profile,IAchievement achievement) {
        if (achievement.getCategory() == AchievementCategory.DISTANCE) {
            if (profile.getTotalDistance() < achievement.getRequirement()) {
                double progress = (double)profile.getTotalDistance() / (double)achievement.getRequirement();
                return progress * 100.0;
            } else {
                return 100.0;
            }

        }
        if (achievement.getCategory() == AchievementCategory.TRIPS) {
            if (profile.getBusTrips() < achievement.getRequirement()) {
                double progress = (double)profile.getBusTrips() / (double)achievement.getRequirement();
                return progress * 100.0;
            } else {
                return 100.0;
            }
        }
        return 100;
    }
}
