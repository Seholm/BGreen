package com.bgreen.filips.bgreen.profile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        sortTopList();
        System.out.println(profiles.size());
    }

    public List<IProfile> getProfiles() {
        return this.profiles;
    }

    public void sortTopList(){

        Collections.sort(profiles, new Comparator<IProfile>() {
            @Override
            public int compare(IProfile p1, IProfile p2) {
                return p1.getTotalDistance() - p2.getTotalDistance(); // Ascending
            }
        });
        
        int i = 1;
        for(IProfile p: profiles){
            p.setPlacement(i);
            i++;
        }
    }

}
