package com.m2dl.superpeachsis.actors;

import android.graphics.Rect;
import android.util.Pair;

public class Block extends Enemy {

    public Block(int screenWidth, int screenHeight) {
        super(screenWidth, screenHeight);
    }

    @Override
    public Rect getRectangle() {
        Rect r = new Rect();
        r.left = this.getCoordinates().first - getSurface();
        r.top = this.getCoordinates().second - getSurface();
        r.right = this.getCoordinates().first + getSurface();
        r.bottom = this.getCoordinates().second + getSurface();
        return r;
    }

    @Override
    public void refreshCoordinate() {
        setCoordinate(new Pair<>(getCoordinates().first - 5, getCoordinates().second));
    }
}
