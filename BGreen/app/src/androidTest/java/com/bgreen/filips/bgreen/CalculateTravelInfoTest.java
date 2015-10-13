package com.bgreen.filips.bgreen;

import android.test.InstrumentationTestCase;

import static junit.framework.Assert.*;

/**
 * Created by flarssonn on 2015-09-21.
 * edited by seholm on 2015-10-10
 */
public class CalculateTravelInfoTest extends InstrumentationTestCase {


    //Test correct startpoint with one stop in total
    public void testStartPoint1(){
        String startPoint = "GötaplatsenA";
        String nextStop = "ValandC";
        ICalculateTravelInfo calc = new CalculateTravelInfo();
        calc.main(false,nextStop,"Lindholmen");
        assertEquals(startPoint,calc.getStartPoint());
    }

    //Test correct startpoint with multiple stops
    public void testStartPoint2(){
        String startPoint = "GötaplatsenA";
        String nextStop1 = "ValandC";
        String nextStop2 = "KungsportsplatsenC";
        String nextStop3 = "BrunnsparkenB";
        String nextStop4 = "Lilla BommenB";
        ICalculateTravelInfo calc = new CalculateTravelInfo();
        calc.main(false,nextStop1,"Lindholmen");
        calc.main(false,nextStop1,"Lindholmen");
        calc.main(false,nextStop2,"Lindholmen");
        calc.main(false,nextStop2,"Lindholmen");
        calc.main(false,nextStop3,"Lindholmen");
        calc.main(false,nextStop3,"Lindholmen");
        calc.main(false,nextStop4,"Lindholmen");
        calc.main(true,nextStop4,"Lindholmen");
        assertTrue(startPoint.equals(calc.getStartPoint()));
    }

    //Test correct endpoint with one stop in total
    public void testEndPoint1(){
        String endPoint = "ValandC";
        String nextStop = "ValandC";
        ICalculateTravelInfo calc = new CalculateTravelInfo();
        calc.main(false, nextStop, "Lindholmen");
        calc.main(true, nextStop, "Lindholmen");
        assertTrue(endPoint.equals(calc.getEndPoint()));
    }

    //Test correct endpoint with multiple stops in total
    public void testEndPoint2(){
        String endPoint = "Lilla BommenB";
        String nextStop1 = "ValandC";
        String nextStop2 = "KungsportsplatsenC";
        String nextStop3 = "BrunnsparkenB";
        String nextStop4 = "Lilla BommenB";
        ICalculateTravelInfo calc = new CalculateTravelInfo();
        calc.main(false,nextStop1, "Lindholmen");
        calc.main(false,nextStop1, "Lindholmen");
        calc.main(false,nextStop2, "Lindholmen");
        calc.main(false,nextStop2, "Lindholmen");
        calc.main(false,nextStop3, "Lindholmen");
        calc.main(false, nextStop3, "Lindholmen");
        calc.main(false, nextStop4, "Lindholmen");
        calc.main(true, nextStop4, "Lindholmen");
        assertTrue(endPoint.equals(calc.getEndPoint()));
    }

    //Test correct endpoint with one stop in total
    public void testLatestPoint1(){
        String latestPoint = "ValandC";
        String nextStop1 = "ValandC";
        ICalculateTravelInfo calc = new CalculateTravelInfo();
        calc.main(false, nextStop1, "Lindholmen");
        assertTrue(latestPoint.equals(calc.getLatestPoint()));
    }

    //Test correct endpoint with multiple stops in total
    public void testLatestPoint2(){
        String latestPoint = "BrunnsparkenB";
        String nextStop1 = "GötaplatsenA";
        String nextStop2 = "ValandC";
        String nextStop3 = "KungsportsplatsenC";
        String nextStop4 = "BrunnsparkenB";
        ICalculateTravelInfo calc = new CalculateTravelInfo();
        calc.main(false, nextStop1, "Lindholmen");
        calc.main(false, nextStop1, "Lindholmen");
        calc.main(false, nextStop2, "Lindholmen");
        calc.main(false, nextStop2, "Lindholmen");
        calc.main(false, nextStop3, "Lindholmen");
        calc.main(false, nextStop3, "Lindholmen");
        calc.main(false, nextStop4, "Lindholmen");
        calc.main(false, nextStop4, "Lindholmen");
        assertTrue(latestPoint.equals(calc.getLatestPoint()));
    }

    //Test correct distance calculation with one stop. Between götaplatsen and valand
    public void testCalcTotalDistance1(){
        int totDistance = 400;
        String nextStop1 = "ValandC";
        ICalculateTravelInfo calc = new CalculateTravelInfo();
        calc.main(false, nextStop1, "Lindholmen");
        calc.main(true, nextStop1, "Lindholmen");
        assertTrue(totDistance == calc.getFinalResult());

    }

    //Test correct distance calculation with multiple stops between kapell and brunns
    public void testCalcTotalDistance2(){
        int totDistance = 1850;
        String nextStop1 = "GötaplatsenA";
        String nextStop2 = "ValandC";
        String nextStop3 = "KungsportsplatsenC";
        String nextStop4 = "BrunnsparkenB";
        ICalculateTravelInfo calc = new CalculateTravelInfo();
        calc.main(false, nextStop1, "Lindholmen");
        calc.main(false, nextStop1, "Lindholmen");
        calc.main(false, nextStop2, "Lindholmen");
        calc.main(false, nextStop2, "Lindholmen");
        calc.main(false, nextStop3, "Lindholmen");
        calc.main(false, nextStop3, "Lindholmen");
        calc.main(false, nextStop4, "Lindholmen");
        calc.main(true, nextStop4, "Lindholmen");
        assertTrue(totDistance == calc.getFinalResult());

    }

    //Test correct distance calculation with multiple stops and other route brunns to götapl
    public void testCalcTotalDistance3(){
        int totDistance = 1530;
        String nextStop1 = "BrunnsparkenA";
        String nextStop2 = "KungsportsplD";
        String nextStop3 = "ValandD";
        String nextStop4 = "GötaplatsenB";
        ICalculateTravelInfo calc = new CalculateTravelInfo();
        calc.main(false, nextStop1, "Johanneberg");
        calc.main(false, nextStop1, "Johanneberg");
        calc.main(false, nextStop2, "Johanneberg");
        calc.main(false, nextStop2, "Johanneberg");
        calc.main(false, nextStop3, "Johanneberg");
        calc.main(false, nextStop3, "Johanneberg");
        calc.main(false, nextStop4, "Johanneberg");
        calc.main(true, nextStop4, "Johanneberg");
        assertTrue(totDistance == calc.getFinalResult());

    }

    //Test correct distance calculation with one stop. Between götaplatsen and valand with null inbetween
    public void testCalcTotalDistanceWithNull1(){
        int totDistance = 400;
        String nextStop1 = "ValandC";
        ICalculateTravelInfo calc = new CalculateTravelInfo();
        calc.main(false, nextStop1, "Lindholmen");
        calc.main(false,null,null);
        calc.main(true, nextStop1, "Lindholmen");
        assertTrue(totDistance == calc.getFinalResult());

    }

    //Test correct distance calculation with one stop. Between götaplatsen and valand with null as last stop
    public void testCalcTotalDistanceWithNull2(){
        int totDistance = 400;
        String nextStop1 = "ValandC";
        ICalculateTravelInfo calc = new CalculateTravelInfo();
        calc.main(false, nextStop1, "Lindholmen");
        calc.main(true, null, null);
        assertTrue(totDistance == calc.getFinalResult());

    }

    //Test correct distance calculation with only null
    public void testCalcTotalDistanceWithNull3(){
        int totDistance = 0;
        ICalculateTravelInfo calc = new CalculateTravelInfo();
        calc.main(false, null, null);
        calc.main(true, null, null);
        System.out.println(calc.getFinalResult());
        assertTrue(totDistance == calc.getFinalResult());

    }

    //Test correct distance calculation with multiple stops and other route brunns to götapl
    public void testCalcTotalDistanceWithNull4(){
        int totDistance = 1530;
        String nextStop1 = "BrunnsparkenA";
        String nextStop2 = "KungsportsplD";
        String nextStop3 = "ValandD";
        String nextStop4 = "GötaplatsenB";
        ICalculateTravelInfo calc = new CalculateTravelInfo();
        calc.main(false, null,null);
        calc.main(false, nextStop1, "Johanneberg");
        calc.main(false, nextStop1, "Johanneberg");
        calc.main(false, nextStop2, "Johanneberg");
        calc.main(false, null,null);
        calc.main(false, nextStop2, "Johanneberg");
        calc.main(false, nextStop3, "Johanneberg");
        calc.main(false, nextStop3, "Johanneberg");
        calc.main(false, null,null);
        calc.main(false, nextStop4, "Johanneberg");
        calc.main(true, null,null);
        assertTrue(totDistance == calc.getFinalResult());

    }

    //Test correct distance calculation with one stop. Between götaplatsen and valand with null as last stop
    public void testCalcTotalDistanceWithEjITrafik1(){
        int totDistance = 450;
        String nextStop1 = "Sven Hultins pl.A";
        ICalculateTravelInfo calc = new CalculateTravelInfo();
        calc.main(false, nextStop1, "Johanneberg");
        calc.main(true, null, "Ej i trafik");
        assertTrue(totDistance == calc.getFinalResult());

    }

    //Test correct distance calculation with one stop. Between götaplatsen and valand with null as last stop
    public void testCalcTotalDistanceWithEjITrafik2(){
        int totDistance = 400;
        String nextStop1 = "ValandC";
        ICalculateTravelInfo calc = new CalculateTravelInfo();
        calc.main(false, nextStop1, "Lindholmen");
        calc.main(true, nextStop1, "Ej i trafik");
        assertTrue(totDistance == calc.getFinalResult());

    }

}
