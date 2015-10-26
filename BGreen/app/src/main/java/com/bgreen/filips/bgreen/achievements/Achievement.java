package com.bgreen.filips.bgreen.achievements;

import android.graphics.drawable.Drawable;

import com.bgreen.filips.bgreen.R;

/**
 * Created by medioloco on 15-10-26.
 */
public class Achievement implements IAchievement {

    private String  title;
    private int requirement;
    private AchievementCategory category;
    private String description;
    private String imgURL;

    public Achievement(String title, int requirement, AchievementCategory category,
                       String description, String imgURL) {
        setTitle(title);
        setRequirement(requirement);
        setCategory(category);
        setDescription(description);
        setImgURL(imgURL);
    }


    @Override
    public String getImgURL() {
        return this.imgURL;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public int getRequirement() {
        return this.requirement;
    }

    @Override
    public AchievementCategory getCategory() {
        return this.category;
    }

    @Override
    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setRequirement(int requirement) {
        this.requirement = requirement;
    }

    @Override
    public void setCategory(AchievementCategory category) {
        this.category = category;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }
}
