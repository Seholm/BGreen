package com.bgreen.filips.bgreen;

/**
 * Created by flarssonn on 2015-09-15.
 */
public interface ICalculateTravelInfo {

    //Method for returning a total distance
    int getFinalResult();
    //Method which Service class calls to give info for calculating the distance
    void main(boolean lastStop, String nextStopTest, String route);

}
