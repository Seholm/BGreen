package com.bgreen.filips.bgreen;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by flarssonn on 2015-09-15.
 */
public class CalculateTravelInfo implements ICalculateTravelInfo {

    //TODO: fix correct busstops + busstoplengths


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
    //Integer for saving the total didstance of the trip
    int totDistance;

    //Method for setting bus stops if route is Chalmers-Lindholmen
    private void setBusStopsNorthRoute(){
        busStops.clear();

        busStops.add("Sven Hultins plats");
        busStops.add("Chalmersplatsen");
        busStops.add("Kapellplatsen");
        busStops.add("Götaplatsen");
        busStops.add("Valand");
        busStops.add("Kungsportsplatsen");
        busStops.add("Brunnsparken");
        busStops.add("Lilla Bommen");
        busStops.add("Frihamnsporten");
        busStops.add("Pumpgatan");
        busStops.add("Regnbågsgatan");
        busStops.add("Lindholmen");
        busStops.add("Teknikgatan");
    }

    //Method for setting bus stops distances if route is Chalmers-Lindholmen
    private void setBusStopsNorthRouteDistances(){
        busStopsLength.clear();

        busStopsLength.add(0);
        busStopsLength.add(10);
        busStopsLength.add(10);
        busStopsLength.add(10);
        busStopsLength.add(10);
        busStopsLength.add(10);
        busStopsLength.add(10);
        busStopsLength.add(10);
        busStopsLength.add(10);
        busStopsLength.add(10);
        busStopsLength.add(10);
        busStopsLength.add(10);
        busStopsLength.add(10);
    }

    //Method for setting bus stops if route is Lindholmen-Chalmers
    private void setBusStopsSouthRoute(){
        busStops.clear();

        busStops.add("Lindholmen");
        busStops.add("Teknikgatan");
        busStops.add("Lindholmsplatsen");
        busStops.add("Lindholmen");
        busStops.add("Regnbågsgatan");
        busStops.add("Pumpgatan");
        busStops.add("Frihamnsporten");
        busStops.add("Lilla Bommen");
        busStops.add("Brunnsparken");
        busStops.add("Kungsportsplatsen");
        busStops.add("Valand");
        busStops.add("Götaplatsen");
        busStops.add("Ålandsgatan");
        busStops.add("Chalmers tvärgata");
        busStops.add("Sven Hultins plats");
    }

    //Method for setting bus stops distances if route is Lindholmen-Chalmers
    private void setBusStopsSouthRouteDistance(){
        busStopsLength.clear();

        busStopsLength.add(0);
        busStopsLength.add(10);
        busStopsLength.add(10);
        busStopsLength.add(10);
        busStopsLength.add(10);
        busStopsLength.add(10);
        busStopsLength.add(10);
        busStopsLength.add(10);
        busStopsLength.add(10);
        busStopsLength.add(10);
        busStopsLength.add(10);
        busStopsLength.add(10);
        busStopsLength.add(10);
        busStopsLength.add(10);
        busStopsLength.add(10);
    }


    //Method for setting a startpoint and route of the trip. Parameters is a point and a route.
    private void setStartPoint(String point, String route){

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
        while(!point.equals(getStartPoint())){
            for(int i=0; i<busStops.size(); i++){
                if (point.equals(busStops.get(i))){
                    totDistance = totDistance + busStopsLength.get(i);
                    System.out.println("avstånd från " + busStops.get(i-1) + " till " + busStops.get(i) +": "+ busStopsLength.get(i));

                    numberOfStops = numberOfStops +1;

                    //If point is lastpoint, break so totDistance is finished calculating
                    if(busStops.get(i).equals(getEndPoint())){
                        setTotalDistance(totDistance);
                        System.out.println("avstånd från " + busStops.get(i) + " till " + busStops.get(i+1) +": "+ busStopsLength.get(i+1));
                        numberOfStops = numberOfStops +1;
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
        return totDistance;
    }

    //The method the Service class calls to give information which this class can calculate
    public void main(boolean lastStop, String nextStop, String route){

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

    //Method for returning the totaldistance. Only called when wifi is lost and calculation done
    public int getFinalResult(){
        return totDistance;
    }

}
