package com.bgreen.filips.bgreen.buslogging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Filip on 2015-09-13.
 */
public class MinuteReciever extends BroadcastReceiver {
    // the class works as a reciever which checks if a specific wifi is in the area

    @Override
    public void onReceive(Context context, Intent intent) {
        WifiManager wifiManager=(WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        List<ScanResult> wifiScanList = wifiManager.getScanResults();
        List<String> bssid= new ArrayList<>(); // A list containing mac-adresses
        IBusses busses = new Busses();
        System.out.println(wifiScanList.toString());

        for (ScanResult result:wifiScanList  ){
            //creates a list of all the MAC-adresses in the area
            bssid.add(result.BSSID);
        }

        if(busses.doesBusExist(bssid)){
            // if any of the MAC-adresses is a Electrycity MAC-adress, start service
            Intent serviceIntent = new Intent(context,IdentifyTravelService.class);
            context.startService(serviceIntent);
        }

    }
}
