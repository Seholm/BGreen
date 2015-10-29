package com.bgreen.filips.bgreen.buslogging.model;

import com.bgreen.filips.bgreen.buslogging.model.IBusses;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Filips on 9/15/2015.
 */
public class Busses implements IBusses {

    Map<String,String> busses;
    public Busses() {

        busses = new HashMap<String, String>();
        // a Map with id and BSSID of all the busses

        busses.put("04:f0:21:10:09:df", "100021");//reg.no: EPO 136
        busses.put("04:f0:21:10:09:e8", "100022");//reg.no: EPO 143
        busses.put("04:f0:21:10:09:53", "171327");//reg.no: EOG 622
        busses.put("04:f0:21:10:09:b7", "171330");//reg.no: EOG 634
        busses.put("04:f0:21:10:0a:07", "100020");//reg.no: EPO 131
        busses.put("04:f0:21:10:09:e7", "171234");//reg.no: EOG 606
        busses.put("04:f0:21:10:09:b8", "171164");//reg.no: EOG 604
    }

    @Override
    public String getCurrentBus(List<String> bssids) {
        //gets the busses id from a BSSID
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
                return true;
            }
        }
        return false;
    }

}
