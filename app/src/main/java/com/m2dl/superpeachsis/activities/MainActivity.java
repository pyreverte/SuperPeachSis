package com.m2dl.superpeachsis.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Button;

import com.m2dl.superpeachsis.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button newGameButton = findViewById(R.id.new_game_button);
        newGameButton.setOnClickListener(v -> toGameActivity());

        final Button aboutButton = findViewById(R.id.about_button);
        // TODO aboutButton.setOnClickListener(v -> toAboutActivity());

        final Button quitButton = findViewById(R.id.quit_button);
        quitButton.setOnClickListener(v -> System.exit(0));
    }

    public void toGameActivity() {
        Intent i = new Intent(this, GameActivity.class);
        startActivity(i);
    }
}