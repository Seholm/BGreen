package com.bgreen.filips.bgreen.achievements.model;

/**
 * Interface for Achievement Model
 * Created by Albertsson on 15-10-26.
 */
public interface IAchievement {

    String getImgURL();
    String getTitle();
    String getDescription();
    int getRequirement();
    AchievementCategory getCategory();

    void setImgURL(String imgURL);
    void setTitle(String title);
    void setRequirement(int requirement);
    void setCategory(AchievementCategory category);
    void setDescription(String description);

}
