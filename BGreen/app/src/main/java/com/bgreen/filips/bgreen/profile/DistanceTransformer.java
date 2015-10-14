package com.bgreen.filips.bgreen.profile;

/**
 * Created by paki on 14/10/15.
 */
public class DistanceTransformer {
    //transforms the traveled distance to km and mil.
    public String transformer(int i) {
        if (i >= 10000) {
            return i/10000 + " mil";
        } else if (i >= 1000) {
            return i/1000 + " km";
        } else {
            return i + " m";
        }
    }
}
