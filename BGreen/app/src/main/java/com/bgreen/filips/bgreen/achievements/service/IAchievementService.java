package com.bgreen.filips.bgreen.achievements.service;

import com.bgreen.filips.bgreen.achievements.model.IAchievement;

import java.util.List;

/**
 * Interface for AchievementService.
 * Created by Albertsson on 15-10-26.
 */
public interface IAchievementService {

    List<IAchievement> getAllAchievements() throws Exception;

}
