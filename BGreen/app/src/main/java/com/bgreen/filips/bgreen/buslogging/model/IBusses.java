package com.bgreen.filips.bgreen.buslogging.model;

import java.util.List;

/**
 * Created by Filips on 9/15/2015.
 */
public interface IBusses {
    // a interface that represents the Busses, uses BSSID to get information about busses
    public String getCurrentBus(List<String> bssids);
    public boolean doesBusExist(String BSSID);
    public boolean doesBusExist(List<String> BSSIDs);
}
