package com.bgreen.filips.bgreen;

import android.test.InstrumentationTestCase;

import static junit.framework.Assert.*;

/**
 * Created by flarssonn on 2015-09-21.
 */
public class CalculateTravelInfoTest extends InstrumentationTestCase {

    
    //Test correct startpoint with one stop in total
    public void testStartPoint1(){
        String startPoint = "Götaplatsen";
        String nextStop = "Valand";
        ICalculateTravelInfo calc = new CalculateTravelInfo();
        calc.main(false,nextStop,"Lindholmen");
        assertEquals(startPoint,calc.getStartPoint());
    }

    //Test correct startpoint with multiple stops
    public void testStartPoint2(){
        String startPoint = "Götaplatsen";
        String nextStop1 = "Valand";
        String nextStop2 = "Kungsportsplatsen";
        String nextStop3 = "Brunnsparken";
        String nextStop4 = "Lilla Bommen";
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
        String endPoint = "Valand";
        String nextStop = "Valand";
        ICalculateTravelInfo calc = new CalculateTravelInfo();
        calc.main(false, nextStop, "Lindholmen");
        calc.main(true, nextStop, "Lindholmen");
        assertTrue(endPoint.equals("Valand"));
    }

    //Test correct endpoint with multiple stops in total
    public void testEndPoint2(){
        String endPoint = "Lilla Bommen";
        String nextStop1 = "Valand";
        String nextStop2 = "Kungsportsplatsen";
        String nextStop3 = "Brunnsparken";
        String nextStop4 = "Lilla Bommen";
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
        String latestPoint = "Valand";
        String nextStop1 = "Valand";
        ICalculateTravelInfo calc = new CalculateTravelInfo();
        calc.main(false, nextStop1, "Lindholmen");
        assertTrue(latestPoint.equals(calc.getLatestPoint()));
    }

    //Test correct endpoint with multiple stops in total
    public void testLatestPoint2(){
        String latestPoint = "Brunnsparken";
        String nextStop1 = "Götaplatsen";
        String nextStop2 = "Valand";
        String nextStop3 = "Kungsportsplatsen";
        String nextStop4 = "Brunnsparken";
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

    //Test correct distance calculation with one stop
    public void testCalcTotalDistance1(){
        int totDistance = 10;
        String nextStop1 = "Valand";
        ICalculateTravelInfo calc = new CalculateTravelInfo();
        calc.main(false, nextStop1, "Lindholmen");
        calc.main(true, nextStop1, "Lindholmen");
        assertTrue(totDistance == calc.getFinalResult());

    }

    //Test correct distance calculation with multiple stops
    public void testCalcTotalDistance2(){
        int totDistance = 40;
        String nextStop1 = "Götaplatsen";
        String nextStop2 = "Valand";
        String nextStop3 = "Kungsportsplatsen";
        String nextStop4 = "Brunnsparken";
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

    //Test correct distance calculation with multiple stops and other route
    public void testCalcTotalDistance3(){
        int totDistance = 40;
        String nextStop1 = "Brunnsparken";
        String nextStop2 = "Kungsportsplatsen";
        String nextStop3 = "Valand";
        String nextStop4 = "Götaplatsen";
        ICalculateTravelInfo calc = new CalculateTravelInfo();
        calc.main(false, nextStop1, "Chalmers");
        calc.main(false, nextStop1, "Chalmers");
        calc.main(false, nextStop2, "Chalmers");
        calc.main(false, nextStop2, "Chalmers");
        calc.main(false, nextStop3, "Chalmers");
        calc.main(false, nextStop3, "Chalmers");
        calc.main(false, nextStop4, "Chalmers");
        calc.main(true, nextStop4, "Chalmers");
        assertTrue(totDistance == calc.getFinalResult());

    }

}
