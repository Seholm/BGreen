package com.bgreen.filips.bgreen.buslogging.controller;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.IBinder;

import com.bgreen.filips.bgreen.buslogging.model.Busses;
import com.bgreen.filips.bgreen.buslogging.model.CalculateTravelModel;
import com.bgreen.filips.bgreen.buslogging.model.IBusses;
import com.bgreen.filips.bgreen.buslogging.model.ICalculateTravelModel;
import com.bgreen.filips.bgreen.buslogging.service.DatabaseService;
import com.bgreen.filips.bgreen.buslogging.service.IDatabaseService;
import com.bgreen.filips.bgreen.buslogging.service.RetrieveBusData;
import com.bgreen.filips.bgreen.profile.model.IUserHandler;
import com.bgreen.filips.bgreen.profile.model.UserHandler;
import com.parse.Parse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Filips on 10/3/2015.
 */

public class IdentifyTravelService extends Service {

    // a class that loops every 10 second when the user is on a buss, feeds data to a algorithm and sends data to the database

    private IBusses busses;
    private Handler handler;
    private Runnable onBusTask; //runnable that loops every 10 second
    private ICalculateTravelModel calculator; //algorithm that calculates the distance travelled
    private final String PARSE_CLIENT_KEY = "0qM0pkPsSmWoEuhqbN4iKHbbSfmgXwLwEJy7ZUHV";
    private final String PARSE_APPLICATION_ID = "Wi3ExMtOI5koRFc29GiaE3C4qmukjPokmETpcPQA";
    private IUserHandler userhandler; //used to get the current user
    private String nextStop;
    private String rutt;
    private boolean shouldILoop; //keeps track if this runnable should keep on running or not

    public IdentifyTravelService() {
    }

    @Override
    public void onCreate(){

        try {
            Parse.initialize(this, PARSE_APPLICATION_ID, PARSE_CLIENT_KEY);
        }catch (Exception e){
            //Parse already initialized, nothing should happen in that case
        }

        busses = new Busses();
        calculator = new CalculateTravelModel();
        userhandler = new UserHandler(this);

        runTask(); // starts the runnable that loops every 10 second when on a buss

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){

        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void runTask(){

        onBusTask = new Runnable() {

            @Override
            public void run() {
                shouldILoop = true;
                List<String> macAdresses = getBSSID(((WifiManager)getSystemService(Context.WIFI_SERVICE)).getScanResults());
                // A list of all the BSSIDs in the area
                if (busses.doesBusExist(macAdresses)) {
                    //if there is a ElectriCity bus in the area feed data to calculator and loop
                    IDatabaseService service = new DatabaseService();
                    setNextStopAndRoute(macAdresses);
                    if(rutt!=null) {
                        if (rutt.equals("Ej i trafik")) { //this is a special case which happens rarely
                            specialCaseOccured(service);
                        } else { //This is the normal flow
                            calculator.main(nextStop, rutt); //feeds data to the algorithm that calculates the distance
                        }
                    }
                    if(shouldILoop) {
                        handler.postDelayed(this, 10000); //tells the runnable to run again in 10 seconds
                    }else{
                        stopSelf(); //kills the service if it shouldn't loop
                    }
                } else { //if there is no busWifi nearby the process is done and data is sent to the database
                    setNextStopAndRoute(macAdresses);
                    calculator.main(nextStop,rutt); //feeds data to the algorithm that calculates
                    DatabaseService service = new DatabaseService();
                    service.saveBusTrip(calculator.getFinalResult(),userhandler.getUserID());
                    calculator.clear();
                    stopSelf();
                    //saves data and kills the service
                }
            }
        };

        handler = new Handler();
        handler.post(onBusTask);

    }

    private void setNextStopAndRoute(List<String> macAdresses){
        try {
            //get info from Electrycity API. about next stop
            this.nextStop =new RetrieveBusData().execute(busses.getCurrentBus(macAdresses), "Next_Stop").get();
        }catch (Exception e){} //Nothing should happen since the service allways runs in the background whithout possibility to turn off
        try {
            //gets info from Electrycity API. about current route
            this.rutt = new RetrieveBusData().execute(busses.getCurrentBus(macAdresses), "Journey_Info").get();
        }catch (Exception e){} //Nothing should happen since the service allways runs in the background whithout possibility to turn off

    }

    private List<String> getBSSID(List<ScanResult> wifiList){
        //converts scanresults to a list of BSSID'S

        List<String> bssid= new ArrayList<>();
        for (ScanResult result:wifiList  ){
            //creates a list of all the MAC-adresses in the area
            bssid.add(result.BSSID);
        }
        return bssid;
    }

    private void specialCaseOccured(IDatabaseService service){
        //this happens when the buss changes route from lindholmen to chalmers or vice versa
        calculator.main(nextStop, rutt); //feeds data to the algorithm that calculates the distance
        if(calculator.getFinalResult() >0) {
            service.saveBusTrip(calculator.getFinalResult(), userhandler.getUserID());
            calculator.clear();
            shouldILoop = false;
            //saves to database and clears the algorithm and turns off the looping
        }

    }
}
