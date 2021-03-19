package com.m2dl.superpeachsis.actors;

import android.graphics.Rect;
import android.util.Pair;

import java.util.Random;

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

    private static int getRandomNumberInRange(int[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

    @Override
    public void refreshCoordinate() {
        int i = getRandomNumberInRange(new int[]{11, 12, 13});
        setCoordinate(new Pair<>(getCoordinates().first - i, getCoordinates().second));
    }
}
