package com.m2dl.superpeachsis.actors;

import android.util.Pair;

public class Player extends Actor {
    private Integer defaultY;

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    private boolean jumping;

    public Player(int screenWidth, int screenHeight) {
        super(screenWidth, screenHeight);
        this.jumping = false;
        this.defaultY = screenHeight - getSurface() - getMargin_Y();
        setCoordinate(new Pair<>(getSurface() + getMargin_X(), screenHeight - getSurface() - getMargin_Y()));
    }

    @Override
    public void refreshCoordinate() {
        if (!getCoordinates().second.equals(defaultY) && isJumping()) {
            int stride = 3;
            if (getCoordinates().second > 0.2 * getScreenWidth()) { // Pic du saut atteint ?
                setCoordinate(new Pair<>(getCoordinates().first, getCoordinates().second - stride));
            } else {
                setCoordinate(new Pair<>(getCoordinates().first, getCoordinates().second + stride));
            }
        } else {
            jumping = false;
        }
    }
}
