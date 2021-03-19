package com.m2dl.superpeachsis.actors;

import android.graphics.Rect;
import android.util.Pair;

public class Barrier extends Enemy {

    public Barrier(int screenWidth, int screenHeight) {
        super(screenWidth, screenHeight);
    }

    @Override
    public Rect getRectangle() {
        Rect r = new Rect();
        r.top = this.getCoordinates().second - (getSurface() * 5);
        r.left = this.getCoordinates().first - getSurface();
        r.right = this.getCoordinates().first + getSurface();
        r.bottom = this.getCoordinates().second + getSurface();
        return r;
    }

    @Override
    public void refreshCoordinate() {
        setCoordinate(new Pair<>(getCoordinates().first - 5, getCoordinates().second));
    }
}
