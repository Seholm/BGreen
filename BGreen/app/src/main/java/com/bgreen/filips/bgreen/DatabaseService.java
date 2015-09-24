package com.bgreen.filips.bgreen;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by medioloco on 2015-09-15.
 */
public class DatabaseService implements IDatabaseService{

    @Override
    public void saveBusTrip(int distance, String userEmail) {
        ParseObject busTrip = new ParseObject("busTrip");
        busTrip.put("distance", distance);
        saveBusTripOfUser(busTrip, userEmail, distance);
    }

    private void saveBusTripOfUser(final ParseObject busTrip, final String userEmail, final int distance) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
        query.whereEqualTo("email", userEmail);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e==null){
                    busTrip.put("parent", list.get(0));
                    busTrip.saveInBackground();
                    updateProfileStats(list.get(0), distance);
                }else {
                    e.printStackTrace();
                }
            }
        });
    }

    private void updateProfileStats(ParseObject user, int distance) {
        user.increment("totaldistance", distance);
        user.increment("bustTrips");
    }
}
