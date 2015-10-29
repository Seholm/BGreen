package com.bgreen.filips.bgreen.toplist;

/**
 * Created by Filips on 10/3/2015.
 */
public interface IFlipcard {
    // A interface used to flip a card on a specific position. Used to not get circulair dependencies from ToplistAdapter
    public void flipCard(int position);
}
