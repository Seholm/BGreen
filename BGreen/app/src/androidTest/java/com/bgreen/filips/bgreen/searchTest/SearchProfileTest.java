package com.bgreen.filips.bgreen.searchTest;

import android.test.InstrumentationTestCase;

import com.bgreen.filips.bgreen.profile.IProfile;
import com.bgreen.filips.bgreen.profile.User;
import com.bgreen.filips.bgreen.search.SearchModel;

import java.util.ArrayList;
import java.util.List;


public class SearchProfileTest extends InstrumentationTestCase {
    private List<IProfile> profiles = new ArrayList<>();
    private SearchModel searchModel = new SearchModel();

    public void testProfileSearch1() {
        IProfile profile = User.getInstance();
        profile.setFirstName("TestPerson");
        profile.setLastName("Lastname");
        profiles.clear();
        profiles.add(profile);
        List<IProfile> resultList = searchModel.doSearch("Test", profiles);
        assertTrue(resultList.size() == 1);
    }

    public void testProfileSearch2() {
        IProfile profile = User.getInstance();
        profile.setFirstName("TestPerson");
        profile.setLastName("Lastname");
        profiles.clear();
        profiles.add(profile);
        List<IProfile> resultList = searchModel.doSearch("Tast", profiles);
        assertTrue(resultList.size() == 0);
    }

    public void testProfileSearch3() {
        IProfile profile = User.getInstance();
        profile.setFirstName("TestPerson");
        profile.setLastName("Lastname");
        profiles.clear();
        profiles.add(profile);
        List<IProfile> resultList = searchModel.doSearch("test", profiles);
        assertTrue(resultList.size() == 1);
    }

    public void testProfileSearch4() {
        IProfile profile = User.getInstance();
        profile.setFirstName("testPerson");
        profile.setLastName("Lastname");
        profiles.clear();
        profiles.add(profile);
        List<IProfile> resultList = searchModel.doSearch("", profiles);
        assertTrue(resultList.size() != 0);
    }

    public void testProfileSearch5() {
        IProfile profile1 = User.getInstance();
        IProfile profile2 = User.getInstance();
        profile1.setFirstName("testPerson1");
        profile1.setLastName("Lastname");
        profile2.setFirstName("testPerson2");
        profile2.setLastName("Lastname");
        profiles.clear();
        profiles.add(profile1);
        profiles.add(profile2);
        List<IProfile> resultList = searchModel.doSearch("testPerson", profiles);
        assertTrue(resultList.size() == 2);
    }

    public void testProfileSearch6() {
        IProfile profile = User.getInstance();
        profile.setFirstName("testPerson");
        profile.setLastName("Lastname");
        profiles.clear();
        profiles.add(profile);
        List<IProfile> resultList = searchModel.doSearch(null, profiles);
        assertTrue(resultList.size() != 0);
    }

    public void testProfileSearch7() {
        List<IProfile> tempList = null;
        List<IProfile> resultList = searchModel.doSearch("", tempList);
        assertTrue(resultList.size() == 0);
    }
}