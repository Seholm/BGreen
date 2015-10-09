package com.bgreen.filips.bgreen.search;

import com.bgreen.filips.bgreen.profile.IProfile;
import com.bgreen.filips.bgreen.profile.Profile;
import com.bgreen.filips.bgreen.profile.ProfileHolder;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by flarssonn on 2015-10-02.
 */
public class SearchModel {

    List<IProfile> resultList;
    List<IProfile> firstNameResultList;
    List<IProfile> lastNameResultList;
    //List<IProfile> emailResultList;

    public List<IProfile> doSearch(String searchString){

        resultList = new ArrayList<IProfile>();
        firstNameResultList  = new ArrayList<IProfile>();
        lastNameResultList = new ArrayList<IProfile>();
        List<IProfile> profilesList = ProfileHolder.getInstance().getProfiles();

        String[] searchStrings = searchString.split("\\s+");
        if(searchStrings==null){
            searchStrings[0] = searchString;
        }

        searchFirstName(searchStrings, profilesList);
        searchLastName(searchStrings, profilesList);

        setResult();
        for(int i=0; i<firstNameResultList.size(); i++){
            //System.out.println(firstNameResultList.get(i).getFirstName()+i);
        }
        System.out.println(firstNameResultList.size());

        return getResult();
    }

    private void searchFirstName(String[] searchString, List<IProfile> profilesList){
        firstNameResultList.clear();
        for(int i=0; i<searchString.length; i++){

            String searchLine = searchString[i].toLowerCase();

            for(int j=0; j<profilesList.size(); j++){

                if(profilesList.get(j).getFirstName().toLowerCase().contains(searchLine)){
                    firstNameResultList.add(profilesList.get(j));
                    //System.out.println(profilesList.get(j).getFirstName()+j);
                }
            }
        }
    }

    private void searchLastName(String[] searchString, List<IProfile> profilesList){
        lastNameResultList.clear();
        for (int i=0; i<searchString.length; i++){
            String searchLine = searchString[i].toLowerCase();
            for(int j=0; j<profilesList.size(); j++){
                if(profilesList.get(j).getLastName().toLowerCase().contains(searchLine)){
                    lastNameResultList.add(profilesList.get(j));
                }

            }
        }
    }
    /*
    private void searchEmail(String[] searchString, List<IProfile> profilesList){
        emailResultList = new ArrayList<IProfile>();
        for (int i=0; i<searchString.length; i++){
            String searchLine = searchString[i].toLowerCase();
            for(int j=0; j<profilesList.size(); j++){
                if(profilesList.get(j).getEmail().toLowerCase().contains(searchLine)){
                    emailResultList.add(profilesList.get(i));
                }
            }
        }
    }*/

    private void setResult(){

        //If match on first- and last name
        if(firstNameResultList.size()!=0 && lastNameResultList.size()!=0){
            //See if firstname and lastname has same profile then that is answer
            for(int i=0; i<firstNameResultList.size(); i++){
                for(int j=0; j<lastNameResultList.size(); j++){
                    if(firstNameResultList.get(i).equals(lastNameResultList.get(j))){
                        resultList.add(firstNameResultList.get(i));
                        System.out.println("kuken1 "+i);
                    }
                }
            }
            if(resultList.size()==0){
                resultList.addAll(firstNameResultList);
                resultList.addAll(lastNameResultList);
            }
            /*
            if(resultList==null){
                for(int i=0; i<firstNameResultList.size(); i++){
                    resultList.add(firstNameResultList.get(i));
                }
                for(int i=0; i<lastNameResultList.size(); i++){
                    resultList.add(lastNameResultList.get(i));
                }
            }*/
        }
        //If only matches on firstname add all firstname-profiles to result
        if(firstNameResultList.size()!=0 && lastNameResultList.size()==0){
            for(int i=0; i<firstNameResultList.size(); i++){
                resultList.add(firstNameResultList.get(i));
                System.out.println("kuken2 " + i);
            }
        }
        //If only matches on lastname add all lastname-profiles to result
        if(firstNameResultList.size()==0 && lastNameResultList.size()!=0){
            for(int i=0; i<lastNameResultList.size(); i++){
                resultList.add(lastNameResultList.get(i));
                System.out.println("kuken3 " + i);
            }
        }
    }

    private List<IProfile> getResult(){
        return resultList;
    }

}
