package com.bgreen.filips.bgreen.profile;

/**
 * Created by Isaac on 2015-09-30.
 */
public class User extends Profile{

    private static User instance = null;

    protected User() {
        // Exists only to defeat instantiation.
    }
    public static User getInstance() {
        if(instance == null) {
            instance = new User();
        }
        return instance;
    }

    public void setUser(String firstName, String lastName, String email,
                        int totalDistance, int busTrips) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setTotalDistance(totalDistance);
        setBusTrips(busTrips);
    }

}
