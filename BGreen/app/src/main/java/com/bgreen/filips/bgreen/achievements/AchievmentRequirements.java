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
}
