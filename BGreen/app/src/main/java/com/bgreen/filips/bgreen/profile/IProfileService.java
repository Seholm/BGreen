package com.bgreen.filips.bgreen.profile;

/**
 * Created by medioloco on 2015-09-29.
 */
public interface IProfileService {
    public void saveNewProfile(IProfile profile, IUserHandler handler);

    public void fetchProfileOfUser(final String ID);
}
