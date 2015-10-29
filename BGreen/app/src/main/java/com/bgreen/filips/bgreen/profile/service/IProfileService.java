package com.bgreen.filips.bgreen.profile.service;

import com.bgreen.filips.bgreen.profile.model.IUser;
import com.bgreen.filips.bgreen.profile.model.IUserHandler;

/**
 * interface for ProfileService
 * Created by medioloco on 2015-09-29.
 */
public interface IProfileService {
    public void saveProfileIfNew(final IUserHandler handler, IUser user) throws Exception;
    public void startUpFetchOfUser(final String ID, final IUserHandler handler, IUser user) throws Exception;
    public void fetchProfileOfUser(final String ID, final IUserHandler handler) throws Exception;
    public void fetchAllProfiles() throws Exception;
}
