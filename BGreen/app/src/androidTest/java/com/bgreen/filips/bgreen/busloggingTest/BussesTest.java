package com.bgreen.filips.bgreen.busloggingTest;

import android.test.InstrumentationTestCase;

import com.bgreen.filips.bgreen.buslogging.Busses;
import com.bgreen.filips.bgreen.buslogging.IBusses;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Filips on 9/21/2015.
 */
public class BussesTest extends InstrumentationTestCase {

    public void testDoesBusExist(){
        IBusses busses = new Busses();
        assertTrue(busses.doesBusExist("00:13:95:13:49:f5"));
        assertTrue(busses.doesBusExist("00:13:95:13:4b:be"));
        assertTrue(busses.doesBusExist("00:13:95:14:3b:f0"));
        assertTrue(busses.doesBusExist("00:13:95:14:69:8a"));
        assertTrue(busses.doesBusExist("00:13:95:13:49:f7"));
        assertTrue(busses.doesBusExist("00:13:95:13:62:96"));
        assertTrue(busses.doesBusExist("00:13:95:0f:92:a4"));
        assertTrue(busses.doesBusExist("00:13:95:13:4b:bc"));
        assertTrue(busses.doesBusExist("00:13:95:14:3b:f2"));
        assertTrue(busses.doesBusExist("00:13:95:13:5f:20"));
        assertFalse(busses.doesBusExist("TEST"));
        assertFalse(busses.doesBusExist("13:95:13:5f:20"));

    }
    public void testDoesBusExistList(){
        IBusses busses = new Busses();
        List<String> busList= new ArrayList<>();
        busList.add("Test");
        assertFalse(busses.doesBusExist(busList));
        busList.add("00:13:95:13:5f:20");
        assertTrue(busses.doesBusExist(busList));
        busList.add("00:13:95:14:3b:f0");
        assertTrue(busses.doesBusExist(busList));

    }
}
