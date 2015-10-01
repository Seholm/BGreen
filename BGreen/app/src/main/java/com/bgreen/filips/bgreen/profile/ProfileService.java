package com.bgreen.filips.bgreen.profile;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

/**
 * Created by medioloco on 2015-09-22.
 */
public class ProfileService implements IProfileService{

    private User user = User.getInstance();

    @Override
    public void saveNewProfile(IProfile profile, IUserHandler handler){
        ParseObject parseProfile = new ParseObject("User");
        setParseProfile(profile, parseProfile);
        uploadToParse(parseProfile, handler);
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
    private void uploadToParse(final ParseObject parseProfile, final IUserHandler handler){
        parseProfile.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    user.setParseID(parseProfile.getObjectId());
                    handler.writeToFile(user.getParseID());
                } else {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void fetchProfileOfUser(final String ID) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
        query.getInBackground(ID, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                if (e == null) {
                    writeToProfile(parseObject);
                } else {
                    e.printStackTrace();
                }
            }
        });
    }

    private void writeToProfile(ParseObject parseObject) {
        user.setUser(parseObject.getString("FirstName"),
                parseObject.getString("LastName"),
                parseObject.getString("Email"),
                parseObject.getInt("TotalDistance"),
                parseObject.getInt("BusTrips"));
        user.setParseID(parseObject.getObjectId());
    }

}
