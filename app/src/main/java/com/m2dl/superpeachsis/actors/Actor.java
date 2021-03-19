package com.m2dl.superpeachsis.actors;

import android.util.Pair;

public abstract class Actor {

    private Pair<Integer, Integer> coordinates;

    public int getSurface() {
        return surface;
    }

    public int getMargin_X() {
        return margin_X;
    }

    private final int surface = 50;
    private final int margin_X = 50;
    private final int margin_Y = 25;


    public int getMargin_Y() {
        return margin_Y;
    }


    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    private int screenWidth;
    private int screenHeight;

    public Actor(int screenWidth, int screenHeight) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
    }

    public Pair<Integer, Integer> getCoordinates() {
        return coordinates;
    }

    public void setCoordinate(Pair<Integer, Integer> coordinates) {
        this.coordinates = coordinates;
    }

    public abstract void refreshCoordinate();
}
