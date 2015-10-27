package com.bgreen.filips.bgreen.test.achievementsTest;

import android.test.InstrumentationTestCase;

import com.bgreen.filips.bgreen.achievements.model.AchievementCategory;
import com.bgreen.filips.bgreen.achievements.model.AchievmentRequirements;
import com.bgreen.filips.bgreen.achievements.model.IAchievement;
import com.bgreen.filips.bgreen.profile.model.IProfile;
import com.bgreen.filips.bgreen.profile.model.User;
import com.bgreen.filips.bgreen.test.searchTest.MockProfile;

/**
 * Created by flarssonn on 2015-09-29.
 */
public class AchievementModelTest extends InstrumentationTestCase {

    //test when an achivment shouldn't be unlocked with distance
    public void testAchievement1Unlocked1(){
        AchievmentRequirements testModel = new AchievmentRequirements();
        IProfile profile = new MockProfile();
        profile.setBusTrips(0);
        profile.setTotalDistance(0);
        IAchievement achievement = new MockAchivment1();
        achievement.setCategory(AchievementCategory.DISTANCE);
        achievement.setRequirement(1000);
        assertFalse(testModel.checkAchivment(profile,achievement));
    }

    //test when an achivment should be unlocked with distance
    public void testAchievement1Unlocked2(){
        AchievmentRequirements testModel = new AchievmentRequirements();
        IProfile profile = new MockProfile();
        profile.setBusTrips(0);
        profile.setTotalDistance(5001);
        IAchievement achievement = new MockAchivment1();
        achievement.setCategory(AchievementCategory.DISTANCE);
        achievement.setRequirement(5000);
        assertTrue(testModel.checkAchivment(profile, achievement));
    }

    //test when an achivment shouldn't be unlocked with trips
    public void testAchievement1Unlocked3(){
        AchievmentRequirements testModel = new AchievmentRequirements();
        IProfile profile = new MockProfile();
        profile.setBusTrips(0);
        profile.setTotalDistance(10000);
        IAchievement achievement = new MockAchivment1();
        achievement.setCategory(AchievementCategory.TRIPS);
        achievement.setRequirement(7);
        assertFalse(testModel.checkAchivment(profile,achievement));
    }

    //test when an achivment should be unlocked with trips
    public void testAchievement1Unlocked4(){
        AchievmentRequirements testModel = new AchievmentRequirements();
        IProfile profile = new MockProfile();
        profile.setBusTrips(7);
        profile.setTotalDistance(0);
        IAchievement achievement = new MockAchivment1();
        achievement.setCategory(AchievementCategory.TRIPS);
        achievement.setRequirement(7);
        assertTrue(testModel.checkAchivment(profile,achievement));
    }

    //test if progress works for Distance
    public void testAchievementProgress1(){
        AchievmentRequirements testModel = new AchievmentRequirements();
        IProfile profile = new MockProfile();
        profile.setBusTrips(7);
        profile.setTotalDistance(50000);
        IAchievement achievement = new MockAchivment1();
        achievement.setCategory(AchievementCategory.DISTANCE);
        achievement.setRequirement(100000);
        assertTrue(50 == testModel.checkAchivmentProgress(profile, achievement));
    }

    //test if progress works for Trips
    public void testAchievementProgress2(){
        AchievmentRequirements testModel = new AchievmentRequirements();
        IProfile profile = new MockProfile();
        profile.setBusTrips(7);
        profile.setTotalDistance(50000);
        IAchievement achievement = new MockAchivment1();
        achievement.setCategory(AchievementCategory.TRIPS);
        achievement.setRequirement(10);
        assertTrue(70==testModel.checkAchivmentProgress(profile,achievement));
    }


}
