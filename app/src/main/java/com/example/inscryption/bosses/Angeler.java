package com.example.inscryption.bosses;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

import com.example.inscryption.MainActivity;
import com.example.inscryption.R;

public class Angeler extends AppCompatActivity {

    private MediaPlayer aPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_angeler);

        aPlayer = MediaPlayer.create(this, R.raw.angler);
        ImageButton buttonPlay = findViewById(R.id.buttonplay);
        ImageButton buttonPause = findViewById(R.id.buttonpause);
        ImageButton buttonStop = findViewById(R.id.buttonstop);

        buttonPause.setVisibility(View.GONE);
        buttonStop.setVisibility(View.GONE);

        buttonPlay.setOnClickListener(v -> {
            if (MainActivity.mPlayer != null && MainActivity.mPlayer.isPlaying()) {
                MainActivity.mPlayer.pause();
            }

            aPlayer.start();
            buttonPlay.setVisibility(View.GONE);
            buttonPause.setVisibility(View.VISIBLE);
            buttonStop.setVisibility(View.VISIBLE);
        });

        buttonPause.setOnClickListener(v -> {
            aPlayer.pause();
            buttonPause.setVisibility(View.GONE);
            buttonStop.setVisibility(View.GONE);
            buttonPlay.setVisibility(View.VISIBLE);

            MainActivity.resumeMusic(); // воспроизводим музыку из Главного Меню
        });

        buttonStop.setOnClickListener(v -> {
            aPlayer.stop();
            buttonStop.setVisibility(View.GONE);
            buttonPause.setVisibility(View.GONE);
            buttonPlay.setVisibility(View.VISIBLE);

            aPlayer = MediaPlayer.create(this, R.raw.angler);

            MainActivity.resumeMusic(); // воспроизводим музыку из Главного Меню
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        aPlayer.pause();
        aPlayer.release();

        MainActivity.resumeMusic(); // воспроизводим музыку из Главного Меню
    }
}

