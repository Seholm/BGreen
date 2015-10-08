package com.bgreen.filips.bgreen;

/**
 * Created by flarssonn on 2015-09-15.
 */
public interface ICalculateTravelInfo {

    //Method for returning a total distance
    public int getFinalResult();
    //Method which Service class calls to give info for calculating the distance
    public void main(boolean lastStop, String nextStopTest, String route);
    public String getStartPoint();
    public String getEndPoint();
    public String getLatestPoint();
    public void resetTotalDistance();

}
