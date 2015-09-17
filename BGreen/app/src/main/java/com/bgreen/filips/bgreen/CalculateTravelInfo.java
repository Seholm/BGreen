package com.bgreen.filips.bgreen;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by flarssonn on 2015-09-15.
 */
public class CalculateTravelInfo implements ICalculateTravelInfo {

    private String startPoint;
    private String latestPoint;
    private String endPoint;
    private List<String> busStops = new ArrayList<String>();
    private List<Integer> busStopsLength = new ArrayList<Integer>();

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
    //lägger till parameter för vilken rutt
    private void setStartPoint(String point, String route){

        if(route == "Lindholmen"){
            setBusStopsNorthRoute();
            setBusStopsNorthRouteDistances();
        }else{
            setBusStopsSouthRoute();
            setBusStopsSouthRouteDistance();
        }
        for(int i=0; i<busStops.size(); i++){

            if(busStops.get(i).equals(point)){
                startPoint = busStops.get(i-1);
                setLatestPoint(startPoint);
                break;
            }
        }
    }
    private String getStartPoint(){
        return startPoint;
    }
    private void setLatestPoint(String point){
        latestPoint = point;
    }

    private void setEndPoint(){
        endPoint = latestPoint;
    }
    private String getEndPoint(){
        return endPoint;
    }
    private Integer calcTotalDistance(){
        String point = getStartPoint();
        int totDistance = 0;
        while(point.equals(getStartPoint())){
            for(int i=0; i<busStops.size()+1; i++){
                if(point.equals(busStops.get(i))){
                    point = busStops.get(i+1);
                    break;
                }
            }
        }
        while(point != getStartPoint()){
            for(int i=0; i<busStops.size()+1; i++){
                if (point.equals(busStops.get(i))){
                    totDistance = totDistance + busStopsLength.get(i);
                    System.out.println(totDistance);
                    point = busStops.get(i+1);

                }
                if(point.equals(getEndPoint())){
                    break;
                }
            }
            break;
        }
        System.out.println(totDistance);
        return totDistance;
    }

    //Sätter en parameter för nästa stopp för att kunna testa
    public void main(boolean lastStop, String nextStopTest, String route){
        String nextStop = nextStopTest;
        if(startPoint==null){
            setStartPoint(nextStop, route);
        }
        else{
            setLatestPoint(nextStop);
        }
        if(lastStop == true){
            setEndPoint();
            calcTotalDistance();
        }
    }

}
