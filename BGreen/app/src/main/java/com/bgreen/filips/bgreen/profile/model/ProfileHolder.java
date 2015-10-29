package com.bgreen.filips.bgreen.profile.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;

/**
 * Singleton class that contains all Profiles.
 * Created by medioloco on 2015-10-02.
 */
public class ProfileHolder extends Observable {
    private static ProfileHolder instance = null;
    private List<IProfile> profiles = new ArrayList<>();
    private boolean startUp = true;

    public static ProfileHolder getInstance(){
        if(instance == null){
            instance = new ProfileHolder();
        }
        return instance;
    }

    private ProfileHolder(){

    }

    public void setProfiles(List<IProfile> list){
        profiles.clear();
        profiles.addAll(list);
        sortTopList();

        //if this is the first time this method has been called, then notify
        //our observer (LogginActivity).
        if(startUp){
            setChanged();
            notifyObservers();
            startUp = false;
        }
    }

    public List<IProfile> getProfiles() {
        return this.profiles;
    }

    //sort the list of profiles on distance descending
    public void sortTopList(){

        Collections.sort(profiles, new Comparator<IProfile>() {
            @Override
            public int compare(IProfile p1, IProfile p2) {
                int compare = p1.getTotalDistance() - p2.getTotalDistance();
                return (-1)*compare;
            }
        });

        int i = 1;
        //sets the right placement for the user
        for(IProfile p: profiles){
            if(p.getParseID().equals(User.getInstance().getParseID())){
                User.getInstance().setPlacement(i);
            }
            p.setPlacement(i);
            i++;
        }
    }

}
