package com.bgreen.filips.bgreen.profile.model;


public interface ITransformer {
    //Method for calculating carbondioxide spill compared to a regular car
    String calculateSpill(int i);
    //Method for converting distance to a better unit. for example meters to kilometers when numbers get high
    String distanceTransformer(int i);
}
