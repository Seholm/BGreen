package com.bgreen.filips.bgreen.profile;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by medioloco on 2015-09-22.
 */
public class ProfileService implements IProfileService{

    @Override
    public void saveNewProfile(IProfile profile){
        ParseObject parseProfile = new ParseObject("User");
        setParseProfile(profile, parseProfile);
        uploadToParse(parseProfile);
    }

    //Parse cannot save own made classes like Profile, so we have to copy its content into
    //a ParseObject.
    private void setParseProfile(IProfile p, ParseObject returnParseObject) {
        returnParseObject.put("FirstName", p.getFirstName());
        returnParseObject.put("LastName", p.getLastName());
        returnParseObject.put("Email", p.getEmail());
        returnParseObject.put("TotalDistance", p.getTotalDistance());
        returnParseObject.put("BusTrips", p.getBusTrips());
    }

    //saves to Parse
    private void uploadToParse(ParseObject parseProfile){
        parseProfile.saveInBackground();
    }

    @Override
    public void fetchProfileOfUser(final IProfile profile) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
        query.getInBackground(profile.getParseID(), new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                if (e == null) {
                    writeToProfile(parseObject, profile);
                } else {
                    e.printStackTrace();
                }
            }
        });
    }

    private void writeToProfile(ParseObject parseObject, IProfile profile) {
        profile.upDateProfile(parseObject.getString("FirstName"),
                parseObject.getString("LastName"),
                parseObject.getString("Email"),
                parseObject.getInt("TotalDistance"),
                parseObject.getInt("BusTrips"));
        profile.setParseID(parseObject.getObjectId());
    }

}
