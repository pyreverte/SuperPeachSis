package com.m2dl.superpeachsis.actors;

import android.util.Pair;

public class Ghost extends Enemy {

    public Ghost(int screenWidth, int screenHeight) {
        super(screenWidth, screenHeight);
    }

    @Override
    public void refreshCoordinate() {
        setCoordinate(new Pair<>(getCoordinates().first - 5, getCoordinates().second));
    }
}
