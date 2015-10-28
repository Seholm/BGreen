package com.bgreen.filips.bgreen.achievements.service;

import com.bgreen.filips.bgreen.achievements.model.AchievementCategory;
import com.bgreen.filips.bgreen.achievements.model.Achievement;
import com.bgreen.filips.bgreen.achievements.model.IAchievement;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Database-Service for Achievements. Here we handle fetching of all Achievements from our db.
 * Note that we only retrieve achievements.
 * Created by medioloco on 15-10-26.
 */
public class AchievementService implements IAchievementService {

    private final String ACHIEVEMENT = "Achievement";
    private final String TITLE = "Title";
    private final String REQUIREMENT = "Requirement";
    private final String DESCRIPTION = "Description";
    private final String IMGURL = "ImgURL";
    private final String CATEGORY = "Category";

    //retrieves all achievements
    public List<IAchievement> getAllAchievements() throws ParseException{
        ParseQuery<ParseObject> query = new ParseQuery<>(ACHIEVEMENT);
        List<IAchievement> fetchedAchievements = new ArrayList<>();
        List<ParseObject> parseAchievements = query.find();
        for (ParseObject parseAchievement: parseAchievements){
            fetchedAchievements.add(CopyParseAchievement(parseAchievement));
        }
        return fetchedAchievements;
    }

    //makes a new Achievement with the data corresponding an achievements
    //in the retrieved ParseObject
    private IAchievement CopyParseAchievement(ParseObject parseAchievement) {
        return new Achievement(parseAchievement.getString(TITLE),
                parseAchievement.getInt(REQUIREMENT),
                AchievementCategory.valueOf(parseAchievement.getString(CATEGORY)),
                parseAchievement.getString(DESCRIPTION),
                parseAchievement.getString(IMGURL));

    }
}
