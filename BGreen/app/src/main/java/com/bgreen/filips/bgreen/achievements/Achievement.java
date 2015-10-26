package com.bgreen.filips.bgreen.achievements;

import android.graphics.drawable.Drawable;

import com.bgreen.filips.bgreen.R;

/**
 * Created by Albertsson on 15-10-26.
 */
public class Achievement implements IAchievement {

    @Override
    public String getAchievementImageURL() {
        return "http://www.market-pie.com/img/apple-touch-icon-144x144-precomposed.png";
    }

    @Override
    public String getAchievementTitle() {
        return "TEST HEADER";
    }

    @Override
    public String getAchievementDescription() {
        return "TEST Här kan det står massa grejjor";
    }

    @Override
    public int getAchievementRequirements() {
        return 300;
    }

    @Override
    public String getAchievementRequirementCategory() {
        return "trips";
    }
}
