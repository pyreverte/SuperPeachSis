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

import com.m2dl.superpeachsis.actors.Barrier;
import com.m2dl.superpeachsis.actors.Block;
import com.m2dl.superpeachsis.actors.Enemy;
import com.m2dl.superpeachsis.actors.Ghost;
import com.m2dl.superpeachsis.actors.Player;
import com.m2dl.superpeachsis.views.GameView;

import java.util.Random;

public class GameThread extends Thread {

    private Player player;

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

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

                    checkLight();
                    // Enemy
                    if (enemy != null) {
                        enemy.refreshCoordinate();
                        drawEnemy(canvas);
                        checkEnemyIsOnScreen();
                    } else {
                        spawnEnemy();
                    }
                    if (enemy instanceof Ghost) {
                        if (enemy.isDeadly()) {
                            if (checkCollission()) {
                                gameView.endGame();
                            }
                        }

                    }
                    else {
                        if (checkCollission()) {
                            gameView.endGame();
                        }
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
                    Log.v("SENSOR", "Speed : " + speed);
                    if (speed > SHAKE_THRESHOLD) {
                        // TODO Shake Detected
                        Log.v("SENSOR", "Shake Detected");
                        player.setJumping(true);
                    }
                    last_x = x;
                    last_y = y;
                    last_z = z;
                }
            }
        }
    };

    public void checkLight() {
        SensorManager mySensorManager = (SensorManager)gameView.getContext().getSystemService(Context.SENSOR_SERVICE);
        Sensor lightSensor = mySensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if(lightSensor != null){
            mySensorManager.registerListener(
                    lightSensorListener,
                    lightSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);

        }
    }

    private final SensorEventListener lightSensorListener
            = new SensorEventListener(){

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if(event.sensor.getType() == Sensor.TYPE_LIGHT){
                if (event.values[0] < 10) {
                    if (enemy instanceof Ghost) {
                        enemy.setDeadly(false);
                    }
                }
                else {
                    if (enemy instanceof Ghost) {
                        enemy.setDeadly(true);
                    }
                }
            }
        }

    };

    private static int getRandomNumberInRange(int[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

    private void spawnEnemy() {
        int i = getRandomNumberInRange(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9});
        if (i < 6) {
            this.enemy = new Block(gameView.getScreenWidth(), gameView.getScreenHeight());
        } else if (6 <= i && i < 9) {
            this.enemy = new Barrier(gameView.getScreenWidth(), gameView.getScreenHeight());
        } else {
            this.enemy = new Ghost(gameView.getScreenWidth(), gameView.getScreenHeight());
        }
    }

    private void checkEnemyIsOnScreen() {
        if (enemy.getCoordinates().first + surface < 0) {
            this.enemy = null;
        }
    }

    private void drawPlayer(Canvas canvas) {
        canvas.drawRect(player.getRectangle(), gameView.getPlayerPaint());
    }

    private void drawEnemy(Canvas canvas) {
        canvas.drawRect(enemy.getRectangle(), gameView.getEnemyPaint(enemy));
    }

    private boolean checkCollission() {
        Rect rPlayer = player.getRectangle();
        int x_A = rPlayer.left;
        int y_A = rPlayer.top;
        int x_B = rPlayer.left;
        int y_B = rPlayer.bottom;
        int x_C = rPlayer.right;
        int y_C = rPlayer.bottom;
        int x_D = rPlayer.right;
        int y_D = rPlayer.top;
        Rect rEnemy = enemy.getRectangle();
        if (rEnemy.contains(x_A, y_A) || rEnemy.contains(x_B, y_B) || rEnemy.contains(x_C, y_C) || rEnemy.contains(x_D, y_D)) {
            return true;
        }

        return false;
    }
}
