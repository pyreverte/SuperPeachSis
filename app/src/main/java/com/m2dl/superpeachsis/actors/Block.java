package com.m2dl.superpeachsis.actors;

import android.util.Pair;

public class Block extends Enemy {

    public Block(int screenWidth, int screenHeight) {
        super(screenWidth, screenHeight);
    }

    @Override
    public void refreshCoordinate() {
        setCoordinate(new Pair<>(getCoordinates().first - 3, getCoordinates().second));
    }
}
