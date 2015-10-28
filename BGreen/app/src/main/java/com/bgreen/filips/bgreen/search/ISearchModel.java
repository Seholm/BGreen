package com.bgreen.filips.bgreen.search;

import com.bgreen.filips.bgreen.profile.model.IProfile;

import java.util.List;

/**
 * Created by Filips on 10/26/2015.
 */
public interface ISearchModel {
    List<IProfile> doSearch(String searchString, List<IProfile> profilesList);
}
