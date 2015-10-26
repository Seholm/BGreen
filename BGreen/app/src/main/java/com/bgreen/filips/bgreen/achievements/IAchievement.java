package com.bgreen.filips.bgreen.achievements;

import android.graphics.drawable.Drawable;

/**
 * Created by Albertsson on 15-10-26.
 */
public interface IAchievement {

    String getAchievementImageURL();
    String getAchievementTitle();
    String getAchievementDescription();
    int getAchievementRequirements();
    String getAchievementRequirementCategory();

}
