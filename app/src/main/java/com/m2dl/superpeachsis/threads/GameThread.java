package com.m2dl.superpeachsis.threads;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;

import com.m2dl.superpeachsis.actors.Player;
import com.m2dl.superpeachsis.views.GameView;

public class GameThread extends Thread {

    private Player player;
    private final SurfaceHolder surfaceHolder;
    private final GameView gameView;
    private int surface = 50;

    public void setRunning(boolean running) {
        this.running = running;
    }

    public GameThread(SurfaceHolder surfaceHolder, GameView gameView) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;
        this.player = new Player(gameView.getScreenWidth(), gameView.getScreenHeight());
    }

    private boolean running;

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Canvas canvas = null;
            try {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    gameView.draw(canvas);

                    // Player
                    player.refreshCoordinate();
                    drawPlayer(canvas);
                }
            } catch (Exception ignored) {
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void drawPlayer(Canvas canvas) {
        Rect r = new Rect();
        r.left = player.getCoordinates().first - surface;
        r.top = player.getCoordinates().second - surface;
        r.right = player.getCoordinates().first + surface;
        r.bottom = player.getCoordinates().second + surface;
        canvas.drawRect(r, gameView.getPlayerPaint());
    }
}
