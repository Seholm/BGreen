package com.bgreen.filips.bgreen.test.valueTransformerTest;

import android.test.InstrumentationTestCase;

import com.bgreen.filips.bgreen.profile.model.ITransformer;
import com.bgreen.filips.bgreen.profile.model.ValueTransformer;

public class ValueTransformerTest extends InstrumentationTestCase {
    //Tests to see if the correct amount of CO2 is displayed and calculated,
    //and if the calculation for meters to km, to mil works properly.
    private int length;
    private String temp;
    private ITransformer transformer;

    //Test for simple grams.
    public void testIfGrams() {
        transformer = new ValueTransformer();
        length = 1000;
        temp = transformer.calculateSpill(length);
        assertTrue(temp.equals("CO₂ sparad: 167.0g"));
    }

    //Test for transformation to kilogram.
    public void testIfKilograms() {
        transformer = new ValueTransformer();
        length = 10000;
        temp = transformer.calculateSpill(length);
        assertTrue(temp.equals("CO₂ sparad: 1.6kg"));
    }

    //Test for null distance
    public void testIfNull() {
        transformer = new ValueTransformer();
        String temp = transformer.calculateSpill(length);
        assertTrue(temp.equals("CO₂ sparad: 0.0g"));
    }

    //Test for zero distance.
    public void testIfZero() {
        ITransformer transformer = new ValueTransformer();
        length = 0;
        temp = transformer.calculateSpill(length);
        assertTrue(temp.equals("CO₂ sparad: 0.0g"));
    }

    //Test for just below the transformation to Kg.
    public void testUnderChangeKG(){
        ITransformer transformer = new ValueTransformer();
        length = 5988;
        String temp = transformer.calculateSpill(length);
        assertTrue(temp.equals("CO₂ sparad: 999.9g"));
    }

    //Test for just above the transformation to Kg.
    public void testOverChangeKG() {
        transformer = new ValueTransformer();
        length = 5989;
        temp = transformer.calculateSpill(length);
        assertTrue(temp.equals("CO₂ sparad: 1.0kg"));
    }


    //test for various distance transoformations


    //simple test if it displays 100 meters correctly.
    public void testMeter(){
        transformer = new ValueTransformer();
        length = 100;
        temp = transformer.distanceTransformer(length);
        assertTrue(temp.equals("100.0 m"));
    }

    //test if it transforms to km after 1000 meters.
    public void testKMeter(){
        transformer = new ValueTransformer();
        length = 1000;
        temp = transformer.distanceTransformer(length);
        assertTrue(temp.equals("1.0 km"));
    }

    //test if it correctly displays less than 100km as km, and not mil.
    public void testBigKm(){
        transformer = new ValueTransformer();
        length = 10000;
        temp = transformer.distanceTransformer(length);
        assertTrue(temp.equals("10.0 km"));
    }

    //test if it correctly displays more than 100km as mil.
    public void testMil(){
        transformer = new ValueTransformer();
        length = 100000;
        temp = transformer.distanceTransformer(length);
        assertTrue(temp.equals("10.0 mil"));
    }

    //test if it displays less than 1000 meters correctly, and not as km.
    public void testTransitionKMeter() {
        transformer = new ValueTransformer();
        length = 999;
        temp = transformer.distanceTransformer(length);
        assertTrue(temp.equals("999.0 m"));
    }

    //test if it displays less than 100 km correctly.
    public void testTransitionMil() {
        transformer = new ValueTransformer();
        length= 99999;
        temp = transformer.distanceTransformer(length);
        assertTrue(temp.equals("99.9 km"));

    }

}