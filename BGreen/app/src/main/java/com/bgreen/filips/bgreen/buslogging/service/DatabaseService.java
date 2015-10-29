package com.bgreen.filips.bgreen.buslogging.service;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

/**
 * This class is responsible for communication with Parse DB, when
 * concerning bus logging.
 * Created by medioloco on 2015-09-15.
 */
public class DatabaseService implements IDatabaseService {

    private final String TOTAL_DISTANCE = "TotalDistance";
    private final String BUS_TRIPS = "BusTrips";
    private final String USER = "User";

    //saves a busTrip with given distance. The ID is the unique identifier of which User is logged on
    //to the system.
    @Override
    public void saveBusTrip(int distance, String userID) {
        ParseObject busTrip = new ParseObject(BUS_TRIPS);
        busTrip.put(TOTAL_DISTANCE, distance);
        saveBusTripOfUser(busTrip, userID, distance);
    }

    //Help method to the method above.
    private void saveBusTripOfUser(final ParseObject busTrip, final String userID, final int distance) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(USER);
        query.getInBackground(userID, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                if (e == null) {
                    busTrip.put("parent", parseObject);
                    busTrip.saveInBackground();
                    updateProfileStats(parseObject, distance);
                } else {
                    e.printStackTrace();
                    //notify user that something went wrong in the uploading of their trip
                }
            }
        });
    }

    //updates stats of the User that made a busTrip
    private void updateProfileStats(ParseObject userProfile, int distance) {
        userProfile.increment(TOTAL_DISTANCE, distance);
        userProfile.increment(BUS_TRIPS);
        userProfile.saveInBackground(new SaveCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    //update completed
                } else {
                    e.printStackTrace();
                    //notify user that something went wrong in the uploading of their trip
                }
            }
        });
    }
}
