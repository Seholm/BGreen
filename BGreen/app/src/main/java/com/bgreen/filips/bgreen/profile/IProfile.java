package com.bgreen.filips.bgreen.profile;

/**
 * Created by medioloco on 2015-09-29.
 */
public interface IProfile {
    public void upDateProfile(String firstName, String lastName, String email, int totalDistance,
                              int busTrips);
    public void setProfile(String profileName, String email);
    public void setFirstName(String firstName);
    public void setLastName(String lastName);
    public void setEmail(String email);
    public void setParseID(String parseID);
    public void setTotalDistance(int totalDistance);
    public void setBusTrips(int busTrips);

    public String getFirstName();
    public String getLastName();
    public String getEmail();
    public String getParseID();
    public int getTotalDistance();
    public int getBusTrips();
}
