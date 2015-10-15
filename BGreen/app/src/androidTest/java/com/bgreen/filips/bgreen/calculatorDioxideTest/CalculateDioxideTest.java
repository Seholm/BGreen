package com.bgreen.filips.bgreen.calculatorDioxideTest;

import android.test.InstrumentationTestCase;

import com.bgreen.filips.bgreen.profile.ValueTransformer;
import com.bgreen.filips.bgreen.profile.IProfile;
import com.bgreen.filips.bgreen.profile.User;

public class CalculateDioxideTest extends InstrumentationTestCase {
    //Tests to see if the correct amount of CO2 is displayed and calculated.
    private ValueTransformer calculator = new ValueTransformer();
    private IProfile profile = User.getInstance();

    public void testIfGrams() {
        //Test for simple grams.
        profile.setTotalDistance(1000);
        String temp = calculator.calculateSpill(profile.getTotalDistance());
        assertTrue(temp.equals("CO² sparad: 167.0g"));
    }

    public void testIfKilograms() {
        //Test for transformation to kilogram.
        profile.setTotalDistance(10000);
        String temp = calculator.calculateSpill(profile.getTotalDistance());
        assertTrue(temp.equals("CO² sparad: 1.6kg"));
    }

    public void testIfZero() {
        //Test for zero distance.
        profile.setTotalDistance(0);
        String temp = calculator.calculateSpill(profile.getTotalDistance());
        assertTrue(temp.equals("CO² sparad: 0.0g"));
    }

    public void testUnderChangeKG(){
        //Test for just below the transformation to Kg.
        profile.setTotalDistance(5988);
        String temp = calculator.calculateSpill(profile.getTotalDistance());
        assertTrue(temp.equals("CO² sparad: 999.9g"));
    }

    public void testOverChangeKG() {
        //Test for just above the transformation to Kg.
        profile.setTotalDistance(5989);
        String temp = calculator.calculateSpill(profile.getTotalDistance());
        assertTrue(temp.equals("CO² sparad: 1.0kg"));
    }

}