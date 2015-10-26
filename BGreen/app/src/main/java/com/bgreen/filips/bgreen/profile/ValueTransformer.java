package com.bgreen.filips.bgreen.profile;


public class ValueTransformer implements ITransformer {
    private String temp = "";
    private double y;

    // calculates the difference between riding the new electric bus compared
    // to riding with an automobile. All numbers are from SIKA, SCB and EOn.
    // If the value is larger than 1000, returns it in form of kg, else g.
    public String calculateSpill(int i) {
        y = i;
        y = y / 1000;
        y = 167 * y;

        if (y >= 1000) {    //checks if the amount is above 1000 or not.
            y = y / 1000;   // if above, change the value to kilograms and check the decimals.
            temp = "CO₂ sparad: " + checkValue(y) + "kg";
        } else {
            temp = "CO₂ sparad: " + checkValue(y) + "g";
        }
        return temp;
    }

    // Transforms the traveled distance to km and mil.
    // If the distance traveled is greater than 1000m, convert to km.
    // If the distance traveled is greater than 100km, 100000m, convert to mil.
    public String distanceTransformer(int i) {
        y = i;

        if (y >= 100000) {
            y = y/10000;
            temp = checkValue(y) + " mil";
        } else if (y >= 1000) {
            y = y/1000;
            temp = checkValue(y) + " km";
        } else {
            temp = y + " m";
        } return temp;
    }


    private String checkValue(double i) {
        //Returns the integer in String format, to check for decimals.
        String tempValue = "" + i;
        String value = tempValue.substring(0, checkDecimal(tempValue) + 1);
        return value;
    }

    private int checkDecimal(String s) {
        //Checks the position of the . in the string, and adds 1 decimal.
        for (int i = 0; i<s.length(); i++) {
            if (s.charAt(i) == '.') {
                return i+1;
            }
        } return 0;
    }
}
