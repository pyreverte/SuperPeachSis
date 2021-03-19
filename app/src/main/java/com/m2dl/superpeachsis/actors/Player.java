package com.m2dl.superpeachsis.actors;

import android.graphics.Rect;
import android.util.Pair;

public class Player extends Actor {
    private Integer defaultY;
    private boolean descending;

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

    public void setDescending(boolean descending) {
        this.descending = descending;
    }

    @Override
    public void refreshCoordinate() {
        if (isJumping()) {
            int stride = 3;
            if (jumpHighSkipped()) {
                descending = true;
            }
            if (descending) {
                descending(stride);
            } else {
                ascending(stride + 5);
            }
            jumping = !getCoordinates().second.equals(defaultY);
        } else {
            jumping = false;
            descending = false;
        }
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

    private void ascending(int stride) {
        setCoordinate(new Pair<>(getCoordinates().first, getCoordinates().second - stride));
    }

    private void descending(int stride) {
        setCoordinate(new Pair<>(getCoordinates().first, getCoordinates().second + stride));
    }

    private boolean jumpHighSkipped() {
        return getCoordinates().second < 0.6 * getScreenHeight();
    }
}
