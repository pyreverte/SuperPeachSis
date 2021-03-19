package com.m2dl.superpeachsis.threads;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.SurfaceHolder;

import com.m2dl.superpeachsis.actors.Block;
import com.m2dl.superpeachsis.actors.Enemy;
import com.m2dl.superpeachsis.actors.Player;
import com.m2dl.superpeachsis.views.GameView;

import java.util.Random;

public class GameThread extends Thread {

    private Player player;
    private Enemy enemy;
    private final SurfaceHolder surfaceHolder;
    private final GameView gameView;
    private int surface = 50;


    // Shake Sensor
    private long lastUpdate;
    private static final int SHAKE_THRESHOLD = 200;
    private float last_x;
    private float last_y;
    private float last_z;
    private float x;
    private float y;
    private float z;

    public void setRunning(boolean running) {
        this.running = running;
    }


    public GameThread(SurfaceHolder surfaceHolder, GameView gameView) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;
        this.player = new Player(gameView.getScreenWidth(), gameView.getScreenHeight());
        this.enemy = null;
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
                    checkShake();
                    // Player
                    player.refreshCoordinate();
                    drawPlayer(canvas);

                    // Enemy
                    if (enemy != null) {
                        enemy.refreshCoordinate();
                        drawEnemy(canvas);
                        checkEnemyIsOnScreen();
                    } else {
                        spawnEnemy();
                    }
                    if (checkCollission()) {
                        gameView.endGame();
                    }
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

    public void checkShake() {
        SensorManager mySensorManager = (SensorManager) gameView.getContext().getSystemService(Context.SENSOR_SERVICE);
        Sensor shakeSensor = mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (shakeSensor != null) {
            mySensorManager.registerListener(
                    shakeSensorListener,
                    shakeSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    private final SensorEventListener shakeSensorListener
            = new SensorEventListener() {

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                long curTime = System.currentTimeMillis();
                // only allow one update every 100ms.
                if ((curTime - lastUpdate) > 100) {
                    long diffTime = (curTime - lastUpdate);
                    lastUpdate = curTime;

                    x = event.values[0];
                    y = event.values[1];
                    z = event.values[2];

                    float speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;
                    if (speed > SHAKE_THRESHOLD) {
                        player.setJumping(true);
                    }
                    last_x = x;
                    last_y = y;
                    last_z = z;
                }
            }
        }
    };

    private static int getRandomNumberInRange(int[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

    private void spawnEnemy() {
        this.enemy = new Block(gameView.getScreenWidth(), gameView.getScreenHeight());
    }

    private void checkEnemyIsOnScreen() {
        if (enemy.getCoordinates().first + surface < 0) {
            this.enemy = null;
        }
    }

    private void drawPlayer(Canvas canvas) {
        canvas.drawRect(getPlayerRect(), gameView.getPlayerPaint());
    }

    private void drawEnemy(Canvas canvas) {
        Rect r = new Rect();
        r.top = enemy.getCoordinates().second - surface;
        r.left = enemy.getCoordinates().first - surface;
        r.right = enemy.getCoordinates().first + surface;
        r.bottom = enemy.getCoordinates().second + surface;
        canvas.drawRect(r, gameView.getEnemyPaint(enemy));
    }

    private boolean checkCollission() {
        Rect rPlayer = getPlayerRect();
        Rect rEnemy = getEnemyRect();

        int x = rPlayer.left;
        int y = rPlayer.top;

        if (rEnemy.contains(x, y)) {
            return true;
        }

        x = rPlayer.left;
        y = rPlayer.bottom;

        if (rEnemy.contains(x, y)) {
            return true;
        }


        x = rPlayer.right;
        y = rPlayer.bottom;

        if (rEnemy.contains(x, y)) {
            return true;
        }

        x = rPlayer.right;
        y = rPlayer.top;

        if (rEnemy.contains(x, y)) {
            return true;
        }

        return false;
    }

    private Rect getEnemyRect() {
        Rect r = new Rect();
        r.left = enemy.getCoordinates().first - surface;
        r.top = enemy.getCoordinates().second - surface;
        r.right = enemy.getCoordinates().first + surface;
        r.bottom = enemy.getCoordinates().second + surface;
        return r;
    }

    private Rect getPlayerRect() {
        Rect r = new Rect();
        r.left = player.getCoordinates().first - surface;
        r.top = player.getCoordinates().second - surface;
        r.right = player.getCoordinates().first + surface;
        r.bottom = player.getCoordinates().second + surface;
        return r;
    }
}
