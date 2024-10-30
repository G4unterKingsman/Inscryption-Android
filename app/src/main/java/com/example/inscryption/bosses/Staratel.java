package com.example.inscryption.bosses;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

import com.example.inscryption.MainActivity;
import com.example.inscryption.R;

public class Staratel extends AppCompatActivity {
    private MediaPlayer sPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staratel);

        sPlayer = MediaPlayer.create(this, R.raw.prospector);
        ImageButton buttonPlay = findViewById(R.id.buttonplay);
        ImageButton buttonPause = findViewById(R.id.buttonpause);

        buttonPause.setVisibility(View.GONE);
        buttonPlay.setOnClickListener(v -> {

            if (MainActivity.mPlayer != null && MainActivity.mPlayer.isPlaying()) {
                MainActivity.mPlayer.pause();
            }
            sPlayer.start();
            buttonPlay.setVisibility(View.GONE);
            buttonPause.setVisibility(View.VISIBLE);

            buttonPause.setOnClickListener(v1 -> {
                sPlayer.pause();
                buttonPause.setVisibility(View.GONE);
                buttonPlay.setVisibility(View.VISIBLE);

                MainActivity.resumeMusic(); // воспроизводим музыку из Главного Меню
            });
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        sPlayer.pause();
        sPlayer.release();
        MainActivity.resumeMusic(); // воспроизводим музыку из Главного Меню
    }
}

