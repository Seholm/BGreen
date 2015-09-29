package com.bgreen.filips.bgreen.profile;

/**
 * Created by medioloco on 2015-09-28.
 */
public class Profile implements IProfile{
    private static Profile instance = null;
    private String firstName;
    private String lastName;
    private String email;
    private String parseID;
    private int totalDistance;
    private int busTrips;

    protected Profile() {
        // Exists only to defeat instantiation.
    }
    public static Profile getInstance() {
        if(instance == null) {
            instance = new Profile();
        }
        return instance;
    }

    @Override
    public void setProfile(String profileName, String email){
        setFirstName(profileNameSplitter(profileName)[0]);
        setLastName(profileNameSplitter(profileName)[1]);
        setEmail(email);
        setBusTrips(0);
        setTotalDistance(0);
    }

    @Override
    public void upDateProfile(String firstName, String lastName, String email,
                              int totalDistance, int busTrips){
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setTotalDistance(totalDistance);
        setBusTrips(busTrips);
    }

    @Override
    public int getBusTrips() {
        return busTrips;
    }

    @Override
    public void setBusTrips(int busTrips) {
        this.busTrips = busTrips;
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

    private String[] profileNameSplitter(String profileName){
        //är det verkligen mellanslag?
        return profileName.split(" ");
    }
}
