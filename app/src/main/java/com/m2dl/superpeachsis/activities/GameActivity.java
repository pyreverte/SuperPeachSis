package com.m2dl.superpeachsis.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;

import com.m2dl.superpeachsis.views.GameView;

public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenHeight = displayMetrics.heightPixels;
        int screenWidth = displayMetrics.widthPixels;

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new GameView(this, screenHeight, screenWidth));
    }
}