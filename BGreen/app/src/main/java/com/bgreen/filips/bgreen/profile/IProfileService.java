package com.bgreen.filips.bgreen.profile;

/**
 * Created by medioloco on 2015-09-29.
 */
public interface IProfileService {
    public void saveProfileIfNew(final IUserHandler handler);
    public void startUpFetchOfUser(final String ID, final IUserHandler handler);
    public void fetchProfileOfUser(final String ID, final IUserHandler handler);
    public void fetchAllProfiles();
}
