package com.bgreen.filips.bgreen.busloggingTest;

import android.test.InstrumentationTestCase;

import com.bgreen.filips.bgreen.buslogging.CalculateTravelModel;
import com.bgreen.filips.bgreen.buslogging.ICalculateTravelModel;

import static junit.framework.Assert.*;

/**
 * Created by flarssonn on 2015-09-21.
 * edited by seholm on 2015-10-10
 */
public class CalculateTravelModelTest extends InstrumentationTestCase {



    //Test correct distance calculation with one stop. Between götaplatsen and valand
    public void testCalcTotalDistance1(){
        int totDistance = 400;
        String nextStop1 = "ValandC";
        ICalculateTravelModel calc = new CalculateTravelModel();
        calc.main(nextStop1, "Lindholmen");
        calc.main(nextStop1, "Lindholmen");
        assertTrue(totDistance == calc.getFinalResult());

    }

    //Test correct distance calculation with multiple stops between kapell and brunns
    public void testCalcTotalDistance2(){
        int totDistance = 1850;
        String nextStop1 = "GötaplatsenA";
        String nextStop2 = "ValandC";
        String nextStop3 = "KungsportsplC";
        String nextStop4 = "BrunnsparkenB";
        ICalculateTravelModel calc = new CalculateTravelModel();
        calc.main(nextStop1, "Lindholmen");
        calc.main(nextStop1, "Lindholmen");
        calc.main(nextStop2, "Lindholmen");
        calc.main(nextStop2, "Lindholmen");
        calc.main(nextStop3, "Lindholmen");
        calc.main(nextStop3, "Lindholmen");
        calc.main(nextStop4, "Lindholmen");
        calc.main(nextStop4, "Lindholmen");
        assertTrue(totDistance == calc.getFinalResult());

    }

    //Test correct distance calculation with multiple stops and other route brunns to götapl
    public void testCalcTotalDistance3(){
        int totDistance = 1530;
        String nextStop1 = "BrunnsparkenA";
        String nextStop2 = "KungsportsplD";
        String nextStop3 = "ValandD";
        String nextStop4 = "GötaplatsenB";
        ICalculateTravelModel calc = new CalculateTravelModel();
        calc.main(nextStop1, "Johanneberg");
        calc.main(nextStop1, "Johanneberg");
        calc.main(nextStop2, "Johanneberg");
        calc.main(nextStop2, "Johanneberg");
        calc.main(nextStop3, "Johanneberg");
        calc.main(nextStop3, "Johanneberg");
        calc.main(nextStop4, "Johanneberg");
        calc.main(nextStop4, "Johanneberg");
        assertTrue(totDistance == calc.getFinalResult());

    }

    //Test correct distance calculation with one stop. Between götaplatsen and valand with null inbetween
    public void testCalcTotalDistanceWithNull1(){
        int totDistance = 400;
        String nextStop1 = "ValandC";
        ICalculateTravelModel calc = new CalculateTravelModel();
        calc.main(nextStop1, "Lindholmen");
        calc.main(null,null);
        calc.main(nextStop1, "Lindholmen");
        assertTrue(totDistance == calc.getFinalResult());

    }

    //Test correct distance calculation with one stop. Between götaplatsen and valand with null as last stop
    public void testCalcTotalDistanceWithNull2(){
        int totDistance = 400;
        String nextStop1 = "ValandC";
        ICalculateTravelModel calc = new CalculateTravelModel();
        calc.main(nextStop1, "Lindholmen");
        calc.main(null, null);
        assertTrue(totDistance == calc.getFinalResult());

    }

    //Test correct distance calculation with only null
    public void testCalcTotalDistanceWithNull3(){
        int totDistance = 0;
        ICalculateTravelModel calc = new CalculateTravelModel();
        calc.main(null, null);
        calc.main(null, null);
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
        ICalculateTravelModel calc = new CalculateTravelModel();
        calc.main(null,null);
        calc.main(nextStop1, "Johanneberg");
        calc.main(nextStop1, "Johanneberg");
        calc.main(nextStop2, "Johanneberg");
        calc.main(null,null);
        calc.main(nextStop2, "Johanneberg");
        calc.main(nextStop3, "Johanneberg");
        calc.main(nextStop3, "Johanneberg");
        calc.main(null,null);
        calc.main(nextStop4, "Johanneberg");
        calc.main(null,null);
        assertTrue(totDistance == calc.getFinalResult());

    }

    //Test correct distance calculation with multiple stops and other route brunns to götapl
    public void testCalcTotalDistanceWithEjItrafik(){
        int totDistance = 1530;
        String nextStop1 = "BrunnsparkenA";
        String nextStop2 = "KungsportsplD";
        String nextStop3 = "ValandD";
        String nextStop4 = "GötaplatsenB";
        ICalculateTravelModel calc = new CalculateTravelModel();
        calc.main(null,null);
        calc.main(nextStop1, "Johanneberg");
        calc.main(nextStop1, "Johanneberg");
        calc.main(nextStop2, "Johanneberg");
        calc.main(null,null);
        calc.main(nextStop2, "Johanneberg");
        calc.main(nextStop3, "Johanneberg");
        calc.main(nextStop3, "Johanneberg");
        calc.main(null,null);
        calc.main(nextStop4, "Johanneberg");
        calc.main(null,"Ej i trafik");
        assertTrue(totDistance == calc.getFinalResult());

    }



}
