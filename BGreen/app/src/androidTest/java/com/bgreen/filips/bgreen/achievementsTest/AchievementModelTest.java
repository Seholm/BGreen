package com.bgreen.filips.bgreen.achievementsTest;

import android.test.InstrumentationTestCase;

import com.bgreen.filips.bgreen.achievements.AchievementModel;
import com.bgreen.filips.bgreen.profile.IProfile;
import com.bgreen.filips.bgreen.profile.Profile;
import com.bgreen.filips.bgreen.profile.ProfileService;
import com.bgreen.filips.bgreen.profile.User;

import java.util.Map;

/**
 * Created by flarssonn on 2015-09-29.
 */
public class AchievementModelTest extends InstrumentationTestCase {

    //test to assert that achievement1 is not unlocked when bustrips is 0
    public void testAchievement1Unlocked1(){
        AchievementModel testModel = new AchievementModel();
        IProfile profile = User.getInstance();
        profile.setBusTrips(0);
        profile.setTotalDistance(0);
        testModel.checkUnlockedAchievements(profile);
        Map<String,Boolean> achievementsUnlocked = testModel.getAchievementsUnlocked();
        assertFalse(achievementsUnlocked.get("Achievement1"));
    }

    //test to assert that achievement1 is unlocked when bustrips is > 1
    public void testAchievement1Unlocked2(){
        AchievementModel testModel = new AchievementModel();
        IProfile profile = User.getInstance();
        profile.setBusTrips(4);
        profile.setTotalDistance(5000);
        testModel.checkUnlockedAchievements(profile);
        Map<String,Boolean> achievementsUnlocked = testModel.getAchievementsUnlocked();
        assertTrue(achievementsUnlocked.get("Achievement1"));
    }

    //test to assert that achievement1 has progress 0
    public void testAchievement1Progress1(){
        AchievementModel testModel = new AchievementModel();
        IProfile profile = User.getInstance();
        profile.setBusTrips(0);
        profile.setTotalDistance(0);
        testModel.checkProgressAchievements(profile);
        Map<String,Double> achievementsProgress = testModel.getAchievementsProgress();
        assertEquals(achievementsProgress.get("Achievement1"), 0.0);
    }

    //test to assert that achievement1 has progress 1
    public void testAchievement1Progress2(){
        AchievementModel testModel = new AchievementModel();
        IProfile profile = User.getInstance();
        profile.setBusTrips(1);
        profile.setTotalDistance(5000);
        testModel.checkProgressAchievements(profile);
        Map<String,Double> achievementsProgress = testModel.getAchievementsProgress();
        assertEquals(achievementsProgress.get("Achievement1"), 100.0);
    }

    //test to assert that achievement2 is not unlocked when totdistance is less than 10 000m
    public void testAchievement2Unlocked1(){
        AchievementModel testModel = new AchievementModel();
        IProfile profile = User.getInstance();
        profile.setBusTrips(4);
        profile.setTotalDistance(5000);
        testModel.checkUnlockedAchievements(profile);
        Map<String,Boolean> achievementsUnlocked = testModel.getAchievementsUnlocked();
        assertFalse(achievementsUnlocked.get("Achievement2"));
    }

    //test to assert that achievement2 is unlocked when totdistance is >= 10 000m
    public void testAchievement2Unlocked2(){
        AchievementModel testModel = new AchievementModel();
        IProfile profile = User.getInstance();
        profile.setBusTrips(4);
        profile.setTotalDistance(10000);
        testModel.checkUnlockedAchievements(profile);
        Map<String,Boolean> achievementsUnlocked = testModel.getAchievementsUnlocked();
        assertTrue(achievementsUnlocked.get("Achievement2"));
    }

    //test to assert that achievement2 has progress 0,5
    public void testAchievement2Progress1(){
        AchievementModel testModel = new AchievementModel();
        IProfile profile = User.getInstance();
        profile.setBusTrips(4);
        profile.setTotalDistance(5000);
        testModel.checkProgressAchievements(profile);
        Map<String,Double> achievementsProgress = testModel.getAchievementsProgress();
        assertEquals(achievementsProgress.get("Achievement2"), 50.0);
    }

    //test to assert that achievement2 has progress 1
    public void testAchievement2Progress2(){
        AchievementModel testModel = new AchievementModel();
        IProfile profile = User.getInstance();
        profile.setBusTrips(4);
        profile.setTotalDistance(10000);
        testModel.checkProgressAchievements(profile);
        Map<String,Double> achievementsProgress = testModel.getAchievementsProgress();
        assertEquals(achievementsProgress.get("Achievement2"), 100.0);
    }

    //test to assert that achievement3 is not unlocked when 10 trips isn't completed
    public void testAchievement3Unlocked1(){
        AchievementModel testModel = new AchievementModel();
        IProfile profile = User.getInstance();
        profile.setBusTrips(0);
        profile.setTotalDistance(0);
        testModel.checkUnlockedAchievements(profile);
        Map<String,Boolean> achievementsUnlocked = testModel.getAchievementsUnlocked();
        assertFalse(achievementsUnlocked.get("Achievement3"));
    }

    //test to assert that achievement3 is unlocked when 10 trips is completed
    public void testAchievement3Unlocked2(){
        AchievementModel testModel = new AchievementModel();
        IProfile profile = User.getInstance();
        profile.setBusTrips(10);
        profile.setTotalDistance(3000);
        testModel.checkUnlockedAchievements(profile);
        Map<String,Boolean> achievementsUnlocked = testModel.getAchievementsUnlocked();
        assertTrue(achievementsUnlocked.get("Achievement3"));
    }

    //test to assert that achievement3 has progress 0.3
    public void testAchievement3Progress1(){
        AchievementModel testModel = new AchievementModel();
        IProfile profile = User.getInstance();
        profile.setBusTrips(3);
        profile.setTotalDistance(1000);
        testModel.checkProgressAchievements(profile);
        Map<String,Double> achievementsProgress = testModel.getAchievementsProgress();
        assertEquals(achievementsProgress.get("Achievement3"), 30.0);
    }

    //test to assert that achievement3 has progress 1.0
    public void testAchievement3Progress2(){
        AchievementModel testModel = new AchievementModel();
        IProfile profile = User.getInstance();
        profile.setBusTrips(10);
        profile.setTotalDistance(1000);
        testModel.checkProgressAchievements(profile);
        Map<String,Double> achievementsProgress = testModel.getAchievementsProgress();
        assertEquals(achievementsProgress.get("Achievement3"), 100.0);
    }



}
