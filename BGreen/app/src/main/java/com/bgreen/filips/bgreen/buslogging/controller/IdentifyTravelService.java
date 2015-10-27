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

public class IdentifyTravelService extends Service {

    private IBusses busses;
    private Handler handler;
    private Runnable onBusTask;
    private ICalculateTravelModel calculator;
    private final String PARSE_CLIENT_KEY = "0qM0pkPsSmWoEuhqbN4iKHbbSfmgXwLwEJy7ZUHV";
    private final String PARSE_APPLICATION_ID = "Wi3ExMtOI5koRFc29GiaE3C4qmukjPokmETpcPQA";
    private IUserHandler userhandler;

    public IdentifyTravelService() {
    }

    @Override
    public void onCreate(){

        try {
            Parse.initialize(this, PARSE_APPLICATION_ID, PARSE_CLIENT_KEY);
        }catch (Exception e){
            //Parse already initialized
        }

        busses = new Busses();
        calculator = new CalculateTravelModel();
        userhandler = new UserHandler(this);

        runTask();

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
                boolean shouldILoop = true;
                List<String> macAdresses = getBSSID(((WifiManager)getSystemService(Context.WIFI_SERVICE)).getScanResults());
                if (busses.doesBusExist(macAdresses)) {
                    //if there is a ElectriCity bus in the area feed data to calculator and loop
                    String nextStop = null;
                    String rutt = null;
                    IDatabaseService service = new DatabaseService();
                    try {
                        nextStop =new RetrieveBusData().execute(busses.getCurrentBus(macAdresses), "Next_Stop").get();
                    }catch (Exception e){}
                    try {
                        rutt = new RetrieveBusData().execute(busses.getCurrentBus(macAdresses), "Journey_Info").get();
                    }catch (Exception e){}
                    System.out.println("rutt:" + rutt);
                    if(rutt!=null) {
                        if (rutt.equals("Ej i trafik")) {
                            System.out.println("III EJ I TRAFIK");
                            calculator.main(nextStop, rutt);
                            System.out.println("FINAL RESULT ÄR:" +"  "+ calculator.getFinalResult());
                            if(calculator.getFinalResult() >0) {
                                System.out.println("III EJ I TRAFIK, SKA SPARA" + calculator.getFinalResult() + "PÅ DATABAS");
                                service.saveBusTrip(calculator.getFinalResult(), userhandler.getUserID());
                                calculator.clear();
                                shouldILoop = false;
                            }
                        } else {
                            System.out.println(nextStop);
                            System.out.println(rutt);
                            calculator.main(nextStop, rutt);
                        }
                    }
                    if(shouldILoop) {
                        handler.postDelayed(this, 10000); //loops run method every 10 seconds
                    }else{
                        stopSelf();
                    }
                } else { //if there is no busWifi nearby the process is done and data is sent to
                    String nextStop = null;
                    String rutt = null;
                    try {
                        nextStop =new RetrieveBusData().execute(busses.getCurrentBus(macAdresses), "Next_Stop").get();
                    }catch (Exception e){}
                    try {
                        rutt = new RetrieveBusData().execute(busses.getCurrentBus(macAdresses), "Journey_Info").get();
                    }catch (Exception e){}
                    System.out.println(nextStop);
                    calculator.main(nextStop,rutt);
                    System.out.println("DET ÄR OMÖJLIGT:" +calculator.getFinalResult());
                    DatabaseService service = new DatabaseService();
                    service.saveBusTrip(calculator.getFinalResult(),userhandler.getUserID());
                    calculator.clear();
                    stopSelf();
                }
            }
        };

        handler = new Handler();
        handler.post(onBusTask);

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
}
