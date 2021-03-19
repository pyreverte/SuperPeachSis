package com.m2dl.superpeachsis.actors;

import android.graphics.Rect;
import android.util.Pair;

public abstract class Actor {

    private Pair<Integer, Integer> coordinates;
    private final int surface = 50;
    private final int margin_X = 50;
    private final int margin_Y = 25;
    private int screenWidth;
    private int screenHeight;

    public int getSurface() {
        return surface;
    }

    public int getMargin_X() {
        return margin_X;
    }

    public int getMargin_Y() {
        return margin_Y;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public Pair<Integer, Integer> getCoordinates() {
        return coordinates;
    }

    public void setCoordinate(Pair<Integer, Integer> coordinates) {
        this.coordinates = coordinates;
    }

    public Actor(int screenWidth, int screenHeight) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
    }

    public abstract void refreshCoordinate();

    public abstract Rect getRectangle();
}
