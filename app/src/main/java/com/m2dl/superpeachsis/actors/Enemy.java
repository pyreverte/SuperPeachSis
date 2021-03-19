package com.m2dl.superpeachsis.actors;

import android.util.Pair;

public abstract class Enemy extends Actor {

    public Enemy(int screenWidth, int screenHeight) {
        super(screenWidth, screenHeight);
        setCoordinate(new Pair<>(screenWidth + getSurface(), screenHeight - getSurface() - getMargin_Y()));
    }
}
