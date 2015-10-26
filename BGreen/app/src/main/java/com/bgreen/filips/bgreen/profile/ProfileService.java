package com.bgreen.filips.bgreen.profile;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

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
    private final String USER = "User";

    private void saveNewProfile(IUserHandler handler){
        ParseObject parseProfile = new ParseObject(USER);
        setParseProfile(user, parseProfile);
        uploadToParse(parseProfile, handler);
    }

    @Override
    public void saveProfileIfNew(final IUserHandler handler){
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(USER);
        query.whereEqualTo(EMAIL, user.getEmail());
        try {
            ParseObject p = query.getFirst();
            if(p != null){
                startUpFetchOfUser(p.getObjectId(), handler);
            }
        } catch (ParseException e) {
            if(e.getCode() == ParseException.OBJECT_NOT_FOUND)
            {
                //User doesn't exist
                saveNewProfile(handler);
            }
            else
            {
                //unknown error, debug
                e.printStackTrace();
            }
        }

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
    public void fetchProfileOfUser(final String ID, final IUserHandler handler) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(USER);
        query.getInBackground(ID, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                if (e == null) {
                    writeToUser(parseObject, handler);
                } else {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void fetchAllProfiles() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(USER);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                List<IProfile> profileList = new ArrayList<>();
                for(ParseObject parseObject: list){
                    IProfile p = new Profile(parseObject.getString(FIRST_NAME),
                            parseObject.getString(LAST_NAME),
                            parseObject.getString(EMAIL),
                            parseObject.getObjectId(),
                            parseObject.getInt(TOTAL_DISTANCE),
                            parseObject.getInt(BUS_TRIPS),
                            parseObject.getString(IMAGE_URL));
                    profileList.add(p);
                }
                ProfileHolder.getInstance().setProfiles(profileList);
            }
        });
    }

    public void startUpFetchOfUser(final String ID, IUserHandler handler){
        ParseQuery<ParseObject> query = ParseQuery.getQuery(USER);
        try {
            writeToUser(query.get(ID), handler);
        } catch (ParseException e){
            e.printStackTrace();
        }
    }

    private void writeToUser(ParseObject parseObject, IUserHandler handler) {
        user.setUser(parseObject.getString(FIRST_NAME),
                parseObject.getString(LAST_NAME),
                parseObject.getString(EMAIL),
                parseObject.getInt(TOTAL_DISTANCE),
                parseObject.getInt(BUS_TRIPS),
                parseObject.getString(IMAGE_URL));
        user.setParseID(parseObject.getObjectId());
        handler.writeToFile(user.getParseID());
    }

}
