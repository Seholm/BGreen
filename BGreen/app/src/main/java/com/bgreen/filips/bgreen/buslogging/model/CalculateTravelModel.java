package com.bgreen.filips.bgreen.buslogging.model;

import com.bgreen.filips.bgreen.buslogging.model.ICalculateTravelModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by flarssonn on 2015-10-13.
 */
public class CalculateTravelModel implements ICalculateTravelModel {
    private final List<String> busStops=new ArrayList<>();
    private final List<Integer> busStopsLength = new ArrayList<>();
    private List<String> tripStops = new ArrayList<>();
    private int totDistance=0;

    public void main(String nextStop, String route){
        //If busStoplist is empty, set list for which route you are on
        if(busStops.size()==0 && route !=null){
            if(route.equals("Lindholmen")){
                setBusStopsNorthRoute();
                setBusStopsNorthRouteDistances();
            }else if(route.equals("Johanneberg")){
                setBusStopsSouthRoute();
                setBusStopsSouthRouteDistance();
            }
        }
        //Try to add nextStop to tripStops list
        addToList(nextStop);


    }

    private void addToList(String nextStop){
        //If nextStop isn't already in list and nextStop is a stop on your route add nextStop to tripStops
        if(!tripStops.contains(nextStop)&&busStops.contains(nextStop)){
            tripStops.add(nextStop);
            for(int i=0; i<busStops.size(); i++){
                //Add distance for users next stop
                if(busStops.get(i).equals(nextStop)){
                    totDistance = totDistance + busStopsLength.get(i);
                }
            }
        }

    }

    public int getFinalResult(){
        return totDistance;
    }

    public List<String> getTripStops(){
        return tripStops;
    }

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

    public void clear(){
        busStops.clear();
        totDistance = 0;
    }

}
