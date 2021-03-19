package com.m2dl.superpeachsis.actors;

import android.graphics.Rect;
import android.util.Pair;

public abstract class Enemy extends Actor {

    private boolean isDeadly;

    public boolean isDeadly() {
        return isDeadly;
    }

    public void setDeadly(boolean deadly) {
        this.isDeadly = deadly;
    }

    public Enemy(int screenWidth, int screenHeight) {
        super(screenWidth, screenHeight);
        this.isDeadly = true;
        setCoordinate(new Pair<>(screenWidth + getSurface(), screenHeight - getSurface() - getMargin_Y()));
    }


}
