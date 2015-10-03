package com.bgreen.filips.bgreen.profile;

/**
 * Created by medioloco on 2015-09-29.
 */
public interface IProfile {
    public void setFirstName(String firstName);
    public void setLastName(String lastName);
    public void setEmail(String email);
    public void setParseID(String parseID);
    public void setTotalDistance(int totalDistance);
    public void setBusTrips(int busTrips);
    public void setImageURL(String url);
    public void  setPlacement(int placement);

    public String getFirstName();
    public String getLastName();
    public String getEmail();
    public String getParseID();
    public int getTotalDistance();
    public int getBusTrips();
    public String getImageURL();
    public int getPlacement();
}
