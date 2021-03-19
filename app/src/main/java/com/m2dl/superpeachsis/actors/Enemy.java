package com.m2dl.superpeachsis.actors;

import android.graphics.Rect;
import android.util.Pair;

import java.util.Random;

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

    private static int getRandomNumberInRange(int[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

    @Override
    public void refreshCoordinate() {
        int i = getRandomNumberInRange(new int[]{9, 10, 11});
        setCoordinate(new Pair<>(getCoordinates().first - i, getCoordinates().second));
    }
}
