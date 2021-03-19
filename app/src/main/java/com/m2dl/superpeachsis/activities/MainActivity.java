package com.m2dl.superpeachsis.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.m2dl.superpeachsis.R;

public class MainActivity extends Activity {

    private Button newGameButton;
    private Button aboutButton;
    private Button quitButton;
    private Button backButton;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newGameButton = findViewById(R.id.new_game_button);
        newGameButton.setOnClickListener(v -> toGameActivity());

        aboutButton = findViewById(R.id.about_button);
        aboutButton.setOnClickListener(v -> toAboutActivity());

        quitButton = findViewById(R.id.quit_button);
        quitButton.setOnClickListener(v -> System.exit(0));
    }

    public void toGameActivity() {
        Intent i = new Intent(this, GameActivity.class);
        startActivity(i);
    }

    public void toAboutActivity() {
        setContentView(R.layout.activity_about);

        backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> toMainActivity());
    }

    public void toMainActivity() {
        setContentView(R.layout.activity_main);

        newGameButton = findViewById(R.id.new_game_button);
        newGameButton.setOnClickListener(v -> toGameActivity());

        aboutButton = findViewById(R.id.about_button);
        aboutButton.setOnClickListener(v -> toAboutActivity());

        quitButton = findViewById(R.id.quit_button);
        quitButton.setOnClickListener(v -> System.exit(0));
    }
}