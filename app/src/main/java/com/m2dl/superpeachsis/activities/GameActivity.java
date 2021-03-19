package com.m2dl.superpeachsis.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;

import com.m2dl.superpeachsis.views.GameView;

public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenHeight = displayMetrics.heightPixels;
        int screenWidth = displayMetrics.widthPixels;

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new GameView(this, screenHeight, screenWidth));

        toEndActivity();
    }

    public void toEndActivity() {
        startActivity(new Intent(this, EndActivity.class));
        finish();
    }
}