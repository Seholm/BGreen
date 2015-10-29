package com.bgreen.filips.bgreen.test.achievementsTest;

import com.bgreen.filips.bgreen.achievements.model.AchievementCategory;
import com.bgreen.filips.bgreen.achievements.model.IAchievement;

/**
 * Created by Filips on 10/27/2015.
 */
//A mockachievment so that tests on achievement can be done with a fake achievement
public class MockAchivment1 implements IAchievement {

    private AchievementCategory achievementCategory;
    private int requirements;

    @Override
    public String getImgURL() {
        return null;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public int getRequirement() {
        return requirements;
    }

    @Override
    public AchievementCategory getCategory() {
        return achievementCategory;
    }

    @Override
    public void setImgURL(String imgURL) {

    }

    @Override
    public void setTitle(String title) {

    }

    @Override
    public void setRequirement(int requirement) {
        this.requirements = requirement;
    }

    @Override
    public void setCategory(AchievementCategory category) {
        this.achievementCategory = category;
    }

    @Override
    public void setDescription(String description) {

    }
}
