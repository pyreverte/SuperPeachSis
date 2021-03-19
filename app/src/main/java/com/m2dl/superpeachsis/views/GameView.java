package com.m2dl.superpeachsis.views;

import android.app.Notification;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.m2dl.superpeachsis.activities.GameActivity;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private int screenWidth;
    private int screenHeight;
    private Paint backgroundPaint = new Paint(Color.DKGRAY);

    public GameView(Context context) {
        super(context);
    }

    public GameView(Context context, int screenHeight, int screenWidth) {
        super(context);
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;

    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            canvas.drawColor(backgroundPaint.getColor());
        }
    }
}
