package com.bgreen.filips.bgreen;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

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
                if (busses.doesBusExist(getMacAdress(wifiManager.getScanResults()))) {
                    //if there is a ElectriCity bus in the area feed data to calculator and loop
                    System.out.println("I RETUUUURN");
                    new RetrieveBusData().execute();

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

    private String nextStop(){
        return null;
    }
    private String getRutt(){
        return null;
    }
}
