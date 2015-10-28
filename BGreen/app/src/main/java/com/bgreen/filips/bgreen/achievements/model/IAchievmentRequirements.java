package com.bgreen.filips.bgreen.achievements.model;

import com.bgreen.filips.bgreen.profile.model.IProfile;

/**
 * Created by Filips on 10/27/2015.
 */
public interface IAchievmentRequirements {
    boolean checkAchivment(IProfile profile,IAchievement achievement);
    double checkAchivmentProgress(IProfile profile,IAchievement achievement);
}
