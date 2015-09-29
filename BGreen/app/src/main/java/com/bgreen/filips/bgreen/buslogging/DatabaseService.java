package com.bgreen.filips.bgreen.buslogging;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by medioloco on 2015-09-15.
 */
public class DatabaseService implements IDatabaseService {

    @Override
    public void saveBusTrip(int distance, String userID) {
        ParseObject busTrip = new ParseObject("BusTrip");
        busTrip.put("TotalDistance", distance);
        saveBusTripOfUser(busTrip, userID, distance);
    }

    private void saveBusTripOfUser(final ParseObject busTrip, final String userID, final int distance) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
        query.getInBackground(userID, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                if (e == null){
                    busTrip.put("parent", parseObject);
                    busTrip.saveInBackground();
                    updateProfileStats(parseObject, distance);
                }else{
                    e.printStackTrace();
                }
            }
        });
    }

    private void updateProfileStats(ParseObject userProfile, int distance) {
        userProfile.increment("TotalDistance", distance);
        userProfile.increment("bustTrips");
    }
}
