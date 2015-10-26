package com.bgreen.filips.bgreen.achievements;

import com.bgreen.filips.bgreen.profile.IProfile;

import java.util.List;

/**
 * Created by Filips on 10/26/2015.
 */
public class AchievmentRequirements {

    public boolean checkAchivment(IProfile profile,IAchievement achievement){
        if(achievement.getAchievementRequirementCategory().equals("distance")){
            if(profile.getTotalDistance()>=achievement.getAchievementRequirements()){
                return true;
            }else{
                return false;
            }

        }if(achievement.getAchievementRequirementCategory().equals("trips")){
            if(profile.getBusTrips()>=achievement.getAchievementRequirements()){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public double checkAchivmentProgress(IProfile profile,IAchievement achievement) {
        if (achievement.getAchievementRequirementCategory().equals("distance")) {
            if (profile.getTotalDistance() < achievement.getAchievementRequirements()) {
                double progress = (double)profile.getTotalDistance() / (double)achievement.getAchievementRequirements();
                return progress * 100.0;
            } else {
                return 100.0;
            }

        }
        if (achievement.getAchievementRequirementCategory().equals("trips")) {
            if (profile.getBusTrips() < achievement.getAchievementRequirements()) {
                double progress = (double)profile.getBusTrips() / (double)achievement.getAchievementRequirements();
                return progress * 100.0;
            } else {
                return 100.0;
            }
        }
        return 100;
    }


}
