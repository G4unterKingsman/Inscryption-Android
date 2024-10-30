package com.example.inscryption.bosses;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

import com.example.inscryption.MainActivity;
import com.example.inscryption.R;

public class Trader extends AppCompatActivity {
    private MediaPlayer tPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trader);

        tPlayer = MediaPlayer.create(this, R.raw.trader);
        ImageButton buttonPlay = findViewById(R.id.buttonplay);
        ImageButton buttonPause = findViewById(R.id.buttonpause);
        ImageButton buttonStop = findViewById(R.id.buttonstop);

        buttonPause.setVisibility(View.GONE);
        buttonStop.setVisibility(View.GONE);

        buttonPlay.setOnClickListener(v -> {
            if (MainActivity.mPlayer != null && MainActivity.mPlayer.isPlaying()) {
                MainActivity.mPlayer.pause();
            }

            tPlayer.start();
            buttonPlay.setVisibility(View.GONE);
            buttonPause.setVisibility(View.VISIBLE);
            buttonStop.setVisibility(View.VISIBLE);
        });

        buttonPause.setOnClickListener(v -> {
            tPlayer.pause();
            buttonPause.setVisibility(View.GONE);
            buttonStop.setVisibility(View.GONE);
            buttonPlay.setVisibility(View.VISIBLE);

            MainActivity.resumeMusic(); // воспроизводим музыку из Главного Меню
        });

        buttonStop.setOnClickListener(v -> {
            tPlayer.stop();
            buttonStop.setVisibility(View.GONE);
            buttonPause.setVisibility(View.GONE);
            buttonPlay.setVisibility(View.VISIBLE);

            tPlayer = MediaPlayer.create(this, R.raw.trader);

            MainActivity.resumeMusic(); // воспроизводим музыку из Главного Меню
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        tPlayer.pause();
        tPlayer.release();
        MainActivity.resumeMusic(); // воспроизводим музыку из Главного Меню
    }
}

