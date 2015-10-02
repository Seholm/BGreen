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
    private final String FIRST_NAME = "FirstName";
    private final String LAST_NAME = "LastName";
    private final String EMAIL = "Email";
    private final String TOTAL_DISTANCE = "TotalDistance";
    private final String BUS_TRIPS = "BusTrips";
    private final String IMAGE_URL = "ImageURL";

    @Override
    public void saveNewProfile(IProfile profile, IUserHandler handler){
        ParseObject parseProfile = new ParseObject("User");
        setParseProfile(profile, parseProfile);
        uploadToParse(parseProfile, handler);
    }

    //Parse cannot save own made classes like Profile, so we have to copy its content into
    //a ParseObject.
    private void setParseProfile(IProfile p, ParseObject parseObject) {
        parseObject.put(FIRST_NAME, p.getFirstName());
        parseObject.put(LAST_NAME, p.getLastName());
        parseObject.put(EMAIL, p.getEmail());
        parseObject.put(TOTAL_DISTANCE, p.getTotalDistance());
        parseObject.put(BUS_TRIPS, p.getBusTrips());
        parseObject.put(IMAGE_URL, p.getImageURL());
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

    public void startUpFetchOfUser(final String ID){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
        try {
            writeToProfile(query.get(ID));
        } catch (ParseException e){
            e.printStackTrace();
        }
    }

    private void writeToProfile(ParseObject parseObject) {
        user.setUser(parseObject.getString(FIRST_NAME),
                parseObject.getString(LAST_NAME),
                parseObject.getString(EMAIL),
                parseObject.getInt(TOTAL_DISTANCE),
                parseObject.getInt(BUS_TRIPS),
                parseObject.getString(IMAGE_URL));
        user.setParseID(parseObject.getObjectId());
    }

}
