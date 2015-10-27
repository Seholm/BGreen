package com.bgreen.filips.bgreen.buslogging.service;

import com.bgreen.filips.bgreen.buslogging.service.IDatabaseService;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

/**
 * Created by medioloco on 2015-09-15.
 */
public class DatabaseService implements IDatabaseService {

    private final String TOTAL_DISTANCE = "TotalDistance";
    private final String BUS_TRIPS = "BusTrips";
    private final String USER = "User";

    @Override
    public void saveBusTrip(int distance, String userID) {
        ParseObject busTrip = new ParseObject(BUS_TRIPS);
        busTrip.put(TOTAL_DISTANCE, distance);
        saveBusTripOfUser(busTrip, userID, distance);
    }

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
                }
            }
        });
    }

    private void updateProfileStats(ParseObject userProfile, int distance) {
        userProfile.increment(TOTAL_DISTANCE, distance);
        userProfile.increment(BUS_TRIPS);
        userProfile.saveInBackground(new SaveCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    //update completed
                } else {
                    //TODO: handle error
                }
            }
        });
    }
}
