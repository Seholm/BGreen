package com.bgreen.filips.bgreen;

import java.util.Arrays;
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
        busses.put("00:13:95:13:49:f5","Ericsson$100020");
        busses.put("00:13:95:13:4b:be","Ericsson$100021");
        busses.put("00:13:95:14:3b:f0","Ericsson$100022");
        busses.put("00:13:95:14:69:8a","Ericsson$171164");
        busses.put("00:13:95:13:49:f7","Ericsson$171234");
        busses.put("00:13:95:13:62:96","Ericsson$171235");
        busses.put("00:13:95:13:62:96","Ericsson$171327");
        busses.put("00:13:95:13:4b:bc","Ericsson$171328");
        busses.put("00:13:95:14:3b:f2","Ericsson$171329");
        busses.put("00:13:95:13:5f:20","Ericsson$171330");

        List<String> BSSIDs = Arrays.asList("00:13:95:13:49:f5", "00:13:95:13:4b:be", "00:13:95:14:3b:f0",
                "00:13:95:14:69:8a", "00:13:95:13:49:f7", "00:13:95:0f:92:a4", "00:13:95:13:62:96",
                "00:13:95:13:4b:bc", "00:13:95:14:3b:f2", "00:13:95:13:5f:20");

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
        return busses.containsValue(BSSID);
    }
    @Override
    public boolean doesBusExist(List<String> BSSIDs) {
        for(String bssid : BSSIDs){
            if(doesBusExist(bssid)){
                return true;
            }
        }
        return false;
    }

}
