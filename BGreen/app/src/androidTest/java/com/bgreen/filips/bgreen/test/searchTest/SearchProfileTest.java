package com.bgreen.filips.bgreen.test.searchTest;

import android.test.InstrumentationTestCase;

import com.bgreen.filips.bgreen.profile.model.IProfile;
import com.bgreen.filips.bgreen.search.SearchModel;

import java.util.ArrayList;
import java.util.List;


public class SearchProfileTest extends InstrumentationTestCase {
    private List<IProfile> profiles = new ArrayList<>();
    private SearchModel searchModel = new SearchModel();

    public void testOnePersonSearch() {
        IProfile profile = new MockProfile();
        profile.setFirstName("TestPerson");
        profile.setLastName("Lastname");
        profiles.clear();
        profiles.add(profile);
        List<IProfile> resultList = searchModel.doSearch("Test", profiles);
        assertTrue(resultList.size() == 1);
    }

    public void testNoPersonSearch() {
        IProfile profile = new MockProfile();
        profile.setFirstName("TestPerson");
        profile.setLastName("Lastname");
        profiles.clear();
        profiles.add(profile);
        List<IProfile> resultList = searchModel.doSearch("Tast", profiles);
        assertTrue(resultList.size() == 0);
    }

    public void testSearchForNoName() {
        IProfile profile = new MockProfile();
        profile.setFirstName("testPerson");
        profile.setLastName("Lastname");
        profiles.clear();
        profiles.add(profile);
        List<IProfile> resultList = searchModel.doSearch("", profiles);
        assertTrue(resultList.size() != 0);
    }

    public void testSearchForSameName() {
        IProfile profile1 = new MockProfile();
        IProfile profile2 = new MockProfile();
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

    public void testEmptyListSearch() {
        List<IProfile> tempList = null;
        List<IProfile> resultList = searchModel.doSearch("", tempList);
        assertTrue(resultList.size() == 0);
    }
}