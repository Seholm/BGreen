package com.bgreen.filips.bgreen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

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

        for (ScanResult result:wifiScanList  ){
            if(result.SSID.equals("eduroam")){
                //if there is a wifi in the area with the name eduroam
                context.startService(new Intent(context, IdentifyTravelService.class));
            }
        }

    }
}
