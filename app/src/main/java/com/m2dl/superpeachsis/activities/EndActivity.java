package com.m2dl.superpeachsis.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.m2dl.superpeachsis.R;

public class EndActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        SharedPreferences sharedPreferences = this.getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE);
        String score = String.valueOf(sharedPreferences.getInt("score", 0));
        TextView textView = findViewById(R.id.score_value);
        this.runOnUiThread(() -> textView.setText(score));
        // Retry Button
        final Button retryButton = findViewById(R.id.retryButton);
        retryButton.setOnClickListener(v -> toGameActivity());

        // Main Menu Button
        final Button mainMenuButton = findViewById(R.id.mainMenuButton);
        mainMenuButton.setOnClickListener(v -> toMenuActivity());
    }

    public void toGameActivity() {
        startActivity(new Intent(this, GameActivity.class));
        finish();
    }

    public void toMenuActivity() {
        finish();
    }
}
