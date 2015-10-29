package com.bgreen.filips.bgreen.buslogging.reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

import com.bgreen.filips.bgreen.buslogging.controller.IdentifyTravelService;
import com.bgreen.filips.bgreen.buslogging.model.Busses;
import com.bgreen.filips.bgreen.buslogging.model.IBusses;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Filip on 2015-09-13.
 */
public class MinuteReciever extends BroadcastReceiver {
    // the class works as a reciever which checks if a specific wifi is in the area
    //and loops every 1 minute

    @Override
    public void onReceive(Context context, Intent intent) {
        WifiManager wifiManager=(WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        List<ScanResult> wifiScanList = wifiManager.getScanResults();
        List<String> bssid= new ArrayList<>(); // A list containing BSSIDS
        IBusses busses = new Busses(); //Represents info about the busses

        for (ScanResult result:wifiScanList  ){
            //creates a list of all the BSSIDS in the area
            bssid.add(result.BSSID);
        }

        if(busses.doesBusExist(bssid)){
            // if any of the BSSIDS is a Electrycity BSSID, start service
            Intent serviceIntent = new Intent(context,IdentifyTravelService.class);
            context.startService(serviceIntent);
        }

    }
}
