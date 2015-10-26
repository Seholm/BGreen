package com.bgreen.filips.bgreen.achievements;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by medioloco on 15-10-26.
 */
public class AchievementService implements IAchievementService {

    private final String ACHIEVEMENT = "Achievement";
    private final String TITLE = "Title";
    private final String REQUIREMENT = "Requirement";
    private final String DESCRIPTION = "Description";
    private final String IMGURL = "ImgURL";
    private final String CATEGORY = "Category";

    public List<IAchievement> getAllAchievements() {
        ParseQuery<ParseObject> query = new ParseQuery<>(ACHIEVEMENT);
        List<IAchievement> fetchedAchievements = new ArrayList<>();
        try{
            List<ParseObject> parseAchievements = query.find();
            for (ParseObject parseAchievement: parseAchievements){
                fetchedAchievements.add(CopyParseAchievement(parseAchievement));
            }
            return fetchedAchievements;
        }catch (Exception e) {
            if(e.getClass() == com.parse.ParseException.class){
                //handle error
            }
        }
        return null;
    }

    private IAchievement CopyParseAchievement(ParseObject parseAchievement) {
        return new Achievement(parseAchievement.getString(TITLE),
                parseAchievement.getInt(REQUIREMENT),
                AchievementCategory.valueOf(parseAchievement.getString(CATEGORY)),
                parseAchievement.getString(DESCRIPTION),
                parseAchievement.getString(IMGURL));

    }
}
