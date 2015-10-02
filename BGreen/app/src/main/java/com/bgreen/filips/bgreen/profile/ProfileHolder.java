package com.bgreen.filips.bgreen.profile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by medioloco on 2015-10-02.
 */
public class ProfileHolder {
    private static ProfileHolder instance = null;
    private List<IProfile> profiles = new ArrayList<>();

    public static ProfileHolder getInstance(){
        if(instance == null){
            instance = new ProfileHolder();
        }
        return instance;
    }

    private ProfileHolder(){

    }

    public void setProfiles(List<IProfile> list){
        profiles.addAll(list);
        System.out.println(profiles.size());
    }

    public List<IProfile> getProfiles() {
        return this.profiles;
    }

}
