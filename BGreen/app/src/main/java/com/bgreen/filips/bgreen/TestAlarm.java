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
public class TestAlarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("I TESTALARM");
        WifiManager wifiManager=(WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        List<ScanResult> wifiScanList = wifiManager.getScanResults();
        //System.out.println(wifiScanList.toString());

        for (ScanResult result:wifiScanList  ){
            System.out.println(result.SSID);
            if(result.SSID.equals("comhem_E6B448")){
                System.out.println("I IF SATS***************************");
                context.startService(new Intent(context, MyService.class));
            }
        }

    }
}
