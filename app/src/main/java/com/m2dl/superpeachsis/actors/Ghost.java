package com.m2dl.superpeachsis.actors;

import android.graphics.Rect;
import android.util.Pair;

public class Ghost extends Enemy {

    public Ghost(int screenWidth, int screenHeight) {
        super(screenWidth, screenHeight);
    }

    @Override
    public Rect getRectangle() {
        Rect r = new Rect();
        r.top = this.getCoordinates().second - (getSurface() * 5);
        r.right = this.getCoordinates().first;
        r.bottom = this.getCoordinates().second;
        r.left = this.getCoordinates().first - (getSurface() * 5);
        return r;
    }

    @Override
    public void refreshCoordinate() {
        setCoordinate(new Pair<>(getCoordinates().first - 5, getCoordinates().second));
    }
}
