package com.bgreen.filips.bgreen.buslogging;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Filips on 9/15/2015.
 */
public class Busses implements IBusses {

    Map<String,String> busses;
    public Busses(){

        busses = new HashMap<String,String>();
        // a Map with id and mac adresses of al the busses

        busses.put("04:f0:21:10:09:df","100021");//reg.no: EPO 136
        busses.put("04:f0:21:10:09:e8","100022");//reg.no: EPO 143
        busses.put("04:f0:21:10:09:53","171327");//reg.no: EOG 622
        busses.put("04:f0:21:10:09:b7","171330");//reg.no: EOG 634
        busses.put("04:f0:21:10:0a:07","100020");//reg.no: EPO 131
        //reg.no: EOG 606

        busses.put("5c:35:3b:e8:8a:fb","171330"); //HEMMA HOS SEHOLM
        busses.put("74:a0:2f:cc:fc:3e","171330");
        busses.put("3c:0e:23:69:87:50","100022");//FYSIK
        busses.put("c0:25:5c:e4:84:0e","100022");

        // [SSID: Vasttrafik, BSSID: f2:21:10:09:53:00, capabilities: [ESS], level: -41, frequency: 2462, timestamp: 96795888626, distance: ?(cm), distanceSd: ?(cm),
        // SSID: ElectriCity, BSSID: 04:f0:21:10:09:53, capabilities: [ESS], level: -42, frequency: 2462, timestamp: 96795888613, distance: ?(cm), distanceSd: ?(cm),
        // SSID: Alexander Maxen 4G, BSSID: 1c:8e:5c:d3:2a:63, capabilities: [WPA2-PSK-CCMP+TKIP][WPS][ESS], level: -81, frequency: 2427, timestamp: 96795888587, distance: ?(cm), distanceSd: ?(cm),
        // SSID: 23kvm, BSSID: 34:36:3b:bf:b2:e0, capabilities: [WPA2-PSK-CCMP][ESS], level: -84, frequency: 2462, timestamp: 96795888651, distance: ?(cm), distanceSd: ?(cm), SSID: Popsans, BSSID: 10:c3:7b:d7:35:4c, capabilities: [WPA2-PSK-CCMP][WPS][ESS], level: -86, frequency: 2437, timestamp: 96784644889, distance: ?(cm), distanceSd: ?(cm),
        // SSID: ElectriCity, BSSID: 04:f0:21:10:09:e7, capabilities: [ESS], level: -46, frequency: 2462, timestamp: 96795888601, distance: ?(cm), distanceSd: ?(cm), SSID: WisperLink-167, BSSID: 00:27:22:04:a0:d7, capabilities: [WPA2-PSK-CCMP][ESS], level: -89, frequency: 5520, timestamp: 96795888695, distance: ?(cm), distanceSd: ?(cm), SSID: kndevices, BSSID: 9c:5d:12:23:94:e6, capabilities: [WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS], level: -90, frequency: 5200, timestamp: 96795888666, distance: ?(cm), distanceSd: ?(cm), SSID: AventusCC, BSSID: c4:0a:cb:5c:84:31, capabilities: [WPA2-PSK-TKIP][ESS], level: -86, frequency: 2427, timestamp: 96795888561, distance: ?(cm), distanceSd: ?(cm), SSID: WLAN@CORP, BSSID: 00:e1:6d:b3:8e:ff, capabilities: [WPA2-EAP-CCMP][ESS], level: -91, frequency: 5260, timestamp: 96795888681, distance: ?(cm), distanceSd: ?(cm), SSID: Aventus, BSSID: c4:0a:cb:5c:84:30, capabilities: [WEP][ESS], level: -88, frequency: 2427, timestamp: 96795888574, distance: ?(cm), distanceSd: ?(cm),
        // SSID: Vasttrafik, BSSID: f2:21:10:i09:e7:00, capabilities: [ESS], level: -47, frequency: 2462, timestamp: 96795888638, distance: ?(cm), distanceSd: ?(cm)]


        //15761-15761/com.bgreen.filips.bgreen I/System.out﹕ [SSID: eduroam, BSSID: 88:1d:fc:44:56:bf, capabilities: [WPA2-EAP-CCMP][ESS], level: -90, frequency: 5520, timestamp: 115066428685, distance: ?(cm), distanceSd: ?(cm), SSID: eduroam, BSSID: 88:1d:fc:44:56:b0, capabilities: [WPA2-EAP-CCMP][ESS], level: -88, frequency: 2462, timestamp: 115076711726, distance: ?(cm), distanceSd: ?(cm), SSID: ElectriCity, BSSID: 04:f0:21:10:09:e8, capabilities: [ESS], level: -55, frequency: 2462, timestamp: 115076711713, distance: ?(cm), distanceSd: ?(cm), SSID: Vasttrafik, BSSID: f2:21:10:09:e8:00, capabilities: [ESS], level: -73, frequency: 2462, timestamp: 115076711752, distance: ?(cm), distanceSd: ?(cm), SSID: ElectriCity, BSSID: 06:f0:21:11:59:3d, capabilities: [ESS], level: -64, frequency: 5220, timestamp: 115076711765, distance: ?(cm), distanceSd: ?(cm), SSID: Vasttrafik, BSSID: 02:f0:21:11:59:3d, capabilities: [ESS], level: -63, frequency: 5220, timestamp: 115076711778, distance: ?(cm), distanceSd: ?(cm), SSID: ElectriCity, BSSID: 06:f0:21:11:59:70, capabilities: [ESS], level: -54, frequency: 2452, timestamp: 115076711688, distance: ?(cm), distanceSd: ?(cm), SSID: Vasttrafik, BSSID: 02:f0:21:11:59:70, capabilities: [ESS], level: -53, frequency: 2452, timestamp: 115076711701, distance: ?(cm), distanceSd: ?(cm), SSID: NOMAD, BSSID: 88:1d:fc:44:56:be, capabilities: [ESS], level: -92, frequency: 5520, timestamp: 115076711791, distance: ?(cm), distanceSd: ?(cm), SSID: NOMAD, BSSID: 88:1d:fc:44:56:b1, capabilities: [ESS], level: -89, frequency: 2462, timestamp: 115076711740, distance: ?(cm), distanceSd: ?(cm), SSID: eduroam, BSSID: 7c:0e:ce:7a:65:80, capabilities: [WPA2-EAP-CCMP][ESS], level: -85, frequency: 2437, timestamp: 115066428656, distance: ?(cm), distanceSd: ?(cm)]
    }

    @Override
    public String getCurrentBus(List<String> bssids) {
        //gets the busses id from a MAC-adress
        for (String bssid : bssids){
            if(busses.containsKey(bssid)){
                return busses.get(bssid);
            }
        }

        return null;
    }

    @Override
    public boolean doesBusExist(String BSSID) {
        return busses.containsKey(BSSID);
    }
    @Override
    public boolean doesBusExist(List<String> BSSIDs) {
        for(String bssid : BSSIDs){
            if(doesBusExist(bssid)){
                System.out.println("matching mac adress:"+bssid);
                return true;
            }
        }
        return false;
    }

}
