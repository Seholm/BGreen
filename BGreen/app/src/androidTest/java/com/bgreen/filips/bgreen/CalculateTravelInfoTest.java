package com.bgreen.filips.bgreen;

import android.test.InstrumentationTestCase;

import static junit.framework.Assert.*;

/**
 * Created by flarssonn on 2015-09-21.
 */
public class CalculateTravelInfoTest extends InstrumentationTestCase {

    public void testSetStartPoint1(){
        String startPoint = "Götaplatsen";
        String nextStop = "Valand";
        CalculateTravelInfo calc = new CalculateTravelInfo();
        calc.main(false,nextStop,"Lindholmen");
        assertEquals(startPoint,calc.getStartPoint());
    }
    public void testSetStartPoint2(){
        String startPoint = "Götaplatsen";
        String nextStop = "Brunnsparken";
        CalculateTravelInfo calc = new CalculateTravelInfo();
        calc.main(false,nextStop,"Lindholmen");
        assertFalse(startPoint.equals(calc.getStartPoint()));
    }

}
