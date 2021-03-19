package com.m2dl.superpeachsis.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.m2dl.superpeachsis.activities.GameActivity;
import com.m2dl.superpeachsis.actors.Enemy;
import com.m2dl.superpeachsis.threads.GameThread;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private GameActivity gameActivity;
    private GameThread gameThread;

    public Paint getPlayerPaint() {
        return playerPaint;
    }

    public Paint getEnemyPaint(Enemy enemy) {
        return enemyBlockPaint;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    private int screenWidth;
    private int screenHeight;
    private final Paint backgroundPaint = new Paint() {{
        setColor(Color.rgb(180, 180, 255));
    }};

    private final Paint playerPaint = new Paint() {{
        setColor(Color.rgb(44, 180, 46));
    }};

    private final Paint enemyBlockPaint = new Paint() {{
        setColor(Color.RED);
    }};

    private final Paint enemyBarrierPaint = new Paint() {{
        setColor(Color.BLUE);
    }};

    private final Paint enemyGhostPaint = new Paint() {{
        setColor(Color.WHITE);
    }};

    public GameView(Context context) {
        super(context);
    }

    public GameView(Context context, int screenHeight, int screenWidth, GameActivity gameActivity) {
        super(context);
        this.gameActivity = gameActivity;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        getHolder().addCallback(this);
        this.gameThread = new GameThread(getHolder(), this);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        gameThread.setRunning(true);
        gameThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        boolean retry = true;
        gameThread.setRunning(false);
        while (retry) {
            try {
                gameThread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            canvas.drawColor(backgroundPaint.getColor());
        }
    }


    public void endGame() {
        gameThread.setRunning(false);
        gameActivity.toEndActivity();
    }
}
