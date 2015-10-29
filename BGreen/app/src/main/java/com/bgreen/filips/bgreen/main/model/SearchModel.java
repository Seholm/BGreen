package com.bgreen.filips.bgreen.main.model;

import com.bgreen.filips.bgreen.main.model.ISearchModel;
import com.bgreen.filips.bgreen.profile.model.IProfile;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by flarssonn on 2015-10-02.
 */
public class SearchModel implements ISearchModel {

    List<IProfile> resultList;
    List<IProfile> firstNameResultList;
    List<IProfile> lastNameResultList;
    private boolean searchDone = false;

    public List<IProfile> doSearch(String searchString, List<IProfile> profilesList){
        searchDone=true;
        if(profilesList!=null){

            resultList = new ArrayList<IProfile>();
            firstNameResultList  = new ArrayList<IProfile>();
            lastNameResultList = new ArrayList<IProfile>();



            String[] searchStrings = searchString.split("\\s+");
            if(searchStrings==null){
                searchStrings[0] = searchString;
            }

            searchFirstName(searchStrings, profilesList);
            searchLastName(searchStrings, profilesList);

            setResult();


        }else{
            resultList = new ArrayList<>();
        }

        //return a list with all searchresults
        return getResult();
    }
    //Search through all firstnames
    private void searchFirstName(String[] searchString, List<IProfile> profilesList){
        firstNameResultList.clear();
        for(int i=0; i<searchString.length; i++){

            String searchLine = searchString[i].toLowerCase();

            for(int j=0; j<profilesList.size(); j++){

                if(profilesList.get(j).getFirstName().toLowerCase().contains(searchLine)){
                    firstNameResultList.add(profilesList.get(j));

                }
            }
        }
    }
    //Search through all last names
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

    private void setResult(){

        //If match on first- and last name
        if(firstNameResultList.size()!=0 && lastNameResultList.size()!=0){
            //See if firstname and lastname has same profile then that is answer
            for(int i=0; i<firstNameResultList.size(); i++){
                for(int j=0; j<lastNameResultList.size(); j++){
                    if(firstNameResultList.get(i).equals(lastNameResultList.get(j))){
                        resultList.add(firstNameResultList.get(i));

                    }
                }
            }
            if(resultList.size()==0){
                resultList.addAll(firstNameResultList);
                resultList.addAll(lastNameResultList);
            }
        }
        //If only matches on firstname add all firstname-profiles to result
        if(firstNameResultList.size()!=0 && lastNameResultList.size()==0){
            for(int i=0; i<firstNameResultList.size(); i++){
                resultList.add(firstNameResultList.get(i));

            }
        }
        //If only matches on lastname add all lastname-profiles to result
        if(firstNameResultList.size()==0 && lastNameResultList.size()!=0){
            for(int i=0; i<lastNameResultList.size(); i++){
                resultList.add(lastNameResultList.get(i));

            }
        }
    }

    private List<IProfile> getResult(){
        return resultList;
    }
    //Method to see if searh has been done
    public boolean isSearchDone(){
        return searchDone;
    }
}
