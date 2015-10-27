package com.bgreen.filips.bgreen.profile;

/**
 * Created by medioloco on 2015-09-29.
 */
public interface IProfileService {
    public void saveProfileIfNew(final IUserHandler handler) throws Exception;
    public void startUpFetchOfUser(final String ID, final IUserHandler handler) throws Exception;
    public void fetchProfileOfUser(final String ID, final IUserHandler handler) throws Exception;
    public void fetchAllProfiles() throws Exception;
}
