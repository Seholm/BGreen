package com.bgreen.filips.bgreen;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class IdentifyTravelService extends Service {

    private WifiManager wifiManager;
    private IBusses busses;
    private Handler handler;
    private Runnable onBusTask;
    private ICalculateTravelInfo calculator;

    public IdentifyTravelService() {
    }

    @Override
    public void onCreate(){

        System.out.println("I on create");
        wifiManager=(WifiManager)getSystemService(Context.WIFI_SERVICE);
        busses = new Busses();
        calculator = new CalculateTravelInfo();

        runTask();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){

        return super.onStartCommand(intent,flags,startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void runTask(){

        onBusTask = new Runnable() {

            @Override
            public void run() {
                List<String> macAdresses = getMacAdress(wifiManager.getScanResults());
                if (busses.doesBusExist(macAdresses)) {
                    //if there is a ElectriCity bus in the area feed data to calculator and loop
                    String nextStop = null;
                    String rutt = null;
                    try {
                        nextStop =new RetrieveBusData().execute(busses.getCurrentBus(macAdresses), "Next_Stop").get();
                    }catch (Exception e){}
                    try {
                        rutt = new RetrieveBusData().execute(busses.getCurrentBus(macAdresses), "Journey_Info").get();
                    }catch (Exception e){}
                    System.out.println(rutt);
                    System.out.println(nextStop);

                    //calculator.main(true,nextStop(),getRutt());
                    handler.postDelayed(this, 10000); //loops run method every 10 seconds
                } else { //if there is no busWifi nearby the process is done and data is sent to
                        // database and the service is finished
                    //calculator.main(false,nextStop(),getRutt());
                    //calculator.getFinalResult();
                    //TODO:Send to database
                    stopSelf();
                }
            }
        };

        handler = new Handler();
        handler.post(onBusTask);

    }

    private List<String> getMacAdress(List<ScanResult> wifiList){

        List<String> bssid= new ArrayList<>();
        for (ScanResult result:wifiList  ){
            //creates a list of all the MAC-adresses in the area
            bssid.add(result.BSSID);
        }
        return bssid;
    }
}
