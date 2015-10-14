package com.bgreen.filips.bgreen.profile;


public class CarbonDioxideCalculator {
    private String temp = "";
    // calculates the difference between riding the new electric bus compared
    // to riding with an automobile. All numbers are from SIKA, SCB and EOn.
    // If the value is larger than 1000, returns it in form of kg, else g.
    public String calculateSpill(int i) {
        i = i / 1000;
        i = 167 * i;
        if (i > 1000) {
            i = i / 1000;
            temp = "CO2 sparad: " + i + "kg";
        } else {
            temp = "CO2 sparad: " + i + "g";
        }
        return temp;
    }
}
