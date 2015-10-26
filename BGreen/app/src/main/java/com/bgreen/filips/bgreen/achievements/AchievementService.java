package com.bgreen.filips.bgreen.achievements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Albertsson on 15-10-26.
 */
public class AchievementService implements IAchievementService {


    @Override
    public List<IAchievement> getAllAchievements() {
        List<IAchievement> list = new ArrayList<>();
        IAchievement achievement = new Achievement();

        list.add(achievement);
        list.add(achievement);
        list.add(achievement);
        list.add(achievement);
        list.add(achievement);

        return list;
    }
}
