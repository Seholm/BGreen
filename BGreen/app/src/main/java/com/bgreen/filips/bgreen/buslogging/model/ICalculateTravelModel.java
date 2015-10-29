package com.bgreen.filips.bgreen.buslogging.model;

import java.util.List;

/**
 * Created by flarssonn on 2015-10-13.
 */
public interface ICalculateTravelModel {
    //Method called from IdentifyTravelService
    public void main(String nextStop, String route);

    //Method called when IdentifyTravelService wants the total distance
    public int getFinalResult();

    //Method called if some class wants to know which stops a traveller has passed during a trip
    public List<String> getTripStops();

    public void clear();
}
