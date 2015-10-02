package com.bgreen.filips.bgreen.profile;

/**
 * Created by medioloco on 2015-10-01.
 */
public class User extends Profile {

    private static User instance = null;

    public User(){
        //exists only to defeat instantiation
    }

    public static User getInstance() {
        if(instance == null){
            instance = new User();
        }
        return instance;
    }

    public void setUser(String firstName, String lastName, String email,
                        int totalDistance, int busTrips, String imageURL) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setTotalDistance(totalDistance);
        setBusTrips(busTrips);
        setImageURL(imageURL);
    }
}
