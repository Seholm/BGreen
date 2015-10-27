package com.bgreen.filips.bgreen.test.calculatorDioxideTest;

import android.test.InstrumentationTestCase;

import com.bgreen.filips.bgreen.profile.model.ITransformer;
import com.bgreen.filips.bgreen.profile.model.ValueTransformer;

public class CalculateDioxideTest extends InstrumentationTestCase {
    //Tests to see if the correct amount of CO2 is displayed and calculated.
    private ITransformer transformer = new ValueTransformer();
    private int length;

    public void testIfGrams() {
        //Test for simple grams.
        length = 1000;
        String temp = transformer.calculateSpill(length);
        assertTrue(temp.equals("CO₂ sparad: 167.0g"));
    }

    public void testIfKilograms() {
        //Test for transformation to kilogram.
        length = 10000;
        String temp = transformer.calculateSpill(length);
        assertTrue(temp.equals("CO₂ sparad: 1.6kg"));
    }

    public void testIfNull() {
        //Test for null distance
        String temp = transformer.calculateSpill(length);
        assertTrue(temp.equals("CO₂ sparad: 0.0g"));
    }

    public void testIfZero() {
        //Test for zero distance.
        length = 0;
        String temp = transformer.calculateSpill(length);
        assertTrue(temp.equals("CO₂ sparad: 0.0g"));
    }

    public void testUnderChangeKG(){
        //Test for just below the transformation to Kg.
        length = 5988;
        String temp = transformer.calculateSpill(length);
        assertTrue(temp.equals("CO₂ sparad: 999.9g"));
    }

    public void testOverChangeKG() {
        //Test for just above the transformation to Kg.
        length = 5989;
        String temp = transformer.calculateSpill(length);
        assertTrue(temp.equals("CO₂ sparad: 1.0kg"));
    }

}