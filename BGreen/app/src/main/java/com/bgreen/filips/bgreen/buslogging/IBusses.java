package com.bgreen.filips.bgreen.buslogging;

import java.util.List;

/**
 * Created by Filips on 9/15/2015.
 */
public interface IBusses {
    public String getCurrentBus(List<String> bssids);
    public boolean doesBusExist(String BSSID);
    public boolean doesBusExist(List<String> BSSIDs);
}
