package com.bgreen.filips.bgreen.test.searchTest;

import android.os.Parcel;

import com.bgreen.filips.bgreen.profile.model.IProfile;

public class MockProfile implements IProfile {
    String firstName;
    String lastName;
    int totalDistance;
    int trips;

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;

    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public void setEmail(String email) {

    }

    @Override
    public void setParseID(String parseID) {

    }

    @Override
    public void setTotalDistance(int totalDistance) {
        this.totalDistance = totalDistance;
    }

    @Override
    public void setBusTrips(int busTrips) {
        this.trips = busTrips;
    }

    @Override
    public void setImageURL(String url) {

    }

    @Override
    public void setPlacement(int placement) {

    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public String getParseID() {
        return null;
    }

    @Override
    public int getTotalDistance() {
        return totalDistance;
    }

    @Override
    public int getBusTrips() {
        return trips;
    }

    @Override
    public String getImageURL() {
        return null;
    }

    @Override
    public int getPlacement() {
        return 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
