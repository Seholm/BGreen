package com.bgreen.filips.bgreen.profile.service;

import com.bgreen.filips.bgreen.profile.model.IUser;
import com.bgreen.filips.bgreen.profile.model.IUserHandler;
import com.bgreen.filips.bgreen.profile.model.ProfileHolder;
import com.bgreen.filips.bgreen.profile.model.User;
import com.bgreen.filips.bgreen.profile.model.IProfile;
import com.bgreen.filips.bgreen.profile.model.Profile;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Here all the communication (concerning Profiles) with Parse DB is handled
 * Created by medioloco on 2015-09-22.
 */
public class ProfileService implements IProfileService {

    private IUser user;
    //constant "key" Strings
    private final String FIRST_NAME = "FirstName";
    private final String LAST_NAME = "LastName";
    private final String EMAIL = "Email";
    private final String TOTAL_DISTANCE = "TotalDistance";
    private final String BUS_TRIPS = "BusTrips";
    private final String IMAGE_URL = "ImageURL";
    private final String USER = "User";

    //Saves a new Profile as an Object in Parse DB
    //Creates a ParseObject and makes it similar to a User
    private void saveNewProfile(IUserHandler handler) throws ParseException{
        ParseObject parseProfile = new ParseObject(USER);
        setParseProfile(user, parseProfile);
        uploadToParse(parseProfile, handler);
    }

    //Checks if user exists in DB, and then uses it. Else saves a new user.
    //this method is made to prevent duplicated Users.
    @Override
    public void saveProfileIfNew(final IUserHandler handler, final IUser user) throws ParseException{
        this.user = user;
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(USER);
        query.whereEqualTo(EMAIL, user.getEmail());
        try {
            ParseObject p = query.getFirst();
            if(p != null){
                startUpFetchOfUser(p.getObjectId(), handler, user);
            }
        } catch (ParseException e) {
            if(e.getCode() == ParseException.OBJECT_NOT_FOUND)
            {
                //User doesn't exist
                saveNewProfile(handler);
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
    private void uploadToParse(final ParseObject parseProfile, final IUserHandler handler)
            throws ParseException{
        parseProfile.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    user.setParseID(parseProfile.getObjectId());
                    handler.writeToFile(user.getParseID());
                }
            }
        });
    }

    //fetch a specific user given an ID. In Parse each ParseObject has its own specific ObjectID
    @Override
    public void fetchProfileOfUser(final String ID, final IUserHandler handler)
            throws ParseException{
        ParseQuery<ParseObject> query = ParseQuery.getQuery(USER);
        query.getInBackground(ID, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                if (e == null) {
                    writeToUser(parseObject, handler);
                }
            }
        });
    }

    //Fetch all existing profiles
    @Override
    public void fetchAllProfiles() throws ParseException{
        ParseQuery<ParseObject> query = ParseQuery.getQuery(USER);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if(e == null) {
                    List<IProfile> profileList = new ArrayList<>();
                    for (ParseObject parseObject : list) {
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
            }
        });
    }

    @Override
    public void startUpFetchOfUser(final String ID, IUserHandler handler, IUser user)
            throws ParseException{
        this.user = user;
        ParseQuery<ParseObject> query = ParseQuery.getQuery(USER);
        writeToUser(query.get(ID), handler);
    }

    //writes fetched user-data to user.
    private void writeToUser(ParseObject parseObject, IUserHandler handler) {
        user.setUser(parseObject.getString(FIRST_NAME),
                parseObject.getString(LAST_NAME),
                parseObject.getString(EMAIL),
                parseObject.getInt(TOTAL_DISTANCE),
                parseObject.getInt(BUS_TRIPS),
                parseObject.getString(IMAGE_URL));
        user.setParseID(parseObject.getObjectId());
        //needs to be written to file for next login
        handler.writeToFile(user.getParseID());
    }

}
