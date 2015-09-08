package com.bgreen.filips.bgreen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

import java.util.List;

/**
 * Created by Filip on 2015-09-07.
 */
public class TESTBroadCastRecivever extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        WifiManager wifiManager;
        Intent startIntent = new Intent();
        System.out.println("I RECIEVER!!*************************");

        wifiManager=(WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        List<ScanResult> wifiScanList = wifiManager.getScanResults();
            System.out.println(wifiScanList.toString());

    }
}
