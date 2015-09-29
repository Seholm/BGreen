package com.bgreen.filips.bgreen;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.IBinder;

import com.bgreen.filips.bgreen.buslogging.DatabaseService;
import com.bgreen.filips.bgreen.buslogging.IDatabaseService;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class IdentifyTravelService extends Service {

    private WifiManager wifiManager;
    private IBusses busses;
    private Handler handler;
    private Runnable onBusTask;
    private ICalculateTravelInfo calculator;
    private int count;
    private final String PARSE_CLIENT_KEY = "0qM0pkPsSmWoEuhqbN4iKHbbSfmgXwLwEJy7ZUHV";
    private final String PARSE_APPLICATION_ID = "Wi3ExMtOI5koRFc29GiaE3C4qmukjPokmETpcPQA";

    public IdentifyTravelService() {
    }

    @Override
    public void onCreate(){

        Parse.initialize(this, PARSE_APPLICATION_ID, PARSE_CLIENT_KEY);

        System.out.println("I on create");
        wifiManager=(WifiManager)getSystemService(Context.WIFI_SERVICE);
        busses = new Busses();
        calculator = new CalculateTravelInfo();
        count =0;

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
                List<String> macAdresses = getMacAdress(wifiManager.getScanResults());
                if (busses.doesBusExist(macAdresses)) {
                    //if there is a ElectriCity bus in the area feed data to calculator and loop
                    String nextStop = null;
                    String rutt = null;
                    //count++;
                    IDatabaseService service = new DatabaseService();
                    try {
                        nextStop =new RetrieveBusData().execute(busses.getCurrentBus(macAdresses), "Next_Stop").get();
                    }catch (Exception e){}
                    try {
                        rutt = new RetrieveBusData().execute(busses.getCurrentBus(macAdresses), "Journey_Info").get();
                    }catch (Exception e){}
                    if(rutt.equals("Ej i Trafik")){
                        if(calculator.getFinalResult()>0){
                            calculator.main(true,nextStop,rutt);
                            service.saveBusTrip(calculator.getFinalResult(),"fbWLxk4f86");
                        }
                    }else {
                        System.out.println(count);
                        System.out.println(nextStop);
                        System.out.println(rutt);
                        calculator.main(false, nextStop, rutt);
                    }

                    handler.postDelayed(this, 10000); //loops run method every 10 seconds
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
                    calculator.main(true,nextStop,rutt);
                    System.out.println("DET ÄR OMÖJLIGT:" +calculator.getFinalResult());
                    DatabaseService service = new DatabaseService();
                    service.saveBusTrip(calculator.getFinalResult(),"fbWLxk4f86");
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

    private void updateProfileStats(ParseObject user, int distance) {
        user.increment("totaldistance", distance);
        user.increment("bustTrips");
    }
}
