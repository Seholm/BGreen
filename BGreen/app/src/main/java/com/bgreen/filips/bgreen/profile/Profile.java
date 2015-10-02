package com.bgreen.filips.bgreen.profile;

/**
 * Created by medioloco on 2015-09-28.
 */
public class Profile implements IProfile{
    private String firstName;
    private String lastName;
    private String email;
    private String parseID;
    private int totalDistance;
    private int busTrips;
    private String imageURL;

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

}
