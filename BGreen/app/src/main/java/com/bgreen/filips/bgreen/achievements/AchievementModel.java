package com.bgreen.filips.bgreen.achievements;

import com.bgreen.filips.bgreen.profile.IProfile;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by flarssonn on 2015-09-29.
 */
public class AchievementModel {
    Map<String,Boolean> achievementsUnlocked = new HashMap();
    Map<String,Double> achievementsProgress = new HashMap();

    public void checkUnlockedAchievements(IProfile profile){
        achievementsUnlocked.put("Achievement0", isAchievement1Unlocked(profile));
        achievementsUnlocked.put("Achievement1", isAchievement2Unlocked(profile));
        achievementsUnlocked.put("Achievement2", isAchievement3Unlocked(profile));
        achievementsUnlocked.put("Achievement3", isAchievement4Unlocked(profile));
        achievementsUnlocked.put("Achievement4", isAchievement5Unlocked(profile));
    }
    public void checkProgressAchievements(IProfile profile){
        achievementsProgress.put("Achievement0", progressAchievement1(profile));
        achievementsProgress.put("Achievement1", progressAchievement2(profile));
        achievementsProgress.put("Achievement2", progressAchievement3(profile));
        achievementsProgress.put("Achievement3", progressAchievement4(profile));
        achievementsProgress.put("Achievement4", progressAchievement5(profile));
    }

    public Map<String,Boolean> getAchievementsUnlocked(){
        return achievementsUnlocked;
    }
    public Map<String,Double> getAchievementsProgress(){
        return achievementsProgress;
    }

    //Have the user made one bustrip
    private Boolean isAchievement1Unlocked(IProfile profile){
        if(profile.getBusTrips()>0){
            return true;
        }else{
            return false;
        }
    }

    //Have the user travelled 10 000m
    private Boolean isAchievement2Unlocked(IProfile profile){
        if(profile.getTotalDistance()>=10000){
            return true;
        }else{
            return false;
        }
    }

    //Have the user travelled with the bus atleast 10 times
    private Boolean isAchievement3Unlocked(IProfile profile){
        if(profile.getBusTrips()>=10){
            return true;
        }else{
            return false;
        }
    }

    //Have the user travelled with the bus atleast 55 times
    private Boolean isAchievement4Unlocked(IProfile profile){
        if(profile.getBusTrips()>=55){
            return true;
        }else{
            return false;
        }
    }

    //Have the user travelled 101 000m
    private Boolean isAchievement5Unlocked(IProfile profile){
        if(profile.getTotalDistance()>=101000){
            return true;
        }else{
            return false;
        }
    }

    //Progress for a user in making a bustrip
    private double progressAchievement1(IProfile profile){
        if(profile.getBusTrips()<1.0 ){
            return 0.0;
        }else{
            return 100.0;
        }
    }

    //How far has the user made progress in collecting a total distance of 10 000m
    private double progressAchievement2(IProfile profile){
        if(profile.getTotalDistance()<10000.0 ){
            double progress = profile.getTotalDistance()/10000.0;
            return progress*100.0;
        }else{
            return 100.0;
        }
    }

    //How much progress until 10 trips is completed
    private double progressAchievement3(IProfile profile){
        if(profile.getBusTrips()<10.0 ){
            double progress = profile.getBusTrips()/10.0;
            return progress*100.0;
        }else{
            return 100.0;
        }
    }

    //How much progress until 55 trips is completed
    private double progressAchievement4(IProfile profile){
        if(profile.getBusTrips()<55.0 ){
            double progress = profile.getBusTrips()/55.0;
            return progress*100.0;
        }else{
            return 100.0;
        }
    }

    //How far has the user made progress in collecting a total distance of 101 000m
    private double progressAchievement5(IProfile profile){
        if(profile.getTotalDistance()<101000.0 ){
            double progress = profile.getTotalDistance()/101000.0;
            return progress*100.0;
        }else{
            return 100.0;
        }
    }


}
