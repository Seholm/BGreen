package com.bgreen.filips.bgreen;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by flarssonn on 2015-09-15.
 */
public class CalculateTravelInfo implements ICalculateTravelInfo {


    //String for saving startpoint of the trip
    private String startPoint;
    //String for saving the lateststop during the trip
    private String latestPoint;
    //String for saving the lastpoint of the trip
    private String endPoint;
    //List with all busstopps
    private List<String> busStops = new ArrayList<String>();
    //List with all legths between bus stops. on index i is distance between busstop i-1 and i
    private List<Integer> busStopsLength = new ArrayList<Integer>();
    //Integer for saving the total distance of the trip
    int totDistance=0;

    //Method for setting bus stops if route is Chalmers-Lindholmen
    private void setBusStopsNorthRoute(){
        busStops.clear();

        busStops.add("Sven Hultins pl.A");
        busStops.add("ChalmersplatsenA");
        busStops.add("KapellplatsenE");
        busStops.add("GötaplatsenA");
        busStops.add("ValandC");
        busStops.add("KungsportsplC");
        busStops.add("BrunnsparkenB");
        busStops.add("Lilla BommenB");
        busStops.add("FrihamnsportenB");
        busStops.add("PumpgatanB");
        busStops.add("RegnbågsgatanD");
        busStops.add("LindholmenD");
        busStops.add("TeknikgatanA");
    }

    //Method for setting bus stops distances if route is Chalmers-Lindholmen
    private void setBusStopsNorthRouteDistances(){
        busStopsLength.clear();

        busStopsLength.add(0);
        busStopsLength.add(450);
        busStopsLength.add(550);
        busStopsLength.add(600);
        busStopsLength.add(400);
        busStopsLength.add(500);
        busStopsLength.add(350);
        busStopsLength.add(280);
        busStopsLength.add(2200);
        busStopsLength.add(1200);
        busStopsLength.add(300);
        busStopsLength.add(400);
        busStopsLength.add(550);

    }

    //Method for setting bus stops if route is Lindholmen-Chalmers
    private void setBusStopsSouthRoute() {
        busStops.clear();

        busStops.add("TeknikgatanA");
        busStops.add("LindholmsplatsenA");
        busStops.add("RegnbågsgatanB");
        busStops.add("PumpgatanA");
        busStops.add("FrihamnsportenA");
        busStops.add("Lilla BommenA");
        busStops.add("BrunnsparkenA");
        busStops.add("KungsportsplD");
        busStops.add("ValandD");
        busStops.add("GötaplatsenB");
        busStops.add("ÅlandsgatanB");
        busStops.add("Chalmers TvärgB");
        busStops.add("Sven Hultins pl.A");
    }

    //Method for setting bus stops distances if route is johanneberg
    private void setBusStopsSouthRouteDistance(){
        busStopsLength.clear();

        busStopsLength.add(0);
        busStopsLength.add(52);
        busStopsLength.add(550);
        busStopsLength.add(300);
        busStopsLength.add(1200);
        busStopsLength.add(2200);
        busStopsLength.add(280);
        busStopsLength.add(350);
        busStopsLength.add(500);
        busStopsLength.add(400);
        busStopsLength.add(550);
        busStopsLength.add(200);
        busStopsLength.add(1100);
    }


    //Method for setting a startpoint and route of the trip. Parameters is a point and a route.
    private void setStartPoint(String point, String route){
        setTotalDistance(0);
        if(route.equals("Lindholmen")){
            setBusStopsNorthRoute();
            setBusStopsNorthRouteDistances();
        }else{
            setBusStopsSouthRoute();
            setBusStopsSouthRouteDistance();
        }
        for(int i=0; i<busStops.size()+1; i++){

            //Startpoint is the stop before the point saying which is next stop
            if(busStops.get(i).equals(point)){
                startPoint = busStops.get(i-1);
                setLatestPoint(busStops.get(i));
                break;
            }
        }
    }
    public String getStartPoint(){
        return startPoint;
    }


    //Method for setting which stop was the last one a user visited
    private void setLatestPoint(String point){
        latestPoint = point;
    }
    public String getLatestPoint(){
        return latestPoint;
    }

    private void setEndPoint(){
        endPoint = getLatestPoint();
    }
    public String getEndPoint(){
        return endPoint;
    }

    private void setTotalDistance(int distance){
        totDistance = distance;
    }

    //Method for calculation the total distance
    private Integer calcTotalDistance(){
        String point = getStartPoint();
        int numberOfStops=0;
        int totDistance = 0;

        //Get the point after startpoint
        while(point.equals(getStartPoint())){
            for(int i=0; i<busStops.size()+1; i++){
                if(point.equals(busStops.get(i))){

                    //Set point to the one after the startpoint
                    point = busStops.get(i+1);
                    System.out.println("Startpunkt "+busStops.get(i));
                    break;

                }
            }
        }

        //Add together all distances after startpoint until last stop
        while (!point.equals(getStartPoint())){
            for(int i=0; i<busStops.size(); i++){
                if (point.equals(busStops.get(i))){
                    totDistance = totDistance + busStopsLength.get(i);
                    System.out.println("avstånd från " + busStops.get(i-1) + " till " + busStops.get(i) +": "+ busStopsLength.get(i));

                    numberOfStops = numberOfStops +1;

                    //If point is lastpoint, break so totDistance is finished calculating
                    if(busStops.get(i).equals(getEndPoint())){
                        setTotalDistance(totDistance);

                        break;
                    }
                    point = busStops.get(i+1);
                }

            }
            break;
        }
        //Some prints for testing until we have real api and can do real testing
        System.out.println("Antal stopp: " + numberOfStops);
        System.out.println("Sista hållplats: " + getEndPoint());
        System.out.println("totalt avstånd från " + getStartPoint() + " till " + getEndPoint() + ": " + totDistance);
        return getFinalResult();
    }

    //The method the Service class calls to give information which this class can calculate
    public void main(boolean lastStop, String nextStop, String route){
        //Om ej trafik och loopar flera ej trafik
        //Om åkt och sen blir ej i trafik

        //Does calculation and sets points if route is correct
        if(route.equals("Lindholmen") || route.equals("Johanneberg")){
            //method doesn't set any values when nextStop in null
            if(nextStop!=null && route!=null){
                //Set startpoint if there is none else set nextStop as latestPoint
                if(startPoint==null){
                    setStartPoint(nextStop, route);

                }else if(!getLatestPoint().equals(nextStop)){
                    setLatestPoint(nextStop);
                }

                //If wifi lost set nextStop as lastStop
                if(lastStop == true){
                    setLatestPoint(nextStop);
                    setEndPoint();
                    calcTotalDistance();
                }
            }
        }

        //If lastStop is true and nextStop is null, set endPoint and calc with old values
        if(lastStop==true && nextStop==null && getStartPoint()!=null){
            setEndPoint();
            calcTotalDistance();
        }
        //If route isn't correct and have previous stops. Add previous stops together as totDistance
        if(lastStop==true && !route.equals("Lindholmen") && !route.equals("Johanneberg") && getStartPoint()!=null){

            setEndPoint();
            calcTotalDistance();

        }

    }

    //Method for returning the totaldistance. Only called when wifi is lost and calculation done
    public int getFinalResult(){
        return totDistance;
    }

    @Override
    public void resetTotalDistance(){
        setTotalDistance(0);
    }

}










