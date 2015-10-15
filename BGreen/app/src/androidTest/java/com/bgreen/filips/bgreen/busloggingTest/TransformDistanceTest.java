package com.bgreen.filips.bgreen.busloggingTest;

import android.test.InstrumentationTestCase;

import com.bgreen.filips.bgreen.profile.ValueTransformer;

    //test for various distance transoformations
public class TransformDistanceTest extends InstrumentationTestCase {
    private ValueTransformer transformer = new ValueTransformer();
    private int length;

    //simple test if it displays 100 meters correctly.
    public void testMeter(){
        length = 100;
        String temp = transformer.distanceTransformer(length);
        assertTrue(temp.equals("100.0 m"));
    }

    //test if it transforms to km after 1000 meters.
    public void testKmeter(){
        length = 1000;
        String temp = transformer.distanceTransformer(length);
        assertTrue(temp.equals("1.0 km"));
    }

    //test if it correctly displays less than 100km as km, and not mil.
    public void testBigKm(){
        length = 10000;
        String temp = transformer.distanceTransformer(length);
        assertTrue(temp.equals("10.0 km"));
    }

    //test if it correctly displays more than 100km as mil.
    public void testMil(){
        length = 100000;
        String temp = transformer.distanceTransformer(length);
        assertTrue(temp.equals("10.0 mil"));
    }

    //test if it displays less than 1000 meters correctly, and not as km.
    public void testTransitionKmeter() {
        length = 999;
        String temp = transformer.distanceTransformer(length);
        assertTrue(temp.equals("999.0 m"));
    }

    //test if it displays less than 100 km correctly.
    public void testTransitionMil() {
        length= 99999;
        String temp = transformer.distanceTransformer(length);
        assertTrue(temp.equals("99.9 km"));

    }
}
