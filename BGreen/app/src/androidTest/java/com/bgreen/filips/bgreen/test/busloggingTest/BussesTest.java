package com.bgreen.filips.bgreen.test.busloggingTest;

import android.test.InstrumentationTestCase;

import com.bgreen.filips.bgreen.buslogging.model.Busses;
import com.bgreen.filips.bgreen.buslogging.model.IBusses;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Filips on 9/21/2015.
 */
public class BussesTest extends InstrumentationTestCase {

    IBusses busses;

    public void testDoesBusExist(){
        busses = new Busses();
        assertTrue(busses.doesBusExist("04:f0:21:10:09:e8"));
        assertTrue(busses.doesBusExist("04:f0:21:10:09:53"));
        assertTrue(busses.doesBusExist("04:f0:21:10:09:b7"));
        assertTrue(busses.doesBusExist("04:f0:21:10:0a:07"));
        assertTrue(busses.doesBusExist("04:f0:21:10:09:e7"));
        assertTrue(busses.doesBusExist("04:f0:21:10:09:b8"));
        assertTrue(busses.doesBusExist("04:f0:21:10:09:df"));
        assertFalse(busses.doesBusExist("TEST"));
        assertFalse(busses.doesBusExist("13:95:13:5f:20"));

    }
    public void testDoesBusExistList(){
        busses = new Busses();
        List<String> busList= new ArrayList<>();
        busList.add("Test");
        assertFalse(busses.doesBusExist(busList));
        busList.add("04:f0:21:10:09:e8");
        assertTrue(busses.doesBusExist(busList));
        busList.add("04:f0:21:10:09:b7");
        assertTrue(busses.doesBusExist(busList));

    }
}
