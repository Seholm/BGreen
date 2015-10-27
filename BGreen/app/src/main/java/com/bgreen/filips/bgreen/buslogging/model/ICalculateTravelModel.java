package com.bgreen.filips.bgreen.buslogging.model;

import java.util.List;

/**
 * Created by flarssonn on 2015-10-13.
 */
public interface ICalculateTravelModel {

    public void main(String nextStop, String route);
    public int getFinalResult();
    public List<String> getTripStops();
    public void clear();
}
