package com.bgreen.filips.bgreen.profile.model;

import com.bgreen.filips.bgreen.profile.model.IProfile;

/**
 * interface for User
 * Created by Filips on 10/26/2015.
 */
public interface IUser extends IProfile {
    void setUser(String firstName, String lastName, String email,
            int totalDistance, int busTrips, String imageURL);
}
