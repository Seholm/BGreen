package com.bgreen.filips.bgreen.profile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by medioloco on 2015-10-02.
 */
public class ProfileHolder {
    private ProfileHolder instance = new ProfileHolder();
    private List<Profile> profiles = new ArrayList<>();

    public ProfileHolder getInstance(){
        return instance;
    }

    private ProfileHolder(){

    }

    public void setProfiles(List<Profile> list){
        if(list != null){
            profiles.addAll(list);
        }else {

        }
    }

}
