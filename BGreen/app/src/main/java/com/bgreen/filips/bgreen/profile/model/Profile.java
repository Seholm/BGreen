package com.bgreen.filips.bgreen.profile.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Model class for Profile
 * Created by medioloco on 2015-09-28.
 */
public class Profile implements IProfile {
    private String firstName;
    private String lastName;
    private String email;
    private String parseID;
    private int totalDistance;
    private int busTrips;
    private String imageURL;
    private int placement;

    protected Profile() {

    }

    public Profile(String firstName, String lastName, String email, String parseID,
                   int totalDistance, int busTrips, String imageURL){
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setParseID(parseID);
        setTotalDistance(totalDistance);
        setBusTrips(busTrips);
        setImageURL(imageURL);
    }

    @Override
    public int getBusTrips() {
        return busTrips;
    }

    @Override
    public String getImageURL() {
        return imageURL;
    }

    @Override
    public void setBusTrips(int busTrips) {
        this.busTrips = busTrips;
    }

    @Override
    public void setImageURL(String url) {
        this.imageURL = url;
    }

    @Override
    public String getParseID() {
        return parseID;
    }

    @Override
    public void setParseID(String parseID) {
        this.parseID = parseID;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public int getTotalDistance() {
        return totalDistance;
    }

    @Override
    public void setTotalDistance(int totalDistance) {
        this.totalDistance = totalDistance;
    }


    public int getPlacement() {
        return placement;
    }

    public void setPlacement(int placement) {
        this.placement = placement;
    }


    //following methods is required to make Profile be able to be
    // sent between Activities or fragments

    protected Profile(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        email = in.readString();
        parseID = in.readString();
        totalDistance = in.readInt();
        busTrips = in.readInt();
        imageURL = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }



    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(email);
        dest.writeString(parseID);
        dest.writeInt(totalDistance);
        dest.writeInt(busTrips);
        dest.writeString(imageURL);

    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Profile> CREATOR = new Parcelable.Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };

}
