package com.bgreen.filips.bgreen.busloggingTest;

import android.test.InstrumentationTestCase;

import com.bgreen.filips.bgreen.profile.DistanceTransformer;

public class TransformDistanceTest extends InstrumentationTestCase {
    private DistanceTransformer distanceTransformer = new DistanceTransformer();
    private int length;

    public void testMeter(){
        length = 100;
        String temp = distanceTransformer.transformer(length);
        assertTrue(temp.equals("100 m"));
    }

    public void testKmeter(){
        length = 1000;
        String temp = distanceTransformer.transformer(length);
        assertTrue(temp.equals("1 km"));
    }

    public void testMil(){
        length = 10000;
        String temp = distanceTransformer.transformer(length);
        assertTrue(temp.equals("1 mil"));
    }
}
