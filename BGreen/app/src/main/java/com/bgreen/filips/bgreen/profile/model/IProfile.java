package com.bgreen.filips.bgreen.profile.model;

import android.os.Parcelable;

/**
 * interface for Profile. Extends Parcelable to make it able to be sent
 * from one activity to another or to a fragment.
 * Created by medioloco on 2015-09-29.
 */
public interface IProfile extends Parcelable{
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
